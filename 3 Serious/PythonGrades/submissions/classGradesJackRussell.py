numGrades = input('How many grades will be taken: ')
numGrades = int(numGrades)
if numGrades > 0:
    namesList = []
    scoresList = []
    gradesList = []
    for i in range(numGrades):
        name = input('What is the name: ')
        score = input('What is the score: ')
        score = float(score)
        namesList.append(name)
        scoresList.append(score)
        if score > 70:
            gradesList.append('P')
        elif score <= 70:
            gradesList.append('F')
    passNumb = 0
    failNumb = 0
    for i in range(numGrades):
        name1 = namesList[i]
        score1 = scoresList[i]
        grade1 = gradesList[i]
        
        if grade1 == 'P':
            print(name1, score1, 'Pass')
            passNumb = passNumb + 1
        else:
            print(name1, score1, 'fail')
            failNumb = failNumb + 1
    
    print('The number of students who passed ', passNumb)
    print('The number of students who failed ', failNumb)
else:
    print('Error wrong number of grades')
    numGrades = input('How many grades will be taken: ')
