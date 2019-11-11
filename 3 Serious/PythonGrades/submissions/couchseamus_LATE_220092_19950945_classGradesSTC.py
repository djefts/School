nameList = []
gradeList = []
scoreList = []
ans = input("Do you have any grades to enter: (Y/N) ")
while ans.upper() == "Y":
    name = input("Enter name: ")
    while name == "":
        name = input("You need to enter a name: ")
    nameList.append(name)
    grade = float(input("Enter a grade from 0 to 100: "))
    while grade < 0 or grade > 100:
        grade = float(input("Enter a grade from 0 to 100: "))
    gradeList.append(grade)
    ans = input("Do you have any grades to enter: (Y/N) ")
else:
    print("Invalid number of grades")

i = 0
for name in nameList:
    grade = gradeList[i]
    if grade >= 70:
        print(name, grade, "P")
    else:
        print(name, grade, "F")
    i = i + 1

pCount = 0
fCount = 0
i = 0
for name in nameList:
    grade = gradeList[i]
    if grade >= 70:
        print(name, grade, "Pass")
        pCount += 1
    else:
        print(name, grade, "Fail")
        fCount += 1
    i = i + 1
print("The number of students who passed is", (pCount))
print("The number of students who failed is", (fCount))

   


