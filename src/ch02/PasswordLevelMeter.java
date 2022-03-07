package ch02;

public class PasswordLevelMeter {
	public PasswordLevel meter(String s) {
		if (s == null || s.isEmpty()) return PasswordLevel.INVALID;
		int metCnts = getMetCnts(s);

		if (metCnts <= 1) return PasswordLevel.WEAK;
		if (metCnts == 2) return PasswordLevel.NORMAL;
		return PasswordLevel.STRONG;
	}

	private boolean meetsContainingNumberCriteria(String s) {
		for (char ch : s.toCharArray()) {
			if (ch >= '0' && ch <= '9') {
				return true;
			}
		}
		return false;
	}

	private boolean meetsContainingUpperCaseCriteria(String s) {
		for (char ch : s.toCharArray()) {
			if (Character.isUpperCase(ch)) {
				return true;
			}
		}
		return false;
	}

	private int getMetCnts(String s) {
		int metCnts = 0;
		if (s.length() >= 8) metCnts++;
		if (meetsContainingNumberCriteria(s)) metCnts++;
		if (meetsContainingUpperCaseCriteria(s)) metCnts++;
		return metCnts;
	}

}
