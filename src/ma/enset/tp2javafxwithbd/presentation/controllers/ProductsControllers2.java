package ma.enset.tp2javafxwithbd.presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ma.enset.tp2javafxwithbd.dao.CategorieImpl;
import ma.enset.tp2javafxwithbd.dao.ProductImpl;
import ma.enset.tp2javafxwithbd.dao.entites.Categorie;
import ma.enset.tp2javafxwithbd.dao.entites.Product;
import ma.enset.tp2javafxwithbd.services.CatalogueService;
import ma.enset.tp2javafxwithbd.services.CatalogueServiceImpl;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductsControllers2 implements Initializable {

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
    @FXML
    private ListView<Product> listviewProducts;

    private CatalogueService catalogueService;
/*
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
*/




    ObservableList<Product> data= FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listviewProducts.setItems(data);
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
    }

    public void deleteProduct(){
        Product p=listviewProducts.getSelectionModel().getSelectedItem();
        catalogueService.deleteProduct(p);
        loadData();
    }
}
