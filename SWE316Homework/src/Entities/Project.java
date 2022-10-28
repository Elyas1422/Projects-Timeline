package Entities;

import java.util.Date;

public class Project {
	private Date changedOn;
	private Date createdOn;
	private String currency;
	private int customer;
	private String customerProjectID;
	private Date endDate;
	private String nodeID;
	private int stage;
	private Date startDate;
	
	public Project(Date changedOn, Date createdOn, String currency, 
			int customer, String customerProjectID, Date endDate,
			String nodeID, int stage, Date startDate){
	
		this.changedOn = changedOn;
		this.createdOn = createdOn;
		this.currency = currency;
		this.customerProjectID = customerProjectID;
		this.endDate = endDate;
		this.nodeID = nodeID;
		this.stage = stage;
		this.startDate = startDate;
	}
	
	public Date getChangedOn() {
		return changedOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public String getCurrency() {
		return currency;
	}


	public int getCustomer() {
		return customer;
	}


	public String getCustomerProjectID() {
		return customerProjectID;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getNodeID() {
		return nodeID;
	}

	public int getStage() {
		return stage;
	}

	public Date getStartDate() {
		return startDate;
	}
	
}
