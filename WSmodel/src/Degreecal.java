import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Degreecal {

	public static void main(String[] args) {

		String inFileAddress = "";
		String outFileAddress = "";
		int n = 0;

		if (args[0].equals("WS")) {
			inFileAddress = "wsout_" + args[1] + "_" + args[2] + "_" + args[3] + ".csv";
			n = Integer.parseInt(args[2]);
			outFileAddress = "ws_deg_" + args[1] + "_" + args[2] + "_" + args[3] + ".csv";
		} else if (args[0].equals("RND")) {
			inFileAddress = "rndout_" + "_" + args[1] + "_" + args[2] + ".csv";
			n = Integer.parseInt(args[1]);
			outFileAddress = "rnd_deg_" + args[1] + "_" + args[2] + ".csv";
		} else if (args[0].equals("BA")) {
			inFileAddress = "baout_" + args[1] + ".csv";
			n = Integer.parseInt(args[1]);
			outFileAddress = "ba_deg_" + args[1] + ".csv";
		}
		
		int[] degree = new int[n];
		
		File inFile = new File(inFileAddress);
		try {
			BufferedReader br = new BufferedReader(new FileReader(inFile));
			String oneLineStr;
			while ((oneLineStr = br.readLine()) != null) {
				int s = Integer.parseInt(oneLineStr.split(",")[0]);
				int t = Integer.parseInt(oneLineStr.split(",")[1]);
				degree[s] = 1 + degree[s];
				degree[t] = 1 + degree[t];
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 次数ごとの数計算
		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
		for(int i = 0; i < n; i++){
			if(map.get(degree[i])==null){
				map.put(degree[i], 1);
			}else{
				map.put(degree[i], 1 + map.get(degree[i]));
			}
		}
		
		
		
		// ファイル出力
		
		File outFile = new File(outFileAddress);
		int t = 0;
		int sum = 0;
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));

			for (Map.Entry<Integer, Integer> e : map.entrySet()) {
				if(t == 0) t = e.getKey();
				for(int i = t; i < e.getKey(); i++){
					String str = i + " " + 0;
					bw.write(str);
					bw.newLine();	
				}
				
				String str = e.getKey() + " " + e.getValue();
				bw.write(str);
				bw.newLine();	
				t = e.getKey() + 1;
				sum = sum + e.getKey() * e.getValue();
			}
			
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		
		
		}

		// 最大次数
		int maxdeg = t - 1;
		int avdeg = sum / n; 
		if (args[0].equals("WS")) {
			System.out.println("WSmodel k=" + args[1] +" n=" + args[2] + " p=" + args[3] + "   max degree:" + maxdeg +"   average degree:" + avdeg);
		} else if (args[0].equals("RND")) {
			System.out.println("RandomNetwork n=" + args[1] +" p=" + args[2] +"   max degree:" + maxdeg +"   average degree:" + avdeg);
		}
		 
		
	}
}
