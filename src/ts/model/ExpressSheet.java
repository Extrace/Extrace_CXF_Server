package ts.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@org.hibernate.annotations.Proxy(lazy = false)
@Table(name = "expresssheet")
@XmlRootElement(name = "expreessheet")
public class ExpressSheet implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4941503468986892397L;

	public ExpressSheet() {
	}

	@Column(name = "id", nullable = false)
	@Id
	// @GeneratedValue(generator="MODEL_EXPREESSHEET_ID_GENERATOR")
	// @org.hibernate.annotations.GenericGenerator(name="MODEL_EXPREESSHEET_ID_GENERATOR",
	// strategy="assigned")
	private String id;

	@Column(name = "expresstype", nullable = true, length = 4)
	private int expresstype;

	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "sender", referencedColumnName = "id", nullable = true) })
	private Customer sender;

	@ManyToOne(targetEntity = Customer.class, fetch = FetchType.LAZY)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.LOCK })
	@JoinColumns({ @JoinColumn(name = "receiver", referencedColumnName = "id", nullable = true) })
	private Customer receiver;

	@Column(name = "weight", nullable = true)
	private Float weight;

	@Column(name = "transcost", nullable = true)
	private Float transcost;

	@Column(name = "packagecost", nullable = true)
	private Float packagecost;

	@Column(name = "insurcost", nullable = true)
	private Float insurcost;

	@Column(name = "accepter", nullable = true, length = 16)
	private String accepter;

	@Column(name = "deliver", nullable = true, length = 16)
	private String deliver;

	@Column(name = "acceptetime", nullable = true)
	private Date acceptetime;

	@Column(name = "delivetime", nullable = true)
	private Date delivetime;

	@Column(name = "acc1", nullable = true, length = 64)
	private String acc1;

	@Column(name = "acc2", nullable = true, length = 64)
	private String acc2;

	@Column(name = "status", nullable = true, length = 4)
	private Integer status;

	// @ManyToMany(targetEntity=TransPackage.class)
	// @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	// org.hibernate.annotations.CascadeType.LOCK})
	// @JoinTable(name="TransPackageContent", joinColumns={
	// @JoinColumn(name="ExpreesSheetID") }, inverseJoinColumns={
	// @JoinColumn(name="TransPackageID") })
	// @org.hibernate.annotations.LazyCollection(org.hibernate.annotations.LazyCollectionOption.TRUE)
	// private java.util.Set<TransPackage> transPackage = new
	// java.util.HashSet<TransPackage>();

	public String getId() {
		return id;
	}

	public String getORMId() {
		return getId();
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getExpresstype() {
		return expresstype;
	}

	public void setExpresstype(int expresstype) {
		this.expresstype = expresstype;
	}

	public Customer getSender() {
		return sender;
	}

	public void setSender(Customer sender) {
		this.sender = sender;
	}

	public Customer getReceiver() {
		return receiver;
	}

	public void setReceiver(Customer receiver) {
		this.receiver = receiver;
	}

	public Float getWeight() {
		return weight;
	}

	public void setWeight(Float weight) {
		this.weight = weight;
	}

	public Float getTranscost() {
		return transcost;
	}

	public void setTranscost(Float transcost) {
		this.transcost = transcost;
	}

	public Float getPackagecost() {
		return packagecost;
	}

	public void setPackagecost(Float packagecost) {
		this.packagecost = packagecost;
	}

	public Float getInsurcost() {
		return insurcost;
	}

	public void setInsurcost(Float insurcost) {
		this.insurcost = insurcost;
	}

	public String getAccepter() {
		return accepter;
	}

	public void setAccepter(String accepter) {
		this.accepter = accepter;
	}

	public String getDeliver() {
		return deliver;
	}

	public void setDeliver(String deliver) {
		this.deliver = deliver;
	}

	public Date getAcceptetime() {
		return acceptetime;
	}

	public void setAcceptetime(Date acceptetime) {
		this.acceptetime = acceptetime;
	}

	public Date getDelivetime() {
		return delivetime;
	}

	public void setDelivetime(Date delivetime) {
		this.delivetime = delivetime;
	}

	public String getAcc1() {
		return acc1;
	}

	public void setAcc1(String acc1) {
		this.acc1 = acc1;
	}

	public String getAcc2() {
		return acc2;
	}

	public void setAcc2(String acc2) {
		this.acc2 = acc2;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/*
	 * public void setID(String value) { this.ID = value; }
	 * 
	 * public String getID() { return ID; }
	 * 
	 * public String getORMID() { return getID(); }
	 * 
	 * public void setType(int value) { this.type = value; }
	 * 
	 * public int getType() { return type; }
	 * 
	 * public void setWeight(Float value) { this.weight = value; }
	 * 
	 * public Float getWeight() { return weight; }
	 * 
	 * public void setTranFee(Float value) { this.tranFee = value; }
	 * 
	 * public Float getTranFee() { return tranFee; }
	 * 
	 * public void setPackageFee(Float value) { this.packageFee = value; }
	 * 
	 * public Float getPackageFee() { return packageFee; }
	 * 
	 * public void setInsuFee(Float value) { this.insuFee = value; }
	 * 
	 * public Float getInsuFee() { return insuFee; }
	 * 
	 * public void setAccepter(String value) { this.accepter = value; }
	 * 
	 * public String getAccepter() { return accepter; }
	 * 
	 * public void setDeliver(String value) { this.deliver = value; }
	 * 
	 * public String getDeliver() { return deliver; }
	 * 
	 * public void setAccepteTime(Date value) { this.accepteTime = value; }
	 * 
	 * public Date getAccepteTime() { return accepteTime; }
	 * 
	 * public void setDeliveTime(Date value) { this.deliveTime = value; }
	 * 
	 * public Date getDeliveTime() { return deliveTime; }
	 * 
	 * public void setAcc1(String value) { this.acc1 = value; }
	 * 
	 * public String getAcc1() { return acc1; }
	 * 
	 * public void setAcc2(String value) { this.acc2 = value; }
	 * 
	 * public String getAcc2() { return acc2; }
	 * 
	 * public void setStatus(Integer value) { this.status = value; }
	 * 
	 * public Integer getStatus() { return status; }
	 * 
	 * // public void setTransPackage(java.util.Set<TransPackage> value) { //
	 * this.transPackage = value; // } // // public java.util.Set<TransPackage>
	 * getTransPackage() { // return transPackage; // }
	 * 
	 * public void setSender(Customer value) { this.sender = value; }
	 * 
	 * public Customer getSender() { return sender; }
	 * 
	 * public void setRecever(Customer value) { this.recever = value; }
	 * 
	 * public Customer getRecever() { return recever; }
	 */
	@Override
	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getId());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("ExpreesSheet[ ");
			sb.append("Id=").append(getId()).append(" ");
			sb.append("ExpressType=").append(getExpresstype()).append(" ");
			if (getSender() != null)
				sb.append("Sender.Persist_ID=")
						.append(getSender().toString(true)).append(" ");
			else
				sb.append("Sender=null ");
			if (getReceiver() != null)
				sb.append("Receiver.Persist_ID=")
						.append(getReceiver().toString(true)).append(" ");
			else
				sb.append("Receiver=null ");
			sb.append("Weight=").append(getWeight()).append(" ");
			sb.append("Transcost=").append(getTranscost()).append(" ");
			sb.append("Packagecost=").append(getPackagecost()).append(" ");
			sb.append("Insurcost=").append(getInsurcost()).append(" ");
			sb.append("Accepter=").append(getAccepter()).append(" ");
			sb.append("Deliver=").append(getDeliver()).append(" ");
			sb.append("AccepteTime=").append(getAccepter()).append(" ");
			sb.append("DeliveTime=").append(getDelivetime()).append(" ");
			sb.append("Acc1=").append(getAcc1()).append(" ");
			sb.append("Acc2=").append(getAcc2()).append(" ");
			sb.append("Status=").append(getStatus()).append(" ");
			// sb.append("TransPackage.size=").append(getTransPackage().size()).append(" ");
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

	public static final class STATUS {
		public static final int STATUS_CREATED = 0;
		public static final int STATUS_RECEIVED = 1;
		public static final int STATUS_PARTATION = 2;
		public static final int STATUS_TRANSPORT = 3;
		public static final int STATUS_DISPATCHED = 4;
		public static final int STATUS_DELIVERIED = 5;
	}
}
