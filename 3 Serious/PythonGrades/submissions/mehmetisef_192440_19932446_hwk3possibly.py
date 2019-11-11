numOfGrades = int(input("How many grades will you be entering... "))
if numOfGrades <= 0:
    print("Invalid Input")
else:

    nameList = []
    scoreList = []
    gradeList = []
    
    for i in range(0, numOfGrades):
        name = input("Enter a name: ")
        while name == "":
            name = input("You need to enter a name")
        nameList.append(name)
        score = float(input("Enter a test score: "))
        while score == "":
            score = float(input("Enter a test score: "))
        scoreList.append(score)

        if score >= 70:
            grade = 'P'
        else:
            grade = 'F'
        gradeList.append(grade)
        
        passed = 0
        failed = 0
    
    
        i = 0
    for name in nameList:
        score = scoreList[i]
        grade = gradeList[i]
        if score >= 70:
            passed = passed + 1
            print(name, score, "Pass")
        elif score < 70:
            failed = failed + 1
            print(name, score, "Fail")
        i = i + 1

    print(passed, 'Students passed the class', failed, "students failed the class")
    

