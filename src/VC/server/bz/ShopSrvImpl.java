package VC.server.bz;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import VC.common.CourseMessage;
import VC.common.Goods;
import VC.common.GoodsMessage;
import VC.common.Message;
import VC.server.dao.ShopDAO;

public class ShopSrvImpl {

	public ShopDAO shopdao;
	
	public ShopSrvImpl() {

		try {
			shopdao = new ShopDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getAllGoods(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {

		
		List<Goods> Goodslist = new ArrayList<Goods>();
		GoodsMessage sendmsg = new GoodsMessage();

		// 调用dao里的方法
		Goodslist = shopdao.getAllGoods();

		sendmsg.setGoodslist(Goodslist);

		// 发送消息部分
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}

	public void getMyGoods(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {

		List<Goods> Goodslist = new ArrayList<Goods>();
		GoodsMessage sendmsg = new GoodsMessage();
		String username = rcvmsg.getID();

		// 调用dao里的方法
		Goodslist = shopdao.GetMyshoppingcart(username);

		sendmsg.setGoodslist(Goodslist);

		// 发送消息部分
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	public void addshoppingcart(Message rcvmsg, Socket socket) throws SQLException, IOException {

		GoodsMessage sendmsg = new GoodsMessage();
		String goodname = null;
		String username = null;
		String number = null;
		
		boolean res = false;
		GoodsMessage rmsg = (GoodsMessage) rcvmsg;
		goodname = rmsg.getProductName();
		username = rmsg.getID();
		number = rmsg.getGoodsNum();
		
		res = shopdao.addtoshoppingcart(username, goodname, number);

		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
public void delMygood(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {
		
		GoodsMessage sendmsg = new GoodsMessage();
		String goodname = null;
		String username = null;
		boolean res = false;
		GoodsMessage rmsg = (GoodsMessage) rcvmsg;
		goodname = rmsg.getProductName();
		username = rmsg.getID();
		
		System.out.println("kai shi tui ke step2");
		res = shopdao.deletegoodfromcart(username, goodname);

		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
public void payforMygood(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {
	
	GoodsMessage sendmsg = new GoodsMessage();
	String goodname = null;
	String username = null;
	String goodnumber = null;
	boolean res = false;
	GoodsMessage rmsg = (GoodsMessage) rcvmsg;
	goodname = rmsg.getProductName();
	goodnumber = rmsg.getGoodsNum();
	username = rmsg.getID();
	
	System.out.println("kai shi tui ke step2");
	res = shopdao.payforgoodincart(username, goodname, goodnumber);
	

	sendmsg.setRes(res);

	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	oos.writeObject(sendmsg);
	oos.flush();
}
}
