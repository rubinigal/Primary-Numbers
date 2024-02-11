import java.util.ArrayList;

public class PrimaryNumbersStockpile {
	private ArrayList<Integer> primaryNumbers;
	private int maxNumber, maxThreads, index = 1;
	
	public PrimaryNumbersStockpile(int m, int n) {
		primaryNumbers = new ArrayList<>();
		maxNumber = m;
		maxThreads = n;
	}
	
	public ArrayList<Integer> getArray() {
		primaryNumbers.sort(null); // if we want to see that our threads working asynchronized we can remove this line
		return primaryNumbers;
	}

	public synchronized int getWork() {
		while (maxThreads <= 0) { // We limit the amount of threads running at the same time
			try {
				wait();
			} catch(InterruptedException e) {}
		}
		if (index >= maxNumber) // No more numbers to check
			return 0;
		maxThreads--;
		index++;
		return index - 1;
	}
	
	public synchronized void imDone(int number) {
		if (number > 0) {
			primaryNumbers.add(number);
		}
		maxThreads++;
		notifyAll();
	}
}
