package demotiming;

import java.util.LinkedList;
import java.util.List;

import com.csvreader.CsvWriter;

import cse131.ArgsProcessor;
import timing.Experiment;
import timing.GensRepeatRunnable;
import timing.Output;
import timing.RepeatRunnable;
import timing.SizeAndTiming;

public class OrderNSquared extends DoOneThing implements GensRepeatRunnable {

	public RepeatRunnable genRunnable(final long n) {
		return new RepeatRunnable() {

			@Override
			public void reset() {
				resetCounter();
			}

			//
			// Do n-squared things
			//
			@Override
			public void run() {
				for (int i1=0; i1 < n; ++i1) {
					for (int i2=0; i2 < n; ++i2) {
						doOneThing(1000);			
					}
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
		OrderNSquared on = new OrderNSquared();
		List<Integer> sz    = new LinkedList<Integer>();
		for (int i=0; i < N; i = i + bump) {
			sz.add(new Integer(i));
		}

		Experiment e = new Experiment(on, sz, 3);
		e.run();

		for (SizeAndTiming st : e.getSizeAndTiming()) {
			System.out.println(st.size + " " + st.timing.toMillis());
		}
		Output.writeSizeTiming("outputs/ordern.csv", "N Squared", e.getSizeAndTiming());
	}


	@Override
	public RepeatRunnable gen(long size) {
		return genRunnable(size);
	}

}
