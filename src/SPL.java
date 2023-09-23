
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
			int id_min =  matOut.leadingOneRowIdx(row_searching) ;
//			System.out.printf("id_min current = %d \n",id_min);
			for (row_searching_after = row_searching + 1;row_searching_after <= matOut.getLastIdxRow() ; ++row_searching_after) {
				if ( matOut.leadingOneRowIdx(row_searching_after) < id_min ) {
					id_min = row_searching_after;
				}
				
			}
//			System.out.printf("id_min after = %d",id_min);
			if (id_min != matOut.leadingOneRowIdx(row_searching) ) {
				matOut.switchRow(row_searching,id_min);
				matOut.num_switch += 1;
			}
		}
		
		
		return matOut;
	}


}
