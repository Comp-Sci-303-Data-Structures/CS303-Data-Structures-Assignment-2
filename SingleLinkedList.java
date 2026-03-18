import java.util.NoSuchElementException;

// CS-303 Assignment 2
// Yenghoua Lee
// March 17, 2026

public class SingleLinkedList<T> {
    private static class Node<T> {
        T data;          // the element stored at this node
        Node<T> next;    // reference to the next node (null if last)

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
//instance
    private Node<T> head;   // reference to the first node
    private Node<T> tail;   // reference to the last node
    private int size;       // number of elements currently in the list

//constructors
    //create an empty loist
    public SingleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
   
   
    //-----------------------------------------//
    //   create add, remove, and get methods   //
    //-----------------------------------------//


    // Inserts item at the front of the list.//
    public void addFirst(T item) {
        Node<T> newNode = new Node<>(item);

        if (isEmpty()) {
            // list empty: both head and tail point to the new node
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head; // new node points to old head
            head = newNode;      // head advances to new node
        }
        size++;
    }

    // Appends item at the end of the list.//
    public void addLast(T item) {
        Node<T> newNode = new Node<>(item);

        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode; // old tail points forward to new node
            tail = newNode;      // tail advances to new node
        }
        size++;
    }

    /**
     * Removes and returns the first element.
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot removeFirst() on an empty list.");
        }

        T removedData = head.data;
        head = head.next; // advance head to next node
        size--;

        // if the list is now empty, tail must also be null
        if (isEmpty()) {
            tail = null;
        }
        return removedData;
    }

    /**
     * Removes and returns the last element.
     * @throws NoSuchElementException if the list is empty
     * Time complexity: O(n) — must walk to find the node before tail
     */
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot removeLast() on an empty list.");
        }

        T removedData = tail.data;

        if (size == 1) {
            // only one element; list becomes empty
            head = null;
            tail = null;
        } else {
            // walk to the node just before tail
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null; // unlink old tail
            tail = current;      // update tail reference
        }
        size--;
        return removedData;
    }

    /**
     * Returns first element(without removing)
     * @throws NoSuchElementException if the list is empty
     */
    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot getFirst() on an empty list.");
        }
        return head.data;
    }

    /**
     * Returns (without removing) the last element.
     * @throws NoSuchElementException if the list is empty
     */
    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot getLast() on an empty list.");
        }
        return tail.data;
    }

    // Returns true if the list contains no elements//
    public boolean isEmpty() {
        return size == 0;
    }

    // ------------------------- //
    //  Index-based operations   //
    // ------------------------- //

    /**
     * Inserts item at position index (0-based).
     * If index >= size, the item is appended at the end.
     */
    public void insert(int index, T item) {
        // clamp out-of-range indices to the end
        if (index <= 0) {
            addFirst(item);
            return;
        }
        if (index >= size) {
            addLast(item);
            return;
        }

        // walk to the node just before the desired position
        Node<T> newNode = new Node<>(item);
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        // splice new node between current and current.next
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    /**
     * Removes the element at position index (0-based).
     * @return true if successful; false if index is out of bounds
     */
    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false; // out of bounds
        }

        if (index == 0) {
            removeFirst();
            return true;
        }
        if (index == size - 1) {
            removeLast();
            return true;
        }

        // walk to the node just before the target
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next; // skip over the target node
        size--;
        return true;
    }

    /**
     * Returns the index of the first occurrence of item using equals().
     * @return the index if found; size of the list otherwise
     * Time complexity: O(n)
     */
    public int find(T item) {
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            // handle null items gracefully
            if (item == null ? current.data == null : item.equals(current.data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return size; // not found — return size as sentinel
    }

    // ------------ //
    //  Utility     //
    // ------------ //

    /** Returns the number of elements in the list. */
    public int size() {
        return size;
    }

    /** Returns a bracketed, comma-separated string representation. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println("=== SingleLinkedList<T> Demo ===\n");

        SingleLinkedList<Integer> list = new SingleLinkedList<>();

        // addFirst / addLast
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addFirst(5);
        System.out.println("After addFirst(5), addLast(10,20,30): " + list);

        // getFirst / getLast
        System.out.println("getFirst(): " + list.getFirst());
        System.out.println("getLast():  " + list.getLast());

        // insert
        list.insert(2, 15);   // insert 15 at index 2
        System.out.println("After insert(2, 15):  " + list);

        list.insert(0, 1);    // insert at front
        System.out.println("After insert(0, 1):   " + list);

        list.insert(100, 99); // index > size → appends
        System.out.println("After insert(100, 99): " + list);

        // find
        System.out.println("find(15): index = " + list.find(15));
        System.out.println("find(99): index = " + list.find(99));
        System.out.println("find(42): index = " + list.find(42) + " (not found → size)");

        // remove by index
        boolean r = list.remove(0);
        System.out.println("remove(0) returned " + r + ": " + list);

        r = list.remove(list.size() - 1);
        System.out.println("remove(last) returned " + r + ": " + list);

        r = list.remove(100); // out of bounds
        System.out.println("remove(100) returned " + r + " (out of bounds)");

        // removeFirst / removeLast
        System.out.println("removeFirst(): " + list.removeFirst() + " → " + list);
        System.out.println("removeLast():  " + list.removeLast()  + " → " + list);

        // isEmpty
        System.out.println("isEmpty(): " + list.isEmpty());

        // NoSuchElementException on empty list
        try {
            list.getFirst();
        } catch (NoSuchElementException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        System.out.println("\nFinal size: " + list.size());
    }
}