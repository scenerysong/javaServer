package VC.common;

public interface MessageType {
	//消息类型分为三类：命令CMD---数据DAT---结果RST
	
	//General
	String GENERAL_BOOK = "GENERAL_BOOK";
	
	//Command
	String CMD_QUY_BOOK_BOOKNAME = "CMD_QUY_BOOK_BOOKNAME";
	String CMD_JUDGE_LOGIN = "CMD_JUDGE_LOGIN";
	String CMD_GET_ALL_GOODS = "CMD_GET_ALL_GOODS";
	
	//Data
	String DAT_QUY_BOOK_BOOKNAME = "DAT_QUY_BOOK_BOOKNAME";
	
	//Result
	String RST_SUCCESS = "RST_SUCCESS";
	String RST_FAILURE = "RST_FAILURE";
	
}
