package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.event.*;

/**
 * Klasa tworząca obiekt do wpisania nazwy użytkownika
 * @author Helena Chmielewska
 */

public class UserName extends JTextField{
	
	String placeholder;
	boolean showPlaceholder;
	public String userName;
	
	public UserName(String placeholder, int columns){
		super(placeholder, columns);
		this.placeholder = placeholder;
		this.showPlaceholder = true;
		setBackground(new Color(229,204,255));
		setForeground(Color.BLACK);
		setFont(new Font("Arial", Font.BOLD, 14));
		setHorizontalAlignment(SwingConstants.CENTER);
		
		this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showPlaceholder) {
                    setText("");
                    setForeground(Color.BLACK); 
                    showPlaceholder = false;
                }
            }

			@Override
			public void focusLost(FocusEvent e) {
				 String text = getText().trim(); //usuń spacje z początku i końca
	                if (text.isEmpty()) {
	                    setText(placeholder);
	                    showPlaceholder = true;
	                    userName = null;
	                } else {
	                    showPlaceholder = false;
	                    userName = text;
	                }
			}
		});
		
	}
	public String getUserName() {
        return userName;
    }

}
