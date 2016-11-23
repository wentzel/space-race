import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

/**
 * @author  Airaksinen, Tom & Wentzel, Jonatan
 *
 * The class for the High Score Window.
 *
 */
public class SettingsWindow extends JFrame 
			implements ActionListener, ChangeListener {
	
	private        JComboBox  iconToChange;
	private        JLabel     iconToChangeText;

	private        JPanel     buttonPanel;
	private        JPanel     iconPanel;
	private        JPanel     colorPanel;
	private        JPanel     foregroundPanel;
	private        JPanel     backgroundPanel;
	private        JPanel     buttonColorPanel;
	
	private        JLabel     h1;
	private        JLabel     h2;
	private        JLabel     h3;
	
	private        JLabel     floor1;
	private        JLabel     floor1CI;
	private        JLabel     floor1NI;
	private        JLabel     floor2;
	private        JLabel     floor2CI;
	private        JLabel     floor2NI;
	private        JLabel     floor3;
	private        JLabel     floor3CI;
	private        JLabel     floor3NI;
	private        JLabel     target1;
	private        JLabel     target1CI;
	private        JLabel     target1NI;
	private        JLabel     target2;
	private        JLabel     target2CI;
	private        JLabel     target2NI;
	private        JLabel     wall1;
	private        JLabel     wall1CI;
	private        JLabel     wall1NI;
	private        JLabel     wall2;
	private        JLabel     wall2CI;
	private        JLabel     wall2NI;
	
	private        JButton    browseFloor1Button;
	private        JButton    browseFloor2Button;
	private        JButton    browseFloor3Button;
	private        JButton    browseTarget1Button;
	private        JButton    browseTarget2Button;
	private        JButton    browseWall1Button;
	private        JButton    browseWall2Button;
	private        JButton    loadSettingsButton;	
	private        JButton    saveSettingsButton;
	private        JButton    useAsDefaultButton;
	private        JButton    okButton;
	
	private JSlider rForegroundSlider;
	private JSlider gForegroundSlider;
	private JSlider bForegroundSlider;
	
	private JSlider rBackgroundSlider;
	private JSlider gBackgroundSlider;
	private JSlider bBackgroundSlider;
	
	private JSlider rButtonColorSlider;
	private JSlider gButtonColorSlider;
	private JSlider bButtonColorSlider;
	
	private        GameWindow mainWindow;
	private        boolean    gameOver;
	public  static int        GAME_OVER    = 0;
	public  static int        NOT_GAME_OVER = 1;
	
	/**
	 * Creates a new Settings Window.
	 * 
	 * @param w Game Window which will be paused and
	 * over which the Instructions Window will be centered 
	 */
	public SettingsWindow(GameWindow w) {
		mainWindow = w;

		
		Container c = getContentPane();
//		GridBagLayout m = new GridBagLayout();
//		c.setLayout(m);
		
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		c.setBackground(GameWindow.BACKGROUND_COLOR);

		browseFloor1Button = new JButton("Browse");
		browseFloor1Button.addActionListener(this);
		browseFloor1Button.setBorder(GameWindow.BUTTON_BORDER);
		browseFloor1Button.setFont(GameWindow.LABEL_FONT);
		browseFloor1Button.setBackground(GameWindow.BUTTON_COLOR);
		browseFloor1Button.setForeground(GameWindow.FOREGROUND_COLOR);
		
		browseFloor2Button = new JButton("Browse");
		browseFloor2Button.addActionListener(this);
		browseFloor2Button.setBorder(GameWindow.BUTTON_BORDER);
		browseFloor2Button.setFont(GameWindow.LABEL_FONT);
		browseFloor2Button.setBackground(GameWindow.BUTTON_COLOR);
		browseFloor2Button.setForeground(GameWindow.FOREGROUND_COLOR);
		
		browseFloor3Button = new JButton("Browse");
		browseFloor3Button.addActionListener(this);
		browseFloor3Button.setBorder(GameWindow.BUTTON_BORDER);
		browseFloor3Button.setFont(GameWindow.LABEL_FONT);
		browseFloor3Button.setBackground(GameWindow.BUTTON_COLOR);
		browseFloor3Button.setForeground(GameWindow.FOREGROUND_COLOR);
		
		browseTarget1Button = new JButton("Browse");
		browseTarget1Button.addActionListener(this);
		browseTarget1Button.setBorder(GameWindow.BUTTON_BORDER);
		browseTarget1Button.setFont(GameWindow.LABEL_FONT);
		browseTarget1Button.setBackground(GameWindow.BUTTON_COLOR);
		browseTarget1Button.setForeground(GameWindow.FOREGROUND_COLOR);
		
		browseTarget2Button = new JButton("Browse");
		browseTarget2Button.addActionListener(this);
		browseTarget2Button.setBorder(GameWindow.BUTTON_BORDER);
		browseTarget2Button.setFont(GameWindow.LABEL_FONT);
		browseTarget2Button.setBackground(GameWindow.BUTTON_COLOR);
		browseTarget2Button.setForeground(GameWindow.FOREGROUND_COLOR);
		
		browseWall1Button = new JButton("Browse");
		browseWall1Button.addActionListener(this);
		browseWall1Button.setBorder(GameWindow.BUTTON_BORDER);
		browseWall1Button.setFont(GameWindow.LABEL_FONT);
		browseWall1Button.setBackground(GameWindow.BUTTON_COLOR);
		browseWall1Button.setForeground(GameWindow.FOREGROUND_COLOR);
		
		browseWall2Button = new JButton("Browse");
		browseWall2Button.addActionListener(this);
		browseWall2Button.setBorder(GameWindow.BUTTON_BORDER);
		browseWall2Button.setFont(GameWindow.LABEL_FONT);
		browseWall2Button.setBackground(GameWindow.BUTTON_COLOR);
		browseWall2Button.setForeground(GameWindow.FOREGROUND_COLOR);
		
		loadSettingsButton = new JButton("Load Settings");
		loadSettingsButton.addActionListener(this);
		loadSettingsButton.setBorder(GameWindow.BUTTON_BORDER);
		loadSettingsButton.setFont(GameWindow.LABEL_FONT);
		loadSettingsButton.setBackground(GameWindow.BUTTON_COLOR);
		loadSettingsButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		saveSettingsButton = new JButton("Save Settings");
		saveSettingsButton.addActionListener(this);
		saveSettingsButton.setBorder(GameWindow.BUTTON_BORDER);
		saveSettingsButton.setFont(GameWindow.LABEL_FONT);
		saveSettingsButton.setBackground(GameWindow.BUTTON_COLOR);
		saveSettingsButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		useAsDefaultButton = new JButton("Use as default");
		useAsDefaultButton.addActionListener(this);
		useAsDefaultButton.setBorder(GameWindow.BUTTON_BORDER);
		useAsDefaultButton.setFont(GameWindow.LABEL_FONT);
		useAsDefaultButton.setBackground(GameWindow.BUTTON_COLOR);
		useAsDefaultButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		okButton = new JButton("OK");
		okButton.addActionListener(this);
		okButton.setBorder(GameWindow.BUTTON_BORDER);
		okButton.setFont(GameWindow.LABEL_FONT);
		okButton.setBackground(GameWindow.BUTTON_COLOR);
		okButton.setForeground(GameWindow.FOREGROUND_COLOR);
		
		
		rForegroundSlider = new JSlider(JSlider.HORIZONTAL,
				0,
				255,
				GameWindow.FOREGROUND_COLOR.getRed());
		gForegroundSlider = new JSlider(JSlider.HORIZONTAL,
				0,
				255,
				GameWindow.FOREGROUND_COLOR.getGreen());
		bForegroundSlider = new JSlider(JSlider.HORIZONTAL,
				0,
				255,
				GameWindow.FOREGROUND_COLOR.getBlue());
		
		rBackgroundSlider = new JSlider(JSlider.HORIZONTAL,
				0,
				255,
				GameWindow.BACKGROUND_COLOR.getRed());
		gBackgroundSlider = new JSlider(JSlider.HORIZONTAL,
				0,
				255,
				GameWindow.BACKGROUND_COLOR.getGreen());
		bBackgroundSlider = new JSlider(JSlider.HORIZONTAL,
				0,
				255,
				GameWindow.BACKGROUND_COLOR.getBlue());
		
		rButtonColorSlider = new JSlider(JSlider.HORIZONTAL,
				0,
				255,
				GameWindow.BUTTON_COLOR.getRed());
		gButtonColorSlider = new JSlider(JSlider.HORIZONTAL,
				0,
				255,
				GameWindow.BUTTON_COLOR.getGreen());
		bButtonColorSlider = new JSlider(JSlider.HORIZONTAL,
				0,
				255,
				GameWindow.BUTTON_COLOR.getBlue());
	
//		rForegroundSlider.setPaintTicks(true);
//		gForegroundSlider.setPaintTicks(true);
//		bForegroundSlider.setPaintTicks(true);
//		
//		rForegroundSlider.setPaintLabels(true);
//		gForegroundSlider.setPaintLabels(true);
//		bForegroundSlider.setPaintLabels(true);
//		
//		rForegroundSlider.setMajorTickSpacing(255);
//		gForegroundSlider.setMajorTickSpacing(255);
//		bForegroundSlider.setMajorTickSpacing(255);
//		
//		rForegroundSlider.setMinorTickSpacing(50);
//		gForegroundSlider.setMinorTickSpacing(50);
//		bForegroundSlider.setMinorTickSpacing(50);
		
		rForegroundSlider.addChangeListener(this);
		gForegroundSlider.addChangeListener(this);
		bForegroundSlider.addChangeListener(this);
		
//		rBackgroundSlider.setPaintTicks(true);
//		gBackgroundSlider.setPaintTicks(true);
//		bBackgroundSlider.setPaintTicks(true);
//		
//		rBackgroundSlider.setPaintLabels(true);
//		gBackgroundSlider.setPaintLabels(true);
//		bBackgroundSlider.setPaintLabels(true);
//		
//		rBackgroundSlider.setMajorTickSpacing(50);
//		gBackgroundSlider.setMajorTickSpacing(50);
//		bBackgroundSlider.setMajorTickSpacing(50);
//		
//		rBackgroundSlider.setMinorTickSpacing(5);
//		gBackgroundSlider.setMinorTickSpacing(5);
//		bBackgroundSlider.setMinorTickSpacing(5);
		
		rBackgroundSlider.addChangeListener(this);
		gBackgroundSlider.addChangeListener(this);
		bBackgroundSlider.addChangeListener(this);
		
//		rButtonColorSlider.setPaintTicks(true);
//		gButtonColorSlider.setPaintTicks(true);
//		bButtonColorSlider.setPaintTicks(true);
//		
//		rButtonColorSlider.setPaintLabels(true);
//		gButtonColorSlider.setPaintLabels(true);
//		bButtonColorSlider.setPaintLabels(true);
//		
//		rButtonColorSlider.setMajorTickSpacing(50);
//		gButtonColorSlider.setMajorTickSpacing(50);
//		bButtonColorSlider.setMajorTickSpacing(50);
//		
//		rButtonColorSlider.setMinorTickSpacing(5);
//		gButtonColorSlider.setMinorTickSpacing(5);
//		bButtonColorSlider.setMinorTickSpacing(5);
		
		rButtonColorSlider.addChangeListener(this);
		gButtonColorSlider.addChangeListener(this);
		bButtonColorSlider.addChangeListener(this);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		iconPanel = new JPanel(new GridLayout(8, 4));
		colorPanel = new JPanel();
		colorPanel.setLayout(new BoxLayout(colorPanel, BoxLayout.Y_AXIS));
		foregroundPanel = new JPanel();
		foregroundPanel.setLayout(new BoxLayout(
										foregroundPanel, BoxLayout.Y_AXIS));
		backgroundPanel = new JPanel();
		backgroundPanel.setLayout(new BoxLayout(
										backgroundPanel, BoxLayout.Y_AXIS));
		buttonColorPanel = new JPanel();
		buttonColorPanel.setLayout(new BoxLayout(
										buttonColorPanel, BoxLayout.Y_AXIS));
		
		
//		buttonPanel.setBorder(new TitledBorder(GameWindow.BUTTON_BORDER,
//				 "Icon settings",
//				 TitledBorder.LEFT,
//				 TitledBorder.TOP,
//				 GameWindow.LABEL_FONT,
//				 GameWindow.FOREGROUND_COLOR));
		iconPanel.setBorder(new TitledBorder(GameWindow.BUTTON_BORDER,
											 "Icon settings",
											 TitledBorder.LEFT,
											 TitledBorder.TOP,
											 GameWindow.LABEL_FONT,
											 GameWindow.FOREGROUND_COLOR));
		colorPanel.setBorder(new TitledBorder(GameWindow.BUTTON_BORDER,
				 "Color settings",
				 TitledBorder.LEFT,
				 TitledBorder.TOP,
				 GameWindow.LABEL_FONT,
				 GameWindow.FOREGROUND_COLOR));
		foregroundPanel.setBorder(new TitledBorder(GameWindow.BUTTON_BORDER,
				 "Foreground color",
				 TitledBorder.LEFT,
				 TitledBorder.TOP,
				 GameWindow.LABEL_FONT,
				 GameWindow.FOREGROUND_COLOR));
		backgroundPanel.setBorder(new TitledBorder(GameWindow.BUTTON_BORDER,
				 "Background color",
				 TitledBorder.LEFT,
				 TitledBorder.TOP,
				 GameWindow.LABEL_FONT,
				 GameWindow.FOREGROUND_COLOR));
		buttonColorPanel.setBorder(new TitledBorder(GameWindow.BUTTON_BORDER,
				 "Button background color",
				 TitledBorder.LEFT,
				 TitledBorder.TOP,
				 GameWindow.LABEL_FONT,
				 GameWindow.FOREGROUND_COLOR));
		
		rForegroundSlider.setOpaque(false);
		gForegroundSlider.setOpaque(false);
		bForegroundSlider.setOpaque(false);
		rBackgroundSlider.setOpaque(false);
		gBackgroundSlider.setOpaque(false);
		bBackgroundSlider.setOpaque(false);
		rButtonColorSlider.setOpaque(false);
		gButtonColorSlider.setOpaque(false);
		bButtonColorSlider.setOpaque(false);
		buttonPanel.setOpaque(false);
		iconPanel.setOpaque(false);
		colorPanel.setOpaque(false);
		foregroundPanel.setOpaque(false);
		backgroundPanel.setOpaque(false);
		buttonColorPanel.setOpaque(false);
		
//		rForegroundSlider.setForeground(GameWindow.FOREGROUND_COLOR);
//		gForegroundSlider.setForeground(GameWindow.FOREGROUND_COLOR);
//		bForegroundSlider.setForeground(GameWindow.FOREGROUND_COLOR);
//		rBackgroundSlider.setForeground(GameWindow.FOREGROUND_COLOR);
//		gBackgroundSlider.setForeground(GameWindow.FOREGROUND_COLOR);
//		bBackgroundSlider.setForeground(GameWindow.FOREGROUND_COLOR);
//		rButtonColorSlider.setForeground(GameWindow.FOREGROUND_COLOR);
//		gButtonColorSlider.setForeground(GameWindow.FOREGROUND_COLOR);
//		bButtonColorSlider.setForeground(GameWindow.FOREGROUND_COLOR);
//		iconPanel.setForeground(GameWindow.FOREGROUND_COLOR);
//		colorPanel.setForeground(GameWindow.FOREGROUND_COLOR);
//		foregroundPanel.setForeground(GameWindow.FOREGROUND_COLOR);
//		backgroundPanel.setForeground(GameWindow.FOREGROUND_COLOR);
//		buttonColorPanel.setForeground(GameWindow.FOREGROUND_COLOR);
		
		
		h1 = new JLabel("Location");
		h2 = new JLabel("Current icon");
		h3 = new JLabel("New icon");
		
		floor1  = new JLabel("Floor Type 1");
		floor1CI = new JLabel(Floor.getIcon(Floor.FLOOR_TYPE_1));
		floor1NI = new JLabel(Floor.getIcon(Floor.FLOOR_TYPE_1));
		
		floor2  = new JLabel("Floor Type 2");
		floor2CI = new JLabel(Floor.getIcon(Floor.FLOOR_TYPE_2));
		floor2NI = new JLabel(Floor.getIcon(Floor.FLOOR_TYPE_2));
		
		floor3  = new JLabel("Floor Type 3");
		floor3CI = new JLabel(Floor.getIcon(Floor.FLOOR_TYPE_3));
		floor3NI = new JLabel(Floor.getIcon(Floor.FLOOR_TYPE_3));
		
		target1  = new JLabel("Target Player 1");
		target1CI = new JLabel(Floor.getIcon(Floor.FLOOR_TARGET_1));
		target1NI = new JLabel(Floor.getIcon(Floor.FLOOR_TARGET_1));
		
		target2  = new JLabel("Target Player 2");
		target2CI = new JLabel(Floor.getIcon(Floor.FLOOR_TARGET_2));
		target2NI = new JLabel(Floor.getIcon(Floor.FLOOR_TARGET_2));
		
		wall1   = new JLabel("Wall Type 1");
		wall1CI = new JLabel(Wall.getIcon(Wall.WALL_TYPE_1));
		wall1NI = new JLabel(Wall.getIcon(Wall.WALL_TYPE_1));
		
		wall2   = new JLabel("Wall Type 2");
		wall2CI = new JLabel(Wall.getIcon(Wall.WALL_TYPE_2));
		wall2NI = new JLabel(Wall.getIcon(Wall.WALL_TYPE_2));		
		
		
		h1.setForeground(GameWindow.FOREGROUND_COLOR);
		h2.setForeground(GameWindow.FOREGROUND_COLOR);
		h3.setForeground(GameWindow.FOREGROUND_COLOR);
		floor1.setForeground(GameWindow.FOREGROUND_COLOR);
		floor2.setForeground(GameWindow.FOREGROUND_COLOR);
		floor3.setForeground(GameWindow.FOREGROUND_COLOR);
		target1.setForeground(GameWindow.FOREGROUND_COLOR);
		target2.setForeground(GameWindow.FOREGROUND_COLOR);
		wall1.setForeground(GameWindow.FOREGROUND_COLOR);
		wall2.setForeground(GameWindow.FOREGROUND_COLOR);
		
		buttonPanel.add(loadSettingsButton);
		buttonPanel.add(saveSettingsButton);
		buttonPanel.add(useAsDefaultButton);
		buttonPanel.add(okButton);
		
		iconPanel.add(h1);
		iconPanel.add(h2);
		iconPanel.add(h3);
		iconPanel.add(new JLabel());
		iconPanel.add(floor1);
		iconPanel.add(floor1CI);
		iconPanel.add(floor1NI);
		iconPanel.add(browseFloor1Button);
		iconPanel.add(floor2);
		iconPanel.add(floor2CI);
		iconPanel.add(floor2NI);
		iconPanel.add(browseFloor2Button);
		iconPanel.add(floor3);
		iconPanel.add(floor3CI);
		iconPanel.add(floor3NI);
		iconPanel.add(browseFloor3Button);
		iconPanel.add(target1);
		iconPanel.add(target1CI);
		iconPanel.add(target1NI);
		iconPanel.add(browseTarget1Button);
		iconPanel.add(target2);
		iconPanel.add(target2CI);
		iconPanel.add(target2NI);
		iconPanel.add(browseTarget2Button);
		iconPanel.add(wall1);
		iconPanel.add(wall1CI);
		iconPanel.add(wall1NI);
		iconPanel.add(browseWall1Button);
		iconPanel.add(wall2);
		iconPanel.add(wall2CI);
		iconPanel.add(wall2NI);
		iconPanel.add(browseWall2Button);
		
		foregroundPanel.add(rForegroundSlider);
		foregroundPanel.add(gForegroundSlider);
		foregroundPanel.add(bForegroundSlider);
		
		backgroundPanel.add(rBackgroundSlider);
		backgroundPanel.add(gBackgroundSlider);
		backgroundPanel.add(bBackgroundSlider);
		
		buttonColorPanel.add(rButtonColorSlider);
		buttonColorPanel.add(gButtonColorSlider);
		buttonColorPanel.add(bButtonColorSlider);
		
		colorPanel.add(foregroundPanel);
		colorPanel.add(backgroundPanel);
		colorPanel.add(buttonColorPanel);
		
		c.add(iconPanel);
		c.add(colorPanel);
		c.add(buttonPanel);
//		c.add(okButton);
		
//		GridBagConstraints con;
//		con = new GridBagConstraints();
//		con.gridy = 0;
//		con.gridx = 0;
//		m.setConstraints(h1, con);
//		c.add(h1);
//		
//		
//		con = new GridBagConstraints();
//		con.gridy = 0;
//		con.gridx = 1;
//		m.setConstraints(h2, con);
//		c.add(h2);
//		
//		con = new GridBagConstraints();
//		con.gridy = 0;
//		con.gridx = 2;
//		m.setConstraints(h3, con);
//		c.add(h3);
		
		

		
		
		pack();
		
		// Calculate size and position of the window
		Dimension mainWindowSize = mainWindow.getSize();
		int SettingsWindowWidth = getSize().width;
		int SettingsWindowHeight = getSize().height;
		Toolkit t = Toolkit.getDefaultToolkit();
		Insets insets = getInsets();
		Point mainWindowLocation = mainWindow.getLocation();
		int x = mainWindowLocation.x
				+ (mainWindowSize.width / 2)  - (SettingsWindowWidth / 2);
		int y = mainWindowLocation.y
				+(mainWindowSize.height / 2) - (SettingsWindowHeight / 2);

		// Format the window
		setLocation(x, y);
		setTitle("Settings");
//		setSize(SettingsWindowWidth + insets.left + insets.right,
//				SettingsWindowHeight + insets.top + insets.bottom);
//		setResizable(false);
//		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(wl);
	}
	
	private WindowListener wl = new WindowAdapter() {
		public void windowClosed(WindowEvent e) {
			if (gameOver == true) {
				mainWindow.showGameAlternatives();
			}
			else {
				mainWindow.resumeGame();
				mainWindow.getGameArea().requestFocus();
			}
		}
	};
	
	/**
	 * Creates a new High Score Window and displays (or not) the game
	 * alternatives on close
	 * 
	 * @param w Game Window which will be paused and
	 * over which the Instructions Window will be centered 
	 * @param option 0 if game alternatives is to be shown, otherwise 1
	 */
	public SettingsWindow(GameWindow w, int option) {
		this(w);
		if (option == 0) {
			gameOver = true;
		}
		else {
			gameOver = false;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (	source == browseFloor1Button ||
				source == browseFloor2Button ||
				source == browseFloor3Button ||
				source == browseTarget1Button ||
				source == browseTarget2Button ||
				source == browseWall1Button ||
				source == browseWall2Button) {
			
			String workingDirectory = System.getProperty("user.dir");
			JFileChooser fc = new JFileChooser(workingDirectory + "/img");
			int result = fc.showOpenDialog(mainWindow);
			if (result == JFileChooser.APPROVE_OPTION) {
				String fileName = fc.getSelectedFile().getAbsolutePath();
				System.out.println(fileName);
				if (fileName.startsWith(workingDirectory)) {
					fileName = fileName.substring(workingDirectory.length()+1);
					fileName = fileName.replace('\\','/');
					System.out.println(workingDirectory.length());
					System.out.println(fileName);
				}
				if (source == browseFloor1Button) {
					floor1NI.setIcon(new ImageIcon(fileName));
				}
				else if (source == browseFloor2Button) {
					floor2NI.setIcon(new ImageIcon(fileName));
				}
				else if (source == browseFloor3Button) {
					floor3NI.setIcon(new ImageIcon(fileName));
				}
				else if (source == browseTarget1Button) {
					target1NI.setIcon(new ImageIcon(fileName));
				}
				else if (source == browseTarget2Button) {
					target2NI.setIcon(new ImageIcon(fileName));
				}
				else if (source == browseWall1Button) {
					wall1NI.setIcon(new ImageIcon(fileName));
				}
				else if (source == browseWall2Button) {
					wall2NI.setIcon(new ImageIcon(fileName));
				}
				requestFocus();
			}
		}
		else if (e.getSource() == loadSettingsButton) {
			mainWindow.loadSettings(false);
			updateForeground(false);
			updateBackground(false);
			updateButtonColor(false);
			updateIcons();
			requestFocus();
		}
		else if (e.getSource() == saveSettingsButton) {
			applyCurrentSettings();
			mainWindow.saveSettings(false);
			requestFocus();
		}
		else if (e.getSource() == useAsDefaultButton) {
			applyCurrentSettings();
			mainWindow.saveSettings(true);
			requestFocus();
		}
		else if (e.getSource() == okButton) {
			applyCurrentSettings();
			dispose();
		}
	}
	
	public void stateChanged(ChangeEvent e) {
		updateForeground(true);
		updateBackground(true);
		updateButtonColor(true);
	}
	
	public void applyCurrentSettings() {
		mainWindow.updateForeground(new Color(
				rForegroundSlider.getValue(),
				gForegroundSlider.getValue(),
				bForegroundSlider.getValue()));
		mainWindow.updateBackground(new Color(
				rBackgroundSlider.getValue(),
				gBackgroundSlider.getValue(),
				bBackgroundSlider.getValue()));
		mainWindow.updateButtonColor(new Color(
				rButtonColorSlider.getValue(),
				gButtonColorSlider.getValue(),
				bButtonColorSlider.getValue()));
		Floor.setIcon(Floor.FLOOR_TYPE_1, floor1NI.getIcon());
		Floor.setIcon(Floor.FLOOR_TYPE_2, floor2NI.getIcon());
		Floor.setIcon(Floor.FLOOR_TYPE_3, floor3NI.getIcon());
		Floor.setIcon(Floor.FLOOR_TARGET_1, target1NI.getIcon());
		Floor.setIcon(Floor.FLOOR_TARGET_2, target2NI.getIcon());
		Wall.setIcon(Wall.WALL_TYPE_1, wall1NI.getIcon());
		Wall.setIcon(Wall.WALL_TYPE_2, wall2NI.getIcon());
	}
	
	public void updateForeground(boolean useNewSettings) {
		Color color = new Color(rForegroundSlider.getValue(),
								gForegroundSlider.getValue(),
								bForegroundSlider.getValue());
		if (!useNewSettings) {
			int red   = GameWindow.FOREGROUND_COLOR.getRed();
			int green = GameWindow.FOREGROUND_COLOR.getGreen();
			int blue  = GameWindow.FOREGROUND_COLOR.getBlue();
			
			rForegroundSlider.setValue(red);
			gForegroundSlider.setValue(green);
			bForegroundSlider.setValue(blue);
			
			color = new Color(red, green, blue);
		}
		LineBorder border = new LineBorder(color, 3, true);
		
		h1.setForeground(color);
		h2.setForeground(color);
		h3.setForeground(color);
		floor1.setForeground(color);
		floor2.setForeground(color);
		floor3.setForeground(color);
		target1.setForeground(color);
		target2.setForeground(color);
		wall1.setForeground(color);
		wall2.setForeground(color);
		browseFloor1Button.setForeground(color);
		browseFloor2Button.setForeground(color);
		browseFloor3Button.setForeground(color);
		browseTarget1Button.setForeground(color);
		browseTarget2Button.setForeground(color);
		browseWall1Button.setForeground(color);
		browseWall2Button.setForeground(color);
		loadSettingsButton.setForeground(color);
		saveSettingsButton.setForeground(color);
		useAsDefaultButton.setForeground(color);
		okButton.setForeground(color);
		browseFloor1Button.setBorder(border);
		browseFloor2Button.setBorder(border);
		browseFloor3Button.setBorder(border);
		browseTarget1Button.setBorder(border);
		browseTarget2Button.setBorder(border);
		browseWall1Button.setBorder(border);
		browseWall2Button.setBorder(border);
		loadSettingsButton.setBorder(border);
		saveSettingsButton.setBorder(border);
		useAsDefaultButton.setBorder(border);
		okButton.setBorder(border);
		
//		buttonPanel.setBorder(new TitledBorder(border,
//				 "Icon settings",
//				 TitledBorder.LEFT,
//				 TitledBorder.TOP,
//				 GameWindow.LABEL_FONT,
//				 color));
		iconPanel.setBorder(new TitledBorder(border,
				 "Icon settings",
				 TitledBorder.LEFT,
				 TitledBorder.TOP,
				 GameWindow.LABEL_FONT,
				 color));
		colorPanel.setBorder(new TitledBorder(border,
				"Color settings",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				GameWindow.LABEL_FONT,
				color));
		foregroundPanel.setBorder(new TitledBorder(border,
				"Foreground color",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				GameWindow.LABEL_FONT,
				color));
		backgroundPanel.setBorder(new TitledBorder(border,
				"Background color",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				GameWindow.LABEL_FONT,
				color));
		buttonColorPanel.setBorder(new TitledBorder(border,
				"Button background color",
				TitledBorder.LEFT,
				TitledBorder.TOP,
				GameWindow.LABEL_FONT,
				color));
		
//		rForegroundSlider.setForeground(color);
//		gForegroundSlider.setForeground(color);
//		bForegroundSlider.setForeground(color);
//		rBackgroundSlider.setForeground(color);
//		gBackgroundSlider.setForeground(color);
//		bBackgroundSlider.setForeground(color);
//		rButtonColorSlider.setForeground(color);
//		gButtonColorSlider.setForeground(color);
//		bButtonColorSlider.setForeground(color);
//		iconPanel.setForeground(color);
//		colorPanel.setForeground(color);
//		foregroundPanel.setForeground(color);
//		backgroundPanel.setForeground(color);
//		buttonColorPanel.setForeground(color);
	}
	
	public void updateBackground(boolean useNewSettings) {
		Color color = new Color(rBackgroundSlider.getValue(),
								gBackgroundSlider.getValue(),
								bBackgroundSlider.getValue());
		if (!useNewSettings) {
			int red   = GameWindow.BACKGROUND_COLOR.getRed();
			int green = GameWindow.BACKGROUND_COLOR.getGreen();
			int blue  = GameWindow.BACKGROUND_COLOR.getBlue();
			
			rBackgroundSlider.setValue(red);
			gBackgroundSlider.setValue(green);
			bBackgroundSlider.setValue(blue);
			
			color = new Color(red, green, blue);
		}
		Container c = getContentPane();
		c.setBackground(color);
	}
	
	public void updateButtonColor(boolean useNewSettings) {
		Color color = new Color(rButtonColorSlider.getValue(),
								gButtonColorSlider.getValue(),
								bButtonColorSlider.getValue());
		if (!useNewSettings) {
			int red   = GameWindow.BUTTON_COLOR.getRed();
			int green = GameWindow.BUTTON_COLOR.getGreen();
			int blue  = GameWindow.BUTTON_COLOR.getBlue();
			
			rButtonColorSlider.setValue(red);
			gButtonColorSlider.setValue(green);
			bButtonColorSlider.setValue(blue);
			
			color = new Color(red, green, blue);
		}
		browseFloor1Button.setBackground(color);
		browseFloor2Button.setBackground(color);
		browseFloor3Button.setBackground(color);
		browseTarget1Button.setBackground(color);
		browseTarget2Button.setBackground(color);
		browseWall1Button.setBackground(color);
		browseWall2Button.setBackground(color);
		loadSettingsButton.setBackground(color);
		saveSettingsButton.setBackground(color);
		useAsDefaultButton.setBackground(color);
		okButton.setBackground(color);
	}
	
	public void updateIcons() {
		floor1CI.setIcon(Floor.getIcon(Floor.FLOOR_TYPE_1));
		floor1NI.setIcon(Floor.getIcon(Floor.FLOOR_TYPE_1));
		floor2CI.setIcon(Floor.getIcon(Floor.FLOOR_TYPE_2));
		floor2NI.setIcon(Floor.getIcon(Floor.FLOOR_TYPE_2));
		floor3CI.setIcon(Floor.getIcon(Floor.FLOOR_TYPE_3));
		floor3NI.setIcon(Floor.getIcon(Floor.FLOOR_TYPE_3));
		target1CI.setIcon(Floor.getIcon(Floor.FLOOR_TARGET_1));
		target1NI.setIcon(Floor.getIcon(Floor.FLOOR_TARGET_1));
		target2CI.setIcon(Floor.getIcon(Floor.FLOOR_TARGET_2));
		target2NI.setIcon(Floor.getIcon(Floor.FLOOR_TARGET_2));
		wall1CI.setIcon(Wall.getIcon(Wall.WALL_TYPE_1));
		wall1NI.setIcon(Wall.getIcon(Wall.WALL_TYPE_1));
		wall2CI.setIcon(Wall.getIcon(Wall.WALL_TYPE_2));
		wall2NI.setIcon(Wall.getIcon(Wall.WALL_TYPE_2));
	}
}


