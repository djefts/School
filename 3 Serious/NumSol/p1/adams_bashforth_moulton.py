import sys
import time

import numpy as np
import matplotlib.pyplot as plt
from scipy.integrate import odeint
from mpl_toolkits.mplot3d import Axes3D

"""
Write a code in Matlab/Python to implement the Adams-Bashforth-Moulton method of fourth order for the autonomous
system of ODEs.

~Yn+1 = Yn + (h/24) * [55*F(Yn) − 59*F(Yn−1) + 37*F(Yn−2) − 9*F(Yn−3)]  -   Adams–Bashforth method
Yn+1 = Yn + (h/24) * [9*F(~Yn+1) + 19*F(Yn) − 5*F(Yn−1) + F(Yn−2)]      -   Adams–Moulton method

Use it to solve the following well known Lorenz problem that arises in the study of dynamical systems
dy1 = 10*(y2 - y1)          y1 is proportional to the rate of convection
dy2 = y1*(28 - y3) - y2     y2 is proportional to the horizontal temperature variation
dy3 = y1*y2 - (8/3)*y3      y3 is proportional to the vertical temperature variation
with initial conditions y1(0) = 15, y2(0) = 15, y3(0) = 36. Plot the solution curves for 0 ≤ t ≤ 20.
"""


def f(state, t):
    x, y, z = state  # unpack the state vector
    return sigma * (y - x), x * (rho - z) - y, x * y - beta * z  # derivatives


def rk4(rhs, y, t):
    M = len(y)
    N = len(t)
    Y = np.zeros((N, M))
    Y[0, :] = y
    dt = (t[-1] - t[0]) / N
    for n in range(N - 1):
        K1 = rhs(y, t[n])
        K2 = rhs(y + np.multiply(dt / 2, K1), t[n] + dt / 2)
        K3 = rhs(y + np.multiply(dt / 2, K2), t[n] + dt / 2)
        K4 = rhs(y + np.multiply(dt, K3), t[n] + dt)
        y = y + (np.multiply(dt / 6, K1) +
                 np.multiply(dt / 3, K2) +
                 np.multiply(dt / 3, K3) +
                 np.multiply(dt / 6, K4))
        Y[n + 1, :] = y
    return Y


# lorenz parameters
rho = 28
sigma = 10.0
beta = 8.0 / 3.0

""" START OF MAIN """
t0 = 0
tmax = 20
N = 2001
t, h = np.linspace(t0, tmax, N, retstep = True)
# print('T:', list(t))

state0 = [15, 15, 36]

""" get the first 3 spots in each function """
states = rk4(f, state0, t[0:4])

y1 = states[0]
y2 = states[1]
y3 = states[2]
print("Initial + 3 Orders:", states[0:4], sep = '\n', end = '\n\n')
time.sleep(0.1)

for n in range(3, N):
    state = states[n]
    # print(state)
    # calculate ~Y values
    # ~Yn+1 = Yn + (h/24) * [55*F(Yn) − 59*F(Yn−1) + 37*F(Yn−2) − 9*F(Yn−3)]
    y_tilde = state + (h / 24) * (np.multiply(55, f(state, t[n]))
                                  - np.multiply(59, f(states[n - 1], t[n]))
                                  + np.multiply(37, f(states[n - 2], t[n]))
                                  - np.multiply(9, f(states[n - 3], t[n])))
    # calculate next Y value
    # Yn+1 = Yn + (h/24) * [9*F(~Yn+1) + 19*F(Yn) − 5*F(Yn−1) + F(Yn−2)]
    state1 = states[n] + (h / 24) * (np.multiply(9, f(y_tilde, t[n]))
                                     + np.multiply(19, f(state, t[n]))
                                     - np.multiply(5, f(states[n - 1], t[n]))
                                     + np.multiply(1, f(states[n - 2], t[n])))
    states = np.vstack((states, state1))

""" plot everything """
y1 = []
y2 = []
y3 = []
print("=========================================================================")
print("    n\t y1\t\t\t\t\ty2\t\t\t\t\t y3", end = '\n')
for n in range(N):
    y_1 = states[n][0]
    y1.append(y_1)
    y_2 = states[n][1]
    y2.append(y_2)
    y_3 = states[n][2]
    y3.append(y_3)
    print(' {:4d}\t{:0.15f}\t{:0.15f}\t{:0.15f}'.format(n, y_1, y_2, y_3))
print("=========================================================================")

plt.plot(t, y1, '-', t, y2, '-', t, y3, '+')
plt.title("Adams-Bashforth-Moulton method of fourth order Lorenz Problem")
plt.legend(['y1', 'y2', 'y3'], loc = 'best')
plt.ylabel('y_x(t)')
plt.xlabel('t')
plt.grid()
plt.show()

time.sleep(0.1)

# 3D representation
title = 'Lorenz System - $\\rho ={:.4g}, \\sigma ={:.4g}, \\beta ={:.4g}$'.format(rho, sigma, beta)
fig = plt.figure()
ax = fig.gca(projection = '3d')
# fig.set_title(title)
ax.plot(y1, y2, y3)
ax.set_title(title)
plt.show()
