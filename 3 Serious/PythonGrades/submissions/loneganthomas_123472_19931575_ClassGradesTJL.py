# Thomas Lonegan
# CS118
# 10/4/19
# this program is designed to tell the user(s) if they failed a class or whatever was graded
ans = int(input("Enter number of grades expected: "))
# this statement tells user to imput a whole number based on the amount of grades that will be input
if ans > 0: # if a positive number is selected, 3 empty lists will be created
    nameList = []
    scoreList = []
    gradeList = []
    for x in range(ans): # this line limits the loops range to the number the user input
        name = str(input("Enter name: "))
        while name == "": # this line ensures they add a name
            name = input("You need to enter a name: ")
        nameList.append(name) # this adds the given name to the name list
        score = int(input("Enter a score from 0 to 100: "))
        while score < 0 or score > 100:
            score = int(input("Enter a score from 0 to 100: ")) #tells user to enter score
        scoreList.append(score) #adds score to score list
        if score > 69:
            gradeList.append("P") #this designates a passing grade if the score is a 70 or above
        else:
            gradeList.append("F")#this designates a failing grade if the score is below a 70
i = 0
F = 0
P = 0
#list of variables set to 0
for grade in gradeList: #this is setting up what the user will see after they've completed their inputs
    if grade == "P":
        name = nameList[i]
        score = scoreList[i]
        print(name, score, "Pass", "Good Job!") 
        P = P + 1
        i = i + 1
    elif grade == "F":
        name = nameList[i]
        score = scoreList[i]
        print(name, score, "Fail", "Better Luck Next Time!")
        F = F + 1
