package christmas.exception;

public abstract class ChristmasPromotionException extends IllegalArgumentException {

	private static final String ERROR_PREFIX = "[ERROR]";

	protected ChristmasPromotionException(String message) {
		super("%s %s".formatted(ERROR_PREFIX, message));
	}
}
