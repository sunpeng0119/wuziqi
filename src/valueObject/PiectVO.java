package valueObject;

import java.util.ArrayList;
import java.util.List;

public class PiectVO implements Comparable<PiectVO>{

	//横坐标
	int horizontal;
	//纵坐标
	int vertical;
	//颜色
	int color;
	
	
	public int getHorizontal() {
		return horizontal;
	}
	public void setHorizontal(int horizontal) {
		this.horizontal = horizontal;
	}
	public int getVertical() {
		return vertical;
	}
	public void setVertical(int vertical) {
		this.vertical = vertical;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof PiectVO)) {
			return false;
		}
		PiectVO piectVO = (PiectVO)object;
		return this.horizontal==piectVO.getHorizontal()&&
				this.vertical==piectVO.getVertical();
	}
	/**
	 * 获取一个棋子可能完成的五子棋KEY值
	 * @return
	 */
	public List<String> getGobangKey()
	{
		List<String> result = new ArrayList<String>();
		result.add("y="+this.getVertical()+"color:"+this.color);
		result.add("x="+this.getHorizontal()+"color:"+this.color);
		result.add("y=x+"+(this.getVertical()-this.getHorizontal())+"color:"+this.color);
		result.add("y=-x+"+(this.getVertical()+this.getHorizontal())+"color:"+this.color);
		return result;
	}
	
	/**
	 * 判断两个棋子是否是临近棋子
	 * @param piect
	 * @return
	 */
	public boolean isNearPiect(PiectVO piect)
	{
		if (Math.abs(this.horizontal-piect.getHorizontal())==PiectConstant.PIECTSIZE&&Math.abs(this.vertical-piect.getVertical())==PiectConstant.PIECTSIZE) {
			return true;
		}
		if (this.horizontal==piect.getHorizontal()&&Math.abs(this.vertical-piect.getVertical())==PiectConstant.PIECTSIZE) {
			return true;
		}
		if (this.vertical==piect.getVertical()&&Math.abs(this.horizontal-piect.getHorizontal())==PiectConstant.PIECTSIZE) {
			return true;
		}
		return false;
	}
	@Override
	public int compareTo(PiectVO o) {
		if (this.horizontal!=o.getHorizontal()) {
			return horizontal - o.getHorizontal();
		}else {
			return this.vertical-o.getVertical();
		}
	}

}
