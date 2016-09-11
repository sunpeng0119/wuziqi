package sunpeng.com.gobang.api;


public interface Context {
	
	public  Object getValue(String key);
	
	public <T> T getObjectValue(String key);
	
	public void setValue(String key ,Object value);

}
