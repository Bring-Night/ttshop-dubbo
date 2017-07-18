package name.zhy.ttshop.common.vo;

import java.io.Serializable;

/**
 * Created by zhy on 2017/7/4 0004.
 */
public class EasyUITreeNode implements Serializable{
    
	private static final long serialVersionUID = 1L;
    private Long id;
    private String text;
    private String state;//open-closed

    public EasyUITreeNode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
