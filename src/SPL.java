
public class SPL {
	
	public static Matrix giverowEchelon(Matrix mat) {
		Matrix mEchelon = new Matrix(mat.row,mat.col);
		mat.copyMatrix(mEchelon);
			
		int r,c;
		for (c = 0 ; c < mEchelon.getCol() ; ++c) {
			r= c ;
			
			if (mEchelon.getElmt(r, c) == 0) {
				r = r + 1;
				while (mEchelon.getElmt(r, c) == 0 && r < mEchelon.getRow()) {
					++r;
				}
				if (r != mEchelon.getRow()) {
					mEchelon.switchRow(c, r);
					mEchelon.num_switch += 1;
				}
			}
			
			if (c+1 < mEchelon.getRow()) {
				for (r = c + 1 ; r < mEchelon.getRow() ; ++ r) {
					if (mEchelon.getElmt(r, c)!= 0  ) {
						double x;
						x = mEchelon.getElmt(r, c) / mEchelon.getElmt(c, c);
						int k;
						for (k = 0 ; k <= mEchelon.getLastIdxCol() ; ++k) {
							mEchelon.setElmt(r, k, mEchelon.getElmt(r, k) - mEchelon.getElmt(c, k) * x     );
						}
					
					}
				}
			}
			
			
			
			
		}
		
		return mEchelon;
		
	}

}
