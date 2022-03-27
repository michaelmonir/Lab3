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


class dNode
{
    Object value;
    dNode nextdNode;
    dNode prevdNode;
    public dNode()
    {
        value = 0;
        nextdNode = null;
    }
    public dNode(Object v)
    {
        value = v;
        nextdNode = null;
        prevdNode = null;
    }
}

public class DoubleLinkdList implements ILinkedList 
{
    dNode root = null;
    int size = 0;
    
    public DoubleLinkdList() {}
    
    public DoubleLinkdList(int[] elements)
    {
        for (int i = 0; i < elements.length; i++)
            this.add(elements[i]);
    }
    
    public void print()
    {
        System.out.print("[");
        for (dNode c = root; c != null; c = c.nextdNode)
        {
            System.out.print(c.value);
            if (c.nextdNode != null)
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
        dNode current = new dNode(element);
        if (index == -1)
        {
            current.nextdNode = root;
            if (root != null)
                root.prevdNode = current;
            root = current;
            size++;
            return;
        }
        dNode temp = root;
        for (int i = 0; i < index; i++)
            temp = temp.nextdNode;
        //  temp current current.next
        current.nextdNode = temp.nextdNode;
        if (temp.nextdNode != null)
            temp.nextdNode.prevdNode = temp;
        current.prevdNode = temp;
        temp.nextdNode = current;
        size++;
    }
    
    public void add(Object element) 
    {
        dNode current = new dNode(element);
        if (size == 0)
        {
            root = current;
            size++;
            return;
        }
        dNode temp = root;
        while (temp.nextdNode != null)
            temp = temp.nextdNode;
        temp.nextdNode = current;
        current.prevdNode = temp;
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
        dNode current = root;
        for (int i = 0; i < index + 1; i++)
            current = current.nextdNode;
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
        dNode current = root;
        for (int i = 0; i < index + 1; i++)
            current = current.nextdNode;
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
            root = root.nextdNode;
            if (root != null)
                root.prevdNode = null;
            return;
        }
        dNode current = root;
        for (int i = 0; i < index; i++)
            current = current.nextdNode;
        
        if (current.nextdNode.nextdNode != null)
            current.nextdNode.nextdNode.prevdNode = current.nextdNode;
        current.nextdNode = current.nextdNode.nextdNode;
        
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
        
        DoubleLinkdList list = new DoubleLinkdList();
        if (fromIndex > toIndex)
        {
            dNode current = root;
            for (int i = 0; i < fromIndex + 1; i++)
                current = current.nextdNode;
            for (int i = 0; i < fromIndex - toIndex + 1; i++)
            {
                list.add(current.value);
                current = current.prevdNode;
            }
            return list;
        }
        dNode current = root;
        for (int i = 0; i < fromIndex + 1; i++)
            current = current.nextdNode;
        for (int i = 0; i < toIndex - fromIndex + 1; i++)
        {
            list.add(current.value);
            current = current.nextdNode;
        }
        return list;
    }
    
    public boolean contains(Object o) 
    {
        for (dNode c = root; c != null; c = c.nextdNode)
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
        
        DoubleLinkdList list;
        if (sss.length() != 2)
            list = new DoubleLinkdList(arr);
        else
            list = new DoubleLinkdList();
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
            DoubleLinkdList newlist = (DoubleLinkdList)list.sublist(first, second);
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

