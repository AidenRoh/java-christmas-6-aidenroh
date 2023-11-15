package christmas.controller;

import christmas.domain.Benefit;
import christmas.domain.DiscountCenter;
import christmas.domain.DiscountDetail;
import christmas.domain.GiftDetail;
import christmas.domain.OrderDetail;
import christmas.domain.PromotionPeriod;
import christmas.domain.constant.Menu;
import christmas.ui.OutputView;
import christmas.ui.ValidateInputReceiver;

public class PromotionController {

	private final DiscountCenter discountCenter;
	private final Menu giftGoods;
	private final OutputView outputView;

	public PromotionController(DiscountCenter discountCenter,
							   Menu giftGoods,
							   OutputView outputView) {
		this.discountCenter = discountCenter;
		this.giftGoods = giftGoods;
		this.outputView = outputView;
	}

	public void makeReservation() {
		outputView.printInitialization();

		PromotionPeriod date = ValidateInputReceiver.receiveValidBookingDate();
		OrderDetail order = ValidateInputReceiver.receiveValidOrderMenu();
		GiftDetail gift = new GiftDetail(order, giftGoods);
		DiscountDetail discount = discountCenter.createDiscountDetail(order, date);
		Benefit benefit = new Benefit(discount, gift);

		outputView.printAnnounceBenefit(date.getDate().getDayOfMonth());
		renderPromotion(order, gift, benefit);
	}

	private void renderPromotion(OrderDetail order, GiftDetail gift, Benefit benefit) {
		outputView.print(order);
		outputView.print(gift);
		outputView.print(order, benefit);
	}


}
