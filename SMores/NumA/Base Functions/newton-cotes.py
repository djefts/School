from math import *


def f(x):
    # return 0.2 + 25 * x - 200 * x**2 + 675 * x**3 - 900 * x**4 + 400 * x**5
    return exp(3 * x) * sin(2 * x)


def trap_rule(n, h, x):
    n -= 1
    two = 0
    for i in range(1, n - 1):
        two += f(x[i])
    
    return (h / 2) * (f(x[0]) + 2 * two + f(x[n]))


def simpson(n, h, x):
    n -= 1
    two = 0
    for i in range(1, n - 1, 2):
        two += f(x[i])
    three = 0
    for i in range(2, n - 2, 2):
        three += f(x[i])
    return (h / 3) * (f(x[0]) + 4 * two + 2 * three + f(x[n]))


a = 0
b = pi / 4
s = 100
h = (b - a) / s
n = s
x = [0]
for i in range(s - 1):
    x.append(x[len(x) - 1] + h)
print("%d x values:" % len(x), x)
print("Expected:", 2.434782478661022043330862641718147463249065528328797272173)
print("Actual:", simpson(n, h, x))
