package com.example.demo;

import org.springframework.batch.core.launch.support.RunIdIncrementer;

import org.springframework.batch.core.Job; 
import org.springframework.batch.core.Step; 
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing; 
import org.springframework.batch.core.job.builder.JobBuilder;
 import org.springframework.batch.core.repository.JobRepository;
  import org.springframework.batch.core.step.builder.StepBuilder; 
  import org.springframework.context.annotation.Bean; 
  import org.springframework.context.annotation.Configuration; 
  import org.springframework.transaction.PlatformTransactionManager;

  import javax.sql.DataSource;

import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;

import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration(proxyBeanMethods = false)
public class BatchConfig {

    @Bean
    public Job readProductJob(JobRepository jobRepository, Step step1) {
        System.out.println("start step1");
        return new JobBuilder("readProductJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager txManager, ProductTasklet tasklet) {
        System.out.println("prepare step1");
        return new StepBuilder("step1", jobRepository)
                .tasklet(tasklet, txManager)
                .build();
    }

//     @Bean
// public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager txManager) throws Exception {
//     JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//     factory.setDataSource(dataSource);
//     factory.setTransactionManager(txManager);
//     factory.afterPropertiesSet();
//     return factory.getObject();
// }

// @Bean
// public JobLauncher jobLauncher(JobRepository jobRepository) throws Exception {
//     SimpleJobLauncher launcher = new SimpleJobLauncher();
//     launcher.setJobRepository(jobRepository);
//     launcher.afterPropertiesSet();
//     return launcher;
// }

}
