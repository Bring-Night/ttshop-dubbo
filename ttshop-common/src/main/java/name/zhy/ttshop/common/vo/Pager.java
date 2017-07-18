package name.zhy.ttshop.common.vo;

import java.io.Serializable;

/**
 * Created by zhy on 2017/7/1 0001.
 */
public class Pager implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private Integer page;//当前页码
    private Integer rows;//每页记录条数
    private Integer offset;//偏移量


    public void setPagerParams(){
        this.offset = (this.page - 1) * this.rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
