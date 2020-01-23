package LinkedLists;


class Nodde<T> {
    Nodde<T> next;
    T item;

    Nodde(T item) {
        this.item = item;
    }
}


class LinkedList<T> {

    private Nodde<T> first;
    private Nodde<T> last;

    public LinkedList(Nodde<T> first) {
        this.first = first;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Nodde x = first; x != null; x = x.next)
            hashCode = 31*hashCode + (x == null ? 0 : x.hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LinkedList<T> other = (LinkedList<T>) obj;
        Nodde<T> currentListNode = first;
        Nodde<T> otherListNode =  other.first;

        while (currentListNode != null && otherListNode != null) {

            if (currentListNode.item != otherListNode.item) {
                System.out.println(currentListNode.item);
                return false;
            }
            currentListNode = currentListNode.next;
            otherListNode = otherListNode.next;
        }

        return currentListNode == null && otherListNode == null;
    }
}


/**
 * References:
 * http://www.geeksforgeeks.org/given-only-a-pointer-to-a-node-to-be-deleted-in-a-singly-linked-list-how-do-you-delete-it/
 * http://stackoverflow.com/questions/17539326/delete-a-last-node-of-linked-list-given-pointer-to-that-node
 * http://codereview.stackexchange.com/questions/59349/given-only-a-pointer-to-a-node-to-be-deleted-in-a-singly-linked-list-how-do-you
 *
 * BB:
 * 32
 *
 * Complexity:
 * O(1)
 *
 */
public final class DeletePtr<T> {

    private DeletePtr() {}

    public static <T> void delete(Nodde<T> ptr) {

        if (ptr == null) {
            throw new IllegalArgumentException("The pointer cannot be null.");
        }

        // answer either of the following.
        if (ptr.next == null) {
            // linkedlist.removeLast();
            // OR
            //
            // if (first == ptr) {
            //    linkedlist.setFirst(null);
            // }
            //
            // for (Node<T> curr = ll.getFirst(); curr.next != ptr; curr = curr.next);
            // curr.next = null;
        }

        ptr.item = ptr.next.item;
        ptr.next = ptr.next.next;
    }


    public static void main(String[] args) {
        Nodde<Integer> node1 = new Nodde<Integer>(10);
        Nodde<Integer> node2 = new Nodde<Integer>(20);
        Nodde<Integer> node3 = new Nodde<Integer>(30);
        Nodde<Integer> node4 = new Nodde<Integer>(40);
        Nodde<Integer> node5 = new Nodde<Integer>(50);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        DeletePtr.delete(node3);

        LinkedList<Integer> llActual = new LinkedList<Integer>(node1);

        Nodde<Integer> nodeExpected1 = new Nodde<Integer>(10);
        Nodde<Integer> nodeExpected2 = new Nodde<Integer>(20);
        Nodde<Integer> nodeExpected3 = new Nodde<Integer>(40);
        Nodde<Integer> nodeExpected4 = new Nodde<Integer>(50);

        nodeExpected1.next = nodeExpected2;
        nodeExpected2.next = nodeExpected3;
        nodeExpected3.next = nodeExpected4;


        LinkedList<Integer> llExpected = new LinkedList<Integer>(nodeExpected1);
//        assertEquals(llActual, llExpected);
    }
}
