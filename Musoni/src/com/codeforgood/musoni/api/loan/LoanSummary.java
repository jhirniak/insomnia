package com.codeforgood.musoni.api.loan;

import com.google.gson.annotations.SerializedName;

public class LoanSummary 
{
	@SerializedName("totalOutstanding")
	float totalOutstanding;
	
	public String toString()
	{
		return ""+totalOutstanding;
	}

}
