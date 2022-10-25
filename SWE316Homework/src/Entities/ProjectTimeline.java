package Entities;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ProjectTimeline {
	private int duration;
	private String projectID;
	private String objectValue;
	private Stage[] stages;
	
	public ProjectTimeline(String projectID, String objectValue,
			Stage[] stages) {
		this.projectID = projectID;
		this.objectValue = objectValue;
		this.stages = stages;
		calculateDuration();
	}
	
	public int getDuration() {
		return duration;
	}

	public String getProjectID() {
		return projectID;
	}

	public String getObjectValue() {
		return objectValue;
	}

	public Stage[] getStages() {
		return stages;
	}
	
	private void calculateDuration() {
		long seconds = 0;
		
		for(int i = 0, j= i+1; j < stages.length; i++, j= i + 1) {
			Date d1 = stages[i].getDate();
			Date d2 = stages[j].getDate();
			
			if(d1.compareTo(d2) >= 1)
				seconds =+ differenceInSeconds(d2, d1);
			
			else
				seconds =+ differenceInSeconds(d1, d2);
		}
		
		
		duration = (int)TimeUnit.SECONDS.toDays(seconds);
	}
	
	private long differenceInSeconds(Date smaller, Date larger) {
		return TimeUnit.MILLISECONDS.toSeconds(larger.getTime() - smaller.getTime());
		
	}
}

