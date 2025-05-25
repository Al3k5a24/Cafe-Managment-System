package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class CafeShopController implements Initializable {

	@FXML
	private StackPane stackPane;
	
	@FXML
	private AreaChart<?, ?> DI_chart;

	@FXML
	private AnchorPane Inventory_form;

	@FXML
	private BarChart<?, ?> NOC_chart;

	@FXML
	private Label NOC_display;

	@FXML
	private Label NSP_display;

	@FXML
	private Label TI_display;

	@FXML
	private Label TTI_display;

	@FXML
	private Button clear_btn;

	@FXML
	private Button dashboard_btn;

	@FXML
	private AnchorPane dashboard_form;

	@FXML
	private Button delete_btn;

	@FXML
	private AnchorPane display_product_image;

	@FXML
	private Button import_productimage;

	@FXML
	private Button insert_btn;

	@FXML
	private TextField insert_name;

	@FXML
	private TextField insert_price;

	@FXML
	private TextField insert_productID;

	@FXML
	private ComboBox<String> insert_status;

	@FXML
	private TextField insert_stock;

	@FXML
	private ComboBox<String> insert_type;

	@FXML
	private TableColumn<productData, String> inv_col_date;

	@FXML
	private TableColumn<productData, String> inv_col_name;

	@FXML
	private TableColumn<productData, String> inv_col_price;

	@FXML
	private TableColumn<productData, String> inv_col_productID;

	@FXML
	private TableColumn<productData, String> inv_col_status;

	@FXML
	private TableColumn<productData, String> inv_col_stock;

	@FXML
	private TableColumn<productData, String> inv_col_type;

	@FXML
	private Button inventory_btn;

	@FXML
	private TableView<productData> inventory_tableview;

	@FXML
	private Button menu_btn;

	@FXML
	private ImageView noc_image;

	@FXML
	private ImageView nsp_image;

	@FXML
	private Button singout_btn;

	@FXML
	private ImageView ti2_image;

	@FXML
	private ImageView ti_image;

	@FXML
	private Button update_btn;

	@FXML
	private TextField display_change;

	@FXML
	private TextField display_totalCharge;

	@FXML
	private TextField enter_pay;

	@FXML
	private ComboBox<String> menu_combobox;

	@FXML
	private AnchorPane menu_form;

	@FXML
	private GridPane menu_gridPane;

	@FXML
	private ScrollPane menu_scrollPane;

	@FXML
	private Button pay_btn;

	@FXML
	private Button receipt_btn;

	@FXML
	private Button remove_btn;

	@FXML
	private Button table_1;

	@FXML
	private Button table_10;

	@FXML
	private Button table_11;

	@FXML
	private Button table_12;

	@FXML
	private Button table_13;

	@FXML
	private Button table_14;

	@FXML
	private Button table_15;

	@FXML
	private Button table_16;

	@FXML
	private Button table_17;

	@FXML
	private Button table_18;

	@FXML
	private Button table_19;

	@FXML
	private Button table_2;

	@FXML
	private Button table_3;

	@FXML
	private Button table_4;

	@FXML
	private Button table_5;

	@FXML
	private Button table_6;

	@FXML
	private Button table_7;

	@FXML
	private Button table_8;

	@FXML
	private Button table_9;

	@FXML
	private Button tables_btn;

	@FXML
	private AnchorPane tables_form;

	@FXML
	private TableColumn<orderData,String> tables_price;

	@FXML
	private TableColumn<orderData,String> tables_productName;

	@FXML
	private TableColumn<orderData,String> tables_quantity;

	@FXML
	private TableView<orderData> tables_tableview;
	
	@FXML
	private Button putorder_btn;
	
	@FXML
    private Button res_add;

    @FXML
    private ComboBox<String> res_combobox;

    @FXML
    private TableColumn<reservationData,String> res_customer;
    
    @FXML
    private TableColumn<reservationData,String> res_reservationID;

    @FXML
    private DatePicker res_datepicker;

    @FXML
    private TableColumn<reservationData,String> res_table;

    @FXML
    private TableView<reservationData> res_tableview;

    @FXML
    private TextField res_textfield;

    @FXML
    private TableColumn<reservationData,String> res_time;

    @FXML
    private ComboBox<String> res_timecombobox;

    @FXML
    private TextField search_bar;

    @FXML
    private Button search_btn;
    
    @FXML
    private CheckBox check_drinks;

    @FXML
    private CheckBox check_meals;
    
    @FXML
    private Button viewBtn;

	private static final AlertMessage alert = new AlertMessage();

	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	public void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/cafe", "root", "");
	}

	@FXML
	public void logout() {
		try {
			if (alert.confirmationMessage("Are you sure you want to logout?")) {

				Parent root = FXMLLoader.load(getClass().getResource("StartPage.fxml"));
				Stage stage = new Stage();
				stage.setScene(new Scene(root));
				stage.show();

				stage.setTitle("Cafe Managment System");

				singout_btn.getScene().getWindow().hide();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void tableOrderNumber(Button button) {
	    button.setOnAction(event -> {
	        String tekst = ((Button) event.getSource()).getText();
	        Data.table_number=tekst;
	        System.out.println(Data.table_number);
	    	System.out.println("Pritisnuto je dugme za sto broj: " + Data.table_number);
	    });
	}
	
	private List<Button> getAllTableButtons() {
	    return List.of(
	        table_1, table_2, table_3, table_4, table_5, table_6, table_7,
	        table_8, table_9, table_10, table_11, table_12, table_13,
	        table_14, table_15, table_16, table_17, table_18, table_19
	    );
	}
	


	public void switchForm(ActionEvent event) throws ClassNotFoundException, SQLException {
		if (event.getSource() == dashboard_btn) {
			dashboard_form.setVisible(true);
			dashboard_form.setDisable(false);
			dashboardNOR();
			Inventory_form.setVisible(false);
			Inventory_form.setDisable(true);
			tables_form.setVisible(false);
			tables_form.setDisable(true);
			menu_form.setVisible(false);
			menu_form.setDisable(true);
		} else if (event.getSource() == inventory_btn) {
			dashboard_form.setVisible(false);
			dashboard_form.setDisable(true);
			Inventory_form.setVisible(true);
			Inventory_form.setDisable(false);
			productGetData();
			AppointmentShowData();
			tables_form.setVisible(false);
			tables_form.setDisable(true);
			menu_form.setVisible(false);
			menu_form.setDisable(true);
		} else if(event.getSource()==tables_btn) {
			dashboard_form.setVisible(false);
			dashboard_form.setDisable(true);
			Inventory_form.setVisible(false);
			Inventory_form.setDisable(true);
			tables_form.setVisible(true);
			tables_form.setDisable(false);
			menu_form.setVisible(false);
			menu_form.setDisable(true);
			List<Button> tableButtons = getAllTableButtons();
	        changeColorOrder(tableButtons);
		}else if(event.getSource()==menu_btn) {
			dashboard_form.setVisible(false);
			dashboard_form.setDisable(true);
			Inventory_form.setVisible(false);
			Inventory_form.setDisable(true);
			tables_form.setVisible(false);
			tables_form.setDisable(true);
			menu_form.setVisible(true);
			menu_form.setDisable(false);
			menuGetData();
			menuDisplayCard();
			reservationGetData();
            ReservationShowData();
            clearReservation();
		}
	}

	public void TypeList() {
		List<String> listG = new ArrayList<>();
		for (String data : Data.type) {
			listG.add(data);
		}
		ObservableList<String> listData = FXCollections.observableArrayList(listG);
		insert_type.setItems(listData);
	}

	public void StatusList() {
		List<String> listG = new ArrayList<>();
		for (String data : Data.status) {
			listG.add(data);
		}
		ObservableList<String> listData = FXCollections.observableArrayList(listG);
		insert_status.setItems(listData);
	}
	
	public void TablesList() {
		List<String> listG = new ArrayList<>();
		for (String data : Data.tables) {
			listG.add(data);
		}
		ObservableList<String> listData = FXCollections.observableArrayList(listG);
		res_combobox.setItems(listData);
	}
	
	public void ReservationList() {
		List<String> listG = new ArrayList<>();
		for (String data : Data.reservation) {
			listG.add(data);
		}
		ObservableList<String> listData = FXCollections.observableArrayList(listG);
		res_timecombobox.setItems(listData);
	}

	public ObservableList<productData> productGetData() throws ClassNotFoundException, SQLException {
		ObservableList<productData> listData = FXCollections.observableArrayList();
		String sql = "SELECT * FROM product";

		connect();

		try {
			preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

//            String productID, String productName, String type, Integer stock, Double price, String status,
//			String image,Date date

			while (resultSet.next()) {
				productData appdata = new productData(resultSet.getString("product_id"),
						resultSet.getString("product_name"), resultSet.getString("type"), resultSet.getInt("stock"),
						resultSet.getDouble("price"), resultSet.getString("status"), resultSet.getString("image"),
						resultSet.getDate("date"));

				listData.add(appdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Greška pri učitavanju podataka: " + e.getMessage());
		}
		return listData;
	}

	public ObservableList<productData> ProductListData;

	public void AppointmentShowData() throws ClassNotFoundException, SQLException {
		ProductListData = productGetData();

		inv_col_productID.setCellValueFactory(new PropertyValueFactory<productData, String>("product_id"));
		inv_col_name.setCellValueFactory(new PropertyValueFactory<productData, String>("product_name"));
		inv_col_type.setCellValueFactory(new PropertyValueFactory<productData, String>("type"));
		inv_col_stock.setCellValueFactory(new PropertyValueFactory<productData, String>("stock"));
		inv_col_price.setCellValueFactory(new PropertyValueFactory<productData, String>("price"));
		inv_col_status.setCellValueFactory(new PropertyValueFactory<productData, String>("status"));
		inv_col_date.setCellValueFactory(new PropertyValueFactory<productData, String>("date"));

		inventory_tableview.setItems(ProductListData);
		inventory_tableview.refresh();
	}

	public void profileChangeProfile() {
		FileChooser open = new FileChooser();
		open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image", "*.png", "*.jpg", "*.jpeg"));

		// Provera da li AnchorPane postoji
		if (display_product_image == null) {
			System.out.println("ERROR: display_product_image je null! Proverite FXML fajl i @FXML anotaciju.");
			return;
		}

		// Provera da li scena postoji
		if (display_product_image.getScene() == null) {
			System.out.println("ERROR: display_product_image.getScene() je null! GUI možda još nije učitan.");
			return;
		}

		// Otvaranje File Choosera
		File file = open.showOpenDialog(display_product_image.getScene().getWindow());

		if (file == null) {
			System.out.println("Nije izabrana nijedna slika.");
			return;
		}

		// Provera da li fajl postoji i može da se učita
		if (!file.exists() || !file.canRead()) {
			System.out.println("ERROR: Izabrana slika ne postoji ili ne može biti pročitana.");
			return;
		}

		System.out.println("Izabrana slika: " + file.getAbsolutePath());
		Data.path = file.getAbsolutePath();

		// Učitavanje slike i postavljanje na AnchorPane
		try {
			Image image = new Image(file.toURI().toString(), 300, 200, false, true);

			if (image.isError()) {
				throw new IOException("Problem pri učitavanju slike.");
			}

			BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
					BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
					new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

			display_product_image.setBackground(new Background(backgroundImage));
			System.out.println("Slika je uspešno postavljena na AnchorPane.");

		} catch (Exception e) {
			System.out.println("ERROR: Greška pri učitavanju slike -> " + e.getMessage());
		}

	}

	private Image image;

	public void insertProduct() throws ClassNotFoundException, SQLException {
		if (insert_productID.getText().isEmpty() || insert_name.getText().isEmpty()
				|| insert_type.getSelectionModel().isEmpty() || insert_stock.getText().isEmpty()
				|| insert_price.getText().isEmpty() || insert_status.getSelectionModel().isEmpty()
				|| display_product_image.getBackground() == null) {

			if (insert_productID.getText().isEmpty()) {
				insert_productID.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_productID.setPromptText("Please fill this field");
				insert_productID.textProperty().addListener((observable, oldValue, newValue) -> {
					if (!newValue.isEmpty()) {
						insert_productID.setStyle("");
						insert_productID.setPromptText("Product ID");
					}
				});
			}
			if (insert_name.getText().isEmpty()) {
				insert_name.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_name.setPromptText("Please fill this field");
				insert_name.textProperty().addListener((observable, oldValue, newValue) -> {
					if (!newValue.isEmpty()) {
						insert_name.setStyle("");
						insert_name.setPromptText("Product Name");
					}
				});
			}
			if (insert_stock.getText().isEmpty()) {
				insert_stock.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_stock.setPromptText("Please fill this field");
				insert_stock.textProperty().addListener((observable, oldValue, newValue) -> {
					if (!newValue.isEmpty()) {
						insert_stock.setStyle("");
						insert_stock.setPromptText("Stock");
					}
				});
			}
			if (insert_price.getText().isEmpty()) {
				insert_price.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_price.setPromptText("Please fill this field");
				insert_price.textProperty().addListener((observable, oldValue, newValue) -> {
					if (!newValue.isEmpty()) {
						insert_price.setStyle("");
						insert_price.setPromptText("0.0$");
					}
				});
			}
			if (insert_type.getSelectionModel().isEmpty()) {
				insert_type.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_type.valueProperty().addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						insert_type.setStyle("");
						insert_type.setPromptText("Type");
					}
				});
			}
			if (insert_status.getSelectionModel().isEmpty()) {
				insert_status.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_status.valueProperty().addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						insert_status.setStyle("");
						insert_status.setPromptText("Stock");
					}
				});
			}
			if (display_product_image.getBackground() == null) {
				display_product_image.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				alert.errorMessage("Please add an image!");
			}
		} else {
			connect();
			String sql = "Insert into product(product_id,product_name,type,stock,price,status,image,date) values(?,?,?,?,?,?,?,?)";

			try {
				preparedStatement = connect.prepareStatement(sql);
				preparedStatement.setString(1, insert_productID.getText());
				preparedStatement.setString(2, insert_name.getText());
				preparedStatement.setString(3, insert_type.getSelectionModel().getSelectedItem());
				preparedStatement.setString(4, insert_stock.getText());
				preparedStatement.setString(5, insert_price.getText());
				preparedStatement.setString(6, insert_status.getSelectionModel().getSelectedItem());
				preparedStatement.setString(7, Data.path);
				Date date = new Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				preparedStatement.setDate(8, sqlDate);

				int insertedRows = preparedStatement.executeUpdate();
				if (insertedRows > 0) {
					alert.successMessage("Successfully inserted!");
					clearFields();
					productGetData();
					AppointmentShowData();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void clearFields() {
		insert_productID.clear();
		insert_name.clear();
		insert_type.getSelectionModel().clearSelection();
		insert_stock.clear();
		insert_price.clear();
		insert_status.getSelectionModel().clearSelection();
		display_product_image.setBackground(null);
		Data.path = "";

	}

	@FXML
	public void productSelect() throws IOException {

		productData appData = inventory_tableview.getSelectionModel().getSelectedItem();
		int num = inventory_tableview.getSelectionModel().getSelectedIndex();

		if ((num - 1) < -1)
			return;

		Data.temp_productID = appData.getProduct_id();
		insert_productID.setText("" + appData.getProduct_id());
		insert_productID.setDisable(true);
		insert_name.setText(appData.getProduct_name());
		insert_type.getSelectionModel().select(insert_type.getItems().indexOf(appData.getType()));
		insert_stock.setText("" + appData.getStock());
		insert_price.setText("" + appData.getPrice());
		insert_status.getSelectionModel().select(insert_status.getItems().indexOf(appData.getStatus()));

		if (appData.getImage() != null) {
			try {
				// Convert the local file path to a valid URL
				File file = new File(appData.getImage()); // Assuming appData.getImage() returns a file path
				Image image = new Image(file.toURI().toString(), 300, 200, false, true);

				if (image.isError()) {
					throw new IOException("Problem pri učitavanju slike.");
				}

				BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
						BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
						new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));

				display_product_image.setBackground(new Background(backgroundImage));
				System.out.println("Slika je uspešno postavljena na AnchorPane.");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				// Handle error if the file path is not valid
			}
		}
		inventory_tableview.getSelectionModel().clearSelection();

	}

	@FXML
	public void resetFields() {
		insert_productID.clear();
		insert_name.clear();
		insert_type.getSelectionModel().clearSelection();
		insert_stock.clear();
		insert_price.clear();
		insert_status.getSelectionModel().clearSelection();
		display_product_image.setBackground(null);
	}

	@FXML
	public void updateDataProduct() throws SQLException, ClassNotFoundException {
		if (insert_productID.getText().isEmpty() || insert_name.getText().isEmpty()
				|| insert_type.getSelectionModel().isEmpty() || insert_stock.getText().isEmpty()
				|| insert_price.getText().isEmpty() || insert_status.getSelectionModel().isEmpty()) {

			if (insert_productID.getText().isEmpty()) {
				insert_productID.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_productID.setPromptText("Please fill this field");
				insert_productID.textProperty().addListener((observable, oldValue, newValue) -> {
					if (!newValue.isEmpty()) {
						insert_productID.setStyle("");
						insert_productID.setPromptText("Product ID");
					}
				});
			}
			if (insert_name.getText().isEmpty()) {
				insert_name.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_name.setPromptText("Please fill this field");
				insert_name.textProperty().addListener((observable, oldValue, newValue) -> {
					if (!newValue.isEmpty()) {
						insert_name.setStyle("");
						insert_name.setPromptText("Product Name");
					}
				});
			}
			if (insert_stock.getText().isEmpty()) {
				insert_stock.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_stock.setPromptText("Please fill this field");
				insert_stock.textProperty().addListener((observable, oldValue, newValue) -> {
					if (!newValue.isEmpty()) {
						insert_stock.setStyle("");
						insert_stock.setPromptText("Stock");
					}
				});
			}
			if (insert_price.getText().isEmpty()) {
				insert_price.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_price.setPromptText("Please fill this field");
				insert_price.textProperty().addListener((observable, oldValue, newValue) -> {
					if (!newValue.isEmpty()) {
						insert_price.setStyle("");
						insert_price.setPromptText("0.0$");
					}
				});
			}
			if (insert_type.getSelectionModel().isEmpty()) {
				insert_type.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_type.valueProperty().addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						insert_type.setStyle("");
						insert_type.setPromptText("Type");
					}
				});
			}
			if (insert_status.getSelectionModel().isEmpty()) {
				insert_status.setStyle("-fx-border-color: red; -fx-border-width: 1px;");
				insert_status.valueProperty().addListener((observable, oldValue, newValue) -> {
					if (newValue != null) {
						insert_status.setStyle("");
						insert_status.setPromptText("Stock");
					}
				});
			}
		} else {
			try {
				connect();
				String sql = "Select * from product where product_id=?";
				preparedStatement = connect.prepareStatement(sql);
				preparedStatement.setString(1, insert_productID.getText());
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					String updateData = "UPDATE product SET product_id = ?, product_name = ? ,type = ?, stock = ?, price = ?, image = ? , status = ? WHERE product_id = ?";
					preparedStatement = connect.prepareStatement(updateData);
					preparedStatement.setString(1, insert_productID.getText());
					preparedStatement.setString(2, insert_name.getText());
					preparedStatement.setString(3, insert_type.getSelectionModel().getSelectedItem());
					preparedStatement.setString(4, insert_stock.getText());
					preparedStatement.setString(5, insert_price.getText());

					// Ako slika nije promenjena, zadrži postojeću
					String path = null;
					String existingImagePath = resultSet.getString("image"); // Postojeća putanja slike u bazi
					if (Data.path != null && !Data.path.isEmpty()) {
						// Ako je korisnik odabrao novu sliku, kopiraj je
						path = Data.path;
					} else if (existingImagePath != null && !existingImagePath.isEmpty()) {
						// Ako nije selektovana nova slika, zadrži postojeću
						path = existingImagePath;
					} else {
						alert.errorMessage("No image selected or available.");
						return;
					}

					// Kreiraj direktorijum ako ne postoji
					java.nio.file.Path targetDir = Paths
							.get("C:\\Users\\Win10\\Desktop\\Java\\JavaFx\\CafeManagment\\src\\Slike\\Slike-save");
					if (!Files.exists(targetDir)) {
						try {
							Files.createDirectories(targetDir);
						} catch (IOException e) {
							e.printStackTrace();
							alert.errorMessage("Error creating directories: " + e.getMessage());
							return;
						}
					}

					// Ako je slika nova, kopiraj je
					if (Data.path != null && !Data.path.isEmpty()) {
						path = path.replace("\\", "\\\\");
						java.nio.file.Path transfer = Paths.get(path);
						java.nio.file.Path copy = Paths
								.get("C:\\Users\\Win10\\Desktop\\Java\\JavaFx\\CafeManagment\\src\\Slike\\Slike-save"
										+ insert_name.getText() + ".jpg");

						try {
							Files.copy(transfer, copy, StandardCopyOption.REPLACE_EXISTING);
						} catch (IOException e) {
							e.printStackTrace();
							alert.errorMessage("Error while copying the image: " + e.getMessage());
							return;
						}
						path = copy.toAbsolutePath().toString();
					}

					// Postavljanje slike kao pozadine u AnchorPane-u
					try {
						File file = new File(path); // Putanja do slike
						URL imageUrl = file.toURI().toURL();

						Image img = new Image(imageUrl.toString()); // Kreiranje slike sa validnim URL-om
						BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT,
								BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
								new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, true, false));

						display_product_image.setBackground(new Background(backgroundImage)); // Postavljanje slike kao
																								// pozadine
					} catch (MalformedURLException e) {
						e.printStackTrace();
						alert.errorMessage("Error setting image as background: " + e.getMessage());
					}

					preparedStatement.setString(6, path);
					preparedStatement.setString(7, insert_status.getSelectionModel().getSelectedItem());
					preparedStatement.setString(8, Data.temp_productID);

					int rowsUpdated = preparedStatement.executeUpdate();
					if (rowsUpdated > 0 && alert.confirmationMessage(
							"Are you sure that you want to update product ID:" + Data.temp_productID + "?")) {
						alert.successMessage("Successfully updated!");
						productGetData();
						AppointmentShowData();
						clearFields();
					} else {
						alert.errorMessage("Update failed!");
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (preparedStatement != null) {
					preparedStatement.close();
					connect.close();
				}
			}
		}
	}

	public void deleteProduct() throws SQLException {
		String deleteData = "DELETE FROM product WHERE product_id=? and table_id=?";
		try {
			connect(); // Povezivanje sa bazom podataka
			preparedStatement = connect.prepareStatement(deleteData);
			preparedStatement.setString(1, insert_productID.getText());
			preparedStatement.setString(2, Data.table_number);

			// Prvo pitajte korisnika za potvrdu
			if (alert.confirmationMessage(
					"Are you sure that you want to delete product ID: " + insert_productID.getText() + " ?")) {
				// Ako korisnik potvrdi, izvršite brisanje
				int deletedRows = preparedStatement.executeUpdate();

				if (deletedRows > 0) {
					alert.successMessage("Product with ID:" + insert_productID.getText() + "has been deleted successfully.\"");
					clearFields();
				} else {
					alert.errorMessage("No product found with the given ID.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			alert.errorMessage("Error occurred while deleting the product: " + e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
				connect.close();
			}
		}

	}
	
	private ObservableList<productData> cardListData = FXCollections.observableArrayList();

	public ObservableList<productData> menuGetData() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM product";
		connect();

		ObservableList<productData> listData = FXCollections.observableArrayList();

		try {
			preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				productData prod = new productData(resultSet.getInt("id"), resultSet.getString("product_id"),
						resultSet.getString("product_name"), resultSet.getDouble("price"),
						resultSet.getString("image"));
				listData.add(prod);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
				connect.close();
			}
		}

		return listData;
	}
	
	public void menuDisplayCard() throws ClassNotFoundException, SQLException {
	    cardListData.clear();
	    cardListData.addAll(menuGetData());

	    int row = 0;
	    int column = 0;

	    menu_gridPane.getChildren().clear();
	    menu_gridPane.getRowConstraints().clear();
	    menu_gridPane.getColumnConstraints().clear();

	    try {
	        for (int q = 0; q < cardListData.size(); q++) {
	        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/cardProduct.fxml"));
	        	loader.setRoot(new AnchorPane());
	            AnchorPane pane = loader.load();
	            CardProductController cardC = loader.getController();
	            cardC.setData(cardListData.get(q));

	            menu_gridPane.add(pane, column, row);
	            GridPane.setMargin(pane, new Insets(10));

	            column++;
	            if (column == 3) {
	                column = 0;
	                row++;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	public ObservableList<orderData> tableGetData() throws ClassNotFoundException, SQLException {
		ObservableList<orderData> listData = FXCollections.observableArrayList();
		String sql = "SELECT * FROM table_orders where table_id=? and status IS NULL";

		connect();

		try {
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1,Data.table_number);
			resultSet = preparedStatement.executeQuery();

//			String product_id, String product_name, Integer table_id, Integer quantity, Double price

			while (resultSet.next()) {
				orderData appdata = new orderData(resultSet.getString("product_id"),
						resultSet.getString("product_name"), resultSet.getInt("table_id"), resultSet.getInt("quantity"),
						resultSet.getDouble("price"));

				listData.add(appdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Greška pri učitavanju podataka: " + e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
				connect.close();
			}
		}
		return listData;
	}


	public ObservableList<orderData> orderListData;

	public void OrderShowData() throws ClassNotFoundException, SQLException {
		orderListData = tableGetData();

		tables_productName.setCellValueFactory(new PropertyValueFactory<>("product_name"));
		tables_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		tables_price.setCellValueFactory(new PropertyValueFactory<>("price"));

		tables_tableview.setItems(orderListData);
		tables_tableview.refresh();
		
		double totalPay = 0.0;

		for (orderData order : orderListData) {
		    totalPay += order.getQuantity() * order.getPrice();
		}

		display_totalCharge.setText(""+totalPay);
	

	}
	
	public void getNumber(Button button) {
        button.setOnAction(event -> {
            String brojStola = ((Button) event.getSource()).getText();
            Data.table_number = brojStola;

            try {
                OrderShowData(); // učitavanje porudžbina za kliknuti sto
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

	public boolean tablehasOrder(String tableNumber) throws SQLException, ClassNotFoundException {
		String sql = "SELECT COUNT(*) FROM table_orders WHERE table_id = ? and status IS NULL";
		connect();
		try {
			preparedStatement = connect.prepareStatement(sql);
			preparedStatement.setString(1, tableNumber);

			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				int count = resultSet.getInt(1);
				return count > 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
				connect.close();
			}
		}
		return false;
	}
    
    public void changeColorOrder(List<Button> tableButton) throws SQLException, ClassNotFoundException {
        for (Button dugme : tableButton) {
            String brojStola = dugme.getText();

            if (tablehasOrder(brojStola)) {
                dugme.setTextFill(Color.DARKRED); // zauzet sto
            } else {
                dugme.setTextFill(Color.GREEN); // slobodan sto
            }
        }
    }
    
    public void displayChange() {
        try {
            if (!display_totalCharge.getText().isEmpty() && !enter_pay.getText().isEmpty()) {
                double total = Double.parseDouble(display_totalCharge.getText().replace(" $", "")); // Ukloniti "$" ako je prisutno
                double paid = Double.parseDouble(enter_pay.getText());
                double change = 0;
                if (total > paid) {
                    display_change.setText("");
                } else {
                    change =(paid-total);
                    display_change.setText(""+change);
                }
            } else {
                display_change.setText("");
            }
        } catch (NumberFormatException e) {
            display_change.setText("Invalid input");
        }
    }

    @FXML
    public void payBtn() throws ClassNotFoundException, SQLException {
    	if(enter_pay.getText().isEmpty() || display_change.getText().isEmpty()) {
    		alert.errorMessage("Please insert right amount to pay!");
    	}else {
    		connect();
    		String removeData="UPDATE table_orders SET status=?, value=?, date_paid=? WHERE table_id=?";
        	try {
    			preparedStatement = connect.prepareStatement(removeData);
    			preparedStatement.setString(1,"Paid");
    			preparedStatement.setString(2,display_totalCharge.getText());
    			Date date=new Date();
    			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
    			preparedStatement.setDate(3,sqlDate);
    	        preparedStatement.setString(4, Data.table_number);
    	        System.out.println(Data.table_number);
    	        int deletedRows=preparedStatement.executeUpdate();
    	        if(deletedRows>0) {
    	        	alert.successMessage("Order has been paid!");
    	        	changeColorOrder(getAllTableButtons());
    	        	OrderShowData();
    	        	clearFieldsOrder();
    	        }else {
    	        	alert.errorMessage("Something went wrong!");
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
    
    private void clearFieldsOrder() {
    	display_totalCharge.clear();
    	enter_pay.clear();
    	display_change.clear();
		
	}

	public void orderSelect() {
    	orderData appData = tables_tableview.getSelectionModel().getSelectedItem();
		int num = tables_tableview.getSelectionModel().getSelectedIndex();

		if ((num - 1) < -1)
			return;

		Data.temp_order_productName=appData.getProduct_name();
    }
    
    public void removeBtn() throws SQLException {
    	String selectData="Select * from table_orders where product_name=?";
    	try {
    		connect();
    		preparedStatement = connect.prepareStatement(selectData);
	        preparedStatement.setString(1, Data.temp_order_productName);
	        System.out.println(Data.temp_order_productName);
	        resultSet=preparedStatement.executeQuery();
	        if(resultSet.next()) {
	        	String removeData="Delete from table_orders where product_name=?";
	        	preparedStatement = connect.prepareStatement(removeData);
		        preparedStatement.setString(1, Data.temp_order_productName);
		        int deletedRows=preparedStatement.executeUpdate();
		        if(deletedRows>0) {
		        	alert.confirmationMessage("Are you sure that you want to remove product: "+Data.temp_order_productName + "?");
		        	alert.successMessage("Product successfully removed!");
		        	changeColorOrder(getAllTableButtons());
    	        	OrderShowData();
		        }else {
		        	alert.errorMessage("Something went wrong!");
		        }
	        }
	        
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
	        if (preparedStatement != null) {
	            preparedStatement.close();
	            connect.close();
	        }
	    }
    }
   



    public ObservableList<reservationData> reservationGetData() throws ClassNotFoundException, SQLException {
		ObservableList<reservationData> listData = FXCollections.observableArrayList();
		String sql = "SELECT * FROM reservations";

		connect();

		try {
			preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

//			Integer table_id, String customer, String time, Date date_reserved)

			while (resultSet.next()) {
				reservationData appdata = new reservationData(resultSet.getInt("reservation_id"),resultSet.getString("table_id"),
						resultSet.getString("customer"), resultSet.getString("time"), resultSet.getDate("date_reserved")
						);

				listData.add(appdata);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Greška pri učitavanju podataka: " + e.getMessage());
		} finally {
			if (preparedStatement != null) {
				preparedStatement.close();
				connect.close();
			}
		}
		return listData;
	}

    public ObservableList<reservationData> reservationListData;
    public void ReservationShowData() throws ClassNotFoundException, SQLException {
    	reservationListData = reservationGetData();

    	res_table.setCellValueFactory(new PropertyValueFactory<>("table_id"));
    	res_time.setCellValueFactory(new PropertyValueFactory<>("time"));
    	res_customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
    	res_reservationID.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
    	
		res_tableview.setItems(reservationListData);
		res_tableview.refresh();
		}

    public void reservationSelect() {
    	reservationData resData = res_tableview.getSelectionModel().getSelectedItem();
		int num = res_tableview.getSelectionModel().getSelectedIndex();

		if ((num - 1) < -1)
			return;

		res_combobox.getSelectionModel().select(res_combobox.getItems().indexOf(resData.getTable_id()));
		res_timecombobox.getSelectionModel().select(res_timecombobox.getItems().indexOf(resData.getTime()));
		res_textfield.setText(""+resData.getReservation_id());
		
		res_datepicker.setValue(((java.sql.Date) resData.getDate_reserved()).toLocalDate());
		
		res_tableview.getSelectionModel().clearSelection();

    }
    
    public void addBtn() throws ClassNotFoundException, SQLException {
    	if(res_combobox.getSelectionModel().isEmpty() ||
    	   res_textfield.getText().isEmpty() ||
    	   res_datepicker.getValue().equals(null) ||
    	   res_timecombobox.getSelectionModel().isEmpty()) {
    		alert.errorMessage("Fill all blank fields!");
    	}else {
    		String selectData="Select * from reservations where time=? and table_id=?";
    		connect();
    		try {
    			preparedStatement = connect.prepareStatement(selectData);
    			preparedStatement.setString(1, res_timecombobox.getSelectionModel().getSelectedItem());
    			preparedStatement.setString(2, res_combobox.getSelectionModel().getSelectedItem());
    			resultSet = preparedStatement.executeQuery();
    			if(resultSet.next()) {
    				alert.errorMessage("Table: "+res_combobox.getSelectionModel().getSelectedItem()+ " is already taken at time: "+res_timecombobox.getSelectionModel().getSelectedItem());
    			}else {
    				String insertData="Insert into reservations(reservation_id,table_id,customer,time,date_reserved) values(?,?,?,?,?)";
    				preparedStatement = connect.prepareStatement(insertData);
    				int randomNumber = ThreadLocalRandom.current().nextInt(10000, 10000000);
    				preparedStatement.setInt(1,randomNumber);
        			preparedStatement.setString(2, res_combobox.getSelectionModel().getSelectedItem());
        			preparedStatement.setString(3, res_textfield.getText());
    				preparedStatement.setString(4, res_timecombobox.getSelectionModel().getSelectedItem());
    				preparedStatement.setDate(5, java.sql.Date.valueOf(res_datepicker.getValue()));
    				int insertedRows=preparedStatement.executeUpdate();
    				if(insertedRows>0) {
    					alert.successMessage("Successfully inserted reservation!");
    					 reservationGetData();
    			         ReservationShowData();
    			         clearReservation();
    				}else {
    					alert.errorMessage("Something went wrong!");
    				}
    			}
			} catch (Exception e) {
				// TODO: handle exception
			}
    	}
    }

    private void clearReservation() {
    	res_combobox.getSelectionModel().select("Choose Table");
    	res_timecombobox.getSelectionModel().select("Choose Time");
    	res_textfield.setText("");;
    	res_datepicker.setValue(null);
	}
    
    public ObservableList<reservationData> reservationGetDataByTable(String tableId) throws ClassNotFoundException, SQLException {
        ObservableList<reservationData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM reservations WHERE table_id = ?";

        connect();

        try {
            preparedStatement = connect.prepareStatement(sql);
            preparedStatement.setString(1, tableId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                reservationData appdata = new reservationData(
                	resultSet.getInt("reservation_id"),
                    resultSet.getString("table_id"),
                    resultSet.getString("customer"),
                    resultSet.getString("time"),
                    resultSet.getDate("date_reserved")
                );
                listData.add(appdata);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Greška pri učitavanju podataka: " + e.getMessage());
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
                connect.close();
            }
        }
        return listData;
    }

    public void filterReservationByTable() throws ClassNotFoundException, SQLException {
        String selectedTable = res_combobox.getValue();
        if (selectedTable == null || selectedTable.isEmpty()) return;

        reservationListData = reservationGetDataByTable(selectedTable);

        res_table.setCellValueFactory(new PropertyValueFactory<>("table_id"));
        res_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        res_customer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        res_reservationID.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));

        res_tableview.setItems(reservationListData);
        res_tableview.refresh();
    }

    public void viewBtn() {
    	res_tableview.setOnMouseClicked(event -> {
    	    reservationData selected = res_tableview.getSelectionModel().getSelectedItem();
    	    if (selected != null) {
    	        System.out.println("Selektovana rezervacija: " + selected.getReservation_id());
    	    }
    	});
    	
    	Data.temp_resID=res_textfield.getText();
    	System.out.println(Data.temp_resID);

        try {
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("ReservationEdit.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cafe Management System | Reservations");
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardNOR() {
    	String sql="Select count(reservation_id) from reservations where status='Reserved'";
    	try {
    		connect();
    		preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int getIP=0;
			if(resultSet.next()) {
				getIP=resultSet.getInt("count(reservation_id)");
			}
			NOC_display.setText(String.valueOf(getIP));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void dashboardTdyI() {
    	String sql="SELECT SUM(value) FROM table_orders WHERE status = 'Paid' AND DATE(date_paid) = CURDATE();";
    	try {
    		connect();
    		preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int getIP=0;
			if(resultSet.next()) {
				getIP=resultSet.getInt("SUM(value)");
			}
			TI_display.setText("$"+String.valueOf(getIP));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void dashboardTTI() {
    	String sql="SELECT SUM(value) FROM table_orders WHERE status = 'Paid'";
    	try {
    		connect();
    		preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int getIP=0;
			if(resultSet.next()) {
				getIP=resultSet.getInt("SUM(value)");
			}
			TTI_display.setText("$"+String.valueOf(getIP));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void dashboardNOSP() {
    	String sql="Select SUM(quantity) from table_orders WHERE status = 'Paid'";
    	try {
    		connect();
    		preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int getIP=0;
			if(resultSet.next()) {
				getIP=resultSet.getInt("SUM(quantity)");
			}
			NSP_display.setText(String.valueOf(getIP));
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void dashboardDIC() throws ClassNotFoundException, SQLException {
    	DI_chart.getData().clear();

        String sql = "SELECT DATE(date_paid) AS paid_date, SUM(value) AS total_value FROM table_orders WHERE status = 'Paid' GROUP BY DATE(date_paid) ORDER BY paid_date ASC LIMIT 8";

        connect();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	String date = resultSet.getString("paid_date");
                double total = resultSet.getDouble("total_value");
                chart.getData().add(new XYChart.Data(date, total));
            }

            DI_chart.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (resultSet != null) resultSet.close();
            if (connect != null) connect.close();
        }
    }
    
    public void dashboardNORC() throws ClassNotFoundException, SQLException {
    	NOC_chart.getData().clear();

        String sql = "SELECT date_reserved,count(reservation_id) AS total_value FROM reservations WHERE status = 'Reserved' GROUP BY date_reserved ORDER BY date_reserved ASC LIMIT 8";

        connect();

        try {
            XYChart.Series chart = new XYChart.Series<>();
            preparedStatement = connect.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            	String date = resultSet.getString("date_reserved");
                Integer total = resultSet.getInt("total_value");
                chart.getData().add(new XYChart.Data(date, total));
            }

            NOC_chart.getData().add(chart);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) preparedStatement.close();
            if (resultSet != null) resultSet.close();
            if (connect != null) connect.close();
        }
    }


	@Override
    public void initialize(URL location, ResourceBundle resources) {
     TypeList();
     StatusList();
   	 TablesList();
   	 ReservationList();
   	 dashboardNOSP();
        for (Button dugme : getAllTableButtons()) {
            getNumber(dugme);
			try {
				changeColorOrder(getAllTableButtons());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        res_combobox.setOnAction(event -> {
            String selected = res_combobox.getSelectionModel().getSelectedItem();

                try {
                	reservationGetDataByTable(selected);
                	filterReservationByTable();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if ("Choose Table".equals(selected)) {
            	 try {
            		 reservationGetData();
            		 ReservationShowData();           	     
            		 } catch (Exception e) {
                     e.printStackTrace();
                 }
            }
        });

        try {
        	dashboardNOR();
        	dashboardTdyI();
        	dashboardTTI();
        	dashboardDIC();
        	dashboardNORC();
            productGetData();
            AppointmentShowData();
            menuDisplayCard();
            OrderShowData();
            tableGetData();
            reservationGetData();
            ReservationShowData();
            filterReservationByTable();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

}
