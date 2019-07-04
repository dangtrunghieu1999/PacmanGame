package character;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class Maze {
	ArrayList<String> lines;
	int row, column;
	int rows, columns;
	int row2, column2;
	int countPill;
	// parameter load map
	public Maze(int m) {
		try {
			countPill = 0;
			lines = new ArrayList<String>();
			Scanner s = new Scanner(new File("data.txt"));
			int r = 0;
			int r2 = 0;
			while (s.hasNextLine()) {
				String line = s.nextLine();
				lines.add(line);
				if (line.contains("5")) {
					row = r;
					column = line.indexOf('5');
				}
				if (line.contains("4")) {
					row2 = r2;
					column2 = line.indexOf('4');
				}
				for (int i=0; i<line.length(); i++) {
					if (line.charAt(i) == '2') {
						countPill++;
					} else if (line.charAt(i) == '3') {
						countPill++;					}
				}
				r++;
				r2++;
			}
			s.close();
		
			rows = lines.size();
			columns = lines.get(0).length();
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
		
		public char charAt(int r, int c) {
			return lines.get(r).charAt(c);
		}

		public char[][] getCells() {
			char[][] cells = new char[rows][columns];
			for (int r=0; r<rows; r++) {
				System.arraycopy(lines.get(r).toCharArray(), 0, cells[r], 0, columns);
			}
			return cells;
		}
	
		
}
