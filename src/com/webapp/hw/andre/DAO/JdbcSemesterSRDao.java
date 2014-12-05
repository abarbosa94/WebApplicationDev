package com.webapp.hw.andre.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.webapp.hw.andre.con.ConnectionFactory;
import com.webapp.hw.andre.model.SemesterSR;


@Repository
public class JdbcSemesterSRDao {
    private final Connection connection;
    public JdbcSemesterSRDao() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private SemesterSR populateSemesterSR(ResultSet rs) throws SQLException {
        SemesterSR semester = new SemesterSR();

        // populate object semester
        semester.setSemesterID(rs.getLong("semester_id"));
        //start date
        Date date = rs.getDate("start_date");
        if (date != null) {
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(date);
            semester.setStartDate(startDate);
        }
      //end date
       Date finaldate = rs.getDate("end_date");
        if (finaldate != null) {
            Calendar endDate = Calendar.getInstance();
            endDate.setTime(finaldate);
            semester.setEndDate(endDate);
        }
        semester.setSemesterName(rs.getString("semester_name"));
        semester.setSemester(rs.getString("semester"));
        return semester;
    }

    public SemesterSR searchbyID(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id dont exist.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from semester_sr where id = ?");
            stmt.setLong(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return populateSemesterSR(rs);
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public SemesterSR searchbyName(String name) {

        if (name == null) {
            throw new IllegalStateException("Name dont exist.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from semester_sr where semester_name = ?");
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return populateSemesterSR(rs);
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<SemesterSR> list() {
        try {
            List<SemesterSR> tarefas = new ArrayList<SemesterSR>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from semester_sr");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a tarefa na lista
                tarefas.add(populateSemesterSR(rs));
            }

            rs.close();
            stmt.close();

            return tarefas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        JdbcSemesterSRDao teste = new JdbcSemesterSRDao();
        List<SemesterSR> all = teste.list();
        for(SemesterSR element: all) {
            System.out.println("id: "+element.getSemesterID());
            System.out.println("start: "+element.getStartDate().getTime());
            System.out.println("end: "+element.getEndDate().getTime());
            System.out.println("name: "+element.getSemesterName());
            System.out.println("semester: "+element.getSemester());
        }
    }




}
