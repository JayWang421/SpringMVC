package cn.mldn.goods.dao;

import java.util.List;

import cn.mldn.goods.vo.Subitem;
import cn.mldn.util.dao.IBaseDAO;

public interface ISubitemDAO extends IBaseDAO<Long, Subitem> {
	public List<Subitem> findAllByItem(Long iid)throws Exception ;
}
