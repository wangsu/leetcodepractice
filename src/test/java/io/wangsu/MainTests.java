package io.wangsu;

import io.wangsu.service.LeetCodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by SuW on 12/19/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MyApplication.class})
@IntegrationTest
public class MainTests {
    private static final Logger log = LoggerFactory.getLogger(MainTests.class);

    @Inject
    LeetCodeService leetCodeService;

    @Test
    public void test461(){
        //log.info("result {}",leetCodeService.hammingDistance(1,4));
        log.info("result {}",leetCodeService.hammingDistance(1,4));
    }
}
