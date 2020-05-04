package org.jfree.data.function.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.jfree.data.function.LineFunction2D;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
* @author Xingchao Long
* @date 2020年4月8日 上午9:49:17
* @classname LineFunction2DTest
* @description 
* LineFunction2D: A function in the form y = a + bx. 
*     double getValue(double x): Returns the function value.
*/

@RunWith(Parameterized.class)
public class ParameterizedLineFunction2DTest {
    
    private LineFunction2D lineFunction2D;
    private double a;
    private double b;
    private double x;
    private double anticipated;
    
    @Parameters(name = "{index}")
    public static Collection<?> data() {
        return Arrays.asList(new Object[][] {
            {1, 1, 2, 3}, //测试为整数,返回3
            {0, 0, 2354, 0}, //测试为0,返回0
            {1.3453, -5.263718, 2, -9.182136}, //测试为小数,返回-9.182136
        });
    }
    
    public ParameterizedLineFunction2DTest(double a, double b, double x, double anticipated) {
        this.a = a;
        this.b = b;
        this.x = x;
        this.anticipated = anticipated;
    }
    
    @Before
    public void setUp() throws Exception {
        lineFunction2D = new LineFunction2D(a, b);
    }

    @After
    public void tearDown() throws Exception {
        lineFunction2D = null;
    }

    @Test
    public void testGetValue() {
        assertEquals("", anticipated, lineFunction2D.getValue(x), 0.0000001d);
    }

}
