package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

/**
 * Klasa panelu drugiego menu - ustawienia gracza
 * @author Helena Chmielewska
 */

public class SecondMenuPanel extends BackgroundPanel implements ActionListener
{
	CardLayout cardLayout;
    JPanel cardPanel;
    GamePanel gamePanel;
    UserName nazwaUżytkownika;
    static int whatlevel;
    static int whatBackground;
    //public String userName;
	
    public SecondMenuPanel(CardLayout cardLayout,JPanel cardPanel, GamePanel gamePanel) throws HeadlessException {
        
    	super("/tetris_background.png");
    	setLayout(new BorderLayout());
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.gamePanel = gamePanel;
    	
        //tworzenie centralnego panelu
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false); //ustawienie przezroczystości panelu, aby była widoczna grafika tła
        this.add(centerPanel, BorderLayout.CENTER);
        
        
        
       //tworzenie przycisków
        
        nazwaUżytkownika = new UserName("Wpisz nazwę użytkownika",30);
        
        String[] levels = {"Łatwy","Normalny","Trudny"};
        JPanel setLevel = new JPanel();
        setLevel.setLayout(new FlowLayout());
        setLevel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        JLabel label = new JLabel("Poziom Trudności: ");
        JComboBox level = new JComboBox<>(levels); 
        level.setSelectedItem("Normalny");
        setLevel.add(label);
        setLevel.add(level);
        
        String[] gameBackground = {"Fiolet","Misie","Smoki", "Pingwiny"};
        JPanel setBackground = new JPanel();
        setBackground.setLayout(new FlowLayout());
        setBackground.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JLabel label2 = new JLabel("Tło gry: ");
        JComboBox backgrounds = new JComboBox<>(gameBackground);
        setBackground.add(label2);
        setBackground.add(backgrounds);
        
        
        JButton buttonStart = new JButton("START");
        
        JButton buttonBack = new JButton("Powrót");
        
        
        
        //wyśrodkowanie przycisków
        setLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        setBackground.setAlignmentX(Component.CENTER_ALIGNMENT);
        nazwaUżytkownika.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        
        //ustawienie rozmiaru przycisków
        Dimension buttonSize = new Dimension(250,50);
        Dimension buttonSize2 = new Dimension(250,35);
        buttonStart.setMinimumSize(buttonSize);
        buttonStart.setMaximumSize(buttonSize);
        buttonStart.setPreferredSize(buttonSize);
        
        nazwaUżytkownika.setMinimumSize(buttonSize);
        nazwaUżytkownika.setMaximumSize(buttonSize);
        nazwaUżytkownika.setPreferredSize(buttonSize);
        
        setLevel.setMinimumSize(buttonSize2);
        setLevel.setMaximumSize(buttonSize2);
        setLevel.setPreferredSize(buttonSize2);
        
        setBackground.setMinimumSize(buttonSize2);
        setBackground.setMaximumSize(buttonSize2);
        setBackground.setPreferredSize(buttonSize2);
        
        buttonBack.setMinimumSize(new Dimension(100,20));
        buttonBack.setMaximumSize(new Dimension(100,20));
        buttonBack.setPreferredSize(new Dimension(100,20));
     
        
        
        //położenie przycisków
        centerPanel.add(Box.createVerticalStrut(250)); //odstęp u góry
        centerPanel.add(nazwaUżytkownika);
        centerPanel.add(Box.createVerticalStrut(20)); //odstęp 20px pomiędzy przyciskami
        centerPanel.add(setLevel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(setBackground);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonStart);
        centerPanel.add(Box.createVerticalStrut(120));
        centerPanel.add(buttonBack);
        centerPanel.add(Box.createVerticalStrut(100));
        
        
        
        
        //wygląd przycisków
        buttonStart.setBackground(new Color(229,204,255)); //kolor w wartościach RGB
        buttonStart.setFont(new Font("Arial", Font.BOLD, 14));
        buttonStart.setFocusPainted(false); // usuwa efekt obramowania po kliknięciu
        buttonBack.setBackground(new Color(229,204,255)); 
        buttonBack.setFont(new Font("Arial", Font.BOLD, 14));
        buttonBack.setFocusPainted(false); 
        setLevel.setBackground(new Color(229,204,255)); 
        setLevel.setFont(new Font("Arial", Font.BOLD, 14));
        setBackground.setBackground(new Color(229,204,255));
        setBackground.setFont(new Font("Arial", Font.BOLD, 14));
        
        
        
      //Ustawienie funkcjonalności przycisków
        buttonStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = getUserNameFromField();
		        if (userName == null) {
		        	JOptionPane.showMessageDialog(SecondMenuPanel.this, "Musisz wpisać nazwę użytkownika!", "Ostrzeżenie", JOptionPane.WARNING_MESSAGE);
		        	return; // nie przechodzimy dalej
		        }
				
				gamePanel.ustawTlo(whatBackground);
				cardLayout.show(cardPanel, "Game");
				gamePanel.game.gameOver = false;
				gamePanel.game.resetGame();
				gamePanel.launchGame();
			}
			
		});
        buttonBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Menu");
			}
			
		});
        level.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    if (e.getSource() == level) {
        	        String selectedLevel = (String) level.getSelectedItem();
        	        
        	        switch (selectedLevel) {
        	            case "Łatwy":
        	                whatlevel = 1;
        	                break;
        	            case "Normalny":
        	                whatlevel = 2;
        	                break;
        	            case "Trudny":
        	                whatlevel = 3;
        	                break;
        	        }
        	        PlayManager.changeLevel();
        	        //System.out.println("Wybrano poziom: " + selectedLevel); // debug
        	    }
        	}
        });
        
        backgrounds.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	    if (e.getSource() == backgrounds) { //czy zrodlem eventu jest ten combobox
        	        String selectedBackground = (String) backgrounds.getSelectedItem();
        	        
        	        switch (selectedBackground) {
	        	        case "Fiolet":
	    	                whatBackground = 1;
	    	                break;
        	        	case "Misie":
        	                whatBackground = 2;
        	                break;
        	            case "Smoki":
        	            	whatBackground = 3;
        	                break;
        	            case "Pingwiny":
        	            	whatBackground = 4;
        	                break;
        	        }
        	        
        	        //System.out.println("Wybrano tło: " + selectedBackground); //debug
        	    }
        	}
        });
        
        
                       
}
    public String getUserNameFromField() {
    	 String text = nazwaUżytkownika.getText().trim();
    	    if (text.isEmpty() || text.equals(nazwaUżytkownika.placeholder)) {
    	        return null;
    	    }
    	    else {
    	    	return text;
    	    }
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
