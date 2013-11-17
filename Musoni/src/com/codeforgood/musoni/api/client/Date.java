package com.codeforgood.musoni.api.client;

import java.util.ArrayList;

public class Date extends ArrayList<Integer> 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	

	public String toString()
	{
		String result = "";
		
		for (int i = 0; i < this.size(); i++)
			result += get(i)+".";
		
		return result.substring(0, result.length()-1);
	}
	
}

