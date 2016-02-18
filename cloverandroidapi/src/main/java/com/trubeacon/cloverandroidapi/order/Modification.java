package com.trubeacon.cloverandroidapi.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.inventory.Modifier;

public class Modification {

	@JsonProperty
	private String id;
	@JsonProperty
	private String name;
	@JsonProperty
	private Modifier modifier;
	@JsonProperty
	private long amount;
	@JsonProperty
	private String alternateName;
	
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
	public Modifier getModifier() {
		return modifier;
	}
	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getAlternateName() {
		return alternateName;
	}
	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}	
	
}
