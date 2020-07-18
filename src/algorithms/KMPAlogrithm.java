package algorithms;

// KMP�㷨��Ϊ����
// 1. ��һ�����ǵó�����Ҫ���Ӵ���ǰ׺��ͬ����
// 2. �ڶ������ǽ��Ӵ���ǰ׺��ͬ����������ƶ�
// 3. ���������ǽ��Ӵ����������жԱ�


public class KMPAlogrithm {
	
	public void findStr(String main_str, String child_str) {
		int child_str_length = child_str.length();
		int[] prefix = new int[child_str_length]; 
		// �ó�����Ҫ��ǰ׺��
		getPrefix(child_str,prefix,child_str_length);
		// �����õ���ǰ׺������ƶ�
		movePrefix(prefix,child_str_length);
		for(int one_index:prefix) {
			System.out.println(one_index);
		}
		System.out.println("�õ��ƶ�λ�ã���������ַ�����ƥ��");
		int child_str_index = 0;
		int main_str_index = 0;
		while(main_str_index < main_str.length()) {
			if(child_str_index == child_str_length - 1 && 
					child_str.charAt(child_str_index) == main_str.charAt(main_str_index)) {
				System.out.println("�ҵ�һ���ˣ�λ�þ���:"+(main_str_index-child_str_length+1));
				child_str_index = prefix[child_str_index];
			}
			
			if(main_str.charAt(main_str_index) == child_str.charAt(child_str_index)) {
				child_str_index++;
				main_str_index++;
			}else {
				child_str_index = prefix[child_str_index];
				if(child_str_index == -1) {
					child_str_index++;
					main_str_index++;
				}
			}
		}
	}
	

	// �������ƶ�λ�ñ�
	private void movePrefix(int[] prefix,int child_str_length) {
		for(int index = child_str_length - 1;index > 0; index--) {
			prefix[index] = prefix[index - 1];
		}
		prefix[0] = -1;
	}

    // �������Ӵ�ǰ��׺��ͬ������
	public void getPrefix(String child_str,int[] prefix_table,int child_str_length) {
		prefix_table[0] = 0;
		char[] child_chars = child_str.toCharArray();
		// �Աȵ�λ��
		int index = 1;
		// Ŀǰ�ԱȺ�֮ǰ�Աȵ�λ��
		int compared_index = 0;
		
		while(index < child_str_length) { 
			if(child_chars[index] == child_chars[compared_index]) {
				prefix_table[index++] = ++compared_index; 
			}else {
				if(compared_index > 0) {
					// ��һ�����ص㣬�������������ĵط�����������ǵȺŵĴ�����
					// ��ߺú����
					compared_index = prefix_table[compared_index - 1];
					
				}else {
					prefix_table[index++] = 0;
				}
			}
		}
//		for(int one_index:prefix_table) {
//			System.out.println(one_index);
//		}
//		System.out.println(prefix_table);
	}


	public static void main(String[] args) {
		String main_str = "aaaaaaaaac";
		String child_str = "aac";
        KMPAlogrithm kmpa = new KMPAlogrithm();
        kmpa.findStr(main_str, child_str);
	}

}
