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
import com.webapp.hw.andre.model.CourseSearch;

@Repository
public class JdbcCourseSearchDao {
    private final Connection connection;
    public JdbcCourseSearchDao() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private CourseSearch populateCourseSearch(ResultSet rs) throws SQLException {
        CourseSearch course = new CourseSearch();

        // populate object CourseSearch
        course.setCrsId(rs.getLong("crs_index"));
        course.setDeg(rs.getString("d_e_g"));
        course.setDiscipline(rs.getString("disc"));
        course.setCourseNum(rs.getString("crs_num"));
        course.setCourseSec(rs.getString("crs_sec"));
        course.setCouseCode(rs.getString("crs_cd"));
        course.setMeetingDays(rs.getString("meeting_days"));
        course.setStartTime(rs.getString("start_time"));
        course.setStopTime(rs.getString("stop_time"));
        course.setAmpm(rs.getString("am_pm"));
        course.setSeats(rs.getLong("seats_avail"));
        course.setBuilding(rs.getString("building"));
        course.setRoom(rs.getString("rm"));
        Date date = rs.getDate("start_date");
        if (date != null) {
            Calendar startDate = Calendar.getInstance();
            startDate.setTime(date);
            course.setStartDate(startDate);
        }
      //end date
       Date finaldate = rs.getDate("end_date");
        if (finaldate != null) {
            Calendar endDate = Calendar.getInstance();
            endDate.setTime(finaldate);
            course.setEndDate(endDate);
        }
        course.setName(rs.getString("instructor_lname"));
        course.setSemester(rs.getString("semester"));
        return course;
    }




    public List<CourseSearch> list() {
        try {
            List<CourseSearch> tarefas = new ArrayList<CourseSearch>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from crs_sec_sr");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a tarefa na lista
                tarefas.add(populateCourseSearch(rs));
            }

            rs.close();
            stmt.close();

            return tarefas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<CourseSearch> CoursesSelect(String discipline, String coursenumber) {
        try {
            List<CourseSearch> result = new ArrayList<CourseSearch>();
            String query = "select * from crs_sec_sr where disc = ? and crs_num = ?";
            PreparedStatement stmt = this.connection
                    .prepareStatement(query);
            stmt.setString(1, discipline);
            stmt.setString(2, coursenumber);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                result.add(populateCourseSearch(rs));
            }
            rs.close();
            stmt.close();
            return result;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<CourseSearch> findCourse(String semester, String disc, String coursenum,
            String meeting, String instructor, String time, String ampm, String before_after, int value, String department ) {
        String temp = "0";
        if (disc == null) {
            throw new IllegalStateException("Id dont exist.");
        }
        int count = 0;
        try {
            List<CourseSearch> result = new ArrayList<CourseSearch>();
            String query = "select * from crs_sec_sr where semester = ?";
            if(!department.equals("00")) {
                if(!disc.equals("0")) query += " and disc = ?";
                else {
                    query += "and disc = NULL";
                }
            }
            if(!coursenum.equals("")) query += " and crs_num = ?";
            if(!meeting.equals("")) query += " and meeting_days = ?";
            if(!instructor.equals("")) query += " and lower(instructor_lname) = ?";

            if(!time.equals("")) {
                if(before_after.equals("after")){
                    query += " and start_time > ?";
                    query += " and am_pm = ?";
                }

                if(before_after.equals("before")) {
                    query += " and start_time < ?";
                    query += " and am_pm = ?";
                }

                if(before_after.equals("around")) {
                    time = Integer.toString(value);
                    int temporary = value +1;
                    temp += time;
                    time = temp;
                    time +=":00";
                    if(temporary>=10)  temp = Integer.toString(temporary);
                    else {
                        temp = "0";
                        temp += Integer.toString(temporary);
                    }
                    temp +=":00";
                    query += " and start_time > ?";
                    query += " and start_time < ?";
                    query += " and am_pm = ?";
                }


            }
            PreparedStatement stmt = this.connection
                    .prepareStatement(query);
            //There semester parameter always exist
            stmt.setString(++count, semester);
            if(!department.equals("00")) {
                if(!disc.equals("0")) stmt.setString(++count, disc);
            }
            if(!coursenum.equals(""))stmt.setString(++count, coursenum);
            if(!meeting.equals(""))stmt.setString(++count, meeting);
            if(!instructor.equals("")) stmt.setString(++count, instructor.toLowerCase());
            if(!time.equals("")) {
                stmt.setString(++count, time);
                if(before_after.equals("around"))stmt.setString(++count, temp);
                stmt.setString(++count, ampm);
            }
            count = 0;

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {
                CourseSearch found = populateCourseSearch(rs);
                if(result.size()!=0) {
                    CourseSearch current = result.get(result.size()-1);
                    if(current.getCourseCode().equals(found.getCourseCode()) && current.getSemester().equals(found.getSemester())) {

                        current.setMeetingDays(current.getMeetingDays()+(", ")+found.getMeetingDays());
                        current.setStartTime(current.getStartTime()+(", ")+found.getStartTime());
                        current.setStopTime(current.getStopTime()+(", ")+found.getStopTime());
                        current.setBuilding(current.getBuilding()+(", ")+found.getBuilding());
                        current.setRoom(current.getRoom()+(", ")+found.getRoom());
                        current.setAmpm(current.getAmpm()+(", ")+found.getAmpm());
                        current.setName(current.getName()+(", ")+found.getName());
                    }
                    else {
                        result.add(found);
                    }
                }
                //this one is just for the first time
                else {
                    result.add(found);
                }


            }
            rs.close();
            stmt.close();

            if(!time.equals("")) {
                if(ampm.equals("AM") && before_after.equals("after")) {
                    ampm = "PM";
                    query = "select * from crs_sec_sr where semester = ?";
                    if(!department.equals("00")) {
                        if(!disc.equals("0")) query += " and disc = ?";
                        else {
                            query += "and disc = NULL";
                        }
                    }
                    if(!coursenum.equals("")) query += " and crs_num = ?";
                    if(!meeting.equals("")) query += " and meeting_days = ?";
                    if(!instructor.equals("")) query += " and instructor_lname = ?";
                    if(!time.equals("")) {
                            query += " and am_pm = ?";
                    }
                    count = 0;
                    stmt = this.connection
                            .prepareStatement(query);
                    //There semester parameter always exist
                    stmt.setString(++count, semester);
                    if(!department.equals("00")) {
                        if(!disc.equals("0")) stmt.setString(++count, disc);
                    }
                    if(!coursenum.equals(""))stmt.setString(++count, coursenum);
                    if(!meeting.equals(""))stmt.setString(++count, meeting);
                    if(!instructor.equals("")) stmt.setString(++count, instructor);
                    if(!time.equals("")) {
                         stmt.setString(++count, ampm);
                    }
                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        result.add(populateCourseSearch(rs));
                    }
                    rs.close();
                    stmt.close();

                    }
                if(ampm.equals("PM") && before_after.equals("before")) {
                    ampm = "AM";
                    query = "select * from crs_sec_sr where semester = ?";
                    if(!department.equals("00")) {
                        if(!disc.equals("0")) query += " and disc = ?";
                        else {
                            query += "and disc = NULL";
                        }
                    }
                    if(!coursenum.equals("")) query += " and crs_num = ?";
                    if(!meeting.equals("")) query += " and meeting_days = ?";
                    if(!instructor.equals("")) query += " and instructor_lname = ?";
                    if(!time.equals("")) {
                            query += " and am_pm = ?";
                    }
                    count = 0;
                    stmt = this.connection
                            .prepareStatement(query);
                    //There semester parameter always exist
                    stmt.setString(++count, semester);
                    if(!department.equals("00")) {
                        if(!disc.equals("0")) stmt.setString(++count, disc);
                    }
                    if(!coursenum.equals(""))stmt.setString(++count, coursenum);
                    if(!meeting.equals(""))stmt.setString(++count, meeting);
                    if(!instructor.equals("")) stmt.setString(++count, instructor);
                    if(!time.equals("")) {
                         stmt.setString(++count, ampm);
                    }
                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        result.add(populateCourseSearch(rs));
                    }
                    rs.close();
                    stmt.close();
                    }
            }
            connection.close();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String CourseMeeting(List<CourseSearch> meeting, String semester, String coursecode) {
        String meetingdays = null;
        for(CourseSearch element: meeting) {
            if(element.getCourseCode().equals(coursecode) && element.getSemester().equals(semester)) {
                meetingdays += element.getMeetingDays() + "\n";
            }
        }
        return meetingdays;
    }


    public static void main(String[] args) {
       JdbcCourseSearchDao teste = new JdbcCourseSearchDao();
       List<CourseSearch> all = teste.findCourse("fall","BIO","1003","","","", "", "",0,"00"); //is case sensitive
      for(CourseSearch element: all) {
            System.out.println("id: "+element.getCrsId());
            System.out.println("d_e_g: "+element.getDeg());
            System.out.println("course_num: "+element.getCourseNum());
            System.out.println("discipline: "+element.getDiscipline());
            System.out.println("section: "+element.getCourseSec());
            System.out.println("code: "+element.getCourseCode());
            System.out.println("meeting: "+element.getMeetingDays());
            System.out.println("start time: "+element.getStartTime());
            System.out.println("end time: "+element.getStopTime());
            System.out.println("AM_PM: "+element.getAmpm());
            System.out.println("seats: "+element.getSeats());
            System.out.println("building: "+element.getBuilding());
            System.out.println("room: "+element.getRoom());
            System.out.println("Start date "+element.getStartDate().getTime());
            System.out.println("End date "+element.getEndDate().getTime());
            System.out.println("instructor "+element.getName());
            System.out.println("semester "+element.getSemester());

       }
    }


}
