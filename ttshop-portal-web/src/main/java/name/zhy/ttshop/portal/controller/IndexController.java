package name.zhy.ttshop.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import name.zhy.ttshop.content.service.ContentService;
import name.zhy.ttshop.pojo.po.TbContent;

@Controller
public class IndexController {
	
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/")
	public String index(Model model){
		List<TbContent> adList = contentService.getContentListByCategoryId(89L);
		System.out.println(adList);
		model.addAttribute("adList", adList);
		return "index";
	}

}
