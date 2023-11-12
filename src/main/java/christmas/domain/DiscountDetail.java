package christmas.domain;

import christmas.enums.Discount;
import java.util.Map;

public record DiscountDetail(Map<Discount, Integer> discountDetail) {


	public int calculateFinalPayment(int totalAmount) {
		return totalAmount - calculateTotalDiscountAmount();
	}

	public int calculateTotalDiscountAmount() {
		return discountDetail.entrySet().stream()
				.mapToInt(this::getDiscountAmount)
				.sum();
	}

	public int getDiscountAmount(Map.Entry<Discount, Integer> discountDetail) {
		return discountDetail.getValue();
	}
}
