package com.codeforgood.musoni.json;

import com.google.gson.annotations.SerializedName;

public class ClientList 
{
	@SerializedName("totalFilteredRecords")
	int records;
	
	@SerializedName("pageItems")
	PageItems pi;
	
	public PageItems getPageItems()
	{
		return pi;
	}
	
}
