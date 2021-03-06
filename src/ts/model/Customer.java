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
@Table(name = "customer")
@XmlRootElement(name = "Customer")
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3267943602377867497L;

	public Customer() {
	}

	@Column(name = "id", nullable = false)
	@Id
	@GeneratedValue(generator = "MODEL_CUSTOMERINFO_ID_GENERATOR")
	@org.hibernate.annotations.GenericGenerator(name = "MODEL_CUSTOMERINFO_ID_GENERATOR", strategy = "native")
	private int id;

	@Column(name = "cname", nullable = true, length = 16)
	private String cname;

	@Column(name = "telcode", nullable = true, length = 24)
	private String telcode;

	@Column(name = "department", nullable = true, length = 64)
	private String department;

	@Column(name = "regioncode", nullable = true, length = 6)
	private String regioncode;

	@Column(name = "address", nullable = true, length = 64)
	private String address;

	@Column(name = "poscode", nullable = false, length = 10)
	private int poscode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getTelcode() {
		return telcode;
	}

	public void setTelcode(String telcode) {
		this.telcode = telcode;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getPoscode() {
		return poscode;
	}

	public void setPoscode(int poscode) {
		this.poscode = poscode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getId());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("Customer[ ");
			sb.append("ID=").append(getId()).append(" ");
			sb.append("Name=").append(getCname()).append(" ");
			sb.append("TelCode=").append(getTelcode()).append(" ");
			sb.append("Department=").append(getDepartment()).append(" ");
			sb.append("RegionCode=").append(getRegioncode()).append(" ");
			sb.append("Address=").append(getAddress()).append(" ");
			sb.append("PosCode=").append(getPoscode()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

	@Transient
	private String regionString;

	public void setRegionString(String value) {
		this.regionString = value;
	}

	public String getRegionString() {
		return regionString;
	}

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
