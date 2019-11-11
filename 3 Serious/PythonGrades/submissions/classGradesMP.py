numberOfGrades = int(input("How many grades do you expect? "))
if numberOfGrades > 0:
	nameList = []
	scoresList = []
	gradesList = []
	total = 0
	for i in range(numberOfGrades):
		nameList = int(input("Please enter the name you want to added to the list (" + str(i + 1) + "): "))
		scoresList = int(input("Please enter the score you want added to the list"))
		if score >= 70:
			print("P")
		elif score < 70:
			print("F")
	print("Let's count th number of people who got P or F")
	peoplewithP = 0
	peoplewithF = 0
	peoplewithP = []
	peoplewithF = []
	if gradesList == "P":
		print(nameList, scoresList, gradesList, "Pass")
	else:
		print(nameList, scoresList, gradesList, "Fail")
