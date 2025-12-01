package com.embarkx.firstjobapp.job.impl;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceimpl implements JobService {
    private List<Job> jobs = new ArrayList<>();
    @Override
    public List<Job> findAll() {
        return jobs;
    }
    private boolean idAlreadyExists(Long id){
        for(Job j : jobs){
            if(j.getId() != null && j.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    private Long generateNexId(){
        long max = 0;
        for(Job j : jobs){
            if(j.getId()!=null & j.getId() > max){
                max = j.getId();
            }
        }
        return max + 1;
    }


    @Override
    public void createJob(Job job) {
        Long id = job.getId();
        if(id==null || idAlreadyExists(id)){
            Long newId = generateNexId();
            job.setId(newId);
        }
        jobs.add(job);
    }
}
