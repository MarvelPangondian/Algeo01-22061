
import java.io.*;
import java.util.*;

public class FileInputOutput {
	
	
	// memulai dengan readFile
	public static Matrix readFileMatrix() throws IOException{
		boolean path_exist = false;
		String path = "";
		Scanner scan = new Scanner(System.in);
		while (!path_exist) {
			int pilihan;
			do {
				
				System.out.println("====Proses membaca file====");
				
				System.out.println("1.Nama file (pastikan file di folder src)");
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
				path += "\\" + "src\\" + file_name;
				
				
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
		scan.close();
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

	
	

		
}
