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
@Table(name = "user")
@XmlRootElement(name = "user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6899152987896840262L;

	public User() {
	}

	@Column(name = "id", nullable = false)
	@Id
	@GeneratedValue(generator = "MODEL_USERINFO_UID_GENERATOR")
	@org.hibernate.annotations.GenericGenerator(name = "MODEL_USERINFO_UID_GENERATOR", strategy = "native")
	private int id;

	@Column(name = "password", nullable = true, length = 8)
	private String password;

	@Column(name = "uname", nullable = true, length = 16)
	private String uname;

	@Column(name = "role", nullable = false, length = 4)
	private int role;

	@Column(name = "telcode", nullable = false, length = 24)
	private String telcode;

	@Column(name = "status", nullable = false, length = 4)
	private int status;

	@Column(name = "dptid", nullable = true, length = 16)
	private String dptid;

	@Column(name = "receivepid", nullable = true, length = 24)
	private String receivepid;

	@Column(name = "deliverpid", nullable = true, length = 24)
	private String deliverpid;

	@Column(name = "transpid", nullable = true, length = 24)
	private String transpid;

	// private void setUID(int value) {
	// this.UID = value;
	// }
	//
	// public int getUID() {
	// return UID;
	// }
	// public void setPWD(String value) {
	// this.PWD = value;
	// }
	//
	// public String getPWD() {
	// return PWD;
	// }
	//
	// public void setName(String value) {
	// this.name = value;
	// }
	//
	// public String getName() {
	// return name;
	// }
	//
	// public void setURull(int value) {
	// this.URull = value;
	// }
	//
	// public int getURull() {
	// return URull;
	// }
	//
	// public void setTelCode(String value) {
	// this.telCode = value;
	// }
	//
	// public String getTelCode() {
	// return telCode;
	// }
	//
	// public void setStatus(int value) {
	// this.status = value;
	// }
	//
	// public int getStatus() {
	// return status;
	// }
	//
	// public void setDptID(String value) {
	// this.dptID = value;
	// }
	//
	// public String getDptID() {
	// return dptID;
	// }
	//
	// public void setReceivePackageID(String value) {
	// this.receivePackageID = value;
	// }
	//
	// public String getReceivePackageID() {
	// return receivePackageID;
	// }
	//
	// public void setDelivePackageID(String value) {
	// this.delivePackageID = value;
	// }
	//
	// public String getDelivePackageID() {
	// return delivePackageID;
	// }
	//
	// public void setTransPackageID(String value) {
	// this.transPackageID = value;
	// }
	//
	// public String geTransPackageID() {
	// return transPackageID;
	// }

	public int getORMID() {
		return getId();
	}

	public int getId() {
		return id;
	}

	@SuppressWarnings("unused")
	private void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getTelcode() {
		return telcode;
	}

	public void setTelcode(String telcode) {
		this.telcode = telcode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDptid() {
		return dptid;
	}

	public void setDptid(String dptid) {
		this.dptid = dptid;
	}

	public String getReceivepid() {
		return receivepid;
	}

	public void setReceivepid(String receivepid) {
		this.receivepid = receivepid;
	}

	public String getDeliverpid() {
		return deliverpid;
	}

	public void setDeliverpid(String deliverpid) {
		this.deliverpid = deliverpid;
	}

	public String getTranspid() {
		return transpid;
	}

	public void setTranspid(String transpid) {
		this.transpid = transpid;
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
			sb.append("User[ ");
			sb.append("Uid=").append(getId()).append(" ");
			sb.append("PassWord=").append(getPassword()).append(" ");
			sb.append("Name=").append(getUname()).append(" ");
			sb.append("Role=").append(getRole()).append(" ");
			sb.append("TelCode=").append(getTelcode()).append(" ");
			sb.append("Status=").append(getStatus()).append(" ");
			sb.append("DptID=").append(getDptid()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}
}
