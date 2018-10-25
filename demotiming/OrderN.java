package demotiming;

import java.util.LinkedList;
import java.util.List;

import cse131.ArgsProcessor;
import timing.Experiment;
import timing.GensRepeatRunnable;
import timing.Output;
import timing.RepeatRunnable;
import timing.SizeAndTiming;

public class OrderN extends DoOneThing implements GensRepeatRunnable {

	public RepeatRunnable genRunnable(final long n) {
		return new RepeatRunnable() {

			@Override
			public void reset() {
				resetCounter();
			}
			
			//
			// Do n things
			//

			@Override
			public void run() {
				for (int i=0; i < n; ++i) {
					doOneThing(100000);
				}

			}

			public String toString() {
				return "Size " + n;
			}


		};
	}


	public static void main(String[] args) {
		ArgsProcessor ap = new ArgsProcessor(args);
		int N = ap.nextInt("How far should we take this?");
		int bump = ap.nextInt("What bump between runs?");
		OrderN on = new OrderN();
		List<Integer> sz    = new LinkedList<Integer>();
		for (int i=0; i < N; i = i + bump) {
			sz.add(new Integer(i));
		}

		Experiment e = new Experiment(on, sz, 3);
		e.run();

		for (SizeAndTiming st : e.getSizeAndTiming()) {
			System.out.println(st.size + " " + st.timing.toMillis());
		}
		
		Output.writeSizeTiming("outputs/ordern.csv", "N", e.getSizeAndTiming());
	}


	@Override
	public RepeatRunnable gen(long size) {
		return genRunnable(size);
	}

}
