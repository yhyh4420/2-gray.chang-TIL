package org.til.thread.atomic;

public class AtomicOperation  {

    private int count = 0;

    public void increment() {
        count++;
        //count += 1;
    }

    public int getCount() {
        return count;
    }
}
