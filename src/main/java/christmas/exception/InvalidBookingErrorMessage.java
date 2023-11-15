package christmas.exception;

import java.util.function.Supplier;

public enum InvalidBookingErrorMessage {

	EMPTY_INPUT("입력값이 없습니다."),
	OUT_OF_PROMOTION_PERIOD("예약 날짜는 1일에서 31일 사이입니다."),
	NON_NUMERIC("숫자만 입력 가능합니다."),
	NEGATIVE_NUMBER("음수는 사용할 수 없습니다.");

	private final String message;

	InvalidBookingErrorMessage(String message) {
		this.message = message;
	}

	public void findErrorBy(Supplier<Boolean> condition) {
		if (condition.get()) {
			throw new InvalidDateException(this.message);
		}
	}
}
