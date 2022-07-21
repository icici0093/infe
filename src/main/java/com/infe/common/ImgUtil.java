package com.infe.common;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;


/**
 * 문자열관련 유틸 클래스.
 * 
 * @author dolgamza
 *
 */

public class ImgUtil  {


	private   int img_width = 0;
	private   int img_height = 0;
	


	public int getImg_width() {
		return img_width;
	}

	public void setImg_width(int img_width) {
		this.img_width = img_width;
	}

	public int getImg_height() {
		return img_height;
	}

	public void setImg_height(int img_height) {
		this.img_height = img_height;
	}

	/**
	 *	이미지의 폭을 구한다.
	 *
	 */
	public static int getImgWidth(String filePath)
	{
		int wmWidth = 0;
        File file = new File(filePath);
        boolean flag = file.exists();
        if (flag)
        {
			Image img = new ImageIcon(filePath).getImage();
			wmWidth = 0;
			wmWidth = img.getWidth(null); //가로 사이즈
        }
        else
        	wmWidth = 0;
		return wmWidth;
	}
	
	/**
	 *	이미지의 폭을 구한다.
	 *
	 */
	public static int getImgHeight(String filePath)
	{
		int wmHeight = 0;
        File file = new File(filePath);
        boolean flag = file.exists();
        if (flag)
        {		
			Image img = new ImageIcon(filePath).getImage();
			wmHeight = 0;
			wmHeight = img.getHeight(null);//세로 사이즈
        }
	        else
	        	wmHeight = 0;	
		return wmHeight;
	}	
	
	
	/**
	 *	비율에 따른 사이즈를 구함. 
	 *
	 */
	public  void setRatioSize(int oriwidth, int oriheight, int rewidth, int reheight)
	{
		
		double ratio_w = ((double)rewidth) / ((double)oriwidth);
		double ratio_h = ((double)reheight) / ((double)oriheight);
		double ratio_result = 0;
		if (ratio_w < ratio_h) ratio_result = ratio_w;
		else ratio_result = ratio_h;
		rewidth = (int)((double)oriwidth * ratio_result); 
		reheight = (int)((double)oriheight * ratio_result); 	
		setImg_width(rewidth);
		setImg_height(reheight);
	}	
	
	/**
	 *	비율에 따른 사이즈를 구함. 
	 *
	 */
	public  void setRatioSizeBig(int oriwidth, int oriheight, int rewidth, int reheight)
	{
		
		double ratio_w = ((double)rewidth) / ((double)oriwidth);
		double ratio_h = ((double)reheight) / ((double)oriheight);
		double ratio_result = 0;
		if (ratio_w > ratio_h) ratio_result = ratio_w;
		else ratio_result = ratio_h;
		rewidth = (int)((double)oriwidth * ratio_result); 
		reheight = (int)((double)oriheight * ratio_result); 	
		setImg_width(rewidth);
		setImg_height(reheight);
	}		
	
}
