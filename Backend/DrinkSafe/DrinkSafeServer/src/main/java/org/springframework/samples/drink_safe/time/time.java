package org.springframework.samples.drink_safe.time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.samples.drink_safe.user.User;

@Entity // the @Entity annotation tells the complier that this is a Database mapped object
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


	public long getTime_start() {
		return time_start;
	}


	public void setTime_start(long time_start) {
		this.time_start = time_start;
	}


	public long getTime_finish() {
		return time_finish;
	}


	public void setTime_finish(long time_finish) {
		this.time_finish = time_finish;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
    
}