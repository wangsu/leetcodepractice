package io.wangsu.service;

import io.wangsu.domain.ListNode;
import io.wangsu.domain.TreeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by SuW on 12/19/2016.
 */
@Service
public class LeetCodeService {
    private static final Logger log = LoggerFactory.getLogger(LeetCodeService.class);

    /**
     * 283. Move Zeroes Better Solution
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int nonZero = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[nonZero++]=nums[i];
            }
        }
        for(int i=nonZero;i<nums.length;i++){
            nums[i]=0;
        }
    }

    /**
     * 283. Move Zeroes
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        for(int i=0;i<nums.length;i++){
            if(nums[i]==0){
                for(int j=i+1;j<nums.length;j++){
                    if(nums[j]!=0){
                        nums[i] = nums[j];
                        nums[j] = 0;
                        break;
                    }
                }
            }
        }
    }

    /**
     * 455. Assign Cookies
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        for(int i=g.length-1, s_position=s.length-1;i>=0&&s_position>=0;i--){
            if(g[i]<=s[s_position]){
                s_position--;
                result++;
                continue;
            }
        }
        return result;
    }


    /**
     * 226. Invert Binary Tree
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        List<TreeNode> hold = new ArrayList();
        if(root!=null)
            hold.add(root);
        while(!hold.isEmpty()){
            TreeNode current = hold.get(0);
            if(current.left!=null)
                hold.add(current.left);

            if(current.right!=null)
                hold.add(current.right);

            //Switch
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;

            hold.remove(0);
        }
        return root;
    }

    /**
     * 258. Add Digits
     * @param num
     * @return
     */
    public int addDigits(int num) {
        if(num==0){
            return 0;
        }else if(num%9==0){
            return 9;
        }else{
            return num%9;
        }
    }

    /**
     * 389. Find the Difference
     * @param s
     * @param t
     * @return
     */
    public char findTheDifference(String s, String t) {
        Map<Character,Integer> smap = new HashMap();
        Map<Character,Integer> tmap = new HashMap();
        for(int i=0;i<s.length();i++){
            if(!smap.containsKey(s.charAt(i))){
                smap.put(s.charAt(i),1);
            }else{
                smap.put(s.charAt(i),smap.get(s.charAt(i))+1);
            }
        }
        for(int i=0;i<t.length();i++){
            if(!tmap.containsKey(t.charAt(i))){
                tmap.put(t.charAt(i),1);
            }else{
                tmap.put(t.charAt(i),tmap.get(t.charAt(i))+1);
            }
        }
        for(Character tchar:tmap.keySet()){
            if(!smap.containsKey(tchar)){
                if(log.isTraceEnabled()){
                    log.debug("{} is new",""+tchar);
                }
                return tchar;
            }else{
                if(log.isTraceEnabled()) {
                    log.debug("compare char {} on t {} and s {}", tchar,tmap.get(tchar), smap.get(tchar));
                }
                if(tmap.get(tchar)!=smap.get(tchar)){
                    if(log.isTraceEnabled()){
                        log.debug("{} is less count",""+tchar);
                    }
                    return tchar;
                }
            }
        }

        if(log.isTraceEnabled()){
            log.debug("cannot find new, use the last one");
        }
        return t.charAt(t.length()-1);
    }

    /**
     * 371. Sum of Two Integers
     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        int sum = a^b;
        int carry = (a&b) << 1;
        while(carry!=0){
            int t_sum = sum^carry;
            carry = (sum&carry)<<1;
            sum = t_sum;
        }
        return sum;
    }


    /**
     * 292. Nim Game
     * @param n
     * @return
     */
    public boolean canWinNim(int n) {
        if(n<4){
            return true;
        }else{
            if(n%4==0){
                return false;
            }else{
                return true;
            }
        }
    }


    /**
     * 448. Find All Numbers Disappeared in an Array
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i=0;i<nums.length;i++){
            int position = Math.abs(nums[i])-1;
            if(nums[position]>0){
                if(log.isTraceEnabled()){
                    log.debug("mark {} as negative",position);
                }
                nums[position] = -nums[position];
            }

        }
        List<Integer> result = new ArrayList();
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0){
                result.add(i+1);
            }
        }
        return result;
    }

    /**
     * 463. Island Perimeter
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        int result = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++){
                if(grid[i][j]==1){
                    result += (4-getSideCount(i,j,grid));
                    if(log.isTraceEnabled()){
                        log.debug("grid[{}][{}] has side {}, so count is {}",i,j,getSideCount(i,j,grid),4-getSideCount(i,j,grid));
                    }
                }
            }
        }
        return result;
    }

    private int getSideCount(int x, int y, int[][] grid) {
        int result = 0;
        try {
            if (grid[x - 1][y] == 1) {
                result++;
            }
        } catch (RuntimeException e) {
        }
        try {
            if (grid[x][y - 1] == 1) {
                result++;
            }
        } catch (RuntimeException e) {
        }
        try {
            if (grid[x + 1][y] == 1) {
                result++;
            }
        } catch (RuntimeException e) {
        }
        try {
            if (grid[x][y + 1] == 1) {
                result++;
            }
        } catch (RuntimeException e) {
        }
        return result;
    }

    /**
     * 344. Reverse String
     * @param s
     * @return
     */
    public String reverseString(String s) {
        StringBuilder result = new StringBuilder();
        for(int i=s.length()-1;i>=0;i--){
            result.append(s.charAt(i));
        }
        return result.toString();
    }

    /**
     * 412. Fizz Buzz
     * @param n
     * @return
     */
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<String>();
        for(int i=1;i<=n;i++){
            if(i%15==0){
                result.add("FizzBuzz");
            }else if(i%5==0){
                result.add("Buzz");
            }else if(i%3==0){
                result.add("Fizz");
            }else{
                result.add(""+i);
            }
        }
        return result;
    }

    /**
     * 461. Hamming Distance
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        String xStr = Integer.toString(x,2);
        String yStr = Integer.toString(y,2);

        if(log.isTraceEnabled()) {
            log.debug("xStr {}", xStr);
            log.debug("yStr {}",yStr);
        }

        int xlength = xStr.length();
        int ylength = yStr.length();
        int result = 0;
        if(xlength>ylength){
            for(int i=0;i<ylength;i++){
                if(log.isTraceEnabled()) {
                    log.debug("x>y charAt({}) {} {} ", i, xStr.charAt(xlength-1-i),yStr.charAt(ylength-1-i));
                }
                if(xStr.charAt(xlength-1-i)!=yStr.charAt(ylength-1-i)){
                    result++;
                }
            }
            for(int i=ylength;i<xlength;i++){
                if(xStr.charAt(xlength-1-i)!="0".charAt(0)){
                    result++;
                }
            }
        }else{
            for(int i=0;i<xlength;i++){
                if(log.isTraceEnabled()) {
                    log.debug("x<=y charAt({}) {} {} ", i, xStr.charAt(xlength-1-i),yStr.charAt(ylength-1-i));
                }
                if(xStr.charAt(xlength-1-i)!=yStr.charAt(ylength-1-i)){
                    result++;
                }
            }
            for(int i=xlength;i<ylength;i++){
                if(log.isTraceEnabled()) {
                    log.debug("x<=y charAt({}) {} ", i, yStr.charAt(ylength-1-i));
                }
                if(yStr.charAt(ylength-1-i)!="0".charAt(0)){
                    result++;
                }
            }

            if(log.isTraceEnabled()) {
                log.debug("xStr {}", xStr);
                log.debug("yStr {}",yStr);
            }
        }
        return result;
    }

    /**
     * 383. Ransom Note
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct(String ransomNote, String magazine) {
        Map<String,Integer> map = new HashMap();
        for(int i=0;i<magazine.length();i++){
            String cur = String.valueOf(magazine.charAt(i));
            if(map.containsKey(cur)){
                map.put(cur,map.get(cur)+1);
            }else{
                map.put(cur,1);
            }
        }
        for(int i=0;i<ransomNote.length();i++){
            String cur = String.valueOf(ransomNote.charAt(i));
            if(map.containsKey(cur)&&map.get(cur)>0){
                map.put(cur,map.get(cur)-1);
            }else{
                return false;
            }
        }
        return true;
    }

    /**
     * 9. Palindrome Number
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        int original = x;
        int rev = 0;
        int digit = 0;
        while(x>0){
            digit = x%10;
            rev = rev*10 + digit;
            x = x/10;
        }
        if(rev==original){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 13. Roman to Integer
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int last = romanChartoInt(s.charAt(0));
        int total = 0;
        for(int i=1;i<s.length();i++){
            int cur = romanChartoInt(s.charAt(i));
            if(last<cur){
                total -= last;
            }else{
                total += last;
            }
            last = cur;
        }
        total += last;
        return total;
    }

    private int romanChartoInt(char c){
        switch(c){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }

    /**
     * 14. Longest Common Prefix
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        String common = "";
        if(strs.length==0){
            return common;
        }
        String baseString = strs[0];
        for(int j=0;j<baseString.length();j++){
            char curInBase = baseString.charAt(j);
            for(int i=1;i<strs.length;i++){
                String compareString = strs[i];
                try {
                    char curInCompare = compareString.charAt(j);
                    if(curInBase!=curInCompare){
                        return common;
                    }
                }catch(StringIndexOutOfBoundsException e){
                    return common;
                }
            }
            common += String.valueOf(curInBase);
        }
        return common;
    }

    /**
     * 20. Valid Parentheses
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        List<Integer> list = new ArrayList();
        for(int i=0;i<s.length();i++){
            int cur = s.charAt(i);
            if(!list.isEmpty()&&(list.get(list.size()-1)==cur-1||list.get(list.size()-1)==cur-2)){
                //51 ( 52 ) 133 [ 135] 173{ 175}
                list.remove(list.size()-1);
            }else{
                list.add(cur);
            }
        }
        if(list.size()==0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 21. Merge Two Sorted Lists
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1==null&&l2==null){
            return null;
        }
        if(l1!=null&&l2==null){
            return l1;
        }
        if(l1==null&&l2!=null){
            return l2;
        }
        ListNode l1cur = l1;
        ListNode l2cur = l2;
        ListNode head = null;
        ListNode result = null;
        while(l1cur!=null||l2cur!=null){
            if(l1cur!=null&&l2cur!=null){
                if(l1cur.val<=l2cur.val){
                    if(result==null){
                        result = new ListNode(l1cur.val);
                        head = result;
                    }else{
                        result.next = new ListNode(l1cur.val);
                        result = result.next;
                    }
                    l1cur = l1cur.next;
                }else{
                    if(result==null){
                        result = new ListNode(l2cur.val);
                        head = result;
                    }else{
                        result.next = new ListNode(l2cur.val);
                        result = result.next;
                    }
                    l2cur = l2cur.next;
                }
            }else if(l1cur!=null&&l2cur==null){
                result.next = new ListNode(l1cur.val);
                l1cur = l1cur.next;
                result = result.next;
            }else if(l1cur==null&&l2cur!=null){
                result.next = new ListNode(l2cur.val);
                l2cur = l2cur.next;
                result = result.next;
            }
        }
        return head;

    }

    /**
     * 26. Remove Duplicates from Sorted Array
     * @param nums
     * @return
     */

    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int lastV = 0;
        for(int i=0;i<nums.length;i++){
            if(i==0){
                lastV=nums[i];
                continue;
            }else{
                int cur = nums[i];
                if(lastV!=cur){
                    nums[++slow] = cur;
                    lastV = cur;
                }
            }
        }
        return ++slow;
    }

    /**
     * 27. Remove Element
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int slow=0;
        for(int i=0;i<nums.length;i++){
            int cur = nums[i];
            if(cur!=val){
                nums[slow++]=cur;
            }
        }
        return slow;
    }

    /**
     * 28. Implement strStr()
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        if(haystack.length()==0&&needle.length()==0){
            return 0;
        }
        for(int i=0;i<haystack.length();i++){
            if(i+needle.length()<=haystack.length()){
                String compareStr = haystack.substring(i,i+needle.length());
                if(compareStr.equals(needle)){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 35. Search Insert Position
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        if(nums.length==0){
            return 0;
        }
        int lastV = nums[0];
        if(target<=lastV){
            return 0;
        }
        for(int i=1;i<nums.length;i++){
            int cur = nums[i];
            if(target==cur){
                return i;
            }
            if(lastV<target&&target<cur){
                return i;
            }
            lastV = cur;
        }
        return nums.length;
    }

    /**
     * 38. Count and Say
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        if(n<=0){
            return "";
        }
        String lastWord = "1";
        for(int i=1;i<n;i++){
            String curWord = sayStr(lastWord);
            lastWord = curWord;
        }
        return lastWord;
    }

    private String sayStr(String str){
        String result = "";
        int last = Integer.parseInt(str.substring(0,1));
        int repeat = 1;
        for(int i=1;i<str.length();i++){
            int cur = Integer.parseInt(str.substring(i,i+1));
            if(cur==last){
                repeat++;
            }else{
                result += ""+repeat+last;
                repeat = 1;
            }
            last = cur;
        }
        result += ""+repeat+last;
        return result;
    }

    /**
     * 53. Maximum Subarray
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int slow = 0;
        if(nums.length==0){return 0;}
        if(nums.length==1){return nums[0];}
        int fast = 1;
        int sum = nums[0];
        int max = nums[0];
        while(slow<nums.length&&fast<nums.length){
            if(sum>max){
                max = sum;
            }
            if(sum<=0){
                slow++;
                fast = slow +1;
                sum = nums[slow];
            }else{
                sum += nums[fast];
                fast ++;
            }
        }
        if(sum>max){
            max = sum;
        }
        return max;
    }

    /**
     * 58. Length of Last Word
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        String[] strs = s.split(" ");
        if(strs.length>0){
            return strs[strs.length-1].length();
        }else{
            return 0;
        }
    }

    /**
     * 66. Plus One
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        boolean carry = false;
        for(int i=digits.length-1;i>=0;i--){
            if(i==digits.length-1){
                digits[i] += 1;
            }else{
                if(carry){
                    digits[i] += 1;
                }
            }
            if(digits[i]==10){
                digits[i] -= 10;
                carry = true;
            }else{
                carry = false;
            }
        }

        if(carry){
            int[] result = new int[digits.length+1];
            for(int i=0;i<result.length;i++){
                if(i==0){
                    result[i]=1;
                }else{
                    result[i]=digits[i-1];
                }
            }
            return result;
        }else{
            return digits;
        }
    }

    /**
     * 67. Add Binary
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int diff = a.length()-b.length();
        if(diff<0){
            diff *= -1;
            String temp = a;
            a = b;
            b = temp;
        }
        boolean carry = false;
        for(int i=a.length()-1;i>=0;i--){
            int sum = Integer.parseInt(String.valueOf(a.charAt(i)))+((i-diff>=0)?Integer.parseInt(String.valueOf(b.charAt(i-diff))):0)+(carry?1:0);
            if(sum>=2){
                sum-=2;
                carry=true;
            }else{
                carry=false;
            }
            sb.insert(0,""+sum);
        }
        if(carry){
            sb.insert(0,"1");
        }
        return sb.toString();

    }

    /**
     * 69. Sqrt(x)
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        for(int i=1;i<x;i++){
            long lower = (long)i*(long)i;
            long higher = (long)(i+1)*(long)(i+1);

            if(lower<=x&&higher>x){
                return i;
            }

        }
        return x;
    }
}
