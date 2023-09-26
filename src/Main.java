import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// TODO Auto-generated method stub
		System.out.println("=======Percobaan gauss jordan method=========");
		System.out.print("number of rows : ");
		int n_row = scan.nextInt();
		System.out.print("number of columns : ");
		int n_col = scan.nextInt();
		Matrix m = new Matrix(n_row,n_col);
		m.readMatrix();
		Matrix hasil_gaussjordan;
		hasil_gaussjordan = SPL.gaussjordan(m);
		System.out.println("hasil pertama :");
		hasil_gaussjordan.displayMatrix();
		System.out.printf("switch = %d",hasil_gaussjordan.num_switch);
		System.out.print("\n");
		System.out.printf("k = %f\n",hasil_gaussjordan.K);
		System.out.printf("determinan = %f\n",Matrix.determinanReduksi(m));
		Matrix mKoef;
		mKoef = Matrix.getMatrixCoefficient(m);
		Matrix mConst;
		mConst = Matrix.getMatrixConstant(m);
		Matrix sub;
		sub = Matrix.subMatrix(m,0,1);
		System.out.println("Matrix coefficient :");
		mKoef.displayMatrix();
		System.out.println("Matrix constant : ");
		mConst.displayMatrix();
		System.out.println("Submatrix (get rid of row 0 column 1) : ");
		sub.displayMatrix();
		System.out.printf("Determinant using expnasion cofaktor : %f\n",Matrix.determinanEkspansiKofaktor(Matrix.getMatrixCoefficient(m)));
		Matrix mat_cramer;
		mat_cramer = SPL.cramerMethod(m);
		System.out.println("=======Percobaan cramer=========");
		System.out.println("Hasil penyelesaian dengan metode cramer : ");
		mat_cramer.displayMatrix();
		
		

		
//		System.out.println("=======Percobaan gauss method=========");
//		System.out.print("number of rows : ");
//		int n = scan.nextInt();
//		System.out.print("number of columns : ");
//		int c = scan.nextInt();
//		Matrix m_n = new Matrix(n,c);
//		m_n.readMatrix();
//		Matrix gauss2;
//		gauss2 = SPL.gauss(m_n);
//		
		
		
//		System.out.println("hasil kedua :");
//		gauss2.displayMatrix();
//		System.out.printf("switch = %d",gauss2.num_switch);
//		System.out.print("\n");
//		System.out.printf("k = %f\n",gauss2.K);
//		
		
		
		
		

	}

}
