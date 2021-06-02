from flask import Flask, render_template, request
import pickle

app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
@app.route('/index')
def home():
    return render_template('html/home.html')

    # if request.method == 'POST':
        # model = pickle.load(open('./Machine Learning/finalized_model.sav', 'rb'))
        # user_input = request.form.get('MTK')
        # print(user_input, type(user_input))
        # user_input = float(user_input)
        # prediction = model.predict([[user_input]])
        # print(prediction)

@app.route('/page1', methods=['GET', 'POST'])
def page1():
    if request.method == 'POST':
        print('Done')
    return render_template('page1.html')

@app.route('/page2', methods=['GET', 'POST'])
def page2():
    if request.method == 'POST':
        print("done")
    return render_template('page2.html')

@app.route('/result', methods=['GET', 'POST'])
def result():
    return render_template('result.html')

if __name__ == '__main__':
    app.run(debug=True)