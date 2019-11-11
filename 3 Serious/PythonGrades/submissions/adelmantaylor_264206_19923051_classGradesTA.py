#Taylor Adelman

# ask the user to enter the number of grades to expect

number = int(input("Enter the number of grades to expect: "))

if number > 0:
    listofnames = []
    listofscores = []
    listofgrades = []
    for i in range(number):
        name = input("Please enter a name (" + str(i+1) + "): ")
        listofnames.append(name)
        score = float(input("Please enter a score (" + str(i+1) + "): "))
        listofscores.append(score)
        if score >= 70:
            listofgrades.append('P')
        else:
            listofgrades.append('F')
    passing = 0
    failing = 0
    i = 0
    for fname in listofnames:
        if listofgrades[i] == 'P':
            score = listofscores[i]
            print(fname, score, "Pass")
            passing = passing + 1
        else:
            score = listofscores[i]
            print(fname, score, "Fail")
            failing = failing + 1
        i = i + 1
    print("The number of students who passed is: ", passing)
    print("The number of students who failed is: ", failing)

else:
    print("Invalid number of grades")

