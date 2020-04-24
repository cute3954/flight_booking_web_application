package com.choi.booking.flight.DTO;

import java.sql.Date;
import java.sql.Time;

public class FlightVO {
	
		private Date fb_flightdate;
		private Date fb_flightdate_return;
		private String fb_flightfrom;
		private String fb_flightfrom_eng;
	    private Time fb_flightfromtime;
	    private String fb_flightto;
	    private String fb_flightto_eng;
	    private Time fb_flighttotime;
	    private String fb_flightname;
	    private String fb_flightequip;
	    private int fb_flightprice;
	    private String fb_flightclass;
	    // 往復か片道か
	    private boolean isroundtrip;
	    
		public Date getFb_flightdate() {
			return fb_flightdate;
		}
		public void setFb_flightdate(Date fb_flightdate) {
			this.fb_flightdate = fb_flightdate;
		}
		public Date getFb_flightdate_return() {
			return fb_flightdate_return;
		}
		public void setFb_flightdate_return(Date fb_flightdate_return) {
			this.fb_flightdate_return = fb_flightdate_return;
		}
		public String getFb_flightfrom() {
			return fb_flightfrom;
		}
		public void setFb_flightfrom(String fb_flightfrom) {
			this.fb_flightfrom = fb_flightfrom;
		}
		public String getFb_flightfrom_eng() {
			return fb_flightfrom_eng;
		}
		public void setFb_flightfrom_eng(String fb_flightfrom_eng) {
			this.fb_flightfrom_eng = fb_flightfrom_eng;
		}
		public Time getFb_flightfromtime() {
			return fb_flightfromtime;
		}
		public void setFb_flightfromtime(Time fb_flightfromtime) {
			this.fb_flightfromtime = fb_flightfromtime;
		}
		public String getFb_flightto() {
			return fb_flightto;
		}
		public void setFb_flightto(String fb_flightto) {
			this.fb_flightto = fb_flightto;
		}
		public String getFb_flightto_eng() {
			return fb_flightto_eng;
		}
		public void setFb_flightto_eng(String fb_flightto_eng) {
			this.fb_flightto_eng = fb_flightto_eng;
		}
		public Time getFb_flighttotime() {
			return fb_flighttotime;
		}
		public void setFb_flighttotime(Time fb_flighttotime) {
			this.fb_flighttotime = fb_flighttotime;
		}
		public String getFb_flightname() {
			return fb_flightname;
		}
		public void setFb_flightname(String fb_flightname) {
			this.fb_flightname = fb_flightname;
		}
		public String getFb_flightequip() {
			return fb_flightequip;
		}
		public void setFb_flightequip(String fb_flightequip) {
			this.fb_flightequip = fb_flightequip;
		}
		public int getFb_flightprice() {
			return fb_flightprice;
		}
		public void setFb_flightprice(int fb_flightprice) {
			this.fb_flightprice = fb_flightprice;
		}
		public String getFb_flightclass() {
			return fb_flightclass;
		}
		public void setFb_flightclass(String fb_flightclass) {
			this.fb_flightclass = fb_flightclass;
		}
		public boolean getIsroundtrip() {
			return isroundtrip;
		}
		public void setIsroundtrip(boolean isroundtrip) {
			this.isroundtrip = isroundtrip;
		}		
}
