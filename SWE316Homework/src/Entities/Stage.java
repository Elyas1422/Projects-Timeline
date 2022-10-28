package Entities;

import java.util.Date;

public class Stage {
	private char changeIndicator;
	private Date date;
	private String fieldName;
	private int newValue;
	private String objectValue;
	private int oldvalue;
	private int textFlag;
	private Date time;
	private boolean progress;
	
	public Stage(char changeIndicator, Date date, String fieldName,
			int newValue, String objectValue, int oldvalue,
			int textFlag, Date time) {
		
		this.changeIndicator = changeIndicator;
		this.date = date;
		this.fieldName = fieldName;
		this.newValue = newValue;
		this.objectValue = objectValue;
		this.oldvalue = oldvalue;
		this.textFlag = textFlag;
		this.time = time;
		checkProgress();
		
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
	
	private void checkProgress() {
		if(oldvalue < newValue)
			progress = true;
		else
			progress = false;
	}
}
