package sunpeng.com.gobang;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sunpeng.com.gobang.api.Context;
import valueObject.PiectConstant;
import valueObject.PiectVO;

public class PiectHelper {
	/**
	 * 根据鼠标点击时间获取棋子
	 * @param e
	 * @param color
	 * @return
	 */
	public static PiectVO getPiectVO(MouseEvent e,int color)
	{
		PiectVO piectVO = new PiectVO();
		int horizontal = e.getX();
		int vertical = e.getY();
		piectVO.setHorizontal(horizontal/40*40);
		piectVO.setVertical(vertical/40*40);
		piectVO.setColor(color);
		
		return piectVO;
	}
	/**
	 * 在画板上画棋子
	 * @param piectVO
	 * @param graphics
	 */
	public static void drawPiect(PiectVO piectVO ,Graphics graphics)
	{
		graphics.setColor(piectVO.getColor()==0?Color.white:Color.black);
		graphics.fillOval(piectVO.getHorizontal(), piectVO.getVertical(),
				PiectConstant.PIECTSIZE, PiectConstant.PIECTSIZE);
		
	}
	/**
	 * 判断棋子是不是已经存在
	 * @param existPiects
	 * @param piectVO
	 * @return
	 */
	public static boolean isExistPiect(List<PiectVO> existPiects,PiectVO piectVO)
	{
		if (existPiects ==null) {
			return false;
		}
		for (PiectVO exsitPiect : existPiects) {
			if (exsitPiect.equals(piectVO)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断是够有完成的五子棋的棋子，有的话放到context中
	 * @param piectVOs
	 * @return
	 */
	public static boolean haveFivePiects(List<PiectVO> piectVOs)
	{
		boolean result = false;
		boolean isClear = false;
		List<PiectVO> five = new ArrayList<>();
		Collections.sort(piectVOs);
		List<PiectVO>  temp;
		for (PiectVO piect : piectVOs) {
				temp = new ArrayList<>();
				if (five.size()==0) {
					five.add(piect);
					continue;
				}
				for (PiectVO piectVO : five) {
					if (piect.isNearPiect(piectVO)) {
						temp.add(piect);
						isClear = false;
						break;
					}else
					{
						isClear = true;
					}
				}
				if (isClear) {
					five.clear();
				}
				five.addAll(temp);
				if (five.size()==5) {
					result = true;
					Context context = GameContext.getGanmeContext();
					List<List<PiectVO>> completePiects = context.getObjectValue(PiectConstant.COMPLETEPIECT);
					if (null==completePiects) {
						completePiects=new ArrayList<>();
					}
					completePiects.add(five);
					context.setValue(PiectConstant.COMPLETEPIECT, completePiects);
				}
				
		}
		return result;
		
	}
	/**
	 * 已经有完成的五子棋，提示获胜者并且画出完成的五子棋
	 * @param graphics
	 */
	public static void winner(Graphics graphics)
	{
		Context context = GameContext.getGanmeContext();
		List<List<PiectVO>> piectsOfWinner = context.getObjectValue(PiectConstant.COMPLETEPIECT);
		
		graphics.setColor(Color.red);
		for (List<PiectVO> piects : piectsOfWinner) {
			Collections.sort(piects);
			PiectVO frist = piects.get(0);
			PiectVO last = piects.get(4);
			graphics.drawLine(frist.getHorizontal()+13, frist.getVertical()+13, 
					last.getHorizontal()+13,last.getVertical()+13);
		}
		int color = piectsOfWinner.get(0).get(0).getColor();
		String winner = color==0?"白色":"黑色";
		
		graphics.drawString(winner+"获胜！", 300, 300);
		
		context.setValue(PiectConstant.ISGAMEOVER, new Boolean(true));
	}
	
	

}
