package controller;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName    : controller
 * fileName       : RandomUtility
 * author         : qkrtn_ulqpbq2
 * date           : 2023-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-07        qkrtn_ulqpbq2       최초 생성
 */
public class RandomUtility {
    private static final int BASEBALL_GAME_NUMBER_LENGTH = 3;
    private static final int BASEBALL_GAME_RANDOM_RANGE_START = 1;
    private static final int BASEBALL_GAME_RANDOM_RANGE_END = 9;

    // 컴퓨터는 서로다른 3자리의 숫자를 생성한다.
    public List<Integer> generateRandomNumbers() {
        List<Integer> randomNumbers = new ArrayList<>();

        for (int i = 0; i < BASEBALL_GAME_NUMBER_LENGTH; i++) {
            randomNumbers.add(generateRandomNumber(randomNumbers));
        }

        return randomNumbers;
    }

    //Randoms라이브러리를 사용하여, 1~9사이의 숫자를 생성
    private int generateRandomNumber(List<Integer> randomNumbers) {
        while (true) {
            int randomNumber = Randoms.pickNumberInRange(BASEBALL_GAME_RANDOM_RANGE_START,
                    BASEBALL_GAME_RANDOM_RANGE_END);

            if (isDifferentNumber(randomNumbers, randomNumber)) {
                return randomNumber;
            }
        }
    }

    //서로 다른 숫자를 생성해야하므로, 컴퓨터가 생성한 숫자는 기존에 생성된 숫자(randomNumbers리스트)에 이미 숫자가 있는지 확인한다.
    private boolean isDifferentNumber(List<Integer> randomNumbers, int randomNumber) {
        return !randomNumbers.contains(randomNumber);
    }
}
