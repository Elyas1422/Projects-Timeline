package Entities;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import FilesProcessing.ExcelFileReader;

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
	private ArrayList<Stage> stages;
	
	public Project(Date changedOn, Date createdOn, String currency, 
			int customer, String customerProjectID, Date endDate,
			String nodeID, int stage, Date startDate){
	
	    this.stages= new ArrayList<Stage>();
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
	
	public void setStages() {
	    
	    try {
	        
            ArrayList<Stage> allStages = ExcelFileReader.readStages();
            boolean found = false;
            
            for(int i=0; i<allStages.size(); i++) {

                if(getNodeID().equals(allStages.get(i).getObjectValue())) {
                    stages.add(allStages.get(i));
                    found = true;
                    continue;
                }
                if(found) {break;}
            }
            
            
        } catch (ParseException e) {
    
            e.getMessage();
        }
	    
	}
	public ArrayList<Stage> getStages(){
	    return this.stages;
	}
}
