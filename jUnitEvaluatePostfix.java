import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class jUnitEvaluatePostfix {

	@Test
	void EvaluateTest() {
		String postfix = "23*42-/56*+";
		double answer = ArrayStackTest.evaluatePostfix(postfix);
		assertEquals(answer, 33.0);
	}

}
