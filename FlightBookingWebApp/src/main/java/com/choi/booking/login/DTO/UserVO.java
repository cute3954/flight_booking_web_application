package com.choi.booking.login.DTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserVO {

		@NotBlank(message = "IDを入力してください")
		@Size(min = 8, max = 15, message = "IDは8～15文字以内で入力してください")
		private String fb_userid;
		
		@NotBlank(message = "パスワードを入力してください")
		@Size(min = 8, max = 16, message = "パスワードは8～16文字以内で入力してください")
		private String fb_userpwd;
		
		@NotBlank(message = "お名前（氏）を20文字以内入力してください")
		private String fb_userfirstname;
		
		@NotBlank(message = "お名前（名）を20文字以内入力してください")
		private String fb_userlastname;
		
		@NotBlank(message = "電話番号を入力してください")
		@Pattern(regexp = "[0-9]{10,11}", message = "電話番号は10から11桁で入力してください")
		private String fb_userphonenumber;
		
		@NotBlank(message = "メールを入力してください")
		@Email(message = "メールを正しく入力してください")
		private String fb_useremail;

		public String getFb_userid() {
			return fb_userid;
		}
		public void setFb_userid(String fb_userid) {
			this.fb_userid = fb_userid;
		}
		public String getFb_userpwd() {
			return fb_userpwd;
		}
		public void setFb_userpwd(String fb_userpwd) {
			this.fb_userpwd = fb_userpwd;
		}
		public String getFb_userfirstname() {
			return fb_userfirstname;
		}
		public void setFb_userfirstname(String fb_userfirstname) {
			this.fb_userfirstname = fb_userfirstname;
		}
		public String getFb_userlastname() {
			return fb_userlastname;
		}
		public void setFb_userlastname(String fb_userlastname) {
			this.fb_userlastname = fb_userlastname;
		}
		public String getFb_userphonenumber() {
			return fb_userphonenumber;
		}
		public void setFb_userphonenumber(String fb_userphonenumber) {
			this.fb_userphonenumber = fb_userphonenumber;
		}
		public String getFb_useremail() {
			return fb_useremail;
		}
		public void setFb_useremail(String fb_useremail) {
			this.fb_useremail = fb_useremail;
		}		
	
}
