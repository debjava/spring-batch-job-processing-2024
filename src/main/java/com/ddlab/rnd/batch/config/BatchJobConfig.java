package com.ddlab.rnd.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchJobConfig {
	
	@Bean(name = "appImportJob")
	public Job configureImportJob(JobRepository jobRepository, 
			@Qualifier("updateStep1") Step updateStep1,
			@Qualifier("insertStep2") Step insertStep2) {
		
		Job job = new JobBuilder("appImportJob", jobRepository)
				.incrementer(new RunIdIncrementer())
				.start(updateStep1)
				.next(insertStep2)
				.build();
//				 .listener(sfdcJobCompletionListener()) // Later
		return job;
	}

}
