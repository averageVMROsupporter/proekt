package game;
import java.util.*;
public class ProjectTwo {

	int width=6;
	int length=12;
	String[][] grid = new String [width][length];
	int geroi=334;
	int[] kutiiki=new int[20];
	int[] winCon=new int[20];
	int[] steni=new int[20];
	
	public ProjectTwo() {
		levelSetup();
	}	
	
	public void play () {
		int geroidefault=geroi;
		int kutiikidefault[] = new int[20];
		for (int i=0; i<kutiiki.length; i++) {
			kutiikidefault[i]=kutiiki[i];
		}
		int moves=0;
		while (levelCompletion()) {
			Scanner sc=new Scanner(System.in);
			String movement;
			movement= sc.next();
			switch(movement) {
			case "w": 
				geroi=geroi-100;
				break;
			case "a": 
				geroi=geroi-1;
				break;
			case "s": 
				geroi=geroi+100;
				break;
			case "d": 
				geroi=geroi+1;
				break;
			case "r":
				for (int i=0; i<kutiiki.length; i++) {
					kutiiki[i]=kutiikidefault[i];
				}
				geroi=geroidefault;
				break;
			}
			if (geroi%100>=grid[0].length || geroi/100>=grid.length || 0>geroi%100 || 0>geroi/100) {
				switch(movement) {
				case "w": 
					geroi=geroi+100;
					break;
				case "a": 
					geroi=geroi+1;
					break;
				case "s": 
					geroi=geroi-100;
					break;
				case "d": 
					geroi=geroi-1;
					break;
				}
			}
			if(steni[0]!=0) {
				for (int k=0; k<steni.length; k++) {
					if (geroi/100==steni[k]/100) {
						if (geroi%100==steni[k]%100) {
							switch(movement) {
							case "w": 
								geroi=geroi+100;
								break;
							case "a": 
								geroi=geroi+1;
								break;
							case "s": 
								geroi=geroi-100;
								break;
							case "d": 
								geroi=geroi-1;
								break;
							}
						}
					}
				}
			}
			for (int i=0; i<kutiiki.length; i++) {
				if (geroi==kutiiki[i]) {
					switch(movement) {
					case "w": 
						kutiiki[i]=kutiiki[i]-100;
						break;
					case "a": 
						kutiiki[i]=kutiiki[i]-1;
						break;
					case "s": 
						kutiiki[i]=kutiiki[i]+100;
						break;
					case "d": 
						kutiiki[i]=kutiiki[i]+1;
						break;
					}
				}
				if (kutiiki[i]%100>=grid[0].length || kutiiki[i]/100>=grid.length || 0>kutiiki[i]%100 || 0>kutiiki[i]/100) {
					switch(movement) {
					case "w": 
						kutiiki[i]=kutiiki[i]+100;
						geroi=geroi+100;
						break;
					case "a": 
						kutiiki[i]=kutiiki[i]+1;
						geroi=geroi+1;
						break;
					case "s": 
						kutiiki[i]=kutiiki[i]-100;
						geroi=geroi-100;
						break;
					case "d": 
						kutiiki[i]=kutiiki[i]-1;
						geroi=geroi-1;
						break;
					}
				}
			if(steni[0]!=0) {
				for (int k=0; k<steni.length; k++) {
					if (kutiiki[i]/100==steni[k]/100) {
						if (kutiiki[i]%100==steni[k]%100) {
							switch(movement) {
							case "w": 
								kutiiki[i]=kutiiki[i]+100;
								geroi=geroi+100;
								break;
							case "a": 
								kutiiki[i]=kutiiki[i]+1;
								geroi=geroi+1;
								break;
							case "s": 
								kutiiki[i]=kutiiki[i]-100;
								geroi=geroi-100;
								break;
							case "d": 
								kutiiki[i]=kutiiki[i]-1;
								geroi=geroi-1;
								break;
							}
						}
					}
				}
			}
			}
			moves ++;
			setGrid();
			printGrid();
		}
		try
		{
		    Thread.sleep(800);
		}
		catch(InterruptedException ex)
		{
		    Thread.currentThread().interrupt();
		}
		System.out.println("========================================================================================================");
		System.out.println("                               _____   _____                    _____            _____  _______   _____ ");
		System.out.println(" |       |     /  |           |       |     |       /|     /|  |     |  |       |          |     |      ");
		System.out.println(" |       |    /   |           |       |     |      / |    / |  |     |  |       |          |     |      ");
		System.out.println(" |       |   /    |           |       |     |     /  |   /  |  |_____|  |       |_____     |     |_____ ");
		System.out.println(" |       |  /     |           |       |     |    /   |  /   |  |        |       |          |     |      ");
		System.out.println(" |       | /      |           |       |     |   /    | /    |  |        |       |          |     |      ");
		System.out.println(" |_____  |/       |_____      |_____  |_____|  /     |/     |  |        |_____  |_____     |     |_____ ");
		System.out.println("                                                                                                        ");
		System.out.println("========================================================================================================");
		System.out.println("in "+moves+" moves");
	}
	public void levelSetup() {
		width = 6;
		length = 12;
		kutiiki[0] = 402;
		winCon[0] = 509;
		geroi = 309;
		steni[0] = 103;
		steni[1] = 301;
	}
	public boolean levelCompletion() {
		for (int i=0; i<grid.length; i++) {
			for (int j=0; j<grid[0].length; j++) {
				if (grid[i][j]=="__") {
					return true;
				}
			}
		}
		return false;
	}
	public void printGrid(){
		for (int i=0; i<grid[0].length+1; i++) {
			System.out.print("--");	
		}
		System.out.println();
		for (int i=0; i<grid.length; i++) {
			System.out.print("|");
			for (int j=0; j<grid[0].length; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.print("|");
			System.out.println();
		}
		for (int i=0; i<grid[0].length+1; i++) {
			System.out.print("--");
		}
		System.out.println();
	}
	public void setGrid() {
	for (int i=0; i<grid.length; i++) {
		for (int j=0; j<grid[0].length; j++) {
			grid[i][j]="  ";
			if (i==geroi/100) {
				if (j==geroi%100) {
					grid[i][j]="OO";
				}
			}
			for (int k=0; k<winCon.length; k++) {
				if (i==winCon[k]/100) {
					if (j==winCon[k]%100) {
					grid[i][j]="__";
					}
				}
			}
			for (int k=0; k<kutiiki.length; k++) {
				if (i==kutiiki[k]/100) {
					if (j==kutiiki[k]%100) {
					grid[i][j]="<>";
					}
				}
			}
			if (steni[0]!=0){
				for (int k=0; k<steni.length; k++) {
					if (i==steni[k]/100) {
						if (j==steni[k]%100) {
							grid[i][j]="[]";
						}
					}
				}
			
			}
		}
	}
	}
	}

