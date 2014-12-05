package com.webapp.hw.andre.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webapp.hw.andre.DAO.JdbcCommentsDao;
import com.webapp.hw.andre.DAO.JdbcCourseDao;
import com.webapp.hw.andre.DAO.JdbcCourseSearchDao;
import com.webapp.hw.andre.DAO.JdbcDepartamentDao;
import com.webapp.hw.andre.DAO.JdbcDisciplineDao;
import com.webapp.hw.andre.DAO.JdbcSemesterSRDao;
import com.webapp.hw.andre.DAO.JdbcUpdateTimeDao;
import com.webapp.hw.andre.model.CourseSearch;

@Controller
public class ResultController {
    @RequestMapping("findResult")
    public String listResult(Model model, @ModelAttribute("sem") String semesterName, @ModelAttribute("discipline")String discipline,
            @ModelAttribute("number")String coursenum,@ModelAttribute("week") String meeting,
            @ModelAttribute("prof")String instructor,
            @ModelAttribute("time")String time, String ampm,
            @ModelAttribute("time_a_b") String before_after,
            @ModelAttribute("department") String department,
            @ModelAttribute("div_undr") String checkunder,
            @ModelAttribute("div_grad") String checkgrad) {
      if(discipline.equals("0")&&coursenum.equals("")&&instructor.equals("")&&
              time.equals("")&&meeting.equals("")) {
          model.addAttribute("error", "You must select a semester and either a discipine, course number, day, time, and/or instructor.");
          return "redirect:searchCourse";
      }
      JdbcSemesterSRDao sem = new JdbcSemesterSRDao();
      JdbcCourseSearchDao dao = new JdbcCourseSearchDao();
      JdbcDisciplineDao disc = new JdbcDisciplineDao();
      JdbcDepartamentDao dept = new JdbcDepartamentDao();
      JdbcCommentsDao commentdao = new JdbcCommentsDao();
      int value = 0;
      if(!time.equals("")) {
          value = Integer.parseInt(time);
          if(value > 11) {
              ampm = "PM";
              value = value - 12;
              time = "0";
              time += Integer.toString(value);
              time +=":00";
          }
          else{
              String temp = "0";
              temp += time;
              time = temp;
              time +=":00";
              ampm = "AM";
          }
      }
      String semester = sem.searchbyName(semesterName).getSemester();
      Long dept_id = Long.parseLong(department);
      String depto= dept.FindDeptById(dept_id);
      List<CourseSearch> all = dao.findCourse(semester, discipline, coursenum, meeting, instructor,time, ampm, before_after, value, department) ;
      model.addAttribute("semester", semesterName);
      if(!discipline.equals("0")) model.addAttribute("discipline", discipline);
      else {
          model.addAttribute("discipline", "None");
      }
      if(!instructor.equals("")) model.addAttribute("instructor", instructor);
      else {
          model.addAttribute("instructor", "None entered");
      }
      if(!time.equals("")) model.addAttribute("time", before_after +" " + time+ " " + ampm);
      else {
          model.addAttribute("time", "None");
      }

      if(!instructor.equals("")) model.addAttribute("coursenum", coursenum);
      else {
          model.addAttribute("coursenum", "None entered");
      }

      if(!meeting.equals("")) {
          model.addAttribute("days", meeting);
      }
      else {
          model.addAttribute("days", "None entered");
      }

      if(!department.equals("00")) {
          model.addAttribute("dept", depto);
      }
      else {
          model.addAttribute("dept", "None entered");
      }
      commentdao.commentsFound(all);

      JdbcCourseDao remove =  new JdbcCourseDao();
      if(!checkunder.equals("")&&!checkgrad.equals("")) {
          all = remove.removeDivision("", all);
          model.addAttribute("division", "Undergraduate and Graduate");
          model.addAttribute("results", all);
      }
      if(checkunder.equals("")&&!checkgrad.equals("")) {
          all = remove.removeDivision("U", all);
          model.addAttribute("division", "Graduate");
          model.addAttribute("results", all);
      }
      if((!checkunder.equals("")&&checkgrad.equals(""))) {
          all = remove.removeDivision("G", all);
          model.addAttribute("division", "Undergraduate");
          model.addAttribute("results", all);
      }
      JdbcUpdateTimeDao lastupdate = new JdbcUpdateTimeDao();
      String last = lastupdate.findLastUpdate(semester);
      model.addAttribute("lastupdate", last);

      return "search/scheduleresults";
    }
}
