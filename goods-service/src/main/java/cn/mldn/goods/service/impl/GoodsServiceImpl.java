package cn.mldn.goods.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.mldn.goods.dao.IGoodsDAO;
import cn.mldn.goods.dao.IItemDAO;
import cn.mldn.goods.dao.ISubitemDAO;
import cn.mldn.goods.service.IGoodsService;
import cn.mldn.goods.service.abs.AbstractService;
import cn.mldn.goods.vo.Goods;

@Service
public class GoodsServiceImpl extends AbstractService implements IGoodsService {
	
	@Resource
	private IItemDAO itemDAO ;
	
	@Resource
	private IGoodsDAO goodsDAO ;
	
	@Resource
	private ISubitemDAO subitemDAO ;
	
	@Override
	public boolean add(Goods vo) throws Exception {
		vo.setDelflag(0);
		return this.goodsDAO.doCreate(vo);
	}
	
	@Override
	public Map<String, Object> addPre() throws Exception {
		Map<String ,Object> map=new HashMap<String,Object>() ;
		map.put("allItems", this.itemDAO.findAll()) ;
		return map;
	}
	
	@Override
	public Map<String, Object> list(long currentPage, long lineSize, String column, String keyWord) throws Exception {
		Map<String ,Object> map=new HashMap<>() ;
		map.put("allItems", this.itemDAO.findAll()) ;
		if(column==null || keyWord==null || "".equals(keyWord) || "".equals(column)){
			map.put("allGoodss", this.goodsDAO.findAllByDelflag(currentPage, lineSize, 0)) ;
			map.put("allRecorders", this.goodsDAO.getAllCountByDelflag(0)) ;
		}else{
			map.put("allGoodss", this.goodsDAO.findAllByDelflag(currentPage, lineSize, column, keyWord, 0)) ;
			map.put("allRecorders",this.goodsDAO.getAllCountByDelflag(column, keyWord, 0)) ;
		}
		return map;
	}
	
	@Override
	public Map<String, Object> editPre(Long gid) throws Exception {
		Map<String ,Object> map=new HashMap<>() ;
		map.put("allItems",this.itemDAO.findAll()) ;
		Goods goods=this.goodsDAO.findById(gid) ;
		map.put("goods", this.goodsDAO.findById(gid)) ;
		map.put("allSubitems",this.subitemDAO.findAllByItem(goods.getIid())) ;
		return map;
	}
	
	@Override
	public boolean edit(Goods vo) throws Exception {
		return this.goodsDAO.doUpdate(vo);
	}
	
	@Override
	public boolean deleteByIds(Set<Long> ids) throws Exception {
		if (ids == null || ids.size() == 0) {
			return false ;
		}
		return this.goodsDAO.doRemoveBatch(ids);
	}
}
