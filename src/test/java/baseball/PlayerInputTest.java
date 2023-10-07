package baseball;

import controller.GameController;
import controller.NumberValidator;
import model.GameNumber;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

/**
 * packageName    : baseball
 * fileName       : PlayerInputTest
 * author         : qkrtn_ulqpbq2
 * date           : 2023-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-07        qkrtn_ulqpbq2       최초 생성
 */
public class PlayerInputTest {
    GameController gameController = new GameController();
    GameNumber gameNumber = new GameNumber();
    NumberValidator numberValidator = new NumberValidator();

    @Test
    void 사용자_입력_숫자_유효성_검사_정상_입력() {
        String playerInput = "123";
        List<Integer> playerList = Arrays.asList(1, 2, 3);
        //사용자가 입력한 숫자의 유효성 확인
        assertThat(numberValidator.toValidateData(playerInput)).isEqualTo(playerList);
    }

    @Test
    void 사용자_입력_숫자_유효성_검사_정상_띄어쓰기_입력() {
        // 띄어쓰기는 setInputString()에서 처리해주므로, 이 테스트만 gameNumber에 저장해 사용
        String playerInput = "1 2 3";
        List<Integer> playerList = Arrays.asList(1, 2, 3);
        gameNumber.setPlayerInput(playerInput);
        //사용자가 입력한 숫자의 유효성 확인
        assertThat(numberValidator.toValidateData(gameNumber.getPlayerInput())).isEqualTo(playerList);
    }

    @Test
    void 사용자_입력_숫자_유효성_검사_세자리_미만_입력() {
        String playerInput = "12";
        String exceptionMessage = "숫자 세 자리를 입력해주세요.";

        //사용자가 입력한 숫자의 유효성 확인
        Throwable throwable = catchThrowable(() -> {
            numberValidator.toValidateData(playerInput);
        });
        //예외가 코드에서 예상한 예외 타입인지를 확인
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage(exceptionMessage);
    }

    @Test
    void 사용자_입력_숫자_유효성_검사_세자리_초과_입력() {
        String playerInput = "12345";
        String exceptionMessage = "숫자 세 자리를 입력해주세요.";

        //사용자가 입력한 숫자의 유효성 확인
        Throwable throwable = catchThrowable(() -> {
            numberValidator.toValidateData(playerInput);
        });
        //예외가 코드에서 예상한 예외 타입인지를 확인
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage(exceptionMessage);
    }

    @Test
    void 사용자_입력_숫자_유효성_검사_범위_외_값_입력() {
        String playerInput = "12d";
        String exceptionMessage = "1 ~ 9 사이의 숫자만 입력 가능합니다.";

        Throwable throwable = catchThrowable(() -> {
            numberValidator.toValidateData(playerInput);
        });
        //예외가 코드에서 예상한 예외 타입인지를 확인
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage(exceptionMessage);
    }

    @Test
    void 사용자_입력_숫자_유효성_검사_범위_외_값_입력2() {
        String playerInput = "012";
        String exceptionMessage = "1 ~ 9 사이의 숫자만 입력 가능합니다.";

        //사용자가 입력한 숫자의 유효성 확인
        Throwable throwable = catchThrowable(() -> {
            numberValidator.toValidateData(playerInput);
        });
        //예외가 코드에서 예상한 예외 타입인지를 확인
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage(exceptionMessage);
    }

    @Test
    void 사용자_입력_숫자_유효성_검사_중복_값_존재_입력() {
        String playerInput = "112";
        String exceptionMessage = "서로 다른 세 자리의 숫자만 입력 가능합니다.";

        //사용자가 입력한 숫자의 유효성 확인
        Throwable throwable = catchThrowable(() -> {
            numberValidator.toValidateData(playerInput);
        });
        //예외가 코드에서 예상한 예외 타입인지를 확인
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage(exceptionMessage);
    }

    @Test
    void 사용자_답변_유효성_검사_재시작() {
        String playerAnswer = "1";
        assertThat(gameController.wantRestartGame(playerAnswer)).isTrue();
    }

    @Test
    void 사용자_답변_유효성_검사_종료() {
        String playerAnswer = "2";
        assertThat(gameController.wantRestartGame(playerAnswer)).isFalse();
    }

    @Test
    void 사용자_답변_유효성_검사_오류() {
        String playerAnswer = "4";
        String exceptionMessage = "1 혹은 2만 입력 가능합니다.";

        Throwable throwable = catchThrowable(() -> {
            gameController.wantRestartGame(playerAnswer);
        });
        //예외가 코드에서 예상한 예외 타입인지를 확인
        assertThat(throwable).isInstanceOf(IllegalArgumentException.class);
        assertThat(throwable).hasMessage(exceptionMessage);
    }
}
