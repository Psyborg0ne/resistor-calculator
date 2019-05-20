package resistor;

import java.text.DecimalFormat;

public class Resistance {
	
	int firDigit, secDigit, thiDigit, digitNum;
	double mulDigit, tolDigit, dTotal;
	private static String[] suffix = new String[]{"", "k", "M", "G", "T"};
	private static int MAX_LENGTH = 4;
	
	public Resistance(int digitNum) {
		this.digitNum = digitNum;
		firDigit = 0;
		secDigit = 0;
		thiDigit = 0;
		mulDigit = 0d;
		tolDigit = 0d;
		dTotal = 0d;
	}
	
	public int getFirDigit() {
		return firDigit;
	}

	public void setFirDigit(int firDigit) {
		this.firDigit = firDigit;
	}

	public int getSecDigit() {
		return secDigit;
	}

	public void setSecDigit(int secDigit) {
		this.secDigit = secDigit;
	}

	public int getThiDigit() {
		return thiDigit;
	}

	public void setThiDigit(int thiDigit) {
		this.thiDigit = thiDigit;
	}

	public int getDigitNum() {
		return digitNum;
	}

	public void setDigitNum(int digitNum) {
		this.digitNum = digitNum;
	}

	public double getMulDigit() {
		return mulDigit;
	}

	public void setMulDigit(double mulDigit) {
		this.mulDigit = mulDigit;
	}

	public double getTolDigit() {
		return tolDigit;
	}

	public void setTolDigit(double tolDigit) {
		this.tolDigit = tolDigit;
	}

	public String getResistance() {
		return (digitNum == 2) ? (String.valueOf(firDigit) + String.valueOf(secDigit)) : String.valueOf(firDigit) + String.valueOf(secDigit) + String.valueOf(thiDigit);
	}
	
	public double getTotal() {
		dTotal = (Double.valueOf(this.getResistance()) * mulDigit); 
		return dTotal;
	}
	
	public static String format(double number) {
	    String r = new DecimalFormat("##0E0").format(number);
	    r = r.replaceAll("E[0-9]", suffix[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
	    while(r.length() > MAX_LENGTH || r.matches("[0-9]+\\.[a-z]")){
	        r = r.substring(0, r.length()-2) + r.substring(r.length() - 1);
	    }
	    return r;
	}
	
}
