package ma.enset.tp2javafxwithbd.services;

import ma.enset.tp2javafxwithbd.dao.entites.Categorie;
import ma.enset.tp2javafxwithbd.dao.entites.Product;

import java.util.List;

public interface CatalogueService {
    List<Product> getAllProducts();
    void addProduct(Product p);
    void updateProduct(Product p);
    void deleteProduct(Product p);
    List<Product> searchProductByQuery(String s);

    List<Categorie> getAllCategories();
    void addCategorie(Categorie c);
    void deleteCategorie(Categorie c);
}
