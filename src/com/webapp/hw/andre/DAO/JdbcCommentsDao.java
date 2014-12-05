package com.webapp.hw.andre.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.webapp.hw.andre.con.ConnectionFactory;
import com.webapp.hw.andre.model.Comments;
import com.webapp.hw.andre.model.CourseSearch;

@Repository
public class JdbcCommentsDao {
    private final Connection connection;
    public JdbcCommentsDao() {
        try {
            this.connection = new ConnectionFactory().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Comments populateComments(ResultSet rs) throws SQLException {
        Comments comment = new Comments();

        // populate object comment
        comment.setCode(rs.getString("crs_cd"));
        comment.setComment1(rs.getString("crs_comments1"));
        comment.setComment2(rs.getString("crs_comments2"));
        comment.setComment3(rs.getString("crs_comments3"));
        comment.setComment4(rs.getString("crs_comments4"));
        comment.setComment5(rs.getString("crs_comments5"));
        comment.setSemester(rs.getString("semester"));


        return comment;

    }

    public Comments findComment(Long courseCode, String semester) {
        try {
            String query = "select * from crs_comments_sr where crs_cd = ? and semester = ?";
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.setLong(1, courseCode);
            stmt.setString(2, semester);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                Comments find = populateComments(rs);
                return find;
            }
            System.out.println("aqui");
            rs.close();
            stmt.close();
            return null;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void commentsFound(List<CourseSearch> all) {
        //List<Comments> value = new ArrayList<Comments>();
        for(CourseSearch element: all){
            try {
                String query = "select * from crs_comments_sr where crs_cd = ? and semester = ?";
                PreparedStatement stmt = this.connection.prepareStatement(query);
                stmt.setString(1, element.getCourseCode());
                stmt.setString(2, element.getSemester());
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    Comments find = populateComments(rs);
                    if(find.getComment1()!=null) element.setComments(find.getComment1()+"\n");
                    if(find.getComment2()!=null) element.setComments(find.getComment2()+"\n");
                    if(find.getComment3()!=null) element.setComments(find.getComment3()+"\n");
                    if(find.getComment4()!=null) element.setComments(find.getComment4()+"\n");
                    if(find.getComment5()!=null) element.setComments(find.getComment5()+"\n");
                    //value.add(find);

                }
                rs.close();
                stmt.close();

            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
       // return value;

    }

    public static void main(String[] args) {
        JdbcCommentsDao teste = new JdbcCommentsDao();
        JdbcCourseSearchDao basinga = new JdbcCourseSearchDao();
        List<CourseSearch> all =  basinga.findCourse("fall","0","","","","", "", "",0,"00");
        teste.commentsFound(all);
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
            System.out.println("Start date "+element.getStartDate().getTime());
            System.out.println("End date "+element.getEndDate().getTime());
            System.out.println("instructor "+element.getName());
            System.out.println("semester "+element.getSemester());
            System.out.println("comments: "+ element.getComments());

       }
        /*for(Comments element: end) {
            System.out.println("code: "+element.getCode());
            System.out.println("semester: "+element.getSemester());
            System.out.println("comment 1: "+element.getComment1());
            System.out.println("comment 2: "+element.getComment2());
            System.out.println("comment 3: "+element.getComment3());
            System.out.println("comment 4: "+element.getComment4());
            System.out.println("comment 5: "+element.getComment5());
       }*/
    }

}
