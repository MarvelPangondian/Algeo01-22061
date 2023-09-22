
import java.util.*;
import java.lang.*;
public class Matrix {
	static Scanner scan = new Scanner(System.in);
	//attributes
	int row,col;
	double[][] content;
	
	// initial attributes, for determinant;
	int num_switch = 0;
	double K = 1;
	boolean solvable = true;
	
	
	//Konstruktor
	public Matrix(int r, int c) {
		this.row = r;
		this.col = c;
		this.content = new double[r][c];
	}
	
	// Selektor
	public int getRow() {
		return this.row;
	}
	public int getCol() {
		return this.col;
	}
	public double getElmt(int r, int c) {
		return this.content[r][c];
		
	}
	
	//Primitif
	
	public boolean isMatrixIdxValid(int r, int c) {
		return (r >= 0 && r < this.getRow() && c >= 0 && c < this.getCol());
	}
	
	public int getLastIdxRow() {
		return this.row -1;
	}
	
	public int getLastIdxCol() {
		return this.col - 1;
	}
	
	// Setter
	public void setElmt(int r, int c, double x) {
		this.content[r][c] = x;
	}
	
	// Reader
	public void readMatrix() {
		int r,c;
		for (r = 0 ; r < this.row ; ++r) {
			for (c = 0 ; c < this.col ; ++c ) {
				this.setElmt(r, c,scan.nextDouble());
			}
		}
	}
	
	
	public Matrix copyMatrix() {
		Matrix matOut = new Matrix(this.getRow(),this.getCol());
		int row,col;
		for (row = 0 ; row < matOut.getRow() ; ++row) {
			for (col = 0 ; col < matOut.getCol() ; ++col) {
				matOut.setElmt(row, col, this.getElmt(row, col));
			}
		}
		
		return matOut;
		
	}
	
	public void displayMatrix() {
		int r,c;
		for (r = 0 ; r < this.row ; ++r) {
			for (c = 0 ; c < this.col ; ++c) {
				if (c != this.col - 1) {
					System.out.print(this.getElmt(r, c));
					System.out.print(" ");
				
				}
				else {
					System.out.print(this.getElmt(r, c));
				}
			}
			System.out.print("\n");
			
		}
	}
	
	public void switchRow(int r1, int r2) {
		double[] temp = this.content[r1];
		this.content[r1] = this.content[r2];
		this.content[r2] = temp;
		
		
	
	}
	public int leadingOneRowIdx(int r) {
		// mencari letak leading one pada sebuah row, mengembalikan index kolom letak leading one pada row r;
		int idx,col;
		idx = 9999; // mark, if no leading one
		
		for (col = 0 ; col < this.getCol() ; ++col) {
			if (this.getElmt(r, col) == 1) {
				idx = col;
				break;
			}
		}
		return idx;
		
		
		
	}
	
	public static double determinanReduksi(Matrix mat) {
		Matrix mhasil;
		mhasil = SPL.gauss(mat);
		double det = Math.pow(-1, mhasil.num_switch);
		int row,col;
		for (row = 0 ; row <= mhasil.getLastIdxRow() ; ++row) {
			for (col = 0 ; col <= mhasil.getLastIdxCol() ; ++col) {
				if (row == col) {
					det *= mhasil.getElmt(row, col);
				}
		det = det / mhasil.K;
		return det;
				
			}
		}
		
		return det;
	}

}
