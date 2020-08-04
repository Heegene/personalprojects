package com.appress.spring.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;


@Entity
public class Journal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String title;
	private Date regdate;
	private String summary;
	
	
	@Transient
	private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getRegdateAsShort() {
		return format.format(regdate);
	}



	@Override
	public String toString() {
		return "Journal [id=" + id + ", title=" + title + ", regdate=" + regdate + ", summary=" + summary + ", format="
				+ format + "]";
	}
	
	
	public Journal(String title, String summary, Date date) throws ParseException  {
		this.title = title;
		this.summary = summary;
		this.regdate = date;
	}
	
	public Journal() {}
}
