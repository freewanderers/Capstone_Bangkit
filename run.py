from flask import Flask, render_template, request
import pickle

app = Flask(__name__)

@app.route('/', methods=['GET', 'POST'])
@app.route('/index')


#not done yet
def home():
    if request.method == 'POST':
        model = pickle.load(open('./Machine Learning/finalized_model.sav', 'rb'))
        user_input = request.form.get('MTK')
        print(user_input, type(user_input))
        user_input = float(user_input)
        prediction = model.predict([[user_input]])
        print(prediction)
    return render_template('index.html')

if __name__ == '__main__':
    app.run(debug=True)