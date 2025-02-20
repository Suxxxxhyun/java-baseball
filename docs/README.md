## 흐름에 따른 수행 기능 이해

1. 게임 시작
    1) 서로 다른 3자리의 난수 생성를 생성한다


2. 플레이어에게 입력 받는다 → 정답을 맞출 때까지 반복
    1. 사용자가 입력한 숫자가 유효한 숫자인지 확인
        1) 세 자리인지 확인
        2) 숫자인지 확인
        3) 서로 다른 숫자인지 확인
    2. 정답인지 확인
        1) 난수와 비교하여 정답인지 확인한다


3. 정답이 아니라면 플레이어에게 힌트를 제공한다
    - 같은 수 같은 자리 : 스트라이크
    - 같은 수 다른 자리 : 볼
    - 같은 수 없음 : 낫싱 or 포볼
    - 모두 맞음 : 3개의 숫자를 모두 맞히셨습니다!


4. 게임이 끝나면 재시작 혹은 종료를 묻는다
    - 사용자가 1 입력 : 게임 재시작
    - 사용자가 2 입력 : 게임 완전히 종료

<br>

---
## MVC 기반의 기능 분류
### model

1. GameNumber
- [x]  computerNumbers 데이터 저장
- [x]  inputString 데이터 저장 (문자열 형태)
    - [x]  띄어쓰기를 제거한 뒤 저장하기
- [x]  playerNumbers 데이터 저장 (inputStrings 숫자로 변환)
    - [x]  데이터 유효성 검사기 체크
    - [x]  inputString 데이터 숫자로 변환

### view

1. OutputView
- [x]  숫자 야구 게임 시작 알림 메시지 출력
- [x]  힌트 메시지 출력
- [x]  게임 종료 알림 메시지 출력

1. InputView
- [x]  플레이어 숫자 입력
- [x]  게임 재시작 답변 입력

### controller
1. GameController
- [x]  게임 진행
    - [x]  게임 시작
    - [x]  정답을 맞출 때까지 반복
    - [x]  게임이 끝나면 재시작/종료 묻기
        - [x]  1 입력 : 게임 재시작
        - [x]  2 입력 : 게임 종료
        - [x]  1, 2 외의 값 입력 시 오류 메시지 전달 (IllegalArgumentException 발생 후 종료)


2. ComputerController
- [x]  게임 시작
    - [x]  스트라이크, 볼 카운터 변수 0으로 초기화
    - [x]  난수 생성 후 모델에 저장
    - [x]  게임 시작 사용자에게 알리기
- [x]  세자리의 난수와 플레이어 입력 숫자 비교
- [x]  힌트 결정
    - [x]  같은 수 같은 자리 : 스트라이크
    - [x]  같은 수 다른 자리 : 볼
    - [x]  같은 수 없음 : 낫싱 or 포볼
    - [x]  모두 맞음 : 3개의 숫자를 모두 맞히셨습니다!


3. NumberValidator
- [x]  inputNumber 유효성 검사 및 숫자 변환
    - [x]  각 경우에 따른 오류 메시지 전달 (IllegalArgumentException 발생 후 종료)
    - [x]  세 자리인지 확인
    - [x]  1~9 사이의 숫자 값인지 확인
    - [x]  서로 다른 숫자인지 확인
    - [x]  숫자로 변환해주기


4. RandomUtility
- [x]  난수 생성
    - [x]  1~9 사이의 세자리의 난수 생성
    - [x]  서로 다른 수의 난수로 생성