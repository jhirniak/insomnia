package com.codeforgood.musoni.api.loan;

import com.google.gson.annotations.SerializedName;

public class LoanList 
{
	@SerializedName("totalFilteredRecords")
	int records;
	
	@SerializedName("pageItems")
	LoanPageItems pi;
	
	public LoanPageItems getLoans()
	{
		return pi;
	}
	
}
