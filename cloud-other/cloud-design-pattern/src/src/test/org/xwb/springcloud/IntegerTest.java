package org.xwb.springcloud;

import org.junit.Test;

/**
 * @description
 */
public class IntegerTest {
    public static void main(String[] args) {
        //如果Integer.valueOf(x) x在 -128 --- 127之间，使用的是享元模式
        // 如果不在范围内，则仍然new一个新对象

        //小结：
        //1、在valueOf方法中，先判断值是否在IntegerCache中存在，如果不在，则新创建新的对象，否则直接从cache缓存池中取出返回，
        //2、valueOf方法， 就使用到了享元模式
        //3、如果使用到了valueOf方法得到一个Integer实例，范围在-128 - 127，直行速度比new快
        Integer x = Integer.valueOf(127);
        Integer y = new Integer(127);
        Integer z = Integer.valueOf(127);
        Integer w = new Integer(127);
        System.out.println(x.equals(y));  //true
        System.out.println(x == y);//false
        System.out.println(x == z);//true
        System.out.println(w == x);//false
        System.out.println(w == y);//false
        System.out.println("=============================");
        Integer x1 = Integer.valueOf(200);
        Integer y1 = Integer.valueOf(200);
        System.out.println(x1==y1);//false
    }
    @Test
    public void test1(){

        int[] arr=new int[]{1,5,6,0,7,4,9,3};
        int[] index =new int[]{0,1,2,3,4,0,5,1,2,6,7};
        String tel="";
        for(int i:index){
            tel+=arr[i];
        }
        System.out.println(tel);
    }
}
