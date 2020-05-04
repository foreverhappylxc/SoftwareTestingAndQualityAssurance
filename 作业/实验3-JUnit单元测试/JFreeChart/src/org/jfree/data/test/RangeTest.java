package org.jfree.data.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.jfree.data.Range;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
* @author Xingchao Long
* @date 2020年4月8日 上午9:24:06
* @classname RangeTest
* @description 
* 1. boolean contains(double value):  Returns the value within the range that is closest to the specified value.
* 2. double getLength(): Returns the length of the range.
* 3. double getLowerBound(): Returns the lower bound for the range.
* 4. double getUpperBound(): Returns the upper bound for the range.
* 5. String toString(): Returns a string representation of this Range.
*/

@RunWith(Parameterized.class)
public class RangeTest {
    
    enum Type {
        contains,
        getLength,
        getLowerBound,
        getUpperBound,
        toString
    }
    
    private Range testRange;
    private Type type;
    private double low;
    private double up;
    private double arg;
    private double anticipated;
    
    @Parameters(name = "{0}: {index}")
    public static Collection<?> data() {
        return Arrays.asList(new Object[][] {
            //当类型为contains时，你四个参数是传入的值，第五个参数是1是true,0是false
            {Type.contains, 1, 2, 1, 1}, //测试整数下边界，返回值为true
            {Type.contains, 1, 2, 2, 1}, //测试整数上边界,返回值为true
            {Type.contains, -1, 2, 1.1, 1}, //测试整数范围内的值,返回true
            {Type.contains, 1.2, 2.1, 1.2, 1}, //测试小数下边界,返回true
            {Type.contains, 1.2, 2.1, 2.1, 1}, //测试小数上边界,返回true
            {Type.contains, 1.2, 2.1, 2.09999999, 1}, //测试小数范围内的值,返回true
            {Type.contains, 1.2, 2.1, 2.100000000001, 0}, //测试小数范围上边界外(接近边界)的值,返回false
            {Type.contains, 1.2, 2.1, 1.199999999999, 0}, //测试小数范围下边界外(接近边界)的值,返回false
            {Type.contains, -3.2, -1.6, -1.699999, 1}, //测试负小数范围上边界的值(接近边界),返回true
            {Type.contains, -3.2, -1.6, -2, 1}, //测试负小数范围内的值,返回true
            {Type.contains, -3.2, -1.6, -3.199999, 1}, //测试负小数范围下边界的值(接近边界),返回true
            {Type.contains, -3.2, -1.6, -4, 0}, //测试负小数范围外的值(下边界),返回false
            {Type.contains, -3.2, -1.6, -1, 0}, //测试负小数范围外的值(上边界),返回false
            
            //当类型为getLength时,传入的第四个参数无意义
            {Type.getLength, 1.1, 2.1, 0, 1}, //测试范围为整数的情况,返回1
            {Type.getLength, 13, 16.8, 0, 3.8}, //测试范围为小数的情况,返回3.8
            {Type.getLength, 16.8, 16.8, 0, 0}, //测试范围为0的情况,返回0
            {Type.getLength, 16.00001, 16.0001, 0, 0.00009}, //测试多小数位,上下边界相近的情况,返回0.00009
            {Type.getLength, 41.798461, 56.461283794, 0, 14.662822794}, //测试更多小数位的情况,返回14.662822794
            {Type.getLength, -41.798461, 56.461283794, 0, 98.259744794}, //测试含负数的情况,返回98.259744794
            {Type.getLength, -41.798461, -33.461283794, 0, 8.337177206}, //测试边界都是负数的情况,返回8.337177206
            
            //当类型为getLowerBound时,传入的第四个参数无意义
            {Type.getLowerBound, 45.2, 50.3, 0, 45.2}, //测试正小数的下边界,返回45.2
            {Type.getLowerBound, -45.000002, 50.3, 0, -45.000002}, //测试负小数的下边界,返回-45.000002
            {Type.getLowerBound, 0.000001, 50.3, 0, 0.000001}, //测试正小数的下边界,返回0.000001
            {Type.getLowerBound, -2, 50.3, 0, -2}, //测试负整数的下边界,返回-2
            {Type.getLowerBound, 3, 50.3, 0, 3}, //测试正整数的下边界.返回3
            
            //当类型为getUpperBound时,传入的第四个参数无意义
            {Type.getUpperBound, 45.2, 50.3, 0, 50.3}, //测试上边界小数的情况,返回50.3
            {Type.getUpperBound, -45.2, -0.3, 0, -0.3}, //测试上边界负小数的情况,返回-0.3
            {Type.getUpperBound, -3, -2.776756767, 0, -2.776756767}, //测试上边界多位小数情况,返回-2.776756767
            {Type.getUpperBound, 0, 0, 0, 0}, //测试上下边界都相等的情况,返回0
            
            //当类型为toString时,传入的第四五个参数无意义
            {Type.toString, 23.1, 24, 0, 0}, //测试都为正数,但是不相等的情况,返回Range[23.1,24]
            {Type.toString, -41.798461, 56.461283794, 0, 0}, //测试下边界为负数的情况,返回Range[-41.798461,56.461283794]
            {Type.toString, 16.8, 16.8, 0, 0}, //测试上下边界相等的情况,返回Range[16.8,16.8]
            {Type.toString, -16.8, -16, 0, 0}, //测试都为负数的情况,返回Range[-16.8,-16]
        });
    }
    
    public RangeTest(Type type, double low, double up, double arg, double anticipated) {
        this.type = type;
        this.low = low;
        this.up = up;
        this.arg = arg;
        this.anticipated = anticipated;
    }
    
    @Before
    public void setUp() throws Exception {
        testRange = new Range(low, up);
    }

    @After
    public void tearDown() throws Exception {
        testRange = null;
    }

    @Test
    public void testContains() {
        Assume.assumeTrue(type == Type.contains);
        if (anticipated == 0) {
            assertFalse("Range[" + low + "," + up + "]",testRange.contains(arg));
        } else {
            assertTrue("Range[" + low + "," + up + "]",testRange.contains(arg));
        }
    }
    
    @Test
    public void testGetLength() {
        Assume.assumeTrue(type == Type.getLength);
        assertEquals("Range[" + low + "," + up + "]",anticipated, testRange.getLength(), 0.0000001d);
    }
    
    @Test
    public void testGetLowerBound() {
        Assume.assumeTrue(type == Type.getLowerBound);
        assertEquals("Range[" + low + "," + up + "]",anticipated, testRange.getLowerBound(), 0.0000001d);
        return;
    }
    
    @Test
    public void testGetUpperBound() {
        Assume.assumeTrue(type == Type.getUpperBound);
        assertEquals("Range[" + low + "," + up + "]",anticipated, testRange.getUpperBound(), 0.0000001d);
    }
    
    @Test
    public void testToString() {
        Assume.assumeTrue(type == Type.toString);
        String string = "Range["+ low + "," + up + "]";
        assertEquals("Range[" + low + "," + up + "]",string, testRange.toString());
    }

}
