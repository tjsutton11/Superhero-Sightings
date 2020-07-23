/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.dao.SuperheroSightingDao;
import com.sg.superherosightingsspringmvc.dto.Location;
import java.math.BigDecimal;
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
public class LocationController {
    
    SuperheroSightingDao dao;

    @Inject
    public LocationController(SuperheroSightingDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = "/displayLocationsPage", method = RequestMethod.GET)
    public String loadLocationsPage(Model model) {
        List<Location> locationList = dao.getAllLocations();
        model.addAttribute("locationList", locationList);
        return "locations";
    }
    
    @RequestMapping(value = "/createLocation", method=RequestMethod.POST)
    public String addLocation(HttpServletRequest request) {
        Location l = new Location();
        l.setName(request.getParameter("locationName"));
        l.setDescription(request.getParameter("locationDescription"));
        l.setStreet(request.getParameter("streetName"));
        l.setCity(request.getParameter("cityName"));
        l.setState(request.getParameter("stateName"));
        l.setZip(request.getParameter("zipCode"));
        l.setLatitude(new BigDecimal(request.getParameter("latitude")));
        l.setLongitude(new BigDecimal(request.getParameter("longitude")));
        
        dao.addLocation(l);
        
        return "redirect:displayLocationsPage";
    }
    
    @RequestMapping(value = "/displayEditLocationForm", method = RequestMethod.GET)
    public String displayEditLocationForm(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location location = dao.getLocationById(locationId);
        model.addAttribute("location", location);
        return "editLocationForm";
    }
    
    @RequestMapping(value = "/displayLocationDetails", method = RequestMethod.GET)
    public String displayLocationDetails(HttpServletRequest request, Model model) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        
        Location location = dao.getLocationById(locationId);
        model.addAttribute("location", location);
        return "locationDetails";
    }
    
    @RequestMapping(value = "/editLocation", method = RequestMethod.POST)
    public String editLocation(@Valid @ModelAttribute("location") Location location, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editHeroForm";
        }
        
        dao.updateLocation(location);
        
        return "redirect:displayLocationsPage";
    }
    
    @RequestMapping(value = "/deleteLocation", method = RequestMethod.GET)
    public String deleteLocation(HttpServletRequest request) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        dao.deleteLocation(locationId);
        return "redirect:displayLocationsPage";
    }
    
}
