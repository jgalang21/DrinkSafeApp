package org.springframework.samples.drink_safe.drink_time;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity // the @Entity annotation tells the complier that this is a Database mapped object
@Table(name = "drink_time")
public class drink_time {

	@Id
	 @Column(name = "dtusername")
    @NotFound(action = NotFoundAction.IGNORE)
	private String person; 
	
    @Column(name = "tid")
    @NotFound(action = NotFoundAction.IGNORE)
	private int timeLeft;

    public drink_time()
    {
    	
    }
    public drink_time(String person, int time)
    {
    	this.timeLeft = time; 
    	this.person = person; 
    	
    }

    
    public String returnuser() {
    	return this.person; 
    }
    

    public int returnTimeRaw() {
    	return this.timeLeft ;
    }
    
    

    
    

	
	
}
