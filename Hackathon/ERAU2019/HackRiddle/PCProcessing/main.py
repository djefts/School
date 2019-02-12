from webstuff import Web
from parsetext import Parse
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


def grab_pics():
    site = "https://www.erauezgiftcard.com"
    driver = webdriver.Chrome()
    driver.get(site)
    assert 'HOME' in driver.title
    
    xpathF = '//*[@id="id1549818255063"]'
    xpathB = '//*[@id="id1549820171244"]'
    
    """front butt"""
    frontE = driver.find_element_by_xpath(xpathF)
    f_loc = frontE.location
    f_size = frontE.size
    front = driver.get_screenshot_as_png()
    left = f_loc['x']
    top = f_loc['y']
    right = f_loc['x'] + f_size['width']
    bottom = f_loc['y'] + f_size['height']
    front = front.crop((left, top, right, bottom))  # defines crop points
    front.save('web-front.png')  # saves new cropped image
    
    """back butt"""
    backE = driver.find_element_by_xpath(xpathB)
    b_loc = backE.location
    b_size = backE.size
    back = driver.get_screenshot_as_png()
    left = b_loc['x']
    top = b_loc['y']
    right = b_loc['x'] + b_size['width']
    bottom = b_loc['y'] + b_size['height']
    back = front.crop((left, top, right, bottom))  # defines crop points
    back.save('web-back.png')  # saves new cropped image
    
    # __main__(front, back)


def __main__():
    companiesDict = {
        "Starbucks": (Web.starbucks, {"Starbucks", "starbucks", "star bucks", "starbuck", "Sturbucks"}),
        "Baskin-Robbins": (
            Web.baskin_robbins, {"Baskin-Robbins", "Baskin Robbins", "Baskin", "Robbins", "baskin", "robbins"}),
        "Dunkin' Donuts": (Web.dunkin_donuts, {"Dunkin' Donuts", "Dunkin", "Dunkin'", "Donuts"}),
        "Chick-Fil-A": (Web.chickfila, {"Chick-Fil-A", "chick-fil-a"}),
        "Visa": ('Web.visa', {"Visa, visa"}),
        "MasterCard": ('Web.mastercard', {"MasterCard", "Mastercard"}),
        "Publix": (Web.publix, {"Publix", "publix"}),
        "Panera": (Web.panera_bread, {"Panera Bread", "Panera", "panera", "Bread", "bread"})
    }
    print("Running the main method")
    
    """"""""""""""""""
    """"BASE FILES"""""
    """"""""""""""""""
    
    # get the necessary values
    base_path = '/Users/davidjefts/Desktop/School/Hackathon/ERAU2019/HackRiddle/Test Pictures/'
    front = base_path + "chick_front.png"
    back = base_path + "chick_back.png"
    parse = Parse(front, back)
    parse.parse()
    company = parse.company
    card_num = parse.card_number
    card_pin = parse.pin
    
    # send that shit to the web
    web = Web()
    balance = companiesDict[company][0](web, card_num, card_pin)
    
    # gimme that sweet numerical
    if balance[0] == 'C':
        print(balance)
    else:
        print("Card balance is: " + balance)


if __name__ == '__main__':
    __main__()
