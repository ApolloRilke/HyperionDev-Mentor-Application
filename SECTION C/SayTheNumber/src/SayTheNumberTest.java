import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SayTheNumberTest {

	@Test
	void test() {
		assertEquals(SayTheNumber.numberToWords(20), "Twenty.");
		assertEquals(SayTheNumber.numberToWords(0), "Zero.");
		assertEquals(SayTheNumber.numberToWords(11), "Eleven.");
	}

}
