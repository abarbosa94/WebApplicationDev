package com.webapp.hw.andre.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.webapp.hw.andre.con.ConnectionFactory;
import com.webapp.hw.andre.model.Discipline;
@Repository
public class JdbcDisciplineDao {
    private final Connection connection;
    public JdbcDisciplineDao() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Discipline populateDiscipline(ResultSet rs) throws SQLException {
        Discipline disc = new Discipline();

        // populate object disc
        disc.setDeptID(rs.getLong("dept_id"));
        disc.setDiscAbreviation(rs.getString("disc_abbreviation"));
        disc.setDisciplineName(rs.getString("discipline_name"));
        disc.setSchoolID(rs.getLong("school_ID"));
        return disc;
    }

    public List<Discipline> list(Long id) {


        try {
            List<Discipline> disc = new ArrayList<Discipline>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from discipline_sr where dept_id = ?"); //here where dept_id = ? stmt.setLong(1, id);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                // adiciona a tarefa na lista
                disc.add(populateDiscipline(rs));
            }

            rs.close();
            stmt.close();

            return disc;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Discipline> DisciplineSearchByID(Long id) {
        try {
            List<Discipline> disc = new ArrayList<Discipline>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from discipline_sr where dept_id = ?"); //here where dept_id = ? stmt.setLong(1, id);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                // adiciona a tarefa na lista
                disc.add(populateDiscipline(rs));
            }

            rs.close();
            stmt.close();

            return disc;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Discipline FindDisciplineByName(String name) {
        try {
            List<Discipline> disc = new ArrayList<Discipline>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from discipline_sr where disc_abbreviation = ?"); //here where dept_id = ? stmt.setLong(1, id);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                return populateDiscipline(rs);
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) {
        JdbcDisciplineDao teste = new JdbcDisciplineDao();
        /*List<Discipline> all = teste.list((long)31);
        for(Discipline element: all) {
            System.out.println("id: "+element.getDeptID());
            System.out.println("name: "+element.getDisciplineName());
            System.out.println("abbreviation: "+element.getDiscAbreviation());
            System.out.println("school id: "+element.getSchoolID());
        }*/
        /*Discipline element = teste.DisciplineSearchByID((long)31);
            System.out.println("id: "+element.getDeptID());
            System.out.println("name: "+element.getDisciplineName());
            System.out.println("abbreviation: "+element.getDiscAbreviation());
            System.out.println("school id: "+element.getSchoolID());*/
    }
}
