package concurrentUtil;

import java.util.ArrayList;
import java.util.List;

public class BubbleSort {

    public static void main(String[] args) {
         Integer[] list = new Integer[]{1,4,6,7,2,8};


         for (int i = 0; i < list.length - 1; i++) {
             for (int j = 0; j < list.length - 1 - i; j++) {
                 if (list[j] < list[j+1]) {
                     int temp = list[j];
                     list[j] = list[j + 1];
                     list[j + 1] = temp;
                 }
             }
         }
         for (int i = 0; i < list.length; i++) {
             System.out.println(list[i]);
         }

    }

//    class Node {
//        int val;
//
//        Node next;
//    }
}
