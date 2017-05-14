package cn.mldn.goods.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.mldn.goods.service.IGoodsService;

@ContextConfiguration(locations={"classpath:spring/spring-common.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class GoodsTest {
	
	@Resource 
	private IGoodsService goodsService ;
	
	@Test
	public void testAddPre(){
		try {
			System.out.println(this.goodsService.addPre());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testListFindByDelflag(){
		try {
			System.out.println(this.goodsService.list(2, 5, null, null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
