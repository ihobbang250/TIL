from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time
import os

options = webdriver.ChromeOptions()
options.add_experimental_option('excludeSwitches', ['enable-logging']) # Usb Error ignore
options.add_argument("no-sandbox") 

browser = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
browser.get("https://shop.danawa.com/virtualestimate/?controller=estimateMain&methods=index&marketPlaceSeq=16")

wait = WebDriverWait(browser, 10)

def find_present(css):
    return wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, css)))

def finds_present(css): # Find all include selector 
    find_present(css)
    return browser.find_elements(By.CSS_SELECTOR, css)


def find_visible(css):
    return wait.until(EC.visibility_of_element_located((By.CSS_SELECTOR, css)))


def finds_visible(css): # Find all include selector 
    find_visible(css)
    return browser.find_elements(By.CSS_SELECTOR, css)


def choose_one(text, options):
    print("--------------------------------")
    print(text)
    print("--------------------------------")
    for i in range(len(options)):
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
options = finds_visible("input[name=makerCode]+span")
i = choose_one("CPU 제조사 번호를 선택", options)

# cpu version
options = finds_visible("div.search_option_item")
if i == "인텔": 
    options[1].find_elements(By.CSS_SELECTOR, "button")[0].click()
else:
    options[2].find_elements(By.CSS_SELECTOR, "button")[0].click()

options = finds_visible("div[class$=open] span.item_text")
choose_one("CPU 종류를 선택", options)
time.sleep(2)

# cpu list
products = finds_visible("div.scroll_box tr[class^=productList]")

for idx, p in enumerate(products):
    name = p.find_element(By.CSS_SELECTOR, "p.subject a").text
    try:
        price = p.find_element(By.CSS_SELECTOR, "span.prod_price").text
    except:
        print("재고없음")
        continue
    print(f"{idx+1}. {name}, -----{price}")

# mainboard = find_visible(category_css["Mainboard"])
# mainboard.click()

time.sleep(5)

browser.quit()