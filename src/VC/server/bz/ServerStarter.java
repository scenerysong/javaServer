package VC.server.bz;

/**
 * 服务器端GUI设计
 *
 */
import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import VC.server.bz.MultiServerImpl;


public class ServerStarter extends Application{
	private BorderPane pane = new BorderPane();	
	private Button sta = new Button("启动服务器");
	private Button sto = new Button("关闭服务器");
	private Stage start = new Stage();
	ListView<String> list = new ListView<String>();
	static ObservableList<String> items =FXCollections.observableArrayList ();

	MultiServerImpl multiserver;
	
	public void start(Stage stage) {
		pane.setTop(setText());
		pane.setBottom(setButton());
		
		sta.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				multiserver = new MultiServerImpl();
				multiserver.start();
			}
		});
		
		sto.setOnAction(new EventHandler<ActionEvent>(){
			public void handle(ActionEvent e) {
				start.close();
				multiserver.setClosed(true);
			}
			
		});
		
		
		Scene scene = new Scene(pane,350,250);
		start.setTitle("虚拟校园系统1.0-服务器系统");
		start.setScene(scene);
		start.setResizable(false);
		start.show();

	}
	
	public VBox setText() {
		VBox x = new VBox();
		x.setPadding(new Insets(15,15,15,15));
		x.setAlignment(Pos.CENTER);
		
		list.setPrefWidth(100);
		list.setPrefHeight(150);
		
		//放在更新函数里
		int size = 	items.size();
		x.getChildren().add(list);
		list.setItems(items);
		list.refresh();
//		items.remove(1);
		items.add(size, "用户登录情况");
//		items.clear();

		return x;
	}
	
	public static void adduser(String username) {
		items.add(items.size(), username);
	}
	
	public HBox setButton() {
		HBox x = new HBox();
		x.setPadding(new Insets(15,15,15,15));
		x.setAlignment(Pos.CENTER);
		x.setSpacing(20);

		x.getChildren().add(sta);
		x.getChildren().add(sto);
		
		return x;
	}
	
	public static void main(String[] args) {
		Application.launch(ServerStarter.class,args);
	}
}
