class GridPoint:
    def __init__(self, row, col, let):
        self.row = row
        self.col = col
        self.let = let
        self.neighbors = []
    
    def addNeighbors(self, neighbor):
        self.neighbors.append(neighbor)
    
    def set_let(self, let):
        self.let = let

class Search:
    def __init__(self):
        self.arena = [[GridPoint(row, col, 'O') for col in range(7)] for row in range(6)]
        # GRID INITIALIZATION
        #         [r  c]
        self.arena[5][0].set_let('S')  # START
        self.arena[5][6].set_let('G')  # GOAL
        self.arena[0][0].set_let('X')
        self.arena[0][1].set_let('X')
        self.arena[0][3].set_let('X')
        self.arena[0][4].set_let('X')
        self.arena[0][5].set_let('X')
        self.arena[0][6].set_let('X')
        self.arena[1][0].set_let('X')
        self.arena[1][6].set_let('X')
        self.arena[2][0].set_let('X')
        self.arena[2][2].set_let('X')
        self.arena[2][3].set_let('X')
        self.arena[3][3].set_let('X')
        self.arena[3][5].set_let('X')
        self.arena[4][1].set_let('X')
        self.arena[4][3].set_let('X')
        self.arena[4][5].set_let('X')
        self.arena[5][1].set_let('X')
        self.arena[5][5].set_let('X')
        # GRAPH INITIALIZATION
        self.arena[5][0].addNeighbors((self.arena[4][0],))  # START
        self.arena[4][0].addNeighbors((self.arena[5][0], self.arena[3][0],))
        self.arena[3][0].addNeighbors((self.arena[4][0], self.arena[3][1],))
        self.arena[3][1].addNeighbors((self.arena[3][0], self.arena[3][2], self.arena[2][1]))
        self.arena[2][1].addNeighbors((self.arena[3][1], self.arena[1][1],))
        self.arena[1][1].addNeighbors((self.arena[2][1], self.arena[1][2],))
        self.arena[5][2].addNeighbors((self.arena[4][2], self.arena[5][3],))
        self.arena[4][2].addNeighbors((self.arena[5][2], self.arena[3][2],))
        self.arena[3][2].addNeighbors((self.arena[4][2], self.arena[3][1],))
        self.arena[1][2].addNeighbors((self.arena[1][1], self.arena[0][2], self.arena[1][3],))
        self.arena[0][2].addNeighbors((self.arena[1][2],))
        self.arena[5][3].addNeighbors((self.arena[5][2], self.arena[5][4],))
        self.arena[1][3].addNeighbors((self.arena[1][2], self.arena[1][4],))
        self.arena[5][4].addNeighbors((self.arena[5][3], self.arena[4][4],))
        self.arena[4][4].addNeighbors((self.arena[5][4], self.arena[3][4],))
        self.arena[3][4].addNeighbors((self.arena[4][4], self.arena[2][4],))
        self.arena[2][4].addNeighbors((self.arena[3][4], self.arena[1][4], self.arena[2][5],))
        self.arena[1][4].addNeighbors((self.arena[1][3], self.arena[1][5], self.arena[2][4],))
        self.arena[2][5].addNeighbors((self.arena[2][4], self.arena[2][6], self.arena[1][5],))
        self.arena[1][5].addNeighbors((self.arena[1][4], self.arena[2][5],))
        self.arena[5][6].addNeighbors((self.arena[4][6],))  # GOAL
        self.arena[4][6].addNeighbors((self.arena[3][6],))
        self.arena[3][6].addNeighbors((self.arena[2][6],))
        self.arena[2][6].addNeighbors((self.arena[2][5], self.arena[3][6],))
        
        self.currSpot = self.arena[5][0]
    
    # SEARCH ALGORITHMS #
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
    
    # MOVEMENT #
    def up(self):
        if self.arena[self.currSpot.row - 1][self.currSpot.col].let == 'X' and 'P':
            return False
        self.currSpot = self.arena[self.currSpot.row - 1][self.currSpot.col]
        self.arena[self.currSpot.row][self.currSpot.col].let = 'P'
        return True
    
    def down(self):
        if self.arena[self.currSpot.row + 1][self.currSpot.col].let == 'X' and 'P':
            return False
        self.currSpot = self.arena[self.currSpot.row + 1][self.currSpot.col]
        self.arena[self.currSpot.row][self.currSpot.col].let = 'P'
        return True
    
    def left(self):
        if self.arena[self.currSpot.row][self.currSpot.col - 1].let == 'X' and 'P':
            return False
        self.currSpot = self.arena[self.currSpot.row][self.currSpot.col - 1]
        self.arena[self.currSpot.row][self.currSpot.col].let = 'P'
        return True
    
    def right(self):
        if self.arena[self.currSpot.row][self.currSpot.col + 1].let == 'X' and 'P':
            return False
        self.currSpot = self.arena[self.currSpot.row][self.currSpot.col + 1]
        self.arena[self.currSpot.row][self.currSpot.col].let = 'P'
        return True
    
    def getSpot(self):
        return self.arena[self.currSpot.row][self.currSpot.col]
    
    def printState(self):
        for row in ([0, 1, 2, 3, 4, 5]):
            for col in ([0, 1, 2, 3, 4, 5, 6]):
                print(self.arena[row][col].let, end = ' ')
            print()  # new line


search = Search()
search.printState()
print()
search.up()
search.up()
search.right()
search.up()
search.up()
search.right()
search.right()
search.right()
search.right()
search.down()
search.right()
search.down()
search.down()
search.down()
search.printState()
