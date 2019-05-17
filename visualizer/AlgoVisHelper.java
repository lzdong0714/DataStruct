package visualizer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class AlgoVisHelper {
	
	//±‰≥…æ≤Ã¨¿‡
	private  AlgoVisHelper() {	} 
	
	public static final Color Red=new Color(0xF4433);
	
	public static void strokeCircle(Graphics2D g2d,int x,int y,int r ) {
		Double circle=new Ellipse2D.Double(x-r,y-r,2*r,2*r);
		g2d.draw(circle);
	}
	
	public static void fillCircle(Graphics2D g2d,int x,int y,int r) {
		Ellipse2D circle=new Ellipse2D.Double(x-r,y-r,2*r,2*r);
		g2d.fill(circle);
	}
	
	public static void strokeRectangle(Graphics2D g,int x,int y,int w,int h ) {
		Rectangle2D rectangle=new Rectangle(x, y, w, h);
		g.draw(rectangle);
	}
	
	public static void fillRectangle(Graphics2D g,int x,int y,int w,int h) {
		Rectangle2D rectangle=new Rectangle(x, y, w, h);
		g.fill(rectangle);
	}
	
	public static void setColor(Graphics2D g,Color color) {
		g.setColor(color);
	}
	
	public static void setStrokeWidth(Graphics2D graphics2d,int w) {
		int strokeWidth=w;
		graphics2d.setStroke(new BasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
	}
	
	public static void pause(int t) {
		try {
			Thread.sleep(t);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	public static void putImage(Graphics2D g,int x,int y,String imageURL)
	{
		ImageIcon icon=new ImageIcon(imageURL);
		Image image=icon.getImage();
		
		g.drawImage(image, x, y, null);
	}
	
	public static void drawText(Graphics2D g,String text,int centerx,int centery)  {
		if(text==null)
		{
			throw new IllegalArgumentException("Text is null in drawText function");
		}
		
		FontMetrics metrics=g.getFontMetrics();		
		int w=metrics.stringWidth(text);
		int h=metrics.getDescent();
		g.drawString(text, centerx-w/2, centery+h);
		
	}


}
