package com.dreamstone.graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.dreamstone.core.Dystopia;
import com.dreamstone.core.Start;
import com.dreamstone.file.DirectoryMaster;
import com.dreamstone.file.FileSystem;
import com.dreamstone.util.DystopiaLogger;
import com.dreamstone.world.TempWorldGen;
import com.dreamstone.world.data.LoadManager;

public class TempLoginFrame extends JFrame {

	private static final long serialVersionUID = 595647001496794694L;
	private JPanel mainPanel = new JPanel();
	private JPanel titlePanel = new JPanel();
	private JPanel inputPanel = new JPanel();
	private JPanel usernamePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private JLabel title = new JLabel("Dystopia");
	
	private JLabel username = new JLabel("Username: ");
	private JTextField usernameField = new JTextField(25);
	private JButton createNewWorld = new JButton("Create New World");
	private JButton loadExistingWorld = new JButton("Load Existing World");
	
	private JLabel worldName = new JLabel("World Name: ");
	private JTextField worldNameField = new JTextField(25);
	private JLabel selectWorld = new JLabel("Select your World:");
	private JComboBox<String> worldDropdown = new JComboBox<>();
	private JButton begin = new JButton("Enter your Dystopia!");
	
	private DocumentListener usernameFieldListener = new DocumentListener() {
		public void changedUpdate(DocumentEvent e) {}

		@Override
		public void insertUpdate(DocumentEvent e) {
			if (e.getDocument().getLength() > 0 && (!createNewWorld.isEnabled() || !loadExistingWorld.isEnabled())) {
				createNewWorld.setEnabled(true);
				loadExistingWorld.setEnabled(true);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			if (e.getDocument().getLength() <= 0 && (createNewWorld.isEnabled() || loadExistingWorld.isEnabled())) {
				createNewWorld.setEnabled(false);
				loadExistingWorld.setEnabled(false);
			}
		}
	};
	
	private DocumentListener worldNameFieldListener = new DocumentListener() {
		public void changedUpdate(DocumentEvent e) {}

		@Override
		public void insertUpdate(DocumentEvent e) {
			if (e.getDocument().getLength() > 0 && !begin.isEnabled()) {
				begin.setEnabled(true);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			if (e.getDocument().getLength() <= 0 && begin.isEnabled()) {
				begin.setEnabled(false);
			}
		}
	};
	
	public TempLoginFrame() {
		this.setTitle("Dystopia World Creator!!!!");
		
		this.startupPanel();
		this.add(mainPanel);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true); 
	}
	
	public void startupPanel() {
		titlePanel.setBorder(BorderFactory.createEtchedBorder());
		title.setFont(new Font("Montserrat", Font.BOLD, 72));
		titlePanel.add(title);
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(titlePanel, BorderLayout.NORTH);
		username.setFont(new Font("Montserrat", Font.PLAIN, 36));
		
		usernameField.getDocument().addDocumentListener(usernameFieldListener);
		usernameField.setFont(new Font("Montserrat", Font.PLAIN, 12));
		usernameField.setBorder(BorderFactory.createCompoundBorder(usernameField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		usernamePanel.setLayout(new FlowLayout());
		usernamePanel.add(username);
		usernamePanel.add(usernameField);
		usernamePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		
		createNewWorld.setBackground(new Color(220, 85, 85));
		createNewWorld.setFont(new Font("Montserrat", Font.PLAIN, 16));
		createNewWorld.setEnabled(false);
		createNewWorld.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setupWorldCreation();
			}
		});
		
		loadExistingWorld.setBackground(new Color(220, 85, 85));
		loadExistingWorld.setFont(new Font("Montserrat", Font.PLAIN, 16));
		loadExistingWorld.setEnabled(false);
		loadExistingWorld.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setupLoadWorld();
			}
		});
		
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(createNewWorld);
		buttonPanel.add(loadExistingWorld);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		inputPanel.setLayout(new BorderLayout());
		inputPanel.add(usernamePanel, BorderLayout.NORTH);
		inputPanel.add(buttonPanel, BorderLayout.SOUTH);
		inputPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0), BorderFactory.createEtchedBorder()));
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}
	
	public void setupWorldCreation() {
		this.setVisible(false);
		clearPanel(usernamePanel);
		clearPanel(buttonPanel);
		clearPanel(inputPanel);
		worldName.setFont(new Font("Montserrat", Font.BOLD, 36));
		
		worldNameField.getDocument().addDocumentListener(worldNameFieldListener);
		worldNameField.setFont(new Font("Montserrat", Font.PLAIN, 12));
		worldNameField.setBorder(BorderFactory.createCompoundBorder(worldNameField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		
		begin.setBackground(new Color(220, 85, 85));
		begin.setFont(new Font("Montserrat", Font.PLAIN, 16));
		begin.setEnabled(false);
		begin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				beginWorldLoading(worldNameField.getText(), usernameField.getText(), true);
			}
		});
		
		usernamePanel.setLayout(new FlowLayout());
		usernamePanel.add(worldName);
		usernamePanel.add(worldNameField);
		usernamePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(begin);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		inputPanel.setLayout(new BorderLayout());
		inputPanel.add(usernamePanel, BorderLayout.NORTH);
		inputPanel.add(buttonPanel, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	protected void setupLoadWorld() {
		this.setVisible(false);
		clearPanel(usernamePanel);
		clearPanel(buttonPanel);
		clearPanel(inputPanel);
		selectWorld.setFont(new Font("Montserrat", Font.BOLD, 36));
		
		File[] worlds = FileSystem.getFile(DirectoryMaster.worldsFolder).listFiles();
		for (File f : worlds) {
			worldDropdown.addItem(f.getName());
		}
		worldDropdown.setFont(new Font("Montserrat", Font.PLAIN, 12));
		worldDropdown.setBackground(new Color(204, 204, 204));
		
		begin.setBackground(new Color(220, 85, 85));
		begin.setFont(new Font("Montserrat", Font.PLAIN, 16));
		begin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (worldDropdown.getSelectedItem() != null) {
					beginWorldLoading(worldDropdown.getItemAt(worldDropdown.getSelectedIndex()), usernameField.getText(), false);
				} else {
					DystopiaLogger.logWarning("YOU HAVE NO WORLD SELECTED!");
					return;
				}
			}
		});
		
		usernamePanel.setLayout(new FlowLayout());
		usernamePanel.add(selectWorld);
		usernamePanel.add(worldDropdown);
		usernamePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(begin);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		inputPanel.setLayout(new BorderLayout());
		inputPanel.add(usernamePanel, BorderLayout.NORTH);
		inputPanel.add(buttonPanel, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void beginWorldLoading(String worldName, String username, boolean newWorld) {
		if (this.isVisible()) {
			this.setVisible(false);
			this.dispose();
		}
		
		if (newWorld) {
			Dystopia.getGame().setCurrentWorld(LoadManager.createWorld(worldName, username));
			TempWorldGen.setTilesForTestWorld();
		}
		else {
			Dystopia.getGame().setCurrentWorld(LoadManager.loadWorld(worldName, username));
		}
		
		Start.startGame();
		DystopiaLogger.logInfo("Loading Complete!");
	}

	private static void clearPanel(JPanel jp) {
		jp.removeAll();
		jp.revalidate();
		jp.repaint();
	}
	
}
