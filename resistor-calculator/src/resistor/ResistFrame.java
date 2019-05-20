package resistor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Canvas;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import resistor.Resistance;

public class ResistFrame {

	private JFrame frmResistorCalculator;
	int clickedTimes;
	String canvasBack;
	Color canvasColor;
	HashMap<String, Color> canvasColors;
	int resistorNum;
	Resistance objResistance;
	double mulNum, tolNum;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResistFrame window = new ResistFrame();
					window.frmResistorCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ResistFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		clickedTimes = 0;
		resistorNum = 0;
		objResistance = new Resistance(2);
		mulNum = 0d;
		tolNum = 0d;
		
		canvasColors = new HashMap<String, Color>();
		frmResistorCalculator = new JFrame();
		frmResistorCalculator.setBackground(Color.WHITE);
		frmResistorCalculator.setTitle("Resistor Calculator");
		frmResistorCalculator.setBounds(100, 100, 510, 365);
		frmResistorCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmResistorCalculator.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));

		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frmResistorCalculator.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblResistance = new JLabel("Resistance:");
		lblResistance.setHorizontalAlignment(SwingConstants.CENTER);
		lblResistance.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clickedTimes += 1;
				if (clickedTimes == 10) {
				    String msg = "<html><center>WE ARE THE BORG."
				    		+ "<br> LOWER YOUR SHIELDS AND SURRENDER YOUR SHIPS.<br> "
				    		+ "WE WILL ADD YOUR BIOLOGICAL AND TECHNOLOGICAL DISTINCTIVENESS TO OUR OWN."
				    		+ "<br> YOUR CULTURE WILL ADAPT TO SERVICE US."
				    		+ "<br><br> RESISTANCE IS FUTILE.";
				    JOptionPane.showMessageDialog(frmResistorCalculator, msg);
				    clickedTimes = 0;
				}
			}
		});
		lblResistance.setFont(new Font("Arial Black", Font.PLAIN, 18));
		lblResistance.setBounds(120, 286, 272, 28);
		panel.add(lblResistance);
		
		JLabel digit1 = new JLabel("Digit 1");
		digit1.setHorizontalAlignment(SwingConstants.CENTER);
		digit1.setBounds(168, 174, 67, 14);
		panel.add(digit1);
		
		JLabel digit2 = new JLabel("Digit 2");
		digit2.setHorizontalAlignment(SwingConstants.CENTER);
		digit2.setBounds(245, 174, 67, 14);
		panel.add(digit2);
		
		JLabel digit3 = new JLabel("Digit 3");
		digit3.setHorizontalAlignment(SwingConstants.CENTER);
		digit3.setBounds(322, 174, 70, 14);
		panel.add(digit3);
		
		JLabel digitMulti = new JLabel("Multiplier");
		digitMulti.setHorizontalAlignment(SwingConstants.CENTER);
		digitMulti.setBounds(168, 230, 67, 14);
		panel.add(digitMulti);
		
		JLabel digitTol = new JLabel("Tolerance");
		digitTol.setHorizontalAlignment(SwingConstants.CENTER);
		digitTol.setBounds(245, 230, 67, 14);
		panel.add(digitTol);
		
		JLabel digitNo = new JLabel("Digits");
		digitNo.setHorizontalAlignment(SwingConstants.CENTER);
		digitNo.setBounds(120, 174, 38, 14);
		panel.add(digitNo);
		
		JLabel BandsLbl = new JLabel("4-Band Resistor");
		BandsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		BandsLbl.setFont(new Font("Arial Black", Font.PLAIN, 14));
		BandsLbl.setBounds(176, 0, 163, 28);
		panel.add(BandsLbl);
		
		Canvas canvas1 = new Canvas();
		canvas1.setBackground(Color.RED);
		canvas1.setForeground(Color.WHITE);
		canvas1.setBounds(176, 34, 14, 74);
		panel.add(canvas1);
		
		Canvas canvas2 = new Canvas();
		canvas2.setForeground(Color.WHITE);
		canvas2.setBackground(Color.GREEN);
		canvas2.setBounds(200, 34, 14, 74);
		panel.add(canvas2);
		
		Canvas canvas3 = new Canvas();
		canvas3.setForeground(Color.WHITE);
		canvas3.setBackground(new Color(0, 0, 255));
		canvas3.setBounds(223, 34, 14, 74);
		panel.add(canvas3);
		
		Canvas canvasMult = new Canvas();
		canvasMult.setForeground(Color.WHITE);
		canvasMult.setBackground(new Color(211, 211, 211));
		canvasMult.setBounds(275, 34, 14, 74);
		panel.add(canvasMult);
		
		Canvas canvasTol = new Canvas();
		canvasTol.setForeground(Color.WHITE);
		canvasTol.setBackground(new Color(255, 215, 0));
		canvasTol.setBounds(325, 34, 14, 74);
		panel.add(canvasTol);
			
		//Digit 1 Combo Box
		JComboBox<String> comboDigit1 =new JComboBox();
		comboDigit1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas1.setBackground(canvasColors.get(comboDigit1.getSelectedItem()));
				try {
					objResistance.setFirDigit(getResistorNum(comboDigit1.getSelectedItem().toString()));
					lblResistance.setText("Resistance: " + objResistance.format(objResistance.getTotal()) + "Ù  ±" + objResistance.getTolDigit() + "%");
				} catch (Exception e) {
					e.printStackTrace();
				}			
			}
		});
		comboDigit1.setToolTipText("First");
		comboDigit1.setSelectedIndex(-1);
		comboDigit1.setBounds(168, 199, 67, 20);
		panel.add(comboDigit1);
		
		
		//Digit 2 Combo Box
		JComboBox comboDigit2 = new JComboBox();
		comboDigit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas2.setBackground(canvasColors.get(comboDigit2.getSelectedItem()));
				try {
					objResistance.setSecDigit(getResistorNum(comboDigit2.getSelectedItem().toString()));
					lblResistance.setText("Resistance: " + objResistance.format(objResistance.getTotal()) + "Ù  ±" + objResistance.getTolDigit() + "%");
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		comboDigit2.setToolTipText("Second");
		comboDigit2.setSelectedIndex(-1);
		comboDigit2.setBounds(245, 199, 67, 20);
		panel.add(comboDigit2);
		
		
		//Digit 3 Combo Box
		JComboBox comboDigit3 = new JComboBox();
		comboDigit3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas3.setBackground(canvasColors.get(comboDigit3.getSelectedItem()));
				try {
					objResistance.setThiDigit(getResistorNum(comboDigit3.getSelectedItem().toString()));
					lblResistance.setText("Resistance: " + objResistance.format(objResistance.getTotal()) + "Ù  ±" + objResistance.getTolDigit() + "%");
				} catch (Exception e) {
					e.printStackTrace();
				}		
			}
		});
		comboDigit3.setToolTipText("Third");
		comboDigit3.setSelectedIndex(-1);
		comboDigit3.setBounds(325, 199, 67, 20);
		panel.add(comboDigit3);
		
		
		//Multiplier Combo Box
		JComboBox comboMult = new JComboBox();
		comboMult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvasMult.setBackground(canvasColors.get(comboMult.getSelectedItem()));
				try {
					objResistance.setMulDigit(getMultNum(comboMult.getSelectedItem().toString()));
					lblResistance.setText("Resistance: " + objResistance.format(objResistance.getTotal()) + "Ù  ±" + objResistance.getTolDigit() + "%");
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		});
		comboMult.setToolTipText("Multiplier");
		comboMult.setSelectedIndex(-1);
		comboMult.setBounds(168, 255, 67, 20);
		panel.add(comboMult);
		
		//Tolerance Combo Box
		JComboBox comboTol = new JComboBox();
		comboTol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvasTol.setBackground(canvasColors.get(comboTol.getSelectedItem()));			
				try {
					objResistance.setTolDigit(getTolNum(comboTol.getSelectedItem().toString()));
					lblResistance.setText("Resistance: " + objResistance.format(objResistance.getTotal()) + "Ù  ±" + objResistance.getTolDigit() + "%");
				} catch (Exception e) {
					e.printStackTrace();
				}	
				
			}
		});
		comboTol.setToolTipText("Tolerance");
		comboTol.setSelectedIndex(-1);
		comboTol.setBounds(245, 255, 67, 20);
		panel.add(comboTol);
			
		JComboBox comboNo = new JComboBox();
		comboNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (Integer.parseInt(comboNo.getSelectedItem().toString()) == 3) {
					hideBands(canvas3, comboDigit3, digit3, true);
					objResistance.setDigitNum(3);
					BandsLbl.setText("5-Band Resistor");
				}else { 
					hideBands(canvas3, comboDigit3, digit3, false);
					objResistance.setDigitNum(2);
					BandsLbl.setText("4-Band Resistor");
				}
				lblResistance.setText("Resistance: " + objResistance.format(objResistance.getTotal()) + "Ù  ±" + objResistance.getTolDigit() + "%");
			}
		});
		comboNo.setToolTipText("Select the number of digits (first three bands)");
		comboNo.setModel(new DefaultComboBoxModel(new String[] {"2", "3"}));
		comboNo.setSelectedIndex(0);
		comboNo.setBounds(120, 199, 38, 20);
		panel.add(comboNo);
		
		JLabel lblPsyborgThe = new JLabel("Psyborg, making things work since 1997");
		lblPsyborgThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPsyborgThe.setFont(new Font("Plantagenet Cherokee", Font.PLAIN, 10));
		lblPsyborgThe.setBounds(294, 314, 206, 16);
		panel.add(lblPsyborgThe);
		
		JLabel backImg = new JLabel("background");
		backImg.setIcon(new ImageIcon(ResistFrame.class.getResource("/assets/resistor.png")));
		backImg.setBounds(0, 0, 500, 330);
		panel.add(backImg);
		
		JComboBox[] bandBox = {comboDigit1, comboDigit2, comboDigit3, comboMult, comboTol};
		for (JComboBox cb : bandBox) {
			initColors(canvasColors, cb);
		}
	}
	
	public void hideBands(Canvas c, JComboBox cb, JLabel lbl, Boolean bool ) {
		c.setVisible(bool);
		cb.setVisible(bool);
		lbl.setVisible(bool);
	}
	
	
	public void initColors(HashMap<String, Color> hm, JComboBox cb) {

		hm.put("Black", Color.black);
		hm.put("Brown", new Color(139,69,19));
		hm.put("Red", Color.red);
		hm.put("Orange", Color.orange);
		hm.put("Yellow", Color.yellow);
		hm.put("Green", Color.green);
		hm.put("Blue", Color.blue);
		hm.put("Violet", new Color(238,130,238) );
		hm.put("Gray", Color.DARK_GRAY);
		hm.put("White", Color.white);
		hm.put("Gold", new Color(212,175,55));
		hm.put("Silver", new Color(188,198,204));
		java.util.Iterator iter = hm.keySet().iterator();
		
		while (iter.hasNext()) {
			Object str = iter.next();
			
			switch (cb.getToolTipText()) {
			case "First":
				if (str != "Gold" && str != "Silver") {cb.addItem(str);}
				break;
				
			case "Second":
				if (str != "Gold" && str != "Silver") {cb.addItem(str);}
				break;
				
			case "Third":
				if (str != "Gold" && str != "Silver") {cb.addItem(str);}
				break;
				
			case "Multiplier":
				if (str != "Silver") {cb.addItem(str);}
				break;
				
			case "Tolerance":
				if (str != "Orange" && str != "Yellow" && str != "White" && str != "Black") {cb.addItem(str);}
				break;

			default:
				break;
			} //switch
		} //while
	} //init colors
	
	public int getResistorNum(String c) {
		
		switch (c) {
		case "Black":
			resistorNum = 0;
			break;
			
		case "Brown":
			resistorNum = 1;
			break;
			
		case "Red":
			resistorNum = 2;
			break;	
			
		case "Orange":
			resistorNum = 3;
			break;	
			
		case "Yellow":
			resistorNum = 4;
			break;
			
		case "Green":
			resistorNum = 5;
			break;
			
		case "Blue":
			resistorNum = 6;
			break;	
			
		case "Violet":
			resistorNum = 7;
			break;
			
		case "Gray":
			resistorNum = 8;
			break;
			
		case "White":
			resistorNum = 9;
			break;	
			
		case "Gold":
			resistorNum = 0;
			break;
			
		case "Silver":
			resistorNum = 0;
			break;	

		default:
			resistorNum = 0;
			break;
		}
		
		
		return resistorNum;
	}
	
	public double getMultNum(String c) {

		switch (c) {
		case "Black":
			mulNum = 1d;
			break;
			
		case "Brown":
			mulNum = 10;
			break;
			
		case "Red":
			mulNum = 100;
			break;	
			
		case "Orange":
			mulNum = 1000;
			break;	
			
		case "Yellow":
			mulNum = 10000;
			break;
			
		case "Green":
			mulNum = 100000;
			break;
			
		case "Blue":
			mulNum = 1000000;
			break;	
			
		case "Violet":
			mulNum = 10000000;
			break;
			
		case "Gray":
			mulNum = 100000000;
			break;
			
		case "White":
			mulNum = 1000000000;
			break;	
			
		case "Gold":
			mulNum = 0.1;
			break;

		default:
			mulNum = 0;
			break;
		}
		
		return mulNum;
	}
	
	public double getTolNum(String c) {
		switch (c) {
		
		case "Brown":
			tolNum = 1;
			break;
			
		case "Red":
			tolNum = 2;
			break;	
			
		case "Green":
			tolNum = 0.5d;
			break;
			
		case "Blue":
			tolNum = 0.25d;
			break;	
			
		case "Violet":
			tolNum = 0.10d;
			break;
			
		case "Gray":
			tolNum = 0.05d;
			break;
			
		case "Gold":
			tolNum = 5;
			break;
			
		case "Silver":
			tolNum = 10;
			break;

		default:
			tolNum = 0;
			break;
		}
		
		return tolNum;
	}
}
