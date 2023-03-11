package ma.enset.tp2javafxwithbd.services;

import ma.enset.tp2javafxwithbd.dao.CategorieImpl;
import ma.enset.tp2javafxwithbd.dao.ProductImpl;
import ma.enset.tp2javafxwithbd.dao.entites.Categorie;
import ma.enset.tp2javafxwithbd.dao.entites.Product;

import java.util.List;

public class Application1 {
    public static void main(String[] args) {
        CatalogueService catalogueService=new CatalogueServiceImpl(new ProductImpl(), new CategorieImpl());
        Categorie c=new Categorie();
        c.setId(1);
        c.setName("MAC");

        Product p1=new Product();
        p1.setName("BB");
        p1.setReference("REF1_BB");
        p1.setPrix(456);
        p1.setCategorie(c);

        //catalogueService.addProduct(p1);

        List<Product> products=catalogueService.getAllProducts();
        for(Product p: products){
            System.out.println("ID: "+p.getId()+" Name: "+p.getName()+" Ref: "+p.getReference()+" Prix: "+p.getPrix()+" CatID: "+p.getCategorie());
        }

    }
}
