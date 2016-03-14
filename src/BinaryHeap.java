//CIS 313 Lab 3 part 2 
// Allen Roush 
//Binary Min Heap implementation using Arrays 
//NOTE: All functions are under 12 lines (except main due to parantheses) 

import java.util.Arrays;
import java.util.Scanner;

public class BinaryHeap
{

    /** A binary heap will always have 2 children **/
    private static final int d = 2;
    private int              heapSize;
    private int[]            heap;

    public BinaryHeap(int capacity)
    {
        heapSize = 0;
        heap = new int[capacity + 1];
        Arrays.fill(heap, -1);
    }

    public boolean isFull()
    {
        return heapSize == heap.length;
    }

    public boolean isEmpty()
    {
        return heapSize == 0;
    }

    public void insert(int toInsert)
    {
        if (isFull() == true)
            System.out.println("Couldn't insert, heap is full!");
        // insert 
        heap[heapSize++] = toInsert;
        // heapify 
        heapifyUp(heapSize - 1);
        System.out.println("Inserted: " + toInsert);
    }

    public int delete(int toDelete)
    {
        if (isEmpty() == true)
            System.out.println("Couldn't insert, heap is full!");
        int keyItem = heap[toDelete];
        heap[toDelete] = heap[heapSize - 1];
        heapSize--;
        heapifyDown(toDelete);
        return keyItem;
    }

    public int findMin()
    {
        if (isEmpty() == true)
            System.out.println("Couldn't find a minimum, heap is empty!");
        int min = heap[0];
        System.out.println("Found minimum value:" + min);
        return min;
    }

    /** Function to delete min element **/
    public int deleteMin()
    {
        int keyItem = heap[0]; //store a copy of the deleted value to return 
        delete(0);
        System.out.println("Deleted minimum value:" + keyItem);
        return keyItem;
    }

    // This function keeps the heap proper during insert opertions 
    private void heapifyUp(int input)
    {
        int tmp = heap[input];
        while (input > 0 && tmp < heap[parent(input)])
        {
            heap[input] = heap[parent(input)];
            input = parent(input);
        }
        heap[input] = tmp;
    }

    // This function keeps the heap proper during delete operations 
    private void heapifyDown(int input)
    {
        int child;
        int tmp = heap[input];
        while (kthChild(input, 1) < heapSize)
        {
            child = minChild(input);
            if (heap[child] < tmp)
                heap[input] = heap[child];
            else
                break;
            input = child;
        }
        heap[input] = tmp;
    }

    // This function gets the minimum child of a passed in value 
    private int minChild(int input)
    {
        int minChild = kthChild(input, 1);
        int k = 2;
        int pos = kthChild(input, k);
        while ((k <= d) && (pos < heapSize))
        {
            if (heap[pos] < heap[minChild])
                minChild = pos;
            pos = kthChild(input, k++);
        }
        return minChild;
    }

    //This function gets the index of value K, which is the child of i. 
    private int kthChild(int i, int k)
    {
        return 2 * i + k;
    }

    // this function gets the index of the parent of i. 
    private int parent(int i)
    {
        return (i - 1) / 2;
    }
    
    
    public static void main(String[] args)
    {
        BinaryHeap b = new BinaryHeap(100); //Initialize heap to 100 
        Scanner scanner = new Scanner(System.in);
        int i = 0;

        String userInput = scanner.next();
        while (!userInput.equals("exit"))
        {
            if (userInput.equals("insert"))
            {
                i = scanner.nextInt();
                b.insert(i);
            } else if (userInput.equals("findMin"))
            {
                b.findMin();
            }

            else if (userInput.equals("removeMin"))
            {
                b.deleteMin();

            }
            userInput = scanner.next();
        }
        System.out.println("Exiting!");
        scanner.close();

}
}
