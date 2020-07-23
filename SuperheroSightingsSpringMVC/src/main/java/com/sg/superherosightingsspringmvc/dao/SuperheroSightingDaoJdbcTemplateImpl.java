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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tjsut
 */
public class SuperheroSightingDaoJdbcTemplateImpl implements SuperheroSightingDao {
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    // ====================================
    // Location Related Prepared Statements
    // ====================================
    
    private static final String SQL_INSERT_LOCATION
            = "insert into location (name, description, street, city, state, zip,"
            + " latitude, longitude) values (?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String SQL_DELETE_LOCATION
            = "delete from location where locationId = ?";
    
    private static final String SQL_UPDATE_LOCATION
            = "update location set name = ?, description = ?, street = ?, city = ?,"
            + " state = ?, zip = ?, latitude = ?, longitude = ? where locationId = ?";
    
    private static final String SQL_SELECT_LOCATION
            = "select * from location where locationId = ?";
    
    private static final String SQL_SELECT_ALL_LOCATIONS
            = "select * from location";
    
        private static final String SQL_FIND_HEROES_BY_LOCATION_ID
            = "select h.name, "
            + "from hero h "
            + "join herosighting hs on hs.heroId = h.heroId "
            + "join sighting s on hs.sightingId = s.sightingId "
            + "where s.locationId = ?;";
    
    // ===========================================================
    // Organization & HeroOrganization Related Prepared Statements
    // ===========================================================
    
    private static final String SQL_INSERT_ORGANIZATION
            = "insert into organization (name, description, locationId,"
            + " phone, email) values (?, ?, ?, ?, ?)";
    
    private static final String SQL_INSERT_HERO_ORGANIZATION
            = "insert into heroorganization (heroId, organizationId) values(?, ?)";

    private static final String SQL_DELETE_ORGANIZATION
            = "delete from organization where organizationId = ?";
    
    private static final String SQL_DELETE_ORGANIZATION_FROM_BRIDGE
            = "delete from heroorganization where organizationId = ?";
    
    private static final String SQL_DELETE_HERO_FROM_BRIDGE
            = "delete from heroorganization where heroId = ?";

    private static final String SQL_UPDATE_ORGANIZATION
            = "update organization set name = ?, description = ?,"
            + " locationId = ?, phone = ?, email = ? where organizationId = ?";

    private static final String SQL_SELECT_ORGANIZATION
            = "select * from organization where organizationId = ?";

    private static final String SQL_SELECT_ALL_ORGANIZATIONS
            = "select * from organization";
    
    private static final String SQL_SELECT_HEROES_BY_ORGANIZATION_ID
            = "select h.heroId, h.name, h.description, h.power from hero h "
            + "join heroorganization ho on h.heroId = ho.heroId "
            + "where ho.organizationId = ?";
    
    private static final String SQL_SELECT_ORGANIZATIONS_BY_HERO_ID
            = "select o.organizationId, o.name, o.description, o.locationId, o.phone, o.email "
            + "from organization o join heroorganization ho on heroId "
            + "where o.organizationId = ho.organizationId and ho.heroId = ?;";
    
    private static final String SQL_SELECT_LOCATION_BY_ORGANIZATION_ID
            = "select l.locationId, l.name, l.description, l.street, l.city, l.state, l.zip, l.latitude, l.longitude "
            + "from location l join organization o on o.locationId = l.locationId "
            + "where o.organizationId = ?";
    
    // ===================================================
    // Sighting & HeroSighting Related Prepared Statements
    // ===================================================
    
    private static final String SQL_INSERT_SIGHTING
            = "insert into sighting (LocationID, `date`, `time`) values (?, ?, ?)";
    
    private static final String SQL_INSERT_HERO_SIGHTING
            = "insert into herosighting (heroId, sightingId) values (?, ?)";
    
    private static final String SQL_DELETE_SIGHTING
            = "delete from sighting where sightingId = ?";
    
    private static final String SQL_DELETE_HERO_FROM_HS_BRIDGE
            = "delete from herosighting where heroId = ?";
    
    private static final String SQL_DELETE_SIGHTING_FROM_HS_BRIDGE
            = "delete from herosighting where sightingId = ?";
    
    private static final String SQL_UPDATE_SIGHTING
            = "update sighting set locationId = ?, `date` = ?, `time` = ? where sightingId = ?";
    
    private static final String SQL_SELECT_SIGHTING
            = "select * from sighting where sightingId = ?";
    
    private static final String SQL_SELECT_ALL_SIGHTINGS
            = "select * from sighting";
    
    private static final String SQL_GET_LAST_TEN_SIGHTINGS
            = "select * from sighting order by date desc limit 10;";
    
    private static final String SQL_FIND_SIGHTINGS_ON_DATE
            = "select * from sighting "
            + "where date = ?";
    
    private static final String SQL_SELECT_HERO_BY_SIGHTING_ID
            = "select h.heroId, h.name, h.description, h.power from hero h "
            + "join herosighting hs on sightingId "
            + "where h.heroId = hs.heroId and hs.sightingId = ?;";
    
    private static final String SQL_SELECT_SIGHTING_BY_HERO_ID
            = "select s.sightingId, s.locationId, s.date, s.time "
            + "join herosighting hs on heroId "
            + "where s.sightingId = hs.sightingId and hs.heroId = ?;";
    
    private static final String SQL_SELECT_LOCATION_BY_SIGHTING_ID
            = "select l.locationId, l.name, l.description, l.street, l.city, l.state, l.zip, l.latitude, l.longitude "
            + "from location l join sighting s on s.locationId = l.locationId "
            + "where s.sightingId = ?";
    
//    private static final String SQL_SELECT_ALL_SIGHTINGS_AT_LOCATION
//            = "select * from sighting "
//            + "where locationId = ?";
    
    // ================================
    // Hero Related Prepared Statements
    // ================================
    
    private static final String SQL_INSERT_HERO
            = "insert into hero (name, description, power) values (?, ?, ?)";
    
    private static final String SQL_DELETE_HERO
            = "delete from hero where heroId = ?";
    
    private static final String SQL_UPDATE_HERO
            = "update hero set name = ?, description = ?, power = ? where heroId = ?";
    
    private static final String SQL_SELECT_HERO
            = "select * from hero where heroId = ?";
    
    private static final String SQL_SELECT_ALL_HEROES
            = "select * from hero";
    
    private static final String SQL_FIND_ALL_LOCATIONS_FOR_HERO
            = "select l.* "
            + "from location l "
            + "join sighting s on s.locationId = l.locationId "
            + "join herosighting hs.sightingId = s.sightingId "
            + "where heroId = ?";
    
//    private static final String SQL_FIND_ALL_HEROES_BY_ORGANIZATION
//            = "SELECT h.* from heroorganization ho "
//            + "join hero h on h.heroId = ho.heroId "
//            + "where ho.organizationId = ?";
    
    // ==================================
    // Location Related Interface Methods
    // ==================================
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addLocation(Location location) {
        jdbcTemplate.update(SQL_INSERT_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLatitude(),
                location.getLongitude());
        
        int locationId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        
        location.setLocationId(locationId);
    }
    
    @Override
    public void deleteLocation(int locationId) {
        jdbcTemplate.update(SQL_DELETE_LOCATION, locationId);
    }
    
    @Override
    public void updateLocation(Location location) {
        jdbcTemplate.update(SQL_UPDATE_LOCATION,
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZip(),
                location.getLatitude(),
                location.getLongitude(),
                location.getLocationId());
    }
    
    @Override
    public Location getLocationById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION, 
                    new LocationMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Location> getAllLocations() {
        return jdbcTemplate.query(SQL_SELECT_ALL_LOCATIONS, new LocationMapper());
    }
    
    // ======================================
    // Organization Related Interface Methods
    // ======================================
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addOrganization(Organization org) {
        jdbcTemplate.update(SQL_INSERT_ORGANIZATION,
                org.getName(),
                org.getDescription(),
                org.getLocation().getLocationId(),
                org.getPhone(),
                org.getEmail());
                org.setOrganizationId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
                
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteOrganization(int orgId) {
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION_FROM_BRIDGE, orgId);
        jdbcTemplate.update(SQL_DELETE_ORGANIZATION, orgId);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateOrganization(Organization org) {
        jdbcTemplate.update(SQL_UPDATE_ORGANIZATION,
                org.getName(),
                org.getDescription(),
                org.getLocation().getLocationId(),
                org.getPhone(),
                org.getEmail(),
                org.getOrganizationId());
    }
    
    @Override
    public Organization getOrganizationById(int id) {
        try {
            Organization org = jdbcTemplate.queryForObject(SQL_SELECT_ORGANIZATION,
                    new OrganizationMapper(), id);
            org.setMemberList(findMembersOfOrganization(org));
            return org;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Organization> getAllOrganizations() {
        List<Organization> orgList = jdbcTemplate.query(SQL_SELECT_ALL_ORGANIZATIONS, new OrganizationMapper());
        return orgList;
    }
    
    // ==============================
    // Hero Related Interface Methods
    // ==============================
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addHero(Hero hero) {
        jdbcTemplate.update(SQL_INSERT_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getPowers());
                hero.setHeroId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));           
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteHero(int id) {
        jdbcTemplate.update(SQL_DELETE_HERO_FROM_BRIDGE, id);
        jdbcTemplate.update(SQL_DELETE_HERO_FROM_HS_BRIDGE, id);
        jdbcTemplate.update(SQL_DELETE_HERO, id);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateHero(Hero hero) {
        jdbcTemplate.update(SQL_UPDATE_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getPowers(),
                hero.getHeroId());
    }
    
    @Override
    public Hero getHeroById(int id) {
        try {
            Hero hero = jdbcTemplate.queryForObject(SQL_SELECT_HERO, new HeroMapper(), id);
            hero.setOrganizations(findOrganizationsForHero(hero));
            hero.setSightings(findSightingsOfHero(hero));
            return hero;
        } catch (EmptyResultDataAccessException | BadSqlGrammarException ex) {
            Hero hero = jdbcTemplate.queryForObject(SQL_SELECT_HERO, new HeroMapper(), id);
            return hero;
        }
    }
    
    @Override
    public List<Hero> getAllHeroes() {
        List<Hero> heroList = jdbcTemplate.query(SQL_SELECT_ALL_HEROES, new HeroMapper());
        return heroList;
    }
    
    @Override
    public void addHeroToOrganization(Hero hero, Organization org) {
//        List<Organization> heroOrgs = hero.getOrganizations();
//        heroOrgs.add(org);
//        hero.setOrganizations(heroOrgs);
//        
//        List<Hero> memberList = org.getMemberList();
//        memberList.add(hero);
//        org.setMemberList(memberList);
        
        insertHeroOrganization(org, hero);
    }
    
    public void addHeroToSighting(Hero hero, Sighting sighting) {
        List<Sighting> heroSightings = hero.getSightings();
        heroSightings.add(sighting);
        hero.setSightings(heroSightings);
        
        insertHeroSighting(hero, sighting);
    }
    
    // ==================================
    // Sighting Related Interface Methods
    // ==================================
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_INSERT_SIGHTING,
                sighting.getLocation().getLocationId(),
                java.sql.Date.valueOf(sighting.getDate()),
                java.sql.Time.valueOf(sighting.getTime()));
                sighting.setSightingId(jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class));
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void deleteSighting(int id) {
        jdbcTemplate.update(SQL_DELETE_SIGHTING_FROM_HS_BRIDGE, id);
        jdbcTemplate.update(SQL_DELETE_SIGHTING, id);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateSighting(Sighting sighting) {
        jdbcTemplate.update(SQL_UPDATE_SIGHTING,
                sighting.getLocation().getLocationId(),
                java.sql.Date.valueOf(sighting.getDate()),
                java.sql.Time.valueOf(sighting.getTime()),
                sighting.getSightingId());
        
        jdbcTemplate.update(SQL_DELETE_SIGHTING_FROM_HS_BRIDGE, sighting.getSightingId());
        insertHeroSighting(sighting.getHero(), sighting);
    }
    
    @Override
    public Sighting getSightingById(int id) {
        try {
            Sighting s = jdbcTemplate.queryForObject(SQL_SELECT_SIGHTING, new SightingMapper(), id);
            s.setLocation(findLocationOfSighting(s));
            return s;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    @Override
    public List<Sighting> getAllSightings() {
        List<Sighting> sightingList = jdbcTemplate.query(SQL_SELECT_ALL_SIGHTINGS, new SightingMapper());
        return associateLocationsWithSightings(sightingList);
    }
    
    @Override
    public List<Sighting> getLastTenSightings() {
        List<Sighting> recentSightings = jdbcTemplate.query(SQL_GET_LAST_TEN_SIGHTINGS, new SightingMapper());
        return associateLocationsWithSightings(recentSightings);
    }
    
    // ==============
    // Helper Methods
    // ==============
    
    private void insertHeroOrganization(Organization org, Hero hero) {
        final int orgId = org.getOrganizationId();
        final int heroId = hero.getHeroId();

        jdbcTemplate.update(SQL_INSERT_HERO_ORGANIZATION, heroId, orgId);
    }
    
    @Override
    public void insertHeroSighting(Hero hero, Sighting sighting) {
        final int heroId = hero.getHeroId();
        final int sightingId = sighting.getSightingId();
        
        jdbcTemplate.update(SQL_INSERT_HERO_SIGHTING, heroId, sightingId);
    }

    @Override
    public List<Hero> findMembersOfOrganization(Organization org) {
        return jdbcTemplate.query(SQL_SELECT_HEROES_BY_ORGANIZATION_ID,
                new HeroMapper(), org.getOrganizationId());
    }

    @Override
    public Location findLocationForOrganization(Organization org) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_ORGANIZATION_ID,
                new LocationMapper(), org.getOrganizationId());
    }
    
    @Override
    public Location findLocationOfSighting(Sighting sighting) {
        return jdbcTemplate.queryForObject(SQL_SELECT_LOCATION_BY_SIGHTING_ID, 
                new LocationMapper(), sighting.getSightingId());
    }

    private List<Organization> associateLocationAndMembersWithOrganization(List<Organization> orgList) {
        for (Organization currentOrg : orgList) {
            currentOrg.setMemberList(findMembersOfOrganization(currentOrg));
            //currentOrg.setLocationId(findLocationForOrganization(currentOrg));
        }
        return orgList;
    }
    
    @Override
    public List<Organization> findOrganizationsForHero(Hero hero) {
        return jdbcTemplate.query(SQL_SELECT_ORGANIZATIONS_BY_HERO_ID,
                new OrganizationMapper(), hero.getHeroId());
    }

    private List<Sighting> findSightingsOfHero(Hero hero) {
        return jdbcTemplate.query(SQL_SELECT_SIGHTING_BY_HERO_ID,
                new SightingMapper(), hero.getHeroId());
    }
    
    private List<Sighting> associateLocationsWithSightings(List<Sighting> sightingList) {
        for (Sighting s : sightingList) {
            s.setLocation(findLocationOfSighting(s));
        }
        return sightingList;
    }

    private List<Hero> associateOrganizationsAndSightingsWithHero(List<Hero> heroList) {
        for (Hero h : heroList) {
            h.setOrganizations(findOrganizationsForHero(h));
            h.setSightings(findSightingsOfHero(h));
        }
        return heroList;
    }
    
    @Override
    public Hero findHeroForSighting(Sighting sighting) {
        try {
        return jdbcTemplate.queryForObject(SQL_SELECT_HERO_BY_SIGHTING_ID,
                new HeroMapper(), sighting.getSightingId());
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
    
    // =============
    // Other Methods
    // =============
    
    @Override
    public List<Hero> displayHeroesSightedAtLocation(int locationId) {
        List<Hero> heroes = jdbcTemplate.query(SQL_FIND_HEROES_BY_LOCATION_ID, new HeroMapper(), locationId);
        return heroes;
    }
    
    @Override
    public List<Location> displayLocationsHeroSighted(int heroId) {
        List<Location> placesSeen = jdbcTemplate.query(SQL_FIND_ALL_LOCATIONS_FOR_HERO, new LocationMapper(), heroId);
        return placesSeen;
    }
    
    @Override
    public List<Sighting> displaySightingsOnDate(LocalDate date) {
        List<Sighting> sightings = jdbcTemplate.query(SQL_FIND_SIGHTINGS_ON_DATE, new SightingMapper(), date);
        return sightings;
    }
    
    @Override
    public List<Hero> displayOrganizationMembers(int organizationId) {
        Organization org = getOrganizationById(organizationId);
        List<Hero> memberList = findMembersOfOrganization(org);
        return memberList;
    }
    
    @Override
    public List<Organization> displayHeroMembership(int heroId) {
        Hero hero = getHeroById(heroId);
        List<Organization> memberships = hero.getOrganizations();
        return memberships;
    }
    
    // =======
    // MAPPERS
    // =======
    
    private static final class HeroMapper implements RowMapper<Hero> {
        
        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setName(rs.getString("name"));
            hero.setDescription(rs.getString("description"));
            hero.setPowers(rs.getString("power"));
            hero.setHeroId(rs.getInt("heroId"));
            return hero;
        }
    }
    
    private static final class LocationMapper implements RowMapper<Location> {
        
        @Override
        public Location mapRow(ResultSet rs, int i) throws SQLException {
            Location loc = new Location();
            loc.setName(rs.getString("name"));
            loc.setDescription(rs.getString("description"));
            loc.setStreet(rs.getString("street"));
            loc.setCity(rs.getString("city"));
            loc.setState(rs.getString("state"));
            loc.setZip(rs.getString("zip"));
            loc.setLongitude(rs.getBigDecimal("longitude"));
            loc.setLatitude(rs.getBigDecimal("latitude"));
            loc.setLocationId(rs.getInt("locationId"));
            return loc;
        }
    }
    
    private static final class OrganizationMapper implements RowMapper<Organization> {
        
        @Override
        public Organization mapRow(ResultSet rs, int i) throws SQLException {
            Organization org = new Organization();
            org.setName(rs.getString("name"));
            org.setDescription(rs.getString("description"));
            org.setPhone(rs.getString("phone"));
            org.setEmail(rs.getString("email"));
            org.setOrganizationId(rs.getInt("organizationId"));
            return org;
        }
    }
    
    private static final class SightingMapper implements RowMapper<Sighting> {
        
        @Override
        public Sighting mapRow(ResultSet rs, int i) throws SQLException {
            Sighting s = new Sighting();
            s.setDate(rs.getTimestamp("date").toLocalDateTime().toLocalDate());
            s.setTime(rs.getTimestamp("time").toLocalDateTime().toLocalTime());
            s.setSightingId(rs.getInt("sightingId"));
            return s;
        }
    }
    
}
