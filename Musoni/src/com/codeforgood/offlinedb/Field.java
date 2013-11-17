package com.codeforgood.offlinedb;

public class Field<FieldType> {
	
	FieldType value;
	
	public FieldType get()
	{ return value; }
	
	public void set(FieldType value)
	{ this.value = value; }

	public boolean sameType(Object other)
	{ return this.getClass().isInstance(other); }
	
	@Override
	public String toString()
	{ return value.toString(); }
}
