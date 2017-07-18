package name.zhy.ttshop.common.vo;

import java.io.Serializable;

/**
 * Created by zhy on 2017/7/3 0003.
 */
public class Order implements Serializable{
    
	private static final long serialVersionUID = 1L;
    //拍序列
    private String sort;
    //asc-正序-desc-倒叙
    private String order;

    public Order() {
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
