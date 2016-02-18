package com.trubeacon.cloverandroidapi.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.trubeacon.cloverandroidapi.common.Identifiable;

public class Tender implements Identifiable {

	public static final String[] FILTERABLE_FIELDS = new String[] {
		"id", "deletedTime", "enabled", "visible", "labelKey", "label"
	};
	
	@JsonProperty
	private String id;
	@JsonProperty
	private boolean enabled;
	@JsonProperty
	private boolean supportsTipping;
	@JsonProperty
	private String instructions;
	@JsonProperty
	private boolean visible;
	@JsonProperty
	private String labelKey;
	@JsonProperty
	private String label;
	@JsonProperty
	private boolean opensCashDrawer;
	@JsonProperty
	private boolean editable;
	
	@Override
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isSupportsTipping() {
		return supportsTipping;
	}
	public void setSupportsTipping(boolean supportsTipping) {
		this.supportsTipping = supportsTipping;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public String getLabelKey() {
		return labelKey;
	}
	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isOpensCashDrawer() {
		return opensCashDrawer;
	}
	public void setOpensCashDrawer(boolean opensCashDrawer) {
		this.opensCashDrawer = opensCashDrawer;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((label == null) ? 0 : label.hashCode());
	    result = prime * result
	            + ((labelKey == null) ? 0 : labelKey.hashCode());
	    return result;
    }
	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (!(obj instanceof Tender))
		    return false;
	    Tender other = (Tender) obj;
	    if (label == null) {
		    if (other.label != null)
			    return false;
	    } else if (!label.equals(other.label))
		    return false;
	    if (labelKey == null) {
		    if (other.labelKey != null)
			    return false;
	    } else if (!labelKey.equals(other.labelKey))
		    return false;
	    return true;
    }

}
