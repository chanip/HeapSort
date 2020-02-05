public class Countingsort {
    public static void main(String[] args)
    {
        int[] A={-99,5,5,0,6,2,0,1,3,2,6,1,4,2,4};
        int[] B = new int[A.length];
        for(int i=1;i<A.length;i++)
            B[i]=9;
        countingsort(A,B,7);
        System.out.println("the sorted array is ");
        for(int i=1;i<A.length;i++)
            System.out.print(+B[i]+" ");
    }
    public static void countingsort(int[] A, int[] B, int k)
    {
        int[] C = new int[k];
        System.out.println("now create C array");
        for (int i=0;i<k;i++)
        {   C[i]=0;
            System.out.print(C[i]+" ");
        }
        System.out.println("");
        for (int j = 1; j<A.length; j++)
            C[A[j]]++;
        System.out.println("C array store the counitng number");
        for (int i=0;i<k;i++)
        {
            System.out.print(C[i]+" ");
        }
        System.out.println("");
        for (int i=1;i<k;i++)
            C[i]=C[i]+C[i-1];
        System.out.println("C array store the cumulative number");
        for (int i=0;i<k;i++)
        {
            System.out.print(C[i]+" ");
        }
        System.out.println("");
        System.out.println("the 9 in B array means empty");
        System.out.println("the B array is                           C array is");

        for (int j=A.length-1;j>=1;j--)
            {  B[C[A[j]]]=A[j];
                C[A[j]]=C[A[j]]-1;
                System.out.println("");
                for(int i=1;i<A.length;i++)
                    System.out.print(+B[i]+" ");
                System.out.print("              ");
                for (int i=0;i<k;i++)
                {
                    System.out.print(C[i]+" ");
                }
            }
        System.out.println("");
    }
}
