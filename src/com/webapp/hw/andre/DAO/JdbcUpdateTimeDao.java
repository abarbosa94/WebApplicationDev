package com.webapp.hw.andre.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.webapp.hw.andre.con.ConnectionFactory;
import com.webapp.hw.andre.model.UpdateTime;

public class JdbcUpdateTimeDao {

    private final Connection connection;
    public JdbcUpdateTimeDao() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private UpdateTime populateUpdateTime(ResultSet rs) throws SQLException {
        UpdateTime update= new UpdateTime();
        update.setSemester(rs.getString("semester"));
        update.setTime(rs.getString("update_time"));

        return update;

    }

    public String findLastUpdate(String semestercourse) {
        try {
            UpdateTime update = new UpdateTime();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from update_time_sr where semester = ?");

            stmt.setString(1, semestercourse);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                update = populateUpdateTime(rs);
            }

            rs.close();
            stmt.close();

            return update.getTime();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        JdbcUpdateTimeDao time = new JdbcUpdateTimeDao();
        String last = time.findLastUpdate("fall");
        System.out.println(last);

    }



    }

