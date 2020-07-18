package algorithms;

// KMP算法分为三步
// 1. 第一步就是得出所需要的子串的前缀相同个数
// 2. 第二步就是将子串的前缀相同个数都向后移动
// 3. 第三步就是将子串和主串进行对比


public class KMPAlogrithm {
	
	public void findStr(String main_str, String child_str) {
		int child_str_length = child_str.length();
		int[] prefix = new int[child_str_length]; 
		// 得出所需要的前缀表
		getPrefix(child_str,prefix,child_str_length);
		// 将所得到的前缀表向后移动
		movePrefix(prefix,child_str_length);
		for(int one_index:prefix) {
			System.out.println(one_index);
		}
		System.out.println("得到移动位置，下面进行字符串的匹配");
		int child_str_index = 0;
		int main_str_index = 0;
		while(main_str_index < main_str.length()) {
			if(child_str_index == child_str_length - 1 && 
					child_str.charAt(child_str_index) == main_str.charAt(main_str_index)) {
				System.out.println("找到一个了，位置就是:"+(main_str_index-child_str_length+1));
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
	

	// 创建出移动位置表
	private void movePrefix(int[] prefix,int child_str_length) {
		for(int index = child_str_length - 1;index > 0; index--) {
			prefix[index] = prefix[index - 1];
		}
		prefix[0] = -1;
	}

    // 创建出子串前后缀相同个数表
	public void getPrefix(String child_str,int[] prefix_table,int child_str_length) {
		prefix_table[0] = 0;
		char[] child_chars = child_str.toCharArray();
		// 对比的位置
		int index = 1;
		// 目前对比和之前对比的位置
		int compared_index = 0;
		
		while(index < child_str_length) { 
			if(child_chars[index] == child_chars[compared_index]) {
				prefix_table[index++] = ++compared_index; 
			}else {
				if(compared_index > 0) {
					// 这一步是重点，整个函数中最精髓的地方，个人理解是等号的传递性
					// 这边好好理解
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
