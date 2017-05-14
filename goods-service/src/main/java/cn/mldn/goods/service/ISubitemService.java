package cn.mldn.goods.service;

import java.util.List;

import cn.mldn.goods.vo.Subitem;

public interface ISubitemService {
	public List<Subitem> listByItem(long iid)throws Exception ;
}
