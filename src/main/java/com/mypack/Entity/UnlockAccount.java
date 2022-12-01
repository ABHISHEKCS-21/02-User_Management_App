package com.mypack.Entity;

import lombok.Data;

@Data
public class UnlockAccount {
	private String email;
	private String temPassword;
	private String newPassword;
	private String confPassword;
}
