package app.controller;

import app.StudentCalc;
import app.helper.Loan;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;



public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField lblTotalInterest;
	
	@FXML
	private Label lblTotalPayments;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField AdditionalPayment;
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {
		
		LocalDate localDate = PaymentStartDate.getValue();
		System.out.println(localDate);
		
		double dLoanAmount = Double.parseDouble(LoanAmount.getText());
		System.out.println("Amount: " + dLoanAmount);
		
		double dInterestRate = Double.parseDouble(InterestRate.getText());
		System.out.println(dInterestRate);
		
		double dAdditionalPayment = Double.parseDouble(AdditionalPayment.getText());
		System.out.println(dAdditionalPayment);
		
		int iNbrOfYears = Integer.parseInt(NbrOfYears.getText());
		System.out.println(iNbrOfYears);
		
		Loan l = new Loan (dLoanAmount, dInterestRate, iNbrOfYears, dAdditionalPayment, null, false, dAdditionalPayment);
		
		lblTotalPayments.setText(Double.toString(Math.round(l.sumPayments())));
		lblTotalInterest.setText(Double.toString(Math.round(l.totalInterestPaid())));


	}
}