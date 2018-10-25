package lab0;

import java.util.Arrays;

import lab0.links.LinkedList;
import lab0.links.LinkedListWithTail;
import timing.Experiment;
import timing.GensRepeatRunnable;
import timing.Output;
import timing.RepeatRunnable;
import timing.SizeAndTiming;

public class ListExperiment implements GensRepeatRunnable {
	
	private ListGenner<Integer> gensList;
	
	/**
	 * Run the list experiment, using the specified list-implementation-generator
	 * @param gensList generates a list implementation for experimentation
	 */
	public ListExperiment(ListGenner<Integer> gensList) {
		this.gensList = gensList;
	}

	public RepeatRunnable genRunnable(final long n) {
		return new RepeatRunnable() {
			
			private List<Integer> list;

			@Override
			public void reset() {
				//
				// Start with a new, fresh list each time
				//    using the generation method of interest here
				//    (it will be LinkedList or LinkedListWithTail)
				//
				list = gensList.createNewList();
			}
			
			//
			// Our work consists of:
			//    Appending n times to our list
			//

			@Override
			public void run() {
				for (int i=0; i < n; ++i) {
					list.addLast(i);
				}

			}

			public String toString() {
				return "List " + gensList + " of size " + n;
			}


		};
	}


	public static void main(String[] args) {
		ListGenner<Integer> notail = new ListGenner<Integer>() {

			@Override
			public List<Integer> createNewList() {
				return new LinkedList<Integer>();
			}
			
			public String toString() { return "No Tail"; }
			
		};
		
		ListGenner<Integer> withtail = new ListGenner<Integer>() {

			@Override
			public List<Integer> createNewList() {
				return new LinkedListWithTail<Integer>();
			}
			
			public String toString() { return "With Tail"; }
			
		};
		
		runExperiment(notail,   500);   // Fiddle with value if necessary
		runExperiment(withtail, 500);   // FIXME change to 25000 or some large number so times show up
	}
	
	private static void runExperiment(ListGenner<Integer> gimmeList, int factor) {
		int start = 0;
		int end   = 30;
		Integer[] sizes = new Integer[end-start];
		for (int i=start; i < end; ++i) {
			sizes[i-start] = i*factor;
		}
		
		Experiment e = new Experiment(new ListExperiment(gimmeList), Arrays.asList(sizes), 3);
		e.run();

		for (SizeAndTiming st : e.getSizeAndTiming()) {
			System.out.println(st.size + " " + st.timing.toMillis());
		}
		
		Output.writeSizeTiming("outputs/linkedlist.csv", gimmeList.toString(), e.getSizeAndTiming());
	}


	@Override
	public RepeatRunnable gen(long size) {
		return genRunnable(size);
	}

}
