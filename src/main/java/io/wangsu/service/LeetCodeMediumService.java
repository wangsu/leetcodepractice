package io.wangsu.service;

import io.wangsu.domain.ListNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
