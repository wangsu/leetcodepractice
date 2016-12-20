package io.wangsu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SuW on 12/19/2016.
 */
@Service
public class LeetCodeService {
    private static final Logger log = LoggerFactory.getLogger(LeetCodeService.class);

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
}
