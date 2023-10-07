package controller;

import model.GameNumber;
import view.OutputView;

import java.util.List;

/**
 * packageName    : controller
 * fileName       : ComputerController
 * author         : qkrtn_ulqpbq2
 * date           : 2023-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-07        qkrtn_ulqpbq2       최초 생성
 */
public class ComputerController {
    private static final int BASEBALL_GAME_NUMBER_LENGTH = 3;
    private static final int BASEBALL_SUCCESS_STRIKE_COUNT = 3;
    private static final int INIT_ZERO = 0;
    private static final String STRIKE_MESSAGE = "스트라이크";
    private static final String BALL_MESSAGE = "볼";
    private static final String NOTHING_MESSAGE = "낫싱";
    private static final String NULL_MESSAGE = "";
    private static final String SPACING_MESSAGE = " ";
    private int strikeCount;
    private int ballCount;
    RandomUtility randomUtility = new RandomUtility();
    OutputView outputView = new OutputView();


    //컴퓨터는 서로다른 3자리의 숫자를 생성한다.
    public void startGame(GameNumber gameNumber) {
        List<Integer> computerNumbers = randomUtility.generateRandomNumbers();
        gameNumber.setComputerNumbers(computerNumbers);
    }

    //스트라이크의 개수가 3개라면 true, 아니면 false반환
    public boolean proceedComputerToDo(GameNumber gameNumber) {
        List<Integer> playerNumbers = gameNumber.getPlayerNumbers();
        List<Integer> computerNumbers = gameNumber.getComputerNumbers();

        // 초기화 및 스트라이크와 볼의 개수를 확인한후, 메시지 출력메소드 호출
        compareNumbers(playerNumbers, computerNumbers);

        //스트라이크의 개수가 3개인지 판별하는 메소드
        return isThreeStrike();
    }

    // 초기화 및 스트라이크와 볼의 개수를 확인한후, 메시지 출력메소드
    public void compareNumbers(List<Integer> playerNumbers, List<Integer> computerNumbers) {
        //초기화. 스트라이크의 개수 0, 볼의 개수 0으로 초기화한다.
        initCount();
        //스트라이크의 개수와 볼의 개수를 확인한다.
        checkStrikeAndBall(playerNumbers, computerNumbers);
        //메시지 출력 메서드
        provideHint();
    }

    //스트라이크의 개수와, 볼의 개수를 0으로 초기화하는 메소드
    private void initCount() {
        strikeCount = INIT_ZERO;
        ballCount = INIT_ZERO;
    }


    //스트라이크의 개수와 볼의 개수를 확인하는 메소드
    private void checkStrikeAndBall(List<Integer> playerNumbers, List<Integer> computerNumbers) {
        for (int i = 0; i < BASEBALL_GAME_NUMBER_LENGTH; i++) {
            countStrike(computerNumbers.get(i), playerNumbers.get(i));
            countBall(computerNumbers, playerNumbers.get(i), i);
        }
    }

    //스트라이크의 개수를 확인하는 메소드
    private void countStrike(int computerNumber, int playerNumber) {
        if (computerNumber == playerNumber) {
            strikeCount++;
        }
    }

    //볼의 개수를 확인하는 메소드

    private void countBall(List<Integer> computerNumbers, int playerNumber, int numberIndex) {
        if (computerNumbers.get(numberIndex) != playerNumber && computerNumbers.contains(playerNumber)) {
            ballCount++;
        }
    }

    //메시지 출력 메소드
    public void provideHint() {
        String hintMessage = "";

        hintMessage += getBallHintMessage();
        hintMessage += getStrikeHintMessage();
        hintMessage += getNotingHintMessage();

        outputView.printBaseballHint(hintMessage);
    }

    //볼의 개수가 한개라면, "1볼 "이라고 출력
    //볼의 개수가 없다면, ""반환
    private String getBallHintMessage() {
        if (ballCount > 0) {
            return ballCount + BALL_MESSAGE + SPACING_MESSAGE;
        }

        return NULL_MESSAGE;
    }

    //스트라이크의 개수가 한개라면, "2스트라이크 "라고 출력
    //스트라이크의 개수가 없다면, ""반환
    private String getStrikeHintMessage() {
        if (strikeCount > 0) {
            return strikeCount + STRIKE_MESSAGE + SPACING_MESSAGE;
        }

        return NULL_MESSAGE;
    }

    //"낫싱" -> 볼과 스트라이크가 아예 없는경우, 낫싱반환
    //볼과 스트라이크 중 둘중에 하나라도 존재한다면, ""반환
    private String getNotingHintMessage() {
        if (ballCount == 0 && strikeCount == 0) {
            return NOTHING_MESSAGE;
        }

        return NULL_MESSAGE;
    }

    //스트라이크의 개수가 3개인지 판별하는 메소드
    public boolean isThreeStrike() {
        return strikeCount == BASEBALL_SUCCESS_STRIKE_COUNT;
    }
}
