from flask import Flask, render_template, request, jsonify
import pickle
import json
import re

app = Flask(__name__)

# Declare empty list for user input
input = []
inputs = []

@app.route('/', methods=['GET', 'POST'])
@app.route('/index')
def home():
    # Clears all input, redirect to home.html template
    inputs.clear()
    input.clear()
    return render_template('home.html')

@app.route('/page1', methods=['GET', 'POST'])
def page1():
    # Clears all input
    inputs.clear()
    input.clear()

    # Take on the user input and append them to 'input' list using POST method
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

        # Validate the user input
        print(input)
    return render_template('page1.html')

@app.route('/page2', methods=['GET', 'POST'])
def page2():
    # # Take on the user input and append them to 'input' list using POST method
    if request.method == 'POST':
        bio = request.form.get("Bio")
        input.append(bio)
        eko = request.form.get("Eko")
        input.append(eko)
        geo = request.form.get("Geo")
        input.append(geo)
        sos = request.form.get("Sos")
        input.append(sos)

        # Validate the user input
        print(input)
    return render_template('page2.html')

@app.route('/result', methods=['GET', 'POST'])
def result():
    # Load the machine learning model
    model = pickle.load(open('./Machine Learning/finalized_model2.sav', 'rb'))
    
    # Check the user input
    print("inputted list:")
    print(input)
    
    try:
        # Convert to float and append the data in 'input' list to 'inputs' list
        inputs = [float(i) for i in input]
        # Predict the result according to data in 'inputs' list
        results = model.predict([inputs])
    except Exception as e:
        return e

    # Check data in 'inputs' list
    print("inputs were")
    print(inputs)
    
    # Change result into bytes/string type 
    result2 = results.tobytes()

    # Decode the bytes into UTF-8 standard
    result = result2.decode('utf8')
    
    # Return the result
    return render_template('result.html', result=result)

@app.route('/api', methods=['GET', 'POST'])
def api():
    # Load machine learning model
    # '''
    model = pickle.load(open('./Machine Learning/finalized_model2.sav', 'rb'))
    
    # Declare empty dict
    my_dict = {}

    # if request.method == "POST":
    if request.method == "GET":
        ind = request.args.get("Ind")
        input.append(ind)
        ing = request.args.get("Ing")
        input.append(ing)
        mat = request.args.get("Mat")
        input.append(mat)
        kim = request.args.get("Kim")
        input.append(kim)
        fis = request.args.get("Fis")
        input.append(fis)
        bio = request.args.get("Bio")
        input.append(bio)
        eko = request.args.get("Eko")
        input.append(eko)
        geo = request.args.get("Geo")
        input.append(geo)
        sos = request.args.get("Sos")
        input.append(sos)

    print(input)
    try:
        inputs = [float(i) for i in input]
        results = model.predict([inputs])
    except Exception as e:
        return e

    # Validate inputs
    print(inputs)

    # Change the result type to string/bytes
    result2 = results.tostring()

    # Validate the result
    print(type(result2))

    # Decode the result into UTF-8 format
    result = result2.decode('utf8')

    # Validate Results
    print(result)
    
    # Clear unicode NULL (\u0000) on the result
    final = re.sub(u'\u0000', "", result)

    # Validate final result and the type
    print(final)
    print(type(final))

    # Declare dict for ease output
    my_dict['jurusan'] = final

    # Append university choice according to the result
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
    # '''
    # Return dict into json type
    return jsonify(my_dict)

if __name__ == '__main__':
    app.run(host='0.0.0.0', debug=True)
    # app.run(debug=True)
