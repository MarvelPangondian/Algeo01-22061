
import java.io.*;
import java.util.*;

public class FileInputOutput {
	
	private static Scanner scan = new Scanner(System.in);
	// memulai dengan readFile
	public static Matrix readFileMatrix() throws IOException{
		boolean path_exist = false;
		String path = "";
		
		while (!path_exist) {
			int pilihan;
			do {
				
				System.out.println("====Proses membaca file====");
				
				System.out.println("1.Nama file (pastikan file di folder test)");
				System.out.println("2.Full path");
				System.out.print("Pilihan (1/2) : ");
				pilihan = scan.nextInt();
				scan.nextLine();
				if (pilihan != 1 && pilihan != 2) {
					System.out.println("Tolong masukkan pilihan yang benar");
				}
			}while (pilihan != 1 && pilihan != 2);
			
			if (pilihan == 1) {
				path = System.getProperty("user.dir");
				
				System.out.print("Masukkan nama file (dengan extension) : ");
				String file_name = scan.nextLine();
				path += "\\" + "test\\" + file_name;
				
				
			}
			else if (pilihan == 2) {
				System.out.print("Masukkan full path dengan benar : ");
				path = scan.nextLine();
			}
			
			
			
			File file = new File(path);
			if (file.exists()) {
				if (FileInputOutput.getColCountFile(path) == 0 || FileInputOutput.getRowCountFile(path) == 0) {
					System.out.println("Matriks tidak ada/Tidak dalam posisi yang benar dalam file, tolong masukkan ulang nama file");
				}
				else {
					path_exist = true;
				}
			}
			else {
				System.out.print("File tidak ada, tolong masukkan ulang nama file \n");
			
			}
		}
		
		return FileInputOutput.getMatrixFromFile(path);
		
		
	}
	
	public static int getRowCountFile(String path) throws IOException{
		int row_count = 0 ;
		File file = new File(path);
		Scanner scan_row = new Scanner(file);
		while (scan_row.hasNextLine()) {
			row_count++;
			scan_row.nextLine();
		}
		scan_row.close();
		
		
		return row_count;
	}
	
	public static int getColCountFile(String path) throws IOException{
		int col_count = 0;
		File file = new File(path);
		Scanner scan = new Scanner(file);
		if (!scan.hasNextLine()) {
			scan.close();
			return 0;
		}
		Scanner scan_col = new Scanner(scan.nextLine());
		while(scan_col.hasNextDouble()) {
			col_count++;
			scan_col.nextDouble();
		}
		scan.close();
		scan_col.close();
		
		
		return col_count;
	}
	
	public static Matrix getMatrixFromFile(String path) throws IOException {
		int row = 0 , col = 0 ;
		int num_row  =getRowCountFile(path) ,num_col = getColCountFile(path);
		Matrix mOut = new Matrix(num_row, num_col);
		File file = new File(path);
		Scanner scan = new Scanner(file);
		while (scan.hasNextDouble()) {
			mOut.setElmt(row, col, scan.nextDouble());
			col++;
			if (col >= num_col) {
				col = 0;
				row++;
			}
			if (row >= num_row) {
				break;
			}
		}
		scan.close();
		
		return mOut;
		
	}

	
	// Write file 
	
	// opsi untuk gauss, gauss jordan, cremer, dan metode spl matriks balikan
	public static void opsiSaveFile(String[] arr) {
		int opsi;
		
		do {
			System.out.println("Apakah ingin simpan hasil ke dalam file ?");
			System.out.println("1.Iya");
			System.out.println("2.Tidak");
			opsi = scan.nextInt();
			scan.nextLine();
			 // hilangkan newline
			if (opsi != 1 && opsi != 2) {
				System.out.println("Tolong masukkan opsi yang valid");
			}
		}while(opsi != 1 && opsi != 2);
		if (opsi == 1) {
			writeSPL(arr);
		}
		
		
		
		
	}
		public static void opsiSaveFilePolinom(String[] arr) {
		int opsi;
		
		do {
			System.out.println("Apakah ingin simpan hasil ke dalam file ?");
			System.out.println("1.Iya");
			System.out.println("2.Tidak");
			opsi = scan.nextInt();
			scan.nextLine();
			 // hilangkan newline
			if (opsi != 1 && opsi != 2) {
				System.out.println("Tolong masukkan opsi yang valid");
			}
		}while(opsi != 1 && opsi != 2);
		if (opsi == 1) {
			writePolinom(arr);
		}
		
		
		
		
	}
	
	// opsi untuk determinan
	public static void opsiSaveFile(double k) {
		int opsi;
		
		do {
			System.out.println("Apakah ingin simpan hasil ke dalam file ?");
			System.out.println("1.Iya");
			System.out.println("2.Tidak");
			opsi = scan.nextInt();
			scan.nextLine(); // hilangkan newline
			if (opsi != 1 && opsi != 2) {
				System.out.println("Tolong masukkan opsi yang valid");
			}
		}while(opsi != 1 && opsi != 2);
	
		
		
		
	}
	
	// opsi untuk matrix inverse 
	public static void opsiSaveFile(Matrix mInv) {
		int opsi;
		
		do {
			System.out.println("Apakah ingin simpan hasil ke dalam file ?");
			System.out.println("1.Iya");
			System.out.println("2.Tidak");
			opsi = scan.nextInt();
			scan.nextLine(); // hilangkan newline
			if (opsi != 1 && opsi != 2) {
				System.out.println("Tolong masukkan opsi yang valid");
			}
		}while(opsi != 1 && opsi != 2);

		if (opsi == 1){
			writeMatrixInverse(mInv);
		}
		
		
		
		
	}

	
	// Operasi Menulis SPL
	public static void writeSPL(String[] arr) {
		
		File file = null;
		// save di output
		do {
			System.out.print("Masukkan nama file bersama extension .txt : ");
			
			String name_file =  scan.nextLine();
			String path = System.getProperty("user.dir");
			path += "\\test\\Output\\" +name_file;
			file = new File(path);
			if (file.exists()) {
				System.out.println("File tersebut sudah ada !, tolong masukkan nama file ulang");
			}
			
		}
		while (file.exists());
		try {
			PrintWriter pw = new PrintWriter(file);
			for (int i = 0 ; i < arr.length ; ++i) {
				pw.printf("x%d = %s\n",i+1,arr[i]);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// 
		
	
		
	}
		public static void writePolinom(String[] arr) {
		
		File file = null;
		// save di output
		do {
			System.out.print("Masukkan nama file bersama extension .txt : ");
			
			String name_file =  scan.nextLine();
			String path = System.getProperty("user.dir");
			path += "\\test\\Output\\" +name_file;
			file = new File(path);
			if (file.exists()) {
				System.out.println("File tersebut sudah ada !, tolong masukkan nama file ulang");
			}
			
		}
		while (file.exists());
		try {
			PrintWriter pw = new PrintWriter(file);
			for (int i = 0 ; i < arr.length ; ++i) {
				pw.printf("%s\n",arr[i]);
			}
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		// 
		
	
		
	}

	
	
	public static void writeDeterminan(double k) {
		
		File file = null;
		// save di output
		do {
			System.out.print("Masukkan nama file bersama extension .txt : ");
			
			String name_file =  scan.nextLine();
			String path = System.getProperty("user.dir");
			path += "\\test\\Output\\" +name_file;
			file = new File(path);
			if (file.exists()) {
				System.out.println("File tersebut sudah ada !, tolong masukkan nama file ulang");
			}
			
		}
		while (file.exists());
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.print(k);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	
	public static void writeMatrixInverse(Matrix mat) {
		
		File file = null;
		// save di output
		do {
			System.out.print("Masukkan nama file bersama extension .txt : ");
			
			String name_file =  scan.nextLine();
			String path = System.getProperty("user.dir");
			path += "\\test\\Output\\" +name_file;
			file = new File(path);
			if (file.exists()) {
				System.out.println("File tersebut sudah ada !, tolong masukkan nama file ulang");
			}
			
		}
		while (file.exists());
		try {
			PrintWriter pw = new PrintWriter(file);
			for (int row = 0 ; row < mat.getRow() ; ++row) {
				for (int col = 0 ; col < mat.getRow() ; ++col) {
					pw.print(mat.getElmt(row, col) + " ");
					if (col == mat.getLastIdxCol()) {
						pw.print("\n");
					}
				}
			}
			
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}


		
}


