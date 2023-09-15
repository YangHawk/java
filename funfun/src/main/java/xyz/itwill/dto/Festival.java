package xyz.itwill.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class Festival {
	private int idx;
	private String accountId;
	private String subject;
	private String site;
	private String startDay;
	private String endDay;
	private String address1;
	private String address2;
	private String address3;
	private String host;
	private String mainImg;
	private String subImg;
	private long target;
	private String fundingStart;
	private String fundingEnd;
	private int state;
	private long option1;
	private long option2;
	private long option3;
	private long option4;
	private long collected;
	private int sponsor;
	private long avgstar;
	private double percentage;
	private int deadline;
	private MultipartFile mainMultipartFile;
	private MultipartFile subMultipartFile;
}
