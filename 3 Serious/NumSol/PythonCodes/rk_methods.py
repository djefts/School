from pprint import pprint
from gen_RK_array import RKArray
import math
import numpy as np
import matplotlib.pyplot as plt


def exact(t, y):
    return np.exp(-0.5 * np.power((t - 2), 2))


def f(t, y):
    return (2 - t) * y


def rk(array, t, h, y):
    if array[0][-1] is not None or array[-1][0] != 0:
        raise
    order = len(array)
    for n in range(y - 1):
        
        # calculate phi for each step
        phi = 0
        k = [0] * order
        for r in range(1, order):
            a = array[0][r]
            summation = 0
            for s in range(1, r - 1):
                b = array[r][s]
                summation += b * k[s]
            c = array[-1][r]
            k[r] = f(t[n] + a * h, y[n] + h * summation)
            phi += c * k[r]
        
        y[n + 1] = y[n] + h * phi


def rk2(t, h, y0, N):
    y = np.zeros(N)
    y[0] = y0
    for n in range(N - 1):
        K1 = f(t[n], y[n])
        K2 = f(t[n] + h, y[n] + h * K1)
        y[n + 1] = y[n] + (h / 2) * (K1 + K2)
    return t, y


# input data
N = 20
t0 = 2
tmax = 7
y0 = 1
# starting data
t, h = np.linspace(t0, tmax, N, retstep = True)
y = [0] * len(t)
y[0] = y0

print(t0, "< t <", tmax)
print("h =", h)
print("y(t0) =", y0)
butcher = RKArray('RK4', 'Classic')
print("RK Array:")
pprint(butcher.array, indent = 2, )
