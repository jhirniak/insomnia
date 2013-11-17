package com.codeforgood.musoni.api.client;

import com.google.gson.annotations.SerializedName;

public class ClientList 
{
	@SerializedName("totalFilteredRecords")
	int records;
	
	@SerializedName("pageItems")
	PageItems pi;
	
	public PageItems getClients()
	{
		return pi;
	}
	
}
