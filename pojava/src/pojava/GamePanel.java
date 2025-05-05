package pojava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

/**
 * Klasa panelu gry
 * @author Helena Chmielewska
 */

public class GamePanel extends JPanel{

	int wynik = 0 ;
	CardLayout cardLayout;
    JPanel cardPanel;
	
    public GamePanel(CardLayout cardLayout,JPanel cardPanel) throws HeadlessException {
        
    	setLayout(new BorderLayout());
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        
      
        JPanel centerWrapper = new JPanel();
        centerWrapper.setLayout(new GridBagLayout()); 
        this.add(centerWrapper);
        
      //tworzenie panelu gry .
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(new Color(0,0,139));
        
         
      //componenty nad ramka gry
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
	    
        JButton buttonBack = new JButton("Menu");
	    topPanel.add(buttonBack);
        
        JLabel label = new JLabel("Wynik:");
        topPanel.add(label);
        
	    JLabel result = new JLabel(String.format("%d", wynik));
	    topPanel.add(result);
	    
	    
	    
	    
	    //rozmiar elementów
	    Dimension panleSize = new Dimension(400,550);
	    gamePanel.setPreferredSize(panleSize);
	    gamePanel.setMinimumSize(panleSize);
        gamePanel.setMaximumSize(panleSize);
        
        //wygląd komponentów - kolor, czcionka
        buttonBack.setBackground(new Color(229,204,255)); //kolor w wartościach RGB
        buttonBack.setFocusPainted(false); // usuwa efekt obramowania po kliknięciu
        buttonBack.setFont(new Font("Arial", Font.BOLD, 16));
        label.setFont(new Font("Arial", Font.BOLD, 16));
        result.setFont(new Font("Arial", Font.BOLD, 16));
        
	    
	   //Położenie elementów
	    GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
	    centerWrapper.add(topPanel,gbc);
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    centerWrapper.add(gamePanel,gbc);
	   
	    
	    //Funkcjonalność przycisków
	    buttonBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Menu");
			}
			
		});
	    
	    
        
			
		}
}
