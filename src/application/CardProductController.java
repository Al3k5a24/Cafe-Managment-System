package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class CardProductController implements Initializable{

    @FXML
    private AnchorPane card_form;

    @FXML
    private Button prod_addBTN;

    @FXML
    private ImageView prod_image;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;
    
    @FXML
    private ComboBox<String> menu_combobox;

    @FXML
    private Spinner<Integer> prod_spinner;
    
    private SpinnerValueFactory<Integer> spin;
	
    private static final AlertMessage alert = new AlertMessage();

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/cafe", "root", "");
	}
	
	private productData prodData;
	private Image image;
	
	public void setData(productData prodData) {
		this.prodData=prodData;
		
		prod_name.setText(prodData.getProduct_name());
		prod_price.setText("$ "+String.valueOf(prodData.getPrice()));
		String path="File:" + prodData.getImage();
		image=new Image(path,225,80,false,true);
		prod_image.setImage(image);
	}
    
	public void setQuantity() {
		spin=new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
		prod_spinner.setValueFactory(spin);
	}
	

	public void TablesList() {
		List<String> listG = new ArrayList<>();
		for (String data : Data.tables) {
			listG.add(data);
		}
		ObservableList<String> listData = FXCollections.observableArrayList(listG);
		menu_combobox.setItems(listData);
	}
	
	public void addbtn() throws ClassNotFoundException, SQLException {
		int qty=prod_spinner.getValue();
		
		String check="";
		String checkAvailable="Select status from product where product_name=?";
		connect();

		try {
			preparedStatement = connect.prepareStatement(checkAvailable);
			preparedStatement.setString(1, prod_name.getText());
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				check=resultSet.getString("status");
			}
			
			if(!check.equals("Available") || qty==0) {
				alert.errorMessage("The product: " +prodData.getProduct_name()+" is unavailable right now!");
			}else {
				String selectData = "SELECT type FROM product WHERE product_id=?";
				preparedStatement = connect.prepareStatement(selectData);
				preparedStatement.setString(1, prodData.getProduct_id());
				resultSet = preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					Data.typecopy=resultSet.getString("type");
					String insertData = "INSERT INTO table_orders(product_id, product_name, table_id, type, quantity, price, date) VALUES (?, ?, ?, ?, ?, ?, ?)";
				    preparedStatement = connect.prepareStatement(insertData);
				    preparedStatement.setString(1, prodData.getProduct_id());
				    preparedStatement.setString(2, prodData.getProduct_name());
				    preparedStatement.setString(3, menu_combobox.getSelectionModel().getSelectedItem()); // ID stola, trenutno 0
				    preparedStatement.setString(4, Data.typecopy);
				    preparedStatement.setInt(5, qty);
				    preparedStatement.setDouble(6, prodData.getPrice());

				    System.out.println(prodData.getProduct_id());
				    System.out.println(prodData.getProduct_name());
				    System.out.println(Data.typecopy);
				    System.out.println(prodData.getPrice());
				    System.out.println(qty);
				    
				    Date date = new Date();
				    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				    preparedStatement.setDate(7, sqlDate);

				    int insertedRow = preparedStatement.executeUpdate();
				    if (insertedRow > 0) {
				        alert.successMessage("Successfully added!");
				    } else {
				        alert.errorMessage("Something went wrong");
				    }
				} else {
				    alert.errorMessage("Product not found!");
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
	        if (preparedStatement != null) {
	            preparedStatement.close();
	            connect.close();
	        }
	    }
		if(qty==0) {
			alert.errorMessage("");
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setQuantity();
		TablesList();
		
	}

}
