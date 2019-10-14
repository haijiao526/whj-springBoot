package com.whj.study;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author whj
 * @createtime 2017年2月12日 下午5:10:13
 * @todo 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={WhjTestApplication.class})
@WebAppConfiguration
public class BaseTest{

	protected final Logger log = LoggerFactory.getLogger(getClass());

}
