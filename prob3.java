import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


interface IPolynomialSolver {
    void setPolynomial(char poly, int[] terms);
    String print(char poly);
    void clearPolynomial(char poly);
    float evaluatePolynomial(char poly, float value);
    int[] add(char poly1, char poly2);
    int[] subtract(char poly1, char poly2);
    int[] multiply(char poly1, char poly2);
}

class Node
{
    int value;
    Node nextNode;
    public Node()
    {
        value = 0;
        nextNode = null;
    }
    public Node(int v)
    {
        value = v;
        nextNode = null;
    }
    public Node(int v, Node n)
    {
        value = v; nextNode = n;
    }
}

 class SingleLinkedList  
{
    Node root = null;
    int size = 0;
    
    public SingleLinkedList() {}
    
    public SingleLinkedList(int[] elements)
    {
        for (int i = 0; i < elements.length; i++)
            this.add(elements[i]);
    }
    
    public void print()
    {
        System.out.print("[");
        for (Node c = root; c != null; c = c.nextNode)
        {
            System.out.print(c.value);
            if (c.nextNode != null)
                System.out.print(", ");
        }
        System.out.print("]\n");
    }
    
    public void add(int index, int element) 
    {
        index-=2;
        if(index >= size)
        {
            System.out.println("Error");
            System.exit(0);
            return;
        }
        Node current = new Node(element, null);
        if (index == -1)
        {
            current.nextNode = root;
            root = current;
            size++;
            return;
        }
        Node temp = root;
        for (int i = 0; i < index; i++)
            temp = temp.nextNode;
        current.nextNode = temp.nextNode;
        temp.nextNode = current;
        size++;
    }
    
    public void add(int element) 
    {
        Node current = new Node(element);
        if (size == 0)
        {
            root = current;
            size++;
            return;
        }
        Node temp = root;
        while (temp.nextNode != null)
            temp = temp.nextNode;
        temp.nextNode = current;
        size++;
    }
    // don't forget -1
    public int get(int index) 
    {
        index-=2;
        if (index >= size - 1 || index < -1)
        {
            System.out.println("Error");
            System.exit(0);
            return -1;
        }
        Node current = root;
        for (int i = 0; i < index + 1; i++)
            current = current.nextNode;
        return current.value;
    }
    
    public void set(int index, int element) 
    {
        index-=2;
        if (index >= size - 1 || index < -1)
        {
            System.out.println("Error");
            System.exit(0);
            return;
        }
        Node current = root;
        for (int i = 0; i < index + 1; i++)
            current = current.nextNode;
        current.value = element;
    }
    
    
    public void clear() {root = null; size = 0;}
    
    public boolean isEmpty() {return this.size == 0;}
    
    public void remove(int index) 
    {
        index-=2;
        if (index >= size - 1 || index < -1)
        {
            System.out.println("Error");
            System.exit(0);
            return;
        }
        if (index == -1)
        {
            root = root.nextNode;
            return;
        }
        Node current = root;
        for (int i = 0; i < index; i++)
            current = current.nextNode;
        
        current.nextNode = current.nextNode.nextNode;
        size--;
    }
    


}
public class PolynomialSolver implements IPolynomialSolver{
    SingleLinkedList A = new SingleLinkedList();
    SingleLinkedList B = new SingleLinkedList();
    SingleLinkedList C = new SingleLinkedList();
    SingleLinkedList R = new SingleLinkedList();
    SingleLinkedList R2 = new SingleLinkedList();
    SingleLinkedList add1 = new SingleLinkedList();
    SingleLinkedList sub = new SingleLinkedList();
    SingleLinkedList multi = new SingleLinkedList();
    public void setPolynomial(char poly, int[] terms){
        if(poly=='A'){
            A = new SingleLinkedList(terms);
        }
        else if(poly=='B'){
            B = new SingleLinkedList(terms);
        }
        else{
            C = new SingleLinkedList(terms);
        }
    
    }
    public String print(char poly){
        R = new SingleLinkedList();        
        if(poly == 'A'){
            R = A;

        }
        else if(poly =='B'){
            R = B;
        }
        else if(poly =='C'){
            R = C;
        }    
        else if(poly =='d'){
            R = add1;
        }
        else if(poly =='b'){
            R = sub;
        }
        else if(poly =='m'){
            R = multi;
        }
        if(R.root == null){
            return "Error";
        }
        else{
            String sum = "";
            int x= R.size-1,b=1,m=1;
            Node current = R.root;
            for(int y = 0;y<R.size;y++){
                if(current.value!=0){
  
                    if(current.value>0&&x != R.size-1&&b==1) {
                        sum+="+";
                        m=1;
                    }    
                    if( current.value==1&&x!=0)
                        sum += "x^"+x;
                    else if( x > 1 ){
                            sum += current.value+"x^"+x; 
                    }
                    else if(x==1)
                        sum += current.value+"x";
                    else if(x==0)
                        sum += current.value;
                    
                }
                else
                    b=0;
                if(b== 0&& m==0)
                    b=1;
                    
                
                m = 0;    
                x--;
                current = current.nextNode;
            }
                
            return sum;
        }
        
    }
    
    public void clearPolynomial(char poly){
        if(poly == 'A'){
            A.clear();

        }
        else if(poly =='B'){
            B.clear();
        }
        else if(poly =='C'){
            C.clear();
        }   
    }
    public float evaluatePolynomial(char poly, float value) {
        R = new SingleLinkedList();        
        int x = 0;
        if(poly == 'A'){
            R = A;
        }
        else if(poly =='B'){
            R = B;
        }
        else if(poly =='C'){
            R = C;
        }
        float sum=0;
        Node current = R.root;
        for(int i =R.size-1;i>=0;i--){
            sum += (current.value)*(Math.pow(value,i));
            current = current.nextNode;
        }
        return sum;
    }
    
    public int[] add(char poly1, char poly2){
        R = new SingleLinkedList();
        R2 = new SingleLinkedList();
        if(poly1 =='A')
            R = A;
        else if(poly1=='B')
            R = B;
        else
            R2 = C;
        if(poly2 =='A')
            R = A;
        else if(poly2=='B')
            R2 = B;
        else
            R2= C;
        
        int[] arr = new int[R.size];
        
        Node current = R.root;
        for(int i = 0;i<R.size;i++){
            arr[i] = current.value;
            current = current.nextNode;
        }
        
        Node current2 = R2.root;
        for(int j=0;j<R.size;j++){
            arr[j] += current2.value;
            current2 = current2.nextNode;
        }
        add1 = new SingleLinkedList(arr);
        return arr;
    } 
    public int[] subtract(char poly1, char poly2){
        R = new SingleLinkedList();
        R2 = new SingleLinkedList();
        if(poly1 =='A')
            R = A;
        else if(poly1=='B')
            R = B;
        else
            R2 = C;
        if(poly2 =='A')
            R = A;
        else if(poly2=='B')
            R2 = B;
        else
            R2= C;
        
        int[] arr = new int[Math.max(R.size,R2.size)];
        Node current = R.root;
        for(int i =0 ;i<R.size;i++){
            arr[i] = current.value;
            current = current.nextNode;
        }
        Node current2 = R2.root;
        for(int j =0 ; j<R2.size; j++){
            arr[j] -= current2.value;
            current2 = current2.nextNode;
        }
        sub = new SingleLinkedList(arr);
        return arr;
    }
    public int[] multiply(char poly1, char poly2){
        R = new SingleLinkedList();
        R2 = new SingleLinkedList();
        if(poly1 =='A')
            R = A;
        else if(poly1=='B')
            R = B;
        else
            R2 = C;
        if(poly2 =='A')
            R = A;
        else if(poly2=='B')
            R2 = B;
        else
            R2= C;
        
        int[] arr = new int[R.size+R2.size-1];
        Node current = R.root;
        for(int i =0;i<R.size;i++){
            Node current2 = R2.root;
            for(int j=0;j<R2.size;j++){
                arr[j+i] += (current.value)*(current2.value);
                current2 = current2.nextNode;
            }
            current = current.nextNode;
        }  
        multi = new SingleLinkedList(arr);
        return arr;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        PolynomialSolver p = new PolynomialSolver();
        try{
        while(st!=""){
            if(st.equals("set")){
                char c = sc.nextLine().charAt(0);
                if(c!='A'&&c!='B'&&c!='C'){
                    System.out.println("Error");
                    break;
                }
                String sss = sc.nextLine();
                String sin = sss.replaceAll("\\[|\\]", "");
                String[] s = sin.split(",");;
                int[] arr = new int[s.length];
                if (s.length == 1 && s[0].isEmpty())
                    arr = new int[]{};
                else 
                {
                    for(int i = 0; i < s.length; ++i)
                         arr[i] = Integer.parseInt(s[i]);
                }
                if(arr.length==0){
                    System.out.println("Error");
                    break;
                }
                    
                p.setPolynomial(c,arr);
            }
            else if(st.equals("print")){
                char c = sc.nextLine().charAt(0);
                if(c!='A'&&c!='B'&&c!='C'){
                    System.out.println("Error");
                    break;
                }
                System.out.println(p.print(c));
            }
            else if(st.equals("clear")){
                char c = sc.nextLine().charAt(0);
                if(c!='A'&&c!='B'&&c!='C'){
                    System.out.println("Error");
                    break;
                }
                p.clearPolynomial(c);
                System.out.println("[]");
            }
            else if(st.equals("eval")){
                char c = sc.nextLine().charAt(0);
                if(c!='A'&&c!='B'&&c!='C'){
                    System.out.println("Error");
                    break;
                }
                float f = sc.nextFloat();
                int z =Math.round(p.evaluatePolynomial(c,f));
                System.out.println(z);
            }
            else if(st.equals("add")){
                char c = sc.nextLine().charAt(0);
                char c2 = sc.nextLine().charAt(0);
                if(c!='A'&&c!='B'&&c!='C'){
                    System.out.println("Error");
                    break;
                }
                if(c2!='A'&&c2!='B'&&c2!='C'){
                    System.out.println("Error");
                    break;
                }
                int[] arr= p.add(c,c2);
                System.out.println(p.print('d'));
            }
            else if(st.equals("sub")){
                char c = sc.nextLine().charAt(0);
                char c2 = sc.nextLine().charAt(0);
                if(c!='A'&&c!='B'&&c!='C'){
                    System.out.println("Error");
                    break;
                }
                if(c2!='A'&&c2!='B'&&c2!='C'){
                    System.out.println("Error");
                    break;
                }
                int[] arr = p.subtract(c,c2);
                System.out.println(p.print('b'));
            }
            else if(st.equals("mult")){
                char c = sc.nextLine().charAt(0);
                char c2 = sc.nextLine().charAt(0);
                if(c!='A'&&c!='B'&&c!='C'){
                    System.out.println("Error");
                    break;
                }
                if(c2!='A'&&c2!='B'&&c2!='C'){
                    System.out.println("Error");
                    break;
                }
                int[] arr = p.multiply(c,c2);
                System.out.println(p.print('m'));
            }
            else{
                System.out.println("Error");
                break;
            }
            st = sc.nextLine();
        }
        }
        catch(Exception e){
            System.out.println("");
        }
    }
}