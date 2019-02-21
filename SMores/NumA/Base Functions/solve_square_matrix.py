class Matrix:
    def __init__(self, matrix):
        self.matrix = matrix
        self.n = len(self.matrix)
        self.sol = []
    
    def forwardElimination(self):
        for k in range(self.n - 1):  # loop through steps
            for i in range(k + 1, len(self.matrix)):  # loop through rows in bottom left corner
                m = self.matrix[i][k] / self.matrix[k][k]
                for j in range(len(self.matrix) + 1):  # loop through columns
                    self.matrix[i][j] = self.matrix[i][j] - m * self.matrix[k][j]
    
    def backwardSubstitution(self):
        sol = [0] * self.n
        for row in range(self.n - 1, -1, -1):  # loop through rows backwards
            sum = self.matrix[row][self.n]  # value in right-most column
            for col in range(row + 1, self.n):  # loop through columns
                sum -= self.matrix[row][col] * sol[col]  # subtract each known value
            sol[row] = sum / self.matrix[row][row]  # divide by the diag value
        self.sol = sol
    
    def partialPivoting(self, r, c):
        row = -1
        largest = 0
        # find the row with the largest value for the input column
        for i in range(self.n):
            if self.matrix[i][c] > largest:
                largest = self.matrix[i][c]
                row = i
        # swap the row found above with the input row
        if row > -1:
            temp = self.matrix[r]
            self.matrix[r] = self.matrix[row]
            self.matrix[row] = temp
    
    def scaleMatrix(self):
        for i in range(len(self.matrix) - 1):
            largest = 0
            for j in range(len(self.matrix[i])):
                if abs(self.matrix[i][j]) > largest:
                    largest = self.matrix[i][j]
            for j in range(len(self.matrix[i])):
                self.matrix[i][j] = self.matrix[i][j] / largest
    
    def getSpot(self, i, j):
        return self.matrix[i][j]
    
    def solution(self):
        return '[' + ', '.join(['{}'.format(str(item)) for item in self.sol]) + ']\n'
    
    def __str__(self):
        mx = max((len(str(i)) for row in self.matrix for i in row))
        output = '['
        output += ']\n['.join([', '.join(['{:6.2f}'.format(i, mx = mx) for i in r]) for r in self.matrix])
        return output + ']\n'


n = 4
# x - y + 3 * z = -3
# -x - 2 * z = 1
# 2 * x + 2 * y + 4 * z = 0

# mat = Matrix([[1, 1, 0, 3, 4],
#               [2, 1, -1, 1, 1],
#               [3, -1, -1, 2, -3],
#               [-1, 2, 3, -1, 4]])
mat = Matrix([[1, -1, 3, -3],
              [-1, 0, -2, 1],
              [2, 2, 4, 0]])
print(mat, '\nScaled Matrix:\n')
mat.scaleMatrix()
print(mat, '\nForward Elimination:\n')
mat.forwardElimination()  # WORKS!!
print(mat, '\nBackward Substitution:\n')
mat.backwardSubstitution()
print(mat.solution())
