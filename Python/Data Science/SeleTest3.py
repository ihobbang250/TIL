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


def text_deco(text):
    print("--------------------------------")
    print(text)
    print("--------------------------------")
    
    
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

user_cart = {}
# cpu category
cpu = find_visible(category_css["CPU"])
cpu.click()
time.sleep(1)

# cpu company
options = find_visibles("input[name=makerCode]+span")
text_deco("CPU 제조사 번호를 선택")
for idx, o in enumerate(options):
    print(f"{idx+1}. {o.text}")
choice = int(input("-> "))
options[choice-1].click()
time.sleep(1)

# cpu list
products = find_visibles("div.scroll_box tr[class^=productList]")
products_list = []
text_deco("CPU를 선택")
for idx, p in enumerate(products):
    name = p.find_element(By.CSS_SELECTOR, "p.subject a").text
    name = re.sub(r"\(.* ?\)", "", name)
    spec = p.find_element(By.CSS_SELECTOR, "a.spec").text
    socket = spec.split("/")[0]
    if name.find("AMD") != -1:
        core = int(re.findall(r'\d+', spec.split("/")[3])[0])
        thread = int(re.findall(r'\d+', spec.split("/")[4])[0])
        clock = float(re.findall(r'\d.\d', spec.split("/")[5].split(":")[1])[0])
        performance = int(core * thread * clock)
    try:
        price = p.find_element(By.CSS_SELECTOR, "span.prod_price").text
        price = int(re.sub(r'[^0-9]', "", price))
    except:
        continue
    products_list.append([name, socket, price])
    print(f"{idx+1}. {name}/ {socket} / {price} / {performance}")
choice = int(input("-> "))
selected_cpu = products_list[choice-1]
selected_socket = selected_cpu[1]

# mainboard category
mainboard = find_visible(category_css["Mainboard"])
mainboard.click()
time.sleep(1)

# mainboard filter socket
options = find_visibles("div.search_option_item") # socket row
options[2].find_elements(By.CSS_SELECTOR, "button")[0].click() # more button
options = find_visibles("div[class$=open] span.item_text")
for s in options:
    if s.text.strip() == selected_socket:
        s.click()
        break
time.sleep(1)

# mainboard list
products = find_visibles("div.scroll_box tr[class^=productList]")
mainboards_list = []
for idx, p in enumerate(products):
    name = p.find_element(By.CSS_SELECTOR, "p.subject a").text
    try:
        price = p.find_element(By.CSS_SELECTOR, "span.prod_price").text
        price = int(re.sub(r'[^0-9]', "", price))
    except:
        price = None
    mainboards_list.append([name, price])
    if len(mainboards_list) > 5:
        break
print(mainboards_list)
             
# time.sleep(1)

browser.quit()