package pojava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;


public class MenuPanel extends JPanel implements ActionListener
{
	CardLayout cardLayout;
    JPanel cardPanel;
	
    public MenuPanel(CardLayout cardLayout, JPanel cardPanel){
        
    	setLayout(new BorderLayout());
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
    	
        //tworzenie centralnego panelu
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        this.add(centerPanel, BorderLayout.CENTER);
        
        
        //tworzenie dolnego panelu
        JPanel bottomPanel = new JPanel(); 
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT)); //wyrównanie do prawej
        this.add(bottomPanel, BorderLayout.SOUTH);
        
        
       //tworzenie przycisków
        JButton buttonGraj = new JButton("GRAJ");
        JButton buttonRanking = new JButton("Ranking użytkowników");
        JButton buttonInstrukcja = new JButton("Instrukcja");
        JButton buttonJezyk = new JButton("Zmień język");
        
        
        //wyśrodkowanie przycisków
        buttonGraj.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonRanking.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonInstrukcja.setAlignmentX(Component.CENTER_ALIGNMENT);
        //buttonJezyk.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        //ustawienie rozmiaru przycisków
        Dimension buttonSize = new Dimension(200,50);
        Dimension buttonSize2 = new Dimension(150,50);
        buttonGraj.setMinimumSize(buttonSize);
        buttonGraj.setMaximumSize(buttonSize);
        buttonGraj.setPreferredSize(buttonSize);
        
        buttonRanking.setMinimumSize(buttonSize);
        buttonRanking.setMaximumSize(buttonSize);
        buttonRanking.setPreferredSize(buttonSize);
        
        buttonInstrukcja.setMinimumSize(buttonSize);
        buttonInstrukcja.setMaximumSize(buttonSize);
        buttonInstrukcja.setPreferredSize(buttonSize);
        
        buttonJezyk.setMinimumSize(buttonSize2);
        buttonJezyk.setMaximumSize(buttonSize2);
        buttonJezyk.setPreferredSize(buttonSize2);
        
        
        //położenie przycisków
        centerPanel.add(Box.createVerticalStrut(250)); //odstęp u góry
        centerPanel.add(buttonGraj);
        centerPanel.add(Box.createVerticalStrut(20)); //odstęp 20px pomiędzy przyciskami
        centerPanel.add(buttonRanking);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonInstrukcja);
        centerPanel.add(Box.createVerticalStrut(20));
        bottomPanel.add(buttonJezyk);
        //centerPanel.add(Box.createVerticalGlue()); 
        
        
        //wygląd przycisków
        buttonGraj.setBackground(new Color(229,204,255)); //kolor w wartościach RGB
        buttonGraj.setFocusPainted(false); // usuwa efekt obramowania po kliknięciu
 
        //Ustawienie funkcjonalności przycisków
        buttonGraj.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Menu2");
			}
			
		});
                
}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
