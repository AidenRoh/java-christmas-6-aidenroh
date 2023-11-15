package christmas.ui;


import camp.nextstep.edu.missionutils.Console;
import christmas.validation.UIValidator;
import java.util.function.Consumer;

public final class InputView extends ConsoleWriter {

	public String inputBookingDate() {
		UIMessage.BOOKING_DATE.accept(this::println);
		return readLine(UIValidator::validateBookingDate);
	}

	public String inputOrderMenu() {
		UIMessage.ORDER_MENU.accept(this::println);
		return readLine(UIValidator::validateOrderMenu);
	}

	private static String readLine(Consumer<String> validator) {
		String input = Console.readLine();
		validator.accept(input);
		return input;
	}
}
