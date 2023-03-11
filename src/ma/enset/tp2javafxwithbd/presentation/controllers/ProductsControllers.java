package ma.enset.tp2javafxwithbd.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import ma.enset.tp2javafxwithbd.dao.CategorieImpl;
import ma.enset.tp2javafxwithbd.dao.ProductImpl;
import ma.enset.tp2javafxwithbd.dao.entites.Categorie;
import ma.enset.tp2javafxwithbd.dao.entites.Product;
import ma.enset.tp2javafxwithbd.services.CatalogueService;
import ma.enset.tp2javafxwithbd.services.CatalogueServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsControllers implements Initializable {

    @FXML
    private TextField textNom;
    @FXML
    private TextField textRef;
    @FXML
    private TextField textPrix;
    @FXML
    private TextField textSearch;
    @FXML
    private ComboBox<Categorie> comboCategories;


    private CatalogueService catalogueService;

    @FXML
    private TableView<Product> tableViewProduct;
    @FXML
    private TableColumn<Long,Product> columnId;
    @FXML
    private TableColumn<String,Product> columnName;
    @FXML
    private TableColumn<String,Product> columnRef;
    @FXML
    private TableColumn<Float,Product> columnPrix;
    @FXML
    private TableColumn<Categorie,Product> columnCat;





    ObservableList<Product> data= FXCollections.observableArrayList();
    private Product selectedProduct;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewProduct.setItems(data);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
        columnPrix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        columnCat.setCellValueFactory(new PropertyValueFactory<>("categorie"));

        catalogueService=new CatalogueServiceImpl(new ProductImpl(),new CategorieImpl());
        comboCategories.getItems().addAll(catalogueService.getAllCategories());
        loadData();
        textSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                data.clear();
                data.addAll(catalogueService.searchProductByQuery(newValue));
            }
        });

        tableViewProduct.selectionModelProperty().addListener((observable, oldValue, newValue) -> {

        });
    }

    private void loadData(){
        data.clear();
        List<Product> products=catalogueService.getAllProducts();
        data.addAll(products);
    }

    public void addProduct(){
        Product p=new Product();
        p.setName(textNom.getText());
        p.setReference(textRef.getText());
        p.setPrix(Float.parseFloat(textPrix.getText()));
        p.setCategorie(comboCategories.getSelectionModel().getSelectedItem());

        catalogueService.addProduct(p);
        loadData();
        clearAllTextField();
    }

    public void deleteProduct(){
        Product p=tableViewProduct.getSelectionModel().getSelectedItem();
        catalogueService.deleteProduct(p);
        loadData();
        clearAllTextField();
    }

    public void updateProduct(){
        selectedProduct.setName(textNom.getText());
        selectedProduct.setReference(textRef.getText());
        selectedProduct.setPrix(Float.parseFloat(textPrix.getText()));
        selectedProduct.setCategorie(comboCategories.getSelectionModel().getSelectedItem());
        catalogueService.updateProduct(selectedProduct);
        loadData();
        clearAllTextField();
    }

    public void editProduct(){
        selectedProduct=tableViewProduct.getSelectionModel().getSelectedItem();
        textNom.setText(selectedProduct.getName());
        textRef.setText(selectedProduct.getReference());
        textPrix.setText(String.valueOf(selectedProduct.getPrix()));

        comboCategories.setValue(comboCategories.getSelectionModel().getSelectedItem());
    }

    public void clearAllTextField(){
        textNom.setText("");
        textRef.setText("");
        textPrix.setText(String.valueOf(""));
    }

}
