import math


class GoldenSearch:
    def __init__(self, tol):
        self.tol = tol
        self.s = 0.6180339887
    
    def min(self, a, b):
        err = abs(b - a)
        a1 = None
        while err > self.tol:
            b1 = a + self.s * (b - a)
            a1 = b - self.s * (b - a)
            print(a, b)
            if f(a1) > f(b1):
                a = a1
            else:
                b = b1
            err = abs(b - a)
        return f(a1)
    
    def max(self, a, b):
        err = abs(b - a)
        a1 = None
        while err > self.tol:
            b1 = a + self.s * (b - a)
            a1 = b - self.s * (b - a)
            print(a, b)
            if f(a1) < f(b1):
                a = a1
            else:
                b = b1
            err = abs(b - a)
        return f(a1)


class ParabolicInterpolation:
    def __init__(self, tol):
        self.tol = tol
    
    def solve(self, x0, x1, x2):
        err = abs(x2 - x1)
        while err >= self.tol:
            x3 = self.newX(x0, x1, x2)
            print(x3)
            x0 = x1
            x1 = x2
            x2 = x3
            err = abs(x2 - x1)
        return f(x2)
    
    def newX(self, x0, x1, x2):
        y0 = f(x0)
        y1 = f(x1)
        y2 = f(x2)
        x3 = y0 * (x1**2 - x2**2) + y1 * (x2**2 - x0**2) + y2 * (x0**2 - x1**2)
        return x3 / (2 * (y0 * (x1 - x2) + y1 * (x2 - x0) + y2 * (x0 - x1)))


def f(x):
    return x**2 + 2 * x  # find min
    # return -x**2 + 2 * x  # find max
    # return 2 * math.sin(x) - (x**2 / 10)


tolerance = 0.00001
g1 = -10
g2 = 10
g3 = 7
gold = GoldenSearch(tolerance)
print("Solution:", gold.min(g1, g2))
para = ParabolicInterpolation(tolerance)
print("Solution:", para.solve(g1, g2, g3))
