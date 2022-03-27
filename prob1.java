import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ILinkedList 
{
    public void add(int index, Object element);
    public void add(Object element);
    public Object get(int index);
    
    public void set(int index, Object element);
    public void clear();
    public boolean isEmpty();
    public void remove(int index);
    public int size();
    public ILinkedList sublist(int fromIndex, int toIndex);
    public boolean contains(Object o);
}
class Node
{
    Object value;
    Node nextNode;
    public Node()
    {
        value = 0;
        nextNode = null;
    }
    public Node(Object v)
    {
        value = v;
        nextNode = null;
    }
    public Node(Object v, Node n)
    {
        value = v; nextNode = n;
    }
}

public class SingleLinkedList implements ILinkedList 
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
    
    public void add(int index, Object element) 
    {
        index-=2;
        if(index >= size || index < -1)
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
    
    public void add(Object element) 
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
    public Object get(int index) 
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

    public void set(int index, Object element) 
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
    
    public int size() {return this.size;}
    
    // don't forget null
    public ILinkedList sublist(int fromIndex, int toIndex) 
    {
        fromIndex-=2; toIndex-=2;
        if (fromIndex >= size - 1 || fromIndex < -1 || fromIndex > toIndex)
        {
            System.out.println("Error");
            System.exit(0);
            return null;
        }
        if (toIndex >= size - 1 || toIndex < -1)
        {
            System.out.println("Error");
            System.exit(0);
            return null;
        }
        SingleLinkedList list = new SingleLinkedList();
        
        Node current = root;
        for (int i = 0; i < fromIndex + 1; i++)
            current = current.nextNode;
        for (int i = 0; i < toIndex - fromIndex + 1; i++)
        {
            list.add(current.value);
            current = current.nextNode;
        }
        return list;
    }
    
    public boolean contains(Object o) 
    {
        for (Node c = root; c != null; c = c.nextNode)
            if (c.value == o)
                return true;
        
        return false;
    }
    
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String sss = sc.nextLine();
        String sin = sss.replaceAll("\\[|\\]", "");
        String[] s = sin.split(", ");;
        int[] arr = new int[s.length];
        if (s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else 
        {
            for(int i = 0; i < s.length; ++i)
               arr[i] = Integer.parseInt(s[i]);
        }
        
        SingleLinkedList list;
        if (sss.length() != 2)
            list = new SingleLinkedList(arr);
        else
            list = new SingleLinkedList();
        String str = sc.nextLine();
        if (str.equals( "add"))
        {
            int e = sc.nextInt();
            list.add(e);
            list.print();
        }
        else if (str.equals( "addToIndex"))
        {
            int index = sc.nextInt() + 1, e = sc.nextInt();
            list.add(index, e);
            list.print();
        }
        else if (str.equals( "get"))
        {
            int index = sc.nextInt() + 1;
            System.out.println(list.get(index));
        }
        else if (str.equals( "set"))
        {
            int index = sc.nextInt() + 1, e = sc.nextInt();
            list.set(index, e);
            list.print();
        }
        else if (str.equals( "clear"))
        {
            list.clear();
            list.print();
        }
        else if (str.equals( "isEmpty"))
        {
            if (list.isEmpty())
                System.out.println("True");
            else
                System.out.println("False");    
        }
        else if (str.equals( "remove"))
        {
            int index = sc.nextInt() + 1;
            list.remove(index);
            list.print();
        }
        else if (str.equals( "sublist"))
        {
            int first = sc.nextInt() + 1, second = sc.nextInt() + 1;
            SingleLinkedList newlist = (SingleLinkedList)list.sublist(first, second);
            newlist.print();
        }
        else if (str.equals( "contains"))
        {
            int e = sc.nextInt();
            if (list.contains(e))
                System.out.println("True");
            else
                System.out.println("False");    
        }
        else if (str.equals( "size"))
        {
            System.out.println(list.size());
        }
        else
            System.out.println("Error");
    }
}

