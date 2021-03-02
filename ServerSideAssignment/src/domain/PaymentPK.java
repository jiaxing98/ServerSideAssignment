package domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the payments database table.
 * 
 */
@Embeddable
@NamedQuery(name="PaymentPK.findbycustomernumber", query="SELECT p FROM PaymentPK p WHERE p.customernumber = :customernumber")
@NamedQuery(name="PaymentPK.findbychecknumber", query="SELECT p FROM PaymentPK p WHERE p.checknumber = :checknumber")
public class PaymentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=true, updatable=false)
	private Integer customernumber;

	private String checknumber;

	public PaymentPK() {
	}
	public Integer getCustomernumber() {
		return this.customernumber;
	}
	public void setCustomernumber(Integer customernumber) {
		this.customernumber = customernumber;
	}
	public String getChecknumber() {
		return this.checknumber;
	}
	public void setChecknumber(String checknumber) {
		this.checknumber = checknumber;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PaymentPK)) {
			return false;
		}
		PaymentPK castOther = (PaymentPK)other;
		return 
			this.customernumber.equals(castOther.customernumber)
			&& this.checknumber.equals(castOther.checknumber);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.customernumber.hashCode();
		hash = hash * prime + this.checknumber.hashCode();
		
		return hash;
	}
}