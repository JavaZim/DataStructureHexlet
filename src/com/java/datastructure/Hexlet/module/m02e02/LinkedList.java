package com.java.datastructure.Hexlet.module.m02e02;

import java.util.*;
import java.util.Arrays;
import java.util.List;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;

public class LinkedList<T> implements List<T> {

    private Item<T> first = null;

    private Item<T> last = null;

    private int size;

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public boolean contains(final Object o) {
        // BEGIN (write your solution here)
        for (Item<T> currentItem = first; currentItem != null; currentItem = currentItem.next) {
            if (currentItem.element.equals(o)) {
                return true;
            }
        }
        return false;
        // END
    }

    @Override
    public Iterator<T> iterator() {
        return new ElementsIterator();
    }

    @Override
    public Object[] toArray() {
        // BEGIN (write your solution here)

        Object[] array = new Object[this.size()];
        int index = 0;
        for (Item<T> currentItem = first; currentItem != null; currentItem = currentItem.next) {
            array[index++] = currentItem.element;
        }

        return array;
        // END
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        // BEGIN (write your solution here)
        if (a.length < size) {
            a = (T1[]) new Integer[size()];
        }

        int index = 0;
        Object[] result = a;

        for (Item<T> currentItem = first; currentItem != null; currentItem = currentItem.next) {
            result[index++] = currentItem.element;
        }

        if (a.length > size()) {
            a[size()] = null;
        }

        return a;
        // END
    }

    @Override
    public boolean add(final T t) {
        // BEGIN (write your solution here)
        linkNewItem(t);
        return true;
        // END
    }

    @Override
    public boolean remove(final Object o) {
        // BEGIN (write your solution here)
        for (Item<T> currentItem = first; currentItem != null; currentItem = currentItem.next) {

            if (currentItem.element.equals(o)) {

                Item<T> prevItem = currentItem.prev;
                Item<T> nextItem = currentItem.next;

                if (prevItem == null) {
                    first = nextItem;
                } else {
                    prevItem.next = nextItem;
                    currentItem.prev = null;
                }

                if (nextItem == null) {
                    last = prevItem;
                } else {
                    nextItem.prev = prevItem;
                    currentItem.next = null;
                }

                currentItem.element = null;
                size--;
                return true;
            }

        }

        return false;
        // END
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        for (final Object item : c) {
            if (!this.contains(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends T> c) {
        for (final T item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        for (final Object item : c) {
            remove(item);
        }
        return true;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        for (final Object item : this) {
            if (!c.contains(item)) this.remove(item);
        }
        return true;
    }

    @Override
    public void clear() {
        // BEGIN (write your solution here)
        for (Item<T> current = first; current != null; ) {

            Item<T> nextItem = current.next;

            current.element = null;
            current.prev = null;
            current.next = null;
            current = nextItem;
        }

        first = last = null;
        size = 0;
        // END
    }

    @Override
    public T remove(final int index) {
        // BEGIN (write your solution here)
        Item<T> currentItem = first;
        for (int i = 0; i < index; i++) {
            currentItem = currentItem.next;
        }
        remove(currentItem);
        return currentItem.element;
        // END
    }

    // BEGIN (write your solution here)

    // END
    @Override
    public List<T> subList(final int start, final int end) {
        return null;
    }

    @Override
    public ListIterator listIterator() {
        return new ElementsIterator(0);
    }

    @Override
    public ListIterator listIterator(final int index) {
        return new ElementsIterator(index);
    }

    @Override
    public int lastIndexOf(final Object target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object target) {
        // BEGIN (write your solution here)
        int index = 0;
        for (Item<T> currentItem = first; currentItem != null; currentItem = currentItem.next) {
            if (currentItem.getElement().equals(target)) {
                return index;
            }
            index++;
        }
        return -1;// END
    }

    @Override
    public void add(final int index, final T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(final int index, final Collection elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(final int index, final T element) {
        // BEGIN (write your solution here)
        int indexSourceItem = 0;
        Item<T> previousSpecItem = null;
        for (Item<T> currentItem = first; currentItem != null; currentItem = currentItem.next) {
            if (indexSourceItem == index) {
                previousSpecItem = currentItem;
                currentItem.element = element;
            }
            indexSourceItem++;
        }
        return previousSpecItem.getElement();
        // END
    }

    @Override
    public T get(final int index) {
        // BEGIN (write your solution here)
        int indexSourceItem = 0;
        for (Item<T> currentItem = first; currentItem != null; currentItem = currentItem.next) {
            if (indexSourceItem == index) {
                return currentItem.element;
            }
            indexSourceItem++;
        }
        return null;
        // END
    }

    // BEGIN (write your solution here)
    private void linkNewItem(T t) {
        Item<T> lastItem = last;
        Item<T> newItem = new Item(t, lastItem, null);
        last = newItem;
        if (lastItem == null) {
            first = newItem;
        } else {
            lastItem.next = newItem;
        }
        size++;
    }


    private Item<T> getItem(int index) {

        int indexItem = 0;
        Item<T> item = first;
        for (int i = 0; i < index; i++) {
            item = item.next;
        }
        return item;
    }
// END

    private class ElementsIterator implements ListIterator<T> {

        private Item<T> lastReturned;

        private Item<T> next;

        private int index;

        public ElementsIterator() {
            this(0);
        }

        public ElementsIterator(final int index) {

            // BEGIN (write your solution here)
            next = (index == size) ? null : getItem(index);
            this.index = index;
            // END
        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public T next() {
            // BEGIN (write your solution here)
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            lastReturned = next;
            index++;
            next = next.getNext();
            return lastReturned.getElement();
            // END
        }

        @Override
        public void add(final T element) {

            lastReturned = null;
            if (next == null) {
                linkNewItem(element);
            }
            index++;
        }

        @Override
        public void set(final T element) {
            // BEGIN (write your solution here)
            if (lastReturned == null)
                throw new IllegalStateException();
            lastReturned.element = element;
            // END
        }

        @Override
        public int previousIndex() {
            // BEGIN (write your solution here)
            return index - 1;
            // END
        }

        @Override
        public int nextIndex() {
            // BEGIN (write your solution here)
            return index + 1;
            // END
        }

        @Override
        public boolean hasPrevious() {
            // BEGIN (write your solution here)
            if (size == 0) {
                return false;
            } else {
                return true;
            }
            // END
        }

        @Override
        public T previous() {
            // BEGIN (write your solution here)
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            return lastReturned.element;
            // END
        }

        @Override
        public void remove() {
            // BEGIN (write your solution here)

            if (lastReturned == null)
                throw new IllegalStateException();
            LinkedList.this.remove(lastReturned.element);
            lastReturned = null;
            index--;


            // END
        }

    }

    private static class Item<T> {

        private T element;

        private Item<T> next;

        private Item<T> prev;

        public Item(final T element, final Item<T> prev, final Item<T> next) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        public T getElement() {
            return element;
        }

        public Item<T> getNext() {
            return next;
        }

        public Item<T> getPrev() {
            return prev;
        }

    }

}

