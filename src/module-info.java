module CafeManagment {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires javafx.base;
    requires javafx.swing;
    requires java.desktop;
	requires net.sf.jasperreports.core;

    opens application to javafx.graphics, javafx.fxml, javafx.base;
}
