import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class isSubSeq {
    public static boolean isSubsequence(String s, String t)
    {
        if(s.length() == 0)
            return true;
        int indexS = 0, indexT = 0;
        while(indexT < t.length()) {
            if(s.charAt(indexS) == t.charAt(indexT)) {
                indexS++;
            }
            indexT++;
            if(indexS == s.length())
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
/*		int[] nums1 = {1,2,2,1};
		int[] nums2 = {2,2};
		new Solution().intersection(nums1,nums2);
		int[] result = new Solution().intersection(nums1,nums2);
		for(int num: result) {
			System.out.println(num + " ");
		}*/
        String s = "421",t = "4321";
        System.out.println(isSubsequence(s, t));
    }

}
