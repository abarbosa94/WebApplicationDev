package com.webapp.hw.andre.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webapp.hw.andre.DAO.JdbcCourseDao;
import com.webapp.hw.andre.DAO.JdbcDepartamentDao;
import com.webapp.hw.andre.DAO.JdbcDisciplineDao;
import com.webapp.hw.andre.model.Course;
import com.webapp.hw.andre.model.Discipline;

@Controller
public class DetailsController {
    @RequestMapping("courseDetails")
    public String details(Model model, @ModelAttribute("semester") String semester,
            @ModelAttribute("subject") String course, @ModelAttribute("number") String number,
            @ModelAttribute("code") Long code, @ModelAttribute("section") String section,
            @ModelAttribute("begin") String startDate, @ModelAttribute("end") String endDate,
            @ModelAttribute("meeting") String meeting, @ModelAttribute("start") String startTime,
            @ModelAttribute("stop") String stopTime, @ModelAttribute("building") String building,
            @ModelAttribute("room") String room, @ModelAttribute("instructor") String instructor,
            @ModelAttribute("seats") Long seats, @ModelAttribute("dep") String dept, @ModelAttribute("comments") String comments, @ModelAttribute("ampm") String when){
        JdbcCourseDao dao = new JdbcCourseDao();
        //precisa adicionar  os meetings aqui
        if(dept.equals("None entered")) {
            JdbcDisciplineDao disc = new JdbcDisciplineDao();
            Discipline noun = disc.FindDisciplineByName(course);
            JdbcDepartamentDao dep = new JdbcDepartamentDao();
            dept = dep.FindDeptById(noun.getDeptID());
        }

        Course found  = dao.findCourses(course, number);

        model.addAttribute("course", found);
        if(found.getLevel().equals("u")) {
            model.addAttribute("division", "Undergraduate");
        }
        else if(found.getLevel().equals("g")) {
            model.addAttribute("division", "Graduate");
        }
        model.addAttribute("semester", semester);
        model.addAttribute("code", code);
        model.addAttribute("section", section);
        model.addAttribute("department", dept);
        model.addAttribute("begin", startDate);
        model.addAttribute("end", endDate);
        model.addAttribute("start", startTime);
        model.addAttribute("stop", stopTime);
        model.addAttribute("seat", seats);
        model.addAttribute("meeting", meeting);
        model.addAttribute("building", building);
        model.addAttribute("room", room);
        model.addAttribute("instructor", instructor);
        model.addAttribute("comments", comments);
        model.addAttribute("ampm", when);



        return "details/coursedetails";
    }
}
