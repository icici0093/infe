package com.infe.notice;

import java.sql.Date;
import java.util.ArrayList;

import com.infe.file.FileVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Notice {
	private int noNo;
	private int rowNum;
	private int userNo;
	private String noTitle;
	private String noContent;
	private Date noInsertDate;
	private String noUseYn;
	private int noCount;
	private ArrayList<FileVO> noFiles;
	private String fileName;
	private Date noUpdateDate;
	
	public Notice() {}
}
