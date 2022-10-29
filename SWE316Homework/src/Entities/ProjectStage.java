package Entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectStage {
	private char changeIndicator;
	private Date date;
	private String fieldName;
	private int newValue;
	private String objectValue;
	private int oldvalue;
	private int textFlag;
	private Date time;
	private boolean progress;
	private int documentNumber;
	
	public ProjectStage(char changeIndicator, Date date, String fieldName,
			int newValue, String objectValue, int oldvalue,
			int textFlag, Date time, int documentNumber) {
		
		this.changeIndicator = changeIndicator;
		this.date = date;
		this.fieldName = fieldName;
		this.newValue = newValue;
		this.objectValue = objectValue;
		this.oldvalue = oldvalue;
		this.textFlag = textFlag;
		this.time = time;
		this.documentNumber=documentNumber; //TODO: replace it with an object of type document
		checkProgress();
		
	}
	
	@Override
    public String toString() {
	    SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate= DateFor.format(date);
        return stringDate+", "+newValue;
    }

    public int getDocumentNumber() {
        return documentNumber;
    }

    public char getChangeIndicator() {
		return changeIndicator;
	}

	public Date getDate() {
		return date;
	}

	public String getFieldName() {
		return fieldName;
	}

	public int getNewValue() {
		return newValue;
	}

	public String getObjectValue() {
		return objectValue;
	}

	public int getOldvalue() {
		return oldvalue;
	}

	public int getTextFlag() {
		return textFlag;
	}

	public Date getTime() {
		return time;
	}
	
	public boolean getProgress() {
		return progress;
	}
	
	
	public void setDate(Date date) {
        this.date = date;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    private void checkProgress() {
		if(oldvalue < newValue)
			progress = true;
		else
			progress = false;
	}
}
