package application;

import java.util.Date;

public class reservationData {

	private Integer id;
	private Integer reservation_id;
	private String table_id;
	private String customer;
	private String time;
	private Date date_reserved;
	private String number;
	private String status;

	public reservationData(Integer reservation_id,String table_id, String customer, String time, Date date_reserved) {
		super();
		this.reservation_id=reservation_id;
		this.table_id = table_id;
		this.customer = customer;
		this.time = time;
		this.date_reserved = date_reserved;
	}
	
	public reservationData(Integer id,Integer reservation_id , String table_id, String customer, String time,
			Date date_reserved, String number, String status) {
		super();
		this.id = id;
		this.reservation_id=reservation_id;
		this.table_id = table_id;
		this.customer = customer;
		this.time = time;
		this.date_reserved = date_reserved;
		this.number = number;
		this.status = status;
	}
	public Integer getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Integer reservation_id) {
		this.reservation_id = reservation_id;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTable_id() {
		return table_id;
	}
	public void setTable_id(String table_id) {
		this.table_id = table_id;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Date getDate_reserved() {
		return date_reserved;
	}
	public void setDate_reserved(Date date_reserved) {
		this.date_reserved = date_reserved;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
