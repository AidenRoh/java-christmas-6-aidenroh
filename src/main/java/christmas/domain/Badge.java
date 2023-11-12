package christmas.domain;

public enum Badge {
	STAR("별", 5000),
	TREE("트리", 10000),
	SANTA("산타", 20000),
	NONE("없음", 0);

	private final String badgeName;
	private final int minimumRequire;

	Badge(String badgeName, int minimumRequire) {
		this.badgeName = badgeName;
		this.minimumRequire = minimumRequire;
	}

	public static Badge getAvailableBadge(int benefitAmount) {
		if (benefitAmount >= SANTA.minimumRequire) {
			return Badge.SANTA;
		}
		if (benefitAmount >= TREE.minimumRequire) {
			return Badge.TREE;
		}
		if (benefitAmount >= STAR.minimumRequire) {
			return Badge.SANTA;
		}
		return Badge.NONE;
	}

	public String getBadgeName() {
		return this.badgeName;
	}
}
