package lab0;

public interface ListGenner<T> {
	
	/**
	 * We use this to generate either a LinkedList<T> or LinkedListWithTail<T>
	 * @return a new implementation of List, ready for addLasts
	 */
	public List<T> createNewList();

}
