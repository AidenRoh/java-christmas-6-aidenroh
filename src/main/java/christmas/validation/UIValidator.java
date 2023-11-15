package christmas.validation;

import static christmas.exception.InvalidBookingErrorMessage.NEGATIVE_NUMBER;
import static christmas.exception.InvalidBookingErrorMessage.NON_NUMERIC;
import static christmas.exception.InvalidMenuErrorMessage.BAD_DELIMITER;

import christmas.domain.constant.Menu;
import christmas.exception.InvalidBookingErrorMessage;
import christmas.exception.InvalidMenuErrorMessage;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;

public class UIValidator {

	public static void validateBookingDate(String input) {
		InvalidBookingErrorMessage.EMPTY_INPUT.findErrorBy(() -> validateEmptyInput(input));
		NON_NUMERIC.findErrorBy(() -> validateNumericValue(input));
		NEGATIVE_NUMBER.findErrorBy(() -> validateNegativeNumber(input));
	}

	public static void validateOrderMenu(String input) {
		InvalidMenuErrorMessage.EMPTY_INPUT.findErrorBy(() -> validateEmptyInput(input));
		BAD_DELIMITER.findErrorBy(() -> validateBadDelimiter(input));
	}

	public static boolean validateEmptyInput(String input) {
		return input.isEmpty();
	}

	public static boolean validateNumericValue(String input) {
		try {
			Integer.parseInt(input);
			return false;
		} catch (NumberFormatException ignore) {
			return true;
		}
	}

	public static boolean validateBadDelimiter(String input) {
		return (input.charAt(0) == ',' || input.charAt(input.length() - 1) == ',');
	}

	public static boolean validateNegativeNumber(String input) {
		return Integer.parseInt(input) < 0;
	}

	public static boolean validateBlankByComma(String input) {
		String[] menus = input.split(",");
		return Arrays.stream(menus).anyMatch(String::isBlank);
	}

	public static boolean validateBlankByHyphen(String input) {
		String[] menus = input.split("-");
		return Arrays.stream(menus).anyMatch(String::isBlank);
	}

	public static boolean validateMenuFormat(String input) {
		Pattern pattern = Pattern.compile("^[가-힣]{0,10}-.*");
		return !pattern.matcher(input).matches();
	}

	public static boolean validateExistMenu(String input) {
		return Menu.findMenu(input) == Menu.NONE;
	}

	public static boolean validateOrderAmount(String amount) {
		int value = Integer.parseInt(amount);
		return (value < 1 || value > 20);
	}

	public static boolean validateDuplicateMenu(String input, Map<String, Integer> map) {
		return input.split(",").length != map.size();
	}
}
