package name.zhy.ttshop.search.service.impl;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import name.zhy.ttshop.dao.TbItemMapperCustom;
import name.zhy.ttshop.pojo.vo.MessageResult;
import name.zhy.ttshop.pojo.vo.TbSearchItemCustom;
import name.zhy.ttshop.search.service.SearchItemService;

@Service
public class SearchItemServiceImpl implements SearchItemService {

	@Autowired
	private SolrServer solrServer;

	@Autowired
	private TbItemMapperCustom itemMapperCustom;

	@Override
	public MessageResult importAllItems() throws SolrServerException, IOException {
		List<TbSearchItemCustom> searchItemList = itemMapperCustom.getSearchItemList();
		for (TbSearchItemCustom searchItem : searchItemList) {
			// 创建文档对象
			SolrInputDocument document = new SolrInputDocument();
			// 向文档对象中添加域：对应schema.xml配置文件中的域名
			document.addField("id", searchItem.getId());
			document.addField("item_title", searchItem.getTitle());
			document.addField("item_sell_point", searchItem.getSellPoint());
			document.addField("item_price", searchItem.getPrice());
			document.addField("item_image", searchItem.getImage());
			document.addField("item_category_name", searchItem.getCatName());
			// 把文档对象写入索引库
			solrServer.add(document);
		}
		solrServer.commit();
		MessageResult mr = new MessageResult();
		mr.setCode(0);
		mr.setData(null);
		mr.setMessage("导入成功");
		mr.setSuccess(true);
		
		return mr;

	}

}
