/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.dao.SuperheroSightingDao;
import com.sg.superherosightingsspringmvc.dto.Hero;
import com.sg.superherosightingsspringmvc.dto.Location;
import com.sg.superherosightingsspringmvc.dto.Sighting;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author 13044
 */
@Controller
public class SightingController {
    
    SuperheroSightingDao dao;

    @Inject
    public SightingController(SuperheroSightingDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displaySightingsPage", method = RequestMethod.GET)
    public String loadSightingsPage(Model model) {
        List<Sighting> sightingList = dao.getAllSightings();
        for(Sighting s : sightingList) {
            s.setHero(dao.findHeroForSighting(s));
        }
        List<Hero> heroList = dao.getAllHeroes();
        List<Location> locationList = dao.getAllLocations();
        model.addAttribute("sightingList", sightingList);
        model.addAttribute("heroList", heroList);
        model.addAttribute("locationList", locationList);
        return "sightings";
    }
    
    @RequestMapping(value = "/createSighting", method = RequestMethod.POST)
    public String addSighting(HttpServletRequest request) {
        Sighting s = new Sighting();
        Hero h = dao.getHeroById(Integer.parseInt(request.getParameter("heroId")));
        s.setHero(h);
        Location l = dao.getLocationById(Integer.parseInt(request.getParameter("locationId")));
        s.setLocation(l);
        s.setDate(LocalDate.parse(request.getParameter("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        s.setTime(LocalTime.parse(request.getParameter("time"), DateTimeFormatter.ofPattern("HH:mm")));
        dao.addSighting(s);
        dao.insertHeroSighting(h, s);

        return "redirect:displaySightingsPage";
    }
    
    @RequestMapping(value = "/deleteSighting", method = RequestMethod.GET)
    public String deleteSighting(HttpServletRequest request) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        dao.deleteSighting(sightingId);
        return "redirect:displaySightingsPage";
    }
    
    @RequestMapping(value = "/displayEditSightingForm", method = RequestMethod.GET)
    public String displayEditSightingForm(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = dao.getSightingById(sightingId);
        sighting.setHero(dao.findHeroForSighting(sighting));
        List<Hero> heroList = dao.getAllHeroes();
        List<Location> locationList = dao.getAllLocations();
        model.addAttribute("sighting", sighting);
        model.addAttribute("heroList", heroList);
        model.addAttribute("locationList", locationList);
        return "editSightingForm";
    }
    
    @RequestMapping(value = "/editSighting", method = RequestMethod.POST)
    public String editSighting(HttpServletRequest request, @ModelAttribute("sighting") Sighting sighting) {
        sighting.setLocation(dao.getLocationById(Integer.parseInt(request.getParameter("location.locationId"))));
        sighting.setHero(dao.getHeroById(Integer.parseInt(request.getParameter("heroId"))));
        dao.updateSighting(sighting);

        return "redirect:displaySightingsPage";
    }
    
    @RequestMapping(value = "/displaySightingDetails", method = RequestMethod.GET)
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = dao.getSightingById(sightingId);
        sighting.setLocation(dao.findLocationOfSighting(sighting));
        sighting.setHero(dao.findHeroForSighting(sighting));
        model.addAttribute("sighting", sighting);
        return "sightingDetails";
    }
    
}
