package vw.nama.web.MVCdemo.model;

import java.io.Serializable;

public class MyCounter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3886323711198085882L;
	private int value;
	
	public MyCounter(int value)
	{
		this.value = value;
	}
	public int increment()
	{
	   this.value++;
	   return this.value;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "MyCounter [value=" + value + "]";
	}
	
	

}
