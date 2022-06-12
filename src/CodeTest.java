import javax.tools.Diagnostic;
import java.util.*;

public class CodeTest {
    public static void swap(int[] arr, int i, int j){
        int mid = arr[i];
        arr[i]=arr[j];
        arr[j]=mid;
    }
    public static void bubbleSort(int[] arr){
        int lastExchangeIndex = 0;
        int sortBorder = arr.length - 1;
        for(int i=0;i<arr.length;i++){
            boolean isSorted = true;
            for (int j = 0;j<sortBorder;j++){
                if(arr[j]>arr[j+1]){
                    lastExchangeIndex = j;
                    isSorted = false;
                    swap(arr,j,j+1);
                }
            }
            sortBorder = lastExchangeIndex;
            if(isSorted){
                break;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
    public static void selectSort(int[]arr){
        for(int i=0;i<arr.length;i++){
            int min = i;
            for(int j=i+1;j< arr.length;j++){
                if(arr[min]>arr[j]){
                    min = j;
                }
            }
            swap(arr,min,i);
        }
    }
    public static void insertSort(int[]arr){
        for(int i=1;i< arr.length;i++){
            int put = arr[i];
            int j =i-1;
            while(j>=0&&put<arr[j]){
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j+1]=put;
        }
    }
    public static void mergeSort(int[]arr,int left,int right){
        if(left==right){
            return;
        }
        int mid = left+(right-left>>1);
//      分成两部分
        mergeSort(arr,left,mid);
        mergeSort(arr,mid+1,right);
//      合并两部分
        // 定义 3 个指针来辅助合并
        // 指向左边序列的起始位置
        int l=left;
        // 指向右边序列的起始位置
        int r=mid+1;
        // 指向辅助数组的起始位置
        int tempIndex=0;
        // （一）
        // 先把左右两边（有序）的数据按照规则填充到temp数组
        // 直到左右两边的有序序列，有一边处理完毕为止
        int[] temp = new int[right-left+1];
        while(l<=mid&&r<=right){
            // 如果左边的有序序列的当前元素小于等于右边有序序列的当前元素
            // 即将左边的当前元素，拷贝到temp数组
            temp[tempIndex++]=arr[l]<arr[r]?arr[l++]:arr[r++];
        }
        // （二）
        // 把有剩余数据的一边的数据依次全部填充到temp
        while (l<=mid){
            temp[tempIndex++]=arr[l++];
        }
        while (r<=right){
            temp[tempIndex++]=arr[r++];
        }
        // （三）
        // 将temp数组的元素拷贝到arr
        for(int i=left;i<=right;i++){
            arr[i]=temp[i-left];
        }
    }
    public static void quickSort(int[]arr,int left,int right) {
        if(left>right){
            return;
        }
        int l = left;
        int r = right;
//      产生一个left~right随机数作为基准,并将其放在数组首位
        int ri = (int)(Math.random()*(r-l+1)+l);
        System.out.println(ri);
        swap(arr,ri,l);
        int privot = arr[l];
        while(l<r){
            while(l<r&&arr[r]>privot){
                r--;
            }
            if (l < r) {
                arr[l]=arr[r];
            }
            while (l<r&&arr[l]<=privot){
                l++;
            }
            if (l < r) {
                arr[r]=arr[l];
            }
        }
        arr[r]=privot;
        quickSort(arr,left,l-1);
        quickSort(arr,l+1,right);
    }
    public static void shellSort(int[] arr, int length) {
        for(int gap = length / 2;gap>0;gap=gap/2){
            for(int i=gap;i< length;i++){
                int j = i;
                while(j-gap>=0&&arr[j]<arr[j-gap]){
                    swap(arr,j,j-gap);
                    j=j-gap;
                }
            }
        }

    }
    public static void heapSort(int[]arr){
//      数组为空或数组长度小于二不需要排序
        if(arr==null||arr.length<2){
            return;
        }
//      构建大根堆
        for(int i=1;i<arr.length;i++){
            heapInsert(arr,i);
        }
       //生成大根堆的另一种写法
//        for(int i=arr.length-1;i>=0;i--){
//            heapIfy(arr,i,arr.length);//生成大根堆
//        }
        int heapSize = arr.length;
//      将最大的数和最后的数交换,并将大根堆长度减去1
        while (heapSize>0){
            swap(arr,0,--heapSize);
            heapIfy(arr,0,heapSize);
        }
    }
//    此方法构建大根堆,节点向下沉
//    堆结构就两个最基本的操作 heapInsert heapIfy
    public static void heapIfy(int[]arr, int index,int heapSize){
        int left = index*2+1;
        //下方还有孩子的时候
        while(left<heapSize){
            //两个孩子中谁的值大把下标给largest
            int largest=left+1<heapSize&&arr[left+1]>arr[left]?left+1:left;
            //比较父是否大于子孩子,如果不大于结束循环
            if(arr[largest]>arr[index]){
                swap(arr,largest,index);
                index = largest;
                left = index*2+1;
            }else{
                break;
            }
        }
    }
//    插入一个数到数组末尾，并向上浮
    public static void heapInsert(int[] arr,int index){
/*     小根堆，加入一个数到末尾根节点并形成小根堆
        while(arr[index]<arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index =(index-1)/2;
        }
        */
//      大根堆，加入一个数到末尾根节点并形成大根堆
        while(arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index =(index-1)/2;
        }
    }
    public static void countSort(int[]arr){
        if(arr==null||arr.length<2){
            return;
        }
        int max = arr[0];
        int min = arr[0];
        for(int i:arr){
            max = Math.max(max,i);
            min = Math.min(min,i);
        }
        int[]newarr = new int[max-min+1];
        for(int i:arr){
            newarr[i-min]+=1;
        }
        int j=0;
        for(int i=0;i<newarr.length;i++){
            while (newarr[i]>0){
                arr[j++]=i+min;
                newarr[i]--;
            }
        }
    }
    public static void bucketSort(int[]nums){
//        假设桶的容量为5
        int bucketSize = 5;
        int min = nums[0];
        int max = nums[0];
        for(int i=1;i<nums.length;i++){
            min = Math.min(min,nums[i]);
            max = Math.max(max,nums[i]);
        }
//        计算需要多少个桶
        int bucketCount =(int) Math.ceil(nums.length*1.0/bucketSize);
//        int bucketCount = (int)Math.ceil((max-min+1)*1.0/bucketSize);
        ArrayList<LinkedList<Integer>> buckets = new ArrayList<>();
//      将桶装进ArrayList集合中
        for(int i=0;i<bucketCount;i++){
            buckets.add(new LinkedList<>());
        }
//       将输入的数据放入对应的桶中并完成排序
        for(int i:nums){
//           计算该桶应该放在第几个桶中
//            int index = (i-min)/(max-min+1)*bucketCount;
            int index = (i-min)/ nums.length*bucketCount;
            insertBucketSort(buckets.get(index),i);
        }
        // 将桶中元素全部取出来并放入 nums 中
        int index = 0;
        for(LinkedList<Integer> bucket:buckets){
            for(int data:bucket){
                nums[index++]=data;
            }
        }
    }

    private static void insertBucketSort(LinkedList<Integer> bucket, int data) {
        // 记录是否已经插过值
        boolean insertFlag = true;
        ListIterator<Integer> listIterator = bucket.listIterator();
        while(listIterator.hasNext()){
            if(data<=listIterator.next()){
                listIterator.previous();
                listIterator.add(data);
                insertFlag = false;
                break;
            }
        }
        // 如果没有在中间找到合适的位置，插到末尾
        if(insertFlag){
            listIterator.add(data);
        }

    }
    public static void radixSort(int[]nums){
//      拿到最大的数字有多少位
        int count = getMaxDigitCount(nums);
//      对每一位都进行桶排序
        for (int i=0;i<count;i++){
            radix_bucketSort(nums,i);
        }
    }

    private static int getMaxDigitCount(int[] nums) {
        int count = 1;
        int base = 10;
        for(int num:nums){
            while(base<num){
                count++;
                base*=10;
            }
        }
        return count;
    }

    private static void radix_bucketSort(int[] nums, int digit) {
        int base = (int)Math.pow(10,digit);
        ArrayList<LinkedList<Integer>> buckets = new ArrayList<>();
        // 数字范围明显是0~9，所以准备10个桶
        for (int i = 0; i < 10; i++) {
            buckets.add(new LinkedList<Integer>());
        }
        for (int num : nums) {
            // 计算出当前位上的数是多少
            // 随着 base 的增大，数位从低向高移动
            int index = num / base % 10;
            buckets.get(index).add(num);
        }
        // 将桶中的数拷贝回原数组
        int index = 0;
        for (LinkedList<Integer> bucket : buckets) {
            for (int i : bucket) {
                nums[index++] = i;
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,8,7,5,6,3,4,2,1,0};
        System.out.println("jaja");
        bucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
