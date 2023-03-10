package game;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Highscore extends JFrame {
	private JPanel contentPane;
	public Highscore() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JTable Ftable = new JTable();
		Ftable.setVisible(true);
		Ftable.setModel(new DefaultTableModel(
				new Object[][] {
					{"id", "name", "highscore"},
					{null, null, null}
				},
				new String[] {
					"id", "name", "highscore"
				}
			));
			
		Ftable.setBounds(91, 55, 504, 336);
		contentPane.add(Ftable);
	}
}
