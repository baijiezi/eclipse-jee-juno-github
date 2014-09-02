package Algorithms.sort;

//  ѡ�������ǳ����ڲ������һ�֣�������ʵ���㷨��ֱ��ѡ�������㷨�Ͷ������㷨��ѡ������Ļ���˼����ÿ�δӴ���������ѡ���nС�����ݷŵ������б�ĵ�n��λ�ã����繲��N�����ݴ��ţ���ô����N-1������󣬴������ݾ��Ѿ����մ�С�����˳�������ˡ�
//  ֱ��ѡ�������㷨��˼��Ƚϼ򵥣����������ݷ���һ������a�У�������ĳ�����N��
//  1����a[0]-a[N-1]��ѡ����С�����ݣ�Ȼ����a[0]����λ��
//  2����a[1]-a[N-1]��ѡ����С�����ݣ�Ȼ����a[1]����λ�ã���1��������a[0]����N��������Сֵ��
//  3����a[2]-a[N-1]��ѡ����С�����ݣ�Ȼ����a[2]����λ�ã���2��������a[1]����N-1��������Сֵ��
//  �Դ����ƣ�N-1������󣬴������ݾ��Ѿ����մ�С�����˳�������ˡ�
//  ֱ��ѡ�������javaʵ�����£�

public class SelectionSort {
	
	public static void selectionSort(int[] elements){
        for(int i = 0; i < elements.length-1; ++i){
            int k = i;
            for(int j = i; j < elements.length; ++j){
                if(elements[k] > elements[j]){
                    k = j;
                }
            }
            if(k != i){//����Ԫ��
                int temp = elements[i];
                elements[i] = elements[k];
                elements[k] = temp;
            }
            
            System.out.print("��" + (i + 1) + "����������");
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
