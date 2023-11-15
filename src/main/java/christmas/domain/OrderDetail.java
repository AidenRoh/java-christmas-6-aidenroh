package christmas.domain;

import static christmas.exception.InvalidMenuErrorMessage.BEVERAGE_ONLY_ORDER;
import static christmas.exception.InvalidMenuErrorMessage.MAXIMUM_AMOUNT_OF_ORDER;
import static christmas.validation.DomainValidator.validateBeverageOnly;
import static christmas.validation.DomainValidator.validateMaximumOrderAmount;

import christmas.domain.constant.Menu;
import java.util.EnumMap;
import java.util.Map;

public class OrderDetail {

	private final Map<Menu, Integer> orderDetail;

	private OrderDetail(Map<Menu, Integer> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public static OrderDetail of(Map<String, Integer> orderMenu) {
		BEVERAGE_ONLY_ORDER.findErrorBy(() -> validateBeverageOnly(orderMenu));
		MAXIMUM_AMOUNT_OF_ORDER.findErrorBy(() -> validateMaximumOrderAmount(orderMenu));

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
