package lab0;

public interface List<T> {
	
	public void addLast(T thing);
	
	public T getItemAt(int n);
	
	//
	// To think about:
	//
	// public int getSize();
	//
	// normally we'd also have:
	//  addFirst, size(), isEmpty(), ....  --- the stuff you'd want to do with a List

}
