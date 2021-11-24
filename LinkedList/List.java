/**
 * @author Shiv Patel
 * @created on 22/11/2021 at 02:38 AM
 */

public interface List<E> {

    int size();

    boolean isEmpty();

    E first();

    E last();

    void addFirst(E element);

    void addLast(E element);

    void add(int index, E element);

    E removeFirst();

    E removeLast();

    E remove(int index);

    E set(int index, E element);
}

