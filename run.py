from flask import Flask, render_template, request, jsonify
import pickle
import json
import re

app = Flask(__name__)

input = []
inputs = []

@app.route('/', methods=['GET', 'POST'])
@app.route('/index')
def home():
    inputs.clear()
    input.clear()
    return render_template('home.html')

@app.route('/page1', methods=['GET', 'POST'])
def page1():
    inputs.clear()
    input.clear()
    if request.method == 'POST':
        ind = request.form.get("Ind")
        input.append(ind)
        ing = request.form.get("Ing")
        input.append(ing)
        mat = request.form.get("Mat")
        input.append(mat)
        kim = request.form.get("Kim")
        input.append(kim)
        fis = request.form.get("Fis")
        input.append(fis)
        print(input)
    return render_template('page1.html')

@app.route('/page2', methods=['GET', 'POST'])
def page2():
    if request.method == 'POST':
        bio = request.form.get("Bio")
        input.append(bio)
        eko = request.form.get("Eko")
        input.append(eko)
        geo = request.form.get("Geo")
        input.append(geo)
        sos = request.form.get("Sos")
        input.append(sos)
        print(input)
    return render_template('page2.html')

@app.route('/result', methods=['GET', 'POST'])
def result():
    model = pickle.load(open('./Machine Learning/finalized_model2.sav', 'rb'))
    print("inputted list:")
    print(input)
    blacklist = "[']"
    try:
        inputs = [float(i) for i in input]
        results = model.predict([inputs])
    except Exception as e:
        return e
    print("inputs were")
    print(inputs)
    # print(type(results))
    result2 = results.tobytes()
    # print(type(result2))
    result = result2.decode('utf8')
    # result.replace(blacklist, '')
    return render_template('result.html', result=result)

@app.route('/result2', methods=['GET', 'POST'])
def result2():
    model = pickle.load(open('./Machine Learning/finalized_model2.sav', 'rb'))
    blacklist = "[']"
    try:
        inputs = [float(i) for i in input]
        results = model.predict([inputs])
    except Exception as e:
        return e
    print(inputs)
    result2 = results.tostring()
    print(type(result2))
    result = result2.decode('utf8')
    return render_template('result2.html', result=result)

@app.route('/api', methods=['GET', 'POST'])
def api():
    model = pickle.load(open('./Machine Learning/finalized_model2.sav', 'rb'))
    my_dict = {}
    try:
        inputs = [float(i) for i in input]
        results = model.predict([inputs])
    except Exception as e:
        return e
    print(inputs)
    result2 = results.tostring()
    print(type(result2))
    result = result2.decode('utf8')
    print(result)
    # final = result.replace(" ","")
    final = re.sub(u'\u0000', "", result)
    print(final)
    print(type(final))
    my_dict['jurusan'] = final

    if final == "Komunikasi":
        my_dict['Universitas'] = ["Universitas Telkom", "Universitas Gadjah Mada", "Universitas Indonesia", "Universitas Airlangga", "Universitas Negeri Yogyakarta"]
    elif final == "Akuntansi":
        my_dict['Universitas'] = ["Universitas Diponegoro", "Universitas Udayana", "Universitas Indonesia", "Universitas Sebelas Maret", "Universitas Gadjah Mada"]
    elif final == "Hukum":
        my_dict['Universitas'] = ["Universitas Indonesia", "Universitas Brawijaya", "Universitas Diponegoro", "Universitas Gadjah Mada", "Universitas Padjajaran"]
    elif final == "Teknik Informatika / Ilmu Komputer":
        my_dict['Universitas'] = ["Institut Teknologi Bandung", "Institut Teknologi Sepuluh Nopember", "Universitas Telkom", "Universitas Bina Nusantara", "Universitas Udayana"]
    elif final == "Kedokteran Umum":
        my_dict['Universitas'] = ["Universitas Indonesia", "Universitas Gadjah Mada", "Universitas Airlangga", "Universitas Padjajaran", "Universitas Brawijaya"]
    print(type(my_dict))
    return jsonify(my_dict)

if __name__ == '__main__':
    # app.run(host='0.0.0.0', debug=True)
    app.run(debug=True)
