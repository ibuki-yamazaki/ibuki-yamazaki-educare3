import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Combination_Calc_Test {

	@Test
	public void testCombination_NormalCases() {
        assertEquals(20, Combination_Calc.combination(6, 3));
        assertEquals(10, Combination_Calc.combination(5, 2));
        assertEquals(1, Combination_Calc.combination(4, 0));
        assertEquals(1, Combination_Calc.combination(4, 4));
    }

    @Test
    public void testCombination_EdgeCases() {
        assertEquals(1, Combination_Calc.combination(0, 0));
        assertThrows(IllegalArgumentException.class, () -> Combination_Calc.combination(3, -1));
        assertThrows(IllegalArgumentException.class, () -> Combination_Calc.combination(3, 4));
    }
}
