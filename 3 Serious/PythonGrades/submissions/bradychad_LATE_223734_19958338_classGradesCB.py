numGrades = int(input("Enter number of grades to expect "))
if numGrades > 0:
    nameList = []
    scoreList = []
    gradeList = []
    for i in range(numGrades):
        name = input("Enter name ")
        nameList.append(name)
        score = int(input("Enter score "))
        scoreList.append(score)
        if score >= 70:
            grade = "P"
            gradeList.append("P")
        else:
            grade = "F"
            gradeList.append("F")
print(nameList, scoreList, gradeList)
Passed = 0
Failed = 0
for i in range(numGrades):
    if grade == "P":
        i = 0
        for score in scoreList:
            name = nameList[i]
            print(name, score)
            i = i + 1
    
        
        
