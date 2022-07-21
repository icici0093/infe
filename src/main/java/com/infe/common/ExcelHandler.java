package com.infe.common;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelHandler<T> implements ResultHandler{
	
	private Workbook workbook;
    private Sheet sheet;
    private Row row;
    private int rowNum = 0;
    private String [] titleArr = {};
    private String [] titleValueArr = {};
    
    public String[] getTitleArr() {
		return titleArr;
	}
    public String[] getTitleValueArr() {
    	return titleValueArr;
    }

	@Override
	public void handleResult(ResultContext resultContext) {
		// TODO Auto-generated method stub
		CubeMap data = (CubeMap)resultContext.getResultObject();
		createBody(data);
	}
	
	public ExcelHandler(Workbook workbook, String [] titleArr, String [] titleValueArr) {
        this.workbook = workbook;
        sheet = workbook.createSheet();
        
        if(titleArr != null) 		this.titleArr = titleArr;
        if(titleValueArr != null) 	this.titleValueArr = titleValueArr;
        createTitle();
    }
    
    /**
     * @Method Name : createTitle
     * @작성일 : 2020. 3. 23.
     * @작성자 : chan
     * @변경이력 :
     * @Method 설명 : Excel Title Draw
     */
    private void createTitle() {
        int colIdx = 0;
		row = sheet.createRow(colIdx);
		
		if(this.getTitleArr() != null){
    		for(int i=0; i<this.getTitleArr().length; i++) {
    			row.createCell(colIdx++).setCellValue(this.getTitleArr()[i]);
    		}
    	}
		
        rowNum++;
    }
   
    /**
     * @Method Name : createBody
     * @작성일 : 2020. 3. 23.
     * @작성자 : chan
     * @변경이력 :
     * @Method 설명 : Excel value Draw
     * @param data
     */
    private void createBody(CubeMap data) {
    	
    	int colidx = 0;
    	
    	row = sheet.createRow(rowNum);
        if(!data.isEmpty()) {
        	
        	if(this.getTitleValueArr() != null){
        		for(int i=0; i<this.getTitleValueArr().length; i++) {
        			row.createCell(colidx++).setCellValue(data.get(this.getTitleValueArr()[i], ""));
        		}
        		rowNum++;
        	}
        }
    }
}
