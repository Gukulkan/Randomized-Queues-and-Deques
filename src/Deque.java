import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    Node first;
    Node last;

    private int N;

    public Deque(){// construct an empty deque
        first = null;
        last = null;
        N = 0;
    }
    public boolean isEmpty(){  // is the deque empty?
        return N == 0;
    }
    public int size()    {                    // return the number of items on the deque
        return N;
    }
    public void addFirst(Item item){          // add the item to the front
        if( item == null ) throw new java.lang.NullPointerException("You cannot add null.");
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        if(isEmpty()){
            first.next = null;
            last = first;
        }else{
            first.next = oldFirst;
            oldFirst.prev = first;
        }

        N++;
    }
    public void addLast(Item item){           // add the item to the end
        if( item == null) throw new java.lang.NullPointerException("You cannot add null.");
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            last.prev = null;
            first = last;
        }else{
            last.prev = oldLast;
            oldLast.next = last;
        }

        N++;
    }
    public Item removeFirst() {               // remove and return the item from the front
        if(isEmpty()) throw new java.util.NoSuchElementException();

        Item returnedItem = first.item;
        first = first.next;
        N--;
        if(isEmpty()){
            first = last = null;
        }else {
            first.prev = null;
        }
        return returnedItem;
    }
    public Item removeLast(){                 // remove and return the item from the end
        if(isEmpty()) throw new java.util.NoSuchElementException();

        Item removedItem = last.item;
        last = last.prev;
        N--;
        if(isEmpty()){
            first = last = null;
        }else {
            last.next = null;
        }

        return removedItem;
    }
    public Iterator<Item> iterator(){         // return an iterator over items in order from front to end
        return new DequeIterator();
    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    private class DequeIterator implements Iterator<Item>{

        Node currentNode = first;

        public boolean hasNext() {
            return currentNode != null;
        }

        public Item next() {
            if(!hasNext()) throw new java.util.NoSuchElementException();
            Item itemToReturn = currentNode.item;
            currentNode = currentNode.next;
            return itemToReturn;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }



    public static void main(String[] args){   // unit testing
        Deque<String> deque = new Deque<String> ();
        while(!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if(!s.equals("-")) {
                StdOut.println("It was " +deque.size());
                deque.addFirst(s);
                StdOut.println("Now " +deque.size());
            }
            else  {
                StdOut.println("It was " +deque.size());
                StdOut.println(deque.removeFirst() + " ");
                StdOut.println("Now " +deque.size());
            }
        }
        StdOut.println("(" + deque.size() +" left on the deque)");
    }
}