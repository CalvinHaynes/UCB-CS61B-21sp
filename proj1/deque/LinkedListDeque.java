package deque;

public class LinkedListDeque<T> {
    private class StuffNode {
        private T item;
        private StuffNode prev;
        private StuffNode next;

        public StuffNode(T item, StuffNode prev, StuffNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private StuffNode sentinelNode;
    private int size;

    public LinkedListDeque() {
        sentinelNode = new StuffNode(null,null,null);
        sentinelNode.prev = sentinelNode;
        sentinelNode.next = sentinelNode;
        size = 0;
    }

    public void addFirst(T item) {
        StuffNode first = new StuffNode(item, sentinelNode, sentinelNode.next);
        sentinelNode.next.prev = first;
        sentinelNode.next = first;
        size += 1;
    }

    public void addLast(T item) {
        StuffNode last = new StuffNode(item, sentinelNode.prev, sentinelNode);
        sentinelNode.prev.next = last;
        sentinelNode.prev = last;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        StuffNode printNode = sentinelNode.next;
        for (int i = 0; i < size; i++) {
            System.out.print(printNode.item + " ");
            printNode = printNode.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()){
            return null;
        } else {
            T removedItem = sentinelNode.next.item;
            sentinelNode.next.next.prev = sentinelNode;
            sentinelNode.next = sentinelNode.next.next;
            size -= 1;
            return removedItem;
        }
    }

    public T removeLast() {
        if (isEmpty()){
            return null;
        } else {
            T removedItem = sentinelNode.prev.item;
            sentinelNode.prev.prev.next = sentinelNode;
            sentinelNode.prev = sentinelNode.prev.prev;
            size -= 1;
            return removedItem;
        }
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds!");
        } else {
            StuffNode getNode = sentinelNode.next;
            for (int i = 0; i < index; i++) {
                getNode = getNode.next;
            }
            return getNode.item;
        }
    }

}
