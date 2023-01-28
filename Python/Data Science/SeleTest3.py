from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
import re

options = webdriver.ChromeOptions() 
options.add_experimental_option('excludeSwitches', ['enable-logging']) # Usb Error ignore
options.add_argument("no-sandbox") 

browser = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
browser.get("https://shop.danawa.com/virtualestimate/?controller=estimateMain&methods=index&marketPlaceSeq=16")

wait = WebDriverWait(browser, 10)

def find_present(css):
    return wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, css)))

def find_presents(css): # Find all include selector 
    find_present(css)
    return browser.find_elements(By.CSS_SELECTOR, css)


def find_visible(css):
    return wait.until(EC.visibility_of_element_located((By.CSS_SELECTOR, css)))


def find_visibles(css): # Find all include selector 
    find_visible(css)
    return browser.find_elements(By.CSS_SELECTOR, css)


def choose_one(text, options):
    print("--------------------------------")
    print(text)
    print("--------------------------------")
    for i in range(len(options)):
        if i == 10:
            break
        print(f"{i+1}. {options[i].text}")
    choice = int(input("-> "))
    options[choice-1].click()
    return options[choice-1].text
    
category = {
    "CPU": "873",
    "Cooler": "887",
    "Mainboard": "875",
    "Memory": "874",
    "GPU": "876",
    "SSD": "326617",
    "Case": "879",
    "Power": "880"
}

category_css = {
    c: "dd.category_" + category[c] + " a" for c in category
}

# cpu category
cpu = find_visible(category_css["CPU"])
cpu.click()
time.sleep(1)

# cpu company
options = find_visibles("input[name=makerCode]+span")
maker = choose_one("CPU 제조사 번호를 선택", options)
time.sleep(1)

# cpu version
# options = find_visibles("div.search_option_item")
# if maker == "인텔": 
#     options[1].find_elements(By.CSS_SELECTOR, "button")[0].click()
# else: #AMD
#     options[2].find_elements(By.CSS_SELECTOR, "button")[0].click()

# options = find_visibles("div[class$=open] span.item_text")
# choose_one("CPU 종류를 선택", options)

# cpu list
products = find_visibles("div.scroll_box tr[class^=productList]")
products_list = []
for idx, p in enumerate(products):
    name = p.find_element(By.CSS_SELECTOR, "p.subject a").text
    name = re.sub(r"\(.* ?\)", "", name)
    socket = p.find_element(By.CSS_SELECTOR, "a.spec").text.split("/")[0]
    try:
        price = p.find_element(By.CSS_SELECTOR, "span.prod_price").text
    except:
        price = None
    products_list.append([name, price])
    print(f"{idx+1}. {name}/ {socket} / {price}")

# # cpu socket
# spec = find_visible("div.scroll_box div.spec_bg a").text
# socket = spec.split("/")[0]


# performance = spec.split("/")[-2].split(":")[1]
# performance_number = int(re.sub(r'[^0-9]', "", performance))

# print(socket, performance_number)
# # mainboard = find_visible(category_css["Mainboard"])
# mainboard.click()

time.sleep(5)

browser.quit()