import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.util.*;
public class Main {
	public static void main(String[] args) throws IOException  {
		Scanner scan = new Scanner(System.in);
		int menu_choice = 0;
		
		do {
			MenuIO.mainMenu();
			System.out.print("Masukkan pilihan : ");
			menu_choice = scan.nextInt();
			scan.nextLine();
			
			switch (menu_choice) {
				case 1 : // SPL
					MenuIO.menuSPL();
					break;
				case 2:
					MenuIO.menuDeterminan();
					break;
				case 3:
					MenuIO.menuMatriksBalikan();
					break;
				case 4:
					MenuIO.menuInterpolasiPolinom();
					break;
				case 5:
					MenuIO.menuBicubic();
					break;
				case 6:
					MenuIO.menuRegresi();
					break;
				default :
					System.out.printf("Input tidak valid, silakan ulangi");
			}
		}while(menu_choice != 7);
	}
}
