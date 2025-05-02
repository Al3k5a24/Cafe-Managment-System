package application;

import java.util.Date;

public class orderData {

	private Integer ID;
	private String product_id;
	private String product_name;
	private Integer table_id;
	private String type;
	private Integer quantity;
	private Double price;
	private Date date;

	public orderData(String product_id, String product_name, Integer table_id, Integer quantity, Double price) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.table_id = table_id;
		this.quantity = quantity;
		this.price = price;
	}
	public orderData(Integer iD, String product_id, String product_name, Integer table_id, String type,
			Integer quantity, Double price, Date date) {
		super();
		ID = iD;
		this.product_id = product_id;
		this.product_name = product_name;
		this.table_id = table_id;
		this.type = type;
		this.quantity = quantity;
		this.price = price;
		this.date = date;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getProduct_id() {
		return product_id;
	}
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getTable_id() {
		return table_id;
	}
	public void setTable_id(Integer table_id) {
		this.table_id = table_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
