package com.ddlab.rnd.batch.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.ddlab.rnd.batch.listener.InsertItemProcessListener;
import com.ddlab.rnd.batch.listener.InsertItemReadListener;
import com.ddlab.rnd.batch.listener.InsertItemWriteListener;
import com.ddlab.rnd.batch.processor.InsertItemProcessor;
import com.ddlab.rnd.batch.processor.UpdatetemProcessor;
import com.ddlab.rnd.batch.reader.InsertItemReader;
import com.ddlab.rnd.batch.reader.UpdateItemReader1;
import com.ddlab.rnd.batch.writer.InsertItemWriter;
import com.ddlab.rnd.batch.writer.UpdateItemWriter;

@Configuration
public class BatchStepConfig {
	
	@Value("${batch.chunk.size}")
	private String batchChunkSize;
	
	@Autowired
	private UpdateItemReader1 updateItemReader1;
	
	@Autowired
	private UpdatetemProcessor updateItemProcessor;
	
	@Autowired
	private UpdateItemWriter updateItemWriter;
	
	@Autowired
	private InsertItemReader insertItemReader;
	
	@Autowired
	private InsertItemReadListener insertItemReadListener;
	
	@Autowired
	private InsertItemProcessor insertItemProcessor;
	
	@Autowired
	private InsertItemProcessListener insertItemProcessListener;
	
	@Autowired
	private InsertItemWriter insertItemWriter;
	
	@Autowired
	private InsertItemWriteListener insertItemWriteListener;
	
	@Bean("updateStep1")
	public Step updateStep1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		Step step1 = new StepBuilder("updateStep1", jobRepository)
				.chunk(Integer.parseInt(batchChunkSize), transactionManager)
				.faultTolerant()
				.skip(Exception.class)
				.skipLimit(9999999)
//				.listener(sfdcOpportunitySkipListener)
				.reader(updateItemReader1)
				.processor(updateItemProcessor)
				.writer(updateItemWriter)
				.build();
		
		return step1;
	}
	
	@Bean("insertStep2")
	public Step insertStep2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder("insertStep2", jobRepository)
				.chunk(Integer.parseInt(batchChunkSize), transactionManager)
				.reader(insertItemReader)
				.listener(insertItemReadListener)
				.processor(insertItemProcessor)
				.listener(insertItemProcessListener)
				.writer(insertItemWriter)
				.listener(insertItemWriteListener)
				.build();
	}

}
