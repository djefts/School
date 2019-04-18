# Code adapted from geeksforgeeks.org

# Function to find the product term
def proterm(i, value, x):
    pro = 1
    for j in range(i):
        pro = pro * (value - x[j])
    return pro


# Function for calculating divided difference table
def dividedDiffTable(x, y, n):
    for i in range(1, n):
        for j in range(n - i):
            y[j][i] = ((y[j][i - 1] - y[j + 1][i - 1]) / (x[j] - x[i + j]))
    return y


# Function for applying Newton’s divided difference formula
def applyFormula(value, x, y, n):
    sum = y[0][0]
    for i in range(n):
        sum = sum + (proterm(i, value, x) * y[0][i])
        print("Sum: ", sum)
    return sum


# Function for displaying divided difference table
def printDiffTable(y, n):
    for i in range(n):
        for j in range(n - i):
            print("{:}\t".format(y[i][j]), end = " ")
        print("")


# set up inputs
n = 4
y = [[0.0 for i in range(10)] for j in range(10)]
x = [1.0, 4.0, 5.0, 6.0]
y[0][0] = 0.0000
y[1][0] = 1.386294
y[2][0] = 1.609438
y[3][0] = 1.791759
# calculating divided difference table
y = dividedDiffTable(x, y, n)
# displaying divided difference table
printDiffTable(y, n)
# value to be interpolated
value = 2
# printing the value
print("\nValue at", value, "is", "{:.4}".format(applyFormula(value, x, y, n)))
