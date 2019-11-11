import csv


# The following function expects CSV files with header lines
def get_data_from_file(filename, dataLists):
    file = open(filename, "r+")
    csv_reader = csv.reader(file)
    for line in csv_reader:
        dataLists.append(line)
    file.close()
    return dataLists


def menu():
    choice = input('stuff')
    return choice


def display_data(data_matrix):
    size_of_data = len(data_matrix)
    
    answer = input("Enter q to quit or just enter to see the next 25 lines: ")
    start = 0
    end = 25
    while size_of_data > 0 and answer.upper() != 'Q':
        if end > size_of_data:
            end = size_of_data
            
        columns = "Year\tType   \tCost"
        print(columns)
        
        for i in range(start, end):
            line = data_matrix[i]
            print(line[0], line[1], line[2])
            
        size_of_data = size_of_data - 25
        
        if size_of_data > 0:
            answer = input("Enter q to quit or just enter to see the next 25 lines: ")


def get_total_by_year(expenditures):
    year_choice = input("Enter a year or enter 'every' for all: ")
    
    total = 0
    if year_choice == "every":
        for line in expenditures:
            total = total + line[2]
    else:
        for line in expenditures:
            if line[0] == year_choice:
                total = total + line[2]
            else:
                pass
    
    print("Total for {}".format(year_choice), total)
    return total


def get_total_by_item(expenditures):
    item_choice = input("Enter an item or enter 'every' for all: ")
    
    total = 0
    if item_choice == "every":
        for line in expenditures:
            total = total + line[2]
    else:
        for line in expenditures:
            if line[1] == item_choice:
                total = total + line[2]
            else:
                pass
    
    print("Total for {}".format(item_choice), total)
    return total


########################################
# main logic #

"""
Assign an empty list to a variable that will hold all of the data.
Assign the string " expenditures.csv " to a variable that will store the file name.
Call the getDataFromFile function and pass it the variable with the file name and the variable with the empty list.
Call the menu function and store what it returns in a variable. While the user's choice is not q and user's choice is not Q do:
If the choice is 1, then call the displayAllData function and pass it the list. If the choice is 2, then call the getTotalByYear function and pass it the list.
Else if the choice is 3, then call the getTotalByItem function and pass it the list.
Else print the fact that the choice was invalid.
Call the menu function and store what it returns in the same variable as above the loop.
"""

expenditures = []
filename = "expenditures.csv"
get_data_from_file(filename, expenditures)
choice = menu()
while choice != 'q' and choice != 'Q':
    if choice == '1':
        display_data(expenditures)
    elif choice == '2':
        get_total_by_year(expenditures)
    elif choice == '3':
        get_total_by_item(expenditures)
    else:
        print("choice was invalid")
    choice = menu()
