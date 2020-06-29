package com.kh.springProject.member.model.exception;

//public class MemberException extends Exception{
public class MemberException extends RuntimeException{
	public MemberException(String msg) {
		super(msg);
	}
	
}
