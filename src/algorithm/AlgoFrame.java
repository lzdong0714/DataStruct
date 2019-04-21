package algorithm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class AlgoFrame extends JFrame {
	private int canvasWidth;
	private int canvasHeight;
public AlgoFrame (String title,int canvasWidth,int canvasHeight) {	
	super(title);
	this.canvasWidth=canvasWidth;
	this.canvasHeight=canvasHeight;
	
	AlgoCanvas canvas=new AlgoCanvas();
	canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
	setContentPane(canvas);
	pack();
	//setSize(canvasWidth,canvasHeight);
	setVisible(true);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

public AlgoFrame (String title) {
	this(title,1024,786);
}
public int getCanvasWidth() {
	return canvasWidth;
}
public int getCanvasHeight() {
	return canvasHeight;
}

private Circle[] circles;
public void render(Circle[] circles) {
	this.circles=circles;
	repaint();//会重新绘制整个画布
}

private class  AlgoCanvas extends JPanel{
	public AlgoCanvas() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawOval(50, 50, 300, 300);
		
		Graphics2D graphics2d=(Graphics2D)g;
		
		//抗锯齿
		RenderingHints hints=new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.addRenderingHints(hints);
		AlogVisHelper.setStrokeWidth(graphics2d, 5);
		
		// 绘制一个球体示例
//		AlogVisHelper.setColor(graphics2d, Color.BLUE);
//		AlogVisHelper.strokeCircle(graphics2d, canvasWidth/2, canvasHeight/2, 200);
//		
//		AlogVisHelper.setColor(graphics2d, Color.GREEN);
//		AlogVisHelper.fillCircle(graphics2d, canvasWidth/2, canvasHeight/2, 200);
		
		//具体绘制
		AlogVisHelper.setStrokeWidth(graphics2d, 1);
		AlogVisHelper.setColor(graphics2d, Color.RED);
		for (Circle circle:circles)
		{
			if(!circle.isFilled)
			AlogVisHelper.strokeCircle(graphics2d, circle.x, circle.y, circle.getR());
			else 
			AlogVisHelper.fillCircle(graphics2d, circle.x, circle.y, circle.getR());
		}
	
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(canvasWidth, canvasHeight);
		
	}
	}
}
