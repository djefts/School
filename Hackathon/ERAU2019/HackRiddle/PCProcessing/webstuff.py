from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC


class Web:
    def __init__(self):
        self.balance = 0
        self.browser = None
    
    def starbucks(self, card_num, card_pin):
        self.browser = webdriver.Chrome()
        # get the site
        site = 'https://www.starbucks.com/card'
        self.browser.get(site)
        assert 'Starbucks Gift Card' in self.browser.title
        
        # enter card deets
        number = self.browser.find_element_by_name('Card.Number')
        number.send_keys(str(card_num) + Keys.TAB)
        pin = self.browser.find_element_by_name('Card.Pin')
        pin.send_keys(str(card_pin) + Keys.RETURN)
        
        # get those numerical
        xpath = '//*[@id="fetch_balance"]/h2/span'
        try:
            WebDriverWait(self.browser, 5).until(EC.presence_of_element_located((By.XPATH, xpath)))
            self.balance = self.browser.find_element_by_xpath(xpath).get_attribute("innerHTML")
        finally:
            pass
            #self.browser.quit()
        return self.balance
    
    def publix(self, card_num, card_pin):
        return "Card balance cannot be checked online. Must check in person at Publix."
    
    def baskin_robbins(self, card_num, card_pin):
        return self.dunkin_donuts(card_num, card_pin)
    
    def dunkin_donuts(self, card_num, card_pin):
        self.browser = webdriver.Chrome()
        site = "https://www.dunkindonuts.com/en/dd-cards/check-balance"
        self.browser.get(site)
        assert "Dunkin' Donuts" in self.browser.title
        
        # enter card deets
        number = self.browser.find_element_by_name('card-number')
        number.send_keys(str(card_num) + Keys.TAB)
        pin = self.browser.find_element_by_name('pin')
        pin.send_keys(str(card_pin) + Keys.RETURN)
        
        # get those numerical
        xpathD = '//*[@id="main-content"]/div[2]/div/div/div/div[1]/div[1]/div/section/div[1]/div/div[2]/div[2]/div[' \
                 '2]/p[2]/span'
        xpathC = '//*[@id="main-content"]/div[2]/div/div/div/div[1]/div[1]/div/section/div[1]/div/div[2]/div[2]/div[' \
                 '2]/p[2]/sup'
        try:
            WebDriverWait(self.browser, 5).until(
                EC.presence_of_element_located((By.CLASS_NAME, 'form-card-slider__amount')))
            self.balance = self.browser.find_element_by_class_name('form-card-slider__amount')
        finally:
            pass
            self.browser.quit()
        return self.balance
    
    def panera_bread(self, card_num, card_pin):
        self.browser = webdriver.Chrome()
        site = "https://wbiprod.storedvalue.com/WBI/lookupservlet?language=en&host=panerabread.com"
        self.browser.get(site)
        assert "Panera" in self.browser.title
        
        # enter card deets
        number = self.browser.find_element_by_name('cardNoH')
        number.send_keys(str(card_num) + Keys.RETURN)
        
        # FUCK RECAPTCHA
        
        # get those numerical
        try:
            pass
            # WebDriverWait(self.browser, 5).until(EC.presence_of_element_located((By.CLASS_NAME,
            # 'form-card-slider__amount')))
            # self.balance = self.browser.find_element_by_class_name('form-card-slider__amount')
        finally:
            self.browser.quit()
        return self.balance
    
    def chickfila(self, card_num, card_pin):
        print("Card number:", card_num, card_pin)
        return "Card balance cannot be checked online. Call 1-888-232-1864."
    
    def chipotle(self, card_num, card_pin):
        self.browser = webdriver.Chrome()
        site = "https://www.chipotle.com/gift-cards-frame?id=cmg.bi"
        self.browser.get(site)
        assert "Chipotle" in self.browser.title
        
        # enter card deets
        number = self.browser.find_element_by_name('card_number')
        number.send_keys(str(card_num) + Keys.TAB)
        email = self.browser.find_element_by_name('email_address')
        email.send_keys("example@gmail.com" + Keys.TAB)
        phone = self.browser.find_element_by_name('mobile_phone')
        phone.send_keys("1234567890" + Keys.TAB)
        captcha = self.browser.find_element_by_name('g-recaptcha-response')
        captcha.send_keys(Keys.ENTER + Keys.TAB + Keys.TAB + Keys.TAB + Keys.TAB + Keys.ENTER)
        
        # FUCK RECAPTCHA
        
        # get those numerical
        try:
            pass
            # WebDriverWait(self.browser, 5).until(EC.presence_of_element_located((By.CLASS_NAME,
            # 'form-card-slider__amount')))
            # self.balance = self.browser.find_element_by_class_name('form-card-slider__amount')
        finally:
            self.browser.quit()
        return self.balance


if __name__ == '__main__':
    web = Web()
    web.starbucks(6156564187185371, 49216115)
    # web.dunkin_donuts(6107864420687896, 46838537)  # FUCK CAPTCHA
    # web.chipotle(6120397727116497)
    print(web.balance)
