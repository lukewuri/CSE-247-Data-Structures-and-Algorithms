package lab0.links;

import lab0.List;

public class LinkedList<T> implements List<T> {
	
	private ListNode<T> head;
	
	public LinkedList() {
		this.head = null;   // nothing in our list yet
	}

	@Override
	public void addLast(T thing) {
		if (head == null) { // empty list, slide 176
			ListNode<T> p = new ListNode<T>();
			p.value = thing;
			head = p;
		}
		else {
			ListNode<T> q = new ListNode<T>();
			q.value = thing;
			// search for the end of the list
			//
			ListNode<T> p = head;
			while (p.next != null) {
				p = p.next;
			}
			//  p is where it needs to be slide 201
			p.next = q;
		}
		
	}

	/**
	 * 
	 * @param n which time, 0 is the first item
	 * @return
	 */
	@Override
	public T getItemAt(int n) {
		ListNode<T> p = head;
		for (int i=0; i < n; ++i) {
			p = p.next;
		}
		return p.value;
	}
	
	public String toString() {
		String ans = "[ ";
		for (ListNode<T> p = head; p != null; p = p.next) {
			ans = ans + p.value + " ";
		}
		ans = ans + "]";
		return ans;
	}
	
	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		list.addLast("hello");
		list.addLast("again");
		for (int i=0; i < 10; ++i) {
			list.addLast("String " + i);
			System.out.println(list);
		}
		System.out.println("List is " + list);
		System.out.println("expect again? " + list.getItemAt(1));
	}

}
