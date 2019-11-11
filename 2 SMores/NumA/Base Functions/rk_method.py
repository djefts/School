def dydx(x, y):  # base example ODE
    return -2 * x**3 + 12 * x**2 - 20 * x + 8.5


def f(x):  # for verification against other methods
    return -0.5 * x**4 + 4 * x**3 - 10 * x**2 + 8.5 * x + 1


def euler(x_i, x_f, y_i, h):
    print("\nEuler Method:")
    x = x_i
    y = y_i
    while x <= x_f:
        error = abs(f(x) - y)
        print("x = %.2f\ty = %.3f\terror = %.3f" % (x, y, error))
        # print("%.2f,%.5f,%.5f" % (x, y, error))
        
        y = y + dydx(x, y) * h  # new y value
        x += h  # take a step
    print("")


def heun(x_i, x_f, y_i, h):
    print("\nHeun Method:")
    x = x_i
    y = y_i
    
    while x <= x_f:
        error = abs(f(x) - y)
        print("x = %.2f\ty = %.3f\terror = %.3f" % (x, y, error))
        # print("%.2f,%.5f,%.5f" % (x, y, error))
        
        dy1 = dydx(x, y)
        y_pred = y + dy1 * h  # first estimate
        dy2 = dydx(x + h, y_pred)
        phi = (dy1 + dy2) / 2  # average the slopes
        
        y = y + phi * h  # new y value
        x += h  # take a step
    print("")


x_i = 0
y_i = 1
x_f = 4
h = 0.25
euler(x_i, x_f, y_i, h)
heun(x_i, x_f, y_i, h)
