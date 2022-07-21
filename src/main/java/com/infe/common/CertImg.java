package com.infe.common;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class CertImg {
	
	public static BufferedImage createCert(int width, int height, String certnum) throws IOException {
		
		
		Color color_bg = new Color(209, 209, 209);
		Color color_txt = new Color(0, 0, 120);
		BufferedImage destImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = (Graphics2D) destImg.getGraphics();
		graphics.setBackground(color_bg);
		graphics.setColor(color_bg);
		graphics.fillRect (0, 0, width, height);
		
		graphics.setColor(color_txt);
	 	graphics.setFont(new Font("맑은 고딕",Font.ITALIC,25));
	 	graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	 	graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
	 	graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);	

	 	Font font = graphics.getFont();
	 	FontMetrics metrics = graphics.getFontMetrics(font);
	 	int widthtxt = metrics.stringWidth(certnum);
	 	graphics.drawString(certnum,(width/2)-(widthtxt/2),35);
	 	return destImg;
	}
	

}
