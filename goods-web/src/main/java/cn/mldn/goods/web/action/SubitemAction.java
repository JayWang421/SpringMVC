package cn.mldn.goods.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.mldn.goods.service.ISubitemService;
import cn.mldn.goods.web.action.abs.AbstractBaseAction;
import net.sf.json.JSONObject;
@Controller
@RequestMapping("/pages/back/admin/subitem/*")
public class SubitemAction extends AbstractBaseAction{
	
	@Resource
	private ISubitemService subitemService ;
	
	@RequestMapping("list_subitem")
	public ModelAndView addPre(long iid,HttpServletResponse response){
		JSONObject obj=new JSONObject() ;
		try {
			obj.put("allSubitems", this.subitemService.listByItem(iid)) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.print(response, obj);
		return null ;
	}
	
}
