/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.dao.SuperheroSightingDao;
import com.sg.superherosightingsspringmvc.dto.Hero;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author 13044
 */
@Controller
public class HeroController {
    
    SuperheroSightingDao dao;
    
    @Inject
    public HeroController(SuperheroSightingDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displayHeroesPage", method = RequestMethod.GET)
    public String loadHeroPage(Model model) {
        List<Hero> heroList = dao.getAllHeroes();
        model.addAttribute("heroList", heroList);
        return "heroes";
    }
    
    @RequestMapping(value = "/createHero", method = RequestMethod.POST)
    public String addHero(HttpServletRequest request) {
        Hero h = new Hero();
        h.setName(request.getParameter("name"));
        h.setDescription(request.getParameter("description"));
        h.setPowers(request.getParameter("powers"));
        
        dao.addHero(h);
        
        return "redirect:displayHeroesPage";
    }
    
    @RequestMapping(value = "/deleteHero", method = RequestMethod.GET)
    public String deleteHero(HttpServletRequest request) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        dao.deleteHero(heroId);
        return "redirect:displayHeroesPage";
    }
    
    @RequestMapping(value = "/displayEditHeroForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);
        Hero hero = dao.getHeroById(heroId);
        model.addAttribute("hero", hero);
        return "editHeroForm";
    }
    
    @RequestMapping(value = "/editHero", method = RequestMethod.POST)
    public String editHero(@Valid @ModelAttribute("hero") Hero hero, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editHeroForm";
        }
        
        dao.updateHero(hero);

        return "redirect:displayHeroesPage";
    }
    
    @RequestMapping(value = "/displayHeroDetails", method = RequestMethod.GET)
    public String displayHeroDetails(HttpServletRequest request, Model model) {
        String heroIdParameter = request.getParameter("heroId");
        int heroId = Integer.parseInt(heroIdParameter);

        Hero hero = dao.getHeroById(heroId);
        hero.setOrganizations(dao.findOrganizationsForHero(hero));
        
        model.addAttribute("hero", hero);
        return "heroDetails";
    }
    
}
