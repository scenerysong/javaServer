package VC.server.bz;

import VC.server.vo.StuSrv;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.Book;
import VC.common.BookMessage;
import VC.common.Message;
import VC.server.dao.LibraryDAO;
import VC.server.dao.StuDAO;
import VC.server.vo.LibrarySrv;

public class StuSrvImpl implements StuSrv{

	public StuDAO studao;
	
	public StuSrvImpl() {
		
		try {
			studao = new StuDAO();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getInfo(Message rcvmsg, Socket socket) {
		
	}
	
	public void updateInfo(Message rcvmsg, Socket socket) {
		
	}
}
