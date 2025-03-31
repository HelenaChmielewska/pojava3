package pojava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;


public class SecondMenuPanel extends JPanel implements ActionListener
{
	CardLayout cardLayout;
    JPanel cardPanel;
	
    public SecondMenuPanel(CardLayout cardLayout,JPanel cardPanel) throws HeadlessException {
        
    	setLayout(new BorderLayout());
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
    	
        //tworzenie centralnego panelu
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
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
        
        
        
        //wyśrodkowanie przycisków
        setLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
        setColor.setAlignmentX(Component.CENTER_ALIGNMENT);
        nazwaUżytkownika.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonStart.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        
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
     
        
        
        //położenie przycisków
        centerPanel.add(Box.createVerticalStrut(250)); //odstęp u góry
        centerPanel.add(nazwaUżytkownika);
        centerPanel.add(Box.createVerticalStrut(20)); //odstęp 20px pomiędzy przyciskami
        centerPanel.add(setLevel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(setColor);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonStart);
        centerPanel.add(Box.createVerticalStrut(20));
        
        
        
        
        //wygląd przycisków
        buttonStart.setBackground(new Color(229,204,255)); //kolor w wartościach RGB
        setLevel.setBackground(new Color(229,204,255)); 
        setColor.setBackground(new Color(229,204,255)); 
        buttonStart.setFocusPainted(false); // usuwa efekt obramowania po kliknięciu
        
        
      //Ustawienie funkcjonalności przycisków
        buttonStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Game");
			}
			
		});
  
                
}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
