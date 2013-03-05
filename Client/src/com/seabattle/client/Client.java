package com.seabattle.client;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class Client extends JFrame{

	loadupPanel lup;
	LoginPanel	logPan;
	public Client()
	{
		super();
		

		lup = new loadupPanel();
		logPan = new LoginPanel();
		
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
			this.getContentPane().setVisible(false);
			this.getContentPane().remove(lup);
			this.getContentPane().add(logPan);
			this.setLocationRelativeTo(null);
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
