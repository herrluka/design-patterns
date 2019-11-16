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

public class DialogCircle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPoluprecnik;
	private boolean ok;
	private JTextField txtKoordY;
	private JTextField txtKoordX;
	private Color bojaUnut;
	private Color bojaIvice;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogCircle dialog = new DialogCircle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
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
		
		JButton btnBojaUnutrasnjosti = new JButton("Boja unutrasnjosti");
		btnBojaUnutrasnjosti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				 bojaUnut=JColorChooser.showDialog(null, "Izaberite boju", Color.WHITE);
				 
			}
		});
		
		JButton btnBojaIvice = new JButton("Boja ivice");
		btnBojaIvice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bojaIvice=JColorChooser.showDialog(null, "Izaberite boju", Color.BLACK);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblKoordinataY, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPoluprenik))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(txtPoluprecnik, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
									.addComponent(txtKoordY, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
									.addComponent(btnBojaUnutrasnjosti)))
							.addContainerGap())
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCentar, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblKoordinataX)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtKoordX, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
							.addContainerGap(198, Short.MAX_VALUE))))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addContainerGap(389, Short.MAX_VALUE)
					.addComponent(btnBojaIvice)
					.addGap(48))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblCentar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addGap(27)
									.addComponent(lblKoordinataX, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtKoordX, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKoordinataY, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtKoordY, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
							.addGap(43)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPoluprenik)
								.addComponent(txtPoluprecnik, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(106)
							.addComponent(btnBojaUnutrasnjosti)
							.addGap(34)
							.addComponent(btnBojaIvice)))
					.addContainerGap(52, Short.MAX_VALUE))
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
	public Color getBojaIvice() {
		return bojaIvice;
	}

	public void setBojaIvice(Color bojaIvice) {
		this.bojaIvice = bojaIvice;
	}

	public Color getBojaUnut() {
		return bojaUnut;
	}

	public void setBojaUnut(Color bojaUnut) {
		this.bojaUnut = bojaUnut;
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
}
