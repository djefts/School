#----------------------------------------------------------------
#Submitted by Jacob Welch
#Homework 3
#Credit to:
#Jake Welch
#Submitted on 10/1/2019
#By submitting the program with my name,
#I affirm that the creation and modifacation
#of this program is primarily my own work
#----------------------------------------------------------------
#This program will act to create a list of passing and failing students
import math
amtgr = int(input("How many grades will be input? "))
listofgr=[]
listofnames=[]
listofscores=[]
listfin = []
if amtgr == 0 :
    print("No grades will be input")
else :
    stupass = 0
    stufail = 0
    tot = 0
    for a in range(0, amtgr) :
        name = input("Enter student name " + str(a+1)+ " ")
        score = input("Enter the grade of " + name + " ")
        tot = tot +int(score)
        listofnames.append(name)
        listofscores.append(score)
        if float(score) >= 70 :
            gr = "P"
            listofgr.append(gr)
            fin = name + " " + score + " PASS"
            listfin.append(fin)
            stupass = stupass + 1
        else :
            gr = "F"
            listofgr.append(gr)
            fin = name + " " + score + " FAIL"
            listfin.append(fin)
            stufail = stufail + 1
    amttot= len(listofnames)
    for a in range(0, amttot) :
        print(listfin[a])
    print("A total of ", int(stupass), " students passed. ")
    print("A total of ", int(stufail), " students failed. ")
    print("The class average was ", tot/amttot)
print("Program complete")
