package Physics;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PointCal {
	
	public static double x;
	public static double y;
	public static BigInteger mass = new BigInteger("5972190000000000000000000"); 
	public static BigInteger tempn;
	public static BigInteger tempm;
	public static int Quadrant = 0;
	
	
	public static int getQuadrant() {
		return Quadrant;
	}


	public static void setQuadrant(int quadrant) {
		Quadrant = quadrant;
	}


	public static void PointCalculation(double x1, double y1, double x2, double y2, double m1, double m2) {
		
		double temp = Math.pow((x2-x1), 2);
		double temp2 =Math.pow((y2-y1), 2);
		System.out.println("temp1 " +temp);
		System.out.println("temp2 " +temp2);
		m1 = m1+1;
		m2 = m2+1;
		
		
		BigInteger m1temp = BigInteger.valueOf((long) m1);
		BigInteger m2temp = BigInteger.valueOf((long) m2);
		
		m1temp = mass.multiply(m1temp);
		m2temp = mass.multiply(m2temp);
		
		if (temp < 0) {
			temp = temp*(-1);
		}
		if (temp2 < 0) {
			temp2 = temp2*(-1);
		}
		
		int r = (int) Math.sqrt(temp + temp2);
		r = r * 200;
		
		System.out.println("r " +r);
		BigInteger FMASS = m1temp.multiply(m2temp);
		System.out.println("fmass " +FMASS);
		BigInteger G = new BigInteger("100000000000000");
		BigInteger FMASSG = FMASS.divide(G);
		double d = (FMASSG.doubleValue() / G.doubleValue());
		d = d * 6673;
		
		double f = d/(Math.pow(r, 2));
		
		BigInteger F = BigInteger.valueOf((long) f);
		BigInteger temporary = new BigInteger("10000000000");
		BigInteger multiply = F.multiply(temporary);
		
		System.out.println("f " +F);
		System.out.println("mass " +mass);
		BigInteger gt = multiply.divide(mass);
		System.out.println("g " +gt.doubleValue());
		
		double g = gt.doubleValue();
		if (gt.doubleValue() < 0.5) {
			g = 0.5;
		}
		
		double theta = Math.atan(((y2-y1)/(x2-x1)));
		System.out.println("theta " +theta);
		System.out.println("gSmall " +g);
		x = (double) ((double) Math.cos(theta)*g);
		y = (double) ((double) Math.sin(theta)*g);
		
		System.out.println(Math.toDegrees(theta));
				
				
		System.out.println(x);
		System.out.println(y);
		
		
		
		if ((x2-x1) > 0 && (y2-y1) < 0) {
			Quadrant = 1;
		}
		if ((x2-x1) < 0 && (y2-y1) < 0) {
			Quadrant = 2;
		}
		if ((x2-x1) < 0 && (y2-y1) > 0) {
			Quadrant = 3;
		}
		if ((x2-x1) > 0 && (y2-y1) > 0) {
			Quadrant = 4;
		}
		
		x = (double)Math.abs(x);
		y = (double)Math.abs(y);
		
		if (Quadrant == 1) {
			y=y*(-1);
		}
		if (Quadrant == 2) {
			y=y*(-1);
			x=x*(-1);
		}
		if (Quadrant == 3) {
			x=x*(-1);
		}
		if (Quadrant == 4) {
			
		}
		System.out.println("----------------------");
		System.out.println(Quadrant);
		System.out.println("temp1 " +temp);
		System.out.println("temp2 " +temp2);
		System.out.println("----------------------");
		
		
	}


	public static double getX() {
		return x;
	}


	public static double getY() {
		return y;
	}

}
