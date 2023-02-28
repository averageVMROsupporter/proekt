package game;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Game {
String[][] grid = new String [6][12];
int kutiiki1[]= {407};
int winCon1[] = {302};
int geroi1 = 309;
int steni[]= {0};
public static void play (String grid[][], int kutiiki[], int geroi, int winCon[], int steni[]) {
	int geroidefault=geroi;
	int kutiikidefault[] = new int[20];
	for (int i=0; i<kutiiki.length; i++) {
		kutiikidefault[i]=kutiiki[i];
	}
	while (levelCompletion(grid)) {
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
		for (int i=0; i<kutiiki.length; i++) {
			for (int j=0; j<kutiiki.length; j++) {
				if (i!=j) {
				if (kutiiki[i]/100==kutiiki[j]/100) {
					if (kutiiki[i]%100==kutiiki[j]%100) {
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
					i=0;
					j=0;
					}
				}
			}
			}
		}
		setGrid(grid, kutiiki, geroi, winCon, steni);
		printGrid(grid);
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
	
}
public static boolean levelCompletion(String grid[][]) {
	for (int i=0; i<grid.length; i++) {
		for (int j=0; j<grid[0].length; j++) {
			if (grid[i][j]=="__") {
				return true;
			}
		}
	}
	return false;
}
public static void printGrid(String grid[][]){
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
public static void setGrid(String grid[][], int kutiiki[], int geroi, int winCon[], int steni[]) {
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


