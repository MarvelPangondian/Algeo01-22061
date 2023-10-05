
public class SPL {
	
	public static Matrix gauss(Matrix matIn) {
		Matrix matOut;
		matOut = matIn.copyMatrix();
		
		// mencari elemen pada baris row yang tidak 
		int row,col;
		for (row = 0 ; row < matOut.getRow() ; ++row) {
			// mencari pada kolom col pada baris row yang memilih element bukan 0;
			col = 0 ;
			while (col <= matOut.getLastIdxCol() && matOut.getElmt(row, col) == 0) {
				++col;
			}
			
			//situasi dimana satu baris memiliki elemen 0
			if (col == matOut.getCol()) {
				col = 0;
				
			}
			
			// situasi dimana elemen matrix pada baris row semua bernilai 0, kecuali bagian augmented (matrix unsolvable)
			if (col == matOut.getLastIdxCol()) {
				matOut.solvable = false;
				
			}
			
			// mengubah elemen pada baris row dan kolom col menjadi 1 (leading 1)
			double k; // menjadi pembagi satu baris
			k = matOut.getElmt(row, col);
			int col_start_dividing;
			col_start_dividing = col;
			
			if (k != 0) {
				for (; col_start_dividing <= matOut.getLastIdxCol(); ++col_start_dividing) {
					matOut.setElmt(row, col_start_dividing, matOut.getElmt(row, col_start_dividing)/k   );
				}
			}
			if (k != 0) {
				matOut.K *= (1/k); // mengkalikan k pada atribut K objek (untuk determinan nanti)
			}
			
			
			// membuat setiap elemen dibawah leading 1 menjadi 0
			int row_start_making_zero;
			int col_start_making_zero;
			col_start_making_zero = 0;
			for (row_start_making_zero = row + 1 ; row_start_making_zero <= matOut.getLastIdxRow() ; ++row_start_making_zero) {
				double multiplier = matOut.getElmt(row_start_making_zero, col);
				for (col_start_making_zero = 0 ; col_start_making_zero <= matOut.getLastIdxCol() ;++col_start_making_zero) {
					matOut.setElmt(row_start_making_zero, col_start_making_zero, matOut.getElmt(row_start_making_zero, col_start_making_zero) - multiplier * matOut.getElmt(row, col_start_making_zero)) ;
				}
			
				
			}
			
		}
		
		
		// proses switching rows
		int row_searching, row_searching_after;
		
		for (row_searching = 0 ; row_searching <= matOut.getLastIdxRow(); ++row_searching) {
			int id_min =  row_searching ;
			for (row_searching_after = row_searching + 1;row_searching_after <= matOut.getLastIdxRow() ; ++row_searching_after) {
				if ( matOut.leadingOneRowIdx(row_searching_after) < matOut.leadingOneRowIdx(id_min) ) {
					id_min = row_searching_after;
				}
				
			}
			if (id_min != row_searching ) {
				matOut.switchRow(row_searching,id_min);
				matOut.num_switch += 1;
			}
		}
		
		
		return matOut;
	}
	
	public static Matrix cramerMethod(Matrix mIn) {
		Matrix mOut = new Matrix(mIn.getRow(),1);
		double detIn =Matrix.determinanReduksi(Matrix.getMatrixCoefficient(mIn));
		
		double x = 9999; // marker
		int row,col;
		
		for (row = 0 ; row < mIn.getRow() ; ++row) {
			mOut.setElmt(row, 0, x); // set all element of mOut to marker first
		}
		row = 0;
		if (detIn != 0) {
			for (col = 0 ; col < mIn.getCol()-1 ; ++col) {
				Matrix mTemp;
				mTemp = Matrix.changeColumn(Matrix.getMatrixCoefficient(mIn), Matrix.getMatrixConstant(mIn), col);
				double detOut = Matrix.determinanReduksi(mTemp);
				double num = ((double)detOut/detIn) ;
				mOut.setElmt(row, 0,  num);
				row++;
				
			}
		}
	
		
		
		return mOut; // if detIn == 0, cramer cannot be done, mOut will be filled with marker
		
		
	}
	public static Matrix gaussjordan(Matrix mIn){
		Matrix matOut;
		int row;
		matOut = SPL.gauss(mIn);
		for (row = matOut.getLastIdxRow() ; row >= 0; row--){
			int currCol = matOut.leadingOneRowIdx(row);
			if (currCol == 9999){
				currCol = 0;
			}
			int row_make_zero, col_make_zero;
			for(row_make_zero = row-1; row_make_zero>=0; row_make_zero--){
				double faktor = matOut.getElmt(row_make_zero, currCol);
				for(col_make_zero=0; col_make_zero<matOut.getCol(); col_make_zero++){
					matOut.setElmt(row_make_zero,col_make_zero, matOut.getElmt(row_make_zero,col_make_zero)-faktor*matOut.getElmt(row,col_make_zero));
				}
			}
			

		}
		return matOut;
	}
	public static Matrix splInverse(Matrix mIn) {
		Matrix mInvers = Matrix.getMatrixCoefficient(mIn);
		Matrix mConstant = Matrix.getMatrixConstant(mIn);
		mInvers = Matrix.inversGaussJordan(mInvers);
		Matrix mOut = Matrix.multiplyMatrix(mInvers, mConstant);
		return mOut;
	}
	
	
}
