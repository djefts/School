import numpy as np
import matplotlib.pyplot as plt
from scipy import integrate


def integrand(t):
    return np.exp((-t)**2)


def exact(t):
    # problem 2:
    # return np.exp(-0.5 * np.power((t - 2), 2))
    
    # problem 3:
    y = [0] * len(t)
    for n in range(len(t)):
        I = integrate.quad(integrand, 0, t[n])
        y[n] = (2 / np.sqrt(np.pi)) * I[0]
    return y


def rhs(y, t):
    yprime = -3 * t * y**2 + 1 / (1 + t**3)
    return yprime


def f(t, y):
    # problem 2:
    # return (2 - t) * y
    # problem 3:
    return (2 / np.sqrt(np.pi)) * np.exp((-t)**2)


def euler(t0, tmax, y0, N):
    t, dt = np.linspace(t0, tmax, N, retstep = True)
    y = np.zeros(N)
    y[0] = y0
    for n in range(N - 1):
        y[n + 1] = y[n] + dt * f(t[n], y[n])
    return t, y


def rk2(t0, tmax, y0, N):
    t, dt = np.linspace(t0, tmax, N, retstep = True)
    y = np.zeros(N)
    y[0] = y0
    for n in range(N - 1):
        K1 = f(t[n], y[n])
        K2 = f(t[n] + dt, y[n] + dt * K1)
        y[n + 1] = y[n] + dt * (K1 + K2) / 2
    return t, y


def rk4(t0, tmax, y0, N):
    t, dt = np.linspace(t0, tmax, N, retstep = True)
    y = np.zeros(N)
    y[0] = y0
    for n in range(N - 1):
        K1 = f(t[n], y[n])
        K2 = f(t[n] + 0.5 * dt, y[n] + 0.5 * dt * K1)
        K3 = f(t[n] + 0.5 * dt, y[n] + 0.5 * dt * K2)
        K4 = f(t[n] + dt, y[n] + dt * K3)
        y[n + 1] = y[n] + dt * (K1 + 2 * K2 + 2 * K3 + K4) / 6
    return t, y


# problem 2:
# t0 = 2
# tmax = 7
# y0 = 1
# N = 20
# problem 3:
t0 = 0
tmax = 2
y0 = 0


# [t, Y1] = euler(t0, tmax, y0, N)
# [t, Y2] = rk2(t0, tmax, y0, N)
# [t, Y3] = rk4(t0, tmax, y0, N)
# y = exact(t)
#
# plt.plot(t, y, 'd-', t, Y1, '-', t, Y2, '-', t, Y3, ':')
# plt.title("IVP: $y'=(2 / √π) * e^{-t^2}$")
# plt.legend(['Exact Sol', 'Euler', 'RK2', 'RK4'], loc = 'best')
# plt.ylabel('y(t)')
# plt.xlabel('t')
# plt.grid()
# plt.show()
#
# abs_err1 = abs(y - Y1)
# abs_err2 = abs(y - Y2)
# abs_err3 = abs(y - Y3)

print("================================================================")
print("   n \ttn\t\t\tEuler_err\tRK2_err\t\tRK4_err", end = '\n')
for N in [10, 50, 100, 500, 1000]:
    _, h = np.linspace(t0, tmax, N, retstep = True)
    [t, Y1] = euler(t0, tmax, y0, N)
    [t, Y2] = rk2(t0, tmax, y0, N)
    [t, Y3] = rk4(t0, tmax, y0, N)
    y = exact(t)

    abs_err1 = abs(y - Y1)
    abs_err2 = abs(y - Y2)
    abs_err3 = abs(y - Y3)
    
    print('{0:4d} \t{1:.6f}\t{2:0.6f}\t{3:0.6f}\t{4:0.6f}'.format(N, t[-1], abs_err1[-1], abs_err2[-1], abs_err3[-1]))
print("================================================================")
# print("================================================================")
# print("Input Data:")
# print(t0, "< t <", tmax)
# print("h =", h)
# print("================================================================")
# print("y(t0) =", y0)
# print("  n\ttn\t\t\tRK4_err", end = '\n')
# for n in range(N):
#     print('{0:3d}\t{1:.6f}\t{2:0.6f}'.format(n, t[n], abs_err3[n]))
# print("================================================================")
