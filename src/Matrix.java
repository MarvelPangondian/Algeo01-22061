
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

	public static boolean isDeterminantZero(Matrix mIn) {
		double det = DisplaySolution.dformat(Matrix.determinanEkspansiKofaktor(mIn));
		
		return ( Double.compare(det, (double)0.0) == 0  );
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
		boolean not_num;
		
		do {
			not_num  = false;
			for (r = 0 ; r < this.row ; ++r) {
				for (c = 0 ; c < this.col ; ++c ) {
					if (scan.hasNextDouble()) {
						this.setElmt(r, c,scan.nextDouble());
					}
					else {
						System.out.println("Input anda salah !, tolong ulangi dari awal");
						not_num = true;
						break;
					}
					
					
				}
				if (not_num) {
					break;
				}
			}
		}while(not_num);
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

	public Matrix copyMatrixCustom(int sRow, int sCol, int fRow, int fCol){
		Matrix mOut = new Matrix(fRow, fCol);
		for (row = 0 ; row < mOut.getRow() ; ++row) {
			for (col = 0 ; col < mOut.getCol() ; ++col) {
				mOut.setElmt(row, col, this.getElmt(row+sRow, col+sCol));
			}
		}
		return mOut;
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
	
	public static boolean  isPersegi(Matrix mIn) {
		return mIn.getCol() == mIn.getRow();
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
	
	public static Matrix matMultiplyByConst(Matrix mIn, double k) {
		Matrix mOut = mIn.copyMatrix();
		for (int row = 0 ; row < mOut.getRow() ; ++row) {
			for (int col = 0 ; col < mOut.getCol() ; ++col) {
				mOut.setElmt(row, col, k * mOut.getElmt(row, col));
				
			}
		}
		return mOut;
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
				double hasil = 0;
				for(i = 0; i< m2.getRow(); i++){
					hasil += m1.getElmt(row,i)*m2.getElmt(i, col);
				}
				mHasil.setElmt(row, col, hasil);
			}
		}
		return mHasil;
	}
	public void identityMatrix(){
		int i,j;
		for(i = 0; i<this.row; i++){
			for(j = 0; j<this.col; j++){
				if (i == j){
					this.setElmt(i, j, 1);
				}else{
					this.setElmt(i, j, 0);
				}
			}
		}
	}
	public static Matrix konkatMatrix(Matrix m1, Matrix m2) {
		Matrix mHasil = new Matrix(m1.getRow(),m1.getCol()+m2.getCol());
		int i,j;
		for(i = 0; i<m1.getRow();i++) {
			for(j=0; j<m1.getCol();j++) {
				mHasil.setElmt(i, j, m1.getElmt(i, j));
			}
		}
		
		for(i = 0; i<m1.getRow();i++) {
			int currCol = 0;
			for(j= m1.getCol(); j<mHasil.getCol();j++) {
				mHasil.setElmt(i, j, m2.getElmt(i, currCol));
				currCol += 1;
			}
		}
		return mHasil;
	}
	
	public static Matrix inversGaussJordan(Matrix mIn){
		int i,j;
		
		if (Matrix.isDeterminantZero(mIn)) {
			Matrix mOut = mIn.copyMatrix();
			mOut.turnToMark();
			return mOut;
		}
		if (mIn.getCol() == 1 && mIn.getRow() == 1) {
			Matrix mOutOneByOne = mIn.copyMatrix();
			mOutOneByOne.setElmt(0, 0, 1/mIn.getElmt(0, 0));
		}
		Matrix mIdentity = new Matrix(mIn.getRow(), mIn.getCol());
		mIdentity.identityMatrix();
		Matrix mKonkat = Matrix.konkatMatrix(mIn, mIdentity);
		mKonkat = SPL.gaussjordan(mKonkat);
		Matrix mHasil = mIdentity.copyMatrix();
		for(i = 0; i<mKonkat.getRow();i++) {
			int currCol = 0;
			for(j= mIn.getCol(); j<=mKonkat.getLastIdxCol();j++) {
				mHasil.setElmt(i, currCol, DisplaySolution.dformat(mKonkat.getElmt(i,j)) );
				currCol++;
			}
		}
		
		return mHasil;
	}
	
	public static Matrix getMatrixCofactor(Matrix mIn) {
		Matrix mOut = mIn.copyMatrix();
		for (int row = 0 ; row < mOut.getRow() ; ++row) {
			for (int col = 0 ; col < mOut.getCol() ; ++col) {
				if ( (row + col) % 2 == 0) {
					mOut.setElmt(row, col, Matrix.determinanEkspansiKofaktor(Matrix.subMatrix(mIn, row, col) ) );
					
				}
				else {
					mOut.setElmt(row, col, (-1)* Matrix.determinanEkspansiKofaktor(Matrix.subMatrix(mIn, row, col) ) );
				}
				
			}
		}
		return mOut;
		
	}
	public static Matrix getMatrixtranspose(Matrix mIn) {
		Matrix mOut = new Matrix(mIn.getCol(), mIn.getCol());
		
		for (int row = 0 ; row < mOut.getRow() ; ++row) {
			for (int col = 0 ; col < mOut.getCol(); ++col) {
				mOut.setElmt(row, col, mIn.getElmt(col, row));
			}
		}
		return mOut;
	}
	
	public void turnToMark() {
		final double mark = 9999;
		for (int row = 0 ; row < this.getRow() ; ++row) {
			for (int col = 0 ; col < this.getCol() ; ++col) {
				this.setElmt(row, col,mark);
			}
		}
	}	
	public static Matrix getMatrixInverseAdjoin(Matrix mIn) {
		double det = Matrix.determinanEkspansiKofaktor(mIn);
		Matrix adjoin = Matrix.getMatrixtranspose(Matrix.getMatrixCofactor(mIn));
		Matrix inverse = new Matrix(mIn.getRow(),mIn.getCol());
		if (DisplaySolution.dformat(det) == 0.0) {
			inverse.turnToMark();
			
		}
		else {
			if (inverse.getCol() == 1 && inverse.getRow() == 1) {
				inverse.setElmt(0, 0, 1/mIn.getElmt(0, 0));
			}
			else {
				inverse = Matrix.matMultiplyByConst(adjoin, 1/det);
			}
			
		}
		
		return inverse;
	}
	
	public void dfMat() {
		
		for (int row = 0 ; row < this.getRow() ; row++) {
			for (int col = 0 ; col < this.getCol() ; col++) {
				this.setElmt(row, col, DisplaySolution.dformat(this.getElmt(row, col)));
			}
		}
	}
	
	public static boolean isMarkMat(Matrix mIn) {
		boolean state = true;
		final double mark = 9999;
		for (int row = 0 ; row < mIn.getRow() ; row++) {
			for(int col = 0 ; col < mIn.getCol() ; col++) {
				if ( !(Double.compare(mIn.getElmt(row, col), mark) == 0)) {
					state = false;
				}
				
			}
		}
		return state;
	}
	
	
}
