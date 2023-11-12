package christmas.domain;

import christmas.enums.Menu;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class OrderDetail {
	private final Map<Menu, Integer> orderDetail;

	private OrderDetail(Map<Menu, Integer> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public static OrderDetail of(HashMap<String, Integer> orderMenu) {
		Map<Menu, Integer> confirmedOrder = new EnumMap<>(Menu.class);
		orderMenu.forEach((key, value) -> confirmedOrder.put(Menu.findMenu(key), value));
		return new OrderDetail(confirmedOrder);
	}

	public int calculateTotalOrderAmount() {
		return orderDetail.entrySet().stream()
				.mapToInt(this::calculateItemPrice)
				.sum();
	}

	public Map<Menu, Integer> getOrderDetail() {
		return this.orderDetail;
	}

	private int calculateItemPrice(Map.Entry<Menu, Integer> item) {
		return item.getKey().getPrice() * item.getValue();
	}
}
