package io.wangsu.service;

import io.wangsu.domain.ListNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Wangs on 5/4/2017.
 */
@Service
public class LeetCodeMediumService {
    private static final Logger log = LoggerFactory.getLogger(LeetCodeMediumService.class);

    /**
     * 2. Add Two Numbers
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean carry = false;
        int initVal = l1.val+l2.val;
        if(initVal>=10){
            initVal -=10;
            carry = true;
        }
        ListNode result = new ListNode(initVal);
        l1 = l1.next;
        l2 = l2.next;
        ListNode curResult = result;
        while(l1!=null||l2!=null){
            int curVal = (l1==null?0:l1.val)+(l2==null?0:l2.val)+(carry?1:0);
            if(curVal>=10){
                curVal-=10;
                carry = true;
            }else{
                carry = false;
            }
            curResult.next = new ListNode(curVal);
            curResult = curResult.next;
            l1 = (l1==null?null:l1.next);
            l2 = (l2==null?null:l2.next);
        }
        if(carry){
            curResult.next = new ListNode(1);
        }
        return result;
    }

    /**
     * 3. Longest Substring Without Repeating Characters
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s==null){
            return 0;
        }
        if(s!=null&&s.length()<2){
            return s.length();
        }
        Map<String,Integer> map = new HashMap();
        int slow = 0;
        int max = 0;
        for(int i=0;i<s.length();i++){
            if(map.containsKey(String.valueOf(s.charAt(i)))){
                slow = Math.max(slow,map.get(String.valueOf(s.charAt(i)))+1);
            }
            map.put(String.valueOf(s.charAt(i)),i);
            max = Math.max(i-slow+1,max);
        }
        return max;
    }

    public String longestPalindrome(String s) {
        String result = "";
        int curPLength = 0;
        for(int i=0;i<s.length();i++){
            if(isPalindrome(s,i-curPLength-1,i)){
                result = s.substring(i-curPLength-1,i+1);
                curPLength+=2;
            }else if(isPalindrome(s,i-curPLength,i)){
                result = s.substring(i-curPLength,i+1);
                curPLength+=1;
            }
        }
        return result;
    }

    /**
     * 5. Longest Palindromic Substring
     * @param s
     * @param start
     * @param end
     * @return
     */
    private boolean isPalindrome(String s, int start,int end){
        if(start<0){
            return false;
        }
        while(start<end){
            if(s.charAt(start++)!=s.charAt(end--)){
                return false;
            }
        }
        return true;
    }

    /**
     * 8. String to Integer (atoi)
     * @param str
     * @return
     */
    public int myAtoi(String str) {
        if(str==null||str.length()==0){
            return 0;
        }
        str = str.trim();
        int sign = 1;
        int pos = 0;
        if(str.startsWith("-")){
            sign = -1;
            pos++;
        }
        if(str.startsWith("+")){
            sign = 1;
            pos++;
        }
        long sum = 0;
        while(pos<str.length()){
            if (!Character.isDigit(str.charAt(pos))){
                return (int) sum * sign;
            }
            sum = sum*10 + Integer.parseInt(String.valueOf(str.charAt(pos)));
            if(sum>Integer.MAX_VALUE){
                if(sign == -1){
                    return Integer.MIN_VALUE;
                }else{
                    return Integer.MAX_VALUE;
                }
            }
            pos++;
        }
        return (int) sum * sign;

    }

    /**
     * 11. Container With Most Water
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int result = 0;
        if(height.length<2){
            return 0;
        }
        int slow=0;
        int fast=height.length-1;
        while(slow<fast&&slow<height.length-1){
            result = Math.max(result,(fast-slow)*Math.min(height[fast],height[slow]));
            if(height[slow]<height[fast]){
                slow++;
            }else{
                fast--;
            }
        }
        return result;
    }

    /**
     * 6. ZigZag Conversion
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(s.length()<=1||numRows==1){
            return s;
        }
        List<List<Character>> list = new ArrayList();
        for(int i=0;i<s.length();i++){
            int col = i%(2*numRows-2);
            if(col>=numRows){
                col = 2*numRows-col-2;
            }

            if(list.size()<numRows){
                List<Character> column = new ArrayList();
                list.add(column);
                column.add(s.charAt(i));
            }else{
                List<Character> column = list.get(col);
                column.add(s.charAt(i));
            }
        }
        StringBuilder sb = new StringBuilder();
        for(List<Character> column:list){
            for(Character c:column){
                sb.append(String.valueOf(c));
            }
        }
        return sb.toString();
    }

    /**
     * 12. Integer to Roman
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        int[] values = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] strs = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder result = new StringBuilder();
        for(int i=0;i<values.length;i++){
            while(num>=values[i]){
                num = num-values[i];
                result.append(strs[i]);
            }
        }
        return result.toString();

    }

    /**
     * 15. 3Sum
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i>=1&&nums[i]==nums[i-1]){
                continue;
            }
            int target = -nums[i];
            int j=i+1;
            int k=nums.length-1;
            while(j<k){
                if(nums[j]+nums[k]==target){
                    result.add(Arrays.asList(nums[i],nums[j++],nums[k--]));
                    while(j>i&&j<k&&nums[j]==nums[j-1]){
                        j++;
                    }
                    while(j<k&&k+1<nums.length-1&&nums[k]==nums[k+1]){
                        k--;
                    }
                }else if(nums[j]+nums[k]>target){
                    k--;
                }else{
                    j++;
                }

            }

        }
        return result;
    }

    /**
     * 16. 3Sum Closest
     * @param nums
     * @param target
     * @return
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0]+nums[1]+nums[2];
        int diff = Math.abs(target-result);
        for(int i=0;i<nums.length-2;i++){
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                int curSum = nums[j]+nums[k]+nums[i];
                if(curSum>target){
                    k--;
                }else{
                    j++;
                }
                if(Math.abs(target-curSum)<diff){
                    result = curSum;
                    diff = Math.abs(target-curSum);
                }
            }
        }
        return result;
    }

    /**
     * 17. Letter Combinations of a Phone Number
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList();
        if(digits==null||digits.isEmpty()){
            return result;
        }
        int[] numbers = new int[]{0,1,2,3,4,5,6,7,8,9};
        String[] letters = new String[]{" ","","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        int totalSize = 1;
        String[] strsCur = new String[digits.length()];
        for(int i=0;i<digits.length();i++){
            int curDigit = Integer.parseInt(String.valueOf(digits.charAt(i)));
            String letter = letters[curDigit];
            totalSize *= letter.length();
            strsCur[i] = letter;
        }

        for(int i=0;i<totalSize;i++){
            String now = "";
            int curSize = totalSize;
            for(int j=0;j<digits.length();j++){
                String letter = strsCur[j];
                int lengthForCurString = letter.length();
                curSize /= lengthForCurString;
                now += String.valueOf(letter.charAt((i/curSize)%lengthForCurString));
            }
            result.add(now);
        }
        return result;
    }


    /**
     * 18. 4Sum
     * @param nums
     * @param target
     * @return
     */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList();
        if(nums.length<4){
            return result;
        }
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1;j<nums.length-2;j++){
                if(j>i+1&&nums[j]==nums[j-1]){
                    continue;
                }
                int k=j+1;
                int m=nums.length-1;
                while(k<m){
                    if(nums[i]+nums[j]+nums[k]+nums[m]==target){
                        result.add(Arrays.asList(nums[i],nums[j],nums[k],nums[m]));
                        while(k<m&&nums[k]==nums[k+1])k++;
                        while(k<m&&nums[m]==nums[m-1])m--;
                        k++;
                        m--;
                    }else if(nums[i]+nums[j]+nums[k]+nums[m]<target){
                        k++;
                    }else{
                        m--;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 19. Remove Nth Node From End of List
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode slow = start, fast = start;
        for(int i=0;i<=n;i++){
            fast = fast.next;
        }
        while(fast!=null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return start.next;
    }

    /**
     * 22. Generate Parentheses
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList();
        generateLoop("",result,n,n);
        return result;
    }

    private void generateLoop(String part, List<String> result, int left, int right){
        if(left>right){
            return;
        }
        if(left>0){
            generateLoop(part+"(",result,left-1,right);
        }
        if(right>0){
            generateLoop(part+")",result,left,right-1);
        }
        if(left==0&&right==0){
            result.add(part);
        }
    }

    /**
     * 24. Swap Nodes in Pairs 
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if(head==null){
            return head;
        }
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode from = start;
        while(from.next!=null&&from.next.next!=null){
            ListNode first = from.next;
            ListNode second = from.next.next;
            first.next = second.next;
            second.next = first;
            from = first;
        }
        return start.next;
    }

    /**
     * 29. Divide Two Integers
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        boolean isNeg = false;
        if(dividend>0&&divisor<0){
            isNeg = true;
        }else if(dividend<0&&divisor>0){
            isNeg = true;
        }
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);

        long result = 0;
        long sub = ldivisor;
        long c = 1;

        while(ldividend>=ldivisor){
            if(ldividend>=sub){
                ldividend -= sub;
                result += c;
                sub = sub << 1;
                c = c<<1;
            }else{
                sub = sub >>1;
                c = c>>1;
            }
        }

        if(isNeg){
            result = -result;
        }
        return (int)Math.min(Math.max(Integer.MIN_VALUE,result),Integer.MAX_VALUE);

    }

    /**
     * 31. Next Permutation
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        if(nums.length<=1){
            return;
        }
        int first = -1;
        for(int i=nums.length-1;i>=1;i--){
            if(nums[i-1]<nums[i]){
                first = i-1;
                break;
            }
        }
        int second = 0;
        if(first!=-1){
            second = first +1;
            for(int i=nums.length-1;i>first;i--){
                if(nums[i]>nums[first]){
                    int temp = nums[i];
                    nums[i] = nums[first];
                    nums[first] = temp;
                    break;
                }
            }
        }
        int end = nums.length-1;
        while(second<end){
            int temp = nums[second];
            nums[second] = nums[end];
            nums[end] = temp;
            second ++;
            end --;
        }

        return;
    }

    /**
     * 33. Search in Rotated Sorted Array
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;
        while(low<high){
            int medium = (low+high)/2;
            if(nums[0]>target^target>nums[medium]^nums[0]>nums[medium]){
                low = medium+1;
            }else{
                high = medium;
            }
        }
        return low==high&&nums[low]==target?low:-1;
    }

    /**
     * 34. Search for a Range
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = findFirst(nums, target);
        result[1] = findLast(nums, target);
        return result;
    }

    private int findFirst(int[] nums,int target){
        int result = -1;
        int start = 0;
        int end = nums.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(nums[mid]>=target){
                end = mid -1;
            }else{
                start = mid +1;
            }
            if(nums[mid]==target){
                result = mid;
            }
        }
        return result;
    }

    private int findLast(int[] nums,int target){
        int result = -1;
        int start = 0;
        int end = nums.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(nums[mid]<=target){
                start = mid +1;
            }else{
                end = mid -1;
            }
            if(nums[mid]==target){
                result = mid;
            }
        }
        return result;
    }

    /**
     * 36. Valid Sudoku
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        Set set  = new HashSet();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                char cur = board[i][j];
                if(cur!='.'){
                    if(!set.add(cur+" at row "+i)||!set.add(cur+" at col "+j)||!set.add(cur+" at "+i/3+" block "+j/3)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 39. Combination Sum
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates==null||candidates.length==0){
            return result;
        }
        List<Integer> comb = new ArrayList<>();
        Arrays.sort(candidates);
        helper(candidates, target, 0, comb, result);
        return result;
    }

    private void helper(int[] candidates, int target, int position, List<Integer> comb, List<List<Integer>> result){
        if(target==0){
            result.add(new ArrayList<Integer>(comb));
            return;
        }
        for(int i=position;i<candidates.length;i++){
            int curTarget = target - candidates[i];
            if(curTarget>=0){
                comb.add(candidates[i]);
                helper(candidates, curTarget, i, comb, result);
                comb.remove(comb.size()-1);
            }else{
                break;
            }
        }
    }

    /**
     * 40. Combination Sum II
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates==null||candidates.length==0) return result;
        Arrays.sort(candidates);
        helper2(candidates,target,0,new ArrayList<Integer>(),result);
        return result;
    }

    private void helper2(int[] candidates, int target, int pos, List<Integer> comb, List<List<Integer>> result){
        if(target==0){
            result.add(new ArrayList<Integer>(comb));
            return;
        }
        for(int i=pos;i<candidates.length;i++){
            int newTarget = target - candidates[i];
            if(newTarget>=0){
                comb.add(candidates[i]);
                helper2(candidates, newTarget, i+1, comb, result);
                comb.remove(comb.size()-1);
            }else{
                break;
            }
            while(i<candidates.length-1&&candidates[i]==candidates[i+1])
                i++;
        }
    }
}
