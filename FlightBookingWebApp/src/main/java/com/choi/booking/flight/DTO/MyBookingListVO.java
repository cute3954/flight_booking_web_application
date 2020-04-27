package com.choi.booking.flight.DTO;

/* 予約者番号、航空券番号 */
public class MyBookingListVO {
	
		private int fb_userno;
		private int fb_flightno;

		public int getFb_userno() {
			return fb_userno;
		}
		public void setFb_userno(int fb_userno) {
			this.fb_userno = fb_userno;
		}
		public int getFb_flightno() {
			return fb_flightno;
		}
		public void setFb_flightno(int fb_flightno) {
			this.fb_flightno = fb_flightno;
		}
}
