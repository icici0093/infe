package com.infe.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	MemberStore mStore;
	
	public int enrollMember(Member member) {
		return mStore.enrollMember(member);
	}

	public Member loginMember(Member mOne) {
		return mStore.loginMember(mOne);
	}

}
