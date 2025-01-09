package com.soarcrm.domain;

import java.util.List;

import javax.mail.Address;

import org.springframework.web.multipart.MultipartFile;

public class CRMEmail 
{
	private Address sender;
	private String recipient;
	private String subject;
	private String content;
	private String time;
	private String from;
	private String traceNo;
	private int emailSequenceNo;
	private String cc;
	private String loginname;
	
	private String hiddenFrom;
	private String hiddenRecipient;
	private String hiddenSubject;
	private String hiddenContent;
	private String hiddenCc;
	
	private List<MultipartFile> fileUpload;
	private List<CRMEmail> mailDetailsList;

	public String getRecipient() 
	{
		return recipient;
	}
	public void setRecipient(String recipient)
	{
		this.recipient = recipient;
	}
	public String getSubject() 
	{
		return subject;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	public String getContent() 
	{
		return content;
	}
	public void setContent(String content) 
	{
		this.content = content;
	}
	public Address getSender()
	{
		return sender;
	}
	public void setSender(Address sender) 
	{
		this.sender = sender;
	}
	public String getTime() 
	{
		return time;
	}
	public void setTime(String time) 
	{
		this.time = time;
	}
	public String getFrom() 
	{
		return from;
	}
	public void setFrom(String from) 
	{
		this.from = from;
	}
	public String getTraceNo() 
	{
		return traceNo;
	}
	public void setTraceNo(String traceNo) 
	{
		this.traceNo = traceNo;
	}
	public List<MultipartFile> getFileUpload() 
	{
		return fileUpload;
	}
	public void setFileUpload(List<MultipartFile> fileUpload)
	{
		this.fileUpload = fileUpload;
	}
	public int getEmailSequenceNo() 
	{
		return emailSequenceNo;
	}
	public void setEmailSequenceNo(int emailSequenceNo) 
	{
		this.emailSequenceNo = emailSequenceNo;
	}
	public String getCc() 
	{
		return cc;
	}
	public void setCc(String cc) 
	{
		this.cc = cc;
	}
	public String getLoginname() 
	{
		return loginname;
	}
	public void setLoginname(String loginname) 
	{
		this.loginname = loginname;
	}
	public List<CRMEmail> getMailDetailsList() 
	{
		return mailDetailsList;
	}
	public void setMailDetailsList(List<CRMEmail> mailDetailsList) 
	{
		this.mailDetailsList = mailDetailsList;
	}
	public String getHiddenFrom() 
	{
		return hiddenFrom;
	}
	public void setHiddenFrom(String hiddenFrom) 
	{
		this.hiddenFrom = hiddenFrom;
	}
	public String getHiddenRecipient() 
	{
		return hiddenRecipient;
	}
	public void setHiddenRecipient(String hiddenRecipient) 
	{
		this.hiddenRecipient = hiddenRecipient;
	}
	public String getHiddenSubject() 
	{
		return hiddenSubject;
	}
	public void setHiddenSubject(String hiddenSubject) 
	{
		this.hiddenSubject = hiddenSubject;
	}
	public String getHiddenContent() 
	{
		return hiddenContent;
	}
	public void setHiddenContent(String hiddenContent)
	{
		this.hiddenContent = hiddenContent;
	}
	public String getHiddenCc() 
	{
		return hiddenCc;
	}
	public void setHiddenCc(String hiddenCc) 
	{
		this.hiddenCc = hiddenCc;
	}
	


}
