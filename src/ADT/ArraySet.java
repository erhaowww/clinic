/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import java.util.NoSuchElementException;

/**
 *
 * @author star
 * @param <T>
 */
public class ArraySet<T> implements SetInterface<T> {

    T[] setArray;
    int numberOfElements;
    private static final int DEFAULT_CAPACITY = 10;

    public ArraySet() {
        this(DEFAULT_CAPACITY);
    }

    public int getSize() {
        return numberOfElements;
    }

    public ArraySet(int initialCapacity) {
        numberOfElements = 0;
        setArray = (T[]) new Object[initialCapacity];
    }

    @Override
    public boolean add(T newElement) {
        for (int i = 0; i < numberOfElements; i++) {
            if (setArray[i] == newElement) {
                return false;
            }
        }

        if (isArrayFull()) {
            doubleArray();
        }
        setArray[numberOfElements] = newElement;
        numberOfElements++;
        return true;
    }

    @Override
    public boolean remove(T newElement) {
        for (int i = 0; i < numberOfElements; i++) {
            if (setArray[i].equals(newElement)) {
                removeGap(i);
                numberOfElements--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkSubset(SetInterface anotherSet) {
        boolean success = false;
        int num = 0;
        if (anotherSet instanceof ArraySet) {
            ArraySet aSet = (ArraySet) anotherSet;

            if (aSet.numberOfElements > this.numberOfElements) {
                System.out.println("false 1");
                return success;
            }
            for (int i = 0; i < aSet.numberOfElements; i++) {
                for (int j = 0; j < numberOfElements; j++) {
                    if (aSet.setArray[i].equals(setArray[j])) {
                        num++;
                    }
                }

            }
            if (num == aSet.numberOfElements) {
                success = true;
            } else {
                success = false;
            }
            return success;
        }

        return success;
    }

    @Override
    public void union(SetInterface anotherSet) {
        if (anotherSet instanceof ArraySet) {
            ArraySet aSet = (ArraySet) anotherSet;
            for (int i = 0; i < aSet.numberOfElements; i++) {
                add((T) aSet.setArray[i]);

            }
        }
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    @Override
    public SetInterface intersection(SetInterface anotherSet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void doubleArray() {
        T[] oldArray = setArray;
        setArray = (T[]) new Object[2 * oldArray.length];

        for (int i = 0; i < numberOfElements; i++) {
            setArray[i] = oldArray[i];
        }

    }

    public boolean isArrayFull() {
        return numberOfElements == setArray.length;
    }

    private void removeGap(int givenPosition) {
        // move each entry to next lower position starting at entry after the
        // one removed and continuing until end of array
        int removedIndex = givenPosition;
        int lastIndex = numberOfElements;

        for (int index = removedIndex; index < lastIndex; index++) {
            setArray[index] = setArray[index + 1];
        }
    }

    @Override
    public String toString() {
        String outputStr = "";
        for (int i = 0; i < numberOfElements; i++) {
            outputStr += setArray[i] + "\n";
        }
        return outputStr;
    }

    public boolean contains(T element) {

        if (numberOfElements > 0) {
            for (int i = 0; i < numberOfElements; i++) {
                if (setArray[i].equals(element)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Iterator<T> iterator() {
        return new ArrayIterator<T>(setArray, numberOfElements);
    }

    @Override
    public void clear() {
        numberOfElements=0;
    }

    private class ArrayIterator<T> implements Iterator<T> {

        private T[] items;
        private int count;
        private int current;

        public ArrayIterator(T[] elements, int size) {
            items = elements;
            count = size;
            current = 0;
        }

        @Override
        public boolean hasNext() {
            return (current < count);
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T result = items[current];
            current++;
            return result;
        }

        @Override
        public void remove() {
            ArraySet.this.removeGap(current);
            numberOfElements--;
        }

    }

  
}
