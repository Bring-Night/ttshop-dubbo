package name.zhy.ttshop.item.service;

import name.zhy.ttshop.common.vo.EasyUITreeNode;

import java.util.List;

/**
 * Created by zhy on 2017/7/4 0004.
 */
public interface ItemCatService {

    List<EasyUITreeNode> getItemCatList(Long parentId);
}
