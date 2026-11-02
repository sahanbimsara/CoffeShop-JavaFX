
package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class CoffeShopLogin {

    @FXML
    void btnLoginOnAction(ActionEvent event) {

    }

    @FXML
    void btnPasswordOnAction(ActionEvent event) {

    }

    @FXML
    void btnRegisterNav(ActionEvent event) throws IOException {

        // Load Register.fxml
        Parent root = FXMLLoader.load(
                getClass().getResource("/View/Register.fxml")
        );

        // Get current stage
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();

        // Open Register Form
        stage.setScene(new Scene(root));
        stage.setTitle("Coffee Shop - Register");
        stage.show();
    }

    @FXML
    void btnUserNameOnAction(ActionEvent event) {

    }
}
