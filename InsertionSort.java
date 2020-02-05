public class InsertionSort {
    public static void main(String[] args) {
        int i , key, j;
        int[] A = {0,18, 26, 6, 9, 15, 12, 5, 20, 11, 30};
        for (j = 2; j<A.length; j++)
        {
            key = A[j];
            i=j-1;

            while (i > 0 && A[i] > key)
            {
                A[i + 1] = A[i];
                i = i - 1;
            }
            A[i + 1] = key;
        }
        for (j = 1; j < A.length; j++) {
            System.out.println(A[j]);
        }
    }
}