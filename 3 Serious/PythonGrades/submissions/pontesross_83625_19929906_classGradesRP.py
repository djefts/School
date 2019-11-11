# -----------------------------------------------
# Submitted By: Ross Pontes
# Homework Number: 3
# Credit to:
# 
#
# Submitted On: 10/4/19
#
# By submitting this program with my name,
# I affirm that the creation and modification
# of this program is primarily my ownwork.
# ------------------------------------------------
gradetot = int(input("How many grades do you have ?: "))
if gradetot > 0:
    namelist = []
    scoreslist = []
    gradeslist = []
    passlist = []
    passcount= 0
    failcount= 0
    R = 0
    for i in range(gradetot):
        name = input("What is the student's name ?: ")
        namelist.append(name)
        score = float(input("What was their score?: "))
        scoreslist.append(score)
        if scoreslist[i] >= 70:
            gradeslist.append('P')
            passlist.append('Pass')
            passcount = passcount + 1
        elif scoreslist[i] < 70:
            gradeslist.append('F')
            passlist.append('Fail')
            failcount = failcount + 1
    for R in range(gradetot):
            print(namelist[R],scoreslist[R], passlist[R])
            R = R + 1
print("Total passing grades: ", passcount)
print("Total failing grades: ", failcount)

            
            

            
                      
                    
