import java.util.*;
import java.io.*;

public class Solution {
    public static class MaxHeap{
        int size;
        int[] arr;
        MaxHeap(int[] arr, int size){
            this.arr = arr;
            this.size = size;
        }
    }
    
    public static void printArr(int[] arr){
        for(int i=0; i<arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static void insertion_sort(int[] arr){
        for(int i=1; i<arr.length; i++){
            int key = arr[i];
            int j = i-1;
            while(j>=0 && arr[j] > key){
                arr[j+1] = arr[j];
                j -= 1;
            }
            arr[j+1] = key;
        }
    }
    
    public static void selection_sort(int[] arr) {
        for(int i=0; i<arr.length-1; i++){
            int min_idx = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[j] < arr[min_idx]) min_idx = j;
            }
            swap(arr, i, min_idx);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int i = low-1;
        int pivot = arr[high];
        for(int j=low; j<high; j++){
            if(arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i+1, high);
        return i+1;
    }
    
    private static void quick_sort_helper(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quick_sort_helper(arr, low, pi-1);
            quick_sort_helper(arr, pi+1, high);
        }
    }
    
    public static void quick_sort(int[] arr) {
        quick_sort_helper(arr, 0, arr.length-1);
    }
    
    private static void merge(int[] arr, int low, int mid, int high){
        int[] temp = new int[high-low+1];
        int i = low, j=mid+1, k=0;
        while(i<=mid && j<=high) {
            if(arr[i] <= arr[j]) temp[k++]=arr[i++];
            else temp[k++]=arr[j++];
        }
        
        while(i<=mid) temp[k++] = arr[i++];
        while(j<=high) temp[k++] = arr[j++];
        
        for(i=0; i<k; i++) arr[low+i] = temp[i];
    }
    
    private static void merge_sort_helper(int[] arr, int low, int high) {
        if(low < high){
            int mid = low + (high-low)/2;
            merge_sort_helper(arr, low, mid);
            merge_sort_helper(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }
    
    public static void merge_sort(int[] arr){
        merge_sort_helper(arr, 0, arr.length-1);
    }
    
    private static void max_heapify(MaxHeap heap, int idx){
        int largest = idx;
        int left = (idx<<1) + 1;
        int right = (idx+1) << 1;
        if(left < heap.size && heap.arr[left] > heap.arr[largest]) largest = left;
        if(right < heap.size && heap.arr[right] > heap.arr[largest]) largest = right;
        
        if(idx != largest){
            swap(heap.arr, idx, largest);
            max_heapify(heap, largest);
        }
    }
    
    private static MaxHeap create_and_build_heap(int[] arr){
        MaxHeap heap = new MaxHeap(arr, arr.length);
        for(int i=(heap.size-2)/2; i>=0; i--) max_heapify(heap, i);
        return heap;
    }
    
    public static void heap_sort(int[] arr){
        MaxHeap heap = create_and_build_heap(arr);
        while(heap.size > 1){
            swap(heap.arr, 0, heap.size-1);
            heap.size -= 1;
            max_heapify(heap, 0);
        }
    }
  
    public static void main(String[] args) {
        int[] arr = {6,2,1,5,8,7};
        System.out.print("Input array: ");
        printArr(arr);
        
        insertion_sort(arr);
        System.out.print("Inserstion sort: ");
        printArr(arr);
        
        arr = new int[]{6,2,1,5,8,7};
        selection_sort(arr);
        System.out.print("Selction sort: ");
        printArr(arr);
        
        arr = new int[]{6,2,1,5,8,7};
        quick_sort(arr);
        System.out.print("Quick sort: ");
        printArr(arr);
        
        arr = new int[]{6,2,1,5,8,7};
        merge_sort(arr);
        System.out.print("Merge sort: ");
        printArr(arr);
        
        arr = new int[]{6,2,1,5,8,7};
        heap_sort(arr);
        System.out.print("Heap sort: ");
        printArr(arr);
    }
}
