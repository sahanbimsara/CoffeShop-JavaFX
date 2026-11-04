
package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CoffeShopLogin {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        // Check empty fields
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(
                    Alert.AlertType.WARNING,
                    "Please enter username and password!"
            );
            return;
        }

        // Temporary login credentials
        if (username.equals("admin")
                && password.equals("admin123")) {

            try {
                Parent root = FXMLLoader.load(
                        getClass().getResource("/View/dashboard.fxml")
                );

                Stage stage = (Stage) ((Node) event.getSource())
                        .getScene().getWindow();

                stage.setScene(new Scene(root));
                stage.setTitle("Coffee Shop - Dashboard");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
                showAlert(
                        Alert.AlertType.ERROR,
                        "Unable to open Dashboard!"
                );
            }

        } else {
            showAlert(
                    Alert.AlertType.ERROR,
                    "Invalid username or password!"
            );
        }
    }

    @FXML
    void btnPasswordOnAction(ActionEvent event) {
        // Optional: login when Enter is pressed
        btnLoginOnAction(event);
    }

    @FXML
    void btnRegisterNav(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(
                getClass().getResource("/View/Register.fxml")
        );

        Stage stage = (Stage) ((Node) event.getSource())
                .getScene().getWindow();

        stage.setScene(new Scene(root));
        stage.setTitle("Coffee Shop - Register");
        stage.show();
    }

    @FXML
    void btnUserNameOnAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    private void showAlert(
            Alert.AlertType type, String message) {

        Alert alert = new Alert(type);
        alert.setTitle("Coffee Shop Login");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
