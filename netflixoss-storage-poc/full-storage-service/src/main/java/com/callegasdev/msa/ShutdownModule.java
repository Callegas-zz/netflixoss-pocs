package com.callegasdev.msa;

public class ShutdownModule extends netflix.karyon.ShutdownModule{
	
	public ShutdownModule() {
	    super(7003);
	}
	
}
