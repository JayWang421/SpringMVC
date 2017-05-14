package cn.mldn.goods.dao.abs;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

public abstract class AbstractDAO {
	@Resource
	protected JdbcTemplate jdbcTemplate ;
}	
