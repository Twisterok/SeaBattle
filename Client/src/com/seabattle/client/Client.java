package com.seabattle.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.seabattle.classes.Account;
import com.seabattle.classes.CallbackConstants;
import com.seabattle.classes.SQLConstants;
import com.seabattle.interfaces.Client_int;
import com.seabattle.interfaces.Server_int;

public class Client extends JFrame{

	loadupPanel 	lup;
	LoginPanel		logPan;
	RegisterPanel 	regPan;
	Server_int  	server;
	Client_int		me;
	
	public Client(Server_int _server,Client_int _me)
	{
		super();
		
		this.server = _server;
		this.me		= _me;
		lup = new loadupPanel();
		logPan = new LoginPanel(server,me,this);
		regPan = new RegisterPanel(server,this);
		
		//TRYING TO GET IMAGE	  	
		try {
			byte[] accImg = server.getAvatar("asd");
			ByteArrayInputStream bais = new ByteArrayInputStream(accImg);
			BufferedImage bufImg = ImageIO.read(bais);
			StringBuffer sb = new StringBuffer();
			sb.append("valik.jpg");
			ImageIO.write(bufImg, "jpg" ,new File(sb.toString()));
		} catch (RemoteException e1) {
			System.out.print("REMOTE ERROR\n");
			e1.printStackTrace();
		} catch (IOException e) {

			System.out.print("IO ERROR\n");
			e.printStackTrace();
		}
		
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
			/*
			Thread.currentThread().sleep(1500);
			this.getContentPane().remove(logPan);
			RegisterPanel rg = new RegisterPanel();
			this.getContentPane().add(rg);
			this.setSize(640, 700);
			this.setLocationRelativeTo(null);
			this.getContentPane().setVisible(false);
			this.getContentPane().setVisible(true);
			*/
			
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		//this.setSize(1200, 700);
		//this.setLocationRelativeTo(null);
		//this.setSize(640, 700);
		//this.setLocationRelativeTo(null);		
	}
	
	
	
	
	public void ShowLoginPane()
	{
		this.getContentPane().removeAll();
		this.getContentPane().add(logPan);
		this.setSize(640, 480);
		this.setLocationRelativeTo(null);
		this.getContentPane().setVisible(false);
		this.getContentPane().setVisible(true);
	}
	
	public void ShowRegisterPane()
	{
		this.getContentPane().removeAll();
		this.getContentPane().add(regPan);
		this.setSize(640, 700);
		this.setLocationRelativeTo(null);
		this.getContentPane().setVisible(false);
		this.getContentPane().setVisible(true);
	}
}
