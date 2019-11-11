#!/usr/bin/env python
import math as m

ngrades = input("Enter how many expected grades")
ngrades = int(ngrades)

if ngrades > 0:
    names = []
    scores = []
    grades = []
    n1 = 0
    n2 = 0
    pstudents = 0
    fstudents = 0
    inv = 0
    for n1 in range(ngrades):
        inname = input(("Enter name (" + (str(n1)) +"):"))

        if inname is "":
           print("Enter a valid entry ")

        inscore = input(("Enter score for (" + (str(inname)) +"):"))

        try:
            if float(inscore) >= 70:
                grades.append("P")
                pstudents += 1
            else:
                grades.append("F")
                fstudents += 1
            names.append(inname)
            scores.append(inscore)
        except:
            inv += 1

    for n2 in range(len(grades)): print(names[n2]+":", ("Pass" if (grades[n2] == "P") else "Fail"));
    
    print("Passing", pstudents)
    print("Failing", fstudents)
    if inv >0:
        print("Invalid", inv)





        


    

