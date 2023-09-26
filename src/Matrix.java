
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
		
		
				
			}
		}
		det = det / mhasil.K;
		
		return det;
	}
	
	public static double determinanEkspansiKofaktor(Matrix mat) {
		int col;
		double det,sign;
		sign = 1;
		det = 0;
		
		if (mat.getCol() == 1 && mat.getRow() == 1) {
			return mat.getElmt(0, 0);
		}
		// dengan row pertama 
		
		for (col = 0 ; col < mat.getCol() ; ++col) {
			det += sign * mat.getElmt(0, col)* Matrix.determinanEkspansiKofaktor(Matrix.subMatrix(mat, 0, col)) ;
			sign *= (-1);
			
		}
		
		
		
		return det;
		
		
	}
	
	
	
	public static Matrix getMatrixConstant(Matrix mIn) {
		Matrix mOut = new Matrix (mIn.getRow(),1);
		int row;
		for (row = 0 ; row < mOut.getRow() ; ++row) {
			mOut.setElmt(row, 0, mIn.getElmt(row, mIn.getLastIdxCol()));
		}
		return mOut;
		
	}
	
	public static Matrix getMatrixCoefficient(Matrix mIn) {
		Matrix mOut = new Matrix(mIn.getRow(),mIn.getCol() - 1);
		int row,col;
		for (row = 0 ; row < mIn.getRow() ; ++row) {
			for (col = 0 ; col < mIn.getCol(); ++col) {
				if (col != mIn.getLastIdxCol()) {
					mOut.setElmt(row, col, mIn.getElmt(row, col));
				}
			}
		}
				
		
		return mOut;
	}
	
	
	public static Matrix subMatrix(Matrix mIn, int r, int c) {
		Matrix matOut = new Matrix(mIn.getRow() - 1, mIn.getCol() - 1);
		int row,col;
		int outRow,outCol;
		outRow = 0; outCol = 0 ;
		
		for (row = 0 ; row < mIn.getRow() ; ++row) {
			for (col = 0 ; col < mIn.getCol() ; ++col) {
				if (row != r && col != c) {
					matOut.setElmt(outRow, outCol, mIn.getElmt(row, col));
					++outCol;
					if (outCol >= matOut.getCol()) {
						outCol = 0;
						++outRow;
					}
				}
			}
		}
		return matOut;
	}
	
	public static Matrix changeColumn(Matrix mat, Matrix mat_col, int c) {
		Matrix mHasil;
		int row;
		mHasil  = mat.copyMatrix();
		// matCol terdiri dari satu kolom saja
		for (row = 0 ; row < mHasil.getRow() ; ++row) {
			mHasil.setElmt(row, c, mat_col.getElmt(row, 0));
		}
		return mHasil;
	}

	public static Matrix multiplyMatrix(Matrix m1, Matrix m2){
		Matrix mHasil = new Matrix(m1.getRow(), m2.getCol());
		int row,col,i;
		for(row = 0; row< m1.getRow(); row++){
			for(col = 0; col< m2.getCol(); col++){
				for(i = 0; i< m1.getCol(); i++){
					mHasil.content[row][col] += m1.getElmt(row,col)*m2.getElmt(col, row);
				}
			}
		}
		return mHasil;
	}
	public void identityMatrix(){
		for(i = 0; i<row; i++){
			for(j = 0; j<col; j++){
				if (i == j){
					this.setElmt(row, col, 1);
				}else{
					this.setElmt(row, col, 0);
				}
			}
		}
	}
	public static Matrix inversGaussJordan(Matrix mIn){
		Matrix mOut = new Matrix(mIn.getRow(), mIn.getCol());
		mOut.identityMatrix();

	}
}
