###SPENCER WEIGAND HW33333

import math as m

nGrades = input("Enter the amount of expected grades")
nGrades = int(nGrades)

if nGrades > 0:
    names = []
    scores = []
    grades = []
    n1 = 0
    n2 = 0
    pStudents = 0
    fStudents = 0
    inv = 0
    for n1 in range(nGrades):
        inname = input(("Enter name (" + (str(n1)) + "):"))
        
        if inname is "":
            print("Enter a valid entry")
        
        inscore = input(("Enter score for (" + (str(inname)) + "):"))
        
        try:
            if float(inscore) >= 70:
                grades.append("P")
                pStudents += 1
            else:
                grades.append("F")
                fStudents += 1
            names.append(inname)
            scores.append(inscore)
        except:
            inv += 1
    
    for n2 in range(len(grades)):
        print(names[n2] + ":", ("Pass" if (grades[n2] == "P") else "Fail"))
    
    print("passing", pStudents)
    print("Failing", fStudents)
    if inv > 0:
        print("invalid", inv)
