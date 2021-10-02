import requests

def getArticleApi():

    url = "http://localhost:8080/article"

    response = requests.get(url)
    articleDto = response.json();

    if articleDto["code"] == 1:
       return articleDto["data"]
    else :
        print(articleDto["msg"])