# Glenn M. Little
# Date: October 1, 2019
import math

# initial input
numGrade = int(input('How many grades are you inputing?'))
# first if statement

if numGrade > 0:
    nameList = []
    scoreList = []
    gradeList = []
    passCount = 0
    failCount = 0
    # for loop starts here
    for x in range(numGrade):
        # nameList
        name = input("What is the name of the student?")
        nameList.append(name)
        # scoreList
        addScore = float(input("What was their score? "))
        scoreList.append(addScore)
        # if 2
        if addScore >= 70:
            gradeList.append('p')
        else:
            gradeList.append('f')
    # testing print
    # print(nameList)
    # print(scoreList)
    # print(gradeList)
    for x in range(numGrade):
        if gradeList[x] == 'p':
            nList = nameList[x]
            sList = scoreList[x]
            print("Name:", nList, "Score:", sList, "pass")
            passCount = passCount + 1
        else:
            nList = nameList[x]
            sList = scoreList[x]
            print("Name:", nList, "Score:", sList, "fail")
            failCount = failCount + 1
    print("Total number of passing students is: ", passCount)
    print("Total number of failing students is: ", failCount)
else:
    print("Invalid number of grades.")
