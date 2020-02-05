public class mergesort {
    public static void main(String[] args)
    {
        int r,p=1;
        int[] A= {0,18,26,6,9,15,12,5,20,11,30};
        r=A.length-1;
        mergesort(A,p,r);
        SHOWTREE(A);
        //for (p=1;p<r+1;p++) {
        //    System.out.println(A[p]);
        //}
    }
    public static void SHOWTREE(int[] A)
    {   int i=1;
        int h;

        h=A.length/2+1;
        while (i!=A.length)
        {
            System.out.print(A[i]+"  ");
            i++;
        }
    }
    public static void mergesort(int[]A,int p,int r)
    {
        if (p<r)
        {
            int q=(p+r)/2;
            mergesort(A,p,q);
            mergesort(A,q+1,r);
            merge(A,p,q,r);
        }
    }
    public static void merge(int[] A, int p, int q, int r)
    {

        int n1=q-p+1;
        int n2=r-q;
        int[] L= new int[n1+2];
        int[] R= new int[n2+2];

        for (int i=1;i<=n1;i++) {
            L[i]=A[p+i-1];
        }
        for (int j=1;j<=n2;j++)
        {R[j]=A[q+j];}

        L[n1+1]=200;
        R[n2+1]=200;
        int i=1;int j=1;
        for (;p<r+1;p++)
        {
            if(L[i]<R[j])
            {A[p]=L[i];
            i++;}
            else
            {A[p]=R[j];
            j=j+1;}
        }

    }

}
