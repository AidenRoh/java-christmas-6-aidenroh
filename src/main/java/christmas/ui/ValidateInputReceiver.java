package christmas.ui;

import christmas.domain.OrderDetail;
import christmas.domain.PromotionPeriod;
import christmas.exception.ChristmasPromotionException;
import christmas.util.Convertor;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ValidateInputReceiver {

	private static final InputView inputView = new InputView();
	private static final OutputView outputView = new OutputView();

	public static PromotionPeriod receiveValidBookingDate() {
		return receiveValidateInput(inputView::inputBookingDate, Convertor::InputToEventPeriod);
	}

	public static OrderDetail receiveValidOrderMenu() {
		return receiveValidateInput(ValidateInputReceiver::receiveValidMenuForm, OrderDetail::of);
	}

	public static Map<String, Integer> receiveValidMenuForm() {
		return receiveValidateInput(inputView::inputOrderMenu, Convertor::InputToStringMap);
	}

	private static <T, R> R receiveValidateInput(Supplier<T> inputValue, Function<T, R> conversion) {
		while (true) {
			try {
				T input = inputValue.get();
				return conversion.apply(input);
			} catch (ChristmasPromotionException exception) {
				outputView.print(exception);
			}
		}
	}
}
