package org.springframework.samples.drink_safe.consume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity // the @Entity annotation tells the complier that this is a Database mapped object
@Table(name = "consume")
public class consume {
	
	
    @Column(name = "did")
    @NotFound(action = NotFoundAction.IGNORE)
	private String did;
	
    
    @Column(name = "total_num")
    @NotFound(action = NotFoundAction.IGNORE)
	private int total_num;
    
    @Id
    @Column(name = "cusername")
    @NotFound(action = NotFoundAction.IGNORE)
	private String cusername;
    
    public consume() {}
    
    public consume(String did, String cusername, int total_num)
    {
    	this.did=did;
    	this.cusername=cusername;
    	this.total_num=total_num;
    }

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public int getTotal_num() {
		return total_num;
	}

	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}

	public String getCusername() {
		return cusername;
	}

	public void setCusername(String cusername) {
		this.cusername = cusername;
	}
    
}
