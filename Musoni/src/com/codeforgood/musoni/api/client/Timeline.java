package com.codeforgood.musoni.api.client;

import com.google.gson.annotations.SerializedName;

public class Timeline 
{
	@SerializedName ("activatedOnDate")
	Date date;

	public String toString()
	{
		if (date != null)
			return date.toString();
		else
			return "EMPTY";
	}
}
