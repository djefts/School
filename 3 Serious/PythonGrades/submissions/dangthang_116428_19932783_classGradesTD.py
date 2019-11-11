#Thang Dang
ans = int(input("Enter the number of grades to expect: "))
if ans > 0:
    nameList = []
    scoreList = []
    gradeList = []
    i=0
    for i in range (ans):
        name = input("Enter a name: ")
        nameList.append(name)
        score = float(input("Enter a score: "))
        scoreList.append(score)
        if score >= 70:
            gradeList.append("P")
        else:
            gradeList.append("F")

    pcounter = 0
    fcounter = 0
    for i in range (ans):
        name = nameList[i]
        score = scoreList[i]
        grade = gradeList[i]
        if grade == "P": 
            print(name, score, "pass")
            pcounter = pcounter + 1
        else:
            print(name, score, "fail")
            fcounter = fcounter + 1

    print("Number of students who passed :", pcounter)
    print("Number of students who failed :", fcounter)
    
else:
    print("Invalid number of grades")
    
