import pytesseract as space
from PIL import Image
from PIL import ImageEnhance as ie
from webstuff import Web as web
import os
import re


class Parse:
    companiesDict = {
        "Starbucks": (web.starbucks, {"Starbucks", "starbucks", "star bucks", "starbuck", "Sturbucks"}),
        "Baskin-Robbins": (
            web.baskin_robbins, {"Baskin-Robbins", "Baskin Robbins", "Baskin", "Robbins", "baskin", "robbins"}),
        "Dunkin' Donuts": (web.dunkin_donuts, {"Dunkin' Donuts", "Dunkin", "Dunkin'", "Donuts"}),
        "Chick-Fil-A": (web.chickfila, {"Chick-Fil-A", "chick-fil-a"}),
        "Visa": ('web.visa', {"Visa, visa"}),
        "MasterCard": ('web.mastercard', {"MasterCard", "Mastercard"}),
        "Publix": (web.publix, {"Publix", "publix"}),
        "Panera": (web.panera_bread, {"Panera Bread", "Panera", "panera", "Bread", "bread"})
    }
    
    def __init__(self, front, back):
        print("Initializing Parse class\n")
        self.text = ""
        self.company = ""
        self.card_number = ""
        self.pin = ""
        self.front = front
        self.back = back
    
    def enhance(self, image):
        # base enhancement
        basewidth = 5000
        img = image
        wpercent = (basewidth / float(img.size[0]))
        hsize = int((float(img.size[1]) * float(wpercent)))
        img = img.resize((basewidth, hsize), Image.ANTIALIAS)
        
        # color enhancement
        converter = ie.Color(img)
        i = converter.enhance(0.0)  # darker
        return i
    
    def parse_words(self, front, back):
        frontImg = Image.open(front)
        backImg = Image.open(back)
        frontImg = self.enhance(frontImg)
        backImg = self.enhance(backImg)
        text = "Front Words:\n"
        config = '--psm 10 --oem 3'
        text += space.image_to_string(frontImg, lang = 'eng', config = "")
        text += "\n\nBack Words:\n" + space.image_to_string(backImg, lang = 'eng', config = "")
        text = os.linesep.join([s for s in text.splitlines() if s])
        return text
    
    # deprecated
    def parse_digits(self, front, back):
        frontImg = Image.open(front)
        backImg = Image.open(back)
        text = "Front Numbers:\n"  # eventually unnecessary
        config = '--psm 10 --oem 3 -c tessedit_char_whitelist=0123456789'
        text += space.image_to_string(frontImg, lang = 'eng', config = config)
        text += "\n\nBack Numbers:\n"  # eventually unnecessary
        text += space.image_to_string(backImg, lang = 'eng')
        return text
    
    def get_company(self):
        for company, v in self.companiesDict.items():
            for name in v[1]:
                if self.text.find(name) != -1:
                    return company
        print(self.text)
        raise LookupError("Unable to find the company this card is for.")
    
    def get_number(self):
        # collect all numbers on the card
        numbers = [s for s in self.text.split() if s.isdigit()]
        app = [s for s in self.text.split('.') if s.isdigit()]
        for n in app:
            numbers.append(n)
        app = [s for s in self.text.split(',') if s.isdigit()]
        for n in app:
            numbers.append(n)

        re.findall(r"[\w']+|[.,!?;]", "Hello, I'm a string!")
        
        card_number = ""
        i = 0
        print(numbers)
        for number in numbers:
            if 5 < len(number) < 15:
                self.pin = number
                continue
            if i > 3:
                return int(card_number)
            if len(number) >= 3:
                i += 1
                card_number += number
        print(numbers, self.text, sep = "\n")
        raise LookupError("Unable to find a card number.")
    
    def parse(self):
        print("Getting words please wait...\n")
        self.text = self.parse_words(self.front, self.back)
        
        print("Finding company please wait...\n")
        try:
            self.company = self.get_company()
        except LookupError as e:
            print(e)
            exit(1)
        
        print("Finding card number please wait...\n")
        try:
            self.card_number = self.get_number()
        except LookupError as e:
            print(e)
            exit(1)
        
        print(self.text)


if __name__ == '__main__':
    base_path = '/Users/davidjefts/Desktop/School/Hackathon/ERAU2019/HackRiddle/Test Pictures/'
    parse = Parse(base_path + "star_front.png", base_path + "star_back0.png")
    #back0
    #back1
    #back2
    #back3
    #back4
    #back5
    parse.parse()
    print(parse.text)
    print("\nCompany: " + parse.company)
    print("Card number: " + str(parse.card_number))
    print("Pin number: " + str(parse.pin))
