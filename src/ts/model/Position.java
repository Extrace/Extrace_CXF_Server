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
@Table(name = "position")
@XmlRootElement(name = "position")
public class Position implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Position() {
	}

	@Column(name = "poscode", nullable = false)
	@Id
	@GeneratedValue(generator = "MODEL_POSION_POSCODE_GENERATOR")
	@org.hibernate.annotations.GenericGenerator(name = "MODEL_POSION_POSCODE_GENERATOR", strategy = "native")
	private int poscode;

	@Column(name = "x", nullable = false, length = 10)
	private float x;

	@Column(name = "y", nullable = false, length = 10)
	private float y;

	public int getORMId() {
		return getPoscode();
	}

	public int getPoscode() {
		return poscode;
	}

	public void setPoscode(int poscode) {
		this.poscode = poscode;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return toString(false);
	}

	public String toString(boolean idOnly) {
		if (idOnly) {
			return String.valueOf(getPoscode());
		} else {
			StringBuffer sb = new StringBuffer();
			sb.append("Position[ ");
			sb.append("PosCode=").append(getPoscode()).append(" ");
			sb.append("X=").append(getX()).append(" ");
			sb.append("Y=").append(getY()).append(" ");
			sb.append("]");
			return sb.toString();
		}
	}

}
