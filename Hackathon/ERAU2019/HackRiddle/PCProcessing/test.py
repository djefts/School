import pytesseract as space
from PIL import Image


def enhance(image):
    img = image
    img.convert('L').resize([3 * _ for _ in img.size], Image.BICUBIC).point(lambda p: p > 75 and p + 100)
    return img


def parse_words(front, back):
    frontImg = Image.open(front)
    backImg = Image.open(back)
    frontImg = enhance(frontImg)
    backImg = enhance(backImg)
    text = "Front Words:\n"
    config = '--psm 10 --oem 3'
    text += space.image_to_string(frontImg, lang = 'eng', config = config)
    text += "\n\nBack Words:\n" + space.image_to_string(backImg, lang = 'eng')
    return text


def parse(front, back):
    text = ""  # parse_words(front, back)
    frontImg = Image.open(front)
    enhance(front)
    enhance(back)
    return text


base_path = '/Users/davidjefts/Desktop/School/Hackathon/ERAU2019/HackRiddle/Test Pictures/'
print(parse(base_path + "star_front.png", base_path + "star_back0.png"))
