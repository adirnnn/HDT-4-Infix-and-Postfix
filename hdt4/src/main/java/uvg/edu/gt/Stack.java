package uvg.edu.gt;

interface Stack<T> {
    void push(T item);
    T pop();
    T peek();
    boolean isEmpty();
}
