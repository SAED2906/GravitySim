package PointGen;

public class RanPG {
	
	private static int counter = 0;
	public static int RandomPointx() {
		
		
		
		int x = (int) (Math.random()*1920);
		//System.out.println(x);
		
		
		return x;
	}
	public static int RandomPointy() {
		
		
			
		
		int y = (int) (Math.random()*1080);
		//System.out.println(y);
		
		
		return y;
	}
	
	public static int getCounter() {
		return counter;
	}
	public static void setCounter(int counter) {
		RanPG.counter = counter;
	}

}
