//package MoneyCatcher;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Scanner;
//
//public class chatTest {
//    private ArrayList<String> questions;
//    private ArrayList<String> userResponses;
//
//    public chatTest() {
//        questions = new ArrayList<>();
//        questions.add("안녕하세요! 오늘 기분은 어떠세요?");
//        questions.add("오늘 무엇을 하셨나요?");
//        questions.add("그럼, 오늘 하루 어떻게 마무리 하실 건가요?");
//        userResponses = new ArrayList<>();
//    }
//
//    public void chat() {
//        System.out.println("챗봇: 안녕하세요! 챗봇입니다.");
//        Scanner scanner = new Scanner(System.in);
//        for (String question : questions) {
//            System.out.print("사용자: " + question + " ");
//            String userInput = scanner.nextLine();
//            userResponses.add(userInput);
//        }
//        scanner.close();
//        System.out.println("챗봇: 대화가 종료되었습니다.");
//        System.out.println("사용자의 답변: " + userResponses);
//    }
//
//    public boolean checkKeyword(String keyword) {
//        String happy = "행복";
//        for (String response : userResponses) {
//            if (response.contains(keyword)) {
//                System.out.println("사용자의 답변 중 '" + keyword + "' 키워드를 포함한 답변이 있습니다.");
//                if (happy.equals(keyword)) {
//                    // "행복" 키워드가 포함되면 추가 행동으로 "요가" 키워드를 추가합니다.
//                    keyword2.add("요가");
//                }
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public static void main(String[] args) {
//        chatTest chatbot = new chatTest();
//        chatbot.chat();
//
//        // 특정 키워드 확인
//        String[] keywords = {"즐거운", "행복", "힘들", "피곤"};
//        for (String keyword : keywords) {
//            chatbot.checkKeyword(keyword);
//        }
//
//        Scanner scanner = new Scanner(System.in);
//
//        // 사용자 입력 받기
//        Map<String, Object> user_input = new HashMap<>();
//        System.out.print("성별을 입력하세요 (여자/남자): ");
//        user_input.put("gender", scanner.nextLine());
//        System.out.print("나이를 입력하세요: ");
//        user_input.put("age", Integer.parseInt(scanner.nextLine()));
//        System.out.print("사는곳을 입력하세요 (서울특별시/경기/충청북도/부산광역시 등): ");
//        user_input.put("home", scanner.nextLine());
//        System.out.print("소득을 입력하세요(300만원 미만/없음 등): ");
//        user_input.put("income", scanner.nextLine());
//        System.out.print("취미를 하는 동기를 입력하세요: ");
//        user_input.put("motivation", scanner.nextLine());
//        System.out.print("주중 취미시간을 입력하세요: ");
//        user_input.put("work", Integer.parseInt(scanner.nextLine()));
//        System.out.print("휴일 취미시간을 입력하세요: ");
//        user_input.put("wkend", Integer.parseInt(scanner.nextLine()));
//
//        // 취미 제안 받기
//        String suggested_hobby = suggestHobby(user_input);
//        System.out.println("추천 취미: " + suggested_hobby);
//
//    }
//    public static ArrayList<String> keyword2 = new ArrayList<>();
//
//
//    // "행복" 키워드가 포함되면 추가 행동으로 "요가" 키워드를 저장하기 위한 리스트
//    public static String suggestHobby(Map<String, Object> user_input) {
//        // 엑셀 파일 읽기 대신 가정: 이미 존재하는 데이터
//        ArrayList<Map<String, Object>> existing_data = new ArrayList<>();
//        // 기존 데이터에 대한 가정
//        Map<String, Object> data_point1 = new HashMap<>();
//        data_point1.put("gender", "여자");
//        data_point1.put("age", 30);
//        data_point1.put("home", "서울특별시");
//        data_point1.put("income", "300만원 미만");
//        data_point1.put("motivation", "스트레스 해소");
//        data_point1.put("workday", 2);
//        data_point1.put("wkendday", 5);
//        data_point1.put("hobby", "요가");
//        existing_data.add(data_point1);
//
//        // 사용자 입력과 가장 유사한 조건 찾기
//        double min_distance = Double.POSITIVE_INFINITY;
//        Map<String, Object> closest_data_point = null;
//
//        for (Map<String, Object> data_point : existing_data) {
//            double distance = calculateSimilarity(user_input, data_point);
//            if (distance < min_distance) {
//                min_distance = distance;
//                closest_data_point = data_point;
//            }
//        }
//
//        // 최대 유사도
//        double max_similarity = Math.sqrt(1 + 1 + 1 + 1 + 1);
//
//        // 유사도를 퍼센트로 계산
//        double similarity_percent = Math.sqrt(Math.pow(((max_similarity - min_distance) / max_similarity), 2)) * 100;
//
//        // 가장 유사한 조건에 따른 취미 제안
//        String suggested_hobby = (String) closest_data_point.get("hobby");
//        System.out.printf("유사도: %.2f%%\n", similarity_percent);
//
//        return suggested_hobby;
//    }
//
//    public static double calculateSimilarity(Map<String, Object> user_input, Map<String, Object> existing_data_point) {
//        // 각 속성의 차이를 계산하여 유클리디안 거리 측정
//        int gender_diff = user_input.get("gender").equals(existing_data_point.get("gender")) ? 0 : 1;
//        double age_diff = Math.pow((Integer) user_input.get("age") - (Integer) existing_data_point.get("age"), 2);
//        int home_diff = user_input.get("home").equals(existing_data_point.get("home")) ? 0 : 1;
//        int income_diff = user_input.get("income").equals(existing_data_point.get("income")) ? 0 : 1;
//        int motivation_diff = user_input.get("motivation").equals(existing_data_point.get("motivation")) ? 0 : 1;
//        double work_diff = Math.pow((Integer) user_input.get("work") - (Integer) existing_data_point.get("workday"), 2);
//        double wkend_diff = Math.pow((Integer) user_input.get("wkend") - (Integer) existing_data_point.get("wkendday"), 2);
//
//        double distance = Math.sqrt(age_diff + gender_diff + home_diff + motivation_diff + work_diff + wkend_diff);
//        return distance;
//    }
//
//}
