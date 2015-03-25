package employee;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

public class MyArrayList <E> implements Serializable, Iterable <E> {
    @SuppressWarnings("unchecked")
    private E mas[] = (E[])new Object[15];
    private int size = 0;
    public boolean isEmpty() {
	return size == 0;
    }

    public int size () {
	return size;
    }

    public void add(E e) {
	if (size == mas.length) {
	    resize();
	}
	for (int i = 0; i < mas.length; i++) {
	    if(mas[i] == null) {
		mas[i] = e;
		size++;
		break;
	    }
	}
    }

    private void resize() {
	mas = Arrays.copyOf(mas, mas.length * 2);
    }

    public boolean remove(int idRemove) {
	boolean find = false;
	for (int i = 0; i < mas.length; i++) {
	    if (mas[i] != null) {
		Employee emp = (Employee)mas[i];
		if(emp.idEmployee==idRemove) {
		    mas[i] = null;
		    size--;
		    find = true;
		    return find;
		}
	    }
	}
	return false;
    }

    public void sort(Comparator<E> cmp) {
	Arrays.sort(mas, cmp);
    }

    public void removeAll() {
	for (int i = 0; i < mas.length; i++) {
	    mas[i] = null;
	    size = 0;
	}
    }

    public String toString() {
	String toReturn = "";
	boolean flag = false;
	for (E t : mas) {
	    if (t != null) {
		toReturn += t.toString();
		flag = true;
	    }
	}
	if (flag) {
	    return toReturn;
	}
	else {
	    return "No data";
	}
    }

    public Iterator<E> iterator() {
	return new InnerInt();
    }

    private class InnerInt implements Iterator <E> {
	int start = 0;
	public boolean hasNext() {
	    return start != mas.length;
	}

	public E next() {
	    E toReturn = mas[start];
	    start++;
	    return toReturn;
	}

	public void remove() {
	    mas[start - 1] = null;
	}
    }
}