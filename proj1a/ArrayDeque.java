public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    //    public ArrayDeque(ArrayDeque other) {
//        items = (T[]) new Object[other.size];
//        size = other.size;
//        nextFirst = other.nextFirst;
//        nextLast = other.nextLast;
//        System.arraycopy(items, 0, other.items, 0, size);
//    }
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length;
    }

    private boolean isFull() {
        return size == items.length;
    }

    private void upsize() {
        resize(size * 2);
    }

    private boolean ifSparse() {
        return size <= items.length / 4;
    }

    private void downsize() {
        resize(size / 2);
    }

    private void resize(int capacity) {
        T[] newDeque = (T[]) new Object[capacity];
        int oldIndex = plusOne(nextFirst);
        for (int newIndex = 0; newIndex < size; newIndex++) {
            newDeque[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = newDeque;
    }

    public void addLast(T item) {
        if (isFull()) {
            upsize();
        }

        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }

    public void addFirst(T item) {
        if (isFull()) {
            upsize();
        }
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int ptr = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            System.out.print(items[ptr]);
            ptr = plusOne(ptr);
        }
        System.out.println();
    }

    public T removeFirst() {
        if (ifSparse()) {
            downsize();
        }
        nextFirst = plusOne(nextFirst);
        T toRemove = items[nextFirst];
        items[nextFirst] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;
    }

    public T removeLast() {
        if (ifSparse()) {
            downsize();
        }
        nextLast = minusOne(nextLast);
        T toRemove = items[nextLast];
        items[nextLast] = null;
        if (!isEmpty()) {
            size -= 1;
        }
        return toRemove;

    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }

        int start = plusOne(nextFirst);
        return items[(start + index) % items.length];
    }
}
