package name.zhy.ttshop.item.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import name.zhy.ttshop.common.utils.IDUtils;
import name.zhy.ttshop.common.vo.EasyUIDataGridResult;
import name.zhy.ttshop.common.vo.Order;
import name.zhy.ttshop.common.vo.Pager;
import name.zhy.ttshop.dao.TbItemDescMapper;
import name.zhy.ttshop.dao.TbItemMapper;
import name.zhy.ttshop.dao.TbItemMapperCustom;
import name.zhy.ttshop.item.service.ItemService;
import name.zhy.ttshop.pojo.po.TbItem;
import name.zhy.ttshop.pojo.po.TbItemDesc;
import name.zhy.ttshop.pojo.po.TbItemExample;
import name.zhy.ttshop.pojo.vo.MessageResult;
import name.zhy.ttshop.pojo.vo.TbItemCustom;
import name.zhy.ttshop.pojo.vo.TbItemQuery;

/**
 * 商品服务实现类
 */
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemMapperCustom itemMapperCustom;
	@Autowired
	private TbItemDescMapper itemDescMapper;

	/**
	 * 根据id查找商品
	 * @param itemId
	 * @return
	 */
	@Override
	public TbItem getItemById(Long itemId) {
		return itemMapper.selectByPrimaryKey(itemId);
	}

	/**
	 * 查找所有商品
	 * @return
	 */
	@Override
	public List<TbItem> getItemAll() {
		return itemMapper.selectByExample(null);
	}

	/**
	 * 分页+排序
	 * @param pager
	 * @param order
	 * @return
	 */
	@Override
	public EasyUIDataGridResult getItemByPage(Pager pager, Order order, TbItemQuery tbItemQuery) {
		//计算offset
		pager.setPagerParams();
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put("pager",pager);
		map.put("order",order);
		map.put("tbItemQuery",tbItemQuery);
		List<TbItemCustom> rows = itemMapperCustom.selectByPage(map);

		long total = itemMapperCustom.selectCount(map);

		//设置返回参数
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(rows);
		result.setTotal(total);
		return result;
	}

	/**
	 * 根据ids逻辑删除数据
	 * @param ids
	 * @return
	 */
	@Override
	public MessageResult deleteByIdList(List<Long> ids) {
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andIdIn(ids);
		//设置需要更新的字段

		TbItem record = new TbItem();
		record.setStatus((byte) 3);
		//执行更新,返回影响条数
        int count = itemMapper.updateByExampleSelective(record, example);

        //设置返回值
        MessageResult ms = new MessageResult();
        ms.setSuccess(true);
        ms.setMessage("删除成功");
        ms.setCode(0);
        ms.setData(count);

        System.out.println(ms.isSuccess());
        return ms;
    }

	/**
	 * 修改状态码上架设为1
	 * @param ids
	 * @return
	 */
	@Override
	public MessageResult upByIdList(List<Long> ids) {
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andIdIn(ids);
		//设置需要更新的字段

		TbItem record = new TbItem();
		record.setStatus((byte) 1);
		//执行更新,返回影响条数
		int count = itemMapper.updateByExampleSelective(record, example);

		//设置返回值
		MessageResult ms = new MessageResult();
		ms.setSuccess(true);
		ms.setMessage("上架成功");
		ms.setCode(0);
		ms.setData(count);

		System.out.println(ms.isSuccess());
		return ms;
	}

	/**
	 * 修改状态下架设为2
	 * @param ids
	 * @return
	 */
	@Override
	public MessageResult downByIdList(List<Long> ids) {
		TbItemExample example = new TbItemExample();
		TbItemExample.Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andIdIn(ids);
		//设置需要更新的字段

		TbItem record = new TbItem();
		record.setStatus((byte) 2);
		//执行更新,返回影响条数
		int count = itemMapper.updateByExampleSelective(record, example);

		//设置返回值
		MessageResult ms = new MessageResult();
		ms.setSuccess(true);
		ms.setMessage("下架成功");
		ms.setCode(0);
		ms.setData(count);

		System.out.println(ms.isSuccess());
		return ms;
	}

	@Override
	public MessageResult save(TbItem item,String desc) {
	    //UUID转换long的ItemID
        long itemId = IDUtils.genItemId();

        item.setId(itemId);
        //状态,商品状态
        item.setStatus((byte) 1);
        //设置时间
        Date now = new Date();
        item.setCreated(now);
        item.setUpdated(now);
        //执行插入
        itemMapper.insert(item);

        //插入商品详情
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(now);
		itemDesc.setUpdated(now);
		//执行插入保存
		itemDescMapper.insert(itemDesc);

		//设置前段返回值
        MessageResult mr = new MessageResult();
        mr.setCode(0);
        mr.setSuccess(true);
        mr.setMessage("商品添加成功");
        mr.setData(1);
        return mr;
	}

}
