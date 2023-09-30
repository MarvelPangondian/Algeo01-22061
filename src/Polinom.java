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

    public static double getTaksiran(Matrix solMatrix, double x){
        int i,j;
        double taksiran = 0;
        for(i = 0; i<solMatrix.getRow(); i++){
            for(j=0; j<solMatrix.getCol(); j++){
                taksiran += solMatrix.getElmt(i,j)*Math.pow(x,i);
            }
        }
        return taksiran;
    }

    public static Matrix readPointKeyboard(){
        Scanner scan = new Scanner(System.in);
        int n,i,j;
        System.out.print("Banyak Point : ");
        n = scan.nextInt();
        scan.nextLine();
        Matrix pointMatrix = new Matrix(n,2);
        for(i = 0; i<n; i++){
            for(j=0; j<2; j++){
                pointMatrix.setElmt(i,j,scan.nextDouble());
            }
        }
        return pointMatrix;
    }



}
