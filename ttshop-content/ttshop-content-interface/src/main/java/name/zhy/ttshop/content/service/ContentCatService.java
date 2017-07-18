package name.zhy.ttshop.content.service;

import name.zhy.ttshop.common.vo.EasyUITreeNode;
import name.zhy.ttshop.pojo.vo.MessageResult;

import java.util.List;

/**
 * Created by zhy on 2017/7/7 0007.
 */
public interface ContentCatService {
    /**
     * 查询树结构
     * @param parentId
     * @return
     */
    public List<EasyUITreeNode> getContentCatList(Long parentId);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    public MessageResult delById(Long id);

    /**
     * 添加类目
     * @param parentId
     * @param name
     * @return
     */
    public MessageResult create(Long parentId, String name);

    /**
     * 修改类目
     * @param id
     * @param name
     * @return
     */
    public MessageResult updateById(Long id, String name);
}
