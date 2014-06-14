import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WSmodel {

	public static void main(String[] args) {

		int k = Integer.parseInt(args[1]);
		int n = Integer.parseInt(args[2]);
		double p = Double.parseDouble(args[3]);
		
		String fileAddress = "wsout_" + k + "_" + n + "_" + p + ".csv"; 
		
		ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<Integer, String>();

		// �ŏ��̃O���t�쐬
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < k * 0.5; j++) {
				// �����O���t�����A�ȒP�̂��߂Ɏn�_(s)�ƏI�_(t)��݂���B
				// �n�_�͏�ɏI�_�����������Ȃ�悤�Ɉӎ�����i�_�u����h�����߁j�B
				int s = i;
				int t = i + j;
				if (t >= n) {
					s = t - n;
					t = i;
				}
				// �n�_��n�������ďI�_�𑫂����ƂŁA��̐��œ񎟌��̐���\�����A�n�b�V������
				map.put(s * n + t, "");
			}
		}

		// �����N�̂Ȃ�����
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		while(it.hasNext()){
			
			Map.Entry<Integer, String> e = it.next();
			
			if (Math.random() < p) {

				int s1 = e.getKey() / n;
				int t1 = e.getKey() % n;

				map.remove(e.getKey());
				int a;
				if (Math.random() < 0.5) {
					a = s1;
				} else {
					a = t1;
				}

				int s2;
				int t2;

				while (true) {
					int c = (int) (n * Math.random());
					if (c == s1 || c == t1)
						continue;
					if (c < a) {
						s2 = c;
						t2 = a;
					} else {
						s2 = a;
						t2 = c;
					}
					if (map.get(new Integer(s2 * n + t2)) == null) {
						map.put(s2 * n + t2, "");
						break;
					} else {
						continue;
					}
				}
			}
		}

		// �t�@�C���o��
		
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
