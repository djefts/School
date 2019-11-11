#Clancy Richardson
# Octobe 1, 2019
#CS118
#Grade Pass Fail Calculator

#Initial setup
namesList = []
scoresList = []
gradesList = []
passcount = 0
failcount = 0

#User Input for number of grades/students
num = int(input('Enter the number of grades to expect. '))

#Error checking to make sure a positive number of students 
if num > 0:
    #Loop for gathering Names and Scores
    for x in range(num):
        names = input('Enter a name. ')
        namesList.insert(x,names)
        score = int(input('Enter the score. '))
        scoresList.insert(x,score)

        #Determining passing and failing grades
        if score >= 70:
            gradesList.insert(x,'P')
        else:
            gradesList.insert(x,'F')

        #Check for testing purpose
        #print(namesList)
        #print(scoresList)
        #print(gradesList)
        #print(x)

    #Counting the number of passing and failing students and pritning results       
    for x in range(num):
            #if gradesList[x] is 'P':
        if gradesList[x] == 'P':
            print('Name: ', namesList[x], 'Score: ', scoresList[x], 'Pass')
            passcount = passcount + 1
                
        else:
            print('Name: ', namesList[x], 'Score: ', scoresList[x], 'Fail')
            failcount = failcount + 1
                
    print('Number of passing students: ',passcount)
    print('Number of failing students: ',failcount)


else:
    #The program will not execute when less than 1 student is being graded
    print('Invalid number of grades.')
