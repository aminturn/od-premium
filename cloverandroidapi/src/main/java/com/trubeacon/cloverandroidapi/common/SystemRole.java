package com.trubeacon.cloverandroidapi.common;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum SystemRole {
	
	ADMIN,
	MANAGER,
	EMPLOYEE;
	
	@JsonCreator
	public static SystemRole fromString(String roleString) {
		SystemRole role = null;
		for (SystemRole r : SystemRole.values()) {
			if (r.toString().equals(roleString)) {
				role = r;
				break;
			}
		}
		return role;
	}
	
}
