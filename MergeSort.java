package org.example;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MergeSort {
    public static void main(String args[])
    {
        long startTime = System.currentTimeMillis();
        int[] arr = {45,33,12,11,23,46,78};
        mergeSort(arr);

        for(int j = 0; j < arr.length; j++){
            System.out.print(arr[j]+ " ");
        }
        long endTime = System.currentTimeMillis();
        long exeTime = endTime - startTime;
        System.out.println("Time taken : " + exeTime);
    }
    private static void mergeSort(int[] array) {

        int length = array.length;
        if (length <= 1) return; //base case

        int middle = length / 2;
        int[] leftArray = new int[middle];
        int[] rightArray = new int[length - middle];

        int i = 0; //left array
        int j = 0; //right array

        for(; i < length; i++) {
            if(i < middle) {
                leftArray[i] = array[i];
            }
            else {
                rightArray[j] = array[i];
                j++;
            }
        }
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(()->mergeSort(leftArray));
        executor.submit(()->mergeSort(rightArray));
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
        merge(leftArray, rightArray, array);
    }

    private static void merge(int[] leftArray, int[] rightArray, int[] array) {

        int leftSize = array.length / 2;
        int rightSize = array.length - leftSize;
        int i = 0, l = 0, r = 0;

        //conditions for merging
        while(l < leftSize && r < rightSize) {
            if(leftArray[l] < rightArray[r]) {
                array[i] = leftArray[l];
                i++;
                l++;
            }
            else {
                array[i] = rightArray[r];
                i++;
                r++;
            }
        }
        while(l < leftSize) {
            array[i] = leftArray[l];
            i++;
            l++;
        }
        while(r < rightSize) {
            array[i] = rightArray[r];
            i++;
            r++;
        }
    }
}
