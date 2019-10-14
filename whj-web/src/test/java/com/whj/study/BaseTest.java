package com.whj.study;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author whj
 * @createtime 2017年2月12日 下午5:10:13
 * @todo 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={WhjWebApplication.class})
@WebAppConfiguration
@AutoConfigureMockMvc(print= MockMvcPrint.LOG_DEBUG)
@Transactional
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests{

	protected final Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 将object转换成json字符串
	 * @param object
	 * @return
	 */
	protected String toJSONString(Object object){
		return JSON.toJSONString(object,SerializerFeature.PrettyFormat);
	}
}
