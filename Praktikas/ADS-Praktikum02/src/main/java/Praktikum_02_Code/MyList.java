package Praktikum_02_Code;
import java.util.AbstractList;

class ListNode<T extends Comparable<T>> {
    T data;
    ListNode<T> next, prev;

    ListNode(T o) {
        data = o;
    }
}

public class MyList<T extends Comparable<T>> extends AbstractList<T> {
    protected int size;
    protected ListNode<T> currentPointer; // faengt immer bei 0 an. Heisst, der header wird immer auf die erste Node gesetzt.
    protected final ListNode<T> listNode = new ListNode<T>(null);

    public MyList() {
        clear();
    }

    @Override
    public boolean add(T o) {
        if (currentPointer.data == null) {
            currentPointer.data = o;
        } else {
            ListNode<T> n = new ListNode<T>(o);
            ListNode<T> f = currentPointer;
            while (f.next != null) { // geht durch alle nodes durch bis er beim letzten node angekommen ist.
                f = f.next;
            }
            f.next = n; // hier fuegt er beim letzten node einen neuen node hinzu
            n.prev = f; // hier fuegt er der neuen node den vorherigen node hinzu.
        }
        size++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return currentPointer.data == null;
    }

    @Override
    public boolean remove(Object o) {
        ListNode<T> current = currentPointer;
        if (current.next == null && o.equals(current.data)) {
            clear();
            return true;
        }

        while (current.next != null) {
            if (current.next.next == null && current.next.data.equals(o)) {
                current.next = null;
                size--;
                return true;
            } else if (current.data.equals(o)) {
                if (current.prev == null) {
                    current.next.prev = null;
                    currentPointer = currentPointer.next;
                } else {
                    /**
                     * Beispiel -> [A,B,C]
                     * B wird geloescht.
                     * Hier ist der Current = B (somit gesuchtes Ziel gefunden).
                     * Current.prev = A -> .next = B & .prev = null
                     * &
                     * Current.next = C -> .next = null & .prev = B
                     *
                     * Das heisst, dass Current.next.prev = B (welches das gesuchte Loesch Ziel ist) ersetzt werden muss.
                     * Es wird ersetzt mit dem Current.prev = A
                     * Das heisst, dass Current.prev.next = B (welches das gesuchte Loesch Ziel ist) ersetzt werden muss.
                     * Es wird ersetzt mit dem Current.next = C
                     */
                    current.next.prev = current.prev;
                    current.prev.next = current.next;
                }
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public T get(int index) {
        if (index > size) { // wenn eine position gesucht wird, die nicht vorhanden ist, wird null returned.
            return null;
        }
        ListNode<T> current = currentPointer;
        while (index > 0) { // geht immer eine node weiter (rechts) bis er am punkt angekommen ist wo gesucht wird.
            current = current.next;
            index--;
        }
        return current.data;
    }

    @Override
    public int size() {
        return size; // returned die momentane groesse der Liste.
    }

    @Override
    public void clear() {
        size = 0;
        currentPointer = listNode; // setzt den momentanen listenstatus auf null.
    }
}
