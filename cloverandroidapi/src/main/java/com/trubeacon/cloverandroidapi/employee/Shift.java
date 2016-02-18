package com.trubeacon.cloverandroidapi.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;

public class Shift implements Identifiable {

	public static final String[] FILTERABLE_FIELDS = new String[] {
		"server_banking", "id", "in_and_override_time", "in_time", "override_in_time", "employee.name", "out_and_override_time", "out_time", "employee.id", "has_in_time"
	};
	public static final String[] UPDATABLE_FIELDS = new String[]{
		"overrideOutTime", "inTime", "serverBanking", "cashTipsCollected", "outTime", "overrideInTime"
	};
	public static final String[] EXPANDABLE_FIELDS = new String[] {"employee", "overrideInEmployee", "overrideOutEmployee"};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private long overrideOutTime;
	@JsonProperty
	private Employee overrideInEmployee;
	@JsonProperty
	private long inTime;
	@JsonProperty
	private boolean serverBanking;
	@JsonProperty
	private Employee overrideOutEmployee;
	@JsonProperty
	private long cashTipsCollected;
	@JsonProperty
	private long outTime;
	@JsonProperty
	private long overrideInTime;
	@JsonProperty
	private Employee employee;
	
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getOverrideOutTime() {
		return overrideOutTime;
	}
	public void setOverrideOutTime(long overrideOutTime) {
		this.overrideOutTime = overrideOutTime;
	}
	public Employee getOverrideInEmployee() {
		return overrideInEmployee;
	}
	public void setOverrideInEmployee(Employee overrideInEmployee) {
		this.overrideInEmployee = overrideInEmployee;
	}
	public long getInTime() {
		return inTime;
	}
	public void setInTime(long inTime) {
		this.inTime = inTime;
	}
	public boolean isServerBanking() {
		return serverBanking;
	}
	public void setServerBanking(boolean serverBanking) {
		this.serverBanking = serverBanking;
	}
	public Employee isOverrideOutEmployee() {
		return overrideOutEmployee;
	}
	public void setOverrideOutEmployee(Employee overrideOutEmployee) {
		this.overrideOutEmployee = overrideOutEmployee;
	}
	public long getCashTipsCollected() {
		return cashTipsCollected;
	}
	public void setCashTipsCollected(long cashTipsCollected) {
		this.cashTipsCollected = cashTipsCollected;
	}
	public long getOutTime() {
		return outTime;
	}
	public void setOutTime(long outTime) {
		this.outTime = outTime;
	}
	public long getOverrideInTime() {
		return overrideInTime;
	}
	public void setOverrideInTime(long overrideInTime) {
		this.overrideInTime = overrideInTime;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@Override
    public int hashCode() {
	    final int prime = 19;
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
	    if (!(obj instanceof Shift))
		    return false;
	    Shift other = (Shift) obj;
	    if (id == null) {
		    if (other.id != null)
			    return false;
	    } else if (!id.equals(other.id))
		    return false;
	    return true;
    }
	
}
