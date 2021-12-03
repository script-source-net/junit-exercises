package assertions;

import fakes.assertions.Calculator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class CalculatorTest {

    @Test
    void TestCalculator_1Plus1Is2(){
        Assertions.assertEquals(Calculator.addition(1,1),2);
    }

    @Test
    void TestCalculator_2Mulply2Equals4(){Assertions.assertEquals(Calculator.multiply(2,2),4);}

    @Test
    void TestCalculator_0Multiply4ReturnsException(){Assertions.assertEquals(Calculator.multiply(0,4),0);}
}
