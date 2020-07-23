/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.dao.SuperheroSightingDao;
import com.sg.superherosightingsspringmvc.dto.Sighting;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tjsut
 */

@Controller
public class SuperheroController {
    
    SuperheroSightingDao dao;
    
    @Inject
    public SuperheroController(SuperheroSightingDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String loadHomePage(Model model) {
        List<Sighting> sightingList = dao.getLastTenSightings();
        for(Sighting s : sightingList) {
            s.setHero(dao.findHeroForSighting(s));
        }
        model.addAttribute("sightingList", sightingList);
        return "index"; 
    }
    
}
