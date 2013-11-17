package com.codeforgood.offlinedb;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.xmlpull.v1.XmlSerializer;

import com.codeforgood.musoni.MainActivity;

import android.content.Context;
import android.util.Xml;

public class ModifiedDB implements ModifiedDBInterface
{

	// to be replaced with lock-pattern
	private static final String password = "nyancat"; 
	private static final String filename = "modifsdb.edb";
	
	HashMap<String, HashMap<String, Field>> modifs;
	
	public ModifiedDB()
	{ modifs = new HashMap<String, HashMap<String, Field>>(); }
	
	// different types of fields need to be supported - Integer, Double, String
	public void put(String TABLE, String COLUMN, Field value)
	{
		if (!modifs.containsKey(TABLE))
		{ modifs.put(TABLE, new HashMap<String, Field>()); }
		
		HashMap<String, Field> t = modifs.get(TABLE);
		t.put(COLUMN, value);
	}
	
	/** Save encrypted file of modified DB on device */
	public void save() 
			throws InvalidKeyException,
			NoSuchAlgorithmException, 
			NoSuchPaddingException, 
			IllegalBlockSizeException, 
			BadPaddingException
	{
		String data = serialize();
		byte[] dataBytes = data.getBytes(Charset.forName("UTF-8"));
		saveFile(filename, encrypt(dataBytes));
	}
	
	public String read() 
			throws InvalidKeyException, 
			NoSuchAlgorithmException, 
			NoSuchPaddingException, 
			IllegalBlockSizeException, 
			BadPaddingException
	{ return decrypt(readFile(filename)); }
	
	/** Flush the entire modified DB */
	public void flush()
	{
		Context context = MainActivity.getContext();
		context.deleteFile(filename);
		modifs = new HashMap<String, HashMap<String, Field>>();
	}
	
	/** Returns JSON as defined in Mifos X API
	 * @see http://mlite-demo.musoni.eu/code4good/api-docs/apiLive.htm
	 */
	@Override
	public String toString()
	{
		return null;
	}
	
	private byte[] encrypt(byte[] dataBytes)
			throws NoSuchAlgorithmException,
			NoSuchPaddingException,
			InvalidKeyException,
			IllegalBlockSizeException,
			BadPaddingException
	{
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] passwordBytes = password.getBytes(Charset.forName("UTF-8"));
		SecretKeySpec key = new SecretKeySpec(passwordBytes, "AES");
		c.init(Cipher.ENCRYPT_MODE, key);
		return c.doFinal(dataBytes);
	}
	
	private String decrypt(byte[] dataBytes)
			throws NoSuchAlgorithmException,
			NoSuchPaddingException,
			InvalidKeyException,
			IllegalBlockSizeException,
			BadPaddingException
	{
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] passwordBytes = password.getBytes(Charset.forName("UTF-8"));
		SecretKeySpec key = new SecretKeySpec(passwordBytes, "AES");
		c.init(Cipher.DECRYPT_MODE, key);
		return c.doFinal(dataBytes).toString();
	}
	
	private byte[] readFile(String filename)
	{
		Context context = MainActivity.getContext();
		FileInputStream fs;
		StringBuffer sb = new StringBuffer("");
		try
		{
			fs = context.openFileInput(filename);
			byte[] buffer = new byte[1024];
			while (fs.read(buffer) != -1)
			{ sb.append(new String(buffer)); }
		}
		catch (Exception e)
		{ e.printStackTrace(); }
		return sb.toString().getBytes(Charset.forName("UTF-8"));
	}
	
	private void saveFile(String filename, byte[] buffer)
	{
		Context context = MainActivity.getContext();
		FileOutputStream fs;
		try
		{
			fs = context.openFileOutput(filename, Context.MODE_PRIVATE);
			fs.write(buffer);
			fs.close();
		} catch (Exception e)
		{ e.printStackTrace(); }
	}
	
	private String serialize()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (String tableName : modifs.keySet())
		{
			sb.append(tableName + ": {");
			HashMap<String, Field> table = modifs.get(tableName);
			for (String column : table.keySet())
			{
				sb.append("(" + column + ", " + table.get(column) + ")");
			}
			sb.append("},");
		}
		sb.append("}");
		return sb.toString();
	}
	
	private String toXML()
	{
		StringBuilder sb = new StringBuilder();
		for (String tabName : modifs.keySet())
		{
			sb.append("<" + tabName + ">");
			HashMap<String, Field> tab = modifs.get(tabName);
			for (String colName : tab.keySet())
			{
				sb.append("<" + colName + ">");
				sb.append(tab.get(colName).toString());
				sb.append("<" + colName + "/>");
			}
			sb.append("</" + tabName + ">");
		}
		return sb.toString();
	}
	
	private String parse(String input)
	{
		//XMLParser parser = new XMLParser();
		return null;
	}
}
