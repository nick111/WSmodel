import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Triangle {

	public static void main(String[] args) {

		String inFileAddress = "";
		int n = 0;

		if (args[0].equals("WS")) {
			inFileAddress = "wsout_" + args[1] + "_" + args[2] + "_" + args[3]
					+ ".csv";
			n = Integer.parseInt(args[2]);
		} else if (args[0].equals("RND")) {
			inFileAddress = "rndout_" + "_" + args[1] + "_" + args[2] + ".csv";
			n = Integer.parseInt(args[1]);
		} else if (args[0].equals("BA")) {
			inFileAddress = "baout_" + args[1] + ".csv";
			n = Integer.parseInt(args[1]);
		}

		int[][] a = new int[n][n];

		File inFile = new File(inFileAddress);
		try {
			BufferedReader br = new BufferedReader(new FileReader(inFile));
			String oneLineStr;
			while ((oneLineStr = br.readLine()) != null) {
				int s = Integer.parseInt(oneLineStr.split(",")[0]);
				int t = Integer.parseInt(oneLineStr.split(",")[1]);
				
				a[s][t] = 1;
				a[t][s] = 1;
				
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int num = 0;
		
		for(int i = 0; i < n; i++){
			for(int k = i+1; k < n-1; k++){
				if(a[i][k] == 0) continue;
				for(int j = k+1; j < n; j++){
					if(a[k][j] == 0) continue;
					num++;
				}
			}
		}
		
		
		if (args[0].equals("WS")) {
			System.out.println("WSmodel k=" + args[1] +" n=" + args[2] + " p=" + args[3] + "   number of triangles:" + num);
		} else if (args[0].equals("RND")) {
			System.out.println("RandomNetwork n=" + args[1] +" p=" + args[2] +"   number of triangles:" + num);
		}
		
		

	}
}
