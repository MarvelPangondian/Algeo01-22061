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

    public static String getPolinomEq(Matrix solMatrix){
        String Eq = "f(x) = ";
        int row;
        for(row = 0; row < solMatrix.getRow(); row++){
            if (row == 0 && solMatrix.getElmt(row, solMatrix.getCol()-1) < 0){
                Eq += String.format("-%f ", -1*solMatrix.getElmt(row, solMatrix.getCol()-1));
            }else if(row ==0 ){
                Eq += String.format("%f ", solMatrix.getElmt(row, solMatrix.getCol()-1));
            }else{
                if (solMatrix.getElmt(row, solMatrix.getCol()-1) < 0){
                    Eq += String.format("- %fx^%d ", -1*solMatrix.getElmt(row, solMatrix.getCol()-1), row);
                }else{
                    Eq += String.format("+ %fx^%d ", solMatrix.getElmt(row, solMatrix.getCol()-1), row);
                }
            }
        }
        return Eq;
    }
}
