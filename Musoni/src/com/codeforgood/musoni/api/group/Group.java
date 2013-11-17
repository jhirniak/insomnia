package com.codeforgood.musoni.api.group;

import com.google.gson.annotations.SerializedName;

public class Group 
{
	@SerializedName("id")
	int id;
	
	@SerializedName("name")
	String name;
	
	@SerializedName("status")
	GroupStatus status;
	
	@SerializedName("active")
	boolean active;
	
	@SerializedName("officeId")
	int officeId;
	
	@SerializedName("officeName")
	String officeName;
	
	@SerializedName("hierarchy")
	String hierarchy;
	
	public String toString()
	{
		return  id+" | "+
				name+" | "+
				status.toString()+" | "+
				active+" | "+
				officeId+" | "+
				officeName+" | "+
				hierarchy;
	}	
}
