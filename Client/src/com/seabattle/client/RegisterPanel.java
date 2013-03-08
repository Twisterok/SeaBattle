package com.seabattle.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.plaf.SliderUI;

public class RegisterPanel extends JPanel{
	
	BufferedImage		background;
	JTextField 			login;
	JPasswordField		password;
	JPasswordField		retype;
	JTextField 			Name;
	JTextField 			Surname;
	JComboBox<String>	Country;
	JComboBox<String>	Skill;
	JSpinner			Age;
	JFileChooser		Avatar;
	JRadioButton		Male,Female;
	
	JLabel		login_lbl;
	JLabel		password_lbl;
	JLabel		retype_lbl;
	JLabel		Name_lbl;
	JLabel		Surname_lbl;
	JLabel		Country_lbl;
	JLabel		Skill_lbl;
	JLabel		Age_lbl;
	JLabel		Avatar_lbl;
	JLabel		Gender_lbl;
	
	JPanel		login_pan;
	JPanel		password_pan;
	JPanel		retype_pan;
	JPanel		Name_pan;
	JPanel		Surname_pan;
	JPanel		Country_pan;
	JPanel		Skill_pan;
	JPanel		Age_pan;
	JPanel		Avatar_pan;
	JPanel		Gender_pan;
	JPanel		Buts;
	JButton		Register;
	JButton		Cancel;
	
	public RegisterPanel() {
		
		try {
			background = ImageIO.read(new File("REG.jpg"));
		} catch (IOException e) {
			System.out.print("NO REGISTRATION BACKGROUND FILE");
		}
		
		login_lbl = new JLabel("Login *");				login_lbl.setPreferredSize(new Dimension(150,20));
		login_lbl.setForeground(Color.WHITE);
		password_lbl = new JLabel("Password *");		password_lbl.setPreferredSize(new Dimension(150,20));
		password_lbl.setForeground(Color.WHITE);
		retype_lbl = new JLabel("Retype password *");	retype_lbl.setPreferredSize(new Dimension(150,20));
		retype_lbl.setForeground(Color.WHITE);
		Name_lbl = new JLabel("Name");					Name_lbl.setPreferredSize(new Dimension(150,20));
		Name_lbl.setForeground(Color.WHITE);
		Surname_lbl = new JLabel("Surname");			Surname_lbl.setPreferredSize(new Dimension(150,20));
		Surname_lbl.setForeground(Color.WHITE);
		Country_lbl = new JLabel("Country");			Country_lbl.setPreferredSize(new Dimension(150,20));
		Country_lbl.setForeground(Color.WHITE);
		Skill_lbl = new JLabel("Skill level");			Skill_lbl.setPreferredSize(new Dimension(150,20));
		Skill_lbl.setForeground(Color.WHITE);
		Age_lbl = new JLabel("Age");					Age_lbl.setPreferredSize(new Dimension(150,20));
		Age_lbl.setForeground(Color.WHITE);
		Avatar_lbl = new JLabel("Avatar");				
		Avatar_lbl.setForeground(Color.WHITE);
		Gender_lbl = new JLabel("Gender");				Gender_lbl.setPreferredSize(new Dimension(150,20));
		Gender_lbl.setForeground(Color.WHITE);
		
		
		login = new JTextField();						login.setPreferredSize(new Dimension(175,20));
		password = new JPasswordField();				password.setPreferredSize(new Dimension(175,20));
		retype = new JPasswordField();					retype.setPreferredSize(new Dimension(175,20));
		Name = new JTextField();						Name.setPreferredSize(new Dimension(175,20));
		Surname = new JTextField();						Surname.setPreferredSize(new Dimension(175,20));
		Country = new JComboBox<String>();				Country.setPreferredSize(new Dimension(175,20));
		Skill = new JComboBox<String>();				Skill.setPreferredSize(new Dimension(175,20));
		Age = new JSpinner();							Age.setPreferredSize(new Dimension(175,20));
		Avatar = new JFileChooser();					Avatar.setControlButtonsAreShown(false);
														Avatar.setPreferredSize(new Dimension(400, 250));
		Male = new JRadioButton("Male");	
		Male.setOpaque(false);
		Male.setForeground(Color.WHITE);
		Female = new JRadioButton("Female");
		Female.setOpaque(false);
		Female.setForeground(Color.WHITE);
		login_pan 	= new JPanel();		login_pan.setAlignmentX(CENTER_ALIGNMENT);
										login_pan.setOpaque(false);
		password_pan= new JPanel();		password_pan.setAlignmentX(CENTER_ALIGNMENT);
										password_pan.setOpaque(false);
		retype_pan 	= new JPanel();		retype_pan.setAlignmentX(CENTER_ALIGNMENT);
										retype_pan.setOpaque(false);
		Name_pan 	= new JPanel();		Name_pan.setAlignmentX(CENTER_ALIGNMENT);
										Name_pan.setOpaque(false);
		Surname_pan = new JPanel();		Surname_pan.setAlignmentX(CENTER_ALIGNMENT);
										Surname_pan.setOpaque(false);
		Country_pan = new JPanel();		Country_pan.setAlignmentX(CENTER_ALIGNMENT);
										Country_pan.setOpaque(false);
		Skill_pan 	= new JPanel();		Skill_pan.setAlignmentX(CENTER_ALIGNMENT);
										Skill_pan.setOpaque(false);
		Age_pan	 	= new JPanel();		Age_pan.setAlignmentX(CENTER_ALIGNMENT);
										Age_pan.setOpaque(false);
		Avatar_pan 	= new JPanel();		Avatar_pan.setAlignmentX(CENTER_ALIGNMENT);
										Avatar_pan.setOpaque(false);
		Gender_pan 	= new JPanel();		Gender_pan.setAlignmentX(CENTER_ALIGNMENT);
										Gender_pan.setOpaque(false);
		Buts		= new JPanel();		Buts.setAlignmentX(CENTER_ALIGNMENT);
										Buts.setOpaque(false);
		
		Register = new JButton("Register");
		Cancel  = new JButton("Cancel");
		
		login_pan.add(login_lbl);		login_pan.add(login);
		password_pan.add(password_lbl);	password_pan.add(password);
		retype_pan.add(retype_lbl);		retype_pan.add(retype);
		Name_pan.add(Name_lbl);			Name_pan.add(Name);
		Surname_pan.add(Surname_lbl);	Surname_pan.add(Surname);
		Country_pan.add(Country_lbl);	Country_pan.add(Country);
		Skill_pan.add(Skill_lbl);		Skill_pan.add(Skill);
		Age_pan.add(Age_lbl);			Age_pan.add(Age);	
		Avatar_pan.add(Avatar_lbl);		Avatar_pan.add(Avatar);
		ButtonGroup Gender_grp	= new ButtonGroup();
		Gender_grp.add(Male);
		Gender_grp.add(Female);
		Gender_pan.add(Gender_lbl);		Gender_pan.add(Male);	Gender_pan.add(Female);
		Buts.add(Register);
		Buts.add(Cancel);
		
		
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
		
		this.add(login_pan);
		this.add(password_pan);
		this.add(retype_pan);
		this.add(Name_pan);
		this.add(Surname_pan);
		this.add(Country_pan);
		this.add(Skill_pan);
		this.add(Age_pan);
		this.add(Avatar_pan);
		this.add(Gender_pan);
		this.add(Buts);
		
		this.add(mover);
		mover = new JPanel();
		mover.setOpaque(false);
		
		this.add(mover);
		mover = new JPanel();
		mover.setOpaque(false);

		this.add(mover);
		mover = new JPanel();
		mover.setOpaque(false);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0,getWidth(),getHeight(),null);
        }
}
