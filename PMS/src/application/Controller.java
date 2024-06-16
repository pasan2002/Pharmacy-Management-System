package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller implements Initializable {
    @FXML
    private AnchorPane Main_Form;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    @FXML
    private Button closeButton;
    
    @FXML
    private Label closeLabel;
    
    private PreparedStatement prepare;
    private Connection connect;
    private ResultSet result;
    
    private double x = 0;
    private double y = 0;
    
    public void loginAdmin() {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        connect = dataBase.connectDb();
        
        try {
            prepare = connect.prepareStatement(sql);
            prepare.setString(1, username.getText());
            prepare.setString(2, password.getText());
            
            result = prepare.executeQuery();
            
            Alert alert;
            
            if(username.getText().isEmpty() || password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please enter your username and password correctly.");
                alert.showAndWait();
            } else {                
                if(result.next()) {
                    getData.username = username.getText();
                	alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully logged in.");
                    alert.showAndWait();
                    
                    loginBtn.getScene().getWindow().hide();
                    
                    Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    
                    root.setOnMousePressed((MouseEvent event) -> {
    					x = event.getSceneX();
    					y = event.getSceneY();
    				});
    				
    				root.setOnMouseDragged((MouseEvent event) -> {
    					stage.setX(event.getScreenX() - x);
    					stage.setY(event.getScreenY() - y);
    					
    				});
                    
                    stage.initStyle(StageStyle.TRANSPARENT);
                    
                    stage.setScene(scene);
                    stage.show();
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong username/password. Try again.");
                    alert.showAndWait();
                }
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Initialization code here
    }
}
