package name.zhy.ttshop.content.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import name.zhy.ttshop.common.utils.JsonUtils;
import name.zhy.ttshop.common.utils.jedis.JedisClient;
import name.zhy.ttshop.common.vo.EasyUIDataGridResult;
import name.zhy.ttshop.common.vo.Pager;
import name.zhy.ttshop.content.service.ContentService;
import name.zhy.ttshop.dao.TbContentMapper;
import name.zhy.ttshop.dao.TbContentMapperCustom;
import name.zhy.ttshop.pojo.po.TbContent;
import name.zhy.ttshop.pojo.po.TbContentExample;
import name.zhy.ttshop.pojo.po.TbContentExample.Criteria;
import name.zhy.ttshop.pojo.vo.MessageResult;

/**
 * Created by zhy on 2017/7/11 0011.
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	@Autowired
	private TbContentMapperCustom contentMapperCustom;
	@Autowired
	private JedisClient jedisClient;

	@Override
	public EasyUIDataGridResult getContentListByCategoryId(Long categoryId, Pager pager) {
		// 计算offset
		pager.setPagerParams();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pager", pager);
		map.put("categoryId", categoryId);
		List<TbContent> rows = contentMapperCustom.selectByPage(map);

		long total = contentMapperCustom.selectCount(map);

		// 设置返回参数
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(rows);
		result.setTotal(total);
		return result;
	}

	@Override
	public MessageResult deleteByIdList(List<Long> ids) {
		TbContentExample example = new TbContentExample();
		TbContentExample.Criteria criteria = example.createCriteria();
		// 设置查询条件
		criteria.andIdIn(ids);
		// 执行删除
		TbContent record = new TbContent();
		record.setStatus((byte) 3);
		int count = contentMapper.updateByExampleSelective(record, example);

		MessageResult ms = new MessageResult();
		ms.setData(count);
		ms.setSuccess(true);
		ms.setMessage("删除了" + count + "条记录");
		return ms;
	}

	@Override
	public MessageResult save(TbContent tbContent) {
		// 状态
		tbContent.setStatus((byte) 1);
		// 时间
		Date date = new Date();
		tbContent.setCreated(date);
		tbContent.setUpdated(date);
		// 执行商品插入
		contentMapper.insert(tbContent);

		// 缓存同步,删除缓存中对应的数据。
		jedisClient.hdel("CONTENT_LIST", tbContent.getCategoryId().toString());

		MessageResult ms = new MessageResult();
		ms.setSuccess(true);
		ms.setMessage("添加成功");
		ms.setData(1);
		ms.setCode(0);

		return ms;
	}

	@Override
	public List<TbContent> getContentListByCategoryId(Long categoryId) {

		// 查询缓存
		try {
			// 如果缓存中有直接响应结果
			String json = jedisClient.hget("CONTENT_LIST", categoryId + "");
			if (StringUtils.isNotBlank(json)) {
				List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果没有查询数据库

		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		// 设置查询条件
		criteria.andCategoryIdEqualTo(categoryId);
		criteria.andStatusEqualTo((byte) 1);
		// 执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		
		 //把结果添加到缓存
        try {
            jedisClient.hset("CONTENT_LIST", categoryId + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
		return list;
	}
}
