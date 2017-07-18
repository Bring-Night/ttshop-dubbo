package name.zhy.ttshop.manager.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import name.zhy.ttshop.common.vo.EasyUIDataGridResult;
import name.zhy.ttshop.common.vo.Order;
import name.zhy.ttshop.common.vo.Pager;
import name.zhy.ttshop.item.service.ItemService;
import name.zhy.ttshop.pojo.po.TbItem;
import name.zhy.ttshop.pojo.vo.MessageResult;
import name.zhy.ttshop.pojo.vo.TbItemQuery;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;

	/**
	 * 通过id查询商品信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId){

		return itemService.getItemById(itemId);
	}

	/**
	 * 展示所有商品信息
	 * @return
	 */
	@RequestMapping("/item/list-all")
	@ResponseBody
	public List<TbItem> getItemAll(){

		return itemService.getItemAll();
	}

	/**
	 * 分页+排序
	 * @param pager
	 * @param order
	 * @return
	 */
	@RequestMapping("/item/list-page")
	@ResponseBody
	public EasyUIDataGridResult getItemByPage(Pager pager, Order order, TbItemQuery tbItemQuery ){

		return itemService.getItemByPage(pager,order,tbItemQuery);
	}

	/**
	 * 各种删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/item/delete")
	@ResponseBody
	public MessageResult delete(@RequestParam("ids") List<Long> ids){
		MessageResult ms = itemService.deleteByIdList(ids);
		return ms;
	}
	/**
	 * 上架
	 * @param ids
	 * @return
	 */
	@RequestMapping("/item/up")
	@ResponseBody
	public MessageResult up(@RequestParam("ids") List<Long> ids){
		MessageResult ms = itemService.upByIdList(ids);
		return ms;
	}
	/**
	 * 下架
	 * @param ids
	 * @return
	 */
	@RequestMapping("/item/down")
	@ResponseBody
	public MessageResult down(@RequestParam("ids") List<Long> ids){
		MessageResult ms = itemService.downByIdList(ids);
		return ms;
	}

	/**
	 * 添加商品
	 * @param item
	 * @return
	 */
	@RequestMapping("/item/save")
	@ResponseBody
	public MessageResult save(TbItem item,String desc){
		return itemService.save(item,desc);
	}

}
