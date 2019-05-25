package pkgUT;

import static org.junit.Assert.*;


import org.junit.Test;
import app.helper.Loan;
import java.util.Date;

public class LoanTest {
	
	
	
	@Test
	public void LoanTest1() {
		
		double loanAmount = 100000;
		double interestRate = 0.07;
		int nbrOfYears = 20;
		double additionalPayment = 100;
		Date firstPaymentDate = Loan.parseDate("2019-05-31");
		
		boolean bInterestCalc = false;
		double futureValue=0;
		
		Loan l = new Loan(loanAmount, interestRate, nbrOfYears, additionalPayment,firstPaymentDate,bInterestCalc,futureValue);
	}



}
