package christmas.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

public class EventPeriod {
	private static final int INDEX_GAP = 1;
	private static final int EVENT_START_DATE = 1;
	private static final int EVENT_START_MONTH = 12;
	private static final int EVENT_START_YEAR = 2023;
	private static final LocalDate EVENT_BEGIN = LocalDate.of(EVENT_START_YEAR, EVENT_START_MONTH, EVENT_START_DATE);
	private static final int EVENT_DURATION = 31;
	private static final List<EventPeriod> EVENT_PERIOD;

	static {
		EVENT_PERIOD = IntStream.range(0, EVENT_DURATION)
				.mapToObj(EventPeriod::new)
				.toList();
	}

	private final LocalDate date;

	private EventPeriod(int date) {
		this.date = EVENT_BEGIN.plusDays(date);
	}

	public static EventPeriod valueOf(int date) {
		return EVENT_PERIOD.get(date - INDEX_GAP);
	}

	public LocalDate getDate() {
		return this.date;
	}
}
