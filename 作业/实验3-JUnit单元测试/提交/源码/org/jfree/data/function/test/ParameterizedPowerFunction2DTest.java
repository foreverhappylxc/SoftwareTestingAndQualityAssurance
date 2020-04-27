package org.jfree.data.function.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.jfree.data.function.PowerFunction2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
* @author Xingchao Long
* @date 2020年4月8日 下午6:02:15
* @classname ParameterizedPowerFunction2DTest
* @description 
* PowerFunction2D: A function of the form y = a * x ^ b. 
*     double getValue(double x): Returns the value of the function for a given input ('x').
*/

@RunWith(Parameterized.class)
public class ParameterizedPowerFunction2DTest {
    
    private PowerFunction2D powerFunction2D;
    private double a;
    private double b;
    private double x;
    private double anticipated;

    @Parameters(name = "{index}")
    public static Collection<?> data() {
        return Arrays.asList(new Object[][] {
            {5, 0, 2, 5}, //测试b为0,返回5
            {0, 3, 2, 0}, //测试a为0,返回0
            {3.68328, 2, 3, 33.14952}, //测试a为小数, 返回33.14952
            {-3, 0.2, 3, -3.7371928}, //测试b为小数,a为负数,返回-3.7371928
            {3, -1, 3, 1}, //测试为约分刚好为整数,返回1
            {3, -0.232445, 3, 2.3238966}, //测试指数为负小数,返回2.3238966
        });
    }
    
    public ParameterizedPowerFunction2DTest(double a, double b, double x, double anticipated) {
        this.a = a;
        this.b = b;
        this.x = x;
        this.anticipated = anticipated;
    }
    
    @Before
    public void setUp() throws Exception {
        powerFunction2D = new PowerFunction2D(a, b);
    }

    @After
    public void tearDown() throws Exception {
        powerFunction2D = null;
    }

    @Test
    public void testGetValue() {
        assertEquals("", anticipated, powerFunction2D.getValue(x), 0.0000001d);
    }

}
