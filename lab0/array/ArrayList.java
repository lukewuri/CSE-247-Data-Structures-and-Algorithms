package lab0.array;

import lab0.List;
import lab0.links.LinkedList;

public class ArrayList<T> implements List<T> {

	private T[] elements; // WRT slides, capacity == elements.length
	private int size;

	public ArrayList() {
		elements = (T[] ) new Object[1];  // we have to do this to make Java happy
		this.size = 0;
	}

	@Override
	public void addLast(T thing) {
		if (size == elements.length) {
			resize();
		}
		// size is now where the thing should go in the elements array
		elements[size] = thing;
		size = size + 1;

	}

	// make our elements array biggererer
	private void resize() {
		T[] copy = (T[] ) new Object[elements.length + 1];
		// copy everything over
		for (int i=0; i < size; ++i) {
			copy[i] = elements[i];
		}
		//
		// make elements point at the new array, which makes the old one garbage
		elements = copy;
	}

	@Override
	public T getItemAt(int n) {
		return elements[n];
	}

	public String toString() {
		String ans = "[ ";
		for (int i=0; i < size; ++i) {
			ans = ans + elements[i] + " ";
		}
		ans = ans + "]";
		return ans;
	}

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.addLast("hello");
		list.addLast("again");
		for (int i=0; i < 10; ++i) {
			list.addLast("String " + i);
			System.out.println(list);
		}
		System.out.println("List is " + list);
		// System.out.println("expect again? " + list.getItemAt(1));
	}

}
