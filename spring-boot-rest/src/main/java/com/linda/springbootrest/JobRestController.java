package com.linda.springbootrest;

import com.linda.springbootrest.model.JobPost;
import com.linda.springbootrest.repo.JobRepo;
import com.linda.springbootrest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class JobRestController {

//    private final JobRepo jobRepo;
//
//    // Constructor injection
//    public JobRestController(JobRepo jobRepo) {
//        this.jobRepo = jobRepo;
//    }



    @Autowired
    private JobService jobService;
    @GetMapping(path="jobposts",produces={"application/json"})
    @ResponseBody
    public List<JobPost> getAllJobs(){
        return jobService.getAllJobs();
    }
    @GetMapping("jobpost/{jobId}")
    public JobPost getjob(@PathVariable("jobId") int jobId){
        return jobService.getJob(jobId);
    }
    @PostMapping("jobpost")
    public void addjob(@RequestBody JobPost jobPost){
        jobService.addJob(jobPost);
    }
    @PutMapping("jobpost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        jobService.updateJob(jobPost);
        return jobService.getJob(jobPost.getPostId());
    }
    @DeleteMapping("jobpost/{jobId}")
    public void deleteJob(@PathVariable int jobId){
        jobService.deleteJob(jobId);
        System.out.println("Job "+jobId+" deleted");
    }
    @GetMapping("load")
    public String loadData(){
        jobService.load();
        return "success";
    }
    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
        return jobService.getJobBykeyword(keyword);
    }
}
