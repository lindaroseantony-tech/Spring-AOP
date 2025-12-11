package com.linda.springbootrest.service;

import com.linda.springbootrest.model.JobPost;
import com.linda.springbootrest.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class JobService{
    @Autowired
    private JobRepo jobRepo;

    public JobPost addJob(JobPost jobPost){

        jobRepo.save(jobPost);
        return jobRepo.findById(jobPost.getPostId()).orElse(new JobPost());
    }

    public List<JobPost> getAllJobs(){
        return jobRepo.findAll();
    }

    public JobPost getJob(int jobId) {
        return jobRepo.findById(jobId).orElse(new JobPost());
    }

    public JobPost updateJob(JobPost jobPost) {
        jobRepo.save(jobPost);
        return jobRepo.findById(jobPost.getPostId()).orElse(new JobPost());
    }

    public void deleteJob(int jobId) {
        jobRepo.deleteById(jobId);
    }

    public void load() {
        List<JobPost> jobs = new ArrayList<>();
        jobs.add(new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
                List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")));

        // Frontend Developer Job Post
        jobs.add(
                new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React",
                        3, List.of("HTML", "CSS", "JavaScript", "React")));

        // Data Scientist Job Post
        jobs.add(new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
                List.of("Python", "Machine Learning", "Data Analysis")));

        // Network Engineer Job Post
        jobs.add(new JobPost(4, "Network Engineer",
                "Design and implement computer networks for efficient data communication", 5,
                List.of("Networking", "Cisco", "Routing", "Switching")));

        // Mobile App Developer Job Post
        jobs
                .add(new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android",
                        3, List.of("iOS Development", "Android Development", "Mobile App")));

    jobRepo.saveAll(jobs);
    }

    public List<JobPost> getJobBykeyword(String keyword) {
        return jobRepo.findByPostProfileContainingOrPostDescContaining(keyword,keyword);
    }
}
