package pacman;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.* ; 

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class PacmanMenu extends JFrame implements ActionListener {
	private boolean isBackgroundMusicPlaying = true;
	private Clip clip;
	private JFrame pacman ; 
    private IconShapedButton playing, guild, quit;
    public PacmanMenu() {
    	pacman = new JFrame() ; 
    	Icon thumnail = new ImageIcon("Pacman-master/images/thumnail2.png");
        Icon icon1 = new ImageIcon("Pacman-master/images/playingfix.png");
        Icon icon2 = new ImageIcon("Pacman-master/images/playingfixsecond.png");
        Icon icon3 = new ImageIcon("Pacman-master/images/playingfixthird.png");
        Icon option1 = new ImageIcon("Pacman-master/images/option.png");
        Icon option2 = new ImageIcon("Pacman-master/images/option2.png");
        Icon option3 = new ImageIcon("Pacman-master/images/option3.png");
        Icon quit1 = new ImageIcon("Pacman-master/images/quit1.png");
        Icon quit2 = new ImageIcon("Pacman-master/images/quit2.png");
        Icon quit3 = new ImageIcon("Pacman-master/images/quit3.png");
        quit = new IconShapedButton(quit1,quit2,quit3,700,450) ; 
        guild =new IconShapedButton(option1, option2, option3, 440, 450);
        playing = new IconShapedButton(icon1, icon2,icon3, 180, 450);
        JLabel label = new JLabel(thumnail);
        label.setBounds((int)(1000/2-thumnail.getIconWidth()/2), 40,thumnail.getIconWidth(), thumnail.getIconHeight());
        JPanel backgroundPanel = new JPanel() {
            private Image backgroundImage;
            {
                try {
                    backgroundImage = ImageIO.read(new File("Pacman-master/images/backgroup.jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, null);
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.add(label) ;
        backgroundPanel.add(quit);
        backgroundPanel.add(guild);
        backgroundPanel.add(playing);       
        pacman.setContentPane(backgroundPanel);
        pacman.setTitle("Pacman");
        pacman.setSize(1000, 600);
        pacman.setLocationRelativeTo(null);
        pacman.setVisible(true);
        pacman.setDefaultCloseOperation(EXIT_ON_CLOSE);
        movePacman() ; 
        playBackgroundMusic() ;
        playing.addActionListener(this);
        guild.addActionListener(this);
        quit.addActionListener(this) ; 
    }
    public static void main(String[] abc) {
        PacmanMenu m = new PacmanMenu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playing) {
        	pacman.setVisible(false) ;
        	stopBackgroundMusic() ;
            pacman = new JFrame();
            pacman.setSize(380, 420);
            pacman.setDefaultCloseOperation(EXIT_ON_CLOSE);
            pacman.setLocationRelativeTo(null);
            pacman.setVisible(true);
            pacman.add(new Model());
           
        }
        if (e.getSource() == guild) {
        	pacman.dispose();
        	stopBackgroundMusic() ;
        	JFrame frame = new JFrame("Pacman Game");

            
            JLabel instructionsLabel = new JLabel("<html>Chào mừng bạn đến với phiên bản game Pacman đơn giản của chúng tôi, đây là hướng dẫn chơi: để di chuyển nhân vật Pacman, sử dụng các phím mũi tên, Pacman sẽ di chuyển liên tục cho đến khi chạm bất tường rồi ngừng lại. Trong phiên bản này, đơn giản chỉ là tìm cách né các con ma trong mê cung , mỗi lần PacMan đi qua 1 ô chứa điểm số sẽ cộng thêm 1 điểm</html>");

            
            frame.getContentPane().add(instructionsLabel);

           
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
        if (e.getSource()== quit) {
        	pacman.dispose();
        	stopBackgroundMusic() ; 
        }
    }
    private void movePacman() {   
    	Icon pacmanIcon = new ImageIcon(getClass().getResource("Pacman-master/images/pacmancatchghost2.gif"));
    	JLabel pacmanLabel = new JLabel(pacmanIcon);
      	pacmanLabel.setBounds(-150, -20, pacmanIcon.getIconWidth(), pacmanIcon.getIconHeight()); 
    	pacman.add(pacmanLabel) ; 
    	Timer timer = new Timer(20, new ActionListener() { 
    		int x =-150 ;
    		
    	    public void actionPerformed(ActionEvent e) { 
    	    	   x += 2; 
    	    	   pacmanLabel.setBounds(x, -20, pacmanIcon.getIconWidth(), pacmanIcon.getIconHeight()); 
    	    	   if (x >= 1000 ) { 
    	    		   ((Timer) e.getSource()).stop();
    	    		   pacmanLabel.setVisible(false);} } }); 	    		  
    	pacman.addWindowListener(new WindowAdapter() { 
    		public void windowOpened(WindowEvent e) { 
    			timer.start(); } });
    }
    private void playBackgroundMusic() {
        try {
            File musicFile = new File("Pacman-master/song/Careless-Whisper.wav");
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}