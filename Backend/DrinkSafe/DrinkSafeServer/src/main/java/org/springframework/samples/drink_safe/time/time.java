package org.springframework.samples.drink_safe.time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity // the @Entity annotation tells the complier that this is a Database mapped object
@Table(name = "time")
public class time {
	
	@Id
    @Column(name = "tid")
    @NotFound(action = NotFoundAction.IGNORE)
	private int tid;
	
    
    @Column(name = "time_start")
    @NotFound(action = NotFoundAction.IGNORE)
	private int time_start;
    
    @Column(name = "time_finish")
    @NotFound(action = NotFoundAction.IGNORE)
	private int time_finish;
    
    
    public time(int tid, int time_start,int time_finish) {
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


	public int getTime_start() {
		return time_start;
	}


	public void setTime_start(int time_start) {
		this.time_start = time_start;
	}


	public int getTime_finish() {
		return time_finish;
	}


	public void setTime_finish(int time_finish) {
		this.time_finish = time_finish;
	}
    
}