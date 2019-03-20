import math


def f(x):
    # return x**2 + 2 * x  # find min
    # return -x**2 + 2 * x  # find max
    return 2 * math.sin(x) - (x**2 / 10)


def min(a, b, tol):
    s = 0.6180339887
    err = abs(b - a)
    while err > tol:
        a1 = b - s * (b - a)
        b1 = a + s * (b - a)
        print(f(a1), f(b1))
        if f(a1) <= f(b1):
            b = b1
        if f(a1) > f(b1):
            a = a1
        err = abs(b1 - a1)
    return f(a1)


def max(a, b, tol):
    s = 0.6180339887
    err = abs(b - a)
    a1 = None
    while err >= tol:
        a1 = b - s * (b - a)
        b1 = a + s * (b - a)
        print(a1, b1)
        if f(a1) >= f(b1):
            b = b1
        if f(a1) < f(b1):
            a = a1
        err = abs(b1 - a1)
    return f(a1)


tolerance = 0.00001
a = 0
b = 4
print("Solution:", max(a, b, tolerance))
