package com.fhlxc.test;

/**
* @author Xingchao Long
* @date 2020年4月10日 下午3:49:16
* @classname Test
* @description 
*/

public class Test {
    
    void DoFunc(int x, int y, int z) {
        int k = 0, j= 0;
        if (x > 3 && z < 10) {
            k = x * y - 1;
            j = (int) Math.sqrt(k);
        }
        if (x == 4 || y > 5) {
            j = x * y + 10;
        }
        j = j % 3;
        System.out.println(",预期:(j=" + j + ",k=" + k + ")");
    }
    
    public static void main(String[] args) {
        Test test = new Test();
        test.DoFunc(4, 6, 9);
        test.DoFunc(2, 6, 10);
        test.DoFunc(2, 6, 11);
        test.DoFunc(4, 4, 9);
        test.DoFunc(4, 6, 9);
        test.DoFunc(3, 6, 9);
        test.DoFunc(4, 5, 10);
        test.DoFunc(3, 5, 10);
    }

}
