package ma.enset.tp2javafxwithbd.dao;

import ma.enset.tp2javafxwithbd.dao.entites.Categorie;
import ma.enset.tp2javafxwithbd.dao.entites.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements ProductDao{
    @Override
    public Product find(long id) {
        Product p=new Product();
        Connection connection=ConnectionDBsingleton.getConnection();
        try {
                PreparedStatement pstm=connection.prepareStatement("select * from PRODUCT where ID=?");
                pstm.setLong(1,id);
                ResultSet rs=pstm.executeQuery();

                if(rs.next()){
                    p.setId(rs.getLong("ID"));
                    p.setName(rs.getString("NAME"));
                    p.setReference(rs.getString("REFERENCE"));
                    p.setPrix(rs.getFloat("PRIX"));

                }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return p;
    }

    @Override
    public List<Product> findAll() {
        //maping objet relationnel
        List<Product> products=new ArrayList<>();
        Connection connection=ConnectionDBsingleton.getConnection();

        try {
            PreparedStatement pstm=connection.prepareStatement("select * from PRODUCT");
            ResultSet rs=pstm.executeQuery();
            while (rs.next()){
                Product p=new Product();
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p. setReference(rs.getString("REFERENCE"));
                p.setPrix(rs.getFloat("PRIX"));

                PreparedStatement pstm1=connection.prepareStatement("select * from CATEGORY where ID=?");
                pstm1.setLong(1,rs.getLong("ID_CAT"));
                ResultSet rs1=pstm1.executeQuery();
                Categorie c=new Categorie();
                if (rs1.next())
                    c.setName(rs1.getString("NAME"));
                p.setCategorie(c);
                products.add(p);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public void save(Product o) {
        Connection connection=ConnectionDBsingleton.getConnection();
        try {
            PreparedStatement pstm= connection.prepareStatement("insert into PRODUCT(NAME,REFERENCE,PRIX,ID_CAT) " +
                    "values(?,?,?,?)");
            pstm.setString(1,o.getName());
            pstm.setString(2,o.getReference());
            pstm.setFloat(3,o.getPrix());
            pstm.setLong(4,o.getCategorie().getId());

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Product o) {

        Connection connection=ConnectionDBsingleton.getConnection();
        try {
            PreparedStatement pstm= connection.prepareStatement("delete  from PRODUCT where ID=?");
            pstm.setLong(1,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(Product o) {
        Connection connection=ConnectionDBsingleton.getConnection();
        try {
            PreparedStatement pstm= connection.prepareStatement("update  PRODUCT set Name=?,REFERENCE=?,PRIX=?,ID_CAT=? where ID=?");
            pstm.setString(1,o.getName());
            pstm.setString(2,o.getReference());
            pstm.setFloat(3,o.getPrix());
            pstm.setLong(4,o.getCategorie().getId());
            pstm.setLong(5,o.getId());

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findByQuery(String queruy) {

        List<Product> products=new ArrayList<>();
        Connection connection=ConnectionDBsingleton.getConnection();

        try {
            PreparedStatement pstm=connection.prepareStatement("select * from PRODUCT where NAME like ? or REFERENCE like ? or PRIX like ?");
            pstm.setString(1,"%"+queruy+"%");
            pstm.setString(2,"%"+queruy+"%");
            pstm.setString(3,"%"+queruy+"%");

            ResultSet rs=pstm.executeQuery();
            while (rs.next()){
                Product p=new Product();
                p.setId(rs.getLong("ID"));
                p.setName(rs.getString("NAME"));
                p. setReference(rs.getString("REFERENCE"));
                p.setPrix(rs.getFloat("PRIX"));
                //p.setCategorie(rs.getLong("ID_CAT"));
                products.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }
}
