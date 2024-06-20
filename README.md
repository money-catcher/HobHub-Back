
 <h1>HobHub : AI 기반 맞춤형 취미 추천 및 아카이빙 웹 서비스</h1> 
 사용자가 가진 조건(나이, 성별, 여가시간, 소득, 거주 지역)을 종합적으로 고려해 AI를 기반으로 사용자에게 최적인 취미를 추천함으로써 사용자들이 간편하고 빠르게 취미 활동을 시작할 수 있도록 도와주는 웹 서비스<br/>

![logo](https://github.com/money-catcher/HobHub-Front/assets/109021332/3859f1d4-beb4-42fc-8081-9004cf2b0dd2)

## 팀원
|<img src="https://avatars.githubusercontent.com/u/108976815?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/104544503?v=4" width="150" height="150"/>|<img src="https://avatars.githubusercontent.com/u/109021332?v=4" width="150" height="150"/>|
|:-:|:-:|:-:|
|팀장, 백엔드<br/>[박유진](https://github.com/jin171)|박혜진<br/>[@hyp00](https://github.com/hyp00)|최예<br/>[@beenvyn](https://github.com/beenvyn)|

## 기술스택
![](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)
![](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)<br/>
![](https://img.shields.io/badge/Amazon_AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

## 프로젝트 구조
![image](https://github.com/money-catcher/HobHub-Back/assets/108976815/a07646cc-43e5-4e2b-bb90-b0e77aa46065)

## 폴더별 설명
- `📂Board` : 취미 아카이브 게시물 작성 및 사진 업로드 api
- `📂config` : 설정파일
- `📂File` : 이미지 업로드 api
- `📂Flask` : flask로 데이터 주고받는 폴더
- `📂Hobby` : 취미 저장 및 관리
- `📂KaKao` : 카카오로그인 관리
- `📂Oneday` : 원데이클래스 받아오고 생성하는 폴더
- `📂User` : 사용자 정보 관리하는 폴더
- `📂UserH` : 취미에 대한 별점 관리하는 폴더

## 서버배포
aws ec2, rds를 생성한다.<br/>
사용자 환경에 맞게 ec2 서버에 자바 설치, 깃 설치 한다.<br/>
배포용 폴더를 만들고 깃 pull<br/>
```
git clone https://github.com/money-catcher/HobHub-Back.git
cd 배포용폴더
````
ec2-user에 빌드권한 부여하기
```
sudo chome 777 ./gradlew
````
빌드
```
./gradlew build
````
빌드가 성공하면 jar 파일을 실행한다.
```
java -jar HobHub-0.0.1-snapshot.jar
````


