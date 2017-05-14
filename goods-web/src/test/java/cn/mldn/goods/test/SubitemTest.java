package cn.mldn.goods.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.goods.service.ISubitemService;

@ContextConfiguration(locations={"classpath:spring/spring-common.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SubitemTest {
	
	@Resource
	private ISubitemService subitemService ;
	
	@Test
	public void testAddPre(){
		try {
			System.out.println(this.subitemService.listByItem(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
