package io.wangsu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by SuW on 12/19/2016.
 */
@Service
public class LeetCodeService {
    private static final Logger log = LoggerFactory.getLogger(LeetCodeService.class);

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
