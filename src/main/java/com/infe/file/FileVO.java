package com.infe.file;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FileVO {
	private int fiNo;
	private int boNo;
	private Date fiInsertDate;
	private String fiRealFileName;
	private String fiSaveFileName;
	private String fiDirectory;
	private String fiUseYn;
	
	
	public FileVO() {}

	public FileVO(int boNo) {
		super();
		this.boNo = boNo;
	}

	public FileVO(String fiRealFileName, String fiSaveFileName, String fiDirectory) {
		super();
		this.fiRealFileName = fiRealFileName;
		this.fiSaveFileName = fiSaveFileName;
		this.fiDirectory = fiDirectory;
	}
 
	public FileVO(int boNo, String fiRealFileName, String fiSaveFileName, String fiDirectory) {
		super();
		this.boNo = boNo;
		this.fiRealFileName = fiRealFileName;
		this.fiSaveFileName = fiSaveFileName;
		this.fiDirectory = fiDirectory;
	}
}
