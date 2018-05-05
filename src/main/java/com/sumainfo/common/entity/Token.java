package com.sumainfo.common.entity;

public class Token {
	private int tokenId;
	private int userId;
	private String tokenMsg;
	private String otherIdetify;
	private int count;
	
	
	public Token(){
		
	}
	
	public Token(int userId,String tokenMsg){
		this.userId = userId;
		this.tokenMsg = tokenMsg;
	}
	
	public Token(String tokenMsg,String otherIdetify){
		this.tokenMsg = tokenMsg;
		this.otherIdetify = otherIdetify;
	}
	
	public int getTokenId() {
		return tokenId;
	}
	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTokenMsg() {
		return tokenMsg;
	}
	public void setTokenMsg(String tokenMsg) {
		this.tokenMsg = tokenMsg;
	}
	public String getOtherIdetify() {
		return otherIdetify;
	}
	public void setOtherIdetify(String otherIdetify) {
		this.otherIdetify = otherIdetify;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}


}
