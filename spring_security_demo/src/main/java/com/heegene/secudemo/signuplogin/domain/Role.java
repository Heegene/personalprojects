package com.heegene.secudemo.signuplogin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	ADMIN("ROLE_ADMIN"),
	MEMBER("ROLE_MEMBER");
	// service에서 사용하는 enum객체

	private String value;


}
