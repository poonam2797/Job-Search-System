package com.cg.service;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FavoriteJobsDO {

	private Integer jobId;
	private Integer jobSeekerId;

	public FavoriteJobsDO() {

	}
	public FavoriteJobsDO(Integer jobSeekerId) {

		this.jobSeekerId = jobSeekerId;
	}

}
