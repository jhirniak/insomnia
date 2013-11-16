package com.codeforgood.musoni.json;

import com.google.gson.annotations.SerializedName;

public class Client 
{
	@SerializedName("id")
	int id;
	
	@SerializedName("accountNo")
	String accoutNummber;
	
	@SerializedName("externalId")
	String externalId;
	
	@SerializedName("status")
	ClientStatus status;
	
	@SerializedName("active")
	boolean active;
	
	@SerializedName("activationDate")
	Date activationDate;
	 
	@SerializedName("firstname")
	String firstName;
	
	@SerializedName("lastname")
	String lastName;
	
	@SerializedName("displayName")
	String displayName;
	
	@SerializedName("officeId")
	int officeId;
	
	@SerializedName("officeName")
	String officeName;
	
	@SerializedName("timeline")
	Timeline timeline;
	
	
	public String toString()
	{
		return ""+id+" | "+
				""+accoutNummber+" | "+
				""+externalId+" | "+
				""+status.toString()+" | "+
				""+active+" | "+
				""+getActivationDate()+" | "+
				""+firstName+" | "+
				""+lastName+" | "+
				""+displayName+" | "+
				""+officeId+" | "+
				""+officeName+" | "+
				""+getTimeline()+" | ";
	}
	
	public String getActivationDate()
	{
		if (activationDate != null)
			return activationDate.toString();
		else
			return "EMPTY";
	}
	
	public String getTimeline()
	{
		if (timeline != null)
			return timeline.toString();
		else
			return "EMPTY";
	}
	
}
