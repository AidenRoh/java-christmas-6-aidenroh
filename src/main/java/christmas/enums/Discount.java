package christmas.enums;

public enum Discount {
	CHRISTMAS_DISCOUNT("크리스마스 디데이", "GLOBAL", 1000),
	WEEKDAY_DISCOUNT("평일", "DESSERT", 2023),
	WEEKEND_DISCOUNT("주말", "MAIN_DISH", 2023),
	SPECIAL_DISCOUNT("특별", "GLOBAL", 1000);

	private final String title;
	private final String targetCouseType;
	private final int discountAmount;

	Discount(String title, String appliedArea, int discountAmount) {
		this.title = title;
		this.targetCouseType = appliedArea;
		this.discountAmount = discountAmount;
	}

	public int getDiscountAmount() {
		return discountAmount;
	}

	public String getTargetCouseType() {
		return this.targetCouseType;
	}

}
