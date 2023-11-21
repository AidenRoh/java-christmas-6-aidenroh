package christmas.domain;

import static christmas.exception.InvalidBookingErrorMessage.OUT_OF_PROMOTION_PERIOD;
import static christmas.validation.DomainValidator.validateBookingDate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

public class PromotionPeriod {

	private static final int INDEX_GAP = 1;
	private static final int PROMOTION_START_DATE = 1;
	private static final int PROMOTION_START_MONTH = 12;
	private static final int PROMOTION_START_YEAR = 2023;
	private static final LocalDate PROMOTION_BEGIN = LocalDate.of(
			PROMOTION_START_YEAR,
			PROMOTION_START_MONTH,
			PROMOTION_START_DATE);
	private static final int PROMOTION_DURATION = 31;
	private static final List<PromotionPeriod> PROMOTION_PERIOD;

	static {
		PROMOTION_PERIOD = IntStream.range(0, PROMOTION_DURATION)
				.mapToObj(PromotionPeriod::new)
				.toList();
	}

	private final LocalDate date;

	private PromotionPeriod(int date) {
		this.date = PROMOTION_BEGIN.plusDays(date);
	}

	public static PromotionPeriod valueOf(int date) {
		OUT_OF_PROMOTION_PERIOD.findErrorBy(() -> validateBookingDate(date));
		return PROMOTION_PERIOD.get(date - INDEX_GAP);
	}

	public LocalDate getDate() {
		return this.date;
	}
}