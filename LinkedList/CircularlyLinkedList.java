/**
 * @author Shiv Patel
 * @created on 23/11/2021 at 09:47 AM
 */
public class CircularlyLinkedList<E> implements List<E> {

    private Node<E> tail;
    private int size;

    public static void main(String[] args) {
        CircularlyLinkedList<String> list = new CircularlyLinkedList<>();
        String[] names = {"Alex", "Bob", "Eren", "Justin", "Blake", "Denis", "Zee"};
        for (int i = 0; i < names.length; i += 2) {
            list.addFirst(names[i]);
            if (i + 1 < names.length)
                list.addLast(names[i + 1]);
        }
        System.out.println(list);
        System.out.println("List size: " + list.size);
        System.out.println("Removed first: " + list.removeFirst());
        System.out.println("Removed last: " + list.removeLast());
        System.out.println("First Element: " + list.first());
        System.out.println("Last Element: " + list.last());

        System.out.println("List after operations: " + list);
        list.rotate();
        System.out.println("List after rotation: " + list);

        System.out.println("Removed at index 3: " + list.remove(3));
        list.add(2, "Flake");
        System.out.println("Added Flake at index 2: " + list);

        System.out.printf("\"%s\" changed to \"%s\"\n", list.set(3, "Jacob"), "Jacob");
        System.out.println("Updated list: " + list);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E first() {
        return isEmpty() ? null : tail.getNext().getElement();
    }

    @Override
    public E last() {
        return isEmpty() ? null : tail.getElement();
    }

    public void rotate() {
        if (tail != null)
            tail = tail.getNext();
    }

    @Override
    public void addFirst(E element) {
        if (isEmpty()) {
            tail = new Node<>(element, null);
            tail.setNext(tail);
        } else {
            tail.setNext(new Node<>(element, tail.getNext()));
        }
        size++;
    }

    @Override
    public void addLast(E element) {
        addFirst(element);
        rotate();
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Invalid index.");
        if (index == 0)
            addFirst(element);
        else if (index == size)
            addLast(element);
        else {
            Node<E> previous = tail.getNext();
            for (int i = 0; i < index - 1; i++)
                previous = previous.getNext();
            previous.setNext(new Node<>(element, previous.getNext()));
            size++;
        }
    }

    @Override
    public E removeFirst() {
        if (tail == null)
            throw new IllegalStateException("The list is empty.");
        Node<E> head = tail.getNext();
        if (head == tail)
            tail = null;
        else {
            tail.setNext(head.getNext());
        }
        size--;
        return head.getElement();
    }

    @Override
    public E removeLast() {
        for (int i = 0; i < size - 1; i++)
            rotate();
        return removeFirst();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index.");
        if (index == 0)
            return removeFirst();
        else if (index == size - 1)
            return removeLast();
        Node<E> previous = tail;
        for (int i = 0; i < index; i++)
            previous = previous.getNext();
        Node<E> current = previous.getNext();
        previous.setNext(current.getNext());
        size--;
        return current.getElement();
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index.");
        Node<E> current = tail;
        for (int i = 0 ; i < index + 1 ; i++)
            current = current.getNext();
        E oldElement = current.getElement();
        current.setElement(element);
        return oldElement;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "<< >>";

        StringBuilder sb = new StringBuilder("<< ");
        Node<E> pointer = tail.getNext();
        boolean flag = false;
        while (!flag) {
            sb.append(pointer.getElement()).append(" ");
            if (pointer == tail)
                flag = true;
            else
                pointer = pointer.getNext();
        }
        return sb.append(">>").toString();
    }

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

}
