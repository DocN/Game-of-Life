package bcit.comp2526.DoubleLL;

import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * DoubleLL double linklist for generic type.
 * 
 * @author Ryan Chau A00949065
 * @version 1.00
 * @param <E>
 *            Generic data
 */
public class DoubleLinkedList<E> implements Serializable, Iterable<E> {
    private static final long serialVersionUID = 1L;
    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * DoubleLinkedList Constructor for empty list.
     */
    public DoubleLinkedList() {
        this.setHead(null);
        this.setTail(null);
        this.setSize(0);
    }

    /**
     * DoubleLinkedList Constructor for 1 element.
     * 
     * @param data
     *            first element added when making list
     */
    public DoubleLinkedList(E data) {
        Node<E> newNode = new Node<E>(data);
        this.setHead(newNode);
        this.setTail(newNode);
        this.setSize(1);
    }

    /**
     * addToFront adds element to front of list.
     * 
     * @param newData
     *            the new data we're adding.
     * @throws CouldNotAddException
     *             when the data we're adding is null.
     */
    public void addToFront(E newData) throws CouldNotAddException {
        // check if data is null
        if (newData == null) {
            throw new CouldNotAddException(
                    "Error Unable to add null value to list");
        }
        Node<E> newNode = new Node<E>(newData);
        // check if list is empty
        if (this.getFirst() == null) {
            this.setHead(newNode);
            this.setTail(newNode);
            this.setSize(this.getSize() + 1);
            return;
        }
        newNode.setNext(this.head);
        this.getFirst().setPrev(newNode);
        this.setHead(newNode);
        this.setSize(this.getSize() + 1);
    }

    /**
     * removeFromFront removes an element from the front of the list.
     * 
     * @return removed element
     * @throws CouldNotRemoveException
     *             when an element can't be removed.
     */
    public E removeFromFront() throws CouldNotRemoveException {
        // if there's no element to remove
        if (this.getFirst() == null) {
            throw new CouldNotRemoveException(
                    "Error no node to remove from list");
        }
        Node<E> front = this.getFirst();
        // if there's no element after removing.
        if (front.getNext() == null) {
            this.setTail(null);
            this.setHead(null);
            this.setSize(0);
        } else {
            // replace current head with new one.
            this.setHead(front.getNext());
            this.getFirst().setPrev(null);
            this.setSize(this.getSize() - 1);
        }
        return front.getData();
    }

    /**
     * addToEnd adds an element to the end of the list.
     * 
     * @param newData
     *            the data we're adding.
     * @throws CouldNotAddException
     *             when we can't add null data.
     */
    public void addToEnd(E newData) throws CouldNotAddException {
        // when data is null we can't add it
        if (newData == null) {
            throw new CouldNotAddException(
                    "Error Unable to add null value to list");
        }
        Node<E> newNode = new Node<E>(newData);
        // check if there's an empty list
        if (this.getFirst() == null) {
            this.setHead(newNode);
            this.setTail(newNode);
            this.setSize(this.getSize() + 1);
            return;
        }
        // add to the end
        this.getLast().setNext(newNode);
        newNode.setPrev(this.getLast());
        this.setTail(newNode);
        this.setSize(this.getSize() + 1);
    }

    /**
     * removeFromEnd removes from the end of the list.
     * 
     * @return removed element.
     * @throws CouldNotRemoveException
     *             when there's nothing to remove throw.
     */
    public E removeFromEnd() throws CouldNotRemoveException {
        // nothing to remove
        if (this.getLast() == null) {
            throw new CouldNotRemoveException(
                    "Error no node to remove from list");
        }
        Node<E> rear = this.getLast();
        // if there's no elements after removing this one
        if (rear.getPrev() == null) {
            this.setHead(null);
            this.setTail(null);
            this.setSize(0);
        } else {
            this.setTail(rear.getPrev());
            this.getLast().setNext(null);
            this.setSize(this.getSize() - 1);
        }
        return rear.getData();
    }

    /**
     * getLast returns the last element.
     * 
     * @return last element of list.
     */
    public Node<E> getLast() {
        return tail;
    }

    /**
     * setTail sets the new last element in the list.
     * 
     * @param tail
     *            the data value for the last element.
     */
    private void setTail(Node<E> tail) {
        this.tail = tail;
    }

    /**
     * getSize returns the size of the list.
     * 
     * @return size of list.
     */
    private int getSize() {
        return size;
    }

    /**
     * setSize sets the size of the list.
     * 
     * @param size
     *            the new size of the list.
     */
    private void setSize(int size) {
        this.size = size;
    }

    /**
     * getFirst returns the first element in the list.
     * 
     * @return the first element node.
     */
    public Node<E> getFirst() {
        return head;
    }

    /**
     * setHead sets the node head.
     * 
     * @param head
     *            the new node head.
     */
    private void setHead(Node<E> head) {
        this.head = head;
    }

    /**
     * size returns the size of the list.
     * 
     * @return the size of the list int.
     */
    public int size() {
        return this.size;
    }

    /**
     * printList function prints the list with iterator.
     */
    public void printList() {
        DoubleLinkedList<E> array = this;
        for (E current : array) {
            System.out.println(current);
        }
        System.out.println("");
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>() {
            private Node<E> current = head;
            private boolean firstCheck;

            @Override
            public boolean hasNext() {
                if (current == null) {
                    return false;
                }
                if (!firstCheck) {
                    return true;
                }
                return (current.getNext() != null);
            }

            @Override
            public E next() {
                if (!firstCheck) {
                    firstCheck = true;
                    return head.getData();
                }
                current = current.getNext();
                if (current == null) {
                    throw new NoSuchElementException("Warning no next element");
                }
                return current.getData();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
