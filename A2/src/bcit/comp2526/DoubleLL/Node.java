package bcit.comp2526.DoubleLL;

import java.io.Serializable;

/**
 * Node for Double linkedlist.
 * 
 * @author DrN
 * @version 1.00
 * @param <E>
 *            the data element type.
 */
public class Node<E> implements Serializable {
    /**
     * default serial.
     */
    private static final long serialVersionUID = 1L;
    private E data;
    private Node<E> next;
    private Node<E> prev;

    /**
     * Node constructor for one element.
     * 
     * @param data
     *            the element.
     */
    Node(E data) {
        this.setData(data);
        this.init();
    }

    /**
     * init - initialize node pointers to null.
     */
    private void init() {
        this.setPrev(null);
        this.setNext(null);
    }

    /**
     * getData returns the data stored in node.
     * 
     * @return E data stored in node.
     */
    public E getData() {
        return data;
    }

    /**
     * setData sets the data value in the node.
     * 
     * @param data
     *            the new data value
     */
    public void setData(E data) {
        this.data = data;
    }

    /**
     * getNext gets the next node in the list.
     * 
     * @return the next node.
     */
    public Node<E> getNext() {
        return next;
    }

    /**
     * setNext sets the next node in the list.
     * 
     * @param next
     *            the next node in the list.
     */
    public void setNext(Node<E> next) {
        this.next = next;
    }

    /**
     * getPrev gets the previous node in the list.
     * 
     * @return previous node.
     */
    public Node<E> getPrev() {
        return prev;
    }

    /**
     * setPrev sets the previous node in the list.
     * 
     * @param prev
     *            the new previous node.
     */
    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }

    /**
     * intValue required for returning data in the unit tester.
     * 
     * @return integer value of data.
     */
    public int intValue() {
        return (int) this.getData();
    }
}
