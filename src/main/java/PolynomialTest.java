import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PolynomialTest {

    private PolynomyalStringCalculator calculator = new PolynomyalStringCalculator();

    @Test
    public void testAddPolynomials() {
        String result = calculator.addPolynomials("2x^2 +3x +1", "x^2 -2x +5");
        assertEquals(" + 3.0x^2 + x + 6.0", result);
    }

    @Test
    public void testSubtractPolynomials() {
        String result = calculator.subtractPolynomials("2x^2 +3x +1", "x^2 - 2x +5");
        assertEquals(" + x^2 + 5.0x - 4.0", result);
    }

    @Test
    public void testMultiplyPolynomials() {
        String result = calculator.multiplyPolynomials("2x^2 +3x +1", "x^2 -2x +5");
        assertEquals(" + 2.0x^4 - x^3 + 5.0x^2 + 13.0x + 5.0", result);
    }

    @Test
    public void testComputeDerivative() {
        String result = calculator.computeDerivative("2x^3 +3x^2 - x +5");
        assertEquals(" + 6.0x^2 + 6.0x - 1.0", result);
    }

    @Test
    public void testComputeIntegral() {
        String result = calculator.computeIntegral("2x^3 +3x^2 - x +5");
        assertEquals(" + 0.5x^4 + x^3 - 0.5x^2 + 5.0x", result);
    }
    @Test
    public void testDividePolynomials() {
        PolynomyalStringCalculator calculator = new PolynomyalStringCalculator();
        String result = calculator.computeDivision("4x^3 + 2x^2 - 6x + 3", "2x - 1");
        assertEquals("quotient : + 2.0x^2 + 2.0x - 2.0  remainder : + 1.0", result);
    }

}