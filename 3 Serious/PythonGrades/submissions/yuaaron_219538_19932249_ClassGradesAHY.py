numofgrad = int(input("How many grades do you have? "))
if(numofgrad > 0):
    namelist = []
    gradelist = []
    scorelist = []
    for i in range(numofgrad):
        name = input("What is the name? ")
        namelist.append(name)
        score = int(input("What is the scored? "))
        scorelist.append(score)
        if(score >= 70):
            gradelist.append("P")
        else:
            gradelist.append("F")
            
    numofpass = 0
    numoffail = 0
    for i in range(numofgrad ):
        if gradelist[i] == 'P':
            print(namelist[i] , scorelist[i] , "Pass")
            numofpass = numofpass + 1
        else:
            print(namelist[i] , scorelist[i], "Fail")
            numoffail = numoffail + 1
    print("The number of students who passed: ", numofpass)
    print("The number of students who failed: ", numoffail)
else:
    print("Invalid Number of grades")
    
                
