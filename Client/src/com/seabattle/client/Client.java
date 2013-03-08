package com.seabattle.client;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import com.seabattle.interfaces.Server_int;

public class Client extends JFrame{

	loadupPanel lup;
	LoginPanel	logPan;
	Server_int  server;
	public Client(Server_int _server)
	{
		super();
		
		this.server = _server;
		lup = new loadupPanel();
		logPan = new LoginPanel(server);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Seabattle");
		this.setResizable(false);
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(new BorderLayout());
		
		this.getContentPane().add(lup,BorderLayout.CENTER);
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		
		this.setVisible(true);
		try {
			Thread.currentThread().sleep(1500);

			this.getContentPane().remove(lup);
			this.getContentPane().add(logPan);
			this.setLocationRelativeTo(null);
			this.getContentPane().setVisible(false);
			this.getContentPane().setVisible(true);
			
			/*	REGISTER PANEL*/
			Thread.currentThread().sleep(1500);
			this.getContentPane().remove(logPan);
			RegisterPanel rg = new RegisterPanel();
			this.getContentPane().add(rg);
			this.setSize(640, 700);
			this.setLocationRelativeTo(null);
			this.getContentPane().setVisible(false);
			this.getContentPane().setVisible(true);
			
			
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		//this.setSize(1200, 700);
		//this.setLocationRelativeTo(null);
		//this.setSize(640, 700);
		//this.setLocationRelativeTo(null);		
	}
}
