package ru.spbstu.telematics.sharagin.lab02;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by maksim on 26.04.17.
 */
public class MaksArrayList<T> implements List<T>, Iterable<T> {
    private T[] elements;
    private int size;
    private int index;
    private static final int defCapacity = 10;

    public MaksArrayList(){
        this.elements = (T[])new Object[defCapacity];
        this.size = 0;
        this.index = 0;
    }

    public MaksArrayList(int count){
        if(count > 0){
            this.elements = (T[])new Object[count];
            this.size = 0;
            this.index = 0;
        }
        else {
            throw new IllegalArgumentException("Illegal capacity: " + count);
        }
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public boolean add(T value){
        if(this.index == this.elements.length){
            T[] newArray = (T[])new Object[elements.length*2];
            System.arraycopy(elements, 0, newArray, 0, index - 1);
            this.elements = newArray;
        }
        this.elements[this.index] = value;
        this.index++;
        this.size++;
        return true;
    }

    public T get(int index){
        if(index >= this.size || index < 0){
            throw new IndexOutOfBoundsException("Illegal index: " + index);
        }
        return this.elements[index];
    }

    public boolean contains(Object o){
        for(int i = 0; i < this.size; i++){
            if(this.get(i).equals(o))
                return true;
        }
        return false;
    }

    public boolean remove(Object o){
        for(int i = 0; i < this.size; i++){
            if(this.elements[i].equals(o)){
                int len = this.size - i - 1;
                if(len > 0) {
                    System.arraycopy(this.elements, i + 1, this.elements, i, len);
                }
                this.size--;
                this.index--;
                return true;
            }
        }
        return false;
    }

    public Iterator<T> iterator(){
        return new Iterator<T>() {
            private int curIndex;
            @Override
            public boolean hasNext() {
                if(this.curIndex < size){
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                return get(curIndex++);
            }

            @Override
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }

    public Object[] toArray() {return null;}

    public boolean containsAll(Collection<?> c) {return false;}

    public boolean addAll(Collection<? extends T> c) {return false;}

    public boolean addAll(int index, Collection<? extends T> c) {return false;}

    public boolean removeAll(Collection<?> c) {return false;}

    public boolean retainAll(Collection<?> c) {return false;}

    public ListIterator<T> listIterator() {return null;}

    public ListIterator<T> listIterator(int index) {return null;}

    public List<T> subList(int fromIndex, int toIndex) {return null;}

    public void clear() {}

    public T set(int index, T element) {return null;}

    public int indexOf(Object o) {return 0;}

    public int lastIndexOf(Object o) {return 0;}

    public <T> T[] toArray(T[] a) {return null;}

    public void add(int index, T element) {}

    public T remove(int index) {return null;}
}
