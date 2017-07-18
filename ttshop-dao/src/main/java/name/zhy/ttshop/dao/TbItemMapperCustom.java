package name.zhy.ttshop.dao;

import java.util.List;
import java.util.Map;

import name.zhy.ttshop.pojo.vo.TbItemCustom;
import name.zhy.ttshop.pojo.vo.TbSearchItemCustom;

/**
 * Created by zhy on 2017/7/1 0001.
 */
public interface TbItemMapperCustom {

    /**
     * 分页+排序
     * @param pager
     * @param order
     * @return
     */
/*
    public List<TbItemCustom> selectByPage(
            @Param(value = "pager") Pager pager,
            @Param(value = "order") Order order);
*/
    public List<TbItemCustom> selectByPage(Map<String,Object> map);

    /**
     * 查找分页总条数
     * @return
     */
    public long selectCount(Map<String,Object> map);
    
    /**
     * 分词查询功能
     * @return
     */
    public List<TbSearchItemCustom> getSearchItemList();

}
