package christmas.domain;

import christmas.enums.Menu;
import java.util.HashMap;
import java.util.Map;

public class Benefit {
	private final DiscountDetail discountDetail;
	private final GiftDetail giftDetail;

	public Benefit(DiscountDetail discountDetail, GiftDetail giftDetail) {
		this.discountDetail = discountDetail;
		this.giftDetail = giftDetail;
	}

	public HashMap<String, Integer> generateGiftMenu() {
		HashMap<String, Integer> giftMenu = new HashMap<>();
		Map<Menu, Integer> gift = giftDetail.getGiftGoods();

		gift.forEach((key, value) -> giftMenu.put(key.getItem(), value));
		return giftMenu;
	}

	public HashMap<String, Integer> generateBenefitDetails() {
		HashMap<String, Integer> benefitDetails = new HashMap<>();
		Map<Menu, Integer> gift = giftDetail.getGiftGoods();

		discountDetail.discountDetail().forEach((key, value) -> benefitDetails.put(key.getTitle(), value));
		gift.forEach((key, value) -> benefitDetails.put("증정 이벤트", key.getPrice()));
		return benefitDetails;
	}

	public int generateTotalBenefitAmount() {
		return discountDetail.calculateTotalDiscountAmount() + giftDetail.calculateGiftPrice();
	}

	public String generateBadge() {
		return Badge.getAvailableBadge(generateTotalBenefitAmount()).getBadgeName();
	}

//	private static <T> HashMap<String, Integer> generateDetails(Supplier<T> detail,
//																Function<T, HashMap<String, Integer>> conversion) {
//		T detailMap = detail.get();
//		return conversion.apply(detailMap);
//	}

}
