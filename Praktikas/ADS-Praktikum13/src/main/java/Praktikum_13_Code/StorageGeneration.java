package Praktikum_13_Code;

import java.text.CollationElementIterator;
import java.util.*;
import java.lang.reflect.*;


public class StorageGeneration {
    public static StringBuffer log = new StringBuffer();
    private static List<Collectable> root;
    private static List<Collectable> youngHeap;
    private static List<Collectable> oldHeap;

    static {
        clear();
    }

    public static void clear() {
        root = new LinkedList<Collectable>();
        youngHeap= new LinkedList<Collectable>();
        oldHeap = new LinkedList<Collectable>();
    }

    /* add  root object */
    public static void addRoot(Collectable obj) {
        root.add(obj);
    }

    // create a collectable object of class cls
    public static Collectable _new(String cls, Object arg) {
        Collectable obj = null;
        try {
            // create an object and call constructor
            Constructor cst = Class.forName("Praktikum_13_Code."+cls).getConstructor(arg.getClass());
            obj = (Collectable) cst.newInstance(new Object[] {arg});
            // add object to heap
            youngHeap.add(obj);
            log.append("New: ").append(obj.toString()).append("\n");
        } catch (Exception ex) {
            ex.printStackTrace();
            log.append("error creating object ").append(cls).append("\n");
        }
        return (Collectable) obj;
    }

    /* remove object from heap */
    public static void delete(Collectable obj) {
        if (youngHeap.remove(obj)) {
            log.append("Delete in young heap: ").append(obj.toString()).append("\n");
        } else if (oldHeap.remove(obj)) {
            log.append("Delete in old heap: ").append(obj.toString()).append("\n");
        } else {
            log.append("error trying to delete not existing object ").append(obj.toString()).append("\n");
        }
    }

    /* get all root objects */
    public static Iterable<Collectable> getRoot() {
        return new LinkedList<Collectable>(root);
    }

    /* get heap */
    public static Iterable<Collectable> getOldHeap() {
        return new LinkedList<Collectable>(oldHeap);
    }

    public static Iterable<Collectable> getYoungHeap() {
        return new LinkedList<Collectable>(youngHeap);
    }

    /* get references to collectables of an object */
    public static Iterable<Collectable> getRefs(Collectable obj) {
        // get all fields of an object
        Field[] fields = obj.getClass().getFields();
        List<Collectable> fieldList = new LinkedList<Collectable>();
        for (int i = 0; i < fields.length; i++) {
            try {
                Object o = fields[i].get(obj);
                if (o instanceof Collectable) {
                    fieldList.add((Collectable) o);
                }
            } catch (Exception ex) {}
        }
        return fieldList;
    }

    /* dump an iterator */
    public static void dump(String s, Iterable itr) {
        log.append(s);
        for (Object o: itr) {
            log.append(" ").append(o.toString());
        }
        log.append("\n\n");
    }

    public static String getLog() {
        return log.toString();
    }

    private static void mark(Collectable cObject) {
        // to be done
        if(cObject == null || cObject.isMarked()) return;
        cObject.setMark(true);
        getRefs(cObject).forEach(StorageGeneration::mark);
    }

    private static void sweepYoungHeap() {
        for (Collectable collectable: getYoungHeap()) {
            if (collectable.isMarked()) {
                copyYoungHeapToOldHeap(collectable);
            } else {
                delete(collectable);
            }
        }
    }
    private static void sweepOldHeap() {
        // to be done
        for (Collectable collectable: getOldHeap()) {
            if (collectable.isMarked()) {
                collectable.setMark(false);
            } else {
                delete(collectable);
            }
        }
    }

    private static void copyYoungHeapToOldHeap(Collectable collectable) {
        youngHeap.remove(collectable);
        collectable.setMark(false);
        oldHeap.add(collectable);
    }

    private static void sweep() {
        sweepOldHeap();
        sweepYoungHeap();
    }

    public static void gcYoungHeapOnly() {
        log.append("Collector start young generation only\n");
        // to be done
        for(Collectable collectable: getRoot()) {
            mark(collectable);
        }
        sweepYoungHeap();
        log.append("Collector end\n");
    }

    public static void gc() {
        log.append("Collector start old and young generation\n");
        // to be done
        for(Collectable collectable: getRoot()) {
            mark(collectable);
        }
        sweep();
        log.append("Collector end\n");
    }

}
