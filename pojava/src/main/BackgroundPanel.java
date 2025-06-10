package main;
import javax.swing.*;
import java.awt.*;

/**
 * Klasa wczytująca grafikę tła
 * @author Kinga Urban
 */

public class BackgroundPanel extends JPanel {
    Image backgroundImage;

    public BackgroundPanel(String imagePath) {
    	setBackgroundImage(imagePath);
    }
    
    
    public void setBackgroundImage(String imagePath) {
    	//wczytanie obrazu tła
    	backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage(); //Image nie posiada konstruktora do wczytania pliku, łatwiej to zrobić przez ImageIcon
    	repaint();
    }
    
    // nadpisanie paintComponent do rysowania obrazu tła
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //m.in. czyści panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); //rysowanie obrazu na całej powierzchni panelu, argument this działa jak obserwator ładowania obrazu, gdy np. ładuje się asynchronicznie
    }
}




