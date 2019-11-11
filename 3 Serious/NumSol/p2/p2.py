"""
Heat transfer through a long thin rod can be described by the following partial differential equation
    du/dt(x,t)=d2u/dt2(x,t), 0<x<1, 0<t≤T

At the boundary the temperature is kept constant
    u(0,t)=u(1,t)=0, 0<t<T
The initial temperature profile is given as
    u(x,0)=10sin(πx), 0≤x≤1

A discretization with respect to x yields the following system of ordinary differential equations:
    dv/dt=Av(t), v_i(0)=10sin(πxi), i=1,···,N−1

b. Apply the implicit Euler method
vn+1 = vn + ∆tAvn+1 to find the solution at time t = 0.1 for N = 21.
c. Run your code for 100 time-steps with ∆t = 0.001 and plot the numerical results (the temperature u(x,
t) at the grids (xi, ti).
"""
import time
from pprint import pprint, pformat

import numpy as np
import matplotlib.pyplot as plt


def format_matrix(mat):
    mat_str = '[[' + ', '.join(map(str, mat[0])) + ']\n'
    for j in range(1, len(mat) - 1):
        row = mat[j]
        mat_str += '\t[' + ', '.join(map(str, row)) + ']\n'
    mat_str += '\t[' + ', '.join(map(str, mat[-1])) + ']]'
    return mat_str


def u(x, t):
    if x == 0 or x == 1:
        return 0
    if t == 0:
        return 10 * np.sin(np.pi * x)


def v(x):
    return 10 * np.sin(np.pi * x)


def create_A(size):
    matrix = [[0] * size] * size
    
    matrix[0] = [-2, 1] + [0] * (size - 2)
    for line in range(0, size - 2):
        matrix[line + 1] = [0] * line + [1, -2, 1] + [0] * (size - line - 3)
    matrix[-1] = [0] * (size - 2) + [1, -2]
    
    return matrix


def explicit_euler(v_n, delta_t, A):
    return v_n + np.multiply(delta_t, A, v_n)


def implicit_euler(v_n, delta_t, A):
    return v_n + np.multiply(delta_t, A, v_n)


legend = []

"""
Apply the explicit Euler method to find the solution at time t = 0.1, t = 0.5 and t = 1 for N = 21.
Note: You need to use small time-step ∆t for the stability of the explicit Euler method.
"""
solutions = []
T = 1.0
print('\nExplicit Method')
N = 21
x_vector, delta_x = np.linspace(0, 1, N, retstep = True)

delta_t = 0.001
t_vector = list(np.arange(0, T + delta_t, delta_t))
A = create_A(N)
multiplicand = delta_t / (delta_x**2)
A = np.multiply(multiplicand, A)

# explicit euler's
v = []
v_0 = [u(x, 0) for x in x_vector]
v.append(np.array(v_0))

for n in range(len(t_vector) - 1):
    trans = np.array(v[n])
    new_mat = np.matmul(A, trans)
    v.append(np.add(v[n], new_mat))

print('Solution at t = {}:\n{}'.format(0.1, v[t_vector.index(0.1)]))
print('Solution at t = {}:\n{}'.format(0.5, v[t_vector.index(0.5)]))
print('Solution at t = {}:\n{}'.format(1.0, v[t_vector.index(1.0)]))

plt.plot(x_vector, v[t_vector.index(0.1)], '+-',
         x_vector, v[t_vector.index(0.5)], '.-',
         x_vector, v[t_vector.index(1.0)], 'go--')
legend += ['Explicit 0.1', 'Explicit 0.5', 'Explicit 1.0']

"""
Apply the implicit Euler method to find the solution at time t = 0.1 for N = 21.
"""
print('\nImplicit Method')
T = 0.1
N = 21
x_vector, delta_x = np.linspace(0, 1, N, retstep = True)

delta_t = 0.001
t_vector = list(np.arange(0, T + delta_t, delta_t))
A = create_A(N)
multiplicand = delta_t / (delta_x**2)
A = np.multiply(multiplicand, A)

# implicit euler's
v = []
v_0 = [u(x, 0) for x in x_vector]
v.append(np.array(v_0).T)

difference = np.eye(N) - A
inverse = np.linalg.inv(difference)
print(inverse)

for n in range(len(t_vector) - 1):
    trans = np.array(v[n])
    new_mat = np.matmul(inverse, trans)
    v.append(np.add(v[n], new_mat))

print('Solution at T = {}:\n{}'.format(T, v[-1]))
# plt.plot(x_vector, v[-1], 'ro')
legend += ['Implicit 0.1']

"""
plots
"""
plt.title('Explicit & Implicit Solutions at times t')
plt.legend(legend, loc = 'best')
plt.grid()
plt.show()

for i in range(100):
    delta_t = 0.001
