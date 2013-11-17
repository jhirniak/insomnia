package com.codeforgood.musoni.api.group;

import com.google.gson.annotations.SerializedName;

public class GroupList 
{
	@SerializedName("totalFilteredRecords")
	int records;
	
	@SerializedName("pageItems")
	GroupPageItems pi;
	
	public GroupPageItems getGroups()
	{
		return pi;
	}
	
}
