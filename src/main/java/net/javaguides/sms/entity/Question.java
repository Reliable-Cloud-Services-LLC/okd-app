package net.javaguides.sms.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "first")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "list")
	private String list;
	
	@Column(name = "yes")
	private int yes;
	
	@Column(name ="no")
	private int no;
	
	
	@Column(name ="date")
	String date;
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Question() {
		
	}
	
	public Question(String list, int yes, int no) {
		super();
		this.list = list;
		this.yes = yes;
		this.no = no;
	
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	
	

	public int getYes() {
		return yes;
	}


	public void setYes(int yes) {
		this.yes = yes;
	}
	


	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + list + ", yes=" + yes + ", no=" + no + "]";
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		
	}

//	public static void main(String[] args) {
//		currentDate()
//	}


}
