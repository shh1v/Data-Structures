/**
 * @author Shiv Patel
 * @created on 23/11/2021 at 01:42 PM
 */
public class DoublyLinkedList<E> implements List<E> {
    private final Node<E> header, trailer;
    private int size;

    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, null, header);
        header.setNext(trailer);
    }

    public static void main(String[] args) {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
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
        return isEmpty() ? null : header.getNext().getElement();
    }

    @Override
    public E last() {
        return isEmpty() ? null : trailer.getPrev().getElement();
    }

    @Override
    public void addFirst(E element) {
        addBetween(element, header, header.getNext());
    }

    @Override
    public void addLast(E element) {
        addBetween(element, trailer.getPrev(), trailer);
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Invalid index.");
        Node<E> current = trailer.getNext();
        for (int i = 0; i < index; i++)
            current = current.getNext();
        addBetween(element, current.getPrev(), current.getNext());
    }

    @Override
    public E removeFirst() {
        if (isEmpty())
            throw new IllegalStateException("The list is empty.");
        else
            return removeNode(header.getNext()).getElement();
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            throw new IllegalStateException("The list is empty.");
        else
            return removeNode(trailer.getPrev()).getElement();
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalStateException("Invalid index.");

        Node<E> current = header.getNext();
        for (int i = 0; i < index; i++)
            current = current.getNext();
        return removeNode(current).getElement();
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("The list is empty.");
        Node<E> current = header.getNext();
        for (int i = 0; i < index; i++)
            current = current.getNext();
        E oldElement = current.getElement();
        current.setElement(element);
        return oldElement;
    }

    private void addBetween(E element, Node<E> previous, Node<E> following) {
        Node<E> node = new Node<>(element, previous, following);
        previous.setNext(node);
        following.setPrev(node);
        size++;
    }

    private Node<E> removeNode(Node<E> current) {
        Node<E> previous = current.getPrev();
        Node<E> following = current.getNext();
        previous.setNext(following);
        following.setPrev(previous);
        size--;
        return current;
    }

    @Override
    public String toString() {
        Node<E> pointer = header.getNext();
        StringBuilder sb = new StringBuilder("<< ");
        while (pointer != trailer) {
            sb.append(pointer.getElement()).append(" ");
            pointer = pointer.getNext();
        }
        return sb.append(">> ").toString();
    }

    private static class Node<E> {
        private E element;
        private Node<E> next, prev;

        public Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
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

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }
}
