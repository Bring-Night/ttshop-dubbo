package name.zhy.ttshop.content.service.impl;

import name.zhy.ttshop.common.vo.EasyUITreeNode;
import name.zhy.ttshop.content.service.ContentCatService;
import name.zhy.ttshop.dao.TbContentCategoryMapper;
import name.zhy.ttshop.dao.TbContentCategoryMapperCustom;
import name.zhy.ttshop.pojo.po.TbContentCategory;
import name.zhy.ttshop.pojo.po.TbContentCategoryExample;
import name.zhy.ttshop.pojo.vo.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhy on 2017/7/7 0007.
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Autowired
    private TbContentCategoryMapperCustom tbContentCategoryMapperCustom;

    @Override
    public List<EasyUITreeNode> getContentCatList(Long parentId) {

        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);

        //创建返回结果
        List<EasyUITreeNode> nodes = new ArrayList<>();
        for (TbContentCategory tbContentCategory : list) {
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public MessageResult delById(Long id) {
        MessageResult messageResult = new MessageResult();

        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();

        criteria.andParentIdEqualTo(id);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(example);
        if (list!=null&&list.size()>0){
            messageResult.setSuccess(false);
            messageResult.setData(0);
            messageResult.setCode(-1);//有子节点
            messageResult.setMessage("所选类别有子类别,请先删除子类别");

            return messageResult;
        }
        
        
        Long parentId = tbContentCategoryMapper.selectByPrimaryKey(id).getParentId();
        tbContentCategoryMapper.deleteByPrimaryKey(id);
        
        TbContentCategoryExample exampled = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteriad = exampled.createCriteria();

        criteriad.andParentIdEqualTo(parentId);
        List<TbContentCategory> listd = tbContentCategoryMapper.selectByExample(exampled);
        if (listd==null||listd.size()<1){
        	TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        	category.setIsParent(false);
        	
        	tbContentCategoryMapper.updateByPrimaryKey(category);
        }
        
       

        messageResult.setSuccess(true);
        messageResult.setData(1);
        messageResult.setCode(0);//有子节点
        messageResult.setMessage("删除成功");


        return messageResult;
    }

    @Override
    public MessageResult create(Long parentId, String name) {

        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);

        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);

        Date now = new Date();
        contentCategory.setCreated(now);
        contentCategory.setUpdated(now);

        contentCategory.setIsParent(false);

        int count = tbContentCategoryMapperCustom.insert(contentCategory);

        //修改父节点的isParent属性
        TbContentCategory contentCategoryParent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if (!contentCategoryParent.getIsParent()){
            contentCategoryParent.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(contentCategoryParent);

        }

        MessageResult messageResult = new MessageResult();

        messageResult.setSuccess(true);
        messageResult.setData(contentCategory);
        messageResult.setCode(0);//有子节点
        messageResult.setMessage("添加成功");


        return messageResult;
    }

    @Override
    public MessageResult updateById(Long id, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        Date now = new Date();
        contentCategory.setId(id);
        contentCategory.setName(name);
        contentCategory.setUpdated(now);
        tbContentCategoryMapper.updateByPrimaryKeySelective(contentCategory);

        MessageResult messageResult = new MessageResult();

        messageResult.setSuccess(true);
        messageResult.setData(contentCategory);
        messageResult.setCode(0);//有子节点
        messageResult.setMessage("修改成功");

        return messageResult;
    }
}
