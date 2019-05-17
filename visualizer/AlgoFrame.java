package visualizer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

import algorithm.AlogVisHelper;
import algorithm.Circle;


public class AlgoFrame extends JFrame{
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

//TODO:�����Լ�������
private Object data;
public void render(Object data) {
	this.data=data;
	repaint();
}

private class  AlgoCanvas extends JPanel{
	public AlgoCanvas() {
		// TODO Auto-generated constructor stub
		super(true);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D graphics2d=(Graphics2D)g;
		
		//�����
		RenderingHints hints=new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.addRenderingHints(hints);
		AlogVisHelper.setStrokeWidth(graphics2d, 5);
			
		//�������


	}
		
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(canvasWidth, canvasHeight);
		
	}
  }
}
