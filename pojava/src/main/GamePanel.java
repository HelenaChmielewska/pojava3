package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;

/**
 * Klasa panelu gry
 * @author Helena Chmielewska, Kinga Urban
 */

public class GamePanel extends BackgroundPanel implements Runnable{

	CardLayout cardLayout;
    JPanel cardPanel;
    PlayManager game;
    JPanel topPanel;
    public JLabel result;
    final int FPS = 60;
	Thread gameThread;
	public SecondMenuPanel secondMenu;
	private MenuPanel menu;
	public UsersRanking usersRanking;
	//public UserScore usersScore;

	
    public GamePanel(CardLayout cardLayout,JPanel cardPanel, UsersRanking ranking, SecondMenuPanel secondMenu) throws HeadlessException {
        
    	super("/tetris_kolor.png");
    	setLayout(new BorderLayout());
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        
        this.usersRanking = ranking;
        this.secondMenu = secondMenu;
       
         
        //implementacja KeyListener
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
        
      //tworzenie panelu gry .
        game = new PlayManager();
        
         
      //componenty nad ramka gry
        topPanel = new JPanel();
        topPanel.setOpaque(true);
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
				saveScoreIfValid();
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
	
	public void ustawTlo(int numerTla) {
	    switch (numerTla) {
	        case 1:
	            setBackgroundImage("/tetris_kolor.png");
	            topPanel.setBackground(new Color(194,172,233));
	            break;
	        case 2:
	            setBackgroundImage("/tetris_misie.png");
	            topPanel.setBackground(new Color(230,196,236));
	            break;
	        case 3:
	            setBackgroundImage("/tetris_smoki.png");
	            topPanel.setBackground(new Color(214,254,210));
	            break;
	        case 4:
	            setBackgroundImage("/tetris_pingwiny.png");
	            topPanel.setBackground(new Color(175,231,241));
	            break;
	        default:
	            setBackgroundImage("/tetris_kolor.png"); 
	            topPanel.setBackground(new Color(194,172,233));
	    }
	}
	
	public void saveScoreIfValid() {
	    String username = secondMenu.getUserNameFromField();
	    if (username != null && !username.isEmpty()) {
            usersRanking.aktualizujUserRanking(username, game.score);
            usersRanking.zapiszOsiagniecia();
        } 
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		game.draw(g2);
	}
}