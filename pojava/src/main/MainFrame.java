package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Klasa głównego okna i obsługująca przełączanie między panelami
 * @author Kinga Urban, Helena Chmielewska
 */

public class MainFrame extends JFrame {
	
	public static final int WIDTH = 700;
	public static final int HEIGHT = 700;
	
    public MainFrame() {
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null); //wyśrodkowanie okna
        this.setResizable(false);

        // Ustawienie głównego panelu z CardLayout
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);
        
        //Tworzymy UsersRanking
        UsersRanking usersRanking = new UsersRanking();
        
        //Tworzymy GamePanel z null jako secondMenu
        GamePanel gamePanel = new GamePanel(cardLayout, cardPanel, usersRanking, null);
        
        //Tworzymy SecondMenuPanel i przekazujemy do niego gamePanel
        SecondMenuPanel menu2 = new SecondMenuPanel(cardLayout, cardPanel, gamePanel);
        
        //ustawiamy secondMenu w GamePanel
        gamePanel.secondMenu = menu2;

        // Tworzenie MenuPanel
        MenuPanel menu = new MenuPanel(cardLayout, cardPanel, usersRanking);
        
        
    
        // Dodanie paneli do CardLayout i nadanie im nazw
        cardPanel.add(menu, "Menu");
        cardPanel.add(menu2, "Menu2");
        cardPanel.add(gamePanel, "Game");
        

        // Dodanie panelu do głównego okna
        this.add(cardPanel);

        // Początkowy widok (menu)
        cardLayout.show(cardPanel, "Menu");
        
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (gamePanel != null) {
                    gamePanel.saveScoreIfValid();
                }
               
            }
        });
       
    }
    

    public static void main(String[] args) {  
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);      
    }
}
