package pojava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

public class GamePanel extends JPanel{

	int wynik = 0 ;
	CardLayout cardLayout;
    JPanel cardPanel;
	
    public GamePanel(CardLayout cardLayout,JPanel cardPanel) throws HeadlessException {
        
    	setLayout(new BorderLayout());
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        
      //tworzenie panelu gry
        JPanel centerWrapper = new JPanel();
        centerWrapper.setLayout(new GridBagLayout()); 
        this.add(centerWrapper);
        
        
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.BLUE);
        
         
        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Wynik:");
        topPanel.add(label);
	    JLabel result = new JLabel(String.format("%d", wynik));
	    topPanel.add(result);
	    
	    //Przycisk powrót
	    JButton buttonBack = new JButton("Menu");
	    
	    
	    //rozmiar elementów
	    gamePanel.setPreferredSize(new Dimension(400, 550)); 
	    
	   //Położenie elementów
	    GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
	    centerWrapper.add(topPanel,gbc);
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    centerWrapper.add(gamePanel,gbc);
	    gbc.gridx = 3;
        gbc.gridy = 0;
        centerWrapper.add(buttonBack,gbc);
	    
	    //Funkcjonalność przycisków
	    buttonBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Menu");
			}
			
		});
	    
	    
        
			
		}
}
