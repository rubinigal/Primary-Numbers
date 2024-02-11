import java.util.Scanner;

public class PrimaryNumbers {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		PrimaryNumbersStockpile stock;
		PrimaryNumbersThread t[];
		int m, n;
		
		System.out.print("Enter the max number:");
		m = scanner.nextInt();
		
		System.out.print("Enter the max threads:");
		n = scanner.nextInt();
		
		stock = new PrimaryNumbersStockpile(m, n);
		t = new PrimaryNumbersThread[n];
		
		for (int i = 0; i < t.length; i++) {
			t[i] = new PrimaryNumbersThread(stock);
		}
		for (int i = 0; i < t.length; i++) {
			t[i].start();
		}
		
		for (int i = 0; i < t.length; i++) { // Waiting for threads to be done
			try {
				t[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.print(stock.getArray().toString());
		scanner.close();
	}
}
