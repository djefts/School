# Daniel Lee
# This is my own work
# I did not cheat

import math

# Ask the user to enter the number of grades to expect
grades_expected = input('Enter the number of grades you want to enter: ')

grades_expected = float(grades_expected)
grades_expected = round(grades_expected)

# Trap
if (grades_expected < 0):
    print('ERROR! Incorrect number entered!')

if (grades_expected > 0):
    namesList = list()
    scoresList = list()
    gradesList = list()
    
    for i in range(0, grades_expected):
        
        # Ask user for name and add to list
        name = input('Enter name: ')
        namesList.append(name)
        
        # Ask user for score and add to list
        score = input('Enter score: ')
        score = float(score)
        scoresList.append(score)
        
        # Input letters for pass or fail
        if (score >= 70):
            gradesList.append("P")
        
        else:
            gradesList.append("F")
    
    # Make 2 variables at zero
    num_passed = 0
    num_failed = 0
    
    print("ur dumb", len(gradesList))
    
    for k in range(0, len(gradesList)):
        name = namesList[k]
        score = scoresList[k]
        letter = gradesList[k]
        # if letter is a P
        if k == "P":
            # Print out name, score and "Pass"
            print("name score PASS", name, score, k)
            # Increment num_passed
            num_passed = num_passed + k
        elif k == "F":
            # Print out name, score, and "Fail"
            print("name score FAIL", name, score, k)
            # Increment num_failed
            num_failed = num_failed + k
