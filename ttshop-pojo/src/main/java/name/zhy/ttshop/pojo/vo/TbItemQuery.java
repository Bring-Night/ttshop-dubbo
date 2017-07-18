package name.zhy.ttshop.pojo.vo;

import java.io.Serializable;

/**
 * Created by zhy on 2017/7/4 0004.
 */
public class TbItemQuery implements Serializable{
    
	private static final long serialVersionUID = 1L;
    private String title;
    private Byte status;

    public TbItemQuery() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
