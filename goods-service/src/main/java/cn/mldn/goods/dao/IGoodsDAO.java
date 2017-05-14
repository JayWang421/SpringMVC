package cn.mldn.goods.dao;

import java.util.List;

import cn.mldn.goods.vo.Goods;
import cn.mldn.util.dao.IBaseDAO;

public interface IGoodsDAO extends IBaseDAO<Long, Goods> {
	public List<Goods> findAllByDelflag(Long currentPage,Long lineSize,Integer del)throws Exception ;
	public List<Goods> findAllByDelflag(Long currentPage,Long lineSize,String column,String keyWord,Integer del)throws Exception ;
	public Long getAllCountByDelflag(Integer del)throws Exception ;
	public Long getAllCountByDelflag(String column,String keyWord,Integer del)throws Exception ;
}
