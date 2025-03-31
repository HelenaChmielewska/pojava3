package pojava;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.event.*;

public class UserName extends JTextField{
	
	String placeholder;
	boolean showPlaceholder;
	
	public UserName(String placeholder, int columns){
		super(placeholder, columns);
		this.placeholder = placeholder;
		this.showPlaceholder = true;
		setBackground(new Color(229,204,255));
		setForeground(Color.BLACK);
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
				if(getText().isEmpty()) {
					setText(placeholder);
					showPlaceholder = true;
				}
				
			}
		});
		
	}

}
