package com.seabattle.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.SliderUI;

import com.seabattle.classes.CallbackConstants;
import com.seabattle.classes.SQLConstants;
import com.seabattle.interfaces.Server_int;

public class RegisterPanel extends JPanel implements ActionListener{
	
	Server_int server;
	Client parent;
	public static String[] countries = 
		{
			"Select country",
			"Russia",
			"USA",
			"UK",
			"France",
			"Germany",
			"China",
			"Netherlands",
			"Portugal",
			"Spain",
			"Italy",
			"Sweden"
		};
	
	public static String[] skills = 
		{
			"Low",
			"Medium",
			"High",
			"Pro"
		};
	
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
	
	public RegisterPanel(Server_int _server,Client _parent) {
		
		this.parent	= _parent;
		this.server = _server;
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
		Country = new JComboBox<String>(countries);		Country.setPreferredSize(new Dimension(175,20));
		Skill = new JComboBox<String>(skills);			Skill.setPreferredSize(new Dimension(175,20));
		Age = new JSpinner();							Age.setPreferredSize(new Dimension(175,20));
		Avatar = new JFileChooser();					Avatar.setControlButtonsAreShown(false);
														Avatar.setPreferredSize(new Dimension(400, 250));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & GIF & PNG Images", "jpg", "gif","png");
		Avatar.setFileFilter(filter);
		Male = new JRadioButton("Male");	
		Male.setOpaque(false);
		Male.setSelected(true);
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
		Register.addActionListener(this);
		Register.setPreferredSize(new Dimension(100, 40));
		Cancel  = new JButton("Cancel");
		Cancel.addActionListener(this);
		Cancel.setPreferredSize(new Dimension(100, 40));
		
		
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

	@Override
	public void actionPerformed(ActionEvent e) {

		
		/*
		 * 	BufferedImage		background;
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
		 */
		if (e.getSource() ==Register)
		{
			if (login.getText().length() == 0)
			{
				JOptionPane.showMessageDialog(this, "ERROR: Enter the login");
			}
			else if (password.getPassword().length == 0)
			{
				JOptionPane.showMessageDialog(this, "ERROR: Enter the password");
			}
			else if (retype.getPassword().length == 0)
			{
				JOptionPane.showMessageDialog(this, "ERROR: Retype your password please.");
			}
			else if (!Arrays.equals(password.getPassword(), retype.getPassword()))
			{
				JOptionPane.showMessageDialog(this, "ERROR: Fields \"Password\" and \"Retype password\" fields are different");
			}
			else
			{
				String pass = CallbackConstants.toMD5_String(password.getPassword().toString());
				String log 	= login.getText();
				
				try {
					int callback = server.Sign_up(log, pass);
					switch (callback)
					{
					case CallbackConstants.USER_EXISTS:
						JOptionPane.showMessageDialog(this, "User with such name already exists");
						break;
					case CallbackConstants.BAD:
						JOptionPane.showMessageDialog(this, "Registration error");
						break;
					case CallbackConstants.GOOD:
						if (Name.getText().length() !=0)
							server.Change_name(log, Name.getText());
						if (Surname.getText().length() !=0)
							server.Change_surname(log, Surname.getText());
						if (!Country.getSelectedItem().toString().equals("Select country"))
							server.Change_country(log, Country.getSelectedItem().toString());
						
						server.Change_skill(log, Skill_Convertion(Skill.getSelectedItem().toString()));
						
						if ((Integer)Age.getValue() != 0)
						{
							server.Change_age(log,(Integer) Age.getValue());
						}
						
						
						File imgFile = Avatar.getSelectedFile();
						if (imgFile.exists())
						{
							System.out.print("file exists\n");
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							String type;
							BufferedImage img = ImageIO.read(imgFile);
							int index = imgFile.getName().lastIndexOf('.');
							type = imgFile.getName().substring(index+1);	
							ImageIO.write(img, type, baos );
							baos.flush();
							byte[] imageInByte = baos.toByteArray();
							baos.close();
							server.Change_avatar(log,imageInByte);
							server.Set_type(log, Type_Convertion(type));
						}
						
						if (Male.isSelected())
							server.Change_gender(log, SQLConstants.MALE);
						else
							server.Change_gender(log, SQLConstants.FEMALE);
						
						JOptionPane.showMessageDialog(this, "A new user has been registered");
						break;
					}
					parent.ShowLoginPane();
				} catch (RemoteException e1) {
					JOptionPane.showMessageDialog(this, "ERROR: Remote invocation error"); 
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		else if (e.getSource() == Cancel)
		{
			this.login.setText("");
			this.password.setText("");
			this.retype.setText("");
			this.Name.setText("");
			this.Surname.setText("");
			this.Country.setSelectedIndex(0);
			this.Skill.setSelectedIndex(0);
			this.Age.setValue(0);
			this.Avatar.setSelectedFile(new File(""));
			this.Male.setSelected(true);
			this.Female.setSelected(false);
			parent.ShowLoginPane();
		}
		
	}
	
	private Integer Skill_Convertion(String skill)
	{
		if (skill.equals("Low"))
		{
			return SQLConstants.LOW;
		}
		else if (skill.equals("Medium"))
		{
			return SQLConstants.MEDIUM;
		}
		else if (skill.equals("High"))
		{
			return SQLConstants.HIGH;
		}
		else if (skill.equals("Pro"))
		{
			return SQLConstants.PRO;
		}
		else
			return SQLConstants.LOW;
	}
	
	private Integer Type_Convertion(String type)
	{
		if (type.equals("jpg"))
			return SQLConstants.JPG;
		else if (type.equals("png"))
			return SQLConstants.PNG;
		else if (type.equals("bmp"))
			return SQLConstants.BMP;
		else
			return SQLConstants.NOTYPE;
	}
}
