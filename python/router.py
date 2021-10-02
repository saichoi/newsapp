from flask import Flask
from flask.templating import render_template
import article_api as aa

app = Flask(__name__)

@app.route("/")
def index():
    return render_template("index.html", articles=aa.getArticleApi()) 

if __name__ == "__main__":
    app.run(debug=True, port=5000) 

