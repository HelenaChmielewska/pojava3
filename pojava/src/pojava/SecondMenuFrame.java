package pojava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.event.*;


public class SecondMenuFrame extends JFrame implements ActionListener{
	public SecondMenuFrame() throws HeadlessException {
        
		//tworzenie okna
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700, 700);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null); //wyśrodkowanie okna
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		SecondMenuFrame frame = new SecondMenuFrame();
		frame.setVisible(true);

	}

}
