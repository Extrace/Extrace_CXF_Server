/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Evaluation
 */
package ts.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "package")
@XmlRootElement(name = "package")
public class Package implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3050390478904210174L;

	public Package() {
	}

	@Column(name = "id", nullable = false)
	@Id
	// @GeneratedValue(generator = "MODEL_TRANSPACKAGE_ID_GENERATOR")
	// @org.hibernate.annotations.GenericGenerator(name =
	// "MODEL_TRANSPACKAGE_ID_GENERATOR", strategy = "assigned")
	private String id;

	@Column(name = "sourcenode", nullable = true, length = 8)
	private String sourcenode;

	@Column(name = "targetnode", nullable = true, length = 8)
	private String targetnode;

	@Column(name = "createtime", nullable = true)
	private Date createtime;

	@Column(name = "status", nullable = false, length = 4)
	private int status;

	// @ManyToMany(mappedBy = "Package", targetEntity = ExpressSheet.class)
	// @org.hibernate.annotations.Cascade({
	// org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	// org.hibernate.annotations.CascadeType.LOCK })
	// @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	// private List<ExpressSheet> expreesSheet = new ArrayList<ExpressSheet>();

	@OneToMany(mappedBy = "packageid", targetEntity = TransHistory.class)
	@org.hibernate.annotations.Cascade({
			org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.LOCK })
	@org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.FALSE)
	private List<TransHistory> transHistory = new ArrayList<TransHistory>();

	// public void setExpreesSheet(List<ExpressSheet> value) {
	// this.expreesSheet = value;
	// }
	//
	// @XmlTransient
	// public List<ExpressSheet> getExpreesSheet() {
	// return expreesSheet;
	// }

	public void setTransHistory(List<TransHistory> value) {
		this.transHistory = value;
	}

	@XmlTransient
	public List<TransHistory> getTransHistory() {
		return transHistory;
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

	public String getSourcenode() {
		return sourcenode;
	}

	public void setSourcenode(String sourcenode) {
		this.sourcenode = sourcenode;
	}

	public String getTargetnode() {
		return targetnode;
	}

	public void setTargetnode(String targetnode) {
		this.targetnode = targetnode;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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
			sb.append("TransPackage[ ");
			sb.append("ID=").append(getId()).append(" ");
			sb.append("SourceNode=").append(getSourcenode()).append(" ");
			sb.append("TargetNode=").append(getTargetnode()).append(" ");
			sb.append("CreateTime=").append(getCreatetime()).append(" ");
			sb.append("Status=").append(getStatus()).append(" ");
			// sb.append("ExpreesSheet.size=").append(getExpreesSheet().size())
			// .append(" ");
			sb.append("TransHistory.size=").append(getTransHistory().size())
					.append(" ");
			if (getSourceTransNode() != null) {
				sb.append("sourNode=").append(getSourceTransNode().toString())
						.append(" ");
			}
			if (getTargetTransNode() != null) {
				sb.append("TransNode=").append(getTargetTransNode().toString())
						.append(" ");
				sb.append("]");
			}
			return sb.toString();
		}
	}

	@Transient
	private TransNode sourceTransNode;

	public TransNode getSourceTransNode() {
		return sourceTransNode;
	}

	public void setSourceTransNode(TransNode sourceTransNode) {
		this.sourceTransNode = sourceTransNode;
	}

	@Transient
	private TransNode targetTransNode;

	public TransNode getTargetTransNode() {
		return targetTransNode;
	}

	public void setTargetTransNode(TransNode targetTransNode) {
		this.targetTransNode = targetTransNode;
	}

	@Transient
	private int uid;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
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

	public static final class STATUS {
		public static final int STATUS_CREATED = 0;
		public static final int STATUS_PACKED = 1;
		public static final int STATUS_TRANSPORT = 2;
		public static final int STATUS_PARTATION = 3;
		public static final int STATUS_DELIVED = 4;
		public static final int STATUS_DISPACHED = 5;
	}
}
