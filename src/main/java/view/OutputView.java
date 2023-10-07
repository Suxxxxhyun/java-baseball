package view;

/**
 * packageName    : view
 * fileName       : OutputView
 * author         : qkrtn_ulqpbq2
 * date           : 2023-10-06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-06        qkrtn_ulqpbq2       최초 생성
 */
public class OutputView {
    private static final String GAME_START_MESSAGE = "숫자 야구 게임을 시작합니다.";
    private static final String GAME_END_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";

    //"숫자 야구 게임을 시작합니다.";를 출력하는 메소드
    public void printStartGame() {
        System.out.println(GAME_START_MESSAGE);
    }

    //"3개의 숫자를 모두 맞히셨습니다! 게임 종료"; 를 출력하는 메소드
    public void printEndGame() {
        System.out.println(GAME_END_MESSAGE);
    }

    // 컴퓨터가 입력한 숫자와 사용자가 입력한 숫자를 검사 후, 메시지 출력
    public void printBaseballHint(String hintMessage) {
        System.out.println(hintMessage);
    }
}
