
import java.util.*;
public class Matrix {
	static Scanner scan = new Scanner(System.in);
	//attributes
	int row,col;
	double[][] content;
	
	// initial attributes, for determinant;
	int num_switch = 0;
	int k = 1;
	
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
	
	
	public void copyMatrix(Matrix mKeluar) {
		int r,c;
		
		for (r = 0 ; r < mKeluar.row ; ++r) {
			for (c = 0 ; c < mKeluar.col ; ++c) {
				mKeluar.setElmt(r, c, this.getElmt(r,c));
			}
		}
		
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

}
