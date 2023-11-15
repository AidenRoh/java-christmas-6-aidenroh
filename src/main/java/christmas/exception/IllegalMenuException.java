package christmas.exception;

public class IllegalMenuException extends ChristmasPromotionException {

	private static final String MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";

	protected IllegalMenuException(String message) {
		super("%s %s".formatted(MESSAGE, message));
	}
}
