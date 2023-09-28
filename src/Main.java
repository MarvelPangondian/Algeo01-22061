import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
public class Main {

	public static void main(String[] args) throws IOException  {
		Scanner scan = new Scanner(System.in);
//		// TODO Auto-generated method stub
//		System.out.println("=======Percobaan gauss=========");
//		System.out.print("number of rows : ");
//		int n_row = scan.nextInt();
//		System.out.print("number of columns : ");
//		int n_col = scan.nextInt();
//		Matrix m = new Matrix(n_row,n_col);
//		m.readMatrix();
//		Matrix hasil_gauss;
//		hasil_gauss = SPL.gaussjordan(m);
//		System.out.println("hasil pertama :");
//		hasil_gauss.displayMatrix();
////		System.out.printf("switch = %d",hasil_gauss.num_switch);
////		System.out.print("\n");
////		System.out.printf("k = %f\n",hasil_gauss.K);
////		System.out.printf("determinan = %f\n",Matrix.determinanReduksi(m));
////		Matrix mKoef;
////		mKoef = Matrix.getMatrixCoefficient(m);
////		Matrix mConst;
////		mConst = Matrix.getMatrixConstant(m);
////		Matrix sub;
////		sub = Matrix.subMatrix(m,0,1);
////		System.out.println("Matrix coefficient :");
////		mKoef.displayMatrix();
////		System.out.println("Matrix constant : ");
////		mConst.displayMatrix();
////		System.out.println("Submatrix (get rid of row 0 column 1) : ");
////		sub.displayMatrix();
////		System.out.printf("Determinant using expnasion cofaktor : %f\n",Matrix.determinanEkspansiKofaktor(Matrix.getMatrixCoefficient(m)));
////		Matrix mat_cramer;
////		mat_cramer = SPL.cramerMethod(m);
////		System.out.println("=======Percobaan cramer=========");
////		System.out.println("Hasil penyelesaian dengan metode cramer : ");
////		mat_cramer.displayMatrix();
////		System.out.println("=======inverse =========");
////		Matrix mInverse = Matrix.inversGaussJordan(Matrix.getMatrixCoefficient(m));
////		mInverse.displayMatrix();
////		System.out.println("=======SPL Inverse =========");
////		Matrix M_inverse = SPL.splInverse(m);
////		M_inverse.displayMatrix();
////		
////		Matrix cof = Matrix.getMatrixCofactor(Matrix.getMatrixCoefficient(m));
////		System.out.println("Matrix cofactor : ");
////		cof.displayMatrix();
////		System.out.println("Matrix inverse : ");
////		Matrix inv = Matrix.getMatrixInverseAdjoin(Matrix.getMatrixCoefficient(m));
////		inv.displayMatrix();
//		System.out.println("=========percobaan parametric=====");
//		DisplaySolution.displayGauss(hasil_gauss);
//		
//		
//		
//
//		
////		System.out.println("=======Percobaan gauss method=========");
////		System.out.print("number of rows : ");
////		int n = scan.nextInt();
////		System.out.print("number of columns : ");
////		int c = scan.nextInt();
////		Matrix m_n = new Matrix(n,c);
////		m_n.readMatrix();
////		Matrix gauss2;
////		gauss2 = SPL.gauss(m_n);
////		
//		
//		
////		System.out.println("hasil kedua :");
////		gauss2.displayMatrix();
////		System.out.printf("switch = %d",gauss2.num_switch);
////		System.out.print("\n");
////		System.out.printf("k = %f\n",gauss2.K);
////		
//		
//		System.out.println("==============Test Case==============");
//		// note asumsikan dalam matriks augmented (nanti diatur di UI), untuk test case dalam augmented dulu aja
//		System.out.print("Number of rows : ");
//		int row = scan.nextInt();
//		System.out.print("Number of columns : ");
//		int col = scan.nextInt();
//		Matrix mat = new Matrix(row,col);
//		mat.readMatrix();
//		
//		// hasil gauss
//		Matrix hasil_gauss = SPL.gauss(mat);
//		//hasil gauss jordan
//		Matrix hasil_gauss_jordan = SPL.gaussjordan(mat);
//		//hasil SPL dengan balikan
//		Matrix splBalik =  SPL.splInverse(mat);
//		
//		//hasil cramer
//		Matrix hasil_cramer = SPL.cramerMethod(mat);
//		// Deterimnan reduksi
//		double detOBE = Matrix.determinanReduksi(Matrix.getMatrixCoefficient(mat));
//		// Determinan ekspansi kofaktor
//		double detCofactor = Matrix.determinanEkspansiKofaktor(Matrix.getMatrixCoefficient(mat));
//		
//		//inverse dengan gauss
//		Matrix inverse_gauss = Matrix.inversGaussJordan(Matrix.getMatrixCoefficient(mat));
//		//inverse dengan adjoin
//		Matrix inverse_adj = Matrix.getMatrixInverseAdjoin(Matrix.getMatrixCoefficient(mat));
//		
//		
//		System.out.println("==============Percobaan gauss==============");
//		hasil_gauss.displayMatrix();
//		System.out.println("==============Percobaan gauss jordan==============");
//		hasil_gauss_jordan.displayMatrix();
//		System.out.println("==============Hasil parametric gauss ==============");
//		DisplaySolution.displayGauss(hasil_gauss);
//		System.out.println("==============Hasil parametric gauss jordan ==============");
//		DisplaySolution.displayGaussJordan(hasil_gauss_jordan);
//		System.out.println("============== Hasil dengan cremer (Hanya jika dia ada solusi (gaada parametric), kalo ga diisi marker) ==============");
//		DisplaySolution.displayCramer(hasil_cramer);
//		System.out.println("==============Hasil SPL dengan matriks balikan==============");
//		DisplaySolution.displaySPLbalikan(splBalik);
//		System.out.println("==============Hasil determinan reduksi (OBE)==============");
//		System.out.printf("Determinan OBE = %f\n",detOBE);
//		System.out.println("==============Hasil determinan ekspansi kofaktor ==============");
//		System.out.printf("Determinan kofaktor = %f\n",detCofactor);
//		System.out.println("==============Hasil inverse dengan gauss jordan ==============");
//		inverse_gauss.displayMatrix();
//		System.out.println("==============Hasil inverse dengan adjoin ==============");
//		inverse_adj.displayMatrix();
//		
		System.out.println("==============Test read file ==============");
		Matrix mat = FileInputOutput.readFileMatrix();
		System.out.println("Hasil dari membaca file :");
		mat.displayMatrix();
		Matrix gauss = SPL.gauss(mat);
		System.out.println("Hasil gauss :");
		gauss.displayMatrix();
		System.out.println("Hasil gauss jordan: ");
		Matrix gauss_jordan = SPL.gaussjordan(mat);
		gauss_jordan.displayMatrix();
		System.out.println("Hasil parametric gauss: ");
		DisplaySolution.displayGauss(gauss);
		System.out.println("Hasil parametric gauss jordan: ");
		DisplaySolution.displayGaussJordan(gauss_jordan);
		
	}

}
