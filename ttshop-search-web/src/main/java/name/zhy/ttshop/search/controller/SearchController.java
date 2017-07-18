package name.zhy.ttshop.search.controller;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import name.zhy.ttshop.pojo.vo.MessageResult;
import name.zhy.ttshop.search.service.SearchItemService;

@Controller
public class SearchController {
	
	/*@Autowired
	private SearchItemService searchItemService;
	
	@RequestMapping("/")
	public String showSearchPage(
			String keyword,
			@RequestParam(defaultValue="1") Integer page,
			Model model
			) throws SolrServerException, IOException{
		if (keyword != null) {
			keyword = new String(keyword.getBytes("iso-8859-1"),"utf-8");
			TbSearchItemResult result =  searchItemService.search(keyword,page,60);
			
			model.addAttribute("query", keyword);
			model.addAttribute("totalPages", result);//todo
			model.addAttribute("query", keyword);//todo
			model.addAttribute("query", keyword);//todo
			model.addAttribute("query", keyword);//todo
		}
		
		return "search";
	}
*/
}
