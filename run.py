from flask import Flask, render_template, request
import pickle

app = Flask(__name__)

input = []
inputs = []

@app.route('/', methods=['GET', 'POST'])
@app.route('/index')
def home():
    inputs.clear()
    input.clear()
    return render_template('home.html')

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
        ind = request.form.get("Ind")
        ing = request.form.get("Ing")
        mat = request.form.get("Mat")
        kim = request.form.get("Kim")
        fis = request.form.get("Fis")
        input.append(ind)
        input.append(ing)
        input.append(mat)
        input.append(kim)
        input.append(fis)
        print(input)
    return render_template('page1.html')

@app.route('/page2', methods=['GET', 'POST'])
def page2():
    if request.method == 'POST':
        bio = request.form.get("Bio")
        eko = request.form.get("Eko")
        geo = request.form.get("Geo")
        sos = request.form.get("Sos")
        input.append(bio)
        input.append(eko)
        input.append(geo)
        input.append(sos)
        print(input)
    return render_template('page2.html')

@app.route('/result', methods=['GET', 'POST'])
def result():
    model = pickle.load(open('./Machine Learning/finalized_model.sav', 'rb'))
        # user_input = request.form.get('MTK')
        # print(user_input, type(user_input))
        # user_input = float(user_input)
    print("inputted list:")
    print(input)
    inputs = [float(i) for i in input]
    result = model.predict([inputs])
    print("inputs were")
    print(inputs)
    print(result)
    return render_template('result.html', result=result)

if __name__ == '__main__':
    app.run(debug=True)