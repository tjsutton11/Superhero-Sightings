/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dao;

import com.sg.superherosightingsspringmvc.dto.Hero;
import com.sg.superherosightingsspringmvc.dto.Location;
import com.sg.superherosightingsspringmvc.dto.Organization;
import com.sg.superherosightingsspringmvc.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author tjsut
 */
public interface SuperheroSightingDao {
    
    public void addLocation(Location location);
    
    public void deleteLocation(int locationId);
    
    public void updateLocation(Location location);
    
    public Location getLocationById(int id);
    
    public List<Location> getAllLocations();
    
    public void addOrganization(Organization organization);
    
    public void deleteOrganization(int id);
    
    public void updateOrganization(Organization organization);
    
    public Organization getOrganizationById(int id);
    
    public List<Organization> getAllOrganizations();
    
    public void addHero(Hero hero);
    
    public void deleteHero(int heroId);
    
    public void updateHero(Hero hero);
    
    public Hero getHeroById(int id);
    
    public List<Hero> getAllHeroes();
    
    public void addSighting(Sighting sighting); //Record a sighting for a particular location & date
    
    public void deleteSighting(int sightingId);
    
    public void updateSighting(Sighting sighting);
    
    public Sighting getSightingById(int id);
    
    public List<Sighting> getAllSightings();
    
    public List<Hero> displayHeroesSightedAtLocation(int locationId); //Report heroes sighted at particular location
    
    public List<Location> displayLocationsHeroSighted(int heroId); //Report locations where hero has been seen
    
    public List<Sighting> displaySightingsOnDate(LocalDate date); //Report sightings for a particular date
    
    public List<Hero> displayOrganizationMembers(int organizationId); //Report all members of particular organization
    
    public List<Organization> displayHeroMembership(int heroId); //Report organizations hero is a part of
    
    public Location findLocationForOrganization(Organization o);
    
    public void addHeroToOrganization(Hero hero, Organization org);
    
    public List<Organization> findOrganizationsForHero(Hero hero);
    
    public List<Hero> findMembersOfOrganization(Organization org);
    
    public void insertHeroSighting(Hero hero, Sighting sighting);
    
    public Hero findHeroForSighting(Sighting sighting);
    
    public Location findLocationOfSighting(Sighting sighting);
    
    public List<Sighting> getLastTenSightings();
    
}
