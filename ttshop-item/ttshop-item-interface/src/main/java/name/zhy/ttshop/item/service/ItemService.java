package name.zhy.ttshop.item.service;

import java.time.zone.ZoneOffsetTransitionRule.TimeDefinition;
import java.util.List;

import name.zhy.ttshop.common.vo.EasyUIDataGridResult;
import name.zhy.ttshop.common.vo.Order;
import name.zhy.ttshop.common.vo.Pager;
import name.zhy.ttshop.pojo.po.TbItem;
import name.zhy.ttshop.pojo.vo.MessageResult;
import name.zhy.ttshop.pojo.vo.TbItemQuery;

public interface ItemService {
    /**
     * 通过id查找商品
     * @param itemId
     * @return
     */
	public TbItem getItemById(Long itemId);

    /**
     * 查找所有商品
     * @return
     */
    public List<TbItem> getItemAll();

    /**
     * 分页查找商品信息+排序+查询条件
     * 返回扩展类
     * @param pager
     * @return
     */
    public EasyUIDataGridResult getItemByPage(Pager pager, Order order, TbItemQuery tbItemQuery);

    /**
     * 根据ids删除信息
     * @param ids
     * @return
     */
    public MessageResult deleteByIdList(List<Long> ids);

    /**
     * 上架
     * @param ids
     * @return
     */
    MessageResult upByIdList(List<Long> ids);

    /**
     * 下架
     * @param ids
     * @return
     */
    MessageResult downByIdList(List<Long> ids);

    /**
     * 新增商品
     * @param item
     * @return
     */
    MessageResult save(TbItem item,String desc);
}
