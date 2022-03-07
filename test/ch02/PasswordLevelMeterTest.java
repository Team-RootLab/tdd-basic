package ch02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordLevelMeterTest {
	private PasswordLevelMeter meter = new PasswordLevelMeter();

	private void assertLevel(String password, PasswordLevel level){
		PasswordLevel result = meter.meter(password);
		assertEquals(level, result);
	}

	@Test
	void 아무조건도_충족하지_않은_경우() {
		assertLevel("abc", PasswordLevel.WEAK);
	}

	@Test
	void 입력이_없는_경우() {
		assertLevel(null, PasswordLevel.INVALID);
	}

	@Test
	void 입력이_빈문자열인_경우() {
		assertLevel("", PasswordLevel.INVALID);
	}

	@Test
	void 모든_조건을_만족하는_경우() {
		assertLevel( "ab12!@AB", PasswordLevel.STRONG);
		assertLevel( "abc1!Add", PasswordLevel.STRONG);
	}

	@Test
	void 길이조건만_불만족하는_경우() {
		assertLevel("ab12!@A", PasswordLevel.NORMAL);
		assertLevel("Ab12!c", PasswordLevel.NORMAL);
	}

	@Test
	void 숫자포함조건만_불만족하는_경우() {
		assertLevel("ab!@ABqwer", PasswordLevel.NORMAL);
	}

	@Test
	void 대문자를_포함하지_않는_경우() {
		assertLevel("ab12!@df", PasswordLevel.NORMAL);
	}

	@Test
	void 길이만_8글자_이상인_경우() {
		assertLevel("abcdefgh", PasswordLevel.WEAK);
	}

	@Test
	void 숫자포함조건만_충족하는_경우() {
		assertLevel("12345", PasswordLevel.WEAK);
	}

	@Test
	void 대문자포함조건만_충족하는_경우() {
		assertLevel("ABCDE", PasswordLevel.WEAK);
	}
}
