class Interpolation:
    def __init__(self, n, pts):
        self.n = n
        self.o = n - 1
        self.values = pts
        if len(self.values) != n:
            print("Invalid number of points inputted.")
            exit(1)
        self.polynomial = [0] * self.o
    
    def div_diff(self, order, x, spot):
        if order == 1:
            return self.f(x)
        
        if spot - order == 0:
            self.polynomial[order] = self.div_diff(order - 1, x, spot - 1) - self.div_diff(order - 1, x, spot)
            self.polynomial[order] /= (self.values[spot] - self.values[spot - order])
        return self.div_diff(order - 1, x, spot - 1)
    
    def f(self, x):
        for pt in self.values:
            if pt[0] == x:
                return pt[1]
        print("Unable to find x value in points.")
        exit(1)
    
    def build_poly(self, order, x):
        if order == 1:
            return self.f(x)
        for i in range(order - 1):
            self.polynomial[order] *= (x - self.values[i][0])
        return self.build_poly(order - 1, x)
    
    def approximate(self, x):
        solution = 0
        self.build_poly(self.o, x)
        solution += (p for p in self.polynomial)


pt1 = (1, 0)
pt2 = (4, 1.386294)
pt3 = (5, 1.609438)
pt4 = (6, 1.791759)
points = [pt1, pt2, pt3, pt4]
inter = Interpolation(4, points)
