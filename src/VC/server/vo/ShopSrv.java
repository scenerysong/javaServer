package VC.server.vo;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

import VC.common.Message;

/**
 * 
 * @author song
 *服务器商店功能实现,解析接受的数据,并与客户端直连
 */
public interface ShopSrv {

	/**
	 * 得到所有商品,并发送
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void getAllGoods(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	/**
	 * 解析得到用户,发送当前用户的购物车商品
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void getMyGoods(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	/**
	 * 解析得到用户,商品名称,添加到购物车中,发送是否成功的信息
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 */
	void addshoppingcart(Message rcvmsg, Socket socket) throws SQLException, IOException;

	/**
	 * 解析得到用户,商品名称,删除购物车中对应的商品,发送是否成功的信息
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void delMygood(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

	/**
	 * 根据减少的商品,扣除相应的余额
	 * @param rcvmsg
	 * @param socket
	 * @throws SQLException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	void payforMygood(Message rcvmsg, Socket socket) throws SQLException, IOException, ClassNotFoundException;

}