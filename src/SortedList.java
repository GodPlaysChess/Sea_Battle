

import com.sun.deploy.panel.ITreeNode;

import java.security.PublicKey;
import java.util.*;

public class SortedList<E> implements List<E>, RandomAccess, Cloneable, java.io.Serializable, Comparator, Comparable {

    private transient Object[] elementData;
    private int size;
    private int lastnumber;

    public SortedList(int initialCapacity) {
        lastnumber = 0;
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        this.elementData = new Object[initialCapacity];
    }

    public SortedList() {
        this(10);
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
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }


    @Override
    public int indexOf(Object o) {

        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }


    @Override
    public Iterator<E> iterator() {
        return new Itr();
    }

    @Override
    public int compareTo(Object o) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private class Itr implements Iterator<E> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public E next() {

            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = SortedList.this.elementData;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();


            try {
                SortedList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;

            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            // Make a new array of a's runtime type, but my contents:
            return (T[]) Arrays.copyOf(elementData, size, a.getClass());
        System.arraycopy(elementData, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }


    @Override
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }


    public boolean add(Comparable<E> e) {
        ensureCapacity();
        //copy before index
        Object[] tempdata;
        tempdata = Arrays.copyOf(elementData, size);
        System.arraycopy(tempdata, 0, elementData, 0, findInsertionIndex(e));
        elementData[findInsertionIndex(e)] = e;
        System.arraycopy(tempdata, findInsertionIndex(e), elementData, findInsertionIndex(e) + 1, size - findInsertionIndex(e));
        //copy everything after index

        lastnumber++;
        return true;
    }

    private int findInsertionIndex(Comparable<E> e) {
        if (lastnumber == 0)
            return 0;
        int subsize = size;
        return (findInsertionIndex(e, subsize / 2, subsize));

    }

    private int findInsertionIndex(Comparable<E> e, int currentIndex, int subsize) {
        if (subsize == 2)
            return currentIndex + e.compareTo(this.get(currentIndex)); //check this precisely
        subsize = subsize / 2;
        if (e.compareTo(this.get(currentIndex)) >= 0)
            findInsertionIndex(e, currentIndex + subsize, subsize);
        else findInsertionIndex(e, currentIndex - subsize, subsize);

        return -1;
    }


    public void ensureCapacity() {
        if (lastnumber == size) {
            elementData = Arrays.copyOf(elementData, size * 2);
        }
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    fastRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
        }
        return false;
    }

    private void fastRemove(int index) {
              int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null; // Let gc do its work
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void clear() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public E get(int index) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Override
    public int lastIndexOf(Object o) {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

