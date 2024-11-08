module Sistema_BluePen {

	requires javafx.controls;
	requires java.sql;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.desktop;
	requires transitive javafx.base;
	
	exports packageController;
	
	opens packageModel to javafx.base;
	
	opens packageController to javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
}
