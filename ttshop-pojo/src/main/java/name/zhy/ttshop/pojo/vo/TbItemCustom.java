package name.zhy.ttshop.pojo.vo;

import java.io.Serializable;

import name.zhy.ttshop.pojo.po.TbItem;

/**
 * Created by zhy on 2017/7/3 0003.
 */
public class TbItemCustom extends TbItem implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String catName;
    private String statusName;

    public TbItemCustom() {
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
