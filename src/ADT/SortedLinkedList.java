package ADT;

import ADT.SortedListInterface;

public class SortedLinkedList<T extends Comparable<T>> implements SortedListInterface<T> {

    private Node firstNode;
    private int numberOfEntries;

    public SortedLinkedList() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);

        Node nodeBefore = null;
        Node currentNode = firstNode;
        while (currentNode != null && newEntry.compareTo(currentNode.data) > 0) {
            nodeBefore = currentNode;
            currentNode = currentNode.next;
        }

        if (isEmpty() || (nodeBefore == null)) { // CASE 1: add at beginning
            newNode.next = firstNode;
            firstNode = newNode;
        } else {	// CASE 2: add in the middle or at the end, i.e. after nodeBefore
            newNode.next = currentNode;
            nodeBefore.next = newNode;
        }
        numberOfEntries++;
        return true;
    }

    public boolean replace(int givenPosition, T newEntry) {
        boolean replacedNew = true;
        if ((givenPosition > 0) && (givenPosition <= numberOfEntries)) {
            Node currentNode = firstNode;
            if (givenPosition == 1) {
                currentNode.data = newEntry;
            } else {
                for (int i = 1; i < givenPosition; ++i) {
                    currentNode = currentNode.next;
                }
                currentNode.data = newEntry;
            }
        } else {
            replacedNew = false;
        }
        sortList();
        return replacedNew;
    }

    @Override
    public boolean remove(T anEntry) {
        if (firstNode == null) {
            return false;
        }

        Node current = firstNode;
        Node beforeNode = null;
        while (current.next != null && current.data.compareTo(anEntry) < 0) {
            beforeNode = current;
            current = current.next;
        }

        if (current.data == anEntry && current.data.equals(anEntry)) {
            if (current == firstNode) {
                firstNode = firstNode.next;
            } else {
                System.out.println(current.next);
                beforeNode.next = current.next;
            }
            numberOfEntries--;
            return true;
        }

        return false;
    }



    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node tempNode = firstNode;

        while (!found && (tempNode != null)) {
            if (anEntry.compareTo(tempNode.data) <= 0) {
                found = true;
            } else {
                tempNode = tempNode.next;
            }
        }
        if (tempNode != null && tempNode.data.equals(anEntry)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return (numberOfEntries == 0);
    }

    private class Node {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        String outputStr = "";
        Node currentNode = firstNode;
        while (currentNode != null) {
            outputStr += currentNode.data + "\n";;
            currentNode = currentNode.next;
        }
        return outputStr;
    }

    public Iterator<T> iterator() {
        return new SortedLinkedListIterator<T>(firstNode) {

        };
    }

    class SortedLinkedListIterator<T> implements Iterator<T> {

        Node current;

        public SortedLinkedListIterator(Node current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public void remove() {

        }

        @Override
        public T next() {
            T data = (T) current.data;
            current = current.next;
            return data;
        }

    }



    public void sortList()
    {

        // Node current will point to head
        Node current = firstNode, index = null;

        T temp;

        if (firstNode == null) {
            return;
        }
        else {
            while (current != null) {
                // Node index will point to node next to
                // current
                index = current.next;

                while (index != null) {
                    // If current node's data is greater
                    // than index's node data, swap the data
                    // between them

                    if (current.data.compareTo(index.data)>0) {
                        temp = current.data;
                        current.data = index.data;
                        index.data = temp;
                    }

                    index = index.next;
                }
                current = current.next;
            }
        }
    }
}
