package ma.enset.tp2javafxwithbd.dao;

import ma.enset.tp2javafxwithbd.dao.entites.Categorie;
import ma.enset.tp2javafxwithbd.dao.entites.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategorieImpl implements CategorieDao{
    @Override
    public Categorie find(long id) {
        Categorie c=new Categorie();
        Connection connection=ConnectionDBsingleton.getConnection();
        try {
            PreparedStatement pstm=connection.prepareStatement("select * from CATEGORY where ID=?");
            pstm.setLong(1,id);
            ResultSet rs=pstm.executeQuery();

            if(rs.next()){
                c.setId(rs.getLong("ID"));
                c.setName(rs.getString("NAME"));

            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return c;
    }

    @Override
    public List<Categorie> findAll() {
        //maping objet relationnel
        List<Categorie> categories=new ArrayList<>();
        Connection connection=ConnectionDBsingleton.getConnection();

        try {
            PreparedStatement pstm=connection.prepareStatement("select * from CATEGORY");
            ResultSet rs=pstm.executeQuery();
            while (rs.next()){
                Categorie c=new Categorie();
                c.setId(rs.getLong("ID"));
                c.setName(rs.getString("NAME"));

                categories.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    @Override
    public void save(Categorie o) {
        Connection connection=ConnectionDBsingleton.getConnection();
        try {
            PreparedStatement pstm= connection.prepareStatement("insert into CATEGORY(NAME) " +
                    "values(?)");
            pstm.setString(1,o.getName());

            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Categorie o) {
        Connection connection=ConnectionDBsingleton.getConnection();
        try {
            PreparedStatement pstm= connection.prepareStatement("delete  from CATEGORY where ID=?");
            pstm.setLong(1,o.getId());
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Categorie o) {
        Connection connection=ConnectionDBsingleton.getConnection();
        try {
            PreparedStatement pstm= connection.prepareStatement("update  CATEGORY set Name=? where ID=?");
            pstm.setString(1,o.getName());

            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
