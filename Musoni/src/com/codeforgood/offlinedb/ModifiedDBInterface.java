package com.codeforgood.offlinedb;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public interface ModifiedDBInterface {

	/** Saves encrypted HashMap */
	public void save()
			throws InvalidKeyException,
			NoSuchAlgorithmException, 
			NoSuchPaddingException, 
			IllegalBlockSizeException, 
			BadPaddingException;
	
	public void put(String table, String column, Field value);
	
	/** Flashes whole modified DB */
	public void flush();
	
}
