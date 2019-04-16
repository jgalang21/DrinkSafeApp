package org.springframework.samples.drink_safe.time;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.samples.drink_safe.user.User;


/**
 * The time class
 * 
 * @author Jeremy and Nick
 *
 */
@Entity 
@Table(name = "time")
public class time {
	
	@Id
    @Column(name = "tid")
    @NotFound(action = NotFoundAction.IGNORE)
	private int tid;
	
    
    @Column(name = "time_start")
    @NotFound(action = NotFoundAction.IGNORE)
	private long time_start;
    
    @Column(name = "time_finish")
    @NotFound(action = NotFoundAction.IGNORE)
	private long time_finish;
    
    @OneToOne(mappedBy = "user_time")
    private User user;
    
    
    public time()
    {
    	
    }
    public time(int tid)
    {
    	this.tid = tid;
    }
    public time(int tid, long time_start,long time_finish) {
    	this.tid = tid;
    	this.time_start = time_start;
    	this.time_finish = time_finish;
    }


	public int getTid() {
		return tid;
	}


	public void setTid(int tid) {
		this.tid = tid;
	}

	/**
	 * @return the time in YYYY-MM-DD format
	 */
	public String getTime_start() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");  
		Date date = new Date(time_start);  
		return (formatter.format(date));
	}
	

	public long toModifyTime_start() {
		return time_start;
	}

	/**
	 * Modifiable time for drinking
	 * @return time_start - the time the user starts drinking
	 */
	public void setTime_start(long time_start) {
		this.time_start = time_start;
	}


	/**
	 * @return The time until the user finishes
	 */
	public String getTime_finish() {
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");  
		Date date = new Date(time_finish);  
		return (formatter.format(date));
	}
	public long toModifyTime_finish()
	{
		return time_finish;
	}

	/**
	 * @param Sets the time until the user finishes
	 */
	public void setTime_finish(long time_finish) {
		this.time_finish = time_finish;
	}
	
	public String getUser() {
		return user.getUsername();
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String toString() 
	{
		String returner = "";
		returner += "User's name: "+ user.getName();
		returner += "User's email: " + user.getUsername();
		returner += "Time started: "+ getTime_start();
		returner += "Time to finish: "+getTime_finish();
		return returner;
		
	}
    
}