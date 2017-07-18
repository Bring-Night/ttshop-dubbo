package name.zhy.ttshop.dao;

import name.zhy.ttshop.pojo.po.TbContent;

import java.util.List;
import java.util.Map;

/**
 * Created by zhy on 2017/7/11 0011.
 */
public interface TbContentMapperCustom {
    List<TbContent> selectByPage(Map<String, Object> map);

    long selectCount(Map<String, Object> map);
}
