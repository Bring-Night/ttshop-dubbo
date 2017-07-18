package name.zhy.ttshop.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import name.zhy.ttshop.common.vo.EasyUITreeNode;
import name.zhy.ttshop.item.service.ItemCatService;

/**
 * Created by zhy on 2017/7/4 0004.
 */
@Controller
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping("/item/cat/list")
    @ResponseBody
    public List<EasyUITreeNode> getItemCatList(@RequestParam(name = "id",defaultValue = "0") Long parentId){

        return itemCatService.getItemCatList(parentId);
    }
}
