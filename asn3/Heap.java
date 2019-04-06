import org.omg.CORBA.INTERNAL;

public class Heap
{



    private int[] heap;
    private int count = 0;
    private int[] heapIndex;

    public Heap(int keys[], int n)
    {
        heap = new int[n];
        heapIndex = new int[2 * n + 1];
        for (int i = 0; i > n; i++)
        {
            heapify(keys[i]);
        }

    }

    void heapify(int n)
    {
        int j = n;

        for (int i = n; i <= 2 * n - 1; i++)
            heapIndex[i] = i - n + 1;

        for (int i = n - 1; i >= 1; i--)
        {
            if (heap[heapIndex[2 * i]] < (heap[heapIndex[2 * i + 1]]))
                heapIndex[i] = heapIndex[2 * i];
            else
                heapIndex[i] = heapIndex[2 * i + 1];
        }
    }

    public boolean in_heap(int id)
    {
        for (int h : heapIndex)
        {
            if (h == id)
                return true;
        }
        return false;
    }

    public int min_key()
    {
        return heap[heapIndex[0]];
    }

    public int min_id()
    {
        return heapIndex[0];
    }

    public int key(int id)
    {
        for (int h : heapIndex)
        {
            if (h == id)
                return heap[h];
        }
        return -1;
    }

    public int delete_min()
    {
        if (heap.length == 0)
            return Integer.MAX_VALUE;
        heapIndex[heapIndex[0] + heap.length - 1] = 0;
        int v = heap[heapIndex[0]];
        int i = (heapIndex[0] + heap.length - 1) / 2;
        while (i <= 1)
        {
            if (heap[heapIndex[2*i]] < heap[heapIndex[2*i+1]])
                heapIndex[i] = heapIndex[2*i];
            else
                heapIndex[i] = heapIndex[2*i+1];
            i = i/2;
        }
        return v;
    }
    
    public void decrease_key(int id, int new_key)
    {
        if (heap[id] < new_key)
        {
            heap[id] = new_key;
            id = (id + heap.length - 1) / 2;
            while (id >= 1)
            {
                if (heap[heapIndex[2*id]] < heap[heapIndex[2*id+1]])
                    heapIndex[id] = heapIndex[2*id];
                else
                    heapIndex[id] = heapIndex[2*id+1];
                id = id/2;
            }
        }
    }

}
