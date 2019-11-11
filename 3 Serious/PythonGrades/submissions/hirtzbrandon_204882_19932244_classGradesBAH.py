NGE = int(input("How many grades are there? "))
if NGE > 0:
    nameL = []
    scoreL = []
    gradeL = []
    for i in range(NGE):
        AddN = input("What is the name of this assignemnt or test?: ")
        nameL.append(AddN)
        AddS = int(input("What was the score? "))
        scoreL.append(AddS)
        if AddS >= 70:
            gradeL.append('P')
        else:
            gradeL.append('F')
    numberfailed = 0
    numberpassed = 0
    for i in range(NGE):
        if gradeL[i] == 'P':
            print(nameL[i] , scoreL[i] , "Pass")
            numberpassed = numberpassed + 1
        else:
            print(nameL[i] , scoreL[i] , "Fail")
            numberfailed = numberfailed + 1
    print("The number of students who passed: ", numberpassed)
    print("The number of students who failed: ", numberfailed)
        
else:
    print("Invalid number of grades") 
