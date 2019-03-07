class Matrix:
    def __init__(self, matrix):
        self.matrix = matrix
        self.n = len(self.matrix)
        self.x = []
    
    def forward_elim(self):
        for k in range(self.n - 1):  # loop through steps
            for i in range(k + 1, len(self.matrix)):  # loop through rows in bottom left corner
                m = self.matrix[i][k] / self.matrix[k][k]  # get the row multiplier
                self.matrix[i][k] = m  # set up L
                for j in range(k + 1, len(self.matrix) + 1):  # loop through columns
                    self.matrix[i][j] = self.matrix[i][j] - m * self.matrix[k][j]
            print(self)
    
    def forward_sub(self, b):
        sol = [0] * self.n
        matrix = self.clone(self.matrix)
        
        # setup the L matrix
        for row in range(len(matrix)):
            for col in range(len(matrix[row])):
                if row == col:
                    matrix[row][col] = 1
                if col > row:
                    matrix[row][col] = 0
        for i in range(len(b)):
            matrix[i][self.n] = b[i]
        
        print('[' + ']\n['.join([', '.join(['{:3.0f}'.format(i) for i in r]) for r in matrix]) + ']\n')
        
        for row in range(self.n):  # loop through rows
            sum = b[row]  # b vector value
            for col in range(row):  # loop through to diagonal
                sum -= matrix[row][col] * sol[col]  # subtract each known value
            sol[row] = sum
        return sol
    
    def back_sub(self):
        sol = [0] * self.n
        for row in range(self.n - 1, -1, -1):  # loop through rows backwards
            sum = self.matrix[row][self.n]  # value in right-most column
            for col in range(row + 1, self.n):  # loop through columns
                sum -= self.matrix[row][col] * sol[col]  # subtract each known value
            sol[row] = sum / self.matrix[row][row]  # divide by the diag value
        self.x = sol
    
    def partial_pivoting(self, r, c):
        row = 0
        largest = 0
        # find the row with the largest value for the input column
        for i in range(self.n):
            if abs(self.matrix[i][c]) > largest:
                largest = self.matrix[i][c]
                row = i
        # swap the row found above with the input row
        temp = self.matrix[r]
        self.matrix[r] = self.matrix[row]
        self.matrix[row] = temp
    
    def scale_matrix(self):
        for i in range(len(self.matrix) - 1):
            largest = 0
            for j in range(len(self.matrix[i])):
                if abs(self.matrix[i][j]) > largest:
                    largest = self.matrix[i][j]
            for j in range(len(self.matrix[i])):
                self.matrix[i][j] = self.matrix[i][j] / largest
    
    def gaussian_elimination(self):
        print("Original Matrix:\n", mat, "Forward Elimination:\n", sep = '\n')
        self.forward_elim()
        print('Backwards Substitution:\n')
        self.back_sub()
        print('[' + ', '.join(['{}'.format(str(item)) for item in self.x]) + ']\n')
    
    # noinspection PyPep8Naming
    def LU_decomp(self):
        b = []
        # setup b
        for i in range(len(self.matrix)):
            b.append(self.matrix[i][self.n])
        print("b:", b, end = '\n\n')
        
        # get LU matrix
        self.forward_elim()
        
        # get y values
        y = self.forward_sub(b)
        print("y:", y, end = '\n\n')
        for i in range(len(b)):
            self.matrix[i][self.n] = y[i]
        print(self)
        
        # get solution
        self.back_sub()
        print("SOLUTION:", self.x)
    
    def jacobi(self):
        err = 1
        tol = 0.0001
        
        # initial x guess
        x = [0] * self.n
        for row in range(self.n):
            D = 1 / self.matrix[row][row]
            L = 0
            for col in range(row):
                L += D * self.matrix[row][col] * self.matrix[col][self.n]
                print(self.matrix[row][col], self.matrix[col][self.n], sep = '\t')
            print("\n")
            x[row] = D * self.matrix[row][self.n] - L
        print("Initial Guess:", x)
        
        # consecutive x guesses
        while err > tol:
            for row in range(self.n):
                x_old = x.copy()
                
                D = 1 / self.matrix[row][row]
                x[row] = D * self.matrix[row][self.n]
                U = 0
                for col in range(row + 1, self.n):
                    U += 7
                x[row] -= D * U
                
                # calculate error
                s = 0
                for a in range(len(x_old)):
                    s += (x[a] - x_old[a])**2
                err = s**0.5
            # print(x)
    
    def clone(self, inp):
        result = []
        for r in range(len(inp)):
            row = []
            for c in inp[r]:
                row.append(c)
            result.append(row)
        return result
    
    def __str__(self):
        mx = max((len(str(i)) for row in self.matrix for i in row))
        output = '['
        output += ']\n['.join([', '.join(['{:6.2f}'.format(i, mx = mx) for i in r]) for r in self.matrix])
        return output + ']\n'


# x - y + 3 * z = -3
# -x - 2 * z = 1
# 2 * x + 2 * y + 4 * z = 0

# mat = Matrix([[1, -1, 3, -3],
#               [-1, 0, -2, 1],
#               [2, 2, 4, 0]])
# mat = Matrix([[1, 1, 0, 3, 8],
#               [2, 1, -1, 1, 7],
#               [3, -1, -1, 2, 14],
#               [-1, 2, 3, -1, -7]])
# mat = Matrix([[2, 0, 0, 0, 3],
#               [1, 1.5, 0, 0, 4.5],
#               [0, -3, 0.5, 0, -6.6],
#               [2, -2, 1, 1, 0.8]])
mat = Matrix([[10, -1, 2, 0, 6],
              [-1, 11, -1, 3, 25],
              [2, -1, 10, -1, -11],
              [0, 3, -1, 8, 15]])

mat.jacobi()
