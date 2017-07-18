package name.zhy.ttshop.content.service;

import name.zhy.ttshop.common.vo.EasyUIDataGridResult;
import name.zhy.ttshop.common.vo.Pager;
import name.zhy.ttshop.pojo.po.TbContent;
import name.zhy.ttshop.pojo.vo.MessageResult;

import java.util.List;

/**
 * Created by zhy on 2017/7/10 0010.
 */
public interface ContentService {
	/**
	 * 广告内容列表显示+分页
	 * @param categoryId
	 * @param pager
	 * @return
	 */
    EasyUIDataGridResult getContentListByCategoryId(Long categoryId, Pager pager);

    /**
     * 广告内容删除
     * @param ids
     * @return
     */
    MessageResult deleteByIdList(List<Long> ids);

    /**
     * 广告内容添加
     * @param tbContent
     * @return
     */
    MessageResult save(TbContent tbContent);

    /**
     * 前台轮播广告展示
     * @param categoryId
     * @return
     */
	List<TbContent> getContentListByCategoryId(Long categoryId);
}
