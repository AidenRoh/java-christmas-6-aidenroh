package christmas.util;

import static christmas.exception.InvalidMenuErrorMessage.BAD_DELIMITER_HYPHEN;
import static christmas.exception.InvalidMenuErrorMessage.BLANK_EXIST;
import static christmas.exception.InvalidMenuErrorMessage.DUPLICATE_MENU;
import static christmas.exception.InvalidMenuErrorMessage.INVALID_FORMAT;
import static christmas.exception.InvalidMenuErrorMessage.INVALID_MENU_AMOUNT;
import static christmas.exception.InvalidMenuErrorMessage.NON_EXIST_MENU;
import static christmas.exception.InvalidMenuErrorMessage.NON_NUMERIC;
import static christmas.validation.UIValidator.validateDuplicateMenu;
import static christmas.validation.UIValidator.validateExistMenu;
import static christmas.validation.UIValidator.validateNumericValue;
import static christmas.validation.UIValidator.validateOrderAmount;

import christmas.domain.PromotionPeriod;
import christmas.domain.constant.Discount;
import christmas.domain.constant.Menu;
import christmas.validation.UIValidator;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Convertor {

	private static final String GIFT_GOODS = "증정 이벤트";
	private static final String DELIMITER_COMMA = ",";
	private static final String DELIMITER_HYPHEN = "-";

	//Convertor for Benefit
	public static Map<String, Integer> menuItemToStringMap(Map<Menu, Integer> detail) {
		Map<String, Integer> giftMenu = new HashMap<>();

		detail.forEach((key, value) -> giftMenu.put(key.getItem(), value));
		return giftMenu;
	}

	public static Map<String, Integer> menuPriceToStringMap(Map<Menu, Integer> detail) {
		Map<String, Integer> benefitDetailsOfMenu = new HashMap<>();

		detail.forEach((key, value) -> {
			benefitDetailsOfMenu.put(GIFT_GOODS, key.getPrice());
		});
		return benefitDetailsOfMenu;
	}

	public static Map<String, Integer> discountToStringMap(Map<Discount, Integer> detail) {
		Map<String, Integer> benefitDetailsOfDiscount = new HashMap<>();

		detail.forEach((key, value) -> {
			benefitDetailsOfDiscount.put(key.getTitle(), value);
		});
		return benefitDetailsOfDiscount;
	}

	//Convertor for UI
	public static PromotionPeriod InputToEventPeriod(String input) {
		return PromotionPeriod.valueOf(Integer.parseInt(input));
	}

	public static Map<String, Integer> InputToStringMap(String input) {
		Map<String, Integer> resultMap = Arrays.stream(splitByComma(input))
				.map(Convertor::splitByHyphen)
				.collect(Collectors.toMap(
						entry -> findMenu(entry[0]),
						entry -> convertStringToIntForMap(entry[1]),
						(previous, next) -> previous
				));
		DUPLICATE_MENU.findErrorBy(() -> validateDuplicateMenu(input, resultMap));
		return resultMap;
	}

	private static String findMenu(String value) {
		NON_EXIST_MENU.findErrorBy(() -> validateExistMenu(value));
		return value;
	}

	private static int convertStringToIntForMap(String value) {
		NON_NUMERIC.findErrorBy(() -> validateNumericValue(value));
		INVALID_MENU_AMOUNT.findErrorBy(() -> validateOrderAmount(value));
		return Integer.parseInt(value);
	}

	private static String[] splitByComma(String value) {
		BLANK_EXIST.findErrorBy(() -> UIValidator.validateBlankByComma(value));
		return value.split(DELIMITER_COMMA);
	}

	private static String[] splitByHyphen(String value) {
		INVALID_FORMAT.findErrorBy(() -> UIValidator.validateMenuFormat(value));
		BAD_DELIMITER_HYPHEN.findErrorBy(() -> UIValidator.validateBadByHyphen(value));
		return value.split(DELIMITER_HYPHEN);
	}
}
