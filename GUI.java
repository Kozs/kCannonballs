package org.parabot.Kozs.kCannonballs;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GUI extends JFrame {
	Main m = new Main();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mine Iron", "Mine Coal", "Smelt Steel", "Make Cannonballs!"}));
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
				 if(comboBox.getSelectedItem().equals("Shrimp")) {
	                	
	                }
				 m.guiWait= false;
		            dispose();
					}});
		startBtn.setBounds(95, 116, 89, 23);
		contentPane.add(startBtn);
			}
		}
