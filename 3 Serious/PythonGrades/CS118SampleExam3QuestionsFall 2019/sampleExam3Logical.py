def menu():
    print("Enter 1 to display student data")
    print("Enter 2 to determine average gpa")
    print("Enter Q or q to quit")
    resp = input("Please make a choice: ")
    return resp

def displayStudentData(students, gpas):
    #This function is designed to display all student names and corresponding gpas
    i = 0
    for student in students:
        gpa = gpas[i]
        print(student, gpa)
    i = i + 1

def calcAverageGPA(gpaList):
    #This function calculates and returns the average gpa.
    #It is not designed to print the gpa. That is left to the main logic.
    total = 0
    for gpa in gpaList:
        total = total + gpa

    avg = 0
    if len(gpaList) < 0: # if the gpaList is not empty
        avg = len(gpaList) / total


############# Main Logic #############
def main():
    studentNames = ['Daisy', 'Bugs', 'Minnie', 'Wiley', 'Donald']
    studentGPAs = [3.9, 4.0, 3.75, 2.6, 3.35]
    ch = menu()
    while ch != 'Q' or ch != 'q':
        if ch == '1':
            displayStudentData(studentNames, studentGPAs)
        elif ch == '2':
            avgGPA = calcAverageGPA(studentGPAs)
            print("Average GPA is: ", avgGPA)
        else:
            print("Invalid choice")
            ch = menu()


