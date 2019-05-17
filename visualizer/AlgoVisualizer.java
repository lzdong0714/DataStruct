package visualizer;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {
	
	//创建自己的数据
	private Object date;
	private AlgoFrame frame;
	
	public AlgoVisualizer(int sceneWidth,int sceneHeight) {
		//初始化数据
		//TODO初始化数据
		
		
		//初始化视图
		EventQueue.invokeLater(()->{
			frame=new AlgoFrame("welcome",sceneWidth,sceneHeight);
			
			//
			 frame.addKeyListener(new AlgoKeyListener());
			 frame.addMouseListener(new AlgoMouseListener());
			 
			 new Thread(()-> {
				 run();
			 }).start();
		});
		
	}
	
	//键盘鼠标的输入控制
	private class AlgoKeyListener extends KeyAdapter{}
	private class AlgoMouseListener extends MouseAdapter{}
	//控制动画逻辑
	private void run() {
		
	}
	
	public static void main(String args[]) {
		int sceneWidth=800;
		int sceneHeight=800;
		AlgoVisualizer visualizer=new AlgoVisualizer(sceneWidth, sceneHeight);
		
	}


}
