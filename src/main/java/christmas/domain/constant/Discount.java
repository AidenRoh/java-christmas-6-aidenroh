package christmas.domain.constant;

public enum Discount {

	CHRISTMAS_DISCOUNT("크리스마스 디데이", "GLOBAL", 1000),
	WEEKDAY_DISCOUNT("평일", "DESSERT", 2023),
	WEEKEND_DISCOUNT("주말", "MAIN_DISH", 2023),
	SPECIAL_DISCOUNT("특별", "GLOBAL", 1000);

	private static final String DISCOUNT_SUFFIX = "할인";

	private final String title;
	private final String targetCourse;
	private final int discountAmount;

	Discount(String title, String appliedArea, int discountAmount) {
		this.title = title;
		this.targetCourse = appliedArea;
		this.discountAmount = discountAmount;
	}

	public int getDiscountAmount() {
		return discountAmount;
	}

	public String getTargetCourse() {
		return this.targetCourse;
	}

	public String getTitle() {
		return "%s %s".formatted(this.title, DISCOUNT_SUFFIX);
	}
}
