package Entities;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
    private ArrayList<ProjectStage> stages;
    private ArrayList<ProjectStage> stagesBeforeAwarding;
    private ArrayList<ProjectStage> stagesAfterAwarding;

    public Project(Date changedOn, Date createdOn, String currency, 
            int customer, String customerProjectID, Date endDate,
            String nodeID, int stage, Date startDate){

        this.stages= new ArrayList<ProjectStage>();
        this.stagesBeforeAwarding=new ArrayList<ProjectStage>();
        this.stagesAfterAwarding=new ArrayList<ProjectStage>();
        this.changedOn = changedOn;
        this.createdOn = createdOn;
        this.currency = currency;
        this.customerProjectID = customerProjectID;
        this.endDate = endDate;
        this.nodeID = nodeID;
        this.stage = stage;
        this.startDate = startDate;


    }
    public void setBeforeAfterReworks() {
        int maxValueReached=0;
        boolean isAwarding =false;
        boolean isBeforeRework= false;
        for (ProjectStage ps : stages) {

            if (!ps.getProgress()) {
                if(isAwarding)
                    stagesAfterAwarding.add(ps);
                else {
                    stagesBeforeAwarding.add(ps);
                    isBeforeRework=true;
                }
            }
            else {//it is in progress (moving forward)
                if (maxValueReached<ps.getNewValue()) {
                    if(isBeforeRework) {
                        isAwarding=true;
                        isBeforeRework=false;}
                    maxValueReached= ps.getNewValue();
                }
            }  
        }
    }
    public ArrayList<ProjectStage> getStagesBeforeAwarding() {
        return stagesBeforeAwarding;
    }

    public ArrayList<ProjectStage> getStagesAfterAwarding() {
        return stagesAfterAwarding;
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
    public ArrayList<ProjectStage> getStages(){
        return this.stages;
    }

    public int getDuration() {
        return (int) TimeUnit.MILLISECONDS.toDays(stages.get(stages.size()-1).getDate().getTime() - stages.get(0).getDate().getTime());
    }
    private boolean stagesSet;
    public void setStages() {

        try {
            if (!stagesSet) {
                ArrayList<ProjectStage> allStages = ExcelFileReader.readStages();
                boolean found = false;

                for(int i=0; i<allStages.size(); i++) {

                    if(getNodeID().equals(allStages.get(i).getObjectValue())) {
                        stages.add(allStages.get(i));
                        found = true;
                        continue;
                    }
                    if(found) {break;}
                }
                this.setBeforeAfterReworks();
                stagesSet=true;
            }

        } catch (ParseException e) {

            e.getMessage();
        }

    }

    @Override
    public String toString() {
        return customerProjectID + " stage=" +stage ;
    }

}
