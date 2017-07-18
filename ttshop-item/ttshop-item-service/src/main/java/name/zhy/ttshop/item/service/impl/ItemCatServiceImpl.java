package name.zhy.ttshop.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import name.zhy.ttshop.common.vo.EasyUITreeNode;
import name.zhy.ttshop.dao.TbItemCatMapper;
import name.zhy.ttshop.item.service.ItemCatService;
import name.zhy.ttshop.pojo.po.TbItemCat;
import name.zhy.ttshop.pojo.po.TbItemCatExample;

/**
 * Created by zhy on 2017/7/4 0004.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	@Autowired
	TbItemCatMapper tbItemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		TbItemCatExample.Criteria criteria = example.createCriteria();
		// 设置查询条件
		criteria.andParentIdEqualTo(parentId);
		// 执行查询
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);

		// 创建返回结果
		List<EasyUITreeNode> nodes = new ArrayList<>();
		for (TbItemCat itemCat : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(itemCat.getId());
			node.setText(itemCat.getName());
			node.setState(itemCat.getIsParent() ? "closed" : "open");
			nodes.add(node);
		}
		return nodes;
	}
}
