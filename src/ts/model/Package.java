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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

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
	@GeneratedValue(generator = "MODEL_TRANSPACKAGE_ID_GENERATOR")
	@org.hibernate.annotations.GenericGenerator(name = "MODEL_TRANSPACKAGE_ID_GENERATOR", strategy = "assigned")
	private String id;

	@Column(name = "sourcenode", nullable = true, length = 8)
	private String sourcenode;

	@Column(name = "targetnode", nullable = true, length = 8)
	private String targetnode;

	@Column(name = "createtime", nullable = true)
	private Date createtime;

	@Column(name = "status", nullable = false, length = 4)
	private int status;

	// @ManyToMany(mappedBy="transPackage", targetEntity=ExpreesSheet.class)
	// @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	// org.hibernate.annotations.CascadeType.LOCK})
	// @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	// private java.util.Set<ExpreesSheet> expreesSheet = new
	// java.util.HashSet<ExpreesSheet>();
	//
	// @OneToMany(mappedBy="packeg", targetEntity=TransHistory.class)
	// @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	// org.hibernate.annotations.CascadeType.LOCK})
	// @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	// private java.util.Set<TransHistory> transHistory = new
	// java.util.HashSet<TransHistory>();

	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return id;
	}

	public String getORMId() {
		return getId();
	}

	public void setSourcenode(String value) {
		this.sourcenode = value;
	}

	public String getSourcenode() {
		return sourcenode;
	}

	public void setTargetnode(String value) {
		this.targetnode = value;
	}

	public String getTargetnode() {
		return targetnode;
	}

	public void setCreatetime(Date value) {
		this.createtime = value;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setStatus(int value) {
		this.status = value;
	}

	public int getStatus() {
		return status;
	}

	// public void setExpreesSheet(java.util.Set<ExpreesSheet> value) {
	// this.expreesSheet = value;
	// }
	//
	// public java.util.Set<ExpreesSheet> getExpreesSheet() {
	// return expreesSheet;
	// }
	//
	//
	// public void setTransHistory(java.util.Set<TransHistory> value) {
	// this.transHistory = value;
	// }
	//
	// public java.util.Set<TransHistory> getTransHistory() {
	// return transHistory;
	// }

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
			// sb.append("ExpreesSheet.size=").append(getExpreesSheet().size()).append(" ");
			// sb.append("TransHistory.size=").append(getTransHistory().size()).append(" ");
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