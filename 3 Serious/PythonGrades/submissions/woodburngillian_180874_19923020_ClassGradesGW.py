#ClassGradesGW.py

numofgrades= int(input("How many grades will you be entering? "))

if numofgrades>0:
    names=[]
    scores=[]
    grades=[]
    
    for i in range(numofgrades):
        name=input("Please enter name of student  ")
        names.append(name)

        score=float(input("Please enter the score for student "))
        scores.append(score)

        if score>=70:
            grades.append("P")
        else:
            grades.append("F")
    passcount=0
    failcount=0
    index=0
    for grade in grades:
        if grade=="P":
            print(names[index], scores[index], "pass")
            passcount=passcount+1
        else:
            print(names[index], scores[index], "fail")
            failcount=failcount+1
        index=index+1
    print("Number of students who passed: ", passcount)
    print("number of students who failed: ", failcount)

else:
    print ("Invalid Number of grades")
