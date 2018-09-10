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
import VC.server.dao.LoginDAO;
import VC.server.dao.ShopDAO;
import VC.server.vo.ShopSrv;

public class ShopSrvImpl implements ShopSrv {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see VC.server.bz.ShopSrv#getAllGoods(VC.common.Message, java.net.Socket)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see VC.server.bz.ShopSrv#getMyGoods(VC.common.Message, java.net.Socket)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see VC.server.bz.ShopSrv#addshoppingcart(VC.common.Message, java.net.Socket)
	 */
	@Override
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

		for (int i = 0; i < goodname.size(); i++) {
			shopdao.addtoshoppingcart(username, goodname.get(i), goodnum.get(i));
		}

		res = true;
		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see VC.server.bz.ShopSrv#delMygood(VC.common.Message, java.net.Socket)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see VC.server.bz.ShopSrv#payforMygood(VC.common.Message, java.net.Socket)
	 */
	@Override
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

		res = shopdao.payforgoodincart(username, goodname, goodnumber);

		sendmsg.setRes(res);

		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
	
	public void getMyBalance(Message rcvmsg, Socket socket) throws SQLException, ClassNotFoundException, IOException {
		
		LoginDAO logindao = new LoginDAO();
		GoodsMessage sendmsg = new GoodsMessage();
		GoodsMessage rmsg = (GoodsMessage) rcvmsg;
		String username = rmsg.getID();
		String balance =String.valueOf(logindao.getBalance(username));
		
		sendmsg.setBalance(balance);
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
}
