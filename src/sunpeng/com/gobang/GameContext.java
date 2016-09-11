package sunpeng.com.gobang;

import java.util.HashMap;
import java.util.Map;

import sunpeng.com.gobang.api.Context;

public class GameContext implements Context{
	
	Map<String, Object> content ;
	
	private static GameContext context;
	
	private GameContext() {
		content = new HashMap<>();
	}

	public static GameContext getGanmeContext()
	{
		if (null ==context) {
			context = new GameContext();
		}
		
		return context;
		
	}
	@Override
	public Object getValue(String key) {
		return content.get(key);
	}

	@Override
	public <T> T getObjectValue(String key) {
		Object result = content.get(key);
		return (T)result;
	}

	@Override
	public void setValue(String key, Object value) {
		content.put(key, value);
		
	}
	
	

}
