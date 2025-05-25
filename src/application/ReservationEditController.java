package application;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ReservationEditController implements Initializable{

	@FXML
	private TextField edit_customer;

	@FXML
	private DatePicker edit_date;

	@FXML
	private TextField edit_mobile;

	@FXML
	private TextField edit_resID;

	@FXML
	private ComboBox<String> edit_status;

	@FXML
	private ComboBox<String> edit_tableID;

	@FXML
	private ComboBox<String> edit_time;
	
	@FXML
    private Button removeBtn;

    @FXML
    private Button updateBtn;
	
	private static final AlertMessage alert = new AlertMessage();

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/cafe", "root", "");
	}
	
	public void StatusList() {
		List<String> listG = new ArrayList<>();
		for (String data : Data.reservation_status) {
			listG.add(data);
		}
		ObservableList<String> listData = FXCollections.observableArrayList(listG);
		edit_status.setItems(listData);
	}
	
	public void TimeList() {
		List<String> listG = new ArrayList<>();
		for (String data : Data.reservation) {
			listG.add(data);
		}
		ObservableList<String> listData = FXCollections.observableArrayList(listG);
		edit_time.setItems(listData);
	}
	
	public void TableList() {
		List<String> listG = new ArrayList<>();
		for (String data : Data.tables ) {
			listG.add(data);
		}
		ObservableList<String> listData = FXCollections.observableArrayList(listG);
		edit_tableID.setItems(listData);
	}
	
	public void SetText() throws ClassNotFoundException, SQLException {
		String setData="Select * from reservations where reservation_id=?";
		connect();
		try {
			preparedStatement = connect.prepareStatement(setData);
            preparedStatement.setString(1,Data.temp_resID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
            	edit_resID.setText(resultSet.getString("reservation_id"));
            	edit_tableID.getSelectionModel().select(edit_tableID.getItems().indexOf(resultSet.getString("table_id")));
            	edit_customer.setText(resultSet.getString("customer"));
            	edit_time.getSelectionModel().select(edit_time.getItems().indexOf(resultSet.getString("time")));
            	edit_date.setValue(resultSet.getDate("date_reserved").toLocalDate());
            	edit_mobile.setText(resultSet.getString("mobile_number"));
            	edit_status.getSelectionModel().select(edit_status.getItems().indexOf(resultSet.getString("status")));
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateBtn() throws ClassNotFoundException, SQLException {
		if(edit_tableID.getSelectionModel().isEmpty() ||
				edit_customer.getText().isEmpty() ||
				edit_time.getSelectionModel().isEmpty() ||
				edit_date.getValue().equals(null) ||
				edit_mobile.getText().isEmpty() ||
				edit_status.getSelectionModel().isEmpty() ) {
			alert.errorMessage("Please fill all blank fields!");
		}else {
			String selectData = "Select * from reservations where reservation_id=?";
			connect();
			try {
				preparedStatement = connect.prepareStatement(selectData);
	            preparedStatement.setString(1,edit_resID.getText());
	            resultSet = preparedStatement.executeQuery();
	            if(resultSet.next()) {
	            	String updateData="Update reservations set table_id=? , customer=? , time=? , date_reserved=? , mobile_number=?, status=? where reservation_id=?";
	            	preparedStatement = connect.prepareStatement(updateData);
	                preparedStatement.setString(1,edit_tableID.getSelectionModel().getSelectedItem());
	                preparedStatement.setString(2,edit_customer.getText());
	                preparedStatement.setString(3,edit_time.getSelectionModel().getSelectedItem());
	                LocalDate localDate = edit_date.getValue();
	                preparedStatement.setDate(4,java.sql.Date.valueOf(localDate));
	                preparedStatement.setString(5,edit_mobile.getText());
	                preparedStatement.setString(6,edit_status.getSelectionModel().getSelectedItem());
	                preparedStatement.setString(7,edit_resID.getText());
	                int updatedRows=preparedStatement.executeUpdate();
	                if(updatedRows>0) {
	                	alert.successMessage("Successfully updated!");
	                	updateBtn.getScene().getWindow().hide();
	                	SetText();
	                }else {
	                	alert.errorMessage("Something went wrong!");
	                }
	            }
			} catch (Exception e) {
				// TODO: handle exception
			}	
		}
	}
	
	public void removeBtn() throws ClassNotFoundException, SQLException {
		String deleteData="Delete from reservations where reservation_id=?";
		connect();
		try {
			preparedStatement = connect.prepareStatement(deleteData);
            preparedStatement.setString(1,edit_resID.getText());
            int deletedRows=preparedStatement.executeUpdate();
            if(deletedRows>0) {
            	alert.confirmationMessage("Are you sure that you want to delete Reservation ID: "+ edit_resID.getText());
            	alert.successMessage("Successfully deleted!");
            	removeBtn.getScene().getWindow().hide();
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		StatusList();
		TimeList();
		TableList();
		
		try {
			SetText();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
