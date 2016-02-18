package com.trubeacon.cloverandroidapi.employee;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.AlternateIdentifiable;

import com.trubeacon.cloverandroidapi.common.AlternateIdentifier;
import static com.trubeacon.cloverandroidapi.common.AlternateIdentifier.*;

import com.trubeacon.cloverandroidapi.common.Role;
import com.trubeacon.cloverandroidapi.common.SystemRole;
import com.trubeacon.cloverandroidapi.common.WrappedList;

public class Employee implements AlternateIdentifiable<Employee> {
	
	public static final String[] FILTERABLE_FIELDS = new String[] {
		"id", "deletedTime", "customId", "pin", "nickname", "email", "name", "role", "roleId"
	};
	public static final String[] EXPANDABLE_FIELDS = new String[] {"roles"};
	public static final String[] UPDATABLE_FIELDS = new String[] {
		"nickname", "pin", "customId", "role"
	};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String customId;
	@JsonProperty
	private String nickname;
	@JsonProperty
	private String unhashedPin;
	@JsonProperty
	private String email;
	@JsonProperty
	private String pin; // hashed
	@JsonProperty
	private WrappedList<Role> roles;
	@JsonProperty
	private boolean inviteSent;
	@JsonProperty
	private SystemRole role;
	@JsonProperty("isOwner")
	private boolean isOwner;
	@JsonProperty
	private long claimedTime; // TODO: dunno how to map this...
	
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUnhashedPin() {
		return unhashedPin;
	}
	public void setUnhashedPin(String unhashedPin) {
		this.unhashedPin = unhashedPin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public WrappedList<Role> getRoles() {
		return roles;
	}
	public void setRoles(WrappedList<Role> roles) {
		this.roles = roles;
	}
	public boolean isInviteSent() {
		return inviteSent;
	}
	public void setInviteSent(boolean inviteSent) {
		this.inviteSent = inviteSent;
	}
	public SystemRole getRole() {
		return role;
	}
	public void setRole(SystemRole role) {
		this.role = role;
	}
	public boolean isOwner() {
		return isOwner;
	}
	public void setOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}
	public long getClaimedTime() {
		return claimedTime;
	}
	public void setClaimedTime(long claimedTime) {
		this.claimedTime = claimedTime;
	}
	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((id == null) ? 0 : id.hashCode());
	    return result;
    }
	
	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    // use instanceof instead of getClass() to allow equality for subclasses
	    if (!(obj instanceof Employee))
		    return false;
	    Employee other = (Employee) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }
	
	@Override
    public Collection<AlternateIdentifier> getAlternateIds() {
		return ids(id(this.name, "name"));
    }
	
	@Override
    public boolean alternateEquals(Employee target) {
		boolean equals = true;
		if (target != null) {
			equals |= (this.name == null) ? target.getName() == null : this.name.equals(target.getName()); 
		}
		else {
			equals = false;
		}
	    return equals;
    }
	
}
