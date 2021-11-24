/**
 * @author Shiv Patel
 * @created on 22/11/2021 at 02:46 AM
 */
public class SinglyLinkedList<E> implements List<E> {

    private Node<E> head, tail;
    private int size;

    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
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
        return isEmpty() ? null : head.getElement();
    }

    @Override
    public E last() {
        return isEmpty() ? null : tail.getElement();
    }

    @Override
    public void addFirst(E element) {
        head = new Node<>(element, head);
        if (head.getNext() == null)
            tail = head;
        size++;
    }

    @Override
    public void addLast(E element) {
        if (isEmpty())
            addFirst(element);
        else {
            tail.setNext(new Node<>(element, null));
            tail = tail.getNext();
            size++;
        }
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Invalid index");
        if (index == 0)
            addFirst(element);
        else if (index == size)
            addLast(element);
        else {
            Node<E> previous = head;
            for (int i = 0; i < index - 1; i++)
                previous = previous.getNext();
            previous.setNext(new Node<>(element, previous.next));
            size++;
        }
    }

    @Override
    public E removeFirst() {
        if (isEmpty())
            throw new IllegalStateException("The list is empty.");
        E removedElement = head.getElement();
        head = head.getNext();
        if (head == null)
            tail = null;
        size--;
        return removedElement;
    }

    @Override
    public E removeLast() {
        if (isEmpty() || size == 1)
            return removeFirst();
        Node<E> secondLast = head;
        E removedElement = tail.getElement();
        for (int i = 0; i < size - 2; i++) {
            secondLast = secondLast.getNext();
        }
        secondLast.setNext(null);
        tail = secondLast;
        size--;
        return removedElement;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index.");
        if (index == 0)
            return removeFirst();
        else if (index == size - 1)
            return removeLast();
        Node<E> previous = head;
        for (int i = 0; i < index - 1; i++)
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
        Node<E> current = head;
        for (int i = 0; i < index; i++)
            current = current.getNext();
        E oldElement = current.getElement();
        current.setElement(element);
        return oldElement;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder("<< ");
        Node<E> pointer = head;
        while (pointer != null) {
            string.append(pointer.getElement()).append(" ");
            pointer = pointer.getNext();
        }
        return string.append(">>").toString();
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
