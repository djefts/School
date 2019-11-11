#!/usr/bin/env python3
import numpy as np
import matplotlib.pyplot as plt
from scipy.integrate import odeint, solve_ivp

"""
MA448 - Classwork 3. Solving IVP using SciPy odeinit and solve_ivp 
"""


def f(t, ind):
    x = ind[0]
    y = ind[1]
    f1 = x + y - 4 * t**2 + t**3  # x function
    f2 = x - y + 2 * t - t**2 - t**3  # y function
    return [f1, f2]


def exact_x(t):
    x = np.zeros(len(t))
    for n in range(N):
        x[n] = np.exp(t[n]) * np.cos(t[n]) + t[n]**2
    return x


def exact_y(t):
    y = np.zeros(len(t))
    for n in range(N):
        y[n] = np.exp(t[n]) * np.sin(t[n]) + t[n]**3
    return y


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


def rk4(rhs, y, t):
    M = len(y)
    N = len(t)
    Y = np.zeros((N, M))
    Y[0, :] = y
    dt = (t[-1] - t[0]) / N
    for n in range(N - 1):
        K1 = rhs(t[n], y)
        K2 = rhs(t[n] + dt / 2, y + np.multiply(dt / 2, K1))
        K3 = rhs(t[n] + dt / 2, y + np.multiply(dt / 2, K2))
        K4 = rhs(t[n] + dt, y[n] + y + np.multiply(dt, K3))
        y = y + (np.multiply(dt / 6, K1) +
                 np.multiply(dt / 3, K2) +
                 np.multiply(dt / 3, K3) +
                 np.multiply(dt / 6, K4))
        Y[n + 1, :] = y
    return t, y


def rkf45(rhs, y, t):
    M = len(y)
    N = len(t)
    Y = np.zeros((N, M))
    est_err = np.zeros((N, M))
    err_x = np.zeros(N)
    err_y = np.zeros(N)
    Y[0, :] = y
    dt = (t[-1] - t[0]) / N
    for n in range(N - 1):
        K1 = rhs(t[n], y)
        K2 = rhs(t[n] + (1 / 4) * dt, y + np.multiply(dt / 4, K1))
        K3 = rhs(t[n] + (3 / 8) * dt, y + np.multiply(dt,
                                                      np.multiply(3 / 32, K1)
                                                      + np.multiply(9 / 32, K2)))
        K4 = rhs(t[n] + (12 / 13) * dt, y + np.multiply(dt,
                                                        np.multiply(1932 / 2197, K1)
                                                        - np.multiply(7200 / 2197, K2)
                                                        + np.multiply(7296 / 2197, K3)))
        K5 = rhs(t[n] + dt, y + np.multiply(dt,
                                            np.multiply(439 / 216, K1)
                                            - np.multiply(8, K2)
                                            + np.multiply(3680 / 513, K3)
                                            - np.multiply(845 / 4104, K4)))
        K6 = rhs(t[n] + (1 / 2) * dt, y + np.multiply(dt,
                                                      np.multiply(-8 / 27, K1)
                                                      + np.multiply(2, K2)
                                                      - np.multiply(3544 / 2565, K3)
                                                      + np.multiply(1859 / 4104, K4)
                                                      - np.multiply(11 / 40, K5)))
        ystar = y + np.multiply(dt,
                                np.multiply(25 / 216, K1)
                                + np.multiply(1408 / 2565, K3)
                                + np.multiply(2197 / 4104, K4)
                                - np.multiply(1 / 5, K5))
        y = y + np.multiply(dt,
                            np.multiply(16 / 135, K1)
                            + np.multiply(6656 / 12825, K3)
                            + np.multiply(28561 / 56430, K4)
                            - np.multiply(9 / 50, K5)
                            + np.multiply(2 / 55, K6))
        Y[n + 1, :] = y
        err_x[n + 1] = abs(y[0] - ystar[0])
        err_y[n + 1] = abs(y[1] - ystar[1])
        est_err[n + 1] = [err_x[n + 1], err_y[n + 1]]
    return t, Y, est_err


if __name__ == '__main__':
    t0 = 0
    tmax = 1
    x0 = 1
    y0 = 0
    N = 10
    
    t, dt = np.linspace(t0, tmax, N, retstep = True)
    t = list(t)
    
    [t, Y, est_err] = rkf45(f, [x0, y0], t)
    x = exact_x(t)
    y = exact_y(t)
    
    abs_err_x = abs(x - Y[:, 0])
    abs_err_y = abs(y - Y[:, 1])
    
    print("=========================================================================")
    print("  n\t RKF5 Error X\t\t RKF5 Error Y", end = '\n')
    for n in range(N):
        print('{0:3d}\t{1:0.15f}\t{2:0.15f}'.format(n, abs_err_x[n], abs_err_y[n]))
    print("=========================================================================")
    
    # Y3 = odeint(f, (x0, y0), t, tfirst = True)
    Y4 = solve_ivp(f, [t0, tmax], [x0, y0], method = 'RK45', t_eval = t)
    
    plt.plot(t, y, '-', t, x, '-', Y4.t, Y4.y[0], '+', t, Y, 'o')
    plt.title("IVP: $y'=f(t, x, y), x'=f(t, x, y), y(0)=0, x(0)=1$")
    plt.legend(['Exact Sol Y', 'Exact Sol X', 'solve_ivp', 'RK45'], loc = 'best')
    plt.ylabel('y(t)')
    plt.xlabel('t')
    plt.grid()
    plt.show()
