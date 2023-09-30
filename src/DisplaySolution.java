
import java.io.*;
import java.util.*;
public class DisplaySolution {
	
	

	// Display hasil SPL gauss jordan
	public static void displayGauss(Matrix mGauss) {
		if (mGauss.solvable) {
			int[] allParametric = new int[mGauss.getCol()-1];
			for (int c = 0 ; c < allParametric.length ; ++c) {
				allParametric[c] = 0;
			}
			String[] var_solution = new String[mGauss.getCol() - 1]; // solusi dalam bentuk string
			double[][] value_variable = new double[mGauss.getCol() - 1][mGauss.getCol() - 1];
			for (int var = 0 ; var < value_variable.length ; ++var) {
				for (int k = 0 ; k < mGauss.getCol() - 1  ; k ++) {
					value_variable[var][k] = 0;
				}
			}
			double[] exact_value = new double[mGauss.getCol() - 1];
			char[] Alphabet = new char[mGauss.getCol()-1];
			char curr_char = 'a';
			
			
			double[] arr_const = new double[mGauss.getCol() - 1];
			final double mark = 9999;
			
			// mengisi exact_values dengan mark
			for (int i = 0 ; i < exact_value.length ; ++i) {
				exact_value[i] = mark;
			}
			
			for (int i = 0 ; i < arr_const.length ; ++i) {
				arr_const[i] = 0;
				
			}
			for (int row = mGauss.getLastIdxRow() ; row >= 0 ; --row) {
				boolean exact = true;
				int leading_one = mGauss.leadingOneRowIdx(row);
				if (leading_one == 9999) {
					continue;
				}
				allParametric[leading_one] = 1;
				
				arr_const[leading_one] = mGauss.getElmt(row, mGauss.getLastIdxCol());
				int col_start = leading_one +1;
				
				for (; col_start <= mGauss.getLastIdxCol() - 1 ; ++col_start) {
					if (mGauss.getElmt(row, col_start) == 0) {
						continue;
					}
					if (exact_value[col_start] != mark) {
						arr_const[leading_one] -= exact_value[col_start] * mGauss.getElmt(row, col_start);
					}
					else {
						exact = false;
						arr_const[leading_one] -= arr_const[col_start] * mGauss.getElmt(row, col_start);
						if (allZero(value_variable[col_start])) {
							value_variable[leading_one][col_start] -= mGauss.getElmt(row, col_start);
							
						}
						else {
							for (int i = 0 ; i < mGauss.getCol() - 1 ; ++i){
								value_variable[leading_one][i] -= mGauss.getElmt(row, col_start) * value_variable[col_start][i];
							}
						}
					}
				}
				if (exact) {
					exact_value[leading_one] = arr_const[leading_one];
				}
			}
			
			// filling alphabet
			for (int f = 0 ; f < allParametric.length ; f++) {
				if (allParametric[f] == 0) {
					Alphabet[f] = curr_char;
					curr_char++;
				} 
			}
			
			// putting it all together
			for (int i = 0 ; i < var_solution.length ; ++i) {
				var_solution[i] = "";
				if (exact_value[i] != mark) {
		
					var_solution[i] += exact_value[i];
				}
				else if (allParametric[i] == 0) {
					var_solution[i] += Alphabet[i];
				}
				else {
					var_solution[i] = "";
					if (arr_const[i] != 0) {
						var_solution[i] += arr_const[i];
					}
					
					for (int j = 0 ; j < mGauss.getCol()-1 ; j++) {
						if (value_variable[i][j] != 0) {
							 	if (value_variable[i][j] < 0) {
							 		if (var_solution[i].length() != 0) {
							 			var_solution[i] += " - " ;
							 			}
							 		else {
							 			var_solution[i] += "-" ;
							 		}
							 		
							 	}
							 	else {
							 		if (var_solution[i].length() != 0) {
							 			var_solution[i] += " + " ;
							 			}
							 		else {
							 			var_solution[i] += "" ;
							 		}
							 	}
							 	if (Math.abs(value_variable[i][j]) == 1) {
							 		 var_solution[i] += ""+  Alphabet[j];
							 	}
							 	else {
							 		var_solution[i] += ""+ Math.abs(value_variable[i][j]) + Alphabet[j];
							 	}
						}			
					}
				}
			}
			
			for (int i = 0 ; i < var_solution.length ; ++i) {
				System.out.printf("x%d = %s\n",i+1,var_solution[i]);
			}
			FileInputOutput.opsiSaveFile(var_solution);
			
	
		}
		else {
			System.out.print("Matriks tidak ada solusi\n");
		}
		

	}
	
	
	// Display hasil SPL gauss jordan
	public static void displayGaussJordan(Matrix mGaussJordan) {
		DisplaySolution.displayGauss(mGaussJordan);
	}
	
	//Display hasil SPL metode cramer
	public static void displayCramer(Matrix mat_Cramer) {
		// Matrix input dalam cramer
		double x = -9999;
		if (Double.compare(mat_Cramer.getElmt(0, 0)   , x) == 0    )  {
			System.out.println("Metode cramer tidak dapat dilakukan");
		}
		else {
			String[] var_solution = new String[mat_Cramer.getRow()];
			for (int i = 0 ; i < mat_Cramer.getRow() ; ++i) {
				System.out.printf("x%d = %f\n",i+1,mat_Cramer.getElmt(i, 0));
				var_solution[i] = "" + mat_Cramer.getElmt(i, 0);
			}
			FileInputOutput.opsiSaveFile(var_solution);
			
		}
		
		
	}
	
	// Display hasil SPL matriks balikan
	public static void displaySPLbalikan(Matrix mat_SPL_balikan) {
		// input sudah melalui proses SPLbalikan
		for (int i = 0 ; i < mat_SPL_balikan.getRow(); i++) {
			System.out.printf("x%d = %f\n",i+1,mat_SPL_balikan.getElmt(i, 0));
		}
		String[] var_solution = new String[mat_SPL_balikan.getRow()];
		for (int i = 0 ; i < mat_SPL_balikan.getRow() ; ++i) {
			var_solution[i] = "" + mat_SPL_balikan.getElmt(i, 0);
		}
		FileInputOutput.opsiSaveFile(var_solution);
		
	}
	
	// display hasil determinan
	public static void displayDeterminan(double det) {
		
		System.out.printf("Determinan  = %f\n",DisplaySolution.dformat(det));
		FileInputOutput.opsiSaveFile(det);
		
	}
	
	// displaty hasil matriks inverse
	public static void displayMatriksInverse(Matrix matInv) {
		matInv.displayMatrix();
		FileInputOutput.opsiSaveFile(matInv);
	}
	
	public static boolean allZero(double[] arr) {
		boolean state = true; // all zero initial
		for (int i = 0 ; i < arr.length ; i ++) {
			if (arr[i] != 0)  {
				state = false;
				break;
			}
		}
		return state;
	}
	
	// untuk rounding 
	public static double dformat(double num) {
		return Math.round(num * Math.pow(10, 5))/ Math.pow(10, 5);
	}
}
