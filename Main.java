package org.parabot.Kozs.kCannonballs;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;

@ScriptManifest(author = "",
category = Category.SMITHING,
description = "Makes Cannonballs",
name = "kCannonballs",
servers = { "PKHonor" },
version = 1)

public class Main extends Script {

	Constants c = new Constants();
	GUI g = new GUI();
	public static ArrayList<Strategy> strategies = new ArrayList<Strategy>();
	public boolean guiWait = true;
	
	public boolean onExecute() {
	     g.setVisible(true);
	     while (guiWait == true) {
	             sleep(500);
	        
	     }
	     if (c.mineIron == true) {
	 		strategies.add(new Anti());
		strategies.add(new MineIron());
		strategies.add(new BankingOre());
	    }
	    if (c.mineCoal == true) {
		strategies.add(new Anti());
	    }
	    if (c.smeltSteel == true) {
	    	strategies.add(new Anti());
	    	strategies.add(new BankSteel());
	    	strategies.add(new SmeltSteel());
	    }
	    if (c.makeCannonball == true) {
	    	strategies.add(new Anti());
	    	strategies.add(new MakeCannon());
	    	strategies.add(new BankCannon());
	    }
		provide(strategies);
		return true;
	}
	
	public void onFinish() {
		System.out.println("Thanks for using kCannonballs!");
	}
	public class GUI extends JFrame {
		private JPanel contentPane;

		/**
		 * Launch the application.
		 */
		public void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GUI frame = new GUI();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}

		/**
		 * Create the frame.
		 */
		public GUI() {
			setTitle("kCannonballer - V1.0");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 283, 178);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			final JComboBox comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mine Iron",
					"Mine Coal",
					"Smelt Steel",
					"Make Cannonballs!"}));
			comboBox.setBounds(79, 68, 120, 20);
			contentPane.add(comboBox);
			
			JLabel lblNewLabel = new JLabel("kCannonballs\r\n");
			lblNewLabel.setFont(new Font("Tekton Pro Ext", Font.BOLD | Font.ITALIC, 25));
			lblNewLabel.setBounds(55, 0, 181, 57);
			contentPane.add(lblNewLabel);
			
			JLabel lblNewLabel_1 = new JLabel("Pick a process.\r\n");
			lblNewLabel_1.setBounds(106, 45, 93, 14);
			contentPane.add(lblNewLabel_1);
			
			JButton startBtn = new JButton("Start!");
			startBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 @SuppressWarnings("unused")
					String chosen = comboBox.getSelectedItem().toString();
					 if(comboBox.getSelectedItem().equals("Mine Iron")) {
		                	c.mineIron = true;
		                }
					 if(comboBox.getSelectedItem().equals("Mine Coal")) {
		                	c.mineCoal = true;
		                }
					 if(comboBox.getSelectedItem().equals("Smelt Steel")) {
		                	c.smeltSteel = true;
		                }
					 if(comboBox.getSelectedItem().equals("Make Cannonballs!")) {
		                	c.makeCannonball = true;
		                }
					 guiWait= false;
			            g.dispose();
						}});
			startBtn.setBounds(95, 116, 89, 23);
			contentPane.add(startBtn);
				}
			}

}
