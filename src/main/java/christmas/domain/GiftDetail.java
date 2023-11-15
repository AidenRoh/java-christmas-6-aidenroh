package christmas.domain;

import christmas.domain.constant.Menu;
import java.util.EnumMap;
import java.util.Map;

public class GiftDetail {

	private static final int GIFT_CONDITION = 120_000;
	private static final int GIFT_AMOUNT = 1;
	private final Map<Menu, Integer> giftGoods;

	public GiftDetail(OrderDetail orderDetail, Menu menu) {
		Map<Menu, Integer> giftGoods = new EnumMap<>(Menu.class);

		if (checkAvailability(orderDetail)) {
			giftGoods.put(menu, GIFT_AMOUNT);
		}
		this.giftGoods = giftGoods;
	}

	public int calculateGiftPrice() {
		if (giftGoods.isEmpty()) {
			return 0;
		}
		return giftGoods.entrySet().stream()
				.mapToInt(item -> item.getKey().getPrice() * item.getValue())
				.sum();
	}

	public Map<Menu, Integer> getGiftGoods() {
		return this.giftGoods;
	}

	public boolean checkEmpty() {
		return giftGoods.isEmpty();
	}

	private boolean checkAvailability(OrderDetail orderAmount) {
		return orderAmount.calculateTotalOrderAmount() >= GIFT_CONDITION;
	}
}
