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

	public static void menuMatriksBalikan() throws IOException{
		int sub_menu_choice, input_choice;
		subMenuMatriksBalikan();
		System.out.print("Masukkan pilihan : ");
		sub_menu_choice = scan.nextInt();
		scan.nextLine();
		switch(sub_menu_choice){
			case 1: // Gauss Jordan
				System.out.println("=============== Metode Gauss Jordan ===============");
				inputType();
				System.out.print("Masukkan pilihan : ");
				input_choice = scan.nextInt();
				scan.nextLine();
				switch(input_choice){
					case 1:
						int row,col;
						System.out.print("Banyak baris : ");
						row = scan.nextInt();
						System.out.print("Banyak kolom : ");
						col = scan.nextInt();
						scan.nextLine();
						Matrix inverseMatrix = new Matrix(row,col);
						System.out.println("Input matriks : ");
						inverseMatrix.readMatrix();
						inverseMatrix = Matrix.inversGaussJordan(inverseMatrix);
						inverseMatrix.displayMatrix();
						FileInputOutput.opsiSaveFile(inverseMatrix);
						break;
					case 2:
						Matrix inverseMatriks = FileInputOutput.readFileMatrix();
						inverseMatriks = Matrix.inversGaussJordan(inverseMatriks);
						inverseMatriks.displayMatrix();
						FileInputOutput.opsiSaveFile(inverseMatriks);
						break;
				}
				break;
			case 2: // Adjoint
				System.out.println("=============== Metode Adjoint ===============");
				inputType();
				System.out.print("Masukkan pilihan : ");
				input_choice = scan.nextInt();
				scan.nextLine();
				switch(input_choice){
					case 1:
						int row,col;
						System.out.print("Banyak baris : ");
						row = scan.nextInt();
						System.out.print("Banyak kolom : ");
						col = scan.nextInt();
						scan.nextLine();
						Matrix inverseMatrix = new Matrix(row,col);
						System.out.println("Input matriks : ");
						inverseMatrix.readMatrix();
						inverseMatrix = Matrix.getMatrixInverseAdjoin(inverseMatrix);
						inverseMatrix.displayMatrix();
						FileInputOutput.opsiSaveFile(inverseMatrix);
						break;
					case 2:
						Matrix inverseMatriks = FileInputOutput.readFileMatrix();
						inverseMatriks = Matrix.getMatrixInverseAdjoin(inverseMatriks);
						inverseMatriks.displayMatrix();
						FileInputOutput.opsiSaveFile(inverseMatriks);
						break;
				}
				break;
		}
	}

	public static void menuInterpolasiPolinom() throws IOException{
		int input_choice;
		System.out.println("=============== MENU INTERPOLASI POLINOM ===============");
		inputType();
		System.out.print("Masukkan pilihan : ");
		input_choice = scan.nextInt();
		scan.nextLine();
		switch(input_choice){
			case 1: // Input keyboard
				int n;
				System.out.print("Banyak Point : ");
				n = scan.nextInt();
				scan.nextLine();
				Matrix pointMatrix = new Matrix(n,2);
				pointMatrix.readMatrix();
				Matrix linEqMatrix = Polinom.createLinearEq(pointMatrix);
				Matrix solution = Polinom.getPolinomSol(linEqMatrix);
				String equation = Polinom.getPolinomEq(solution);
				String eqString = String.format("Persamaan interpolasi polinom tersebut adalah : %s", equation );
				System.out.println(eqString);
				System.out.println("Masukkan nilai x yang ingin ditaksir : ");
				double x = scan.nextDouble();
				double taksiran = Polinom.getTaksiran(solution, x);
				String strTaksiran = String.format("Taksiran f(%f) = %f", x, taksiran);
				System.out.println(strTaksiran);
				String[]arrstr = new String[]{eqString,strTaksiran};
				FileInputOutput.opsiSaveFilePolinom(arrstr);
				break;
			case 2: // Input file
				Matrix matrixFile = FileInputOutput.readFileMatrix();
				Matrix poinMatrix = matrixFile.copyMatrix();
				poinMatrix.copyMatrixCustom(0,0,matrixFile.getRow()-1,matrixFile.getCol());
				double eks = matrixFile.getElmt(matrixFile.getRow()-1, 0);
				Matrix linEqMatriks = Polinom.createLinearEq(poinMatrix);
				Matrix sol = Polinom.getPolinomSol(linEqMatriks);
				String eq = Polinom.getPolinomEq(sol);
				String eqStr = String.format("Persamaan interpolasi polinom tersebut adalah : %s", eq);
				System.out.println(eqStr);
				double estimate = Polinom.getTaksiran(sol, eks);
				String strEstimate = String.format("Taksiran f(%f) = %f", eks, estimate);
				System.out.println(strEstimate);
				String[] arr = new String[]{eqStr,strEstimate};
				FileInputOutput.opsiSaveFilePolinom(arr);
				break;
		}
	}
	
	public static void menuDeterminan() throws IOException {
		System.out.println("=============== Menu Determinan Matriks ===============");
		int pilihan;
		System.out.println("Pilih metode metode mencari determinan matriks : ");
		do {	
		
		System.out.println("1.Metode reduksi baris");
		System.out.println("2.Metode ekspansi kofaktor");
		System.out.print("Pilih metode :");
		pilihan = scan.nextInt();
		if (pilihan != 1 && pilihan != 2) {
			System.out.println("Pilihan anda salah, tolong pilih ulang!");
		}
		}while(pilihan != 1 && pilihan !=2);
		
		if (pilihan == 1) {
			System.out.println("=============== Metode reduksi baris ===============");
			inputType();
			System.out.print("Masukkan pilihan :");
			int input_choice = scan.nextInt();
			scan.nextLine();
			switch (input_choice) {
				case 1 ://keyboard
					System.out.print("Masukkan N :");
					int n = scan.nextInt();
					Matrix mat = new Matrix(n,n);
					System.out.println("Input matrix :");
					mat.readMatrix();
					double det = Matrix.determinanReduksi(mat);
					DisplaySolution.displayDeterminan(det);
					break;
				case 2://input text
					Matrix mat2 = FileInputOutput.readFileMatrix();
					if (!Matrix.isPersegi(mat2)) {
						System.out.println("Matrix tidak persegi !, determinan matrix tidak dapat ditemukan");
						
						
					}
					else {
						double det2 = Matrix.determinanReduksi(mat2);
						DisplaySolution.displayDeterminan(det2);
					}
					break;
			}
			
		}
		else {
			System.out.println("=============== Metode Ekspansi Kofaktor ===============");
			inputType();
			System.out.print("Masukkan pilihan :");
			int input_choice = scan.nextInt();
			scan.nextLine();
			switch (input_choice) {
				case 1 ://keyboard
					System.out.print("Masukkan N :");
					int n = scan.nextInt();
					Matrix mat = new Matrix(n,n);
					System.out.println("Input matrix : ");
					mat.readMatrix();
					double det = Matrix.determinanEkspansiKofaktor(mat);
					DisplaySolution.displayDeterminan(det);
					break;
				case 2://input text
					Matrix mat2 = FileInputOutput.readFileMatrix();
					if (!Matrix.isPersegi(mat2)) {
						System.out.println("Matrix tidak persegi !, determinan matrix tidak dapat ditemukan");
						
						
					}
					else {
						double det2 = Matrix.determinanEkspansiKofaktor(mat2);
						DisplaySolution.displayDeterminan(det2);
					}
					break;
			}
			
		}
	
	}

	public static void menuRegresi() throws IOException{
		int input_choice, nVar, nData, i, j;
		String strEq, strTaksiran;
		System.out.println("=============== Regresi Linier Berganda ===============");
		do {
			inputType();
			System.out.print("Masukkan pilihan : ");
			input_choice = scan.nextInt();
			scan.nextLine();
			switch (input_choice) {
				case 1://keyboard-regresi
					System.out.print("Masukkan banyaknya variabel: ");
					nVar = scan.nextInt();
					scan.nextLine();
					double[]arrVarVal = new double[nVar];
					System.out.print("Masukkan banyaknya data: ");
					nData = scan.nextInt();
					scan.nextLine();
					Matrix m_aug = new Matrix(nData, nVar+1);
					System.out.println("Input Matriks (dalam bentuk Matriks Augmented): ");
					m_aug.readMatrix();
					System.out.println("Masukkan nilai setiap variabel yang ingin ditaksir nilainya");
					for(i=0;i<nVar;i++){
						System.out.printf("x%d : ",i+1);
						arrVarVal[i] = scan.nextDouble();
						scan.nextLine();
					}
					Matrix m_solved = new Matrix(m_aug.getRow(), 1);
					m_solved = Regresi.solveRegresi(m_aug);
					strEq = Regresi.getRegresiEq(m_solved);
					System.out.printf("Persamaan Regresi Linear Berganda:\n%s\n", strEq);
					strTaksiran = Regresi.getStrTaksiran(m_solved, arrVarVal);
					System.out.println(strTaksiran);
					String[]arrStrOut = new String[]{strEq,strTaksiran};
					FileInputOutput.opsiSaveFile(arrStrOut);
					break;

				case 2://file regresi
					Matrix m_input = FileInputOutput.readFileMatrix();
					Matrix mfile_aug = new Matrix((m_input.getRow()-1), m_input.getCol());
					for(i=0;i< mfile_aug.getRow();i++){
						for(j=0;j< mfile_aug.getCol();j++){
							mfile_aug.setElmt(i,j,m_input.getElmt(i,j));
						}
					}
					double[]arrVarValFile = new double[mfile_aug.getCol()-1];
					Matrix mfile_solved = new Matrix(mfile_aug.getRow(), 1);
					mfile_solved = Regresi.solveRegresi(mfile_aug);
					strEq = Regresi.getRegresiEq(mfile_solved);
					System.out.printf("Persamaan Regresi Linear Berganda:\n%s\n", strEq);
					strTaksiran = Regresi.getStrTaksiran(mfile_solved, arrVarValFile);
					System.out.println(strTaksiran);
					String[]arrStrOutFile = new String[]{strEq,strTaksiran};
					FileInputOutput.opsiSaveFile(arrStrOutFile);
					break;
				default:
					System.out.println("Input tidak valid, silakan ulangi");
			}
		} while (input_choice != 1 && input_choice != 2);

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
				case 2:
					menuDeterminan();
					break;
				case 3:
					menuMatriksBalikan();
					break;
				case 4:
					menuInterpolasiPolinom();
					break;
				case 6:
					menuRegresi();
					break;
				default :
					menu_choice = 7;
					
					

			}
			
			
		}while(menu_choice != 7);
		
		
		
		
	}

}
