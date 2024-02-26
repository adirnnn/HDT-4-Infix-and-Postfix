package uvg.edu.gt;

abstract class AbstractList<T> {
    abstract void insert(T item);
    abstract T delete();
    abstract T getFirst();
    abstract boolean isEmpty();
}
