package com.infe.board;

import lombok.Getter;
import lombok.ToString;
import lombok.Setter;

@Getter
@Setter
@ToString
public class Search {
	private String searchCondition;
	private String searchValue;
	
	public Search() {}
}
