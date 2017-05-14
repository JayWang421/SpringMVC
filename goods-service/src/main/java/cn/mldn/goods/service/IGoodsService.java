package cn.mldn.goods.service;

import java.util.Map;
import java.util.Set;

import cn.mldn.goods.vo.Goods;

public interface IGoodsService {
	public boolean add(Goods vo)throws Exception ;
	public Map<String ,Object> addPre()throws Exception ;
	public Map<String ,Object> list(long currentPage,long lineSize,String column,String keyWord) throws Exception ;
	public Map<String ,Object> editPre(Long gid)throws Exception ;
	public boolean edit(Goods vo)throws Exception ;
	public boolean deleteByIds(Set<Long> ids)throws Exception ;
}
