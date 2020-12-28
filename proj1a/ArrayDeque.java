public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size];
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        System.arraycopy(items, 0, other.items, 0, size);
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        /* Case:  First.....................Last */
        if (nextFirst == -1) {
            System.arraycopy(items, 0, a, nextFirst + 1 + size, size - nextFirst - 1);
            nextFirst = nextFirst + size;
            nextLast = nextLast + size;
            items = a;
            return;
        }

        /* Case: .....Last First .......*/
        System.arraycopy(items, nextFirst + 1, a, nextFirst + 1 + size, size - nextFirst - 1);
        nextFirst = nextFirst + size;
        System.arraycopy(items, 0, a, 0, nextLast);

        items = a;
    }

    public void addLast(T item) {
        if (size == items.length) resize(size * 2);

        if (nextLast == items.length) nextLast = 0;

        items[nextLast++] = item;
        size++;
    }

    public void addFirst(T item) {
        if (size == items.length) resize(size * 2);
        if (nextFirst == -1) nextFirst = items.length - 1;
        items[nextFirst--] = item;
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
            if (ptr == items.length)
                ptr = 0;
            System.out.print(items[ptr]);
            ptr++;
        }
    }

    public T removeFirst() {
        /* if the index of First is 0 , which means nextFirst = items.length -1 , set nextFirst = 0, items[0] = null*/
        if (nextFirst == items.length - 1) {
            nextFirst = 0;
            T temp = items[0];
            items[0] = null;
            return temp;
        }
        /* if the index of First if not 0, just set items[nextFirst + 1] = null,nextFirst ++ */
        T temp = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst++;
        return temp;
    }

    public T removeLast() {
        /* if the index of Last is items.length - 1  , which means nextLast = 0,
        set nextLast= items.length -1, items[] = null*/
        if (nextLast == 0) {
            nextLast = items.length - 1;
            T temp = items[items.length - 1];
            items[items.length - 1] = null;
            return temp;
        }

        /* if the index of Last if not items.length - 1, just set items[nextLast - 1] = null,nextFirst -- */
        T temp = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast--;
        return temp;

    }

    public T get(int index) {
        int indexOfArray = (nextFirst + index + 1) % items.length;
        return items[indexOfArray];
    }

    public static void main(String[] args) {
        ArrayDeque<String> test = new ArrayDeque<>();
        test.addFirst("a");
        test.addFirst("b");
        test.addFirst("c");
        test.addFirst("d");
        test.addFirst("e");
        test.addLast("f");
        test.addLast("g");
        test.addLast("h");
        test.addLast("i");
        System.out.print(test.get(8));
    }
}
