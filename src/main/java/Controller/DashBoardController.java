
package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardController {

    @FXML
    private Label lblProducts;

    @FXML
    private Label lblOrders;

    @FXML
    private Label lblSales;

    @FXML
    private Label lblCustomers;

    @FXML
    private Label lblMessage;

    @FXML
    public void initialize() {
        // Temporary data
        // We will connect MySQL later
        lblProducts.setText("0");
        lblOrders.setText("0");
        lblSales.setText("Rs. 0.00");
        lblCustomers.setText("0");
    }

    @FXML
    private void btnDashboard(ActionEvent event) {
        lblMessage.setText("Welcome to your dashboard!");
    }

    @FXML
    private void btnProducts(ActionEvent event) {
        showMessage("Product Management");
    }

    @FXML
    private void btnOrders(ActionEvent event) {
        showMessage("Order Management");
    }

    @FXML
    private void btnCustomers(ActionEvent event) {
        showMessage("Customer Management");
    }

    @FXML
    private void btnReports(ActionEvent event) {
        showMessage("Sales Reports");
    }

    @FXML
    private void btnLogout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/View/Login.fxml")
            );

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CoffeeShop - Login");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showMessage("Unable to open Login page.");
        }
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("CoffeeShop");
        alert.setHeaderText(message);
        alert.setContentText(
                "This feature will be implemented next."
        );
        alert.showAndWait();
    }
}
