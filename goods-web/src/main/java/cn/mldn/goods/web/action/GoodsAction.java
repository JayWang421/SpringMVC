package cn.mldn.goods.web.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.goods.service.IGoodsService;
import cn.mldn.goods.util.action.ActionSplitPageUtil;
import cn.mldn.goods.vo.Goods;
import cn.mldn.goods.vo.Item;
import cn.mldn.goods.web.action.abs.AbstractBaseAction;
import cn.mldn.util.web.FileUtils;

@Controller
@RequestMapping("/pages/back/admin/goods/*")
public class GoodsAction extends AbstractBaseAction {
	private static final String FLAG = "商品" ;
	@Resource
	private IGoodsService goodsService;
	@RequestMapping("add")
	public String add(Goods vo, MultipartFile pic, HttpServletRequest request)
			throws Exception {
		FileUtils fu = null;
		if (!(pic == null || pic.isEmpty())) { // 图片不为空
			fu = new FileUtils(pic);
			vo.setPhoto(fu.createFileName()); // 保存图片名称
		}
		if (this.goodsService.add(vo)) {
			if (!(pic == null || pic.isEmpty())) {
				fu.saveFile(request, "upload/goods/", vo.getPhoto());
			}
			super.setUrlAndMsg(request, "goods.add.action", "vo.add.success", FLAG);
		} else { 
			super.setUrlAndMsg(request, "goods.add.action", "vo.add.failure", FLAG);
		}
		return super.getMsg("forward.page");
	}
	@RequestMapping("add_pre")
	public ModelAndView addPre() throws Exception {
		ModelAndView mav = new ModelAndView(super.getMsg("goods.add.page"));
		mav.addAllObjects(this.goodsService.addPre());
		return mav;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mav=new ModelAndView(super.getMsg("goods.list.page")) ;
		ActionSplitPageUtil aspu=new ActionSplitPageUtil(request, null, super.getMsg("goods.list.action")) ;
		try {
			Map<String ,Object> map=this.goodsService.list(aspu.getCurrentPage(), aspu.getLineSize(), "title", aspu.getKeyWord()) ;
			mav.addAllObjects(map) ;
			List<Item> allItems=(List<Item>) map.get("allItems") ;
			Iterator<Item> iter=allItems.iterator() ;
			Map<Long,Object> itemMap=new HashMap<>() ;
			while(iter.hasNext()){
				Item item=iter.next() ;
				itemMap.put(item.getIid(), item.getTitle()) ;
			}
			mav.addObject("allItems",itemMap) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	@RequestMapping("edit_pre")
	public ModelAndView editPre(long gid){
		ModelAndView mav=new ModelAndView(super.getMsg("goods.edit.page")) ;
		try {
			Map<String ,Object> map=this.goodsService.editPre(gid) ;
			mav.addAllObjects(map) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav ;
	}
	
	@RequestMapping("edit")
	public String edit(Goods vo,MultipartFile pic,HttpServletRequest request)throws Exception{
		FileUtils fu = null;
		if (!(pic == null || pic.isEmpty())) { // 图片不为空
			fu = new FileUtils(pic);
		}
		if (this.goodsService.edit(vo)) {
			if (!(pic == null || pic.isEmpty())) {
				fu.saveFile(request, "upload/goods/", vo.getPhoto());
			}
			super.setUrlAndMsg(request, "goods.list.action", "vo.edit.success", FLAG);
		} else { 
			super.setUrlAndMsg(request, "goods.list.action", "vo.edit.failure", FLAG);
		}
		return super.getMsg("forward.page");
	}
	
	@RequestMapping("delete")
	public String delete(String ids,HttpServletRequest request)throws Exception{
		Set<Long> gids=new HashSet<>() ;
		String result[]=ids.split(",") ;
		for(int x=0 ; x<result.length ; x++){
			gids.add(Long.parseLong(result[x])) ;
		}
		if(this.goodsService.deleteByIds(gids)){
			super.setUrlAndMsg(request, "goods.list.action", "vo.delete.success", FLAG);
		}else{
			super.setUrlAndMsg(request, "goods.list.action", "vo.delete.failure", FLAG);
		}
		return super.getMsg("forward.page") ;
	}
}
