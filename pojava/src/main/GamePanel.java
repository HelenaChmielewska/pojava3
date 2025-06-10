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

	CardLayout cardLayout;
    JPanel cardPanel;
    PlayManager game;
    public JLabel result;
    final int FPS = 60;
	Thread gameThread;
	private SecondMenuPanel secondMenu;
	private MenuPanel menu;
	//public UsersRanking usersRanking;
	//public UserScore usersScore;

	
    public GamePanel(CardLayout cardLayout,JPanel cardPanel) throws HeadlessException {
        
    	setLayout(new BorderLayout());
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
       
         
        //implementacja KeyListener
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
        
	    result = new JLabel();
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
				//usersRanking.aktualizujUserRanking(secondMenu.getName(), game.score);
				//usersRanking.aktualizujUserRanking(secondMenu.getName(), game.score);
				//usersRanking.zapiszOsiagniecia();
				cardLayout.show(cardPanel, "Menu");
			}
			
		});
	    setFocusable(true);	
	    requestFocusInWindow();
    }
    
    public void stopGameThread() {
        if (gameThread != null) {
            Thread temp = gameThread;
            gameThread = null;
            try {
                temp.join(); // czekaj, aż wątek się zakończy
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void launchGame() {
    	stopGameThread();
    	gameThread = new Thread(this);
    	gameThread.start();
    }

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//pętla gry
		double drawInterval=FPS/1000000000;
		double delta=0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime-lastTime)/1e9*60;
			lastTime=currentTime;
			
			if(delta >= 1) {
				if(KeyHandler.INSTANCE.pausePressed == false && game.gameOver == false) {
					game.update();
				}
				repaint();
				result.setText(String.valueOf(game.score));

				delta-=1;
			}
			
		}
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		game.draw(g2);
	}
}