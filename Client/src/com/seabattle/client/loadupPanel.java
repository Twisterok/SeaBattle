package com.seabattle.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.text.BoxView;

import org.omg.CORBA.OMGVMCID;

public class loadupPanel extends JPanel
{
	BufferedImage	image;
	JProgressBar	progressBar;
	public loadupPanel() 
	{
		
		
		try {
			image = ImageIO.read(new File("Seabattle_loadup_1.jpg"));
			         
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Sorry inner error. No such file: \"Seabattle_loadup.jpg\"");
		}
		
		
		
		progressBar = new JProgressBar(0,100);
		progressBar.setValue(0);
		progressBar.setIndeterminate(true);
		progressBar.setPreferredSize(new Dimension(200,25));
		progressBar.setAlignmentX(CENTER_ALIGNMENT);
		JPanel southPan = new JPanel();
		southPan.add(progressBar);
		
		southPan.setOpaque(false);
		
		
		
		this.setLayout(new BorderLayout());
		this.add(southPan,BorderLayout.SOUTH);
		
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0,getWidth(),getHeight(),null);
        }
}
