from selenium import webdriver
import time
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager

options = webdriver.ChromeOptions()
options.add_experimental_option('excludeSwitches', ['enable-logging']) # Usb Error ignore
options.add_argument("no-sandbox") 

browser = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=options)
browser.get("https://shopping.naver.com")

# Depend on loading
# Until Method -> return element
wait = WebDriverWait(browser, 10)

el1 = wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, "fieldset input[type=text]")))
el2 = browser.find_element(By.CSS_SELECTOR, "fieldset input[type=text]")
print(el1 == el2)

# Until Method Develop
def find(wait, css_selector):
    return wait.until(EC.presence_of_element_located((By.CSS_SELECTOR, css_selector)))

search = find(wait, "fieldset input[type=text]")
search.send_keys("갤럭시Z플립 케이스")
time.sleep(3)

button = find(wait, "button[type=button] path")
button.click()

time.sleep(3)

browser.close()