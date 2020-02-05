import java.util.Scanner;
import java.util.Random;
public class Heapsort {
    static int heapsize;//the tree size to be used by funtions:MAXHEAPIFY,MaxHeapInsert,Heapsort,HeapExtractMax
    static int inputsize;// the exact size we are going to sort or check or display.
    static int [] B = new int[30];//array that stores event
    public static void main(String[] args) {
        int i = 1, j;
        int order = 0;// input order number to see the functions;
        int[] A = new int[30];//array stores the value

        Random rand = new Random();//set variable rand to generate random number
        Scanner scanner = new Scanner(System.in);
        System.out.println("how many inputs(1-29)?");
        inputsize = scanner.nextInt();// user determines how many events.
        for (j = 1; j < 30; j++) {
            B[j] = j;// event number 1 to 29
        }
        //give values to tree.
        while (i <= inputsize) {
            A[i] = rand.nextInt(100) + 1;//generater number from 1 to 100

            i++;
        }
        heapsize = inputsize;
        System.out.print("random tree is :");
        SHOWTREE(A);
        System.out.println("");


        ////////////////////////above is setting, can't change, below is function test////
        while (order!= 9) {
            System.out.println("if you want to see function  'MAXHEAPIFY'         ,input 1");
            System.out.println("if you want to see function  'BuildMaxHeap'       ,input 2");
            System.out.println("if you want to see function  'Heapsort'           ,input 3");
            System.out.println("if you want to see function  'MaxHeapInsert'      ,input 4");
            System.out.println("if you want to see maximun value in the heaptree  ,input 5");
            System.out.println("if you want to extract maximun  in the heaptree   ,input 6");
            System.out.println("if you want to see the tree                       ,input 7");
            System.out.println("if you want to increase the priority              ,input 8");
            System.out.println("if you want to end                                ,input 9");
            order = scanner.nextInt();

            if (order == 1) {
                heapsize = inputsize;
                int num;
                System.out.println("input the event number you want to apply");
                num = scanner.nextInt();
                MAXHEAPIFY(A, num);
                System.out.println("the result of MAXHEAPIFY is:");
                SHOWTREE(A);
                System.out.println("");
            }
            if (order == 2) {
                heapsize = inputsize;
                BuildMaxHeap(A);
                System.out.println("the result of BuildMaxHeap is:");
                SHOWTREE(A);
                System.out.println("");
            }
            if (order == 3) {
                heapsize = inputsize;
                Heapsort(A);
                System.out.println("the result of Heapsort is:");
                SHOWTREE(A);
                System.out.println("");
            }
            if (order == 4) {
                heapsize = inputsize;
                int num;
                System.out.println("input a  number you want to insert to the tree");
                num = scanner.nextInt();
                MaxHeapInsert(A, num);
                System.out.println("the result of MaxHeapInsert is:");
                SHOWTREE(A);
                System.out.println("");
            }
            if (order == 5) {
                heapsize = inputsize;
                BuildMaxHeap(A);
                System.out.println("the maximun value in the heap tree is: " + HEAPMAXIMUM(A) + "(p" + B[1] + ")  ");
            }
            if (order == 6) {
                heapsize = inputsize;
                System.out.println("the maximun value in the heap tree is: " + HeapExtractMax(A) + "(p" + B[1] + ")  ");
            }
            if (order == 7)
            {
                System.out.print("the current tree is: ");
                SHOWTREE(A);
                System.out.println("");
            }
            if (order == 8)
            {   int k=1;
                System.out.print("input the priority value that you want to increase ");
                int num = scanner.nextInt();
                System.out.print("what value you want to increase to");
                int key = scanner.nextInt();
                while(A[k]!=num && k<29)    // find the value in array
                {
                    k++;
                }
                HeapIncreaseKey(A,k,key);
                System.out.println("");
            }

        }
    }

    //  a function to return input's parent in heap tree
    public static int PARENT(int i) {
        return i / 2;
    }

    //  a function to return input's left child in heap tree
    public static int LEFT(int i) {
        return 2 * i;
    }

    //  a function to return input's right child in heap tree
    public static int RIGHT(int i) {
        return (2 * i) + 1;
    }

    // a function to heapify a array
    public static void MAXHEAPIFY(int[] A, int i) {
        int largest, temp = 0;
        int l = LEFT(i);
        int r = RIGHT(i);
        if (l <= heapsize && A[l] > A[i])
            largest = l;
        else
            largest = i;
        if (r <= heapsize && A[r] > A[largest])
            largest = r;
        if (largest != i) {
            temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;
            temp = B[i];
            B[i] = B[largest];
            B[largest] = temp;
            MAXHEAPIFY(A, largest);
        }// recursively heapify the array to be max tree
    }

    //a function to show the content in heap tree
    public static void SHOWTREE(int[] A) {
        int i = 1;
        while (i <= inputsize) {
            System.out.print(A[i] + "(p"+B[i]+")  ");
            i++;
        }

    }

    // a function to build a maxheap tree which parents always larger than their children, but array not sorted
    public static void BuildMaxHeap(int[] A) {
        int i = inputsize/ 2;
        for (; i >=1; i--) {
            MAXHEAPIFY(A, i);
        }
    }
    // a function which sort the heap tree from smallest to largest
    public static void Heapsort(int[] A) {
        int i, temps = 0;
        BuildMaxHeap(A);
        for (i = inputsize; i >= 2; i--) {
            temps = A[1];
            A[1] = A[i];
            A[i] = temps;
            temps = B[1];
            B[1] = B[i];
            B[i] = temps;

            heapsize--;
            MAXHEAPIFY(A, 1);
        }
    }
    //a function will be call when calling MaxHeapInsert
    public static void HEAPINCREASEKEY(int[] A, int i, int key) {
        int temp;
        if (key < A[i])
            System.out.print("error:new key is smaller that current keys");
        A[i] = key;
        while (i > 1 && A[PARENT(i)] < A[i]) {
            temp = A[i];
            A[i] = A[PARENT(i)];
            A[PARENT(i)] = temp;

            temp = B[i];
            B[i] = B[PARENT(i)];
            B[PARENT(i)] = temp;
            i = PARENT(i);
        }
    }

    // a fuction which allow to insert a number in heap tree and sort
    public static void MaxHeapInsert(int[] A, int key) {
        inputsize++;
        heapsize++;
        A[heapsize] = -100000;
        HEAPINCREASEKEY(A, heapsize, key);
    }
    // get the first element(max) and delete it
    public static int HeapExtractMax(int[] A) {
        if (heapsize < 1)
            System.out.print("error:heap underflow");
        int max = A[1];
        A[1] = A[heapsize];
        inputsize--;
        heapsize--;
        MAXHEAPIFY(A, 1);
        return max;
    }
    // get the first one of the heap tree
    public static int HEAPMAXIMUM(int[] A) {
        return A[1];
    }
    public static void HeapIncreaseKey(int[] A,int i, int key)
    {
        // key is new value
        if (key < A[i])
            System.out.print("error:new key is smaller than current key");
        A[i] = key;
        while (i >1 && A[PARENT(i)] < A[i])
        {
            int temp = A[i];         //exchange A[i] and A[parent(i)]
            A[i] = A[PARENT(i)];
            A[PARENT(i)] = temp;

            i = PARENT(i);
        }

    }
}