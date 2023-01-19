import requests as req
from bs4 import BeautifulSoup as BS
from user_agent import generate_user_agent, generate_navigator
url = "https://www.coupang.com/np/search?component=&q=%EB%85%B8%ED%8A%B8%EB%B6%81&channel=user"

header = generate_user_agent(device_type='desktop')

res = req.get(url, headers={"User-Agent": header})
soup = BS(res.text, "html.parser")

# list comprehension
arr = soup.select("div.name")
for a in arr:
    print(a.get_text(strip=True))
