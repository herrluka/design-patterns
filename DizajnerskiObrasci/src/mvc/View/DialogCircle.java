package mvc.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mvc.Model.Circle;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPoluprecnik;
	private boolean ok;
	private JTextField txtKoordY;
	private JTextField txtKoordX;
	private JPanel pnlCircleOutlineColor; 
	private JPanel pnlCircleInnerColor;

	public DialogCircle() {
		setTitle("Circle");
		setBounds(100, 100, 568, 391);
		getContentPane().setLayout(new BorderLayout());
		this.setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblPoluprenik = new JLabel("Polupre\u010Dnik");
		lblPoluprenik.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPoluprecnik = new JTextField();
		txtPoluprecnik.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtPoluprecnik.setColumns(10);
		
		JLabel lblKoordinataX = new JLabel("Koordinata X");
		lblKoordinataX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblCentar = new JLabel("Centar");
		lblCentar.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblKoordinataY = new JLabel("Koordinata Y");
		lblKoordinataY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		txtKoordY = new JTextField();
		txtKoordY.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtKoordY.setColumns(10);
		
		txtKoordY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		txtKoordX = new JTextField();
		txtKoordX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtKoordX.setColumns(10);
		
		txtKoordX.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		pnlCircleOutlineColor = new JPanel();
		pnlCircleOutlineColor.setBackground(Color.BLACK);
		pnlCircleOutlineColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setPnlCircleOutlineColor(JColorChooser.showDialog(null, "Izaberite boju", Color.BLACK));
			}
		});
		
		pnlCircleInnerColor = new JPanel();
		pnlCircleInnerColor.setBackground(Color.WHITE);
		pnlCircleInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setPnlCircleInnerColor(JColorChooser.showDialog(null, "Izaberite boju", Color.WHITE));
			}
		});
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblKoordinataY, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPoluprenik))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(txtKoordY, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(txtPoluprecnik, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addGap(39)
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(pnlCircleOutlineColor, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
												.addComponent(pnlCircleInnerColor, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblKoordinataX)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtKoordX, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(94, Short.MAX_VALUE))
						.addComponent(lblCentar, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addComponent(lblCentar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(lblKoordinataX, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(txtKoordX, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlCircleOutlineColor, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKoordinataY, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtKoordY, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addGap(43)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPoluprenik)
								.addComponent(txtPoluprecnik, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
							.addGap(48)
							.addComponent(pnlCircleInnerColor, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(80, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						try
						{
						Circle c= new Circle();
						c.setRadius(Integer.parseInt(getTextPoluprecnik()));
						int i=Integer.parseInt(getTxtKoordX());
						int j=Integer.parseInt(getTxtKoordY());
						setOk(true);
						dispose();
						}
						catch(NumberFormatException ex)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!!", "Greška", JOptionPane.WARNING_MESSAGE);
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Vrednost poluprecnika mora da bude pozitivna!", "Greška", JOptionPane.WARNING_MESSAGE);	
						}
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public String getTxtKoordY() {
		return txtKoordY.getText();
	}

	public void setTxtKoordY(String txtKoordY) {
		this.txtKoordY.setText(txtKoordY);
	}

	public String getTxtKoordX() {
		return txtKoordX.getText();
	}

	public void setTxtKoordX(String txtKoordX) {
		this.txtKoordX.setText(txtKoordX);
	}
	
	public void setTxtKoordXEdt(boolean b)
	{
		this.txtKoordX.setEditable(b);
	}
	
	public void setTxtKoordYEdt(boolean b)
	{
		this.txtKoordY.setEditable(b);
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getTextPoluprecnik() {
		return txtPoluprecnik.getText();
	}

	public void setPoluprecnik(String textField) {
		this.txtPoluprecnik.setText(textField);
		}

	public Color getPnlCircleOutlineColor() {
		return pnlCircleOutlineColor.getBackground();
	}

	public void setPnlCircleOutlineColor(Color pnlCircleOutlineColor) {
		this.pnlCircleOutlineColor.setBackground(pnlCircleOutlineColor);
	}

	public Color getPnlCircleInnerColor() {
		return pnlCircleInnerColor.getBackground();
	}

	public void setPnlCircleInnerColor(Color pnlCircleInnerColor) {
		this.pnlCircleInnerColor.setBackground(pnlCircleInnerColor);
	}
	
	
	
	
}
