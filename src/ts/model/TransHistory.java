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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "transhistory")
@XmlRootElement(name = "TransHistory")
public class TransHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6973429316324711538L;

	public TransHistory() {
	}

	@Column(name = "sn", nullable = false)
	@Id
	@GeneratedValue(generator = "MODEL_TRANSHISTORY_SN_GENERATOR")
	@org.hibernate.annotations.GenericGenerator(name = "MODEL_TRANSHISTORY_SN_GENERATOR", strategy = "native")
	private int sn;

	@ManyToOne(targetEntity = Package.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "packageid", referencedColumnName = "id", nullable = false) })
	private Package packageid;

	@Column(name = "acttime", nullable = false)
	private Date acttime;

	@Column(name = "poscode", nullable = true, length = 10)
	private Integer poscode;

	@Column(name = "uidfrom", nullable = false, length = 10)
	private int uidfrom;

	@Column(name = "uidto", nullable = false, length = 10)
	private int uidto;

	@SuppressWarnings("unused")
	private void setSn(int value) {
		this.sn = value;
	}

	public int getSn() {
		return sn;
	}

	public int getORMId() {
		return getSn();
	}

	public void setActtime(Date value) {
		this.acttime = value;
	}

	public Date getActtime() {
		return acttime;
	}

	public void setPoscode(Integer value) {
		this.poscode = value;
	}

	public Integer getPoscode() {
		return poscode;
	}

	public void setUidfrom(int value) {
		this.uidfrom = value;
	}

	public int getUidfrom() {
		return uidfrom;
	}

	public void setUidto(int value) {
		this.uidto = value;
	}

	public int getUidto() {
		return uidto;
	}

	public void setPackageid(Package value) {
		this.packageid = value;
	}

	public Package getPackageid() {
		return packageid;
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
			sb.append("TransHistory[ ");
			sb.append("SN=").append(getSn()).append(" ");
			if (getPackageid() != null)
				sb.append("Packageid.Persist_ID=")
						.append(getPackageid().toString(true)).append(" ");
			else
				sb.append("Packageid=null ");
			sb.append("ActTime=").append(getActtime()).append(" ");
			sb.append("PosCode=").append(getPoscode()).append(" ");
			sb.append("UidFrom=").append(getUidfrom()).append(" ");
			sb.append("UidTo=").append(getUidto()).append(" ");
			sb.append("UserF=").append(getUserFrom()).append(" ");
			sb.append("UserT=").append(getUserTo()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

	@Transient
	private User userTo;
	@Transient
	private User userFrom;
	@Transient
	private Position position;

	public User getUserTo() {
		return userTo;
	}

	public void setUserTo(User userTo) {
		this.userTo = userTo;
	}

	public User getUserFrom() {
		return userFrom;
	}

	public void setUserFrom(User userFrom) {
		this.userFrom = userFrom;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
