package ma.enset.tp2javafxwithbd.dao;

import ma.enset.tp2javafxwithbd.dao.entites.Product;

import java.util.List;

public interface ProductDao extends Dao<Product>{
    List<Product> findByQuery(String queruy);
}
