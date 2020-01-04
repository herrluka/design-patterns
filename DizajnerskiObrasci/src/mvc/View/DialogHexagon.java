package mvc.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogHexagon extends JDialog {

	private JPanel contentPane;
	private JTextField txtCoordX;
	private JTextField txtCoordY;
	private JTextField txtRadius;
	private JPanel pnlOutlineColor;
	private JPanel pnlInnerColor;
	private boolean ok;
	
	public static void main(String[] args) {
		DialogHexagon hex = new DialogHexagon();
		hex.setVisible(true);
	}


	public DialogHexagon() {
		setBounds(100, 100, 564, 380);
		setModal(true);
		setTitle("Hexagon");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtCoordX = new JTextField();
		txtCoordX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCoordX.setColumns(10);
		
		txtCoordY = new JTextField();
		txtCoordY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtCoordY.setColumns(10);
		
		txtRadius = new JTextField();
		txtRadius.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtRadius.setColumns(10);
		
		JLabel lblCoordX = new JLabel("Coordinate X");
		lblCoordX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblCoordY = new JLabel("Coordinate Y");
		lblCoordY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblRadius = new JLabel("Radius");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtCoordX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c < 48 || c > 57) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		
		txtCoordY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c < 48 || c > 57) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		
		txtRadius.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c < 48 || c > 57) {
					e.consume();
					getToolkit().beep();
				}
			}
		});
		
		JButton btnOk = new JButton("OK");
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try
				{
					Integer.parseInt(getTxtCoordX());
					Integer.parseInt(getTxtCoordY());
					if(Integer.parseInt(getTxtRadius()) == 0) {
						throw new Exception();
					}
					setOk(true);
					dispose();
				}
				catch(NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Check are all fields fullfilled with numeric values!!", "Error", JOptionPane.WARNING_MESSAGE);
				}
				catch(Exception exc) {
					JOptionPane.showMessageDialog(new JFrame(), "Radius must be greater than zero!", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		
		pnlOutlineColor = new JPanel();
		pnlOutlineColor.setBackground(Color.BLACK);
		pnlOutlineColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = new JColorChooser().showDialog(null, "Izaberite boju",getPnlOutlineColor());
				if(color != null) {
					setPnlOutlineColor(color);
				}
			}
		});
		
		
		JLabel lblBojaIvice = new JLabel("Outline");
		lblBojaIvice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		pnlInnerColor = new JPanel();
		pnlInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color color = new JColorChooser().showDialog(null, "Izaberite boju",getPnlInnerColor());
				if(color != null) {
					setPnlInnerColor(color);
				}
			}
		});
		pnlInnerColor.setBackground(Color.WHITE);
		
		JLabel lblBojaUnutranjosti = new JLabel("Inner");
		lblBojaUnutranjosti.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(408, Short.MAX_VALUE)
					.addComponent(btnOk)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnCancel)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtCoordX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtCoordY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(56)
									.addComponent(lblBojaIvice)))
							.addGap(42)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblCoordY, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblCoordX, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblBojaUnutranjosti, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addComponent(pnlOutlineColor, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addGap(115)
							.addComponent(pnlInnerColor, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(105, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCoordX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCoordX))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtCoordY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCoordY, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtRadius, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblBojaIvice, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblBojaUnutranjosti, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlOutlineColor, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(pnlInnerColor, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
							.addGap(76))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnOk)
							.addComponent(btnCancel))))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public String getTxtCoordX() {
		return txtCoordX.getText();
	}


	public void setTxtCoordX(String txtCoordX) {
		this.txtCoordX.setText(txtCoordX);
	}


	public String getTxtCoordY() {
		return txtCoordY.getText();
	}


	public void setTxtCoordY(String txtCoordY) {
		this.txtCoordY.setText(txtCoordY);
	}


	public String getTxtRadius() {
		return txtRadius.getText();
	}


	public void setTxtRadius(String txtRadius) {
		this.txtRadius.setText(txtRadius);
	}


	public Color getPnlOutlineColor() {
		return pnlOutlineColor.getBackground();
	}


	public void setPnlOutlineColor(Color pnlOutlineColor) {
		this.pnlOutlineColor.setBackground(pnlOutlineColor);
	}


	public Color getPnlInnerColor() {
		return pnlInnerColor.getBackground();
	}


	public void setPnlInnerColor(Color pnlInnerColor) {
		this.pnlInnerColor.setBackground(pnlInnerColor);
	}
	
	public void setTxtCoordXEditable(boolean editable) {
		this.txtCoordX.setEditable(editable);
	}
	
	public void setTxtCoordYEditable(boolean editable) {
		this.txtCoordY.setEditable(editable);
	}


	public boolean isOk() {
		return ok;
	}


	public void setOk(boolean ok) {
		this.ok = ok;
	}
	
	
	
	
}
