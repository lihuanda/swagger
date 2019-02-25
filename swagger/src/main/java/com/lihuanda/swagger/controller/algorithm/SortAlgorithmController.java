package com.lihuanda.swagger.controller.algorithm;


import com.baomidou.mybatisplus.plugins.Page;
import com.lihuanda.swagger.config.R;
import com.lihuanda.swagger.dto.user.UserDTO;
import com.lihuanda.swagger.util.JsonResult;
import com.lihuanda.swagger.vo.user.UserVO;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/Algorithm")
@Api(value="AlgorithmController", tags="AlgorithmController")
public class SortAlgorithmController {

    private int[] arr;

    //算法实现之快速排序算法
    @ApiOperation(value = "快速排序算法",notes="算法实现之快速排序算法",httpMethod = "POST")
   /* @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name = "list",dataType = "string",required = false,value="输入排序数")
    })*/
    @RequestMapping(value = "/fastSortAlg" ,method= RequestMethod.POST)
    public JsonResult FastSortAlg(@RequestParam(value = "list")  @ApiParam(value = "id列表") int[] list){

        //快速排序
        /*arr=list;
        int[] result = sort(arr);
        System.out.println(Arrays.toString(result));*/

        //快速排序二
        /*arr=list;
        quicksort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));*/

        //冒泡排序
        /*arr=list;
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));*/

        //选择排序
        /*arr=list;
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));*/

        //插入排序
       /* arr=list;
        insertSort(arr);
        System.out.println(Arrays.toString(arr));*/

       //我自己实现的快速排序算法
        arr=list;
        myQuickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));


        if (arr!=null) {
            return JsonResult.success(arr);
        }else {
            return JsonResult.failure(R.queryFailure);
        }
    }

 //  quicksort算法
    public int[] sort(int[] array) {
        doSort(array, 0, array.length - 1);
        return array;
    }



    private  void doSort(int[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right);
            System.out.println(Arrays.toString(array));
            doSort(array, left, pivot - 1);
            doSort(array, pivot , right);
        }
    }


    private  int partition(int[] array, int left, int right) {
        int mid = (left + right) / 2;
       int pivot = array[mid];

        while(left <= right) {
            while(array[left]<pivot){
                ++left;
            }
            while(pivot<array[right]) {
                --right;
            }
            if(left <= right) {
                swap(array, left, right);
                ++left;
                --right;
            }
        }
        return left;
    }


     private boolean swap(int[] array, int idx, int idy){
        int swap = array[idx];
        array[idx] = array[idy];
        array[idy] = swap;
        return true;
    }


    //另一版本quicksort

    void quicksort(int[] a,int left, int right) {
        int i, j, t, temp;
        if (left > right)
            return;
        temp = a[left]; //temp中存的就是基准数
        i = left;
        j = right;
        while (i != j) { //顺序很重要，要先从右边开始找
            while (a[j] >= temp && i < j)
                j--;
            while (a[i] <= temp && i < j)//再找右边的,i<j,保证j减完之后仍然大于i,才接着让i 减 ,因为i要归位,回到第一位
                i++;
            if (i < j)//交换两个数在数组中的位置
            {
                t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        //最终将基准数归位
        a[left] = a[i];
        a[i] = temp;
        System.out.println(Arrays.toString(arr));
        //下面接着从一开始的基准数作为分界点
        quicksort(a, left, i - 1);//继续处理左边的，这里是一个递归的过程
        quicksort(a, i + 1, right);//继续处理右边的 ，这里是一个递归的过程
    }

    //冒泡排序
    private void bubbleSort(int[] arr){
         int temp;
         for(int i=0;i<arr.length;i++){
             for(int j=0; j<arr.length-i-1;j++){
                 if (arr[j]>arr[j+1]){
                     temp = arr[j];
                     arr[j]=arr[j+1];
                     arr[j+1]=temp;
                 }

             }
         }

    }
    //选择排序
    private void selectionSort(int[] arr){
        int minIndex;
        int  temp;
        for(int i= 0;i<arr.length-1;i++){
            minIndex=i;
            for(int j=i+1 ; j<arr.length;j++){
                if(arr[minIndex]>arr[j])
                    minIndex=j;
            }
            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    //插入排序
    private void  insertSort(int[] arr){
        int  tmp ;
        if (arr.length>1) { //数组个数大于两个才有排序的必要，还有一点就是防止下面for循环数组越界
            for (int i = 1; i<arr.length; i++){
                  tmp =arr[i];
                  if (arr[i]<arr[i-1]){
                      int  j;
                      for (j = i-1;j>=0&&tmp<arr[j];j--){
                          arr[j+1]=arr[j];
                      }
                      arr[j+1]=tmp;
                  }
            }
        }
    }
    //希尔(shell)排序
    public void ShellSort(int arr[], int length) {
        int increasement = length;
        int i, j, k;
        do {
            // 确定分组的增量
            increasement = increasement / 3 + 1;
            for (i = 0; i < increasement; i++) {
                for (j = i + increasement; j < length; j += increasement) {
                    if (arr[j] < arr[j - increasement]) {
                        int temp = arr[j];
                        for (k = j - increasement; k >= 0 && temp < arr[k]; k -= increasement) {
                            arr[k + increasement] = arr[k];
                        }
                        arr[k + increasement] = temp;
                    }
                }
            }
        } while (increasement > 1);
    }


    // 归并排序
    void MergeSort(int arr[], int start, int end, int[]  temp) {//temp  辅助空间
        if (start >= end)
            return;
        int mid = (start + end) / 2;
        MergeSort(arr, start, mid, temp);
        MergeSort(arr, mid + 1, end, temp);

        // 合并两个有序序列
        int length = 0; // 表示辅助空间有多少个元素
        int i_start = start;
        int i_end = mid;
        int j_start = mid + 1;
        int j_end = end;
        while (i_start <= i_end && j_start <= j_end) {
            if (arr[i_start] < arr[j_start]) {
                temp[length] = arr[i_start];
                length++;
                i_start++;
            } else {
                temp[length] = arr[j_start];
                length++;
                j_start++;
            }
        }
        while (i_start <= i_end) {
            temp[length] = arr[i_start];
            i_start++;
            length++;
        }
        while (j_start <= j_end) {
            temp[length] = arr[j_start];
            length++;
            j_start++;
        }
        // 把辅助空间的数据放到原空间
        for (int i = 0; i < length; i++) {
            arr[start + i] = temp[i];
        }

    }

    //堆排序


    //自己写个快速排序
    private  void myQuickSort(int[] arr,int left  ,int  right){

        int l = left ;
        int r = right ;
        if (l >= r) //给出结束递归的条件
            return;
        int mid = (l+r)/2;
        int pivot = arr[mid];
        int tmp ;
        System.out.println("l==="+l+"r===="+r+"mid=="+mid+"pivot===="+pivot);
        System.out.println("前--------"+Arrays.toString(arr));
        while(l<=r){
            while(arr[l]<pivot){
                ++l;
            }
            while (arr[r]>pivot){
                --r;
            }
            if(l<=r) {
                tmp = arr[l];
                arr[l] = arr[r];
                arr[r] = tmp;
                ++l;
                --r;
                System.out.println("变换-----"+Arrays.toString(arr));
            }
        }
        System.out.println("后-----"+Arrays.toString(arr));
            myQuickSort(arr, 0, l - 1);
            myQuickSort(arr, l, right);  //right 不一定是数组的最后一个元素
    }






    }
