package Praktikum_02_Code;
class SortedList<T extends Comparable<T>> extends MyList<T> {

    @Override
    public boolean add(T o) {
        boolean foo = true;
        if (currentPointer.data == null) {
            currentPointer.data = o;
        } else {
            ListNode<T> n = new ListNode<T>(o);
            ListNode<T> f = currentPointer;

            while (f.next != null) {
                if (f.data.compareTo(o) < 1 ){
                    f = f.next;
                } else {
                    addObjectOnePlaceBehind(f, n); //wenn das neue Objekt kleiner ist, dann fuegt er das Objekt einen Platz nach hinten ein.
                    foo = false;
                    break;
                }
            }

            if (foo) {
                if (f.data.compareTo(o) < 1 ){ //hier fuegt er ein objekt NACH dem letzten Objekteintrag in die Liste ein
                    f.next = n;
                    n.prev = f;
                } else {
                    addObjectOnePlaceBehind(f, n); //hier fuegt er ein objekt VOR dem letzten Objekteintrag in die Liste ein
                }
            }

        }
        size++;
        return true;
    }

    private void addObjectOnePlaceBehind(ListNode<T> f, ListNode<T> n) {
        if (f.prev != null) {
            f.prev.next = n;
        } else {
            currentPointer = n;
        }
        f.prev = n;
        n.next = f;
    }
}
