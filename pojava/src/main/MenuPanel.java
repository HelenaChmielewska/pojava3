package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

/**
 * Klasa panelu głównego menu
 * @author Kinga Urban
 */

public class MenuPanel extends BackgroundPanel implements ActionListener
{
	CardLayout cardLayout; //cardLayout pozwala przełączać się między panelami
    JPanel cardPanel; 
	
    public MenuPanel(CardLayout cardLayout, JPanel cardPanel){ 
        
    	super("/tetris_background.png"); //przekazujemy nazwę obrazu do BackgroundPanel
    	
    	this.setLayout(new BorderLayout());
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        
    	
        //tworzenie centralnego panelu
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false); //ustawienie przezroczystości panelu, aby była widoczna grafika tła
        this.add(centerPanel, BorderLayout.CENTER); 
        
        
        //tworzenie dolnego panelu
        JPanel bottomPanel = new JPanel(); 
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); //wyśrodkowanie
        bottomPanel.setOpaque(false); //ustawienie przezroczystości panelu, aby była widoczna grafika tła
        this.add(bottomPanel, BorderLayout.SOUTH);
        
        
       //tworzenie komponentów: 3 przyciski, 1 label, 1 lista rozwijana
        JButton buttonGraj = new JButton("GRAJ");
        JButton buttonRanking = new JButton("Ranking użytkowników");
        JButton buttonInstrukcja = new JButton("Instrukcja");
        
        JLabel labelJezyk = new JLabel("Zmień język");
        
        String[] languages = {"polski", "angielski"};
        JComboBox <String>comboBoxJezyk = new JComboBox <>(languages);
        
        
        //wyśrodkowanie przycisków
        buttonGraj.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonRanking.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonInstrukcja.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        //ustawienie rozmiaru przycisków
        Dimension buttonSize = new Dimension(250,50);
        
        buttonGraj.setMinimumSize(buttonSize);
        buttonGraj.setMaximumSize(buttonSize);
        buttonGraj.setPreferredSize(buttonSize);
        
        buttonRanking.setMinimumSize(buttonSize);
        buttonRanking.setMaximumSize(buttonSize);
        buttonRanking.setPreferredSize(buttonSize);
        
        buttonInstrukcja.setMinimumSize(buttonSize);
        buttonInstrukcja.setMaximumSize(buttonSize);
        buttonInstrukcja.setPreferredSize(buttonSize);
        
        
        //położenie przycisków
        centerPanel.add(Box.createVerticalStrut(250)); //odstęp u góry
        centerPanel.add(buttonGraj);
        centerPanel.add(Box.createVerticalStrut(20)); //odstęp 20px pomiędzy przyciskami
        centerPanel.add(buttonRanking);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(buttonInstrukcja);
        centerPanel.add(Box.createVerticalStrut(20));
        bottomPanel.add(labelJezyk);
        bottomPanel.add(comboBoxJezyk);
        
        
        //wygląd komponentów - kolor, czcionka
        buttonGraj.setBackground(new Color(229,204,255)); //kolor w wartościach RGB
        buttonGraj.setFocusPainted(false); // usuwa efekt obramowania po kliknięciu
        buttonGraj.setFont(new Font("Arial", Font.BOLD, 16));
        
        buttonRanking.setBackground(new Color(229,204,255)); 
        buttonRanking.setFocusPainted(false); 
        buttonRanking.setFont(new Font("Arial", Font.BOLD, 14));
        
        buttonInstrukcja.setBackground(new Color(229,204,255)); 
        buttonInstrukcja.setFocusPainted(false); 
        buttonInstrukcja.setFont(new Font("Arial", Font.BOLD, 14));
        
        labelJezyk.setForeground(new Color(229,204,255));
        labelJezyk.setFont(new Font("Arial", Font.BOLD, 12));
        
        comboBoxJezyk.setBackground(new Color(229,204,255)); 
 
        //Ustawienie funkcjonalności przycisków
        buttonGraj.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Menu2");
			}
		});
        buttonInstrukcja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInstructions();
            }
        });
}
	
    private void showInstructions() {
        String instructions = "Instrukcja gry Tetris:\n\n" +
                "1. Celem gry jest układanie klocków w poziome linie.\n" +
                "2. Klocki spadają w dół, a gracz może je obracać i przesuwać.\n" +
                "3. Kiedy linia jest pełna, znika i daje punkty.\n" +
                "4. Gra kończy się, gdy klocki dotkną górnej krawędzi ekranu.\n\n" +
                "Sterowanie:\n" +
                "Strzałki prawo, lewo, dół: poruszanie klockami\n" +
                "Strzałka do góry: obracanie klocka\n"+
                "Spacja: pauza w grze";

        JOptionPane.showMessageDialog(this, instructions, "Instrukcja gry", JOptionPane.INFORMATION_MESSAGE);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
