package org.idea.analytics;



import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Application {
    public static void main( String[] args ) {
        String[] configs = new String[] {"database.xml", "infrastructure.xml", "job.xml"};
        ApplicationContext context = new ClassPathXmlApplicationContext(configs);

        JobLauncher launcher = context.getBean(JobLauncher.class);
        Job job = context.getBean(Job.class);

        try {
            JobExecution execution = launcher.run(job, new JobParameters());
            BatchStatus status = execution.getStatus();
            System.out.println("Batch Status "+status);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }

    }
}
