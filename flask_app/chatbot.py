import numpy as np
import pandas as pd
from sklearn.metrics.pairwise import cosine_similarity


class HobbyRec:
    def __init__(self):
        self.excel_file_path = '/Users/hyejinpark/IdeaProjects/HobHub-Back/flask_app/merged_hobbies_ex.xlsx'
        self.df = pd.read_excel(self.excel_file_path)

    def get_user_input(self, selected_hobbies=None):
        target_user_hobbies = {hobby: 0 for hobby in self.df.columns[1:]}  # Initialize all hobbies to 0

        if selected_hobbies is None:
            print("기존의 취미를 입력하세요:")
            selected_hobbies = input().split(',')

        for hobby in selected_hobbies:
            hobby = hobby.strip()
            if hobby in target_user_hobbies:
                target_user_hobbies[hobby] = 1  # Set the value to 1 for selected hobbies

        return pd.DataFrame([target_user_hobbies])


    def get_similar_users(self, target_user_hobbies):
        users_similarity = cosine_similarity(target_user_hobbies, self.df.iloc[:, 1:].values)
        sim_df = pd.DataFrame({'닉네임': self.df.iloc[:, 0], 'similarity': users_similarity.flatten()})
        sim_df = sim_df.sort_values(by='similarity', ascending=False)
        return sim_df

    def recommend_hobbies(self, target_user_hobbies, num_recommendations=3):
        similar_users_df = self.get_similar_users(target_user_hobbies)
        recommended_hobbies = {}
        highest_similarity = 0

        for index, row in similar_users_df.iterrows():
            similar_username = row['닉네임']
            similarity_score = row['similarity']
            if index == 0:  # The first row will have the highest similarity
                highest_similarity = similarity_score
            similar_user_hobbies = self.df[self.df['닉네임'] == similar_username].iloc[:, 1:]

            for hobby in similar_user_hobbies.columns:
                if target_user_hobbies.iloc[0][hobby] == 0:  # Check if the user does not have this hobby
                    if hobby not in recommended_hobbies:
                        recommended_hobbies[hobby] = 0
                    recommended_hobbies[hobby] += similarity_score * similar_user_hobbies[hobby].values[0]

        recommended_hobbies = dict(sorted(recommended_hobbies.items(), key=lambda item: item[1], reverse=True))
        return list(recommended_hobbies.keys())[:num_recommendations], highest_similarity

class Chatbot:
    def __init__(self):
        pass

    def chat(self, user_inputs):
        print("안녕하세요! 챗봇입니다")


class Chatbot2:
    def __init__(self):
        self.questions = ["취미생활을 하는 목적은 무엇인가요?"]

    def chat2(self, user_inputs):
        responses = []
        for question in self.questions:
            print(question)
            response = input()
            responses.append(response)
        return ' '.join(responses)


def calculate_similarity(user_input, existing_data_point):
    gender_diff = 0 if user_input["gender"] == existing_data_point["gender"] else 1
    age_diff = (user_input["age"] - existing_data_point["age"]) ** 2
    home_diff = 0 if user_input["home"] == existing_data_point["home"] else 1
    income_diff = 0 if user_input["income"] == existing_data_point["income"] else 1
    motivation_diff = 0 if user_input["motivation"] == existing_data_point["motivation"] else 1
    work_diff = (user_input["work"] - existing_data_point["workday"]) ** 2
    wkend_diff = (user_input["wkend"] - existing_data_point["wkendday"]) ** 2

    distance = np.sqrt(age_diff + gender_diff + home_diff + motivation_diff + work_diff + wkend_diff)
    return distance

def suggest_hobby(user_input, existing_data):
    min_distance = float('inf')
    closest_data_point = None

    for data_point in existing_data:
        distance = calculate_similarity(user_input, data_point)
        if distance < min_distance:
            min_distance = distance
            closest_data_point = data_point

    max_similarity = np.sqrt(1 + 1 + 1 + 1 + 1)
    similarity_percent = np.sqrt(((max_similarity - min_distance) / max_similarity) ** 2) * 100

    suggested_hobby = closest_data_point["hobby"]
    print(f"유사도: {similarity_percent:.2f}%")

    return suggested_hobby


if __name__ == "__main__":
    # Load existing data for hobby recommendations
    excel_file_path = "/Users/hyejinpark/IdeaProjects/HobHub-Back/flask_app/hobby3.csv"
    df = pd.read_csv(excel_file_path, encoding='cp949')
    existing_data = df.to_dict(orient="records")

    # Ask the user if they have existing hobbies
    have_hobbies = input("기존의 취미가 있으신가요? (Y/N): ").strip().lower()
    if have_hobbies == 'yes':
        # Proceed with recommending hobbies based on existing hobbies
        hobby_rec = HobbyRec()  # Make sure to instantiate your HobbyRec class correctly
        target_user_hobbies = hobby_rec.get_user_input()
        similar_users_df = hobby_rec.get_similar_users(target_user_hobbies)
        recommended_hobbies = hobby_rec.recommend_hobbies(target_user_hobbies)
        print(f"당신에게 추천하는 취미 : {recommended_hobbies}")

    elif have_hobbies == 'no':
        chatbot = Chatbot()
        chatbot2 = Chatbot2()

        user_inputs = {}
        # Collect responses for Chatbot2's question (about hobby motivation)
        sentence2 = chatbot2.chat2(user_inputs)  # Assuming chat2 method is correctly modified to use user_inputs

        # Now proceed to collect detailed user information
        user_input = {}

        # Gender
        gender_input = input("성별을 입력하세요 (여자/남자): ")
        while gender_input not in ['여자', '남자']:
            print("잘못된 입력입니다. '여자' 또는 '남자'로 입력해주세요.")
            gender_input = input("성별을 입력하세요 (여자/남자): ")
        user_input["gender"] = gender_input

        # Age
        while True:
            try:
                age_input = int(input("나이를 입력하세요: "))
                user_input["age"] = age_input
                break
            except ValueError:
                print("잘못된 입력입니다. 숫자를 입력해주세요.")

        # Location
        home_input = input("사는곳을 입력하세요(서울특별시/경기도/충청도/부산광역시 등): ")
        while not any(city in home_input for city in ['서울', '경기', '충청', '부산']):
            print("잘못된 입력입니다. 다시 작성해주세요")
            home_input = input("사는곳을 입력하세요(서울특별시/경기도/충청도/부산광역시 등): ")
        user_input["home"] = home_input

        # Income
        user_input["income"] = input("소득을 입력하세요(300만원 미만/없음 등): ")

        # Use the motivation from Chatbot2
        user_input["motivation"] = sentence2

        # Work and weekend hobby time
        user_input["work"] = int(input("주중 취미시간을 입력하세요: "))
        user_input["wkend"] = int(input("휴일 취미시간을 입력하세요: "))

        print("입력된 정보:", user_input)

        suggested_hobby = suggest_hobby(user_input, existing_data)
        print(f"당신에게 추천하는 취미: {suggested_hobby}")
    else:
        print("Please answer 'Yes' or 'No'.")