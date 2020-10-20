package Praktikum_02_Code;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

public class ListStack implements Stack {
    private List list;

    public ListStack() {
        removeAll();
    }

    @Override
    public void push(@NotNull Object x) throws StackOverflowError {
        list.add(0,x);
    }

    @Override
    public Object pop() {
        if (isEmpty()) {
            return null;
        };
        return list.remove(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Object peek() {
        if (isEmpty()) {
            return null;
        };
        return list.get(0);
    }

    @Override
    public void removeAll() {
        list = new LinkedList<>();
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
