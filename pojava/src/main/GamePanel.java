package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

/**
 * Klasa panelu gry
 * @author Helena Chmielewska
 */

public class GamePanel extends JPanel implements Runnable{

	int wynik = 0 ;
	CardLayout cardLayout;
    JPanel cardPanel;
    PlayManager game;
    final int FPS = 60;
	Thread gameThread;
	
    public GamePanel(CardLayout cardLayout,JPanel cardPanel) throws HeadlessException {
        
    	setLayout(new BorderLayout());
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
        
      //tworzenie panelu gry .
        game = new PlayManager();
        
         
      //componenty nad ramka gry
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
	    
        JButton buttonBack = new JButton("Menu");
	    topPanel.add(buttonBack);
        
        JLabel label = new JLabel("Wynik:");
        topPanel.add(label);
        
	    JLabel result = new JLabel(String.format("%d", wynik));
	    topPanel.add(result);
	    
	    this.add(topPanel,BorderLayout.NORTH);
	    
	    
	    
	  
        
        //wygląd komponentów - kolor, czcionka
        buttonBack.setBackground(new Color(229,204,255)); //kolor w wartościach RGB
        buttonBack.setFocusPainted(false); // usuwa efekt obramowania po kliknięciu
        buttonBack.setFont(new Font("Arial", Font.BOLD, 16));
        label.setFont(new Font("Arial", Font.BOLD, 16));
        result.setFont(new Font("Arial", Font.BOLD, 16));
        
	    
	   
	    
	    //Funkcjonalność przycisków
	    buttonBack.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Menu");
			}
			
		});
	    
	    
        
			
		}
    public void launchGame() {
    	gameThread = new Thread(this);
    	gameThread.start();
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//pętla gry
		double drawInterval=1000000000/FPS;
		double delta=0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime-lastTime)/drawInterval;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				System.out.println("run");
			}
			
		}
		
	}

	private void update() {
		game.update();
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		game.draw(g2);
	}
}