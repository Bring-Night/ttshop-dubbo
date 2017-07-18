package name.zhy.ttshop.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import name.zhy.ttshop.common.vo.EasyUIDataGridResult;
import name.zhy.ttshop.common.vo.Pager;
import name.zhy.ttshop.content.service.ContentService;
import name.zhy.ttshop.pojo.po.TbContent;
import name.zhy.ttshop.pojo.vo.MessageResult;

/**
 * Created by zhy on 2017/7/10 0010.
 */
@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    /**
     * 广告内容管理
     * @param categoryId
     * @param pager
     * @return
     */
    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentListByCategoryId(
            Long categoryId,
            Pager pager){

        return contentService.getContentListByCategoryId(categoryId,pager);
    }

    /**
     * 删除广告内容
     * @param ids
     * @return
     */
    @RequestMapping("/content/del")
    @ResponseBody
    public MessageResult delete(@RequestParam("ids") List<Long> ids){
        MessageResult ms = contentService.deleteByIdList(ids);
        return ms;
    }

    /**
     * 添加内容
     * @param tbContent
     * @return
     */
    @RequestMapping("/content/save")
    @ResponseBody
    public MessageResult save(TbContent tbContent){
        return contentService.save(tbContent);
    }


}
