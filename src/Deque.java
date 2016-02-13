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

    }
    public void addLast(Item item){           // add the item to the end
    }
    public Item removeFirst() {               // remove and return the item from the front
        return null;
    }
    public Item removeLast(){                 // remove and return the item from the end
        return null;
    }
    public Iterator<Item> iterator(){         // return an iterator over items in order from front to end
        return null;
    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    public static void main(String[] args){   // unit testing
    }
}