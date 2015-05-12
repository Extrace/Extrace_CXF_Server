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
@Table(name = "transnode")
@XmlRootElement(name = "transnode")
public class TransNode implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransNode() {
	}

	@Column(name = "id", nullable = false)
	@Id
	@GeneratedValue(generator = "MODEL_TRANSNODE_ID_GENERATOR")
	@org.hibernate.annotations.GenericGenerator(name = "MODEL_TRANSNODE_ID_GENERATOR", strategy = "assigned")
	private String id;

	@Column(name = "nodename", nullable = true, length = 32)
	private String nodename;

	@Column(name = "nodetype", nullable = false, length = 10)
	private int nodetype;

	@Column(name = "regioncode", nullable = true, length = 6)
	private String regioncode;

	@Column(name = "poscode", nullable = true, length = 10)
	private Integer poscode;

	@Column(name = "telcode", nullable = false, length = 24)
	private String telcode;

	// public void setID(String value) {
	// this.ID = value;
	// }
	//
	// public String getID() {
	// return ID;
	// }
	// public void setNodeName(String value) {
	// this.nodeName = value;
	// }
	//
	// public String getNodeName() {
	// return nodeName;
	// }
	//
	// public void setNodeType(int value) {
	// this.nodeType = value;
	// }
	//
	// public int getNodeType() {
	// return nodeType;
	// }
	//
	// public void setRegionCode(String value) {
	// this.regionCode = value;
	// }
	//
	// public String getRegionCode() {
	// return regionCode;
	// }
	//
	// public void setPostCode(Integer value) {
	// this.postCode = value;
	// }
	//
	// public Integer getPostCode() {
	// return postCode;
	// }
	//
	// public void setTelCode(String value) {
	// this.telCode = value;
	// }
	//
	// public String getTelCode() {
	// return telCode;
	// }

	@Override
	public String toString() {
		return toString(false);
	}

	public String getId() {
		return id;
	}

	public String getORMId() {
		return getId();
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public int getNodetype() {
		return nodetype;
	}

	public void setNodetype(int nodetype) {
		this.nodetype = nodetype;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}

	public Integer getPoscode() {
		return poscode;
	}

	public void setPoscode(Integer poscode) {
		this.poscode = poscode;
	}

	public String getTelcode() {
		return telcode;
	}

	public void setTelcode(String telcode) {
		this.telcode = telcode;
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getId());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("TransNode[ ");
			sb.append("ID=").append(getId()).append(" ");
			sb.append("NodeName=").append(getNodename()).append(" ");
			sb.append("NodeType=").append(getNodetype()).append(" ");
			sb.append("RegionCode=").append(getRegioncode()).append(" ");
			sb.append("PosCode=").append(getPoscode()).append(" ");
			sb.append("TelCode=").append(getTelcode()).append(" ");
			sb.append("]");
			return sb.toString();
		}
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
