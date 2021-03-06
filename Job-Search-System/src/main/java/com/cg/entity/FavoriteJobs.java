package com.cg.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * @author wintech
 *
 */
@Entity
@Table(name="FavJobs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteJobs {
	@Id   
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id",nullable=false)
	private Integer favJob_id;
	@ManyToOne
	@JoinColumn(name="jobseeker_id",nullable=false)
	private JobSeeker jobseeker;
	@ManyToOne
	@JoinColumn(name="job_id",nullable=false)
	private Job job;	
}