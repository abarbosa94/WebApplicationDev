package com.webapp.hw.andre.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.webapp.hw.andre.con.ConnectionFactory;
import com.webapp.hw.andre.model.Course;
import com.webapp.hw.andre.model.CourseSearch;

@Repository
public class JdbcCourseDao {
    private final Connection connection;
    public JdbcCourseDao() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Course populateCourse(ResultSet rs) throws SQLException {
        Course course = new Course();

        // populate object course
        course.setLevel(rs.getString("level_div"));
        course.setCourseNumber(rs.getString("coursenumber"));
        course.setCredit(rs.getString("credithour"));
        course.setDescription(rs.getString("description"));
        course.setDiscipline(rs.getString("discipline"));
        course.setPrereq(rs.getString("prereq"));
        course.setTitle(rs.getString("title"));

        return course;

    }

    public List<Course> list() {
        try {
            List<Course> course = new ArrayList<Course>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from course_sr"); //here

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a tarefa na lista
                course.add(populateCourse(rs));
            }

            rs.close();
            stmt.close();

            return course;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<CourseSearch> removeDivision(String division, List<CourseSearch> all) { //remove the elements with determined division
        division = division.toLowerCase();
        for (Iterator<CourseSearch> iterator = all.iterator(); iterator.hasNext();) {
            CourseSearch element = iterator.next();
            Course course = findCourses(element.getDiscipline(), element.getCourseNum());
            if(course==null) iterator.remove(); //bug correction
            if(course!=null) {
                if (division.equals(course.getLevel())) {
                    // Remove the current element from the iterator and the list.
                    iterator.remove();
                }
            }
        }

        return all;
    }

    public Course findCourses(String discipline, String coursenum ) {
        try {
            Course course = new Course();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from course_sr where discipline = ? and coursenumber = ?"); //here
            stmt.setString(1, discipline);
            stmt.setString(2, coursenum);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // adiciona a tarefa na lista
                course = populateCourse(rs);
                rs.close();
                stmt.close();
                return course;
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        JdbcCourseDao teste = new JdbcCourseDao();
        Course element = teste.findCourses("CIS","2001");

        /*for(Course element: all) {
            System.out.println("level: "+element.getLevel());
            System.out.println("discipline: "+element.getDiscipline());
            System.out.println("course number: "+element.getCourseNumber());
            System.out.println("title: "+element.getTitle());
            System.out.println("credit hour: "+element.getCredit());
            System.out.println("description: "+element.getDescription());
            System.out.println("prereq: "+element.getPrereq());
       }*/
    }
}
