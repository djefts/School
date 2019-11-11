numofgrades = int(input('Enter the number of grades you expect: '))
if numofgrades <= 0:
    print('Invalid number of grades')
else:
    names = []
    scores = []
    grades = []
    for i in range(numofgrades):
        name = input('Please enter number ('+ str(i+1) + ') name: ')
        names.append(name)
        #print(names)
    for e in range(numofgrades):
        score = float(input('Please enter number ('+ str(e+1) + ') score: '))
        scores.append(score)
        #print(scores)
        if score < 70:
            grades.append('F')
        else:
            grades.append('P')
        #print(grades)

Pass = 0
Fail = 0
j = 0
for name in names:
    grade = scores[j]
    if grade >= 70:
        print(name, round(grade,0), 'Pass')
    else:
        print(name, round(grade,0), 'Fail')
    j = j + 1
    if grade >= 70:
        Pass = Pass + 1
    else:
        Fail = Fail + 1
print(Pass, 'student(s) passed the exam')
print(Fail, 'student(s) failed the exam')
