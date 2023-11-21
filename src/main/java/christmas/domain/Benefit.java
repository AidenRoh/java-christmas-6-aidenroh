package christmas.domain;

import christmas.domain.constant.Badge;
import christmas.util.Convertor;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Benefit {

	private final DiscountDetail discountDetail;
	private final GiftDetail giftDetail;

	public Benefit(DiscountDetail discountDetail, GiftDetail giftDetail) {
		this.discountDetail = discountDetail;
		this.giftDetail = giftDetail;
	}

	public Map<String, Integer> generateBenefitDetails() {
		Map<String, Integer> discountMap =
				convertMap(discountDetail::discountDetail, Convertor::discountToStringMap);
		Map<String, Integer> giftMap =
				convertMap(giftDetail::getGiftGoods, Convertor::menuPriceToStringMap);

		return Stream.of(giftMap, discountMap)
				.flatMap(map -> map.entrySet().stream())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	public int generateTotalBenefitAmount() {
		return -(discountDetail.calculateTotalDiscountAmount() + giftDetail.calculateGiftPrice());
	}

	public int generateFinalPayment(int orderAmount) {
		return discountDetail.calculateFinalPayment(orderAmount);
	}

	public Badge generateBadge() {
		return Badge.getAvailableBadge(generateTotalBenefitAmount());
	}

	private <T> Map<String, Integer> convertMap(Supplier<T> detail,
												Function<T, Map<String, Integer>> conversion) {
		T detailMap = detail.get();
		return conversion.apply(detailMap);
	}
}
