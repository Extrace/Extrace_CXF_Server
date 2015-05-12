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

	@SuppressWarnings("unused")
	private void setPoscode(int value) {
		this.poscode = value;
	}

	public int getPoscode() {
		return poscode;
	}

	public int getORMId() {
		return getPoscode();
	}

	public void setX(float value) {
		this.x = value;
	}

	public float getX() {
		return x;
	}

	public void setY(float value) {
		this.y = value;
	}

	public float getY() {
		return y;
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
