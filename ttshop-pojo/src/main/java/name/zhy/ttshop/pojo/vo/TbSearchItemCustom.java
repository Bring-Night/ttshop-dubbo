package name.zhy.ttshop.pojo.vo;

import java.io.Serializable;

public class TbSearchItemCustom implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;// solr中的id是字符串格式
	private String title;
	private String sellPoint;
	private long price;
	private String image;
	private String catName;

	public TbSearchItemCustom() {
		super();
	}

	// getters、setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

}
