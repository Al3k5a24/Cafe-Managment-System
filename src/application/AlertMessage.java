package application;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertMessage {
private Alert alert;

public void errorMessage(String message) {
    alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error message");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}


//uspesni prozor
public void successMessage(String message) {
	alert=new Alert(AlertType.INFORMATION);
	alert.setTitle("Information message");
	alert.setHeaderText(null);
	alert.setContentText(message);
	alert.showAndWait();
}

//potvrdni prozor
public boolean confirmationMessage(String message) {
	alert=new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Confirmation message");
	alert.setHeaderText(null);
	alert.setContentText(message);
	alert.showAndWait();
	Optional<ButtonType> option= alert.showAndWait();
	
	if(option.get().equals(ButtonType.OK)) {
		return true;
	}else {
		return false;
	}
}
}
