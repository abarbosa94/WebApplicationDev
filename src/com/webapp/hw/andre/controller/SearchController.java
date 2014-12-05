/*************************************
*
* Student Name: Andre Barbosa
* Project
* Class: CIS 4160
* Technologies used: Spring MVC, Taglib, AJAX
* The main page is located at : http://localhost:8080/Barbosa_Andre/searchCourse
*
*
*
************************************/
package com.webapp.hw.andre.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.webapp.hw.andre.DAO.JdbcDepartamentDao;
import com.webapp.hw.andre.DAO.JdbcDisciplineDao;
import com.webapp.hw.andre.DAO.JdbcSemesterSRDao;
import com.webapp.hw.andre.model.Discipline;





@Controller
public class SearchController {

    @RequestMapping("searchCourse")
    public String list(Model model, @ModelAttribute("error") String error) {
      if(!error.equals(""))  model.addAttribute("errormsg", error);
      JdbcDepartamentDao daod = new JdbcDepartamentDao();
      JdbcSemesterSRDao daos = new JdbcSemesterSRDao();
      model.addAttribute("semester", daos.list());
      model.addAttribute("dept", daod.list());
      return "search/schedulesearch";
    }

    //ajax
  @RequestMapping("findDiscipline")
    public void listDisc(Long id, HttpServletResponse response) throws IOException {
        String json = null;
        JdbcDisciplineDao dao = new JdbcDisciplineDao();
        if((dao.list(id).size()!=0))json = new Gson().toJson(dao.list(id));
        else {
            List<Discipline> disc = new ArrayList<Discipline>();
            json = new Gson().toJson(disc);
        }
        response.setContentType("application/json");
        response.getWriter().write(json);
        response.setStatus(200);
      }

}
