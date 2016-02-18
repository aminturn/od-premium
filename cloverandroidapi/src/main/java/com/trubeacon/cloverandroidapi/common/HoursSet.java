package com.trubeacon.cloverandroidapi.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HoursSet {

	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private HourRange sunday;
	@JsonProperty
	private HourRange monday;
	@JsonProperty
	private HourRange tuesday;
	@JsonProperty
	private HourRange wednesday;
	@JsonProperty
	private HourRange thursday;
	@JsonProperty
	private HourRange friday;
	@JsonProperty
	private HourRange saturday;
	@JsonProperty
	private Reference reference;
	
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
	public HourRange getSunday() {
		return sunday;
	}
	public void setSunday(HourRange sunday) {
		this.sunday = sunday;
	}
	public HourRange getMonday() {
		return monday;
	}
	public void setMonday(HourRange monday) {
		this.monday = monday;
	}
	public HourRange getTuesday() {
		return tuesday;
	}
	public void setTuesday(HourRange tuesday) {
		this.tuesday = tuesday;
	}
	public HourRange getWednesday() {
		return wednesday;
	}
	public void setWednesday(HourRange wednesday) {
		this.wednesday = wednesday;
	}
	public HourRange getThursday() {
		return thursday;
	}
	public void setThursday(HourRange thursday) {
		this.thursday = thursday;
	}
	public HourRange getFriday() {
		return friday;
	}
	public void setFriday(HourRange friday) {
		this.friday = friday;
	}
	public HourRange getSaturday() {
		return saturday;
	}
	public void setSaturday(HourRange saturday) {
		this.saturday = saturday;
	}
	public Reference getReference() {
		return reference;
	}
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	
	public static class HourRange {

		@JsonProperty
		private int start;
		@JsonProperty
		private int end;
		
		public int getStart() {
			return start;
		}
		public void setStart(int start) {
			this.start = start;
		}
		public int getEnd() {
			return end;
		}
		public void setEnd(int end) {
			this.end = end;
		}
		
	}
	
}
