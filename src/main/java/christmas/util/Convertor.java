package christmas.util;

import static christmas.enums.Delimiter.GIFT_GOODS;

import christmas.enums.Discount;
import christmas.enums.Menu;
import java.util.HashMap;
import java.util.Map;

public class Convertor {

	public static Map<String, Integer> menuItemEnumToMap(Map<Menu, Integer> detail) {
		Map<String, Integer> giftMenu = new HashMap<>();

		detail.forEach((key, value) -> giftMenu.put(key.getItem(), value));
		return giftMenu;
	}

	public static Map<String, Integer> menuPriceEnumToMap(Map<Menu, Integer> detail) {
		Map<String, Integer> benefitDetailsOfMenu = new HashMap<>();

		detail.forEach((key, value) -> {
			benefitDetailsOfMenu.put(GIFT_GOODS.getDelimiter(), key.getPrice());
		});
		return benefitDetailsOfMenu;
	}

	public static Map<String, Integer> discountEnumToMap(Map<Discount, Integer> detail) {
		Map<String, Integer> benefitDetailsOfDiscount = new HashMap<>();

		detail.forEach((key, value) -> {
			benefitDetailsOfDiscount.put(key.getTitle(), value);
		});
		return benefitDetailsOfDiscount;
	}
}
