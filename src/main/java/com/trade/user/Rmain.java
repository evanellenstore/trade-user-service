package com.trade.user;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Calendar;
import java.util.Random;

public class Rmain {

    public static void main(String[] args) {
        System.out.println("Hello, World!");


        try {

			int timeType = Calendar.HOUR;
			int timeAmount = 7;
			long currentTime  =  System.currentTimeMillis();
			Calendar calendar2 = Calendar.getInstance();			
			calendar2.add(timeType, timeAmount);

			Robot robot= new Robot();
			Random randown = new Random();
			while(currentTime < calendar2.getTimeInMillis()) {
				robot.mouseMove(randown.nextInt(10), randown.nextInt(10));
				Thread.sleep(3*60*1000);
				currentTime  = System.currentTimeMillis();
			}
		} catch (AWTException | InterruptedException e) {
			e.printStackTrace();
		}
    }

}
