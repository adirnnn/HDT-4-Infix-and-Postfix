package uvg.edu.gt;

import java.util.Vector;

public class VectorStack<T> implements Stack<T> {
    private Vector<T> vector;

    public VectorStack() {
        vector = new Vector<>();
    }

    @Override
    public void push(T item) {
        vector.add(item);
    }

    @Override
    public T pop() {
        if (isEmpty()) return null;
        return vector.remove(vector.size() - 1);
    }

    @Override
    public T peek() {
        if (isEmpty()) return null;
        return vector.get(vector.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return vector.isEmpty();
    }   
}
