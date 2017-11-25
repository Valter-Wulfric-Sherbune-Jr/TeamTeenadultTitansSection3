package View;

public class GameView {
	
	public void print(String str)
	{
		System.out.print(str);
	}
	public void println(String str)
	{
		System.out.println(str);
	}
	public void delayPrint(String str) {
		try {
			for(String string : str.split(" ")) {
				System.out.print(string);
				Thread.sleep(100);
			}
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
