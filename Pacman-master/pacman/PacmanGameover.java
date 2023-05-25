package pacman ; 
import java.awt.image.BufferedImage; 
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.* ; 
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

public class PacmanGameover extends JFrame implements ActionListener {
    // Declare variables for buttons and labels
    private IconShapedButton Return , exit; 
    private JFrame pacman ; 
    public PacmanGameover()  { 
		pacman = new JFrame() ;    
    	//guild = new IconShapedButtonn() ;
    	Icon icon1 = new ImageIcon("D:/OOP-Pacman/Pacman-master/images/return1.png") ; 
    	Icon icon2 = new ImageIcon("D:/OOP-Pacman/Pacman-master/images/return2.png");
    	Icon icon3 = new ImageIcon("D:/OOP-Pacman/Pacman-master/images/return3.png") ;
    	Return = new IconShapedButton(icon1,icon2,icon3,410,420) ;
    	Icon icon4 = new ImageIcon("D:/OOP-Pacman/Pacman-master/images/exit1.png") ; 
    	Icon icon5 = new ImageIcon("D:/OOP-Pacman/Pacman-master/images/exit2.png") ; 
    	Icon icon6 = new ImageIcon("D:/OOP-Pacman/Pacman-master/images/exit3.png") ; 
    	exit = new IconShapedButton(icon4,icon5,icon6,410,480) ;
    	//Image icon2 = new ImageIcon
    	JPanel f = new JPanel() { 
    	    private Image backgroundImage;
            {
                try {
                    backgroundImage = ImageIO.read(new File("D:/OOP-Pacman/Pacman-master/images/gameoversceen.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
            }
    	};  
		f.setLayout(null);
		f.add(Return);
        f.add(exit);        
        pacman.setContentPane(f);
        pacman.setTitle("Pacman");
        pacman.setSize(1000, 600);
        pacman.setLocationRelativeTo(null);
        pacman.setVisible(true);
        pacman.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Return.addActionListener(this);
        exit.addActionListener(this) ; 
		 
    }


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Return) {
        	pacman.setVisible(false);
            pacman = new JFrame();
            pacman.setSize(380, 420);
            pacman.setDefaultCloseOperation(EXIT_ON_CLOSE);
            pacman.setLocationRelativeTo(null);
            pacman.setVisible(true);
            pacman.dispose();
            pacman.add(new Model());
		}
		if ( e.getSource()==exit) {
            System.exit(0);
            
		}
		
	}
}
