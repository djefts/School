class RKArray:
    def __init__(self, order, method):
        self.array = [[]]
        
        orders = ['RK2', 'RK4']
        RK2_methods = {'Heuns': self.Heuns,
                       'ModEuler': self.ModEuler,
                       'Optimal': self.Optimal}
        RK4_methods = {'Classic': self.Classic}
        
        self.order = order
        if self.order not in orders:
            raise Exception("Invalid Order paramater. Choose from " + str(orders))
        
        self.method = method
        if self.order == orders[0]:
            if self.method not in RK2_methods:
                raise Exception("Invalid RK2 Methods paramater. Choose from " + str(RK2_methods))
            self.array = RK2_methods[method]()
        elif self.order == orders[1]:
            if self.method not in RK4_methods:
                raise Exception("Invalid RK4 Methods paramater. Choose from " + str(RK4_methods))
            self.array = RK4_methods[method]()
    
    def Heuns(self):
        array = [[0.0, 0.0, 0.0], [1.0, 1.0, 0.0], [None, 0.5, 0.5]]
        return array
    
    def ModEuler(self):
        array = [[0.0, 0.0, 0.0], [0.5, 0.5, 0.0], [None, 0.0, 1.0]]
        return array
    
    def Optimal(self):
        array = [[0.0, 0.0, 0.0], [(2.0 / 3.0), (2.0 / 3.0), 0.0], [None, 0.25, 0.75]]
        return array
    
    def Classic(self):
        array = [[0.0, 0.0, 0.0, 0.0, 0.0],
                 [0.5, 0.5, 0.0, 0.0, 0.0],
                 [0.5, 0.0, 0.5, 0.0, 0.0],
                 [1.0, 0.0, 0.0, 1.0, 0.0],
                 [None, 1.0 / 6, 1.0 / 3, 1.0 / 3, 1.0 / 6]]
        return array
