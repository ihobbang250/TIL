from selenium import webdriver
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

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
# Solution 1 -> input Enter
search.send_keys("갤럭시Z플립 케이스\n")
time.sleep(3)

# Solution 2 -> click
# button = find(wait, "button[type=button] path")
# button.click()
# time.sleep(3)

browser.close()