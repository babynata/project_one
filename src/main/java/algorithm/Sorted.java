package algorithm;

public class Sorted {

    public static int[] bubbleSorted(int[] a) {
        for (int j = 0; j < a.length; j++) {
            for (int i = a.length - 1; i > j; i--) {
                if (a[i] < a[i - 1]) {
                    int temp = a[i];
                    a[i] = a[i - 1];
                    a[i - 1] = temp;
                }
            }
        }
        return a;
    }

    public static int[] selectSorted(int[] a) {
        int index = 0;
        int min = 0;
        for (int j = 0; j < a.length; j++) {
            min = a[j];
            for (int i = j + 1; i < a.length; i++) {
                if (min > a[i]) {
                    min = a[i];
                    index = i;
                }
            }
            if (min != a[j]) {
                int temp = a[j];
                a[j] = min;
                a[index] = temp;
            }
        }

        return a;
    }

    public static int[] insertSorted(int[] a) {
        for (int j = 1; j < a.length; j++) {
            int left = a[j];
            int index = j;
            for (int i = j - 1; i >= 0; i--) {
                if (left < a[i]) {
                    int temp = a[i];
                    a[index] = temp;
                    a[i] = left;
                    index = i;
                }
            }
        }
        return a;
    }

    public static void divided(int[] a,int[] left,int[] right) {

        if (a.length > 1) {
            int leftCut = a.length % 2 == 0 ? a.length / 2 : (a.length - 1) / 2;
            int rightCut = a.length % 2 == 0 ? leftCut : leftCut + 1;

            for (int i = 0, j = leftCut; j < a.length && i < leftCut; i++, j++) {
                left[i] = a[i];
                right[i] = a[j];
            }
            if (leftCut != rightCut) {
                right[rightCut - 1] = a[a.length - 1];
            }

//            printArray(left);
//            printArray(right);

//            divided(left);
//            divided(right);

        }

        merge(left,right);


    }

    public static void merge(int[] left, int[] right) {
        int len = left.length + right.length;
        int[] merge = new int[len];
        int loop = left.length;
        int index = 0;
        for(int i=0;i<loop;i++) {
            if (left[i] < right[i]) {
                merge[index] = left[i];
                merge[++index] = right[i];
                ++index;
            }
        }
        if (left.length != right.length) {
            merge[len - 1] = right[right.length - 1];
        }

        printArray(merge);
    }

    public static void printArray(int[] a) {
        for (int i : a) {
            System.out.print(i);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a = {4, 8, 3, 9, 1, 7, 6, 2, 5};
//        printArray(bubbleSorted(a));
//        printArray(selectSorted(a));
//        printArray(insertSorted(a));
//        divided(a);
    }
}
