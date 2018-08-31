package VC.server.bz;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

		// test
		System.out.println(Goodslist.get(1).toString());
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
		Goodslist = shopdao.getAllGoods();

		// test
		System.out.println(Goodslist.get(1).toString());
		sendmsg.setGoodslist(Goodslist);

		// 发送消息部分
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
}
