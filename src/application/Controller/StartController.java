package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class StartController implements Initializable {

	@FXML
	private TextField forgotpass_answer;

	@FXML
	private Button forgotpass_btn;
	
	@FXML
	private Label warning_label1;
	
	@FXML
	private Label warning_label2;

	@FXML
	private AnchorPane forgotpass_form;

	@FXML
	private ComboBox<String> forgotpass_question;

	@FXML
	private ImageView image2;

	@FXML
	private Button login_btn;

	@FXML
	private CheckBox login_checkbox;

	@FXML
	private AnchorPane login_form;

	@FXML
	private Hyperlink login_fp;

	@FXML
	private PasswordField login_password;

	@FXML
	private Hyperlink login_registeerlink;

	@FXML
	private TextField login_showpassword;

	@FXML
	private TextField login_username;

	@FXML
	private TextField register_answer;

	@FXML
	private Button register_btn;

	@FXML
	private CheckBox register_checkbox;

	@FXML
	private TextField register_firstname;

	@FXML
	private AnchorPane register_form;

	@FXML
	private TextField register_lastname;

	@FXML
	private PasswordField register_password;

	@FXML
	private ComboBox<String> register_question;

	@FXML
	private TextField register_showpassword;

	@FXML
	private TextField register_username;

	@FXML
	private Button reset_btn;

	@FXML
	private AnchorPane reset_form;

	@FXML
	private TextField reset_password1;

	@FXML
	private TextField reset_password2;

	@FXML
	private StackPane stackpane;

	@FXML
	private ImageView logo_facebook;

	@FXML
	private ImageView logo_ig;

	@FXML
	private ImageView logo_tiktok;

	@FXML
	private ImageView logo_twitter;
	
	@FXML
	private ImageView caffe_logo;
	
	@FXML
	private ImageView caffe_logo2;
	
	@FXML
	private Hyperlink register_link;
	
	@FXML
	private TextField forgotpass_username;

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/cafe", "root", "");
	}

	public void QuestionList() {
	    List<String> listG = new ArrayList<>();
	    for (String data : Data.question) {
	        listG.add(data);
	    }
	    ObservableList<String> listData = FXCollections.observableArrayList(listG);
	    register_question.setItems(listData);
	    forgotpass_question.setItems(listData);
	}

	
	private static final AlertMessage alert=new AlertMessage();
	
	public void switchscene(ActionEvent event) {
	
		if(event.getSource()==login_registeerlink) {
			login_form.setVisible(false);
			login_form.setDisable(true);
			register_form.setVisible(true);
			register_form.setDisable(false);
		}
		
		else if(event.getSource()==register_link) {
			login_form.setVisible(true);
			login_form.setDisable(false);
			register_form.setVisible(false);
			register_form.setDisable(true);
		}
		else if(event.getSource()==login_fp) {
			login_form.setVisible(false);
			login_form.setDisable(true);
			register_form.setVisible(false);
			register_form.setDisable(true);
			forgotpass_form.setVisible(true);
			forgotpass_form.setDisable(false);
		}
	}
	
	public void registerbtn() throws ClassNotFoundException, SQLException {
		if(register_firstname.getText().isEmpty() ||
		   register_lastname.getText().isEmpty()  ||
		   register_username.getText().isEmpty() ||
		   register_password.getText().isEmpty() ||
		   register_question.getSelectionModel().isEmpty() ||
		   register_answer.getText().isEmpty() ) {
			if (register_firstname.getText().isEmpty()) {
			    register_firstname.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
			    register_firstname.setPromptText("Please fill this field");
			    register_firstname.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.isEmpty()) {
			            register_firstname.setStyle("");
			            register_firstname.setPromptText("First name");
			        }
			    });
			}
			if (register_lastname.getText().isEmpty()) {
			    register_lastname.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
			    register_lastname.setPromptText("Please fill this field");
			    register_lastname.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.isEmpty()) {
			        	register_lastname.setStyle("");
			        	register_lastname.setPromptText("Last name");
			        }
			    });
			}
			if (register_username.getText().isEmpty()) {
			    register_username.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
			    register_username.setPromptText("Please fill this field");
			    register_username.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.isEmpty()) {
			        	register_username.setStyle("");
			        	register_username.setPromptText("Username");
			        }
			    });
			}
			if (register_password.getText().isEmpty()) {
			    register_password.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
			    register_password.setPromptText("Please fill this field");
			    register_password.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.isEmpty()) {
			        	register_password.setStyle("");
			        	register_password.setPromptText("Password");
			        }
			    });
			}
			if (register_question.getSelectionModel().isEmpty()) {
			    register_question.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
			    register_question.valueProperty().addListener((observable, oldValue, newValue) -> {
			        if (newValue != null) {
			            register_question.setStyle("");
			            register_question.setPromptText("Choose...");
			        }
			    });
			}
			if (register_answer.getText().isEmpty()) {
			    register_answer.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
			    register_answer.setPromptText("Please fill this field");
			    register_answer.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.isEmpty()) {
			        	register_answer.setStyle("");
			        	register_answer.setPromptText("Answer");
			        }
			    });
			}
			alert.errorMessage("Error");
		}else {
			connect();
			
			String checkUsername="Select * from user where username=?";
			try {
				 preparedStatement = connect.prepareStatement(checkUsername);
		         preparedStatement.setString(1, register_username.getText());
		         resultSet = preparedStatement.executeQuery();
		         if(resultSet.next()) {
		        	 alert.errorMessage(register_username.getText()+" is already taken!");
		         }else {
		        	 String insertdata="Insert into user(first_name,last_name,username,password,security_question,answer,date) values(?,?,?,?,?,?,?)";
		        	 preparedStatement = connect.prepareStatement(insertdata);
			         preparedStatement.setString(1, register_firstname.getText());
			         preparedStatement.setString(2, register_lastname.getText());
			         preparedStatement.setString(3, register_username.getText());
			         preparedStatement.setString(4, register_password.getText());
			         preparedStatement.setString(5, register_question.getSelectionModel().getSelectedItem());
			         preparedStatement.setString(6, register_answer.getText());
			         Date date = new Date();  // Trenutni datum i vreme
			         Timestamp timestamp = new Timestamp(date.getTime());
			         preparedStatement.setTimestamp(7,timestamp);
			         int insertedRow=preparedStatement.executeUpdate();
			         if(insertedRow>0) {
			        	alert.successMessage("Successfully registered!");
			        	login_form.setVisible(true);
			 			login_form.setDisable(false);
			 			register_form.setVisible(false);
			 			register_form.setDisable(true);
			 			clearFields();
			         }
		         }
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
		        if (preparedStatement != null) {
		            preparedStatement.close();
		            connect.close();
		        }
		    }
		}
	}

	private void clearFields() {
		register_firstname.clear();
		   register_lastname.clear();
		   register_username.clear();
		   register_password.clear();
		   register_question.getSelectionModel().clearSelection();;
		   register_answer.clear();
		
	}
	
	public void registerShowPassword() {
		if(register_checkbox.isSelected()) {
			register_showpassword.setText(register_password.getText());
			register_password.setVisible(false);
			register_password.setDisable(true);
			register_showpassword.setVisible(true);
			register_showpassword.setDisable(false);
		}else if(!register_checkbox.isSelected()){
			register_password.setText(register_showpassword.getText());
			register_password.setVisible(true);
			register_password.setDisable(false);
			register_showpassword.setVisible(false);
			register_showpassword.setDisable(true);
		}
	}

	public void loginbtn() throws ClassNotFoundException, SQLException, IOException {
		if(login_username.getText().isEmpty()||
		   login_password.getText().isEmpty()) {
			if (login_username.getText().isEmpty()) {
				login_username.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				login_username.setPromptText("Please fill this field");
				login_username.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.isEmpty()) {
			        	login_username.setStyle("");
			        	login_username.setPromptText("Username");
			        }
			    });
			}
			if (login_password.getText().isEmpty()) {
				login_password.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				login_password.setPromptText("Please fill this field");
				login_password.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.isEmpty()) {
			        	login_password.setStyle("");
			        	login_password.setPromptText("Password");
			        }
			    });
			}
		}else {
			connect();
			String sql="Select * from user where username=? and password=?";
			try {
				preparedStatement=connect.prepareStatement(sql);
				preparedStatement.setString(1, login_username.getText());
				preparedStatement.setString(2, login_password.getText());
				resultSet=preparedStatement.executeQuery();
				if(resultSet.next()) {
					alert.successMessage("Successfully logged in!");
					Data.temp_username=login_username.getText();
					
					Stage stage=new Stage();
					Parent root=FXMLLoader.load(getClass().getResource("CafeManagmentSystem.fxml"));
					Scene scene=new Scene(root);
					stage.setScene(scene);
					stage.setMinHeight(700);
					stage.setMinWidth(1150);
					
					stage.setTitle("Cafe Managment System | Employee form");
					stage.show();
					
					login_btn.getScene().getWindow().hide();
					
				}else if(!resultSet.next()){
					alert.errorMessage("Invalid username/password");
				}	
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
		        if (preparedStatement != null) {
		            preparedStatement.close();
		            connect.close();
		        }
		    }
		}
	}
	
	public void loginShowpassword() {
		if(login_checkbox.isSelected()) {
			login_showpassword.setText(login_password.getText());
			login_password.setVisible(false);
			login_password.setDisable(true);
			login_showpassword.setVisible(true);
			login_showpassword.setDisable(false);
		}else if(!login_checkbox.isSelected()){
			login_password.setText(login_showpassword.getText());
			login_password.setVisible(true);
			login_password.setDisable(false);
			login_showpassword.setVisible(false);
			login_showpassword.setDisable(true);
		}
	}
	
	public void ForgotPassword() throws ClassNotFoundException, SQLException {
		if(forgotpass_username.getText().isEmpty() ||
				forgotpass_question.getSelectionModel().isEmpty()	||
				forgotpass_answer.getText().isEmpty()) {
			if (forgotpass_username.getText().isEmpty()) {
				forgotpass_username.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				forgotpass_username.setPromptText("Please fill this field");
				forgotpass_username.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.isEmpty()) {
			        	forgotpass_username.setStyle("");
			        	forgotpass_username.setPromptText("Password");
			        }
			    });
			}
			if (forgotpass_question.getSelectionModel().isEmpty()) {
				forgotpass_question.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				forgotpass_question.valueProperty().addListener((observable, oldValue, newValue) -> {
			        if (newValue != null) {
			        	forgotpass_question.setStyle("");
			        	forgotpass_question.setPromptText("Choose...");
			        }
			    });
			}
			if (forgotpass_answer.getText().isEmpty()) {
				forgotpass_answer.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				forgotpass_answer.setPromptText("Please fill this field");
				forgotpass_answer.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.isEmpty()) {
			        	forgotpass_answer.setStyle("");
			        	forgotpass_answer.setPromptText("Answer");
			        }
			    });
			}
			}
		    else {
		    	connect();
				String sql="Select * from user where username=?";
				try {
					preparedStatement=connect.prepareStatement(sql);
					preparedStatement.setString(1, forgotpass_username.getText());
					resultSet=preparedStatement.executeQuery();
					if(resultSet.next()) {
						String checkquestion="Select * from user where username=? and security_question=? and answer=?";
						preparedStatement=connect.prepareStatement(checkquestion);
						preparedStatement.setString(1, forgotpass_username.getText());
						preparedStatement.setString(2, forgotpass_question.getSelectionModel().getSelectedItem());
						preparedStatement.setString(3, forgotpass_answer.getText());
						resultSet=preparedStatement.executeQuery();
						if(resultSet.next()) {
							Data.temp_username=forgotpass_username.getText();
							reset_form.setVisible(true);
							reset_form.setDisable(false);
							forgotpass_form.setVisible(false);
							forgotpass_form.setDisable(true);
						}else {
							alert.errorMessage("Invalid username/answer!");
							forgotpass_username.clear();
							forgotpass_question.getSelectionModel().clearSelection();
							forgotpass_answer.clear();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
			        if (preparedStatement != null) {
			            preparedStatement.close();
			            connect.close();
			        }
			    }
		}
	}
	
	public void resetPassword() throws ClassNotFoundException, SQLException {
		if(reset_password1.getText().isEmpty() ||
			reset_password2.getText().isEmpty()) {
			if (reset_password1.getText().isEmpty()) {
				reset_password1.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				reset_password1.setPromptText("Please fill this field");
				reset_password1.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.isEmpty()) {
			        	reset_password1.setStyle("");
			        	reset_password1.setPromptText("First name");
			        }
			    });
			}
			if (reset_password2.getText().isEmpty()) {
				reset_password2.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				reset_password2.setPromptText("Please fill this field");
				reset_password2.textProperty().addListener((observable, oldValue, newValue) -> {
			        if (!newValue.isEmpty()) {
			        	reset_password2.setStyle("");
			        	reset_password2.setPromptText("Last name");
			        }
			    });
			}
		}else {
			try {
				if(reset_password2.getText().equals(reset_password1.getText())) {
					connect();
					String updateData="Update user set password=? where username=?";
					preparedStatement=connect.prepareStatement(updateData);
					preparedStatement.setString(1, reset_password1.getText());
					preparedStatement.setString(2, Data.temp_username);
					int updatedRows=preparedStatement.executeUpdate();
					if(updatedRows>0) {
						alert.successMessage("Successfully updated!");
						
						login_form.setVisible(true);
						login_form.setDisable(false);
						
						reset_form.setVisible(false);
						reset_form.setDisable(true);
					}
					}else {
						reset_password1.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
						warning_label1.setText("Passwords must match!");
						reset_password1.textProperty().addListener((observable, oldValue, newValue) -> {
					        if (!newValue.isEmpty()) {
					        	reset_password1.setStyle("");
					        	warning_label1.setText("");
					        }
					    });	
						
						reset_password2.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
						warning_label2.setText("Passwords must match!");
						reset_password2.textProperty().addListener((observable, oldValue, newValue) -> {
					        if (!newValue.isEmpty()) {
					        	reset_password2.setStyle("");
					        	warning_label2.setText("");
					        }
					    });	
					}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
		        if (preparedStatement != null) {
		            preparedStatement.close();
		            connect.close();
		        }
		    }
		}
	}
	
	private void enlargeButton(Button button) {
	    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), button);
	    scaleTransition.setToX(1.10);
	    scaleTransition.setToY(1.10);
	    scaleTransition.play();
	}

	private void resetButtonSize(Button button) {
	    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), button);
	    scaleTransition.setToX(1);
	    scaleTransition.setToY(1);
	    scaleTransition.play();
	}
	
	private void enlargeTextField(TextField textField) {
	    // Animacija za povećanje TextField-a
	    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), textField);
	    scaleTransition.setToX(1.05);
	    scaleTransition.setToY(1.05);
	    scaleTransition.play();
	}

	private void resetTextFieldSize(TextField textField) {
	    // Animacija za vraćanje TextField-a na originalnu veličinu
	    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), textField);
	    scaleTransition.setToX(1);
	    scaleTransition.setToY(1);
	    scaleTransition.play();
	}
	
	
	private void enlargeCB(ComboBox<String> cb) {
	    // Animacija za povećanje TextField-a
	    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), cb);
	    scaleTransition.setToX(1.075);
	    scaleTransition.setToY(1.075);
	    scaleTransition.play();
	}


	private void resetCB(ComboBox<String> cb) {
	    // Animacija za vraćanje TextField-a na originalnu veličinu
	    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), cb);
	    scaleTransition.setToX(1);
	    scaleTransition.setToY(1);
	    scaleTransition.play();
	}
	
	private void enlargeCBSH(CheckBox cb) {
	    // Animacija za povećanje TextField-a
	    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), cb);
	    scaleTransition.setToX(1.075);
	    scaleTransition.setToY(1.075);
	    scaleTransition.play();
	}

	private void resetCBSH(CheckBox cb) {
	    // Animacija za vraćanje TextField-a na originalnu veličinu
	    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(150), cb);
	    scaleTransition.setToX(1);
	    scaleTransition.setToY(1);
	    scaleTransition.play();
	}
	
	public void buttons() {
		login_btn.setOnMouseEntered(event -> enlargeButton(login_btn));
		login_btn.setOnMouseExited(event -> resetButtonSize(login_btn));
		
		register_btn.setOnMouseEntered(event -> enlargeButton(register_btn));
		register_btn.setOnMouseExited(event -> resetButtonSize(register_btn));
		
		forgotpass_btn.setOnMouseEntered(event -> enlargeButton(forgotpass_btn));
		forgotpass_btn.setOnMouseExited(event -> resetButtonSize(forgotpass_btn));
		
		reset_btn.setOnMouseEntered(event -> enlargeButton(reset_btn));
		reset_btn.setOnMouseExited(event -> resetButtonSize(reset_btn));
		
		register_btn.setOnMouseEntered(event -> enlargeButton(register_btn));
		register_btn.setOnMouseExited(event -> resetButtonSize(register_btn));

		forgotpass_btn.setOnMouseEntered(event -> enlargeButton(forgotpass_btn));
		forgotpass_btn.setOnMouseExited(event -> resetButtonSize(forgotpass_btn));
		
		reset_btn.setOnMouseEntered(event -> enlargeButton(reset_btn));
		reset_btn.setOnMouseExited(event -> resetButtonSize(reset_btn));
	}
	
	public void fields() {
		login_username.setOnMouseEntered(event -> enlargeTextField(login_username));
		login_username.setOnMouseExited(event -> resetTextFieldSize(login_username));
		
		login_password.setOnMouseEntered(event -> enlargeTextField(login_password));
		login_password.setOnMouseExited(event -> resetTextFieldSize(login_password));
		
		login_showpassword.setOnMouseEntered(event -> enlargeTextField(login_showpassword));
		login_showpassword.setOnMouseExited(event -> resetTextFieldSize(login_showpassword));
		
		login_checkbox.setOnMouseEntered(event -> enlargeCBSH(login_checkbox));
		login_checkbox.setOnMouseExited(event -> resetCBSH(login_checkbox));
		
		register_firstname.setOnMouseEntered(event -> enlargeTextField(register_firstname));
		register_firstname.setOnMouseExited(event -> resetTextFieldSize(register_firstname));
		
		register_lastname.setOnMouseEntered(event -> enlargeTextField(register_lastname));
		register_lastname.setOnMouseExited(event -> resetTextFieldSize(register_lastname));
		
		register_username.setOnMouseEntered(event -> enlargeTextField(register_username));
		register_username.setOnMouseExited(event -> resetTextFieldSize(register_username));
		
		register_password.setOnMouseEntered(event -> enlargeTextField(register_password));
		register_password.setOnMouseExited(event -> resetTextFieldSize(register_password));
		
		register_showpassword.setOnMouseEntered(event -> enlargeTextField(register_showpassword));
		register_showpassword.setOnMouseExited(event -> resetTextFieldSize(register_showpassword));
		
		register_checkbox.setOnMouseEntered(event -> enlargeCBSH(register_checkbox));
		register_checkbox.setOnMouseExited(event -> resetCBSH(register_checkbox));
		
		register_question.setOnMouseEntered(event -> enlargeCB(register_question));
		register_question.setOnMouseExited(event -> resetCB(register_question));
		
		register_answer.setOnMouseEntered(event -> enlargeTextField(register_answer));
		register_answer.setOnMouseExited(event -> resetTextFieldSize(register_answer));
		
		forgotpass_username.setOnMouseEntered(event -> enlargeTextField(forgotpass_username));
		forgotpass_username.setOnMouseExited(event -> resetTextFieldSize(forgotpass_username));
		
		forgotpass_question.setOnMouseEntered(event -> enlargeCB(forgotpass_question));
		forgotpass_question.setOnMouseExited(event -> resetCB(forgotpass_question));
		
		forgotpass_answer.setOnMouseEntered(event -> enlargeTextField(forgotpass_answer));
		forgotpass_answer.setOnMouseExited(event -> resetTextFieldSize(forgotpass_answer));
		
		reset_password1.setOnMouseEntered(event -> enlargeTextField(reset_password1));
		reset_password1.setOnMouseExited(event -> resetTextFieldSize(reset_password1));
		
		reset_password2.setOnMouseEntered(event -> enlargeTextField(reset_password2));
		reset_password2.setOnMouseExited(event -> resetTextFieldSize(reset_password2));
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		QuestionList();
		buttons();
		fields();
	}
}
