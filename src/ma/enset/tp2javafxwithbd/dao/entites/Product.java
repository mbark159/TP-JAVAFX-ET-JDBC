package ma.enset.tp2javafxwithbd.dao.entites;

import java.io.Serializable;

public class Product implements Serializable {
    private long id;
    private String name;
    private String reference;
    private float prix;

    private Categorie categorie;

    public Product() {
    }

    public Product(String name, String reference, float prix, Categorie categorie) {
        this.name = name;
        this.reference = reference;
        this.prix = prix;
        this.categorie=categorie;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return "id= " + id +
                ",  name= " + name +
                ",  reference= " + reference +
                ",  prix= " + prix ;
    }
}
