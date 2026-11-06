
package Controller;

import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ProductController {

    @FXML private TextField txtProductId;
    @FXML private TextField txtProductName;
    @FXML private TextField txtPrice;
    @FXML private TextField txtQuantity;
    @FXML private TextField txtSearch;

    @FXML private ComboBox<String> cmbCategory;
    @FXML private TableView<Product> tblProducts;

    @FXML private TableColumn<Product, Integer> colId;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, String> colCategory;
    @FXML private TableColumn<Product, Double> colPrice;
    @FXML private TableColumn<Product, Integer> colQuantity;

    private final ObservableList<Product> products =
            FXCollections.observableArrayList();

    private FilteredList<Product> filteredProducts;

    @FXML
    public void initialize() {

        cmbCategory.setItems(FXCollections.observableArrayList(
                "Coffee", "Tea", "Cold Drinks",
                "Bakery", "Desserts", "Snacks"
        ));

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(
                new PropertyValueFactory<>("category"));
        colPrice.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        colQuantity.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));

        filteredProducts = new FilteredList<>(products, p -> true);
        tblProducts.setItems(filteredProducts);

        tblProducts.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, selected) -> {
                    if (selected != null) {
                        txtProductId.setText(
                                String.valueOf(selected.getId()));
                        txtProductName.setText(selected.getName());
                        cmbCategory.setValue(selected.getCategory());
                        txtPrice.setText(
                                String.valueOf(selected.getPrice()));
                        txtQuantity.setText(
                                String.valueOf(selected.getQuantity()));
                    }
                });
    }

    private Product readForm() {
        try {
            int id = Integer.parseInt(
                    txtProductId.getText().trim());
            String name = txtProductName.getText().trim();
            String category = cmbCategory.getValue();
            double price = Double.parseDouble(
                    txtPrice.getText().trim());
            int quantity = Integer.parseInt(
                    txtQuantity.getText().trim());

            if (id <= 0 || name.isEmpty() ||
                    category == null || category.isBlank() ||
                    !Double.isFinite(price) ||
                    price < 0 || quantity < 0) {
                throw new IllegalArgumentException();
            }

            return new Product(id, name, category, price, quantity);

        } catch (IllegalArgumentException e) {
            showAlert("Enter valid product details. ID must be positive, "
                    + "price must be non-negative, and stock must be "
                    + "a non-negative whole number.");
            return null;
        }
    }

    @FXML
    private void btnAdd(ActionEvent event) {
        Product product = readForm();
        if (product == null) return;

        for (Product p : products) {
            if (p.getId() == product.getId()) {
                showAlert("Product ID already exists!");
                return;
            }
        }

        products.add(product);
        btnClear(null);
    }

    @FXML
    private void btnUpdate(ActionEvent event) {
        Product updated = readForm();
        if (updated == null) return;

        Product selected = tblProducts.getSelectionModel()
                .getSelectedItem();

        if (selected == null) {
            showAlert("Select a product to update.");
            return;
        }

        for (Product p : products) {
            if (p != selected && p.getId() == updated.getId()) {
                showAlert("Product ID already exists!");
                return;
            }
        }

        int index = products.indexOf(selected);
        products.set(index, updated);
        btnClear(null);
    }

    @FXML
    private void btnDelete(ActionEvent event) {
        Product selected = tblProducts.getSelectionModel()
                .getSelectedItem();

        if (selected == null) {
            showAlert("Select a product to delete.");
            return;
        }

        products.remove(selected);
        btnClear(null);
    }

    @FXML
    private void btnClear(ActionEvent event) {
        tblProducts.getSelectionModel().clearSelection();
        txtProductId.clear();
        txtProductName.clear();
        txtPrice.clear();
        txtQuantity.clear();
        cmbCategory.getSelectionModel().clearSelection();
    }

    @FXML
    private void searchProducts(KeyEvent event) {
        String keyword = txtSearch.getText()
                .trim().toLowerCase();

        filteredProducts.setPredicate(product ->
                product.getName().toLowerCase().contains(keyword)
                        || product.getCategory().toLowerCase().contains(keyword)
                        || String.valueOf(product.getId()).contains(keyword)
        );
    }

    @FXML
    private void btnBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/View/dashboard.fxml")
            );

            Stage stage = (Stage) ((Node) event.getSource())
                    .getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("CoffeeShop - Dashboard");

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Unable to open Dashboard.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Product Management");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
