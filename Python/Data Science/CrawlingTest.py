import requests as req
from bs4 import BeautifulSoup as BS
from user_agent import generate_user_agent

url = "https://www.coupang.com/np/search?component=&q=%EB%85%B8%ED%8A%B8%EB%B6%81&channel=user"

headers = {
    "authority": "www.coupang.com",
    "method": "GET",
    "accept": "*/*",
    "accept-encoding": "gzip, deflate, br",
    "user-agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.104 Whale/3.13.131.36 Safari/537.36",
    "sec-ch-ua-platform": "macOS",
    "cookie": "PCID=31489593180081104183684; _fbp=fb.1.1644931520418.1544640325; gd1=Y; X-CP-PT-locale=ko_KR; MARKETID=31489593180081104183684; sid=03ae1c0ed61946c19e760cf1a3d9317d808aca8b; overrideAbTestGroup=%5B%5D; x-coupang-origin-region=KOREA; bm_sz=8E2A56FCE71E2F5E0425882E9918783A~YAAQbnXTFya5WQmDAQAAbxYyQBHoLAvdgSsflW3UAreWyTlE5TPF9oAQVQolYR1LMUNXF83P5Q/1cfN5adrALT6AtwrzMAqMZfWFh9VbeHC3N6X+5BNZQyF5JXzJ79JtV0s/jcb9vp4xlM33h2a4AnGivx5dkoXYLZWgRYW3l7zYoFcN+L/Y+u+rNdTnw/sitpwOeHJb47ScvJhoSplUMKPcn04m834uRfaM4aJ7npkz43iFHzdh2hvpLqJYsXL65lz6H0AizZh3KFJ9cqHvQvyGVBJLCN7iV+cd1/Sq+0h6Uxqs~4404550~3688004; ak_bmsc=3BE3E467EBB08DC7F9178C22E0B573A2~000000000000000000000000000000~YAAQbnXTFye7WQmDAQAAGB0yQBG3WTdG026dSCjMp3DY1NxD3GLgwTd+BBT7dZPi5dQ2q/5GdBDNXua78PogcA1k3V1hwQQ3g6YyWVSFZROKXzv7eFUglTDnbQDZnjqaUV1lHpzgrKDsKVhazLqBfnl23fCUtg23TU70WwHYiJ950KLsNibyD9KBODxc+sVoyrHWaKHmqgWtm5z0U1J/ue977IBtaY5XDzWSCe7xABSLMSkEyqzQUfWpfBCYDuMS0QUdhl74edJYm32bceUAlAKf8jDUpM0/i1A8I1hvsXWA1WAwgV1pshaVodYl9fCRSVbELXaaZvpy9x+jzcs9Id+A9rMALhtrfoHYpw/e6Zd+rxz7vpvu7loKtzOI7zuCx2L5hAia3N60t7HibJW6YceAP0B9yvRi8By9a3221uQOFJ5FnqS/m8AUI4dvS5pzdfIvhfT7t0ozAIwR4Ys5evF9SoVpRNFpCqzKC9dZvcQINqOmwclrIJYzpSHy; x-coupang-target-market=KR; ILOGIN=Y; CSID=3ukY3FWIMxUuDVFK3iQtHPUwl1C7ju6ySKdGHEr7Y-gsHcy1o8qrJgpXGNxlkCrkvKu_05aLzV3s5iQuxpKC4EM4HiD-Enq1BJyKExMEZpN3; CUPT=i1qMGFhO4kuJ-AvEi_e_H5fsMBasC0LffngIvXitbgzThdPek3VJXOq92c3L97N2SEBUt3tEj3roEIuTIGvGhepJPHXgr8T5tjIqcFr3COevDcLx5MBiYDP2QXBZGfFJJztaDJN7rcjih7vpqh4jHA1Rq0ytViy7s-qitX6o6xuUSyTMe4K3mYnxsCyUg_oErkJrdpb1Va4N1TAEAYNiYetZsGY1ppAMPHwdzbh_1Vdjuzx4faiQbHA; member_srl=127795010; searchSoterTooltip=OFF; FUN=\"{'search':[{'reqUrl':'/search.pang','isValid':true}]}\"; searchKeyword=%EB%8B%A8%EB%B0%B1%EC%A7%88%20%EC%BF%A0%ED%82%A4%7C%EB%9E%A9%EB%85%B8%EC%89%AC; searchKeywordType=%7B%22%EB%8B%A8%EB%B0%B1%EC%A7%88%20%EC%BF%A0%ED%82%A4%22%3A0%7D%7C%7B%22%EB%9E%A9%EB%85%B8%EC%89%AC%22%3A0%7D; x-coupang-accept-language=ko_KR;"
  }

#header = generate_user_agent(os=('win', 'mac'), device_type='desktop')
res = req.get(url,headers=headers)
soup = BS(res.text, "html.parser")

for desc in soup.select("div.descriptions-inner"):
    ads = desc.select("span.ad-badge")
    if len(ads) == 0:
        print("광고")
    else:    
        print(desc.select("div.name")[0].get_text(strip=True))