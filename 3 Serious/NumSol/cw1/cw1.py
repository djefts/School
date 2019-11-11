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


# print('==============================================================================')
# print('  Method\t\t\t\t\t\tStep Size\t\ty(6)\t\t\t\tError', end = '\n')
# final = exact[-1]
# error = abs(final - final)  # exact
# print("  {0}\t{1:.15f}  \t\t{2:.15f} \t{3:0.15f}".format("Exact Solution\t\t\t", h, exact[-1], error))
# error = abs(a[-1] - final)  # eulers
# print("  {0}\t{1:.15f}  \t\t{2:.15f} \t{3:0.15f}".format("Euler's Method\t\t\t", h, a[-1], error))
# error = abs(b[-1] - final)  # eulers+
# print("  {0}\t{1:.15f}  \t\t{2:.15f} \t{3:0.15f}".format("Improved Euler's Method\t", h, b[-1], error))
# error = abs(c[-1] - final)  # Taylor O(2)
# print("  {0}\t{1:.15f}  \t\t{2:.15f} \t{3:0.15f}".format("Second-Order Taylor Series", h, c[-1], error))
# error = abs(d[-1] - final)  # Taylor O(4)
# print("  {0}\t{1:.15f}  \t\t{2:0.15f} \t{3:0.15f}".format("Fourth-Order Taylor Series", h, d[-1], error))
# print('==============================================================================')

n = ['Euler\'s', 'Euler+', '2nd Order Taylor', '4th Order Taylor']
t0 = 1.0
tmax = 6
y0 = 1

""" EULERS APPROXIMATION """
h = 0.125
N = int((tmax - t0) / h + 1)
_, h = np.linspace(t0, tmax, N, retstep = True)
print("{:.14f}".format(h))

# approximate solution
[t, a] = euler(t0, tmax, y0, N)


def exact_sol():
    actual = []
    for val in t:
        actual.append(val * math.log(val) + val)
    exact = np.array(actual)
    error = [(abs(a[n] - exact[n])) for n in range(len(a))]
    return np.array(error)


# exact solution:
exact_sol()
plt.plot(t, exact_sol(), ':^')


""" IMPROVED EULER APPROXIMATION """
h = 0.25
N = int((tmax - t0) / h + 1)
_, h = np.linspace(t0, tmax, N, retstep = True)
print("{:.14f}".format(h))

# approximate solution
[t, a] = improved_euler(t0, tmax, y0, N)

plt.plot(t, exact_sol(), ':s')


""" ORDER 2 TAYLOR SERIES APPROXIMATION """
h = 0.25
N = int((tmax - t0) / h + 1)
_, h = np.linspace(t0, tmax, N, retstep = True)
print("{:.14f}".format(h))

# approximate solution
[t, a] = second_taylor_series(t0, tmax, y0, N)

plt.plot(t, exact_sol(), ':p')


""" ORDER 2 TAYLOR SERIES APPROXIMATION """
h = 0.5
N = int((tmax - t0) / h + 1)
_, h = np.linspace(t0, tmax, N, retstep = True)
print("{:.14f}".format(h))

# approximate solution
[t, a] = fourth_taylor_series(t0, tmax, y0, N)

plt.plot(t, exact_sol(), ':h')


plt.title('Error for Various Numerical Approximations for an IVP')
plt.ylabel('Error')
plt.xlabel('t')
plt.legend(n, loc = 'best')
plt.show()
