package com.seabattle.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding.Style;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import com.seabattle.classes.CallbackConstants;
import com.seabattle.interfaces.Client_int;
import com.seabattle.interfaces.Server_int;

public class LoginPanel extends JPanel	implements ActionListener
{
	JTextField 		login;
	JPasswordField	password;
	JLabel			login_lbl,password_lbl;
	JButton			login_but,register_but;
	BufferedImage	image;
	Server_int		server;
	Client_int		me;
	Client			parent;
	public LoginPanel(Server_int _server,Client_int _me,Client _parent) {
		
		parent	= _parent;
		server 	= _server;
		me		= _me;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setAlignmentX(CENTER_ALIGNMENT);
		
		try {
			image = ImageIO.read(new File("exmp.jpg"));
			         
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Sorry inner error. No such file: \"Seabattle_loadup.jpg\"");
		}
		
		
		login 		= new JTextField();
		login.setPreferredSize(new Dimension(175,25));
		password	= new JPasswordField();
		password.setPreferredSize(new Dimension(175,25));
		
		login_but	= new JButton("Sign in");
		login_but.setPreferredSize(new Dimension(100, 40));
		login_but.addActionListener(this);
		
		register_but= new JButton("Register");
		register_but.setPreferredSize(new Dimension(100, 40));
		register_but.addActionListener(this);
		
		login_lbl 	= new JLabel();//("Login");
		login_lbl.setPreferredSize(new Dimension(80,25));
		login_lbl.setForeground(Color.WHITE);
		login_lbl.setFont(new Font(null, Font.ITALIC, 18));
		
		password_lbl= new JLabel();//("Password");
		password_lbl.setPreferredSize(new Dimension(80,25));
		password_lbl.setForeground(Color.WHITE);
		password_lbl.setFont(new Font(null, Font.ITALIC, 18));
		
		login.setAlignmentX(CENTER_ALIGNMENT);
		login_lbl.setAlignmentX(CENTER_ALIGNMENT);
		password.setAlignmentX(CENTER_ALIGNMENT);
		password_lbl.setAlignmentX(CENTER_ALIGNMENT);
		login_but.setAlignmentX(CENTER_ALIGNMENT);
		register_but.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel loginPan = new JPanel();
		loginPan.add(login_lbl);
		loginPan.add(login);
		loginPan.setOpaque(false);
		
		JPanel passwordPan = new JPanel();
		passwordPan.add(password_lbl);
		passwordPan.add(password);
		passwordPan.setOpaque(false);
		
		JPanel buttonsPan = new JPanel();
		buttonsPan.add(login_but);
		buttonsPan.add(register_but);
		buttonsPan.setOpaque(false);
		
		
		JPanel mover = new JPanel();
		mover.setOpaque(false);		
		
		
		// ADDING MOVER. ADDING EMPTY JPANEL JUST TO MOVE THE OTHER FIELDS
		this.add(mover);
		mover = new JPanel();
		mover.setOpaque(false);
		
		this.add(mover);
		mover = new JPanel();
		mover.setOpaque(false);
		
		this.add(mover);
		mover = new JPanel();
		mover.setOpaque(false);
		
		this.add(mover);
		mover = new JPanel();
		mover.setOpaque(false);

		this.add(mover);
		mover = new JPanel();
		mover.setOpaque(false);

		//this.add(mover);
		//mover = new JPanel();
		//mover.setOpaque(false);
		
		
		// ADDING MAIN PANELS
		this.add(loginPan);
		this.add(passwordPan);
		this.add(buttonsPan);
		
		//ADDING MOVERS
		this.add(mover);
		mover = new JPanel();
		mover.setOpaque(false);
		
		this.add(mover);
		mover = new JPanel();
		mover.setOpaque(false);
		
		this.add(mover);
		
		
		
		
		
				
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0,getWidth(),getHeight(),null);
        }
	
	
	 public void actionPerformed(ActionEvent e) {
		 if(e.getSource() == login_but)
		 { 
			 if (login.getText().length() != 0	&&
				password.getPassword().length != 0)
			 {
				 String pass = CallbackConstants.toMD5_String(password.getPassword().toString());
				 String log = login.getText();
				 try {
					int callback = server.Sign_in(log, pass, me);
					switch (callback)
					{
					case CallbackConstants.AUTORISATION_ERROR:
						JOptionPane.showMessageDialog(this, "ERROR: Authorisation error");
						break;
					case CallbackConstants.USER_ONLINE:
						JOptionPane.showMessageDialog(this, "ERROR: User is already online");
						break;
					case CallbackConstants.GOOD:
						JOptionPane.showMessageDialog(this, "ERROR: LOGGED IN");
						
						if (server.Sign_out(log) == CallbackConstants.GOOD)
						{
							JOptionPane.showMessageDialog(this, "ERROR: LOGGED OUT");
						}
						else
						{
							JOptionPane.showMessageDialog(this, "ERROR: FAILED");
						}
						//...
						break;
					}
						
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(this, "ERROR: Remote invocation error"); 
					e1.printStackTrace();
				}
			 }
			 else
			 {
				 JOptionPane.showMessageDialog(this, "ERROR: Enter your login and password please.");
			 }
		 }
		 else if (e.getSource() == register_but)
		 {
			 parent.ShowRegisterPane();
		 }
	 }
}
