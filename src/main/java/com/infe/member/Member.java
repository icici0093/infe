package com.infe.member;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	private int memNo;
	private String memID;
	private String memPW;
	private String name;
	private String phone;
	private String mail;
	private String postcode;
	private String addr;
	private String addrDt;
	private String useYn;
	private String memLv;
	
	public Member() {}

	public Member(String memID, String memPW) {
		super();
		this.memID = memID;
		this.memPW = memPW;
	}
}
