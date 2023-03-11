package ma.enset.tp2javafxwithbd.services;

import ma.enset.tp2javafxwithbd.dao.CategorieDao;
import ma.enset.tp2javafxwithbd.dao.ProductDao;
import ma.enset.tp2javafxwithbd.dao.entites.Categorie;
import ma.enset.tp2javafxwithbd.dao.entites.Product;

import java.util.List;

public class CatalogueServiceImpl implements CatalogueService{
    private ProductDao productDao;
    private CategorieDao categorieDao;

    public CatalogueServiceImpl(ProductDao productDao, CategorieDao categorieDao) {
        this.productDao = productDao;
        this.categorieDao = categorieDao;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public void addProduct(Product p) {
         productDao.save(p);
    }

    @Override
    public void updateProduct(Product p) {
         productDao.update(p);
    }

    @Override
    public void deleteProduct(Product p) {
        productDao.delete(p);
    }

    @Override
    public List<Product> searchProductByQuery(String s) {
        return productDao.findByQuery(s);
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieDao.findAll();
    }

    @Override
    public void addCategorie(Categorie c) {
        categorieDao.save(c);
    }

    @Override
    public void deleteCategorie(Categorie c) {
        categorieDao.delete(c);
    }
}
