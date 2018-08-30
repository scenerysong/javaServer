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

	public ShopSrvImpl() {
		
	}
	public void getAllGoods(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException {
		
		ShopDAO shopdao = new ShopDAO();
		List<Goods> Goodslist = new ArrayList<Goods>();
		GoodsMessage sendmsg = new GoodsMessage();
		
		//调用dao里的方法
		Goodslist = shopdao.getAllGoods();
		
		//test
		System.out.println(Goodslist.get(1).toString());
		sendmsg.setGoodslist(Goodslist);
		
		//发送消息部分
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(sendmsg);
		oos.flush();
	}
}
