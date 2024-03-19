from flask import Flask, request, jsonify
from flask_cors import CORS
from chatbot import Chatbot, Chatbot2, HobbyRec, suggest_hobby
import pandas as pd
import requests

app = Flask(__name__)
CORS(app)
app.secret_key = 'secret_key'

# Initialize the chatbot
hobby_rec = HobbyRec()
chatbot = Chatbot()
chatbot2 = Chatbot2()

# Load data for the chatbot
df = pd.read_csv("/Users/hyejinpark/IdeaProjects/HobHub-Back/flask_app/hobby3.csv", encoding='cp949')
existing_data = df.to_dict(orient="records")

def call_spring_boot_api(payload):
    response = requests.post('http://localhost:8080/api/process-chat', json=payload)
    return response.json()

@app.route('/')
def home():
    return "Welcome to the Hobby Chatbot API!"

@app.route('/init-chat', methods=['POST'])
def init_chat():
    user_message = request.json.get('message', '').lower()
    if user_message == 'yes':
        response = {"message": "Please enter your existing hobbies.", "next_step": "recommend_based_on_existing"}
    elif user_message == 'no':
        response = {"message": chatbot2.questions[0], "next_step": "collect_details_for_recommendation"}  # Using the first question from Chatbot2 as an example.
    else:
        response = {"message": "I didn't understand that. Do you have any existing hobbies? (Yes/No)", "next_step": "init_chat"}
    return jsonify(response)



@app.route('/recommend-based-on-existing', methods=['POST'])
def recommend_based_on_existing():
    hobbies_input = request.json.get('hobbies', [])
    target_user_hobbies = hobby_rec.get_user_input(hobbies_input)
    recommended_hobbies, highest_similarity = hobby_rec.recommend_hobbies(target_user_hobbies)
    return jsonify({"recommended_hobbies": recommended_hobbies, "highest_similarity": highest_similarity})


@app.route('/collect-details-for-recommendation', methods=['POST'])
def collect_details_for_recommendation():
    user_input = request.json
    suggested_hobby = suggest_hobby(user_input, existing_data)  # Assuming suggest_hobby function is properly implemented to handle this.
    return jsonify({"suggested_hobby": suggested_hobby})


if __name__ == '__main__':
    app.run(debug=True)
