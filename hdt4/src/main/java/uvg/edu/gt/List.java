package uvg.edu.gt;

interface List<T> {
    void insert(T item);
    T delete();
    T getFirst();
    boolean isEmpty();
}
