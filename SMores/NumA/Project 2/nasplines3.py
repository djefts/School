import matplotlib.pyplot as plt
import numpy as np
import copy

x = [110, 119, 123, 128, 135, 146, 150, 155, 166, 180, 187, 194, 201, 206, 210, 215, 220, 222, 224, 227, 232, 234, 235,
     236, 238]
y = [456, 464, 471, 474, 476, 476, 474, 474, 473, 473, 471, 467, 466, 465, 464, 463, 460, 456, 453, 451, 448, 443, 442,
     441, 441]

n = len(x)


def Guass(A):
    n = len(A)
    print(n)
    
    print(A)
    for col in range(n):  # Goes column by column
        for j in range(col + 1, n):  # finds factor to multiply pivot row by
            a = (A[j, col]) / (A[col, col])
            for jj in range(col, n + 1):
                w = A[col, jj]
                b = A[j, jj]
                bb = copy.deepcopy(b)
                z = a * w
                zz = copy.deepcopy(z)
                c = (bb - zz)
                cc = copy.deepcopy(c)
                
                A[j, jj] = bb - zz
    # print(A)
    x = np.zeros(n)
    x[n - 1] = A[n - 1, n] / A[n - 1, n - 1]  # sets last value of x
    for t in range(n - 1, -1, -1):  # backsub
        l = 0  # resets l for each row
        for j in range(t + 1, n):  # eliminates other variables in row from rhs
            l = l + (A[t, j]) * x[j]
        
        x[t] = (A[t, n] - l) / A[t, t]
        # print(x)
    print('X=', x)
    return x


# find diag
# p0=p1
h = []
p = []

for i in range(1, n - 1):
    p.append(2 * (x[i + 1] - x[i - 1]))

print('p', p)

for i in range(1, n):
    h.append(x[i] - x[i - 1])

q = []

for i in range(1, n - 1):
    qq = (3 / h[i]) * (y[i + 1] - y[i]) - (3 / h[i - 1]) * (y[i] - y[i - 1])
    q.append(qq)
print('q', q)

A = np.zeros([n - 2, n - 1])
for col in range(0, n - 1):
    for row in range(0, n - 2):
        A[row, n - 2] = q[row]
        A[row, row] = p[row]
        if row - col == 1:
            if row > col:
                A[row, col] = h[row]
                A[col, row] = h[row]

print('\nA\n', A, end = '')
b = Guass(A)
b = np.concatenate(([0], b, [0]))
print('b', b)
d = []
c = [(((y[1] - y[0]) / h[0]) - (h[0] / 3) * (b[1] + 2 * b[0]))]
for i in range(0, n):
    d.append(y[i])
for i in range(1, n):
    c.append((b[i] + b[i - 1]) * h[i - 1] + c[i - 1])
print('c', c)
print('d', d)
a = []
for i in range(0, n - 1):
    a.append((b[i + 1] - b[i]) / (3 * h[i]))
print('a', a)
print('b', b)
for i in range(0, n - 1):
    X = np.linspace(x[i], x[i + 1], 100)
    s = a[i] * (X - x[i])**3 + b[i] * (X - x[i])**2 + c[i] * (X - x[i]) + d[i]
    plt.plot(X, s)
    plt.plot(x[i], y[i], 'co')
plt.plot(x[n - 1], y[n - 1], 'co')

#
# from PIL import Image
#
# im = Image.open("cute-fat-corgi-on-his-butt.jpg", "r")
# pix_val = list(im.getdata())
#
# print(pix_val)
#
#
#
#
#
#
# #Plot on corgi
# import matplotlib.pyplot as plt
#
# # im = plt.imread("cute-fat-corgi-on-his-butt.jpg")
# # implot = plt.imshow(im)
#
#
# # put a blue dot at (10, 20)
#
# im = plt.imread("cute-fat-corgi-on-his-butt.jpg")
# plt.imshow(im)
#
# # put a red dot, size 40, at 2 locations:
# plt.scatter(x=[156],y=[25])
# plt.scatter(x=[165],y=[27])
# plt.scatter(x=[170],y=[27])
# plt.scatter(x=[180],y=[36])
# plt.scatter(x=[187],y=[45])
# plt.scatter(x=[200],y=[62])
# plt.scatter(x=[210],y=[60])
# plt.scatter(x=[225],y=[63])
# plt.scatter(x=[240],y=[63])
# plt.scatter(x=[250],y=[65])
# plt.scatter(x=[267],y=[69])
# plt.scatter(x=[273],y=[60])
# plt.scatter(x=[283],y=[53])
# plt.scatter(x=[289],y=[45])
# plt.scatter(x=[294],y=[40])
# plt.scatter(x=[305],y=[39])
# plt.scatter(x=[315],y=[36])
#
# plt.show()
