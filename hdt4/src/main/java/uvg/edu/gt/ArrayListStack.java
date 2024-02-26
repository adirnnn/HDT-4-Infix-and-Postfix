package uvg.edu.gt;

import java.util.ArrayList;

public class ArrayListStack<T> implements Stack<T> {
    private ArrayList<T> list;

    public ArrayListStack() {
        list = new ArrayList<>();
    }

    @Override
    public void push(T item) {
        list.add(item);
    }

    @Override
    public T pop() {
        if (isEmpty()) return null;
        return list.remove(list.size() - 1);
    }

    @Override
    public T peek() {
        if (isEmpty()) return null;
        return list.get(list.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
