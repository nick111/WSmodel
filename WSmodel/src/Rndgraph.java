import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class Rndgraph {

	public static void main(String[] args) {
		
		int n = Integer.parseInt(args[1]);
		double p = Double.parseDouble(args[2]);
		
		String fileAddress = "rndout_" + "_" + n + "_" + p + ".csv"; 
		
		ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();
		
		for(int i = 0; i < n; i++){
			for(int j = i + 1; j < n; j++){
				if(Math.random() < p){
					map.put(n * i + j, "");
				}
			}
		}
		
		// ファイル出力
		
		File file = new File(fileAddress);

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));

			for (Map.Entry<Integer, String> e : map.entrySet()) {
				int s = e.getKey() / n;
				int t = e.getKey() % n;
				String str = s + "," + t;
				bw.write(str);
				bw.newLine();	
			}
			
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
