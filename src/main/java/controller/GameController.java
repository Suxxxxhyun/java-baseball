package controller;

import model.GameNumber;
import view.InputView;
import view.OutputView;

import java.util.List;

/**
 * packageName    : controller
 * fileName       : GameController
 * author         : qkrtn_ulqpbq2
 * date           : 2023-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-07        qkrtn_ulqpbq2       최초 생성
 */
public class GameController {
    private static final String WRONG_ANSWER_INPUT_MESSAGE = "1 혹은 2만 입력 가능합니다.";
    private static final String BASEBALL_GAME_RESTART_ANSWER = "1";
    private static final String BASEBALL_GAME_END_ANSWER = "2";
    GameNumber gameNumber = new GameNumber();
    ComputerController computerController = new ComputerController();
    NumberValidator numberValidator = new NumberValidator();
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void proceedGame() {
        boolean gameState = true;

        while (gameState) {
            //"게임을 시작합니다." 출력
            outputView.printStartGame();
            //컴퓨터는 서로 다른 3자리의 숫자를 생성한다.
            computerController.startGame(gameNumber);
            //메시지 출력
            repeatGuessingAnswer();
            //게임을 다시할지 묻는 메소드
            gameState = askRestartGame();
        }
    }

    private void repeatGuessingAnswer() {
        boolean correctAnswer = false;

        while (!correctAnswer) {
            //사용자가 입력한 숫자가 유효한지 검사
            setAndSavePlayerNumbers();
            //대답 반환
            correctAnswer = computerController.proceedComputerToDo(gameNumber);
            //메시지 출력
            sendEndMessageIfTrue(correctAnswer);
        }
    }

    //사용자가 입력한 숫자가 유효한지 검사하기 위한 메소드
    private void setAndSavePlayerNumbers() {
        // 사용자는 숫자를 입력한다.
        gameNumber.setPlayerInput(inputView.enterGameNumber());
        //사용자가 입력한 숫자가 유효한지 검사한다.
        List<Integer> playerNumbers = numberValidator.toValidateData(gameNumber.getPlayerInput());
        gameNumber.setPlayerNumbers(playerNumbers);
    }

    //스트라이크가 3개라면, "3개의 숫자를 모두 맞히셨습니다! 게임 종료"; 라고 메시지 반환하는 메소드
    private void sendEndMessageIfTrue(boolean correctAnswer) {
        if (correctAnswer) {
            outputView.printEndGame();
        }
    }

    //게임 재시작 여부를 묻는 메소드
    private boolean askRestartGame() {
        //"게임을 시작하려면 1, 종료하려면 2를 입력해주세요";를 출력
        String answer = inputView.enterAnswerRestartGame();
        //1(게임 재시작)이면, true반환, 2(종료)이면 false반환
        return wantRestartGame(answer);
    }

    //1(게임 재시작)이면, true반환, 2(종료)이면 false반환
    public boolean wantRestartGame(String answer) {
        if (answer.equals(BASEBALL_GAME_RESTART_ANSWER)) {
            return true;
        } else if (answer.equals(BASEBALL_GAME_END_ANSWER)) {
            return false;
        }

        //IllegalArgumentException예외 처리(1, 2 이외에 값을 입력한 경우)
        throw new IllegalArgumentException(WRONG_ANSWER_INPUT_MESSAGE);
    }
}
