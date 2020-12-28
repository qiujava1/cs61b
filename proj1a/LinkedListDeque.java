public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;

    private class Node {
        T item;
        Node next;
        Node prev;

        public Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /* for the autograder, comment the constructor */
//    public LinkedListDeque(LinkedListDeque other) {
//        size = other.size;
//        sentinel = new Node(null, null, null);
//        Node ptr = other.sentinel.next;
//        Node ptr1 = new Node(ptr.item, sentinel, sentinel);
//        sentinel.next = ptr1;
//        sentinel.prev = ptr1;
//        while (ptr.next != other.sentinel) {
//            ptr = ptr.next;
//            Node temp = new Node(ptr.item, sentinel, ptr1);
//            sentinel.prev = temp;
//            ptr1.next = temp;
//            ptr1 = ptr1.next;
//        }
//    }

    public void addFirst(T item) {
        Node first = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = first;
        sentinel.next = first;
        size++;
    }

    public void addLast(T item) {
        Node last = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        T res = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return res;
    }

    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        T res = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return res;
    }

    public T get(int index) {
        if (index < 0 && index >= size) {
            return null;
        }
        Node ptr = sentinel.next;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

    private T getRecursive(int index, Node curr) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursive(index - 1, curr.next);
    }

}
