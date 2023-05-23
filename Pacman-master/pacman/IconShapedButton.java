package pacman;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class IconShapedButton extends JButton {
    private Icon icon, icon2, icon3 ; 
    private int xPos , yPos ; 

    public IconShapedButton(Icon icon, Icon icon2, Icon icon3, int x , int y) {
        this.icon = icon;
        this.icon2 = icon2 ; 
        this.icon3 = icon3 ; 
        xPos = x ;
        yPos = y ; 
        setIcon(icon) ; 
        setBounds(xPos , yPos, icon.getIconWidth(), icon.getIconHeight()) ;
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false); 
        setOpaque(false);
        addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
            	setIcon(icon2) ; 
            	 setPreferredSize(new Dimension(icon2.getIconWidth(), icon2.getIconHeight()));
                 revalidate();
                 repaint();                           
            }

            public void mouseExited(MouseEvent e) {
            	setIcon(icon) ; 
            	setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
                revalidate();
                repaint();            
            }
            public void mousePressed(MouseEvent e) {           	
            	setIcon(icon3) ; 
            	setPreferredSize(new Dimension(icon3.getIconWidth(), icon3.getIconHeight()));
                revalidate();
                repaint();       	
            }
            public void mouseReleased(MouseEvent e) {
            	setIcon(icon2) ; 
            	setPreferredSize(new Dimension(icon2.getIconWidth(), icon2.getIconHeight()));
                revalidate();
                repaint();  }
            }
            
            );
    } 
    @Override
    protected void paintComponent(Graphics g2d) {
        super.paintComponent(g2d);
        if (getModel().isPressed()) {
            icon3.paintIcon(this, g2d, 0, 0);
        } else if (getModel().isRollover()) {
            icon2.paintIcon(this, g2d, 0, 0);
        } else {
            icon.paintIcon(this, g2d, 0, 0);
        }
    
    }
}
