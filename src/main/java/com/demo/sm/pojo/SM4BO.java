package com.demo.sm.pojo;

public class SM4BO
{
	public int mode;
	
	public long[] sk;
	
	public boolean isPadding;

	public SM4BO()
	{
		this.mode = 1;
		this.isPadding = true;
		this.sk = new long[32];
	}
}
