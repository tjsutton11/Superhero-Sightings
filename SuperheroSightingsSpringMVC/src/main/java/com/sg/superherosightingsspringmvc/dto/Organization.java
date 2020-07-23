/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightingsspringmvc.dto;

import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author tjsut
 */
public class Organization {
    
    private int organizationId;
    @NotEmpty(message = "You must include an organization name.")
    @Length(max = 45, message = "Name must be no more than 45 characters in length.")
    private String name;
    @Length(max = 240, message = "Organization description must be no more than 240 characters in length.")
    private String description;
    private Location location;
    @NotEmpty(message = "You must include a phone number for the organization.")
    @Length(max = 10, message = "Phnne number must be no more than 10 characters in length.")
    private String phone;
    @Email(message = "You must include an email for the organization.")
    @Length(max = 30, message = "Email must be no more than 30 characters in length.")
    private String email;
    private List<Hero> memberList;

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Hero> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Hero> memberList) {
        this.memberList = memberList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.organizationId;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.location);
        hash = 29 * hash + Objects.hashCode(this.phone);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.memberList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organization other = (Organization) obj;
        if (this.organizationId != other.organizationId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.memberList, other.memberList)) {
            return false;
        }
        return true;
    }
    
    
    
}
