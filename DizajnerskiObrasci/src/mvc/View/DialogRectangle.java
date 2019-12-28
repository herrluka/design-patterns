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

import mvc.Model.Rectangle;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DialogRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtXCoord;
	private JTextField txtYCoord;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private boolean ok;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel pnlRectangleInnerColor;
	private JPanel pnlRectangleOutlineColor;
	


	public DialogRectangle() {
		setTitle("Rectangle");
		setBounds(100, 100, 802, 508);
		getContentPane().setLayout(new BorderLayout());
		this.setModal(true);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblXKoordinata = new JLabel("X koordinata");
		lblXKoordinata.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblXKoordinata.setHorizontalTextPosition(SwingConstants.LEFT);
		lblXKoordinata.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		txtXCoord = new JTextField();
		txtXCoord.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtXCoord.setColumns(10);
		
		
		txtXCoord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		
		JLabel lblYKoordinata = new JLabel("Y koordinata");
		lblYKoordinata.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		txtYCoord = new JTextField();
		txtYCoord.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtYCoord.setColumns(10);
		
		
		txtYCoord.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
					if (c=='-') {
						e.consume();
						getToolkit().beep();
					}
			}
		});
		
		JLabel lblVisina = new JLabel("Visina");
		lblVisina.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		
		txtHeight = new JTextField();
		txtHeight.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtHeight.setColumns(10);
		
		
		JLabel lblSirina = new JLabel("Sirina");
		lblSirina.setFont(new Font("Tahoma", Font.PLAIN, 20));
			
		
		
		txtWidth = new JTextField();
		txtWidth.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtWidth.setText("");
		txtWidth.setColumns(10);
		pnlRectangleInnerColor = new JPanel();
		pnlRectangleInnerColor.setBackground(Color.WHITE);
		pnlRectangleInnerColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setPnlRectangleInnerColor(JColorChooser.showDialog(null, "Izaberite boju", Color.WHITE));
			}
		});
		
		pnlRectangleOutlineColor = new JPanel();
		pnlRectangleOutlineColor.setBackground(Color.BLACK);
		pnlRectangleOutlineColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setPnlRectangleOutlineColor(JColorChooser.showDialog(null, "Izaberite boju", Color.BLACK));
			}
		});
		
		
		
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(32)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblXKoordinata)
						.addComponent(lblYKoordinata)
						.addComponent(lblVisina)
						.addComponent(lblSirina, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtWidth)
						.addComponent(txtYCoord, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
						.addComponent(txtXCoord, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
						.addComponent(txtHeight, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED, 162, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlRectangleInnerColor, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnlRectangleOutlineColor, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE))
					.addGap(119))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblXKoordinata)
								.addComponent(txtXCoord, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtYCoord, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblYKoordinata))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtHeight, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblVisina))
							.addGap(18)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtWidth, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSirina, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(pnlRectangleInnerColor, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(pnlRectangleOutlineColor, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(165, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try
						{
							Rectangle r=new Rectangle();
							r.setHeight(Integer.parseInt(getTxtHeight()));
							r.setWidth(Integer.parseInt(getTxtWidth()));
							Integer.parseInt(getTxtXCoord());
							Integer.parseInt(getTxtYCoord());
							ok=true;
							dispose();
						}
						catch(NumberFormatException ex)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Neispravan unos podataka.Proverite da li su sva polja popunjena brojnim vrednostima!", "Greška", JOptionPane.WARNING_MESSAGE);
						}
						catch(Exception ex)
						{
							JOptionPane.showMessageDialog(new JFrame(), "Visina i sirina moraju da budu pozitivne!", "Greška", JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						ok=false;
						dispose();
	
					}
				});
				cancelButton.setActionCommand("Cancel");
			}
			GroupLayout gl_buttonPane = new GroupLayout(buttonPane);
			gl_buttonPane.setHorizontalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addGap(610)
						.addComponent(okButton, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addGap(3)
						.addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
						.addContainerGap())
			);
			gl_buttonPane.setVerticalGroup(
				gl_buttonPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_buttonPane.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl_buttonPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(okButton)
							.addComponent(cancelButton))
						.addContainerGap())
			);
			buttonPane.setLayout(gl_buttonPane);
			
			
		}
	}

	public void setTxtXCoordEnabled(boolean b)
	{
		this.txtXCoord.setEnabled(b);
	}
	
	public void setTxtYCoordEnabled(boolean b)
	{
		this.txtYCoord.setEnabled(b);
	}
	
	public void setTxtHeightEnabled(boolean b)
	{
		this.txtHeight.setEnabled(b);
	}
	
	public void setTxtWidthEnabled(boolean b)
	{
		this.txtWidth.setEnabled(b);
	}

	public String getTxtXCoord() {
		return txtXCoord.getText();
	}

	public void setTxtXCoord(String s) {
		this.txtXCoord.setText(s);
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getTxtYCoord() {
		return txtYCoord.getText();
	}

	public void setTxtYCoord(String s) {
		this.txtYCoord.setText(s);;
	}

	public String getTxtHeight() {
		return txtHeight.getText();
	}

	public void setTxtHeight(String height) {
		this.txtHeight.setText(height);
	}

	public String getTxtWidth() {
		return txtWidth.getText();
	}

	public void setTxtWidth(String width) {
		this.txtWidth.setText(width);
	}

	public Color getPnlRectangleInnerColor() {
		return pnlRectangleInnerColor.getBackground();
	}

	public void setPnlRectangleInnerColor(Color pnlRectangleInnerColor) {
		this.pnlRectangleInnerColor.setBackground(pnlRectangleInnerColor);
	}

	public Color getPnlRectangleOutlineColor() {
		return pnlRectangleOutlineColor.getBackground();
	}

	public void setPnlRectangleOutlineColor(Color pnlRectangleOutlineColor) {
		this.pnlRectangleOutlineColor.setBackground(pnlRectangleOutlineColor);
	}
	
}
