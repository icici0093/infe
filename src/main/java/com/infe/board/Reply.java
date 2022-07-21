package com.infe.board;

import java.sql.Date;
import java.util.ArrayList;

import com.infe.file.FileVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Reply {
	private int reNo;
	private int boNo;
	private int memNo;
	private String content;
	private String name;
	private Date insertDate;
	private Date updateDate;
	private Date deleteDate;
	private String useYn;
	private String count;
	private int rGroup;
	private int rStep;
	private int rIndent;
	
	public Reply() {}
}
