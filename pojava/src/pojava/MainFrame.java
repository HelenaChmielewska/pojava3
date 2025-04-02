package pojava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 700);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null); //wyśrodkowanie okna

        // Ustawienie głównego panelu z CardLayout
        CardLayout cardLayout = new CardLayout();
        JPanel cardPanel = new JPanel(cardLayout);

        // Tworzenie paneli (menu, menu2, gra)
        MenuPanel menu = new MenuPanel(cardLayout, cardPanel);
        SecondMenuPanel menu2 = new SecondMenuPanel(cardLayout, cardPanel);
        GamePanel game = new GamePanel(cardLayout,cardPanel); 
        game.setBackground(Color.BLACK); // 
        

        // Dodanie paneli do CardLayout i nadanie im nazw
        cardPanel.add(menu, "Menu");
        cardPanel.add(menu2, "Menu2");
        cardPanel.add(game, "Game");
        

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
