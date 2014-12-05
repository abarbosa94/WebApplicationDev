package com.webapp.hw.andre.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.webapp.hw.andre.con.ConnectionFactory;
import com.webapp.hw.andre.model.Admin;


@Repository
public class JdbcAdminDao {

    private final Connection connection;

    public JdbcAdminDao() {
        try{
            this.connection = new ConnectionFactory().getConnection();
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }

      }

    public List<Admin> getUserPass() {
        try {

            List<Admin> usernames = new ArrayList<Admin>();
            PreparedStatement stmt = this.connection.prepareStatement(
                    "SELECT dept, password, user_name FROM admin_user_sr");
            ResultSet rs =  stmt.executeQuery();
            while(rs.next()) {

                usernames.add(populateAdmin(rs));
            }
            rs.close();
            stmt.close();
            return usernames;

        }
        catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Admin populateAdmin(ResultSet rs) throws SQLException {
        Admin admin = new Admin();

        // populate Admin object
        admin.setUser(rs.getString("user_name"));
        admin.setPass(rs.getString("password"));
        admin.setDept(rs.getInt("dept"));

        return admin;
    }

    public static void main(String[] args) {
        JdbcAdminDao teste = new JdbcAdminDao();
        List<Admin> contatos = teste.getUserPass();
        for(Admin contato: contatos) {
            System.out.println("Username: "+contato.getUser());
            System.out.println("Password: "+contato.getPass());
            System.out.println("Departament: "+contato.getDept());
        }
    }

}

