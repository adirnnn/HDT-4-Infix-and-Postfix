package uvg.edu.gt;

public class SinglyLinkedList<T> extends AbstractList<T> {
    private Node<T> head;

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    @Override
    public void insert(T item) {
        Node<T> newNode = new Node<>(item);
        newNode.next = head;
        head = newNode;
    }

    @Override
    public T delete() {
        if (isEmpty()) return null;
        T data = head.data;
        head = head.next;
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
