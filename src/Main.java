import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.util.*;
public class Main {

	static Scanner scan = new Scanner(System.in);
	
	
	
	public static void mainMenu() {
		System.out.println("=============== Kalkulator Matrix ===============");
		System.out.println("=============== Main Menu ===============");
		System.out.println("1.Sistem Persamaaan Linier");
		System.out.println("2.Determinan");
		System.out.println("3.Matriks balikan");
		System.out.println("4.Interpolasi Polinom");
		System.out.println("5.Interpolasi Bicubic Spline");
		System.out.println("6.Regresi linier berganda");
		System.out.println("7.Keluar");
		
	}
	
	public static void subMenuSPL() {
		System.out.println("=============== Sub-Menu ===============");
		System.out.println("1.Metode eliminasi Gauss");
		System.out.println("2.Metode eliminasi Gauss-Jordan");
		System.out.println("3.Metode matriks balikan");
		System.out.println("4.Kaidah Cramer");
	}
	
	public static void subMenuDeterminan() {
		System.out.println("=============== Sub-Menu ===============");
		System.out.println("1.Metode OBE");
		System.out.println("2.Metode Ekspansi Kofaktor");

	}
	
	public static void subMenuMatriksBalikan() {
		System.out.println("=============== Sub-Menu ===============");
		System.out.println("1.Metode Gauss Jordan");
		System.out.println("2.Metode Adjoint");
	}
	public static void inputType() {
		System.out.println("=============== Metode Input ===============");
		System.out.println("1.Keyboard");
		System.out.println("2.File txt");
	}
	
	public static void menuSPL() throws IOException {
		int sub_menu_choice,input_choice = 0;
		
		subMenuSPL();
		System.out.print("Masukkan pilihan : ");
		sub_menu_choice = scan.nextInt();
		scan.nextLine();
		
		switch(sub_menu_choice) {
			case 1: // gauss
				System.out.println("=============== Metode Eliminasi Gauss ===============");
				inputType();
				System.out.print("Masukkan pilihan : ");
				input_choice = scan.nextInt();
				scan.nextLine();
				switch(input_choice) {
					case 1://keyboard-gauss
						int row,col;
						System.out.print("Banyak baris : ");
						row = scan.nextInt();
						System.out.print("Banyak kolom : ");
						col = scan.nextInt();
						scan.nextLine();
						Matrix mat = new Matrix(row,col);
						System.out.println("Input matriks : ");
						mat.readMatrix();
						Matrix gauss = SPL.gauss(mat);
						DisplaySolution.displayGauss(gauss);
						break;
					case 2://file-gauss
						Matrix mat2 = FileInputOutput.readFileMatrix();
						Matrix gauss2 = SPL.gauss(mat2);
						DisplaySolution.displayGauss(gauss2);
						break;
				
						
				}
				break;
			case 2 :
				System.out.println("=============== Metode Eliminasi Gauss-jordan ===============");
				inputType();
				System.out.print("Masukkan pilihan : ");
				input_choice = scan.nextInt();
				scan.nextLine();
				switch(input_choice) {
					case 1://keyboard-gauss-jordan
						int row,col;
						System.out.print("Banyak baris : ");
						row = scan.nextInt();
						System.out.print("Banyak kolom : ");
						col = scan.nextInt();
						scan.nextLine();
						Matrix mat = new Matrix(row,col);
						System.out.println("Input matriks : ");
						mat.readMatrix();
						Matrix gauss_jordan = SPL.gaussjordan(mat);
						DisplaySolution.displayGaussJordan(gauss_jordan);
						break;
					case 2://file-gauss-jordan
						Matrix mat2 = FileInputOutput.readFileMatrix();
						Matrix gaussJordan2 = SPL.gauss(mat2);
						DisplaySolution.displayGaussJordan(gaussJordan2);
						break;
				
						
				}
				break;
			case 3: // metode matriks balikan
				System.out.println("=============== Metode Matriks Balikan ===============");
				inputType();
				System.out.print("Masukkan pilihan : ");
				input_choice = scan.nextInt();
				scan.nextLine();
				switch(input_choice) {
					case 1://keyboard-mat_balikan
						int row,col;
						System.out.print("Banyak baris : ");
						row = scan.nextInt();
						System.out.print("Banyak kolom : ");
						col = scan.nextInt();
						scan.nextLine();
						Matrix mat = new Matrix(row,col);
						System.out.println("Input matriks : ");
						mat.readMatrix();
						
						if (!Matrix.isPersegi(Matrix.getMatrixCoefficient(mat))) {
							System.out.println("Matrix tidak persegi !");
						}
						else if (   Double.compare(Matrix.determinanEkspansiKofaktor(Matrix.getMatrixCoefficient(mat)), 0.0) == 0  ) {
							System.out.println("Matriks tidak memiliki inverse (determinan kosong)");
						}
						else {
							Matrix SPLinv = SPL.splInverse(mat);
							DisplaySolution.displaySPLbalikan(SPLinv);
						}
						
						break;
					case 2://file-SPLbalikan
						Matrix mat2 = FileInputOutput.readFileMatrix();
						if (!Matrix.isPersegi(Matrix.getMatrixCoefficient(mat2))) {
							System.out.println("Matrix tidak persegi !");
						}
						else if (   Double.compare(Matrix.determinanEkspansiKofaktor(Matrix.getMatrixCoefficient(mat2)), 0.0) == 0  ) {
							System.out.println("Matriks tidak memiliki inverse (determinan kosong)");
						}
						else {
							Matrix SPLinv2 = SPL.splInverse(mat2);
							DisplaySolution.displaySPLbalikan(SPLinv2);
						}
						break;
				
						
				}
				break;
			case 4:
				System.out.println("=============== Metode Cramer ===============");
				inputType();
				System.out.print("Masukkan pilihan : ");
				input_choice = scan.nextInt();
				scan.nextLine();
				switch(input_choice) {
					case 1://keyboard-cramer
						int row,col;
						System.out.print("Banyak baris dan kolom: ");
						row = scan.nextInt();
						col = row + 1;
						scan.nextLine();
						Matrix mat = new Matrix(row,col);
						System.out.println("Input matriks : ");
						mat.readMatrix();
						Matrix cramer = SPL.cramerMethod(mat);
						DisplaySolution.displayCramer(cramer);;
						break;
					case 2://file-cramer
						Matrix mat2 = FileInputOutput.readFileMatrix();
						Matrix Cramer2 = SPL.cramerMethod(mat2);
						DisplaySolution.displayCramer(Cramer2);
						break;
				
						
				}
				break;
				
		}
		
		
	}
	
	
	public static void main(String[] args) throws IOException  {
		
		int menu_choice = 0;
		
		do {
			mainMenu();
			System.out.print("Masukkan pilihan : ");
			menu_choice = scan.nextInt();
			scan.nextLine();
			
			switch (menu_choice) {
				case 1 : // SPL
					menuSPL();
					break;
				default :
					menu_choice = 7;
					
					

			}
			
			
		}while(menu_choice != 7);
		
		
		
		
	}

}
