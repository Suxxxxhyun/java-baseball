package controller;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * packageName    : controller
 * fileName       : NumberValidator
 * author         : qkrtn_ulqpbq2
 * date           : 2023-10-07
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-10-07        qkrtn_ulqpbq2       최초 생성
 */
public class NumberValidator {
    private static final int BASEBALL_GAME_NUMBER_LENGTH = 3;
    private static final String WRONG_LENGTH_INPUT_MESSAGE = "숫자 세 자리를 입력해주세요.";
    private static final String WRONG_RANGE_INPUT_MESSAGE = "1 ~ 9 사이의 숫자만 입력 가능합니다.";
    private static final String DUPLICATE_NUMBER_INPUT_MESSAGE = "서로 다른 세 자리의 숫자만 입력 가능합니다.";
    private static final char BASEBALL_GAME_START_NUMBER_CHAR = '1';
    private static final char BASEBALL_GAME_END_NUMBER_CHAR = '9';

    public List<Integer> toValidateData(String inputNumber) {
        //isTreeLetters() 문자의 길이가 3인지 확인
        if (!isTreeLetters(inputNumber)) {
            //사용자가 입력한 문자의 길이가 3이 아니라면, "숫자 세 자리를 입력해주세요.";출력
            throw new IllegalArgumentException(WRONG_LENGTH_INPUT_MESSAGE);
        }
        //사용자가 입력한 숫자가 1과 9사이의 숫자인지 판단
        else if (!isNumericString(inputNumber)) {
            //"1 ~ 9 사이의 숫자만 입력 가능합니다.";출력
            throw new IllegalArgumentException(WRONG_RANGE_INPUT_MESSAGE);
        }
        //사용자가 입력한 숫자가 서로다른 숫자로 이루어져있는지 판단.
        //"서로 다른 세 자리의 숫자만 입력 가능합니다.";를 출력
        else if (!isDifferentCharacters(inputNumber)) {
            throw new IllegalArgumentException(DUPLICATE_NUMBER_INPUT_MESSAGE);
        }
        //사용자가 입력한 숫자(String형태)를 int[]배열로 반환
        return convertStringToIntList(inputNumber);
    }

    //사용자가 입력한 문자의 길이가 3인지 확인하는 메소드
    public boolean isTreeLetters(String inputNumber) {
        return inputNumber.length() == BASEBALL_GAME_NUMBER_LENGTH;
    }

    //사용자가 입력한 숫자가 1과 9사이의 숫자인지를 판단하는 메소드
    public boolean isNumericString(String inputNumber) {
        for (int i = 0; i < inputNumber.length(); i++) {
            if (!isBetween1And9(inputNumber.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    //1과 9사이의 숫자인지 확인
    private boolean isBetween1And9(char checkChar) {
        return checkChar >= BASEBALL_GAME_START_NUMBER_CHAR && checkChar <= BASEBALL_GAME_END_NUMBER_CHAR;
    }

    //서로다른 숫자인지 판단. 이때 서로다른 숫자는 3개여야 올바름. 따라서 3으로 비교
    public boolean isDifferentCharacters(String inputNumber) {
        String[] numbers = inputNumber.split("");
        Set<String> setNumbers = Arrays.stream(numbers).collect(Collectors.toSet());

        return setNumbers.size() == BASEBALL_GAME_NUMBER_LENGTH;
    }

    //사용자가 입력한 숫자(String형태)를 int[]배열로 반환
    public List<Integer> convertStringToIntList(String inputNumber) {
        int[] convertIntArray = Stream.of(inputNumber.split("")).mapToInt(Integer::parseInt).toArray();
        return Arrays.stream(convertIntArray).boxed().collect(Collectors.toList());
    }
}
