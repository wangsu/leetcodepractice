package io.wangsu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Wangs on 5/3/2017.
 */
@Service
public class LeetCodeHardService {
    private static final Logger log = LoggerFactory.getLogger(LeetCodeHardService.class);

    /**
     * 4. Median of Two Sorted Arrays
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        boolean isTotalEven = false;
        if((m+n)%2==0){
            isTotalEven = true;
        }
        int midPos = 0;
        int midFinalPos = (m+n)/2;
        int mPos = 0;
        int nPos = 0;
        int preV = 0;
        while(mPos<nums1.length||nPos<nums2.length){
            int curV = 0;
            if(mPos>=nums1.length&&nPos<nums2.length){
                curV = nums2[nPos++];
            }else if(mPos<nums1.length&&nPos>=nums2.length){
                curV = nums1[mPos++];
            }else{
                if(nums1[mPos]<nums2[nPos]){
                    curV = nums1[mPos++];
                }else{
                    curV = nums2[nPos++];
                }
            }

            if(midPos==midFinalPos){
                if(isTotalEven){
                    return ((double)preV+(double)curV)/2d;
                }else{
                    return (double)curV;
                }
            }

            preV = curV;
            midPos++;
        }
        return 0d;
    }

}
