import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class BAModel {

	public static void main(String[] args) {
		
		// 初期状態をノード数３にして全ノード間を結ぶ。一般化する場合は修正要。
		int m0 = 3;
		int m =3;
		int n = Integer.parseInt(args[1]);
		int[] k = new int[n];
		int ksum = 0;
		
		String fileAddress = "baout_" + n + ".csv"; 
		
		ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();

		// 初期状態で全ノード同士がリンクを持つようにしているため、もっと一般化する場合（初期状態で全ノードが
		// 結びつかない状態）は、ここも修正すること。
		for(int i = 0; i < m0; i++){
			for(int j = i + 1; j < m0; j++){
				map.put(n * i + j, "");
				ksum += 2;
				k[i]++;
				k[j]++;
			}			
		}
		
		
		// 以下、ノードの追加ロジック
		for(int i = 3; i < n; i++){
			int mk = 0;
			while(mk < m0){
				double p = Math.random();
				double p1 = 0.0;
				for(int j = 0; j < i; j++){
					if(p > p1 && p <= p1 + (double)k[j]/(double)ksum){
						if(map.get(new Integer(j * n + i)) == null){
							map.put(new Integer(j * n + i), "");
							k[j]++;
							k[i]++;
							ksum += 2;
							mk++;
							break;
						}else{
							break;
						}
					}else{
						p1 += (double)k[j]/(double)ksum;
					}
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
