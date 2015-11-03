package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.NumberFormatter;

public class GUI extends JFrame {

	// public JTextField txtFieldPizza = new JTextField();
	// public JTextField txtFieldSlices = new JTextField();
	// public JTextField txtFieldPeople = new JTextField();

	public JLabel lblPizza = new JLabel("Pizza's: ");
	public JLabel lblSlices = new JLabel("Slices:  ");
	public JLabel lblPeople = new JLabel("People: ");

	public JPanel pnlPeople = new JPanel();
	public JPanel pnlSlices = new JPanel();
	public JPanel pnlPizza = new JPanel();
	public JPanel pnlButtons = new JPanel();

	public JButton btnSend = new JButton("Senden");

	public GUI() {
		this.setSize(200, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		getContentPane().add(pnlPizza);
		getContentPane().add(pnlPeople);
		getContentPane().add(pnlSlices);
		getContentPane().add(pnlButtons);
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		NumberFormat format = NumberFormat.getInstance(); //
		format.setGroupingUsed(false); //
		NumberFormatter formatter = new NumberFormatter(format); //
		formatter.setAllowsInvalid(false); //

		JFormattedTextField txtFieldPizza = new JFormattedTextField(formatter);
		JFormattedTextField txtFieldSlices = new JFormattedTextField(formatter);
		JFormattedTextField txtFieldPeople = new JFormattedTextField(formatter);

		pnlPizza.setLayout(new BoxLayout(pnlPizza, BoxLayout.X_AXIS));
		pnlPeople.setLayout(new BoxLayout(pnlPeople, BoxLayout.X_AXIS));
		pnlSlices.setLayout(new BoxLayout(pnlSlices, BoxLayout.X_AXIS));
		pnlButtons.setLayout(new BoxLayout(pnlButtons, BoxLayout.X_AXIS));

		Dimension d = new Dimension(100, 20);
		txtFieldSlices.setMaximumSize(d);
		txtFieldPeople.setMaximumSize(d);
		txtFieldPizza.setMaximumSize(d);

		pnlPizza.add(lblPizza);
		pnlPizza.add(txtFieldPizza);

		pnlPeople.add(lblPeople);
		pnlPeople.add(txtFieldPeople);

		pnlSlices.add(lblSlices);
		pnlSlices.add(txtFieldSlices);

		pnlButtons.add(btnSend);
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkTxtField(txtFieldPeople) && checkTxtField(txtFieldSlices) && checkTxtField(txtFieldPizza)) {

					int slicesAll = Integer.parseInt(txtFieldPizza.getText())
							* Integer.parseInt(txtFieldSlices.getText());
					int slicesPerPeople = slicesAll / Integer.parseInt(txtFieldPeople.getText());
					int rest = slicesAll % Integer.parseInt(txtFieldPeople.getText());

					if (slicesPerPeople == 1) {
						JOptionPane.showMessageDialog(getContentPane(), "Jede Person kriegt " + slicesPerPeople
								+ " Stück Pizza und es bleiben " + rest + " über!");
					} else if (slicesPerPeople == 0) {
						JOptionPane.showMessageDialog(getContentPane(), "Ihr habt zu wenig Pizza!");
					} else {
						JOptionPane.showMessageDialog(getContentPane(), "Jede Person kriegt " + slicesPerPeople
								+ " Stücke Pizza und es bleiben " + rest + " über!");
					}
				} else {
					JOptionPane.showMessageDialog(getContentPane(), "Bitte geben Sie nur Zahlen ein die größer als 0 sind!");
				}
			}

		});

	}

	public Boolean checkTxtField(JFormattedTextField field) {
		if (Integer.parseInt(field.getText()) < 1) {
			field.setText("");
			return false;
		}
		return true;
	}
}
