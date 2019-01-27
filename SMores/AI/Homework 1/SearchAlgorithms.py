class Search():
    
    def __init__(self):
        self.arena = [['O' for col in range(7)] for row in range(6)]
        #         [r  c]
        self.arena[5][0] = 'S'  # start
        self.arena[5][6] = 'G'  # goal
        self.arena[0][0] = 'X'
        self.arena[0][1] = 'X'
        self.arena[0][3] = 'X'
        self.arena[0][4] = 'X'
        self.arena[0][5] = 'X'
        self.arena[0][6] = 'X'
        self.arena[1][0] = 'X'
        self.arena[1][6] = 'X'
        self.arena[2][0] = 'X'
        self.arena[2][2] = 'X'
        self.arena[2][3] = 'X'
        self.arena[3][3] = 'X'
        self.arena[3][5] = 'X'
        self.arena[4][1] = 'X'
        self.arena[4][3] = 'X'
        self.arena[4][5] = 'X'
        self.arena[5][1] = 'X'
        self.arena[5][5] = 'X'
        
        row, col = 0, 0
        self.currSpot = [row, col]
    
    ### SEARCH ALGORITHMS ###
    def depth(self):
        # TODO: Depth-first Search algorithm
        while self.getSpot() != 'G':
            self.printState()
        return 0
    
    def greedy(self):
        # TODO: Greedy best-first search algorithm
        return 0
    
    def astar(self):
        # TODO: A* search algorithm
        return 0
    
    ### MOVEMENT ###
    def up(self):
        self.currSpot[0] -= 1
        if self.getSpot() == 'X':
            self.currSpot[0] -= 1
            return False
        self.arena[self.currSpot[0]][self.currSpot[1]] = 'P'
    
    def down(self):
        self.currSpot[0] += 1
        if self.getSpot() == 'X':
            self.currSpot[0] += 1
            return False
        self.arena[self.currSpot[0]][self.currSpot[1]] = 'P'
    
    def left(self):
        self.currSpot[1] -= 1
        if self.getSpot() == 'X':
            self.currSpot[1] -= 1
            return False
        self.arena[self.currSpot[0]][self.currSpot[1]] = 'P'
    
    def right(self):
        self.currSpot[1] += 1
        if self.getSpot() == 'X':
            self.currSpot[1] += 1
            return False
        self.arena[self.currSpot[0]][self.currSpot[1]] = 'P'
    
    def getSpot(self):
        return self.arena[self.currSpot[0]][self.currSpot[1]]
    
    def printState(self):
        for row in ([0, 1, 2, 3, 4, 5]):
            for col in ([0, 1, 2, 3, 4, 5, 6]):
                print(self.arena[row][col], end = '  ')
            print()


search = Search()
search.printState()
print()
search.up()
search.up()
search.printState()
