package uvg.edu.gt;

public class DoublyLinkedList<T> extends AbstractList<T> {
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    @Override
    public void insert(T item) {
        Node<T> newNode = new Node<>(item);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    @Override
    public T delete() {
        if (isEmpty()) return null;
        T data = tail.data;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
        return data;
    }

    @Override
    public T getFirst() {
        if (isEmpty()) return null;
        return head.data;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
