public class BicubicSpline {
    private static Matrix toColumnMatrix(Matrix mIn){
        int i,j,k;
        Matrix mOut = new Matrix(mIn.getRow()*mIn.getCol(), 1);
        k = 0;
        for(i=0;i<mIn.getRow();i++){
            for(j=0;j<mIn.getCol();j++){
                mOut.setElmt(k,0, mIn.getElmt(i,j));
//				System.out.printf("Elm %d : %f\n",k+1, mIn.getElmt(i,j));
                k++;
            }
        }
        return  mOut;
    }

    public static Matrix bicubicX(){
        int i,j,k,x,y,col,row;
        double elmtVal;
        Matrix mX = new Matrix(16,16);
        row = 0;
        for(k=0;k<=3;k++) {
            for (y = 0; y <= 1; y++) {
                for (x = 0; x <= 1; x++) {
                    col = 0;
                    for (j = 0; j <= 3; j++) {
                        for (i = 0; i <= 3; i++) {
                            if (row < 4) {
                                elmtVal = (Math.pow(x, i) * Math.pow(y, j));
                            } else if (row < 8) {
                                if(i==0){
                                    elmtVal = 0;
                                } else {
                                    elmtVal = (i * Math.pow(x, i - 1) * Math.pow(y, j));
                                }
                            } else if (row < 12) {
                                if(j==0){
                                    elmtVal = 0;
                                } else {
                                    elmtVal = (j * Math.pow(x, i) * Math.pow(y, j - 1));
                                }
                            } else {
                                if(i==0 || j==0){
                                    elmtVal = 0;
                                } else {
                                    elmtVal = (i * j * Math.pow(x, i - 1) * Math.pow(y, j - 1));
                                }

                            }
//							System.out.printf("Elmt X(%d,%d) : %.4f\n",row,col,elmtVal);
                            mX.setElmt(row, col, elmtVal);
                            col++;
                        }
                    }
                    row++;
                }
            }
        }
        return mX;
    }

    public static Matrix solveBicubic(Matrix mIn){
        Matrix m_SPL = new Matrix(16,1);
        Matrix m_fx = new Matrix(16,1);
        Matrix m_x = new Matrix(16,16);
        m_fx = toColumnMatrix(mIn);
        m_x = bicubicX();
        Matrix m_augmented = new Matrix(m_x.getRow(), (m_x.getCol()+ m_fx.getCol()));
        m_augmented = Matrix.konkatMatrix(m_x, m_fx);
        m_SPL = SPL.splInverse(m_augmented);
        return m_SPL;
    }

    public static double getTaksiranBicubic(Matrix solMatrix, double x, double y){
        int i,j,k;
        k = 0;
        double taksiran = 0;
        for(j = 0; j<4; j++){
            for(i=0; i<4; i++){
                taksiran += solMatrix.getElmt(k,0) * Math.pow(x,i) * Math.pow(y,j);
                k++;
            }
        }
        return taksiran;
    }
}
