#Samantha Villagran
#Homework 3
#10/04/2019N

nameList = []
gradeList = []
scoreList = []



numGrades = int(input("Enter number of grades to expect "))


if numGrades > 0:

   
    for i in range(numGrades):
        name = input("Enter a name: ")
        nameList.append(name)
        score = int(input("Enter score "))
        scoreList.append(score)

        if score >= 70:
            gradeList.append("P")
        else:
            gradeList.append("F")




print(nameList)
print(gradeList)
print(scoreList)
