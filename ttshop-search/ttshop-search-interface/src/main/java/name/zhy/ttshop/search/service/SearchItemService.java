package name.zhy.ttshop.search.service;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;

import name.zhy.ttshop.pojo.vo.MessageResult;

public interface SearchItemService {
	public MessageResult importAllItems() throws SolrServerException, IOException  ;

}
