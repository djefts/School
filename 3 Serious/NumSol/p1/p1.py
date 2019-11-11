import sys
import time

import numpy as np
import matplotlib.pyplot as plt
from scipy.integrate import odeint
from mpl_toolkits.mplot3d import Axes3D

"""
Write a code in Matlab/Python to implement the Adams-Bashforth-Moulton method of fourth order for the autonomous
system of ODEs.

Ỹn+1 = Yn + (h/24) * [55*F(Yn) − 59*F(Yn−1) + 37*F(Yn−2) − 9*F(Yn−3)]  -   Adams–Bashforth method
Ỹn+1 = Yn + (h/24) * 􏰆[9*F(Ỹn+1) + 19*F(Yn) − 5*F(Yn−1) + F(Yn−2)􏰇]      -   Adams–Moulton method

Use it to solve the following well known Lorenz problem that arises in the study of dynamical systems
dy1 = 10*(y2 - y1)          y1 is proportional to the rate of convection
dy2 = y1*(28 - y3) - y2     y2 is proportional to the horizontal temperature variation
dy3 = y1*y2 - (8/3)*y3      y3 is proportional to the vertical temperature variation
with initial conditions y1(0) = 15, y2(0) = 15, y3(0) = 36. Plot the solution curves for 0 ≤ t ≤ 20.
"""


def dy1(y1, y2, y3):
    return 10 * (y2 - y1)


def dy2(y1, y2, y3):
    return y1 * (28 - y3) - y2


def dy3(y1, y2, y3):
    return y1 * y2 - (8 / 3) * y3


def rk4(t, dt, y, N):
    output = [[y[0]], [y[1]], [y[2]]]
    for n in range(N - 1):
        # y1
        K1 = dy1(output[0][n], output[1][n], output[2][n])
        K2 = dy1(output[0][n] + 0.5 * dt * K1, output[1][n], output[2][n])
        K3 = dy1(output[0][n] + 0.5 * dt * K2, output[1][n], output[2][n])
        K4 = dy1(output[0][n] + dt * K3, output[1][n], output[2][n])
        output[0].append(y[n] + dt * (K1 + 2 * K2 + 2 * K3 + K4) / 6)
        # y2
        K1 = dy2(output[0][n], output[1][n], output[2][n])
        K2 = dy2(output[0][n], output[1][n] + 0.5 * dt * K1, output[2][n])
        K3 = dy2(output[0][n], output[1][n] + 0.5 * dt * K2, output[2][n])
        K4 = dy2(output[0][n], output[1][n] + dt * K3, output[2][n])
        output[1].append(output[1][n] + dt * (K1 + 2 * K2 + 2 * K3 + K4) / 6)
        # y3
        K1 = dy3(output[0][n], output[1][n], output[2][n])
        K2 = dy3(output[0][n], output[1][n], output[2][n] + 0.5 * dt * K1)
        K3 = dy3(output[0][n], output[1][n], output[2][n] + 0.5 * dt * K2)
        K4 = dy3(output[0][n], output[1][n], output[2][n] + dt * K3)
        output[2].append(output[2][n] + dt * (K1 + 2 * K2 + 2 * K3 + K4) / 6)
    return output


def main():
    t0 = 0
    tmax = 20
    N = 100
    t, h = np.linspace(t0, tmax, N + 1, retstep = True)
    print('T:', list(t))
    
    y1 = [15]
    y2 = [15]
    y3 = [36]
    # print(y1, y2,  y3)
    
    """ get the first 3 spots in each function """
    y0 = [y1[0], y2[0], y3[0]]
    y = rk4(t, h, y0, 4)
    y1 = y[0]
    y2 = y[1]
    y3 = y[2]
    print("Initial + 3 Orders:", y1, y2, y3, sep = '\n')
    time.sleep(0.1)
    
    for n in range(3, N):
        # calculate Ÿ values
        y1_tilde = y1[n] + (h / 24) * (55 * dy1(y1[n], y2[n], y3[n])
                                       - 59 * dy1(y1[n - 1], y2[n - 1], y3[n - 1])
                                       + 37 * dy1(y1[n - 2], y2[n - 2], y3[n - 2])
                                       - 9 * dy1(y1[n - 3], y2[n - 3], y3[n - 3]))
        y2_tilde = y2[n] + (h / 24) * (55 * dy2(y1[n], y2[n], y3[n])
                                       - 59 * dy2(y1[n - 1], y2[n - 1], y3[n - 1])
                                       + 37 * dy2(y1[n - 2], y2[n - 2], y3[n - 2])
                                       - 9 * dy2(y1[n - 3], y2[n - 3], y3[n - 3]))
        y3_tilde = y3[n] + (h / 24) * (55 * dy3(y1[n], y2[n], y3[n])
                                       - 59 * dy3(y1[n - 1], y2[n - 1], y3[n - 1])
                                       + 37 * dy3(y1[n - 2], y2[n - 2], y3[n - 2])
                                       - 9 * dy3(y1[n - 3], y2[n - 3], y3[n - 3]))
        # calculate next Y value
        y1.append(y1[n] + (h / 24) * (9 * dy1(y1_tilde, y2_tilde, y3_tilde)
                                      + 19 * dy1(y1[n], y2[n], y3[n])
                                      - 5 * dy1(y1[n - 1], y2[n - 1], y3[n - 1])
                                      + 1 * dy1(y1[n - 2], y2[n - 2], y3[n - 2])))
        y2.append(y2[n] + (h / 24) * (9 * dy2(y1_tilde, y2_tilde, y3_tilde)
                                      + 19 * dy2(y1[n], y2[n], y3[n])
                                      - 5 * dy2(y1[n - 1], y2[n - 1], y3[n - 1])
                                      + 1 * dy2(y1[n - 2], y2[n - 2], y3[n - 2])))
        y3.append(y3[n] + (h / 24) * (9 * dy3(y1_tilde, y2_tilde, y3_tilde)
                                      + 19 * dy3(y1[n], y2[n], y3[n])
                                      - 5 * dy3(y1[n - 1], y2[n - 1], y3[n - 1])
                                      + 1 * dy3(y1[n - 2], y2[n - 2], y3[n - 2])))
    
    """ plot everything """
    print("=========================================================================")
    print("  n\t y1\t\t\t\t\t y2\t\t\t\t\t y3", end = '\n')
    for n in range(N):
        print('{:3d}\t {:0.15f}\t {:0.15f}\t {:0.15f}'.format(n, y1[n], y2[n], y3[n]))
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
    fig = plt.figure()
    ax = fig.gca(projection = '3d')
    ax.plot(y1, y2, y3)
    plt.show()


if __name__ == "__main__":
    main()
