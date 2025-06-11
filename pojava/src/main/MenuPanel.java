package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 * Klasa panelu głównego menu
 * @author Kinga Urban, Helena Chmielewska
 */

public class MenuPanel extends BackgroundPanel implements ActionListener
{
	CardLayout cardLayout; //cardLayout pozwala przełączać się między panelami
    JPanel cardPanel;
    private UsersRanking usersRanking;
    
	
    public MenuPanel(CardLayout cardLayout, JPanel cardPanel, UsersRanking usersRanking){ 
        
    	super("/tetris_background.png"); //przekazujemy nazwę obrazu do BackgroundPanel
    	
    	this.setLayout(new BorderLayout());
    	this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;
        this.usersRanking = usersRanking;
        
        
    	
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
        
 
        //Ustawienie funkcjonalności przycisków
        buttonGraj.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Menu2");
			}
		});
        buttonRanking.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				showRankingDialog();
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
    
    public void showRankingDialog() {
        // Najpierw posortuj listę (kopię, by nie zmieniać oryginału)
        ArrayList<UserScore> posortowana = usersRanking.posortuj();

        // Przygotuj dane do tabeli
        String[] columnNames = {"Nazwa użytkownika", "Najwyższy wynik", "Ostatni wynik"};
        Object[][] data = new Object[posortowana.size()][3];

        for (int i = 0; i < posortowana.size(); i++) {
            UserScore u = posortowana.get(i);
            data[i][0] = u.getUzytkownik();
            data[i][1] = u.getNajwyzszyWynik();
            data[i][2] = u.getOstatniWynik();
        }

        // Utwórz model tabeli i JTable
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabela tylko do odczytu
            }
        };
        JTable table = new JTable(model);

        // Opcjonalnie ustaw szerokości kolumn
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);

        // Umieść tabelę w scroll pane na wypadek długiej listy
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        // Pokaż dialog z tabelą
        JOptionPane.showMessageDialog(null, scrollPane, "Ranking użytkowników", JOptionPane.INFORMATION_MESSAGE);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
