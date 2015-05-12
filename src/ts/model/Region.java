package ts.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "region")
@XmlRootElement(name = "region")
public class Region implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2531774702426085013L;

	public Region() {
	}

	@Column(name = "regioncode", nullable = false)
	@Id
	@GeneratedValue(generator = "MODEL_REGION_REGIONCODE_GENERATOR")
	@org.hibernate.annotations.GenericGenerator(name = "MODEL_REGION_REGIONCODE_GENERATOR", strategy = "native")
	private String regioncode;

	@Column(name = "province", nullable = true, length = 32)
	private String province;

	@Column(name = "city", nullable = true, length = 32)
	private String city;

	@Column(name = "town", nullable = true, length = 32)
	private String town;

	@Column(name = "stage", nullable = false, length = 4)
	private int stage;

	public void setRegioncode(String value) {
		this.regioncode = value;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public String getORMId() {
		return getRegioncode();
	}

	public void setProvince(String value) {
		this.province = value;
	}

	public String getProvince() {
		return province;
	}

	public void setCity(String value) {
		this.city = value;
	}

	public String getCity() {
		return city;
	}

	public void setTown(String value) {
		this.town = value;
	}

	public String getTown() {
		return town;
	}

	public void setStage(int value) {
		this.stage = value;
	}

	public int getStage() {
		return stage;
	}

	@Override
	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getRegioncode());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("Region[ ");
			sb.append("RegionCode=").append(getRegioncode()).append(" ");
			sb.append("Prv=").append(getProvince()).append(" ");
			sb.append("Cty=").append(getCity()).append(" ");
			sb.append("Twn=").append(getTown()).append(" ");
			sb.append("Stage=").append(getStage()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

	// @Transient表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性.
	@Transient
	private boolean _saved = false;

	public void onSave() {
		_saved = true;
	}

	public void onLoad() {
		_saved = true;
	}

	public boolean isSaved() {
		return _saved;
	}

}
