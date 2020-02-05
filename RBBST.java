import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.util.Random;

//build RBBST
import java.util.Scanner;

public class RBBST
{

    private static Node x;
    private static Node y;
    private Node root;
    private Node nil=new Node(-99,"NIL");



    public RBBST()
    {
        this.nil.color="BLACK";
        this.root=this.nil;
        this.root.p=this.nil;
        y = null;
        x = null;
    }
    static class Node{
            //construtor
            int key;    // the value of process
            String process,color;// name of process
            Node left,p,right; // one node can trace/link with these three nodes.
            //now set initial values of the node
            public Node()
            {};
            public Node(int key,String name)
            {
                this.process = name;
                this.key = key;
                this.color = "RED";
                left = null;
                right = null;
                p= null;
            }
            //method
            void setColor(String color)
            {
                this.color="color";
            }
    }
    //print out the tree in preoreder.


    //print out the tree in inoreder.
    public static void INORDER(RBBST T,Node x)
    {

        if (x != T.nil)
        {
            INORDER(T,x.left);
            System.out.print(x.key + " (" + x.process+")"+" ("+x.color+")      ");
            INORDER(T,x.right);
        }

    }
    //insert a proccess with name and value
    public static void RBINSERT(RBBST T,Node z)
    {
        y=T.nil;
        x=T.root;
        // go down the tree until x is null with comparision to z
        while (x!=T.nil)
        {
            y=x;
            if (z.key < x.key)
                x=x.left;
            else
                x=x.right;
        }
        z.p=y;
        if (y==T.nil)
        { T.root=z;//tree T was empty
        }

        else if (z.key<y.key)
            y.left=z;// insert to left
        else
            {y.right=z;}//insert to right
        z.left=T.nil;
        z.right=T.nil;
        z.color="RED";
        RBIsertionFixup(T,z);
    }

    private static void RBIsertionFixup(RBBST T, Node z)
    {
        while (z.p.color=="RED")
        {
            if (z.p==z.p.p.left)
            {
                y=z.p.p.right;
                if (y.color==  "RED" )  //case 1
                {
                    z.p.color="BLACK";
                    y.color="BLACK";
                    z.p.p.color="RED";
                    z=z.p.p;
                }                       //case 1
                else if (z==z.p.right)
                {
                    z=z.p;//case 2
                    LeftRotate(T,z);    //case 2
                }
                else {
                    z.p.color="BLACK";      //case 3
                    z.p.p.color="RED";      //case 3
                    RightRotate(T,z.p.p);}   //case 3
            }

            else if (z.p==z.p.p.right)
            {
                y=z.p.p.left;
                if (y.color==  "RED" )  //case 1
                {
                    z.p.color="BLACK";
                    y.color="BLACK";
                    z.p.p.color="RED";
                    z=z.p.p;
                }                       //case 1
                else if (z==z.p.left)
                {
                    z=z.p;              //case 2
                    RightRotate(T,z);    //case 2
                }
                else {z.p.color="BLACK";      //case 3
                    z.p.p.color="RED";      //case 3
                    LeftRotate(T,z.p.p);}   //case 3
            }
        }
        T.root.color="BLACK";

    }

    public static void RBTransplant(RBBST T,Node u,Node v)
    {
        if (u.p==T.nil)
            T.root=v;

        else if (u==u.p.left)
            u.p.left=v;
        else
            u.p.right=v;
        v.p=u.p;
    }
    public static void RBDelete(RBBST T, Node z)
    {
        y=z;
        String YoriginalColor=y.color;
        //z has no left child
        if (z.left==T.nil)
        {
            x=z.right;
            RBTransplant(T,z,z.right);
        }
        else if(z.right==T.nil)
        {
            x=z.left;
            RBTransplant(T,z,z.left);
        }
        //z has two children
        else
        {
            y=Minimum(T,z.right);//find z's successor
            YoriginalColor=y.color;
            x=y.right;

            if (y.p==z)//y lies within y’s right sub tree but is not the root of this subtree*
            {
                x.p=y;
            }
            else
            {
                RBTransplant(T,y,y.right);
                y.right=z.right;
                y.right.p=y;
            }
            //if y is z’s right child
            RBTransplant(T,z,y);
            y.left=z.left;
            y.left.p=y;
            y.color=z.color;
        }
        if (YoriginalColor=="BLACK")
            RBDeleteFixup(T,x);

    }

    private static void RBDeleteFixup(RBBST T, Node x)
    {   Node w=new Node();
        while (x!=T.root && x.color=="BLACK")
        {
            if (x==x.p.left)
            {
                w=x.p.right;
                if (w.color=="RED")                                          //case 1
                {
                    w.color="BLACK";
                    x.p.color="RED";
                    LeftRotate(T,x.p);
                    w=x.p.right;
                }                                                           //case 1
                if (w.left.color=="BLACK" && w.right.color=="BLACK")        //case 2
                {
                    w.color="RED";
                    x=x.p;
                }                                                           //case 2
                else if (w.right.color=="BLACK")                            //case 3
                {
                    w.left.color="BLACK";
                    w.color="RED";
                    RightRotate(T,w);
                    w=x.p.right;
                }                                                           //case 3
                else{
                w.color=x.p.color;                                          //case 4
                x.p.color="BLACK";
                w.right.color="BLACK";
                LeftRotate(T,x.p);
                x=T.root;  }                                                 //case 4
            }
            else
            {
                w=x.p.left;
                if (w.color=="RED")                                          //case 1
                {
                    w.color="BLACK";
                    x.p.color="RED";
                    RightRotate(T,x.p);
                    w=x.p.left;
                }                                                           //case 1
                if (w.right.color=="BLACK" && w.left.color=="BLACK")        //case 2
                {
                    w.color="RED";
                    x=x.p;
                }                                                           //case 2
                else if (w.left.color=="BLACK")                            //case 3
                {
                    w.right.color="BLACK";
                    w.color="RED";
                    LeftRotate(T,w);
                    w=x.p.left;
                }                                                           //case 3
                else {
                w.color=x.p.color;                                          //case 4
                x.p.color="BLACK";
                w.left.color="BLACK";
                RightRotate(T,x.p);
                x=T.root;              }                                     //case 4
            }
        }
        x.color="BLACK";
    }

    private static Node Minimum(RBBST T,Node x)
    {
        while (x.left!=T.nil)
            x=x.left;
        return x;
    }
    private static Node Successor(RBBST T,Node x)
    {
        if (x.right!=T.nil)
            return Minimum(T,x.right);
        y=x.p;
        while (y!=T.nil && x==y.right)
        {   x=y;
            y=y.p;}
        return y;
    }
    private static Node search(RBBST T,Node x,int key)
    {
        if (x==T.nil||key==x.key)
            return x;
        if (key<x.key)
            return search(T,x.left,key);
        else
            return search(T,x.right,key);
    }
    private static void LeftRotate(RBBST T,Node x)
    {
        y=x.right;//set y
        x.right=y.left; //turny's left subtree into x's right subtree
        if (y.left!=T.nil)
        {
            y.left.p=x;
        }
        y.p=x.p;        //link x's parent to y
        if (x.p==T.nil)
        {
            T.root=y;
        }
        else if(x==x.p.left)
        {
            x.p.left=y;
        }
        else
            x.p.right=y;
        y.left=x;   //put x on y's left
        x.p=y;

    }
    private static void RightRotate(RBBST T,Node y)
    {
        x=y.left;//set x
        y.left=x.right; //turn y's left subtree into x's right subtree
        if (x.right!=T.nil)
        {
            x.right.p=y;
        }
        x.p=y.p;        //link x's parent to y's parent
        if (y.p==T.nil) //if y is root
        {
            T.root=x;
        }
        else if(y==y.p.right)  // if y is right child
        {
            y.p.right=x;
        }
        else
            y.p.left=x;
        x.right=y;   //put x on y's right
        y.p=x;
    }
    private static Node search(Node x,int key)
    {
        if (x==null||key==x.key)//if x node is empty or equals to key
            return x;
        if (key<x.key)// if target less then current node
            return search(x.left,key);// go to left node
        else		// if target greater then current node
            return search(x.right,key);//go to riht node


    }

    ///////////////////main strat here///////////////
    public static void main(String[] args)
    {
        int order=1;// user interface controler
        RBBST T = new RBBST();
        int[]A=new int[20];
        Random rand = new Random();//set variable rand to generate random number
        for ( int i=0;i<20;i++)
        {
            A[i] = rand.nextInt(89) + 10;//generater number from 10 to 99

        }

        //create 20 process and insert to the tree
        Node p1 = new Node(A[0], "p1");
        Node p2 = new Node(A[1], "p2");
        Node p3 = new Node(A[2],"p3");
        Node p4 = new Node(A[3], "p4");
        Node p5 = new Node(A[4], "p5");
        Node p6 = new Node(A[5], "p6");
        Node p7 = new Node(A[6], "p7");
        Node p8 = new Node(A[7], "p8");
        Node p9 = new Node(A[8], "p9");
        Node p10 = new Node(A[9], "p10");
        Node p11 = new Node(A[10], "p11");
        Node p12 = new Node(A[11], "p12");
        Node p13 = new Node(A[12], "p13");
        Node p14 = new Node(A[13], "p14");
        Node p15 = new Node(A[14], "p15");
        Node p16 = new Node(A[15], "p16");
        Node p17 = new Node(A[16], "p17");
        Node p18 = new Node(A[17], "p18");
        Node p19 = new Node(A[18], "p19");
        Node p20 = new Node(A[19], "p20");

        RBINSERT(T,p1);
        RBINSERT(T,p2);
        RBINSERT(T,p3);
        RBINSERT(T,p4);
        RBINSERT(T,p5);
        RBINSERT(T,p6);
        RBINSERT(T,p7);
        RBINSERT(T,p8);
        RBINSERT(T,p9);
        RBINSERT(T,p10);
        RBINSERT(T,p11);
        RBINSERT(T,p12);
        RBINSERT(T,p13);
        RBINSERT(T,p14);
        RBINSERT(T,p15);
        RBINSERT(T,p16);
        RBINSERT(T,p17);
        RBINSERT(T,p18);
        RBINSERT(T,p19);
        RBINSERT(T,p20);


        System.out.println("the 20 processes are :");
        for (int i=1;i<21;i++)
            System.out.print(A[i-1]+" (p"+i+")    ");
        System.out.println("");

        while (order!= 0) {
            System.out.println("if you want to insert a process         ,input 1");
            System.out.println("if you want to delete a process    ,input 2");
            System.out.println("if you want to see   sorted preocesses           ,input 3");
            System.out.println("if you want to exit                ,input 0");
            Scanner scanner = new Scanner(System.in);
            order = scanner.nextInt();
            if (order == 1) {
                String process;
                int key;
                System.out.println(" input a process name");
                Scanner scan = new Scanner(System.in);
                process = scan.nextLine();
                System.out.println(" input a priority key for process ");
                key = scanner.nextInt();
                Node input = new Node(key, process);
                RBINSERT(T, input);
                System.out.println("");
            }
            if (order == 2) {
                int key;
                System.out.println(" input a priority key you want to delete ");
                Scanner scaner = new Scanner(System.in);
                key =  scanner.nextInt();;
                RBDelete(T,search(T,T.root,key));
                System.out.println("");
            }
            if (order == 3)
            {
                INORDER(T,T.root);
                System.out.println("");
            }
            if (order == 4)
            {
                System.out.println(" input a priority key you want to increase its value ");
                int key = scanner.nextInt();
                System.out.println(" input a final value you want to increase to ");
                int value = scanner.nextInt();
                //create a updated Node
                Node increase= new Node(value,search(T.root,key).process);
                // delete the changing value node
                RBDelete(T,search(T.root,key));
                //Insert the updated node
                RBINSERT(T,increase);

            }
        }
    }

}

