num_grades = int(input("How many grades do you expect? "))

i = 0
if num_grades > 0:
    namelist = []
    scorelist = []
    gradelist = []
    for i in range(num_grades):
        name = input("What is your name?(" + str(i+1) + "): ")
        namelist.append(name)
        score = float(input("What is your score?(" + str(i+1) + "): "))
        scorelist.append(score)
        if score >= 70:
            gradelist.append("P")
        else:
            gradelist.append("F")


            students_who_fail = 0
            students_who_pass = 0
            
    for i in range(num_grades):
        nameL = namelist[i]
        scoreL = scorelist[i]
        gradeL = gradelist[i]
        
        
        if gradelist[i] == "P":
            print(nameL, scoreL, 'Pass')
            students_who_pass = students_who_pass + 1
                    
        else:
            print(nameL, scoreL,'Fail')
            students_who_fail = students_who_fail + 1


    print("Number of students who passed:", students_who_pass)
    print("Number of students who failed:",students_who_fail)
else:
    print("Invalid number of grades")
            


                
                
