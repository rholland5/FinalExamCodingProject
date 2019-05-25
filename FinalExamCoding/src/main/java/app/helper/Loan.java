package app.helper;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import org.apache.poi.ss.formula.functions.*;

public class Loan {
	
	private double loanAmount;
	private double rate;
	private int lengthOfLoan;
	private double additionalPayment;
	private double currentValue;
	private ArrayList<Payment> paymentList = new ArrayList<Payment>();
	
	public Loan(double loanAmount, double rate, int lengthOfLoan, double additionalPayment, Date firstPaymentDate, boolean bInterestCalc, double futureValue) {
		
		this.loanAmount = loanAmount;
		this.rate = rate;
		this.additionalPayment = additionalPayment;
		this.lengthOfLoan = lengthOfLoan;
		currentValue = this.loanAmount;
		
		int period = 1;
		do {
			
			double pmt = round(Finance.pmt(this.rate/12, this.lengthOfLoan*12, -this.loanAmount) + this.additionalPayment,5);
			double ppmt = round(Finance.ppmt(this.rate/12, period, this.lengthOfLoan*12, -this.loanAmount) + this.additionalPayment,5);
			double ipmt = round(Finance.ipmt(this.rate/12, period, this.lengthOfLoan*12, -this.loanAmount),5);
			Payment p = new Payment(pmt,ppmt,ipmt);
			double presentValue = round(currentValue,2);
			double paymentTable = round(p.getPayment(),2);
			double interestTable = round(p.getiPayment(),2);
			double principleTable = round(p.getpPayment(),2);
			System.out.println("Period: " + period);
			System.out.println("Present Value: " + presentValue);
			System.out.println("Payment: " + paymentTable);
			System.out.println("Interest Payment: " + interestTable);
			System.out.println("Principle Payment: " + principleTable);
			System.out.println();
			paymentList.add(p);
			if(currentValue-p.getpPayment()<=0.001) {
				p.setPayment(currentValue);
				currentValue = 0;
				break;
			}
			else {
				currentValue-=ppmt;
				
			}
			period++;
		}
		while(currentValue>0);
		}
	public double sumPayments() {
		double tot = 0;
		for(Payment i:paymentList) {
			tot+=i.getPayment();
			
		}
		return tot;
	}
	
	public double sumInterest() {
		double tot = 0;
		for(Payment i:paymentList) {
			tot+=i.getiPayment();

		}
		return tot;
	}
	public double sumPrinciple() {
		double tot = 0;
		for(Payment i:paymentList) {
			tot+=i.getpPayment();
		}
		return tot;
	}
	
	public double totalInterestPaid() {
		return this.sumPayments()-this.loanAmount;
	}
	public void printPayments() {
		System.out.println("Number of Payments: " + paymentList.size());
		for(Payment i:paymentList) {
			System.out.println("Interest Payment: " + i.getiPayment());
			System.out.println("Principle Payment: " + i.getpPayment());
			System.out.println();
		}
	}
	public static double round(double value, int places) {
		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places,RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	public static Date parseDate(String string) {
		//TODO Auto-generated method stub
		return null;
		
	}

}