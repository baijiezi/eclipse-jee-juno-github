package Algorithms.sort;

//  选择排序是常用内部排序的一种，常见的实现算法有直接选择排序算法和堆排序算法，选择排序的基本思想是每次从待排数据中选择第n小的数据放到排序列表的第n个位置，假如共有N个数据待排，那么经过N-1次排序后，待排数据就已经按照从小到大的顺序排列了。
//  直接选择排序算法的思想比较简单：（假设数据放在一个数组a中，且数组的长度是N）
//  1：从a[0]-a[N-1]中选出最小的数据，然后与a[0]交换位置
//  2：从a[1]-a[N-1]中选出最小的数据，然后与a[1]交换位置（第1步结束后a[0]就是N个数的最小值）
//  3：从a[2]-a[N-1]中选出最小的数据，然后与a[2]交换位置（第2步结束后a[1]就是N-1个数的最小值）
//  以此类推，N-1次排序后，待排数据就已经按照从小到大的顺序排列了。
//  直接选择排序的java实现如下：

public class SelectionSort {
	
	public static void selectionSort(int[] elements){
        for(int i = 0; i < elements.length-1; ++i){
            int k = i;
            for(int j = i; j < elements.length; ++j){
                if(elements[k] > elements[j]){
                    k = j;
                }
            }
            if(k != i){//交换元素
                int temp = elements[i];
                elements[i] = elements[k];
                elements[k] = temp;
            }
            
            System.out.print("第" + (i + 1) + "次排序结果：");
            for(int a = 0; a < elements.length; a++){
                System.out.print(elements[a] + "\t");
            }
            System.out.println("");
        }
	}
	public static void main(String[] args){
		int score[] = {67, 65, 75, 87, 98, 90, 99, 100};
		SelectionSort selectionSort = new SelectionSort();
		selectionSort.selectionSort(score);
	}
	
	
	
}
