package christmas.enums;

import java.util.Arrays;
import java.util.Objects;

public enum Menu {
	MUSHROOM_SOUP("양송이수프", "APPETIZER", 6_000),
	TAPAS("타파스", "APPETIZER", 5_500),
	CAESAR_SALAD("시저샐러드", "APPETIZER", 8_000),
	T_BONE_STEAK("티본스테이크", "MAIN_DISH", 55_000),
	BBQ_RIB("바비큐립", "MAIN_DISH", 54_000),
	SEAFOOD_PASTA("해산물파스타", "MAIN_DISH", 35_000),
	CHRISTMAS_PASTA("크리스마스파스타", "MAIN_DISH", 25_000),
	CHOCO_CAKE("초코케이크", "DESSERT", 15_000),
	ICE_CREAM("아이스크림", "DESSERT", 5_000),
	ZERO_COKE("제로콜라", "DRINK", 3_000),
	RED_WINE("레드와인", "DRINK", 60_000),
	CHAMPAGNE("샴페인", "DRINK", 25_000);

	private final String item;
	private final String courseType;
	private final int price;

	Menu(String food, String courseType, int price) {
		this.item = food;
		this.courseType = courseType;
		this.price = price;
	}

	public String getCourseType() {
		return this.courseType;
	}

	public int getPrice() {
		return this.price;
	}

	public String getItem() {
		return this.item;
	}

	public static Menu findMenu(String item) {
		return Arrays.stream(Menu.values())
				.filter(menu -> Objects.equals(menu.item, item))
				.findAny()
				.orElseThrow(IllegalArgumentException::new);
	}
}
