nameList = []
gradeList = []
ans = input("Do you have any grades to enter: (Y/N) ")
while ans.upper() == "Y":
    name = input("Enter name: ")
    while name == "":
        name = input("You need to enter a name: ")
    nameList.append(name)
    examList = []
    grade = float(input("Enter a grade from 0 to 100: "))
    while grade < 0 or grade > 100:
        grade = float(input("Enter a grade from 0 to 100: "))
    examList.append(grade)
        
    gradeList.append(examList)
    ans = input("Do you have any grades to enter: (Y/N) ")

    # print(grade)
avg = round(grade, 0)
if grade >= 89:
    print("A")
elif grade >= 79:
    print("B")
elif grade >= 69:
    print("C")
elif grade >= 59:
    print("D")
elif grade >= 0:
    print("F")



i = 0
for name in nameList:
    grade = gradeList[i]
    print(name, grade)
    i = i + 1






