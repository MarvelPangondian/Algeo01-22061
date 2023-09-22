import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		// TODO Auto-generated method stub\
		System.out.println("=======Percobaan gauss method=========");
		System.out.print("number of rows : ");
		int n_row = scan.nextInt();
		System.out.print("number of columns : ");
		int n_col = scan.nextInt();
		Matrix m = new Matrix(n_row,n_col);
		m.readMatrix();
		Matrix hasil_gauss;
		hasil_gauss = SPL.gauss(m);
		System.out.println("hasil pertama :");
		hasil_gauss.displayMatrix();
		System.out.printf("switch = %d",hasil_gauss.num_switch);
		System.out.print("\n");
		System.out.printf("k = %f\n",hasil_gauss.K);
		System.out.printf("determinan = %f\n",Matrix.determinanReduksi(m));

		
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
