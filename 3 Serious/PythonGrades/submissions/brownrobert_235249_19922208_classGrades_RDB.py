import math
grades = int(input("Enter grades to expect: "))
if grades > 0:
    listofnames = []
    scorelist = []
    gradelist = []
     
    for i in range(grades):
        name = input("Please enter first and last names: ")
        listofnames.append(name)
        score = float(input("Please enter scores: "))
        if score >= 70:
            gradelist.append("P")
        else:
            gradelist.append("F")
        scorelist.append(score)
    print() # skip a line
    SP = 0
    SF = 0
    i = 0
    for Names in listofnames:
        sCoreP = scorelist[i]
        sCoreF = scorelist[i]
        if gradelist[i] == "P":
            print(Names, sCoreP)
            SP = SP + 1
        else:
            print(Names, sCoreF)
            SF = SF + 1
        i = i + 1
        
    print("Here are the number of students who had passed", SP)
    print("Here are the number of students who had failed", SF)
else:
    print("invalid number of grades.")
