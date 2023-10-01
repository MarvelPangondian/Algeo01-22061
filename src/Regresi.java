public class Regresi {
    private static Matrix regresiBerganda(Matrix mIn) {
        Matrix m_normalEstimation = new Matrix(mIn.getCol(), (mIn.getCol() + 1));
        int i,j,k;
        double multiplier, sum;
        for(k=0;k< m_normalEstimation.getRow();k++){
            for(j=0;j< m_normalEstimation.getCol();j++){
                if((k==0) && (j==0)){
                    m_normalEstimation.setElmt(k,j,mIn.getRow());
                } else {
                    sum = 0;
                    for(i=0;i< mIn.getRow();i++){
                        if(k==0) {
                            multiplier = 1;
                            sum += (mIn.getElmt(i,(j-1)) * multiplier);
                        } else if(j==0){
                            multiplier = 1;
                            sum += (mIn.getElmt(i,(k-1)) * multiplier);
                        } else {
                            multiplier = mIn.getElmt(i,(k-1));
                            sum += (mIn.getElmt(i,(j-1)) * multiplier);
                        }
                    }
                    m_normalEstimation.setElmt(k,j,sum);
                }
            }
        }
        return m_normalEstimation;
    }

    public static Matrix solveRegresi(Matrix mIn){
        Matrix m_normEst = new Matrix(mIn.getCol(), (mIn.getCol()+1));
        m_normEst =	regresiBerganda(mIn);
        Matrix m_solve = new Matrix(m_normEst.getCol()-1, 1);
        m_solve = SPL.splInverse(m_normEst);
        return m_solve;
    }

    public static String getRegresiEq(Matrix m_solved) {
        String Eq = "f(x) = ";
        int row;
        for(row = 0; row < m_solved.getRow(); row++){
            if (row == 0 && m_solved.getElmt(row, m_solved.getCol()-1) < 0){
                Eq += String.format("-%.5f ", -1*m_solved.getElmt(row, m_solved.getCol()-1));
            }else if(row ==0 ){
                Eq += String.format("%.5f ", m_solved.getElmt(row, m_solved.getCol()-1));
            }else{
                if (m_solved.getElmt(row, m_solved.getCol()-1) < 0){
                    Eq += String.format("- %.5fx%d ", -1*m_solved.getElmt(row, m_solved.getCol()-1), row);
                }else{
                    Eq += String.format("+ %.5fx%d ", m_solved.getElmt(row, m_solved.getCol()-1), row);
                }
            }
        }
        return Eq;
    }

    private static double getTaksiranRegresi(Matrix solMatrix, double[]arrVar){
        int i,j;
        double taksiran = 0;
        for(i = 0; i<solMatrix.getRow(); i++){
            if(i == 0) {
                taksiran += solMatrix.getElmt(i, 0);
            } else {
                taksiran += solMatrix.getElmt(i, 0)*arrVar[i-1];
            }
        }
        return taksiran;
    }

    public static String getStrTaksiran(Matrix mSolve, double[]arrVar){
        int i;
        String strTaksiran = "f(";
        for(i=0;i<(mSolve.getRow()-1);i++){
            if(i == (mSolve.getRow()-2)){
                strTaksiran += String.format("%.4f) = ",arrVar[i]);
            } else {
                strTaksiran += String.format("%.4f,",arrVar[i]);
            }
        }
        strTaksiran += String.format("%.4f", getTaksiranRegresi(mSolve, arrVar));

        return strTaksiran;
    }
}
