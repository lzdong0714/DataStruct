package algorithm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
/*
 * 这是一个绘图的工具类
 * */
public class AlogVisHelper {
	private AlogVisHelper() {}
	
	public static void setStrokeWidth(Graphics2D graphics2d,int w) {
		int strokeWidth=w;
		graphics2d.setStroke(new BasicStroke(strokeWidth,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
	}
	
	public static void strokeCircle (Graphics2D graphics2d,int x,int y,int r) {
		Ellipse2D circle=new Ellipse2D.Double(x-r,y-r,2*r,2*r);
		graphics2d.draw(circle);
	}
	
	public static void fillCircle(Graphics2D graphics2d,int x,int y ,int r) {
		Ellipse2D circle=new Ellipse2D.Double(x-r,y-r,2*r,2*r);
		graphics2d.fill(circle);	
	}
	
	public static void setColor(Graphics2D graphics2d,Color color) {
		graphics2d.setColor(color);
	}
	
	public static void pause(int t) {
		try {
			Thread.sleep(t);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
