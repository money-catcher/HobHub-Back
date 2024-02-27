from flask import Flask, request, jsonify

app = Flask(__name__)

# root URL '/'
@app.route('/')
def index():
    return 'Welcome to HobHub!'

@app.route('/chat', methods=['GET'])
def chat_get():
    # Handle the GET request for the /chat endpoint here
    return 'Hello from Flask!'

@app.route('/chat', methods=['POST'])
def chat_post():
    message = request.json.get('message')

    response = {'response': 'You said: ' + message}

    return jsonify(response)

# 플라스크 실행
if __name__ == '__main__':
    app.run(debug=True)