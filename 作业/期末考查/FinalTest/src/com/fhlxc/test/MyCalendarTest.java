package com.fhlxc.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import problem.medium.MyCalendar;

/**
* @author Xingchao Long
* @date 2020年5月21日 下午9:52:44
* @classname MyCalendarTest
* @description
* 测试MyCalendar的book方法
*/

@RunWith(Parameterized.class)
public class MyCalendarTest {

    MyCalendar myCalendar;
    int start, end, anti;
    Type type;
    
    enum Type {
        Empty,
        OneSize,
        NonEmpty,
        Error
    }
    static List<List<Integer[]>> list = new ArrayList<List<Integer[]>>();
    
    static {
        list.add(null);
        List<Integer[]> tmp = new ArrayList<Integer[]>();
        tmp.add(new Integer[] {-4, 9});
        list.add(tmp);
        tmp = new ArrayList<Integer[]>();
        tmp.add(new Integer[] {9, 10});
        tmp.add(new Integer[] {-185, -100});
        tmp.add(new Integer[] {-89, 7});
        tmp.add(new Integer[] {13, 13});
        tmp.add(new Integer[] {24, 28});
        list.add(tmp);
    }
    
    @Parameters
    public static Collection<?> data() {
        return Arrays.asList(new Object[][] {
            {Type.Empty, 10, 20, 1}, // 测试空map的时候
            {Type.OneSize, -7, -6, 1}, // 测试位于范围左边
            {Type.OneSize, 10, 12, 1}, // 测试位于范围右边
            {Type.OneSize, -6, -2, 0}, // 测试下边界相交
            {Type.OneSize, 8, 14, 0}, // 测试上边界相交
            {Type.OneSize, -2, 7, 0}, // 测试包含
            {Type.NonEmpty, 10, 12, 1}, // 测试一般值
            {Type.NonEmpty, 89, 88, 0}, // 测试start小于end的情况
            {Type.NonEmpty, 10, 12, 1}, // 测试上边界临界
            {Type.NonEmpty, 8, 9, 1}, // 测试下边界临界
            {Type.NonEmpty, -190, -183, 0}, // 测试下边界相交
            {Type.NonEmpty, 27, 30, 0}, // 测试上边界相交
            {Type.NonEmpty, 25, 26, 0}, // 测试内包含
            {Type.NonEmpty, -200, -90, 0}, // 测试外包含
            {Type.NonEmpty, 13, 13, 1}, // 测试特殊值
            {Type.NonEmpty, -200, -185, 1}, // 使分支覆盖率提升 
            {Type.NonEmpty, -200, -98, 0},
            {Type.NonEmpty, -120, -90, 0},
            {Type.Error, 4, 8, 1}, //为了覆盖率百分百添加的这个用例
        });
    }
    
    public MyCalendarTest(Type type, int start, int end, int anti) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.anti = anti;
    }
    
    private void initMap(List<Integer[]> l, MyCalendar myCalendar) {
        if (l == null) {
            return;
        }
        for (Integer[] se: l) {
            myCalendar.book(se[0], se[1]);
        }
    }
    
    @Before
    public void setUp() throws Exception {
        myCalendar = new MyCalendar();
        switch (type) {
        case Empty -> 
            initMap(list.get(Type.Empty.ordinal()), myCalendar);
        case NonEmpty ->
            initMap(list.get(Type.NonEmpty.ordinal()), myCalendar);
        case OneSize ->
            initMap(list.get(Type.OneSize.ordinal()), myCalendar);
        default ->
            throw new IllegalArgumentException("不符合题目的类型");
        }
    }

    @After
    public void tearDown() throws Exception {
        myCalendar = null;
    }

    @Test
    public void testBook() {
        if (anti == 1) {
            assertTrue("start: " + start + " end: " + end + " 期望值是true，但是输出了false", myCalendar.book(start, end));
        } else {
            assertFalse("start: " + start + " end: " + end + " 期望值是false，但是输出了true", myCalendar.book(start, end));
        }
    }

}
