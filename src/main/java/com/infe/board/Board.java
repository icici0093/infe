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
public class Board {
	private int boNo;
	private int boMotherNo;
	private int reMotherNo;
	private int rowNum;
	private int memNo;
	private String name;
	private String title;
	private String content;
	private Date insertDate;
	private Date updateDate;
	private Date deleteDate;
	private String useYn;
	private String count;
	private ArrayList<FileVO> files;
	private String fileName;
	
	public Board() {}
}
