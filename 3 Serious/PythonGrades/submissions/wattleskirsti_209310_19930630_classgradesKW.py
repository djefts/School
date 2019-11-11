numberofgrades = int(input("Enter the number of expected grades"))
if numberofgrades > 0:
    listofnames = []
    listofscores = []
    listofgrades = []
    for i in range(numberofgrades):
        name = input("Please enter a name")
        listofnames.append(name)
        score = int(input("Please enter a score"))
        listofscores.append(score)
        if score >= 70:
            grade = "P"
            listofgrades.append("P")
        else:
            grade = "F" 
            listofgrades.append("F")
            
    passingstudents = 0
    failingstudents = 0
    i = 0
    for grade in listofgrades:
        if grade == "P":
            name = listofnames[i]
            score = listofscores[i]
            print(name, score, "pass")
            passingstudents = i + 1
        else:
            name = listofnames[i]
            score = listofscores[i]
            print(name, score, "fail")
            failingstudents = i + 1
        i+=1
    print("The number of passing students is" , passingstudents)
    print("The number of failing students is", failingstudents)
else:
    print("Invalid number of grades")

        


