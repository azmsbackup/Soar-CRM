package com.soarcrm.domain;

import java.util.List;

public class ClientJsonObject
{

	private int iTotalRecords;
	private int iTotalDisplayRecords;
	private String sEcho;
	private String sColumns;
	private List<Client> aaData;

    public int getiTotalRecords() 
    {
    	return iTotalRecords;
    }
    public void setiTotalRecords(int iTotalRecords) 
    {
    	this.iTotalRecords = iTotalRecords;
    }
    public int getiTotalDisplayRecords() 
    {
    	return iTotalDisplayRecords;
    }
    public void setiTotalDisplayRecords(int iTotalDisplayRecords) 
    {
    	this.iTotalDisplayRecords = iTotalDisplayRecords;
    }
    public String getsEcho() 
    {
    	return sEcho;
    }
    public void setsEcho(String sEcho) 
    {
    	this.sEcho = sEcho;
    }
    public String getsColumns() 
    {
    	return sColumns;
    }
    public void setsColumns(String sColumns) 
    {
    	this.sColumns = sColumns;
    }
    public List<Client> getAaData()
    {
        return aaData;
    }
    public void setAaData(List<Client> aaData) 
    {
        this.aaData = aaData;
    }

    
}