
public class PrimaryNumbersThread extends Thread{
	private PrimaryNumbersStockpile stock;
	private int checkNum;
	
	public PrimaryNumbersThread(PrimaryNumbersStockpile stock) {
		this.stock = stock;
	}
	
	public void run() {
		super.run();
		// Very crude way to find a primary number 
		while ((checkNum = stock.getWork()) > 0) {
			for (int i = 2; i < checkNum; i++) {
				if (checkNum%i == 0)
					checkNum = 0;
			}
			stock.imDone(checkNum);
		}
		
	}
}
