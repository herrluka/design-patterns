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

import mvc.Model.Donut;
import mvc.Model.Point;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class DialogDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtKoordX;
	private JTextField txtKoordY;
	private JTextField txtUnut;
	private JTextField txtSpolj;
	private Color bojaUnut;
	private Color bojaIvice;
	private boolean ok;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogDonut dialog = new DialogDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogDonut() {
		setBounds(100, 100, 730, 492);
		setTitle("Donut");
		getContentPane().setLayout(new BorderLayout());
		setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblCentar = new JLabel("Centar");
		lblCentar.setFont(new Font("Tahoma", Font.PLAIN, 21));
		JLabel lblKoordinataX = new JLabel("Koordinata X");
		lblKoordinataX.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblKoordinataY = new JLabel("Koordinata Y");
		lblKoordinataY.setFont(new Font("Tahoma", Font.PLAIN, 20));
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
		
		JLabel lblRadius = new JLabel("Polupre\u010Dnici");
		lblRadius.setFont(new Font("Tahoma", Font.PLAIN, 21));
		JLabel lblUnutranji = new JLabel("Unutra\u0161nji");
		lblUnutranji.setFont(new Font("Tahoma", Font.PLAIN, 20));
		JLabel lblSpoljni = new JLabel("Spoljni");
		lblSpoljni.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUnut = new JTextField();
		txtUnut.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtUnut.setColumns(10);
		txtSpolj = new JTextField();
		txtSpolj.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtSpolj.setColumns(10);
		
		JButton btnBojaUnutrasnjosti = new JButton("Boja unutrasnjosti");
		btnBojaUnutrasnjosti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bojaUnut=JColorChooser.showDialog(null, "Izaberite boju", Color.WHITE);
			}
		});
		
		JButton btnBojaIvica = new JButton("Boja ivica");
		btnBojaIvica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bojaIvice=JColorChooser.showDialog(null, "Izaberite boju", Color.BLACK);
			}
		});
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(33)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblRadius, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblKoordinataX, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblKoordinataY, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(lblSpoljni, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUnutranji, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGap(40)
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addComponent(txtKoordY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
											.addComponent(btnBojaUnutrasnjosti)
											.addGap(82))
										.addGroup(gl_contentPanel.createSequentialGroup()
											.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(txtUnut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtSpolj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(txtKoordX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
											.addContainerGap(334, Short.MAX_VALUE))))
								.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnBojaIvica)
									.addGap(115))))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblCentar)
							.addContainerGap(603, Short.MAX_VALUE))))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(31)
							.addComponent(lblCentar)
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKoordinataX)
								.addComponent(txtKoordX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblKoordinataY)
								.addComponent(txtKoordY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(43)
							.addComponent(lblRadius, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUnutranji, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtUnut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSpoljni, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtSpolj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(112)
							.addComponent(btnBojaUnutrasnjosti)
							.addGap(66)
							.addComponent(btnBojaIvica)))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try
						{
						int i=Integer.parseInt(getTxtKoordX());
						int j=Integer.parseInt(getTxtKoordY());
						Point p=new Point(i,j);
						Donut d=new Donut(p,Integer.parseInt(getTxtSpolj()),Integer.parseInt(getTxtUnut()));
						setOk(true);
						dispose();
						}
						catch(NumberFormatException ex)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Poluprecnici moraju da budu veci od nule i poluprecnik unutrasnjeg kruga mora da bude manji od poluprecnika velikog kruga!", "Greška", JOptionPane.WARNING_MESSAGE);
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
	public Color getBojaUnut() {
		return bojaUnut;
	}

	public void setBojaUnut(Color bojaUnut) {
		this.bojaUnut = bojaUnut;
	}

	public Color getBojaIvice() {
		return bojaIvice;
	}

	public void setBojaIvice(Color bojaIvice) {
		this.bojaIvice = bojaIvice;
	}


	
	public void setTxtKoordXEditable(boolean b)
	{
		this.txtKoordX.setEditable(b);
	}
	
	public void setTxtKoordYEditable(boolean b)
	{
		this.txtKoordY.setEditable(b);
	}
	
	public void setTxtUnutEditable(boolean b)
	{
		this.txtUnut.setEditable(b);
	}
	
	public void setTxtSpoljEditable(boolean b)
	{
		this.txtSpolj.setEditable(b);
	}
	

	public String getTxtUnut() {
		return txtUnut.getText();
	}

	public void setTxtUnut(String txtUnut) {
		this.txtUnut.setText(txtUnut);
	}

	public String getTxtSpolj() {
		return txtSpolj.getText();
	}

	public void setTxtSpolj(String txtSpolj) {
		this.txtSpolj.setText(txtSpolj);
	}

	public String getTxtKoordX() {
		return txtKoordX.getText();
	}

	public void setTxtKoordX(String txtKoordX) {
		this.txtKoordX.setText(txtKoordX);
	}

	public String getTxtKoordY() {
		return txtKoordY.getText();
	}

	public void setTxtKoordY(String txtKoordY) {
		this.txtKoordY.setText(txtKoordY);
	}
	
	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

}
