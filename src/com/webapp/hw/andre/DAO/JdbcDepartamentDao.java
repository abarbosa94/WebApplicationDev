package com.webapp.hw.andre.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.webapp.hw.andre.con.ConnectionFactory;
import com.webapp.hw.andre.model.Dept;

@Repository
public class JdbcDepartamentDao {
    private final Connection connection;
    public JdbcDepartamentDao() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Dept populateDept(ResultSet rs) throws SQLException {
        Dept dept = new Dept();

        // populate object dept
        dept.setDeptID(rs.getLong("dept_id"));
        dept.setDeptName(rs.getString("dept_name"));
        return dept;
    }
    //this one changed most
    public List<Dept> list() {
        try {
            List<Dept> dept = new ArrayList<Dept>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from dept_sr"); //here

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a tarefa na lista
                dept.add(populateDept(rs));
            }

            rs.close();
            stmt.close();

            return dept;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Long FindIdByName(String deptname) {
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from dept_sr where dept_name = ?"); //here where dept_id = ? stmt.setLong(1, id);
            stmt.setString(1, deptname);
            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                return populateDept(rs).getDeptID();
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String FindDeptById(Long dept_id) {
        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from dept_sr where dept_id = ?"); //here where dept_id = ? stmt.setLong(1, id);
            stmt.setLong(1, dept_id);
            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                return populateDept(rs).getDeptName();
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        JdbcDepartamentDao teste = new JdbcDepartamentDao();
        List<Dept> all = teste.list();
        for(Dept element: all) {
            System.out.println("id: "+element.getDeptID());
            System.out.println("name: "+element.getDeptName());
        }
    }
}
