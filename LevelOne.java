package game;

import java.sql.*;

import java.util.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import java.awt.Button;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;

public class LevelOne extends JFrame {
	int brA=0;
	int brB=0;
	// poleto na igrata, koeto e dvumeren masiv
	String[][] grid = new String[6][6];
	// dolu cheirite sa masivi, koito zapazvat kordinatite na obektite v nivoto,
	// kato parviqt im kordinat e chisloto deleno na 100, a vtoriq ostataka mu pri
//	 delenie na 100
	int kutiiki[] = { 404 };
	int winCon[] = { 300 };
	int geroi = 305;
	int steni[] = { 0 };

//	int geroi = 401;
//	int[]kutiiki= {302, 303, 204, 405, 407};
//	int[]winCon= {704, 705, 706, 707, 708};
//	int steni[]= {3,4,5,6,7, 101,100,102,103,107, 200,203,207,208,209,210, 300,310, 400,404,408,410, 501,500,502,504,508,510, 601,604,605,606,607,608,610, 701,710, 801,802,803,804,805,806,807,808,809,810};
//	String[][] grid=new String[9][11];

//	int geroi = 404;
//	int[] kutiiki = { 303, 403, 305, 504 };
//	int[] winCon = { 103, 401, 306, 604 };
//	int steni[] = { 2, 3, 4, 102, 104, 202, 204, 205, 206, 207, 301, 300, 302, 307, 400, 405, 406, 407, 501,
//			500, 502, 503, 505, 603, 605, 703, 704, 705 };
//	String[][] grid = new String[8][8];

	// promenlivi za zapisvaneto na imeto na igracha i hodovete za koito e minal
	// nivoto
	int highscore = 0;
	int highscore1 = 0;
	String name;
	// movement e promenlivata koqto zapazva na koq posoka e bil mradnat geroq ili
	// dali nivoto e bilo restartirano/varnato nazad
	String movement = "w";
	// tezi v promenlivi prosto sa nuzhni na metodite da rabotqt
	int br = 0;
	int geroidefault = geroi;
	int kutiikidefault[] = new int[20];
	int moves = 0;
	LinkedList<Integer> undoStack = new LinkedList<>();
	boolean counterBug = true;
	int brHighscore = 0;
	private JPanel contentPane;
	JLabel[][] lblArr = new JLabel[grid.length][grid[0].length];
	JTable Ftable = new JTable();
	JTable Htable = new JTable();
	JTable Utable = new JTable();
	int temp=0;
	int sus = 0;
	boolean amogus = true;
	boolean amongus = true;
	static int highscoreText = 0;
	static File file = new File("highscore1.txt");
	static LinkedList<String> list = new LinkedList<>();
	Scanner sc = new Scanner(System.in);
	private JTextField txtYourMoves;
	private JTextField nameField;
	private JTextField textField;

	public LevelOne() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// buton koito marda geroq nagore
		JButton btnMoveUp = new JButton("^");
		btnMoveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMovement("w");
				play();
				if (levelCompletion()) {
					play();
				}
			}
		});
		btnMoveUp.setBounds(76, 70, 45, 45);
		contentPane.add(btnMoveUp);

		// buton koito marda geroq nalqvo
		JButton btnMoveLeft = new JButton("<-");
		btnMoveLeft.setBounds(21, 126, 45, 45);
		contentPane.add(btnMoveLeft);
		btnMoveLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMovement("a");
				play();
				if (levelCompletion()) {
					play();
				}
			}
		});

		// buton koito marda geroq nadolu
		JButton btnMoveDown = new JButton("v");
		btnMoveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMovement("s");
				play();
				if (levelCompletion()) {
					play();
				}
			}
		});
		btnMoveDown.setBounds(76, 126, 45, 45);
		contentPane.add(btnMoveDown);
		

//		
//		


		// buton koito marda geroq nadqsno
		JButton btnMoveRight = new JButton("->");
		btnMoveRight.setBounds(131, 126, 45, 45);
		contentPane.add(btnMoveRight);
		btnMoveRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMovement("d");
				play();
				if (levelCompletion()) {
					play();
				}
			}
		});

		Button buttonStart = new Button("Start game");
		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setGrid();
				printLevel();
				printGrid();
			}
		});
		buttonStart.setBounds(21, 357, 158, 74);
		buttonStart.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(buttonStart);

		// restart
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMovement("r");
				play();
			}
		});
		btnRestart.setBounds(21, 22, 155, 28);
		contentPane.add(btnRestart);

		// undo
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (moves != 0) {
					setMovement("z");
					moves--;
					moves--;
					setGrid();
					printLevel();
					printGrid();
					play();
				}
			}
		});
		btnUndo.setBounds(21, 183, 158, 28);
		contentPane.add(btnUndo);

		// move counter
		txtYourMoves = new JTextField();
		txtYourMoves.setBackground(SystemColor.controlHighlight);
		txtYourMoves.setText("Your Moves: ");
		txtYourMoves.setBounds(21, 234, 158, 34);
		contentPane.add(txtYourMoves);
		txtYourMoves.setColumns(10);

		// shows highscore
		textField = new JTextField();
		textField.setText("Highscore: ");
		textField.setColumns(10);
		textField.setBackground(SystemColor.controlHighlight);
		textField.setBounds(21, 292, 158, 34);
		contentPane.add(textField);
	}

	// izchakva
	public static void waiting() {
		try {
			Thread.sleep(800);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	// dava highscore
	public int giveHighscore(File file) {
		Iterator it1 = list.iterator();
		int br1 = 0;
		String copy1 = "";
		while (it1.hasNext()) {
			Object sus = it1.next();
			if (!sus.toString().equals(",")) {
				copy1 = copy1 + sus.toString();
			} else if (br1 == 0) {
				int maikati = Integer.parseInt(copy1);
				highscore1 = Integer.parseInt(copy1);
				br1 = 1;
				copy1 = "";
			} else if (Integer.parseInt(copy1) < highscore1) {
				highscore1 = Integer.parseInt(copy1);
				copy1 = "";
			} else
				copy1 = "";
		}
		return highscore1;
	}

	// marda kordinatite na geroq pri saotvetniqt buton
	public void normalCharacterMovement(int geroi, int geroidefault, int[] kutiikidefault) {
		switch (movement) {
		case "w":
			geroi = geroi - 100;
			this.geroi = geroi;
			break;
		case "a":
			geroi = geroi - 1;
			this.geroi = geroi;
			break;
		case "s":
			geroi = geroi + 100;
			this.geroi = geroi;
			break;
		case "d":
			geroi = geroi + 1;
			this.geroi = geroi;
			break;
		case "r":
			for (int i = 0; i < kutiiki.length; i++) {
				this.kutiiki[i] = kutiikidefault[i];
			}
			geroi = geroidefault;
			this.geroi = geroi;
			this.moves = -1;
			break;
		case "z":
			undo();
			break;
		}
	}

	// spira geroq da izliza ot ochertaniqta na nivoto
	public void outsideGridCharacterMovemen(int geroi) {
		if (geroi % 100 >= grid[0].length || geroi / 100 >= grid.length || 0 > geroi % 100 || 0 > geroi / 100) {
			switch (movement) {
			case "w":
				geroi = geroi + 100;
				break;
			case "a":
				geroi = geroi + 1;
				break;
			case "s":
				geroi = geroi - 100;
				break;
			case "d":
				geroi = geroi - 1;
				break;
			}
		}
		this.geroi = geroi;
	}

	// spira geroq da vliza v steni
	public void inWallCharacterMovement(int geroi) {
		if (steni[0] != 0) {
			for (int k = 0; k < steni.length; k++) {
				if (geroi / 100 == steni[k] / 100 && geroi % 100 == steni[k] % 100) {
					switch (movement) {
					case "w":
						geroi = geroi + 100;
						break;
					case "a":
						geroi = geroi + 1;
						break;
					case "s":
						geroi = geroi - 100;
						break;
					case "d":
						geroi = geroi - 1;
						break;
					}
				}
			}
		}
		this.geroi = geroi;
	}

	// buta kutiite, kogato igracha se opita da gi butne
	public void normalBoxPushingMovement(int geroi, int[] kutiiki, int i) {
		if (geroi == kutiiki[i]) {
			switch (movement) {
			case "w":
				kutiiki[i] = kutiiki[i] - 100;
				break;
			case "a":
				kutiiki[i] = kutiiki[i] - 1;
				break;
			case "s":
				kutiiki[i] = kutiiki[i] + 100;
				break;
			case "d":
				kutiiki[i] = kutiiki[i] + 1;
				break;
			}
		}
		this.kutiiki[i] = kutiiki[i];
	}

	// prechi na kutiite da izizat ot ochertaniqta na nivoto
	public void outsideGridBoxPushingMovement(int geroi, int[] kutiiki, int i) {
		if (kutiiki[i] % 100 >= grid[0].length || kutiiki[i] / 100 >= grid.length || 0 > kutiiki[i] % 100
				|| 0 > kutiiki[i] / 100) {
			switch (movement) {
			case "w":
				kutiiki[i] = kutiiki[i] + 100;
				geroi = geroi + 100;
				break;
			case "a":
				kutiiki[i] = kutiiki[i] + 1;
				geroi = geroi + 1;
				break;
			case "s":
				kutiiki[i] = kutiiki[i] - 100;
				geroi = geroi - 100;
				break;
			case "d":
				kutiiki[i] = kutiiki[i] - 1;
				geroi = geroi - 1;
				break;
			}
		}
		this.kutiiki[i] = kutiiki[i];
		this.geroi = geroi;
	}

	// spira kutiite da vlizat v steni
	public void inWallBoxPushingMovement(int geroi, int[] kutiiki, int[] steni, int i) {
		if (steni[0] != 0) {
			for (int j = 0; j < steni.length; j++) {
				if (kutiiki[i] / 100 == steni[j] / 100 && kutiiki[i] % 100 == steni[j] % 100) {
					switch (movement) {
					case "w":
						kutiiki[i] = kutiiki[i] + 100;
						geroi = geroi + 100;
						break;
					case "a":
						kutiiki[i] = kutiiki[i] + 1;
						geroi = geroi + 1;
						break;
					case "s":
						kutiiki[i] = kutiiki[i] - 100;
						geroi = geroi - 100;
						break;
					case "d":
						kutiiki[i] = kutiiki[i] - 1;
						geroi = geroi - 1;
						break;
					}
				}
			}
		}
		this.kutiiki[i] = kutiiki[i];
		this.geroi = geroi;
	}

	// buta dve kutii ednovremenno, kogato igracha se opita da gi butne
	public void inBoxBoxPushingMovement(int geroi, int[] kutiiki, int[] steni, int i) {
		for (int j = 0; j < kutiiki.length; j++) {
			if (i != j) {
				if (kutiiki[i] / 100 == kutiiki[j] / 100) {
					if (kutiiki[i] % 100 == kutiiki[j] % 100) {
						switch (movement) {
						case "w":
							kutiiki[i] = kutiiki[i] - 100;
							break;
						case "a":
							kutiiki[i] = kutiiki[i] - 1;
							break;
						case "s":
							kutiiki[i] = kutiiki[i] + 100;
							break;
						case "d":
							kutiiki[i] = kutiiki[i] + 1;
							break;
						}
					}
				}
			}
		}
		this.kutiiki[i] = kutiiki[i];
	}

	// v linkedlist se zapazvat hodovete na igracha za da ima undo buton
	public void fillUndo() {
		if (!getMovement().equals("z")) {
			for (int i = 0; i < kutiiki.length; i++) {
				undoStack.push(kutiiki[i]);
			}
			undoStack.push(geroi);
		}
	}

	// vrashta geroq i mradnatite kutiiki edin hod nazad
	public void undo() {
		if (undoStack.size() != 0) {
			this.geroi = undoStack.pop();
			for (int i = kutiiki.length - 1; i >= 0; i--) {
				kutiiki[i] = undoStack.pop();
			}
		}
	}

	// bug test, koito beshe nuzhen da razreshim edni bugove
	public boolean bugTest(int geroi, int[] kutiiki, int[] steni, boolean counterBug) {
		boolean inWallCharacter = false;
		boolean inWallBox = false;
		boolean inBoxCharacter = false;
		boolean outsideGridBox = false;
		for (int i = 0; i < kutiiki.length; i++) {
			if (geroi == kutiiki[i]) {
				inBoxCharacter = true;
				System.out.println("cum1");
			}
			if (kutiiki[i] % 100 >= grid[0].length || kutiiki[i] / 100 >= grid.length || 0 > kutiiki[i] % 100
					|| 0 > kutiiki[i] / 100) {
				outsideGridBox = true;
				System.out.println("cum2");
			}
			for (int j = 0; j < kutiiki.length; j++) {
				if (geroi / 100 == steni[j] / 100 && geroi % 100 == steni[j] % 100) {
					inWallCharacter = true;
					System.out.println("cum3");
				}
				if ((kutiiki[i] / 100 == steni[j] / 100 && kutiiki[i] % 100 == steni[j] % 100)) {
					inWallBox = true;
					System.out.println("cum4");
				}
			}
		}
		if (geroi % 100 >= grid[0].length || geroi / 100 >= grid.length || 0 > geroi % 100 || 0 > geroi / 100
				|| inWallCharacter || inWallBox || inBoxCharacter || outsideGridBox) {
			counterBug = false;
			this.counterBug = counterBug;
			return true;
		} else {
			return false;
		}
	}

	// vrashta kutiikite na nachalnoto im mqsto v nivoto
	public void setKutiikiDefault() {
		for (int i = 0; i < kutiiki.length; i++) {
			kutiikidefault[i] = kutiiki[i];
		}
		br = 1;
	}

	// runva vsichki metodi, koito interactvat s jframe-a
	public void play() {
		showHighscore(file);
		if(temp==0) {
			geroidefault=geroi;
		
			temp++;
		}
		if (!levelCompletion()) {
			if (br < 1)
				setKutiikiDefault();
			if (movement.equals("a") || movement.equals("d") || movement.equals("w") || movement.equals("s")
					|| movement.equals("r") || movement.equals("z")) {
				fillUndo();
				normalCharacterMovement(geroi, geroidefault, kutiikidefault);
				outsideGridCharacterMovemen(geroi);
				inWallCharacterMovement(geroi);
				for (int i = 0; i < kutiiki.length; i++) {
					normalBoxPushingMovement(geroi, kutiiki, i);
					outsideGridBoxPushingMovement(geroi, kutiiki, i);
					inWallBoxPushingMovement(geroi, kutiiki, steni, i);
					inBoxBoxPushingMovement(geroi, kutiiki, steni, i);
				}
				moves++;
				txtYourMoves.setText("Your Moves: " + moves);
				setGrid();
				printLevel();
				printGrid();
				setMovement("");
			}
		} else {
			JPanel contentPane1 = new JPanel();
			contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane1);
			contentPane1.setLayout(null);
			
			nameField = new JTextField();
			nameField.setText("");
			nameField.setColumns(10);
			nameField.setBackground(SystemColor.controlHighlight);
			nameField.setBounds(200, 200, 200, 60);
			contentPane1.add(nameField);
			
			JButton enter = new JButton("Enter");
			enter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					name=nameField.getText();
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con;
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","");
						Statement myStmt = con.createStatement();
							myStmt.executeUpdate("insert into `highscore` (hname,score) value ('"+name+"', '"+moves+"')");
							ResultSet myRs1 = myStmt.executeQuery("select * from highscore");
							DefaultTableModel model1 = (DefaultTableModel) Htable.getModel();
							DefaultTableModel model2 = (DefaultTableModel) Ftable.getModel();
							while (myRs1.next()) {
								model1.addRow(new Object[] {myRs1.getString("hid"),myRs1.getString("hname"),myRs1.getString("score")});
							}
							myStmt.executeUpdate("insert into `fails` (fname,fscore) value ('"+name+"', '"+moves+"')");
							ResultSet myRs2 = myStmt.executeQuery("select * from fails");
							while (myRs2.next()) {
								model2.addRow(new Object[] {myRs2.getString("fid"),myRs2.getString("fname"),myRs2.getString("fscore")});
							}
						}catch (Exception exc) {
							exc.printStackTrace();
						}
				}
			});
			enter.setBounds(450, 200, 158, 60);
			contentPane1.add(enter);
			
			JButton remove = new JButton("Remove");
			remove.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con;
						con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Demo","root","");
						Statement myStmt = con.createStatement();
						myStmt.executeUpdate("delete from `highscore` where hname= '"+name+"'");
						myStmt.executeUpdate("delete from `fails` where fname= '"+name+"'");
						}catch (Exception exc) {
							exc.printStackTrace();
						}
				}
			});
			remove.setBounds(450, 300, 158, 60);
			contentPane1.add(remove);
			
			JButton table = new JButton("Table");
			table.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			Fhighscore frame1 = new Fhighscore();
			frame1.setVisible(true);
			Highscore frame2 = new Highscore();
			frame2.setVisible(true);
				}
			});
			table.setBounds(450, 380, 158, 60);
			contentPane1.add(table);
			
	
			lvlComplete();

			highscoreText = 0;

		}
	}

	// pokazva highscorea v jframe-a
	public void showHighscore(File file) {
		if (highscoreText == 0) {
			textField.setText("Highscore: " + giveHighscore(file));
			highscoreText = 1;
		}
	}

	// zapazva scorea na vseki igrach minal nivoto v file
	public void setHighscore(File file) {

		PrintWriter fw = null;
		try {
			fw = new PrintWriter(file);
			if (brHighscore != 1) {
				if (!list.isEmpty()) {
					Iterator it = list.iterator();
					while (it.hasNext()) {
						String amongus = it.next().toString();
						fw.write(amongus);
					}
				}
			}
			fw.print(moves);
			fw.print(",");
			String amongus = Integer.toString(moves);
			list.add(amongus);
			list.add(",");
			if (brHighscore == 1) {
				if (moves < highscore) {
					highscore = moves;
				}
			} else
				highscore = moves;
			brHighscore = 1;
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// pokazva finalen ekran kogato nivoto e pobedeno
	public void lvlComplete() {
		waiting();
		System.out.println(
				"========================================================================================================");
		System.out.println(
				"                               _____   _____                    _____            _____  _______   _____ ");
		System.out.println(
				" |       |     /  |           |       |     |       /|     /|  |     |  |       |          |     |      ");
		System.out.println(
				" |       |    /   |           |       |     |      / |    / |  |     |  |       |          |     |      ");
		System.out.println(
				" |       |   /    |           |       |     |     /  |   /  |  |_____|  |       |_____     |     |_____ ");
		System.out.println(
				" |       |  /     |           |       |     |    /   |  /   |  |        |       |          |     |      ");
		System.out.println(
				" |       | /      |           |       |     |   /    | /    |  |        |       |          |     |      ");
		System.out.println(
				" |_____  |/       |_____      |_____  |_____|  /     |/     |  |        |_____  |_____     |     |_____ ");
		System.out.println();
		System.out.println(
				"========================================================================================================");
		System.out.println("in " + moves + " moves");
	}

	public void setGrid(String[][] grid) {
		this.grid = grid;
	}

	public void setKutiiki(int[] kutiiki) {
		this.kutiiki = kutiiki;
	}

	public void setWinCon(int[] winCon) {
		this.winCon = winCon;
	}

	public void setGeroi(int geroi) {
		this.geroi = geroi;
	}

	public void setSteni(int[] steni) {
		this.steni = steni;
	}

	public String getMovement() {
		return movement;
	}

	public void setMovement(String movement) {
		this.movement = movement;
	}

	// proverqva koga e minato nivoto
	public boolean levelCompletion() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == "__") {
					return false;
				}
			}
		}

		return true;
	}

	// printira bezpolezen tekst
	public void printLevel() {
		System.out.println("Level 1 ");
	}

	// printira "frame-ovete" na nivoto
	public void printGrid() {

		for (int i = 0; i < grid[0].length + 1; i++) {
			System.out.print("--");
		}
		System.out.println();
		for (int i = 0; i < grid.length; i++) {
			System.out.print("|");
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.print("|");
			System.out.println();
		}
		for (int i = 0; i < grid[0].length + 1; i++) {
			System.out.print("--");
		}
		System.out.println();
	}

	// aktualizira grida s kordinatite na obektite v nego
	public void setGrid() {
		if (sus == 0) {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[0].length; j++) {
					lblArr[i][j] = new JLabel(new ImageIcon(LevelOne.class.getResource("geroi.png")));
					lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
					getContentPane().add(lblArr[i][j]);
				}
			}
			sus = 1;
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = "  ";

				lblArr[i][j].setVisible(false);

				lblArr[i][j] = new JLabel(new ImageIcon(LevelOne.class.getResource("pod.png")));
				lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
				getContentPane().add(lblArr[i][j]);
				lblArr[i][j].setVisible(true);

				if (i == geroi / 100 && j == geroi % 100) {
					grid[i][j] = "OO";
					lblArr[i][j].setVisible(false);
					lblArr[i][j] = new JLabel(new ImageIcon(LevelOne.class.getResource("geroi.png")));
					lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
					getContentPane().add(lblArr[i][j]);
					lblArr[i][j].setVisible(true);
					amogus = false;
				}
				for (int k = 0; k < winCon.length; k++) {
					if (i == winCon[k] / 100 && j == winCon[k] % 100) {
						grid[i][j] = "__";
						if (amogus) {
							lblArr[i][j].setVisible(false);
							lblArr[i][j] = new JLabel(new ImageIcon(LevelOne.class.getResource("pressure plate.png")));
							lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
							getContentPane().add(lblArr[i][j]);
							lblArr[i][j].setVisible(true);
						}
						amongus = false;
					}
				}
				for (int k = 0; k < winCon.length; k++) {
					if (i == kutiiki[k] / 100 && j == kutiiki[k] % 100) {
						grid[i][j] = "<>";
						if (amongus) {
							lblArr[i][j].setVisible(false);
							lblArr[i][j] = new JLabel(new ImageIcon(LevelOne.class.getResource("kutiq.png")));
							lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
							getContentPane().add(lblArr[i][j]);
							lblArr[i][j].setVisible(true);
						} else {
							lblArr[i][j].setVisible(false);
							lblArr[i][j] = new JLabel(
									new ImageIcon(LevelOne.class.getResource("kutiika varhu pressure plate.png")));
							lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
							getContentPane().add(lblArr[i][j]);
							lblArr[i][j].setVisible(true);
						}
					}
				}
				amongus = true;
				for (int k = 0; k < steni.length; k++) {
					if (steni[0] != 0)
						if (i == steni[k] / 100 && j == steni[k] % 100) {
							grid[i][j] = "[]";
							lblArr[i][j].setVisible(false);
							lblArr[i][j] = new JLabel(new ImageIcon(LevelOne.class.getResource("stena.png")));
							lblArr[i][j].setBounds(300 + j * 32, 100 + i * 32, 32, 32);
							getContentPane().add(lblArr[i][j]);
							lblArr[i][j].setVisible(true);
						}
				}
				amogus = true;
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
		
					file.createNewFile();
					FileReader fr = new FileReader(file);
					int asfddsaf;
					while ((asfddsaf = fr.read()) != -1) {
						String s = "" + (char) asfddsaf;
						list.add(s);
					}
					fr.close();
					LevelOne frame = new LevelOne();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}