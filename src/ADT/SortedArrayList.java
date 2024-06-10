package ADT;

import java.util.NoSuchElementException;

/**
 * SortedArrayList - Implements the ADT Sorted List using an array.- Note: Some
 * methods are not implemented yet and have been left as practical exercises
 *
 * @param <T>
 */
public class SortedArrayList<T extends Comparable<T>> implements SortedArrayListInterface<T> {

    private T[] array;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 5;

    public SortedArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public SortedArrayList(int initialCapacity) {
        numberOfEntries = 0;
        array = (T[]) new Comparable[initialCapacity];
    }

    public boolean add(T newEntry) {
        if (isArrayFull()) {
            doubleArray();
        }

        int i = 0;
        while (i < numberOfEntries && newEntry.compareTo(array[i]) > 0) {
            i++;
        }
        makeRoom(i + 1);
        array[i] = newEntry;
        numberOfEntries++;
        return true;
    }

    public boolean replace(T oldEntry, T updatedEntry) {
        boolean isUpdated = false;
        for (int i = 0; i < numberOfEntries; i++) {
            if (array[i].equals(oldEntry)) {
                array[i] = updatedEntry;
                isUpdated = true;
                break;
            }
        }
        return isUpdated;
    }

    public boolean remove(T anEntry) {

        if (isEmpty()) {
            return false;
        }

        int index = containsBNS(anEntry);

        if (index == -1) {
            return false;
        }

        numberOfEntries--;

        removeGap(index);
        // shift the appropriate elements 
//        for (int scan=index; scan < numberOfEntries; scan++)
//            array[scan] = array[scan+1];

        array[numberOfEntries] = null;

        return true;
    }

    private int find(T target) {

        boolean found = false;
        int indexFound = 0;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (target.equals(array[index])) {
                found = true;
                indexFound = index;
            }
        }
        return indexFound;
    }

    public int containsBNS(T anEntry) {

        int low = 0, high = numberOfEntries - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (anEntry.equals(array[mid])) {
                return mid;
            }
            if (anEntry.compareTo(array[mid]) > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public void clear() {
        numberOfEntries = 0;
    }

    public boolean contains(T anEntry) {
        boolean found = false;
        for (int index = 0; !found && (index < numberOfEntries); index++) {
            if (anEntry.equals(array[index])) {
                found = true;
            }
        }
        return found;
    }

    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    public String toString() {
        String outputStr = "";
        for (int index = 0; index < numberOfEntries; ++index) {
            outputStr += array[index] + "\n";
        }

        return outputStr;
    }

    private boolean isArrayFull() {
        return numberOfEntries == array.length;
    }

    private void doubleArray() {
        T[] oldList = array;
        array = (T[]) new Comparable[2 * oldList.length];  //new Object The erased type of the T[] tab will be Comparable[].
        for (int index = 0; index < numberOfEntries; index++) {
            array[index] = oldList[index];
        }
    }

    private void makeRoom(int newPosition) {
        int newIndex = newPosition - 1;
        int lastIndex = numberOfEntries - 1;

        for (int index = lastIndex; index >= newIndex; index--) {
            array[index + 1] = array[index];
        }
    }

    private void removeGap(int givenPosition) {
        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;

        for (int index = removedIndex; index < lastIndex; index++) {
            array[index] = array[index + 1];
        }
    }

    public void removeStartToEnd(int start, int end) {
        start--;
        end--;
        int totalLeft = numberOfEntries - (end - start) - 1;
        int count = 0;

        for (int index = start; index < totalLeft; index++) {
            array[index] = array[end + 1];
            System.out.println(end);
            if (count <= totalLeft) {
                end++;
                count++;
            }
        }
        numberOfEntries = totalLeft;
        array[numberOfEntries] = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new SortedArrayListIterator<T>(array, numberOfEntries);
    }

    @Override
    public int getSize() {
        return numberOfEntries;
    }

    private class SortedArrayListIterator<T> implements Iterator<T> {

        private T[] items;
        private int count;
        private int current;
        int lastRet = -1; // index of last element returned; -1 if no such

        public SortedArrayListIterator(T[] elements, int size) {
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
            SortedArrayList.this.removeGap(current);
            numberOfEntries--;
        }

    }

}
