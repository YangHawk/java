package xyz.itwill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountAuth {
	private int idx;
	private String id;
	private String auth;
}
