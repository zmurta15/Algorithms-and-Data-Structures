package Boleia;

import java.io.Serializable;

/**
 * The class that represents the time, with hours and minutes
 * @author Jose Murta 55226 & Diogo Rodrigues 56153
 *
 */
public class HourMinutesClass implements HourMinutes, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private static final int NUM_FIELDS = 2;
	
	/**
	 * Array of the integers of the date
	 */
	private int[] raw;
	
	private String time;
	
	/**
	 * Builds a new raw time object.
	 * 
	 * @param time - a string of the form N1:N2, where N1 and N2 are positive
	 *             numbers representable as integers.
	 */
	public HourMinutesClass(String time) {
		this.time = time;
		String[] split = time.split(":");
		raw = new int[NUM_FIELDS];
		
		for (int i = 0; i < split.length; i++) {
			raw[i] = Integer.parseInt(split[i].trim());
		}
 	}
	
	public int getHour() {
		return raw[0];
	}
	
	public int getMinutes() {
		return raw[1];
	}
	
	public boolean isValid() {
		return (getHour()>=0 && getHour()<24 && getMinutes()>=0 && getMinutes()<60);
	}
	
	public String getTime() {
		return time;
	}
}
