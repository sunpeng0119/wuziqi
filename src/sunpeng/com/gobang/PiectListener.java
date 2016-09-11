package sunpeng.com.gobang;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sunpeng.com.gobang.api.Context;
import valueObject.PiectConstant;
import valueObject.PiectVO;

public class PiectListener extends MouseAdapter {
	private  Graphics graphics;
	private int horizontal;
	private int vertical;
	Checkerboard board;
	Context context;
	PiectHelper helper;
	public PiectListener(Graphics graphics,Checkerboard board)
	{
		this.context = GameContext.getGanmeContext();
		this.graphics = graphics;
		this.board = board;
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		Boolean isGameOver = context.getObjectValue(PiectConstant.ISGAMEOVER);
		if (null != isGameOver&&isGameOver)
		{
			return;
		}
		List<PiectVO> piects = context.getObjectValue(PiectConstant.GAME_PIECTS);
		if (null == piects) {
			piects = new ArrayList<PiectVO>();
		}
		PiectVO piectVO = PiectHelper.getPiectVO(e, piects.size()%2);
		if (PiectHelper.isExistPiect(piects, piectVO)) {
			return;
		}
		PiectHelper.drawPiect(piectVO, graphics);
		piects.add(piectVO);
		context.setValue(PiectConstant.GAME_PIECTS, piects);
		
		Map<String, List<PiectVO>> piectLine = context.getObjectValue(PiectConstant.PIECTLINE);
		if (null == piectLine) {
			piectLine = new HashMap<String,List<PiectVO>>();
			context.setValue(PiectConstant.PIECTLINE, piectLine);
		}
		for (String key : piectVO.getGobangKey()) {
			if (piectLine.get(key)==null) {
				List<PiectVO> temp = new ArrayList<>();
				temp.add(piectVO);
				piectLine.put(key, temp);
			}else {
				List<PiectVO> temp = piectLine.get(key);
				temp.add(piectVO);
				if (PiectHelper.haveFivePiects(temp)) {
					PiectHelper.winner(graphics);
				}
				
				
			}
		}
		

	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		super.mouseMoved(e);
	}

}
