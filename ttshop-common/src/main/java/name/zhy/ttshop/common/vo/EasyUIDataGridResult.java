package name.zhy.ttshop.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhy on 2017/7/1 0001.
 */
public class EasyUIDataGridResult implements Serializable{
    
	private static final long serialVersionUID = 1L;
    private long total;
    private List<?> rows;

    public EasyUIDataGridResult() {
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
