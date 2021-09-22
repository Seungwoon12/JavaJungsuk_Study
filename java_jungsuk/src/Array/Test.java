package Array;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};

        for (int i = 0; i < arr.length; i++) {
            System.out.println("arr[i] = " + arr[i]);
        }


//        int[] score;
//        score = new int[] {50, 60, 70, 80, 90};

//        int[] score = new int[0]; // 길이가 0인 배열
//        int[] score = new int[]{}; 길이가 0인 배열
//        int[] score = {};  길이가 0인 배열

        String[] strArr = {"철수", "영희", "짱구"};

        System.out.println("strArr = " + Arrays.toString(strArr));

    }

}
