F = 0  # starts variable to 0
P = 0  # starts variable P to 0
i = 0  # sets variable i to 0
Grades = input(
    'How many grades?')  # asks the user to input how many gardes there are are and stores it as variable grades
Grades = int(Grades)  # converts grades into an integer
if Grades > 0:  # if there are more than 0 grades the following code will run
    Nlist = []  # creates blank list Nlist
    Slist = []  # creates blank list Slist
    Glist = []  # creates blank list Glist
    for x in range(Grades):  # for loop that loops the value of variable Grades
        N = input(
            'Input the Name of the Student')  # asks the user to input the name of the student and stores it as
        # variable N
        Nlist.append(N)  # adds variable N toNlist
        S = input("Input the student's score: ")  # asks useer to input the score and saves it in variable S
        S = int(S)  # converts S into an integer
        Slist.append(S)  # adds variable S to Slist
        if S < 70:  # the following code will run if S is less than 70
            Glist.append('F')  # adds F to Glist
        else:
            Glist.append('P')  # adds P to Glist
    for Grade in Glist:  # for loop that runs as long as the list Glist
        Name = Nlist[i]  # sets variable Name to Nlist[i]
        Score = Slist[i]  # sets variable Score to Slist[i]
        if Grade == 'P':  # the following code will run if Grade is equal to P
            print(Name, Score, 'Pass')
            P = P + 1  # adds up the number of P's
            i = +1  # allows the code to move to the next section of the list
        elif Grade == 'F':  # elif statement that runs the below code if grade is equal to F
            print(Name, Score, 'Fail')  # displays the Name, Score, and the word Fail
            F = F + 1
            i = i + 1
    print('The number of students who passed is', P)
    print('The number of students who failed is', F)
else:
    print('Invalid number of grades')
