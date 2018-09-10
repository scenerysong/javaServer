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

	//get all goods in the database and send the message to the client
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

	//get all of the goods a user has chosen for his shopping cart and send the message to the client
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
	
	// add a good to shopping cart and return the result of the operation
	public void addshoppingcart(Message rcvmsg, Socket socket) throws SQLException, IOException {

		GoodsMessage sendmsg = new GoodsMessage();
		String username = null;
		List<String> goodname;
		List<String> goodnum;
		
		boolean res = false;
		GoodsMessage rmsg = (GoodsMessage) rcvmsg;
		goodname = rmsg.getGoodsName();
		username = rmsg.getID();
		goodnum = rmsg.getNum();
		
		for(int i=0;i<goodname.size();i++) {
			shopdao.addtoshoppingcart(username, goodname.get(i), goodnum.get(i));
		}

		res = true;
		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	//delete a good in shopping cart and return the result of the operation
public void delMygood(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {
		
		GoodsMessage sendmsg = new GoodsMessage();
		String goodname = null;
		String username = null;
		boolean res = false;
		GoodsMessage rmsg = (GoodsMessage) rcvmsg;
		goodname = rmsg.getProductName();
		username = rmsg.getID();
		
		res = shopdao.deletegoodfromcart(username, goodname);

		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
//pay for a good in shopping cart and return the result of the operation

public void payforMygood(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {
	
	GoodsMessage sendmsg = new GoodsMessage();
	String username = null;
	boolean res = true;
	GoodsMessage rmsg = (GoodsMessage) rcvmsg;
	System.out.println("goodnumber = " + rmsg.getGoodsNum());
	username = rmsg.getID();
	
	
	
	List<Goods> Goodslist = new ArrayList<Goods>();
	Goodslist = shopdao.GetMyshoppingcart(username);
	System.out.println("number of list = " + Goodslist.size());
	for(int i = 0; i < Goodslist.size(); i++) {
		Goods gd = Goodslist.get(i);
		res &= shopdao.payforgoodincart(username,gd.getProductName(), gd.getGoodsNum());
	}
	
	
	sendmsg.setRes(res);

	ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
	oos.writeObject(sendmsg);
	oos.flush();
}
}
