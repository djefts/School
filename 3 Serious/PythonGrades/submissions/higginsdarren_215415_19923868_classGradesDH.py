#Darren Higgins

numGrades = int(input('Enter the number of grades to expect: '))

if numGrades > 0 :
    listOfNames = []
    listOfScores = []
    listOfGrades = []
    for i in range(numGrades):
        name = input("Please enter a name (" + str(i+1) + "): ")
        listOfNames.append(name)
        score = float(input("Please enter a score (" + str(i+1) + "): "))
        listOfScores.append(score)
        if score >= 70:
            listOfGrades.append('P')
        else:
            listOfGrades.append('F')
    passing = 0
    failing = 0
    i = 0
    for fname in listOfNames:
        if listOfGrades[i] == 'P':
            score = listOfScores[i]
            print(fname, score, "Pass")
            passing = passing + 1
        else:
            score = listOfScores[i]
            print(fname, score, "Fail")
            failing = failing + 1
        i = i + 1
        
    print("The number of students that passed is ", passing)
    print("The number of students that failed is ", failing)
          
