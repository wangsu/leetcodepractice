package io.wangsu.service;

import io.wangsu.domain.*;
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

    /**
     * 43. Multiply Strings
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();

        int[] res = new int[m+n];
        for(int i=m-1;i>=0;i--){
            for(int j=n-1;j>=0;j--){
                int mult = (num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                int pos1 = i+j;
                int pos2 = i+j+1;
                int sum = res[pos2]+mult;
                res[pos2] = sum%10;
                res[pos1] += sum/10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i:res){
            if(!(sb.length()==0&&i==0)){
                sb.append(i);
            }
        }
        return sb.length()==0?"0":sb.toString();
    }

    /**
     * 46. Permutations
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteHelper(nums,new ArrayList<Integer>(),result);
        return result;
    }

    private void permuteHelper(int[] nums, List<Integer> list, List<List<Integer>> result){
        if(list.size()==nums.length){
            result.add(new ArrayList(list));
        }
        for(int i=0;i<nums.length;i++){
            if(list.contains(nums[i]))continue;
            list.add(nums[i]);
            permuteHelper(nums, list, result);
            list.remove(list.size()-1);
        }
    }

    /**
     * 47. Permutations II
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums==null||nums.length==0)return result;
        Arrays.sort(nums);
        permuteUniqueHelper(nums, new boolean[nums.length], new ArrayList<Integer>(), result);
        return result;
    }

    private void permuteUniqueHelper(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> result){
        if(list.size()==nums.length){
            result.add(new ArrayList(list));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(used[i]||(i!=0&&nums[i]==nums[i-1]&&!used[i-1]))continue;
            used[i]=true;
            list.add(nums[i]);
            permuteUniqueHelper(nums, used, list, result);
            used[i]=false;
            list.remove(list.size()-1);
        }
    }

    /**
     * 48. Rotate Image
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        //first transpose
        for(int i=0;i<matrix.length;i++){
            for(int j=i;j<matrix[0].length;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //then flip by mid column.
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length/2;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length-j-1];
                matrix[i][matrix[0].length-j-1] = temp;
            }
        }
    }

    /**
     * 49. Group Anagrams
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs==null||strs.length==0) return new ArrayList<List<String>>();
        Map<String, List<String>> map = new HashMap<>();
        for(String str:strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String newStr = String.valueOf(chars);
            if(!map.containsKey(newStr)){
                map.put(newStr,new ArrayList<String>());
            }
            map.get(newStr).add(str);
        }
        return new ArrayList<List<String>>(map.values());
    }

    /**
     * 50. Pow(x, n)
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        //x^n = x^(n/2) * x^(n/2) * x^(n%2)
        if(n>=0){
            return myPowPositive(x,n);
        }else{
            return 1/myPowPositive(x,-n);
        }
    }

    private double myPowPositive(double x, int n){
        if(n==0) return 1;
        double result = myPowPositive(x,n/2);
        result *= result;
        //n%2 == 1 ???
        if(n%2!=0){
            result *= x;
        }
        return result;
    }

    /**
     * 54. Spiral Matrix
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix==null||matrix.length==0) return result;

        int rowStart = 0;
        int rowEnd = matrix.length-1;
        int colStart = 0;
        int colEnd = matrix[0].length-1;
        while(rowStart<=rowEnd && colStart<=colEnd){
            //top
            for(int i=colStart;i<=colEnd;i++){
                result.add(matrix[rowStart][i]);
            }
            rowStart++;
            //right
            for(int i=rowStart;i<=rowEnd;i++){
                result.add(matrix[i][colEnd]);
            }
            colEnd--;
            //bottom
            if(rowStart<=rowEnd){
                for(int i=colEnd;i>=colStart;i--){
                    result.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;
            if(colStart<=colEnd){
                for(int i=rowEnd;i>=rowStart;i--){
                    result.add(matrix[i][colStart]);
                }
            }
            colStart++;
        }
        return result;
    }

    /**
     * 55. Jump Game
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if(nums.length==0) return false;
        int max = 0;
        for(int i =0;i<nums.length;i++){
            if(i==nums.length-1) return true;
            max--;
            int cur = nums[i];
            if(cur>max){
                max = cur;
            }
            if(max<=0){
                return false;
            }
        }
        return true;
    }

    /**
     * 56. Merge Intervals
     * @param intervals
     * @return
     */
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> result =new ArrayList<>();
        if(intervals==null||intervals.size()==0) return result;
        intervals.sort((i1,i2)->Integer.compare(i1.start,i2.start));
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        for(Interval cur:intervals){
            if(cur.start<=end){
                end = Math.max(end,cur.end);
            }else{
                result.add(new Interval(start,end));
                start = cur.start;
                end = cur.end;
            }
        }
        result.add(new Interval(start,end));
        return result;
    }

    /**
     * 59. Spiral Matrix II
     * @param n
     * @return
     */
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        int num = 1;
        for(int i=0;i<n/2;i++){
            int last = n-i-1;
            //top
            for(int j=i;j<last;j++){
                result[i][j] = num++;
            }
            //right
            for(int j=i;j<last;j++){
                result[j][last] = num++;
            }
            //bot
            for(int j=last;j>i;j--){
                result[last][j] = num++;
            }
            //left
            for(int j=last;j>i;j--){
                result[j][i] = num++;
            }
        }
        if(n%2==1) result[n/2][n/2] = num;
        return result;
    }

    /**
     * 60. Permutation Sequence
     * @param n
     * @param k
     * @return
     */
    public String getPermutation(int n, int k) {
        int[] factorial = new int[n];
        List<Character> list = new ArrayList<>();
        String result = "";

        //1st get factorial array
        factorial[0] = 1;
        for(int i=1;i<n;i++){
            factorial[i]=factorial[i-1]*i;
        }

        //2nd get char list
        for(int i=1;i<=n;i++){
            list.add((char)(i+'0'));
        }
        //3 k init
        k--;
        for(int i=n;i>=1;i--){
            //4 find current position in char list.
            int j =  k/factorial[i-1];
            result += list.get(j);
            list.remove(j);
            k %= factorial[i-1];
        }

        return result;
    }

    /**
     * 61. Rotate List
     * @param head
     * @param k
     * @return
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(k<1||head==null) return head;
        ListNode currectNode = head;
        int i=1;
        while(currectNode.next!=null){
            currectNode = currectNode.next;
            i++;
        }
        currectNode.next = head;
        k = i - k%i;
        while(k>0){
            currectNode = currectNode.next;
            k--;
        }

        head = currectNode.next;
        currectNode.next = null;

        return head;
    }



    /**
     * 62. Unique Paths
     * @param m
     * @param n
     * @return
     */
    static int[][] paths = new int[101][101];
    public int uniquePaths(int m, int n) {
        if(m<=0||n<=0) return 0;
        if(m==1||n==0) return 1;
        if(paths[m][n]==0){
            paths[m][n] = uniquePaths(m-1,n)+uniquePaths(m,n-1);
        }
        return paths[m][n];
    }

    /**
     * 63. Unique Paths II
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid==null||obstacleGrid.length==0||obstacleGrid[0].length==0||obstacleGrid[0][0]==1){
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] result = new int[m][n];
        for(int i=0;i<m;i++){
            if(obstacleGrid[i][0]==0){
                result[i][0]=1;
            }else{
                break;
            }
        }
        for(int i=0;i<n;i++){
            if(obstacleGrid[0][i]==0){
                result[0][i]=1;
            }else{
                break;
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(obstacleGrid[i][j]==1){
                    result[i][j]=0;
                }else{
                    result[i][j]=result[i-1][j]+result[i][j-1];
                }

            }
        }
        return result[m-1][n-1];

    }

    /**
     * 64. Minimum Path Sum
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        if(grid==null||grid.length==0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] result = new int[m][n];
        result[0][0] = grid[0][0];
        for(int i=1;i<m;i++){
            result[i][0] = grid[i][0]+result[i-1][0];
        }
        for(int i=1;i<n;i++){
            result[0][i] = grid[0][i]+result[0][i-1];
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                result[i][j] = Math.min(result[i-1][j],result[i][j-1])+grid[i][j];
            }
        }
        return result[m-1][n-1];
    }

    /**
     * 71. Simplify Path
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        String[] strs = path.split("/");
        List<String> result = new ArrayList<>();
        for(String str:strs){
            if(str.isEmpty()||str.equals(".")){
                continue;
            }else if(str.equals("..")){
                if(result.size()>0){
                    result.remove(result.size()-1);
                }
            }else{
                result.add(str);
            }
        }
        StringBuilder sb = new StringBuilder();
        for(String str:result){
            sb.append("/").append(str);
        }
        if(result.size()==0){
            sb.append("/");
        }
        return sb.toString();
    }

    /**
     * 73. Set Matrix Zeroes
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        if(matrix==null||matrix.length==0)return;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowZero = false;
        boolean firstColZero = false;
        for(int i=0;i<m;i++){
            if(matrix[i][0]==0){
                firstColZero = true;
                break;
            }
        }
        for(int i=0;i<n;i++){
            if(matrix[0][i]==0){
                firstRowZero = true;
                break;
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][j]==0){
                    matrix[i][0]=0;
                    matrix[0][j]=0;
                }
            }
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(matrix[i][0]==0||matrix[0][j]==0)
                    matrix[i][j]=0;
            }
        }
        if(firstColZero){
            for(int i=0;i<m;i++){
                matrix[i][0]=0;
            }
        }
        if(firstRowZero){
            for(int i=0;i<n;i++){
                matrix[0][i]=0;
            }
        }

    }

    /**
     * 74. Search a 2D Matrix
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null||matrix.length==0)return false;
        int m = matrix.length;
        int n = matrix[0].length;
        int start = 0;
        int end = m*n-1;
        while(start<=end){
            int mid = (start+end)/2;
            int row = mid/n;
            int col = mid%n;
            if(matrix[row][col]==target)return true;
            else if(matrix[row][col]>target){
                end = mid - 1;
            }else{
                start = mid +1;
            }
        }
        return false;
    }

    /**
     * 75. Sort Colors
     * @param nums
     */
    public void sortColors(int[] nums) {
        int i=-1;
        int j=-1;
        for(int k=0;k<nums.length;k++){
            int v = nums[k];
            nums[k] = 2;
            if(v==0){
                nums[++j]=1;
                nums[++i]=0;
            }else if(v==1){
                nums[++j]=1;
            }
        }
    }

    /**
     * 77. Combinations
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        combinehelper(new ArrayList<Integer>(), result, 1, n, k);
        return result;
    }

    private void combinehelper(List<Integer> list, List<List<Integer>> result, int start, int n, int k){
        if(k==0){
            result.add(new ArrayList(list));
            return;
        }
        for(int i=start;i<=n;i++){
            list.add(i);
            combinehelper(list, result,i+1,n,k-1);
            list.remove(list.size()-1);
        }
    }

    /**
     * 78. Subsets
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsetsHelper(new ArrayList<Integer>(), result, 0, nums);
        return result;
    }

    private void subsetsHelper(List<Integer> list, List<List<Integer>> result, int start, int[] nums){
        result.add(new ArrayList(list));
        for(int i =start;i<nums.length;i++){
            list.add(nums[i]);
            subsetsHelper(list,result,i+1,nums);
            list.remove(list.size()-1);
        }
    }

    /**
     * 79. Word Search
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if(board==null||board.length==0||board[0].length==0||word==null)return false;
        if(word.length()==0)return true;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==word.charAt(0)){
                    if(existHelper(board,word,i,j,0)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean existHelper(char[][] board, String word, int i, int j, int n){
        if(word.length()==n)return true;
        if(i<0||i>board.length-1||j<0||j>board[0].length-1||word.charAt(n)!=board[i][j])return false;
        board[i][j]='#';
        boolean result = existHelper(board, word, i+1, j, n+1)||existHelper(board, word, i-1, j, n+1)||existHelper(board, word, i, j+1, n+1)||existHelper(board, word, i, j-1, n+1);
        board[i][j]=word.charAt(n);
        return result;
    }

    /**
     * 80. Remove Duplicates from Sorted Array II
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if(nums==null||nums.length<=2)return nums.length;
        int slow=1,fast = 1;
        boolean dup = false;
        while(fast<nums.length){
            if(nums[fast]!=nums[fast-1]){
                nums[slow++]=nums[fast++];
                dup = false;
            }else{
                if(dup){
                    int skip = fast+1;
                    while(skip<nums.length&&nums[skip]==nums[fast]){
                        skip++;
                    }
                    fast = skip;
                    dup = false;
                }else{
                    nums[slow++]=nums[fast++];
                    dup = true;
                }
            }
        }
        return slow;
    }

    /**
     * 81. Search in Rotated Sorted Array II
     * @param nums
     * @param target
     * @return
     */
    public boolean earchsInRotatedSortedArrayII(int[] nums, int target) {
        if(nums==null||nums.length==0)return false;
        int start = 0;
        int end = nums.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(nums[mid]==target)return true;
            else if(nums[start]==nums[mid]&&nums[mid]==nums[end]){
                start++;end--;
            }else if(nums[start]==nums[mid]){
                start = mid+1;
            }else if(nums[mid]==nums[end]){
                end = mid;
            }else if(nums[start]<nums[mid]){ //left sorted
                if(nums[start]<=target && target < nums[mid]){
                    end = mid-1;
                }else{
                    start = mid;
                }
            }else if(nums[start]>nums[mid]){ //right sorted
                if(nums[mid]<target && target <= nums[end]){
                    start = mid+1;
                }else{
                    end = mid-1;
                }
            }
        }
        return false;
    }

    /**
     * 82. Remove Duplicates from Sorted List II
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode slow = preHead;
        ListNode fast = head;

        while(fast!=null){
            while(fast.next != null && fast.val == fast.next.val){
                //skip all
                fast = fast.next;
            }
            if(slow.next==fast){
                slow = slow.next;
            }else{
                slow.next = fast.next;
            }
            fast = fast.next;

        }
        return preHead.next;
    }

    /**
     * 86. Partition List
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        ListNode head1 = new ListNode(0);
        ListNode head1_start = head1;
        ListNode head2 = new ListNode(0);
        ListNode head2_start = head2;
        while(head!=null){
            if(head.val<x){
                head1_start.next = head;
                head1_start = head1_start.next;
            }else{
                head2_start.next = head;
                head2_start = head2_start.next;
            }
            head = head.next;
        }
        head1_start.next = head2.next;
        head2_start.next = null;
        return head1.next;
    }

    /**
     * 89. Gray Code
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList();
        for(int i=0;i<(1<<n);i++){
            int x = i^i>>1;
            result.add(x);
        }
        return result;
    }

    /**
     * 90. Subsets II
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        subsetsWithDupHelper(result, new ArrayList<Integer>(), 0, nums);
        return result;
    }

    private void subsetsWithDupHelper(List<List<Integer>> result, List<Integer> inter, int pos, int[] nums){
        if(pos<=nums.length){
            result.add(new ArrayList(inter));
        }
        int i=pos;
        while(i<nums.length){
            inter.add(nums[i]);
            subsetsWithDupHelper(result, inter, i+1, nums);
            inter.remove(inter.size()-1);
            i++;
            while(i<nums.length && nums[i]==nums[i-1])i++;
        }
    }

    /**
     * 91. Decode Ways
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if(s==null||s.length()==0||s.charAt(0)=='0')return 0;
        int[] result = new int[s.length()+1];
        result[0]=1;
        result[1]=1;
        for(int i=2;i<=s.length();i++){
            if(isValid(s.substring(i-1,i))){
                result[i]+=result[i-1];
            }
            if(isValid(s.substring(i-2,i))){
                result[i]+=result[i-2];
            }
        }
        return result[s.length()];
    }

    private boolean isValid(String subS){
        if(subS.charAt(0)=='0')return false;
        int value = Integer.valueOf(subS);
        return value>0&&value<=26;
    }

    /**
     * 92. Reverse Linked List II
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head==null)return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for(int i=0;i<m-1;i++) pre = pre.next;
        ListNode start = pre.next;
        ListNode then = start.next;
        for(int i =0;i<n-m;i++){
            start.next= then.next;
            then.next= pre.next;
            pre.next = then;
            then= start.next;
        }
        return dummy.next;
    }

    /**
     * 93. Restore IP Addresses
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList();
        restoreIp(s,result,0,"",0);
        return result;
    }

    private void restoreIp(String ipaddress, List<String> result, int index, String restored, int count){
        if(count>4)return;
        if(count ==4&&index==ipaddress.length())result.add(restored);
        for(int i=1;i<4;i++){
            if(index+i>ipaddress.length())break;
            String s = ipaddress.substring(index,index+i);
            if(((s.startsWith("0"))&&s.length()>1)||(i==3&&Integer.parseInt(s)>=256))continue;
            restoreIp(ipaddress,result,index+i,restored+s+(count==3?"":"."),count+1);
        }
    }

    /**
     * 94. Binary Tree Inorder Traversal
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        if(root==null)return result;
        List<TreeNode> todoNodes = new ArrayList();
        TreeNode cur = root;
        while(cur!=null||!todoNodes.isEmpty()){
            while(cur!=null){
                todoNodes.add(0,cur);
                cur = cur.left;
            }
            cur = todoNodes.get(0);
            todoNodes.remove(0);
            result.add(cur.val);
            cur = cur.right;
        }
        return result;
    }

    /**
     * 95. Unique Binary Search Trees II
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if(n==0){
            return new ArrayList();
        }
        return generateTreesHelper(1,n);
    }
    private List<TreeNode> generateTreesHelper(int start, int end){
        List<TreeNode> result = new ArrayList();
        if(start>end){
            result.add(null);
            return result;
        }
        if(start==end){
            result.add(new TreeNode(start));
            return result;
        }
        for(int i=start;i<=end;i++){
            List<TreeNode> left = generateTreesHelper(start,i-1);
            List<TreeNode> right = generateTreesHelper(i+1,end);
            for(TreeNode lnode:left){
                for(TreeNode rnode:right){
                    TreeNode root = new TreeNode(i);
                    root.left = lnode;
                    root.right = rnode;
                    result.add(root);
                }
            }
        }
        return result;
    }

    /**
     * 96. Unique Binary Search Trees
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] result = new int[n+1];
        result[0]=1;
        result[1]=1;
        for(int i=2;i<=n;i++){
            for(int j=0;j<i;j++){
                result[i]+=result[j]*result[i-j-1];
            }
        }
        return result[n];
    }

    /**
     * 98. Validate Binary Search Tree
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max){
        if(root==null)return true;
        if(root.val<=min||root.val>=max)return false;
        return isValidBST(root.left,min,root.val)&&isValidBST(root.right,root.val,max);
    }

    /**
     * 102. Binary Tree Level Order Traversal
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        levelOrderHelper(result,root,0);
        return result;
    }

    private void levelOrderHelper(List<List<Integer>> result, TreeNode root, int height){
        if(root==null)return;
        if(result.size()<=height){
            result.add(new ArrayList<Integer>());
        }
        result.get(height).add(root.val);
        levelOrderHelper(result,root.left,height+1);
        levelOrderHelper(result,root.right,height+1);
    }

    /**
     * 103. Binary Tree Zigzag Level Order Traversal
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList();
        zigzagLevelOrderHelper(result,root,0);
        return result;
    }

    private void zigzagLevelOrderHelper(List<List<Integer>> result, TreeNode root, int height){
        if(root==null)return;
        if(result.size()<=height){
            result.add(new ArrayList<Integer>());
        }

        if(height%2==0){
            result.get(height).add(root.val);
        }else{
            result.get(height).add(0,root.val);
        }

        zigzagLevelOrderHelper(result,root.left,height+1);
        zigzagLevelOrderHelper(result,root.right,height+1);
    }

    /**
     * 105. Construct Binary Tree from Preorder and Inorder Traversal
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(0,0,inorder.length-1,preorder,inorder);
    }

    private TreeNode buildTreeHelper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder){
        if(preStart>preorder.length-1||inStart>inEnd)return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex = 0;
        for(int i=inStart;i<=inEnd;i++){
            if(preorder[preStart]==inorder[i])
                inIndex = i;
        }
        root.left=buildTreeHelper(preStart+1,inStart,inIndex-1,preorder,inorder);
        root.right=buildTreeHelper(preStart+1+inIndex-inStart,inIndex+1,inEnd,preorder,inorder);
        return root;
    }

    /**
     * 106. Construct Binary Tree from Inorder and Postorder Traversal
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTreePostIn(int[] inorder, int[] postorder) {
        Map<Integer, Integer> inOrderIndexMap = new HashMap();
        for(int i=0;i<inorder.length;i++){
            inOrderIndexMap.put(inorder[i],i);
        }
        return buildTreePostInHepler(inorder,postorder,0,inorder.length-1,0,postorder.length-1,inOrderIndexMap);
    }

    private TreeNode buildTreePostInHepler(int[] inorder, int[] postorder, int inStart, int inEnd, int postStart, int postEnd, Map<Integer, Integer> inOrderIndexMap){
        if(inStart>inEnd||postStart>postEnd) return null;
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inIndex = inOrderIndexMap.get(postorder[postEnd]);
        root.left = buildTreePostInHepler(inorder, postorder, inStart, inIndex-1, postStart, postStart+inIndex-inStart-1, inOrderIndexMap);
        root.right = buildTreePostInHepler(inorder, postorder, inIndex+1, inEnd, postStart+inIndex-inStart, postEnd-1, inOrderIndexMap);
        return root;
    }

    /**
     * 109. Convert Sorted List to Binary Search Tree
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if(head==null)return null;
        return toBST(head,null);
    }

    private TreeNode toBST(ListNode head, ListNode tail){
        ListNode slow = head;
        ListNode fast = head;
        if(head==tail)return null;
        while(fast!=tail&&fast.next!=tail){
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = toBST(head,slow);
        root.right = toBST(slow.next,tail);
        return root;
    }

    /**
     * 113. Path Sum II
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> pathSumResult = new ArrayList();
        if(root==null)return pathSumResult;
        pathSumHelper(root,sum,new ArrayList(),pathSumResult);
        return pathSumResult;
    }

    private void pathSumHelper(TreeNode root, int restSum, List<Integer> inter, List<List<Integer>> pathSumResult){
        if(root==null)return;
        inter.add(root.val);
        if(root.left == null && root.right == null && restSum==root.val){
            pathSumResult.add(new ArrayList(inter));
            inter.remove(inter.size()-1);
            return;
        }else{
            pathSumHelper(root.left,restSum-root.val,inter,pathSumResult);
            pathSumHelper(root.right,restSum-root.val,inter,pathSumResult);
        }
        inter.remove(inter.size()-1);
        return;
    }

    /**
     * 114. Flatten Binary Tree to Linked List
     * @param root
     */
    public void flatten(TreeNode root) {
        if(root==null)return;
        List<TreeNode> list = new ArrayList();
        list.add(root);
        while(!list.isEmpty()){
            TreeNode cur = list.get(0);
            list.remove(0);
            if(cur.right!=null){
                list.add(0,cur.right);
            }
            if(cur.left!=null){
                list.add(0,cur.left);
            }
            if(!list.isEmpty()){
                cur.right=list.get(0);
            }
            cur.left=null;
        }
    }

    /**
     * 116. Populating Next Right Pointers in Each Node
     * @param root
     */
    public void connect(TreeLinkNode root) {
        if(root==null)return;
        if(root.left!=null){
            root.left.next=root.right;
            if(root.next!=null){
                root.right.next=root.next.left;
            }
        }
        connect(root.left);
        connect(root.right);
    }


    /**
     * 117. Populating Next Right Pointers in Each Node II
     * @param root
     */
    public void connect2(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode head = null;
        TreeLinkNode prev = null;
        TreeLinkNode cur = root;
        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {
                    if (prev != null) {
                        prev.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    prev = cur.left;
                }
                if (cur.right != null) {
                    if (prev != null) {
                        prev.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    prev = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
            prev = null;
            head = null;
        }
    }

    /**
     * 120. Triangle
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] result = new int[triangle.size()+1];
        for(int i=triangle.size()-1;i>=0;i--){
            for(int j=0;j<triangle.get(i).size();j++){
                result[j]=Math.min(result[j],result[j+1])+triangle.get(i).get(j);
            }
        }
        return result[0];
    }

    /**
     * 129. Sum Root to Leaf Numbers
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        return sumNumbersHelper(root,0);
    }

    private int sumNumbersHelper(TreeNode root, int level){
        if(root==null)return 0;
        if(root.left==null&&root.right==null) return level*10+root.val;
        return sumNumbersHelper(root.left,level*10+root.val) + sumNumbersHelper(root.right,level*10+root.val);
    }

    /**
     * 131. Palindrome Partitioning
     * @param s
     * @return
     */
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList();
        partitionHelper(s,0,new ArrayList(),result);
        return result;
    }

    private void partitionHelper(String s, int index, List<String> inter, List<List<String>> result){
        if(inter.size()>=0&&index>s.length()-1)
            result.add(new ArrayList(inter));
        for(int i=index;i<s.length();i++){
            if(isPalindrome2(s,index,i)){
                if(index==i)
                    inter.add(Character.toString(s.charAt(i)));
                else
                    inter.add(s.substring(index,i+1));
                partitionHelper(s,i+1,inter,result);
                inter.remove(inter.size()-1);
            }
        }
    }

    private boolean isPalindrome2(String str,int left, int right){
        if(left==right)return true;
        while(left<right){
            if(str.charAt(left)!=str.charAt(right))return false;
            left++;right--;
        }
        return true;
    }

    /**
     * 133. Clone Graph
     * @param node
     * @return
     */
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node==null)return null;
        UndirectedGraphNode result = new UndirectedGraphNode(node.label);
        Map<Integer,UndirectedGraphNode> map = new HashMap();
        map.put(node.label,result);
        List<UndirectedGraphNode> todo = new ArrayList();
        todo.add(node);
        while(!todo.isEmpty()){
            UndirectedGraphNode cur = todo.get(0);
            todo.remove(0);
            for(UndirectedGraphNode neighbor:cur.neighbors){
                if(!map.containsKey(neighbor.label)){
                    map.put(neighbor.label,new UndirectedGraphNode(neighbor.label));
                    todo.add(neighbor);
                }
                map.get(cur.label).neighbors.add(map.get(neighbor.label));
            }
        }
        return result;
    }

    /**
     * 144. Binary Tree Preorder Traversal
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList();
        List<TreeNode> todo = new ArrayList();
        if(root==null)return result;
        todo.add(root);
        while(!todo.isEmpty()){
            TreeNode cur = todo.get(0);
            result.add(cur.val);
            todo.remove(0);
            if(cur.right!=null)
                todo.add(0,cur.right);
            if(cur.left!=null)
                todo.add(0,cur.left);
        }
        return result;
    }

    /**
     * 110. Balanced Binary Tree
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return isBalancedDFSHeight(root)!=-1;
    }

    private int isBalancedDFSHeight(TreeNode root){
        if(root==null)return 0;
        int leftHeight = isBalancedDFSHeight(root.left);
        if(leftHeight==-1)return -1;
        int rightHeight = isBalancedDFSHeight(root.right);
        if(rightHeight==-1)return -1;
        if(Math.abs(leftHeight-rightHeight)>1){
            return -1;
        }
        return Math.max(leftHeight,rightHeight)+1;
    }

    /**
     * 147. Insertion Sort List
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if(head==null)return head;
        ListNode helper = new ListNode(Integer.MIN_VALUE);
        //helper.next = head;
        ListNode cur = head;
        ListNode pre = helper;
        ListNode next = null;
        while(cur!=null){
            next = cur.next;
            while(pre.next!=null&&pre.next.val<cur.val){
                pre = pre.next;
            }
            cur.next = pre.next;
            pre.next = cur;
            pre = helper;
            cur = next;
        }
        return helper.next;
    }

    /**
     * 142. Linked List Cycle II
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        if(head==null||head.next==null)return null;
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        boolean hasCycle = true;
        while(slow!=fast){
            if(slow.next==null){
                hasCycle=false;
                break;
            }
            slow = slow.next;
            if(fast.next==null||fast.next.next==null){
                hasCycle=false;
                break;
            }
            fast=fast.next.next;
        }
        if(!hasCycle){
            return null;
        }
        slow = head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 139. Word Break
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] exists = new boolean[s.length()+1];
        exists[0]=true;
        Set<String> dict = new HashSet();
        for(String str:wordDict){
            dict.add(str);
        }
        for(int end=1;end<=s.length();end++){
            for(int start=0;start<end;start++){
                if(exists[start]&&dict.contains(s.substring(start,end))){
                    exists[end]=true;
                    break;
                }
            }
        }
        return exists[s.length()];
    }
}
