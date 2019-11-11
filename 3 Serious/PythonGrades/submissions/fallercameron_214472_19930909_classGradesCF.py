#Cameron Faller
#10-4-19
#As discussed in the emails I have used a more advanced coding to get the task
#done perscribed by the homework. You have already approved the coding on 10/4
nameList = []
scoreList = []
gradeList = [] 
ans = input("Do you have any scores to enter:  (Y/N) ")
while ans.upper() == "Y":
    name = input("Enter name: ")
    while name == "":
        name = input("You need to enter a name: ")
    nameList.append(name)
    score = float(input("Enter a score from 0 to 100: "))
    while score < 0 or score >100:
        score = float(input("Enter a score from 0 to 100: "))
    scoreList.append(score)
    ans = input("Do you have any scores to enter:  (Y/N)")

i = 0
passed = 0
failed = 0

for name in nameList:
    score = scoreList[i]
    if score >= 70:
        gradeList.append("P")
        print(name,score,"Pass")
        passed = passed + 1
    else:
        gradeList.append("F")
        print(name,score,"Fail")
        failed = failed + 1
    i = i + 1
print("Number of Students who Passed:", passed)
print("Number of Students who Failed:", failed)

