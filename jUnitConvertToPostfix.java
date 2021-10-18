import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * jUnit test to check the ConvertToPostfix method
 */
class jUnitConvertToPostfix {

	@Test
	void CovertTest() {
		String infix = "a*b/(c-a)+d*e";
		String Postfix = LinkedStackTest.convertToPostfix(infix);
		assertEquals("ab*ca-/de*+", Postfix);
	}

}
