package christmas;

import christmas.controller.PromotionController;
import christmas.domain.DiscountCenter;
import christmas.domain.constant.Menu;
import christmas.domain.discount.ChristmasDiscount;
import christmas.domain.discount.SpecialDiscount;
import christmas.domain.discount.WeekdayDiscount;
import christmas.domain.discount.WeekendDiscount;
import christmas.ui.OutputView;

public class Application {
	public static void main(String[] args) {

		DiscountCenter discountCentre = new DiscountCenter(
				new ChristmasDiscount(),
				new SpecialDiscount(),
				new WeekendDiscount(),
				new WeekdayDiscount()
		);
		OutputView outputView = new OutputView();

		PromotionController controller = new PromotionController(
				discountCentre,
				Menu.CHAMPAGNE,
				outputView);

		controller.makeReservation();
	}
}
