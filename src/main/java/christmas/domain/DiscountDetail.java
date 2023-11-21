package christmas.domain;

import christmas.domain.constant.Discount;
import java.util.Map;

public record DiscountDetail(Map<Discount, Integer> discountDetail) {

	public int calculateTotalDiscountAmount() {
		return discountDetail.entrySet().stream()
				.mapToInt(this::getDiscountAmount)
				.sum();
	}

	public int calculateFinalPayment(int orderAmount) {
		return orderAmount - calculateTotalDiscountAmount();
	}

	private int getDiscountAmount(Map.Entry<Discount, Integer> discountDetail) {
		return discountDetail.getValue();
	}
}
