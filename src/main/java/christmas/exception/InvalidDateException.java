package christmas.exception;

public class InvalidDateException extends ChristmasPromotionException {

	private static final String MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";

	InvalidDateException(String errorMessage) {
		super("%s %s".formatted(MESSAGE, errorMessage));
	}
}
