package com.realpacific.projectnoaa.entities;

public class Pair<T, U> {
    private T first;
    private U second;

    public Pair(){}

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        return ((Pair) obj).first.equals(first) && ((Pair) obj).second.equals(second);
    }

    @Override
    public String toString() {
        return String.format("Pair(%s, %s)", first, second);
    }
}
