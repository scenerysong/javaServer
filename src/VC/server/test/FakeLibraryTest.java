package VC.server.test;

import java.io.IOException;
import java.sql.SQLException;

import VC.server.bz.ServerSrvImpl;

public class FakeLibraryTest {

	public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {
		
		ServerSrvImpl serversrvimpl = new ServerSrvImpl();
		serversrvimpl.run();
	}
}
