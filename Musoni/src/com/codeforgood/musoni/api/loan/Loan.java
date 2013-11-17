package com.codeforgood.musoni.api.loan;

import com.google.gson.annotations.SerializedName;

public class Loan 
{
	@SerializedName("id")
	int id;
	
	@SerializedName("clientId")
	int clientId;
	
	@SerializedName("clientName")
	String clientName;
	
	@SerializedName("loanProductName")
	String loanProductName;
	
	@SerializedName("principal")
	int principal;
	
	@SerializedName("termFrequency")
	int termFrequency;
	
	@SerializedName("numberOfRepayments")
	int numberOfRepayments;
	
	@SerializedName("annualInterestRate")
	int annualInterestRate;
	
	@SerializedName("summary")
	LoanSummary summary;
	
	public String toString()
	{
		return  id+" | "+
				clientId+" | "+
				clientName+" | "+
				loanProductName+" | "+
				principal+" | "+
				termFrequency+" | "+
				numberOfRepayments+" | "+
				annualInterestRate+" | "+
				getSummary();
	}
	
	public String getSummary()
	{
		if (summary != null)
			return summary.toString();
		return "";
	}
}
