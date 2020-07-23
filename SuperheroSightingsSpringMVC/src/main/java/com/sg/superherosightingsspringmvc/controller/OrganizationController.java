/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.controller;

import com.sg.superherosightingsspringmvc.dao.SuperheroSightingDao;
import com.sg.superherosightingsspringmvc.dto.Hero;
import com.sg.superherosightingsspringmvc.dto.Location;
import com.sg.superherosightingsspringmvc.dto.Organization;
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
public class OrganizationController {
    
    SuperheroSightingDao dao;

    @Inject
    public OrganizationController(SuperheroSightingDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/displayOrganizationsPage", method = RequestMethod.GET)
    public String loadOrganizationPage(Model model) {
        List<Organization> organizationList = dao.getAllOrganizations();
        for(Organization o : organizationList) {
            Location l = dao.findLocationForOrganization(o);
            o.setLocation(l);
        }
        model.addAttribute("organizationList", organizationList);
        return "organizations";
    }
    
    @RequestMapping(value = "/createOrganization", method = RequestMethod.POST)
    public String addOrganization(HttpServletRequest request) {
        Organization o = new Organization();
        o.setName(request.getParameter("orgName"));
        o.setDescription(request.getParameter("orgDescription"));
        Location l = new Location();
        l.setName(request.getParameter("orgName"));
        l.setDescription(request.getParameter("orgDescription"));
        l.setStreet(request.getParameter("street"));
        l.setCity(request.getParameter("city"));
        l.setState(request.getParameter("state"));
        l.setZip(request.getParameter("zip"));
        l.setLatitude(new BigDecimal(request.getParameter("latitude")));
        l.setLongitude(new BigDecimal(request.getParameter("longitude")));
        dao.addLocation(l);
        o.setLocation(l);
        o.setPhone(request.getParameter("phone"));
        o.setEmail(request.getParameter("email"));
        
        dao.addOrganization(o);

        return "redirect:displayOrganizationsPage";
    }
    
    @RequestMapping(value = "/displayEditOrganizationForm", method = RequestMethod.GET)
    public String displayEditOrganizationForm(HttpServletRequest request, Model model) {
        String orgIdParameter = request.getParameter("organizationId");
        int orgId = Integer.parseInt(orgIdParameter);
        Organization organization = dao.getOrganizationById(orgId);
        organization.setLocation(dao.findLocationForOrganization(organization));
        model.addAttribute("organization", organization);
        return "editOrganizationForm";
    }
    
    @RequestMapping(value = "/editOrganization", method = RequestMethod.POST)
    public String editOrganization(@Valid @ModelAttribute("organization") Organization organization, BindingResult result) {
        
        if (result.hasErrors()) {
            return "editHeroForm";
        }
        
        organization.setLocation(dao.findLocationForOrganization(organization));
        dao.updateOrganization(organization);

        return "redirect:displayOrganizationsPage";
    }
    
    @RequestMapping(value = "/deleteOrganization", method = RequestMethod.GET)
    public String deleteOrganization(HttpServletRequest request) {
        String orgIdParameter = request.getParameter("organizationId");
        int orgId = Integer.parseInt(orgIdParameter);
        dao.deleteOrganization(orgId);
        return "redirect:displayOrganizationsPage";
    }
    
    @RequestMapping(value = "/displayOrganizationDetails", method = RequestMethod.GET)
    public String displayOrganizationDetails(HttpServletRequest request, Model model) {
        String orgIdParameter = request.getParameter("organizationId");
        int orgId = Integer.parseInt(orgIdParameter);
        Organization organization = dao.getOrganizationById(orgId);
        organization.setLocation(dao.findLocationForOrganization(organization));
        organization.setMemberList(dao.findMembersOfOrganization(organization));
        model.addAttribute("organization", organization);
        return "organizationDetails";
    }
    
    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public String addMember(HttpServletRequest request) {
        String heroIdParameter = request.getParameter("heroId");
        String orgIdParameter = request.getParameter("organizationId");
        int heroId = Integer.parseInt(heroIdParameter);
        int orgId = Integer.parseInt(orgIdParameter);
        Hero hero = dao.getHeroById(heroId);
        Organization org = dao.getOrganizationById(orgId);
        dao.addHeroToOrganization(hero, org);
        return "redirect:displayOrganizationsPage";
    }
    
    @RequestMapping(value = "/displayAddMemberForm", method = RequestMethod.GET)
    public String displayAddMemberForm(HttpServletRequest request, Model model) {
        String orgIdParameter = request.getParameter("organizationId");
        int orgId = Integer.parseInt(orgIdParameter);
        Organization organization = dao.getOrganizationById(orgId);
        List<Hero> heroList = dao.getAllHeroes();
        model.addAttribute("heroList", heroList);
        model.addAttribute("organization", organization);
        return "addMemberForm";
    }
    
}
