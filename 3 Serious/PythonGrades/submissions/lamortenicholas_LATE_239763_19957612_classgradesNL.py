numgrades=input("Enter the number of grades")
numgrades=int(numgrades)
if numgrades >0:
    names=[]
    scores=[]
    grades=[]
    for i in range(numgrades):
        name=input("Enter a name")
        names.append(name)
        score=input("Enter a score")
        score=float(score)
        scores.append(score)
        if score >=70:
            grades.append("P")
        else:
            grades.append("F")
    passcount=0
    failcount=0
    for i in range(numgrades):
        name=names[i]
        score=scores[i]
        grade=grades[i]
        if grade=="P":
            print(name,score,"Pass")
            passcount=passcount+1
        else:
            print(name,score,"Fail")
            failcount=failcount+1
    print("Number of students who passed",passcount)
    print("Number of students who failed",failcount)
else:
    print("Invalid number of grades")
    
    
