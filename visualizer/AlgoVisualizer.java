package visualizer;

import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;

public class AlgoVisualizer {
	
	//�����Լ�������
	private Object date;
	private AlgoFrame frame;
	
	public AlgoVisualizer(int sceneWidth,int sceneHeight) {
		//��ʼ������
		//TODO��ʼ������
		
		
		//��ʼ����ͼ
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
	
	//���������������
	private class AlgoKeyListener extends KeyAdapter{}
	private class AlgoMouseListener extends MouseAdapter{}
	//���ƶ����߼�
	private void run() {
		
	}
	
	public static void main(String args[]) {
		int sceneWidth=800;
		int sceneHeight=800;
		AlgoVisualizer visualizer=new AlgoVisualizer(sceneWidth, sceneHeight);
		
	}


}
