package christmas.validation;

import christmas.domain.constant.Menu;
import java.util.Map;

public class DomainValidator {
	//예외상황에 true 값이 나오게
	private static final int EVENT_START = 1;
	private static final int EVENT_END = 31;

	public static boolean validateBookingDate(int date) {
		return (date < EVENT_START || date > EVENT_END);
	}

	public static boolean validateBeverageOnly(Map<String, Integer> userOrder) {
		int countNonBeverage = (int) userOrder.keySet().stream()
				.filter(Menu::isNotBeverage)
				.count();
		return countNonBeverage == 0;
	}

	public static boolean validateMaximumOrderAmount(Map<String, Integer> userOrder) {
		int orderAmount = userOrder.values().stream()
				.mapToInt(Integer::intValue)
				.sum();
		return orderAmount > 20;
	}
}
