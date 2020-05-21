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
        Empty
    }
    static List<List<Integer[]>> list = new ArrayList<List<Integer[]>>();
    
    static {
        list.add(null);
        List<Integer[]> tmp = new ArrayList<Integer[]>();
        tmp.add(new Integer[] {});
    }
    
    @Parameters
    public static Collection<?> data() {
        return Arrays.asList(new Object[][] {
            {Type.Empty, 10, 20, 1}
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
        default ->
            throw new IllegalArgumentException("Unexpected value: " + type);
        }
    }

    @After
    public void tearDown() throws Exception {
        myCalendar = null;
    }

    @Test
    public void testBook() {
        if (anti == 1) {
            assertTrue(myCalendar.book(start, end));
        } else {
            assertFalse(myCalendar.book(start, end));
        }
    }

}
