package ts.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "distributecenter")
@XmlRootElement(name = "distributecenter")
public class DistributeCenter implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2696910600791838998L;

	public DistributeCenter() {
	}

	@Column(name = "sn", nullable = false)
	@Id
	@GeneratedValue(generator = "MODEL_TRANSHISTORY_SN_GENERATOR")
	@org.hibernate.annotations.GenericGenerator(name = "MODEL_TRANSHISTORY_SN_GENERATOR", strategy = "native")
	private int sn;

	@Column(name = "expresssheetid", nullable = false)
	private String expresssheetid;

	@Column(name = "packageid", nullable = false)
	private String packageid;

	public int getSn() {
		return sn;
	}

	public void setPackageid(String value) {
		this.packageid = value;
	}

	public String getPackageid() {
		return packageid;
	}

	public void setExpressSheetID(String value) {
		this.expresssheetid = value;
	}

	public String getExpressSheetID() {
		return expresssheetid;
	}

	@Override
	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getSn());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("DistributeCenter[ ");
			sb.append("SN=").append(getSn()).append(" ");
			sb.append("ExpressSheetId=").append(getExpressSheetID())
					.append(" ");
			sb.append("PackageId=").append(getPackageid()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

}
