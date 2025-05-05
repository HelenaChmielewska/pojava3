package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

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

        // Tworzenie paneli (menu, menu2, gra)
        MenuPanel menu = new MenuPanel(cardLayout, cardPanel);
        GamePanel gamePanel = new GamePanel(cardLayout,cardPanel);
        SecondMenuPanel menu2 = new SecondMenuPanel(cardLayout, cardPanel,gamePanel);
        
         
        

        // Dodanie paneli do CardLayout i nadanie im nazw
        cardPanel.add(menu, "Menu");
        cardPanel.add(menu2, "Menu2");
        cardPanel.add(gamePanel, "Game");
        

        // Dodanie panelu do głównego okna
        this.add(cardPanel);

        // Początkowy widok (menu)
        cardLayout.show(cardPanel, "Menu");
        
       
    }
    

    public static void main(String[] args) {  
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);      
    }
}
