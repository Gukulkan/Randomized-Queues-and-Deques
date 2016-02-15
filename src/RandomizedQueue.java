import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int N;

    public RandomizedQueue(){                 // construct an empty randomized queue
        N = 0;
        arr = (Item[])new Object[1];
    }

    public boolean isEmpty(){                 // is the queue empty?
        return N == 0;
    }

    public int size(){                        // return the number of items on the queue
        return N;
    }

    private void resize(int newN){
        Item[] newArr = (Item[]) new Object[newN];
        for(int i = 0; i < N; i++){
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public void enqueue(Item item){           // add the item
        if(item == null) throw new java.lang.NullPointerException();

        if(N == arr.length) resize(arr.length * 2);

        arr[N++] = item;
    }

    public Item dequeue(){                    // remove and return a random item
        if(isEmpty()) throw new java.util.NoSuchElementException();
        int randomIndex = StdRandom.uniform(N);
        Item returned = arr[randomIndex];

        if(randomIndex != N-1) arr[randomIndex] = arr[N-1];

        arr[N-1] = null;
        N--;

        if(N > 0 && arr.length/4 == N) resize(arr.length / 2);

        return returned;
    }

    public Item sample(){                     // return (but do not remove) a random item
        if(isEmpty()) throw new java.util.NoSuchElementException();
        return arr[StdRandom.uniform(N)];
    }

    public Iterator<Item> iterator(){         // return an independent iterator over items in random order
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>{

        Item[] copyArr = (Item[]) new Object[arr.length];
        int copyN = N;

        public RandomizedQueueIterator(){
            for (int i = 0; i < N; i++){
                copyArr[i] = arr[i];
            }
        }

        public boolean hasNext() {
            return copyN != 0;
        }

        public Item next() {
            if(!hasNext()) throw new java.util.NoSuchElementException();

            int ranndomIndex = StdRandom.uniform(copyN);
            Item returned = copyArr[ranndomIndex];

            if(ranndomIndex != N-1) copyArr[ranndomIndex] = copyArr[N-1];
            copyArr[N-1] = null;

            copyN--;


            return returned;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }
    public static void main(String[] args){   // unit testing
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) q.enqueue(item);
            else if (!q.isEmpty()) StdOut.print(q.dequeue() + " ");
        }
        StdOut.println("SIZE - " + q.size());
    }
}