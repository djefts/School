import math

import numpy as np
import matplotlib.pyplot as plt


def f(t, y):
    return 1 + y / t


def df(t, y):
    return 1 / t


def d2f(t, y):
    return -1 / t**2


def d3f(t, y):
    return 1 / t**3


def euler(t_o, t_max, y_o, N):
    t, dt = np.linspace(t_o, t_max, N, retstep = True)
    y = np.zeros(N)
    y[0] = y_o
    for n in range(N - 1):
        y[n + 1] = y[n] + dt * f(t[n], y[n])
    return t, y


def improved_euler(t0, tmax, y0, N):
    t, dt = np.linspace(t0, tmax, N, retstep = True)
    y = np.zeros(N)
    y[0] = y0
    for n in range(N - 1):
        y[n + 1] = y[n] + (dt / 2) * (f(t[n], y[n])
                                      + f(t[n + 1], y[n] + dt * f(t[n], y[n])))
    return t, y


def second_taylor_series(t0, tmax, y0, N):
    t, dt = np.linspace(t0, tmax, N, retstep = True)
    y = np.zeros(N)
    y[0] = y0
    for n in range(N - 1):
        series = y[n] + dt * f(t[n], y[n])  # first order
        series += ((dt**2) / 2) * df(t[n], y[n])  # second order
        y[n + 1] = series
    return t, y


def fourth_taylor_series(t0, tmax, y0, N):
    t, dt = np.linspace(t0, tmax, N, retstep = True)
    y = np.zeros(N)
    y[0] = y0
    for n in range(N - 1):
        series = y[n] + dt * f(t[n], y[n])  # first order
        series += ((dt**2) / (2)) * df(t[n], y[n])  # second order
        series += ((dt**3) / (3 * 2)) * d2f(t[n], y[n])  # third order
        series += ((dt**4) / (4 * 3 * 2)) * d3f(t[n], y[n])  # fourth order
        y[n + 1] = series
    return t, y


for N in range(1):
    t0 = 1
    tmax = 6
    y0 = 1
    N = 41  # step size of 0.125
    _, h = np.linspace(t0, tmax, N, retstep = True)
    
    # approximate solution
    [t, y] = improved_euler(t0, tmax, y0, N)
    
    # exact solution:
    actual = []
    for val in t:
        actual.append(val * math.log(val) + val)
    exact = np.array(actual)
    
    # Global Error Bound:
    #   |y(tn) − Yn| ≤ [(hM)/(2L)] * [e ^ (L(tn−t0)) – 1)
    # M = max(|y''(t)|)
    M = 0.0
    for n in range(len(t) - 1):
        # y''(t) = 1 / t
        M = max(M, abs(1 / t[n]))
    dy = []
    for n in range(len(exact)):
        dy.append(f(t[n], exact[n]))
    L = 0.0
    for n in range(len(t) - 2):
        L = max(L, abs(f(t[n], y[n]) - f(t[n + 1], y[n + 1])) / abs(y[n] - y[n + 1]))
    g = []
    for n in range(len(t) - 1):
        # Global Error Bound for Euler's Method
        geb = ((h * M) / (2 * L)) * (np.exp(L * (t[n] - t[0])) - 1)
        g.append(geb)
    g.append(((g[-1] - g[0]) / len(g)) + g[-1])  # estimate last GEB
    geb = np.array(g)
    plt.plot(t, exact, '-', t, y, 'd-')
    plt.title('Numerical Approximation to the IVP -- N = ' + str(N))
    plt.legend(['exact_sol', 'euler_approx'], loc = 'best')
    plt.ylabel('x(t)')
    plt.xlabel('t')
    plt.show()
    print('=========================================================================================')
    # print('  n\ttn\t\t\t\t\tyn\t\t\t\t\t|yn-exact(n)|', end = '\n')
    print('  Step Size\t\t\tyn\t\t\t\t\t|yn-exact(n)|\t\tGlobal Error Bound', end = '\n')
    max_error = 0
    for n in range(len(y)):
        error = abs(y[n] - exact[n])
        max_error = max(max_error, error)
        # confirm or nah?
        confirm = False
        if error <= geb[n]:
            confirm = True
        # print("{0:3d}\t{1:0.15f}\t{2:0.15f}\t{3:0.15f}".format(n, t[n], y[n], error))
        print("  {1:0.15f}\t{2:0.15f}\t{3:0.15f}\t{4:0.13f} {5}".format(n, h, y[n], error, geb[n], confirm))
    print('  Maximum error:', max_error)
    print('  Lipschitz Constant:', L)
    print('  Maximum of |y\'\'(t)|:', M)
    print('=========================================================================================')
