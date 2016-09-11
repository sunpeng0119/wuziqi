package sunpeng.com.gobang;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JFrame;
import javax.swing.JPanel;

import valueObject.PiectConstant;

public class Checkerboard extends JFrame{
	/**
	 * 五子棋棋盘
	 */
	private static final long serialVersionUID = 7095305183986258299L;
	public Graphics graphics;
	int piectNum;
//	public static Checkerboard board;
	
	public Checkerboard()
	{
		this.initUI();
		piectNum = 0;
		
	}
	public int addPiectNum()
	{
		piectNum++;
		return piectNum%2;
	}
	
	void initUI()
	{
		 /* 
         * 设置棋盘属性 
         */  
        this.setTitle("五子棋");  
        this.setSize(new Dimension(620, 620));  
        this.setResizable(false);  
        this.setDefaultCloseOperation(3);  
        this.setLocationRelativeTo(null);  
        this.setLayout(null);
        
        JPanel jp = new JPanel()
        {
        	@Override
        	public void paint(Graphics g) {
        		g.setColor(Color.BLACK); 
        		super.paint(g);
        		//画15行
        		for (int i = 0; i < 15; i++) {
        			  g.drawLine(20, 20 + i * PiectConstant.SPACINGVALUE, 20  
                              + (PiectConstant.COLUMN - 1) * PiectConstant.SPACINGVALUE,  
                              20 + i * PiectConstant.SPACINGVALUE); 
				}
        		 // 画15列  
                for (int i = 0; i < 15; i++) {  
                    g.drawLine(20 + i * PiectConstant.SPACINGVALUE, 20, 20 + i  
                            * PiectConstant.SPACINGVALUE, 20  
                            + (PiectConstant.COLUMN  - 1) * PiectConstant.SPACINGVALUE);  
                }  
                
                g.setColor(Color.BLACK);  
                g.fillOval(133, 133, 15, 15);  
                g.fillOval(293, 133, 15, 15);  
                g.fillOval(453, 133, 15, 15);  
                g.fillOval(133, 293, 15, 15);  
                g.fillOval(293, 293, 15, 15);  
                g.fillOval(453, 293, 15, 15);  
                g.fillOval(133, 453, 15, 15);  
                g.fillOval(293, 453, 15, 15);  
                g.fillOval(453, 453, 15, 15);
        	}
        };
        jp.setBackground(new Color(209, 167, 78));  
        jp.setBounds(10, 10, 602, 602); 
        this.add(jp); 
        
      
        this.setVisible(true);  
        graphics = jp.getGraphics();
        PiectListener piectListener = new PiectListener(graphics,this);
        jp.addMouseListener(piectListener);
		
	}

}
