package application;

import java.sql.Date;

public class productData {

	private Integer id;
	private String product_id;
	private String product_name;
	private String type;
	private Integer stock;
	private Double price;
	private String status;
	private String image;
	private Date date;

	public productData(Integer id, String product_id, String product_name, String type, Integer stock, Double price,
			String status, String image, Date date) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.product_name = product_name;
		this.type = type;
		this.stock = stock;
		this.price = price;
		this.status = status;
		this.image = image;
		this.date = date;
	}

	public productData(String product_id, String product_name, String type, Integer stock, Double price, String status,
			String image,Date date) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.type = type;
		this.stock = stock;
		this.price = price;
		this.status = status;
		this.image=image;
		this.date = date;
	}
	
	public productData(Integer id, String product_id, String product_name, Double price, String image) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.product_name = product_name;
		this.price = price;
		this.image = image;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	
}
