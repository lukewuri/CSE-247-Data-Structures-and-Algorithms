package demotiming;

public class DoOneThing {
	
	//
	// The "volatile" attribute tells Java that this counter variable
	//    is needed outside this class, and outside the view of the compiler
	//    The compiler has no choice but to write and read the value from
	//    memory.
	//
	public volatile int counter;
	
	public void resetCounter() {
		counter = 0;
	}
	
	/**
	 * The one thing we do here is bump a counter.
	 *   But we inflate that by the specified factor.
	 * @param factor
	 */
	public void doOneThing(int factor) {
		for (int i=0; i < factor; ++i) {
			counter = counter + 1;
		}
	}

}
