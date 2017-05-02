package io.wangsu.service;

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
}
