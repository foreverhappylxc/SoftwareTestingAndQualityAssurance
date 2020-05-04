package com.fhlxc.hello;

/**
* @author Xingchao Long
* @date 2020年4月22日 下午1:58:02
* @classname HelloWorld
* @description 
*/

public class HelloWorld {

    public static void main(String[] args) {
        int rand = (int) (Math.random() * 100);
        new HelloWorld();
        
        if (rand % 2 == 0) {
            System.out.println("Hello, world! 0");
        } else {
            System.out.println("Hello, world! 1");
        }
        int result = rand % 2 == 0 ? rand + rand : rand * rand;
        System.out.println(result);
    }

}
