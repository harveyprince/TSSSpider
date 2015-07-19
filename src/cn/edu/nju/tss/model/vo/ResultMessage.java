package cn.edu.nju.tss.model.vo;

public class ResultMessage {
	/**
	 * 成功与否
	 */
	private boolean result;
	/**
	 * 标注
	 */
	private String comment;
	/**
	 * 附件｛携带的实体｝
	 */
	private Object obj;
	public boolean isResult() {
		return result;
	}
	public String getComment() {
		return comment;
	}
	public Object getObj() {
		return obj;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
