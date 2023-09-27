import java.util.*;
import java.lang.Math;
public class Polinom {
    public static Matrix createLinearEq(Matrix mPoint){
        Matrix mLinEq;
        int n = mPoint.getRow();
        int row, col;
        mLinEq = new Matrix(n, n+1);
        for(row = 0; row < mLinEq.getRow(); row++){
            for(col = 0; col < mLinEq.getCol(); col++){
                if (col == mLinEq.getCol()-1){
                    mLinEq.setElmt(row, col, mPoint.getElmt(row, 1));
                }else{
                    mLinEq.setElmt(row, col, Math.pow(mPoint.getElmt(row, 0), col) );
                }
            }
        }
        return mLinEq;
    }

    public static Matrix getPolinomSol(Matrix mLinEq){
        Matrix solMatrix;
        solMatrix = SPL.gaussjordan(mLinEq);
        solMatrix = Matrix.getMatrixConstant(solMatrix);
        return solMatrix;
    }
}
