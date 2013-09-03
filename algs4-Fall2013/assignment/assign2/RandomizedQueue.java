import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item>  implements Iterable<Item>
{
    private Item[] a;
    private int N;

    public RandomizedQueue()
    {
        a = (Item[]) new Object[2];
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public int size()
    {
        return N;
    }

    private void resize(int capacity)
    {
        assert capacity >= N;
        Item[] temp = (Item[]) new Object[capacity];
        for(int i = 0; i < N; i++)
        {
            temp[i] = a[i];
        }
        a = temp;
    }
    public void enqueue(Item item)
    {
        if(N == a.length) resize(2*a.length);
        a[N++] = item;
    }

    public Item dequeue(Item item)
    {
        if(isEmpty()) throw new NoSuchElementException("RandomizedQueue underflow");
        item = a[N - 1];
        a[N-1] = null;
        N--;
        if(N > 0 && N == a.length/4) resize(a.length/2);
        return item;
    }
    
    public Item sample()
    {
        return a[0];
    }
    
    public Iterator<Item> iterator()
    {
        return new ArrayIndexIterator();
    }

    private class ArrayIndexIterator implements Iterator<Item> 
    {
        private int i = 0;
        public boolean hasNext()
        {
            return i < a.length - 1;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Item next()
        {
            if(!hasNext()) throw new NoSuchElementException();
            return a[i++];
        }
    }

    public static void main(String[] args)
    {
    }
}