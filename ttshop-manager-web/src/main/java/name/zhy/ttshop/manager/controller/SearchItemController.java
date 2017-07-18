package name.zhy.ttshop.manager.controller;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import name.zhy.ttshop.pojo.vo.MessageResult;
import name.zhy.ttshop.search.service.SearchItemService;

@Controller
public class SearchItemController {
	@Autowired
	private SearchItemService searchItemService; 
	
	@RequestMapping("/search/item/import")
	@ResponseBody
	public MessageResult importItemList() throws SolrServerException, IOException{
		return searchItemService.importAllItems();
	}

}
