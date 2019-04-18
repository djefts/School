def f(x):
    return 0.2 + 25 * x - 200 * x**2 + 675 * x**3 - 900 * x**4 + 400 * x**5


def trap_rule(n, x):
    I = 0
    for i in range(n - 2):
        h = x[i + 1] - x[i]
        
        one = f(x[0])
        
        two = 0
        for ii in range(1, n - 2):
            two += f(x[ii])
        
        three = f(x[n - 1])
        
        I += (h / 2) * (one + 2 * two + three)
    
    return I


x = [0.0, 0.2, 0.4, 0.6, 0.8]

print("Expected:", 1.64053333333)
print("Actual:", trap_rule(len(x), x))
