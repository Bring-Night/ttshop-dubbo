package name.zhy.ttshop.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import name.zhy.ttshop.common.vo.EasyUITreeNode;
import name.zhy.ttshop.content.service.ContentCatService;
import name.zhy.ttshop.pojo.vo.MessageResult;

/**
 * Created by zhy on 2017/7/7 0007.
 */
@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCatService contentCatService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") Long parentId) {

        return contentCatService.getContentCatList(parentId);
    }

    /**
     * 添加类目
     * @param parentId
     * @param name
     * @return
     */
    @RequestMapping("/content/category/create")
    @ResponseBody
    public MessageResult create(Long parentId,String name) {

        return contentCatService.create(parentId,name);
    }

    /**
     * 修改类目
     * @param id
     * @param name
     * @return
     */
    @RequestMapping("/content/category/update")
    @ResponseBody
    public MessageResult update(Long id,String name) {

        return contentCatService.updateById(id,name);
    }

    /**
     * 删除类目
     * @param id
     * @return
     */
    @RequestMapping("/content/category/del")
    @ResponseBody
    public MessageResult deleteById(Long id) {

        return contentCatService.delById(id);
    }


}
