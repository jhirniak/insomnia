package com.codeforgood.musoni.api.group;

import com.google.gson.annotations.SerializedName;

public class GroupStatus 
{
	@SerializedName("id")
	int id;
	
	@SerializedName("code")
	String code;
	
	@SerializedName("value")
	String value;
	
	public String toString()
	{
		return ""+id+", "+code+", "+value;
	}

}
