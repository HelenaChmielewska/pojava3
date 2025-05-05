package pojava;
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
	
    public SecondMenuPanel(CardLayout cardLayout,JPanel cardPanel) throws HeadlessException {
        
    	super("/tetris_background.png");
    	setLayout(new BorderLayout());
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
    	
        //tworzenie centralnego panelu
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false); //ustawienie przezroczystości panelu, aby była widoczna grafika tła
        this.add(centerPanel, BorderLayout.CENTER);
        
        
        
       //tworzenie przycisków
        
        UserName nazwaUżytkownika = new UserName("Wpisz nazwę użytkownika",30);
        
        String[] levels = {"Normalny","Trudny", "Łatwy"};
        JPanel setLevel = new JPanel();
        setLevel.setLayout(new FlowLayout());
        setLevel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JLabel label = new JLabel("Poziom Trudności: ");
        JComboBox level = new JComboBox<>(levels); 
        setLevel.add(label);
        setLevel.add(level);
        
        String[] gameColor = {"Tło1","Tło2", "Tło3"};
        JPanel setColor = new JPanel();
        setColor.setLayout(new FlowLayout());
        setColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JLabel label2 = new JLabel("Tło gry: ");
        JComboBox colors = new JComboBox<>(gameColor);
        setColor.add(label2);
        setColor.add(colors);
        
        
        JButton buttonStart = new JButton("START");
        
        JButton buttonBack = new JButton("Powrót");
        
        
        
        //wyśrodkowanie przycisków
        setLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        setColor.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        
        setColor.setMinimumSize(buttonSize2);
        setColor.setMaximumSize(buttonSize2);
        setColor.setPreferredSize(buttonSize2);
        
        buttonBack.setMinimumSize(new Dimension(100,20));
        buttonBack.setMaximumSize(new Dimension(100,20));
        buttonBack.setPreferredSize(new Dimension(100,20));
     
        
        
        //położenie przycisków
        centerPanel.add(Box.createVerticalStrut(250)); //odstęp u góry
        centerPanel.add(nazwaUżytkownika);
        centerPanel.add(Box.createVerticalStrut(20)); //odstęp 20px pomiędzy przyciskami
        centerPanel.add(setLevel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(setColor);
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
        setColor.setBackground(new Color(229,204,255));
        setColor.setFont(new Font("Arial", Font.BOLD, 14));
        
        
        
      //Ustawienie funkcjonalności przycisków
        buttonStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Game");
			}
			
		});
        buttonBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Menu");
			}
			
		});
  
                
}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
