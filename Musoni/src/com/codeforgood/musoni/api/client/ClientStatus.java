package com.codeforgood.musoni.api.client;

import com.google.gson.annotations.SerializedName;

public class ClientStatus 
{
	@SerializedName("id")
	int id;
	
	@SerializedName("code")
	String code;
	
	@SerializedName("value")
	String value;
	
	public String toString()
	{
		return "status = "+id+", "+code+", "+value;
	}
}
