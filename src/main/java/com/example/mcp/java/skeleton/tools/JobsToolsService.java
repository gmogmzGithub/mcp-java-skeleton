package com.example.mcp.java.skeleton.tools;

import com.example.mcp.java.skeleton.tools.dto.JobDto;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobsToolsService {
    /**
     * This is an example of how one might produce a response to a tools request.
     */
    @Tool(description = "Get jobs list by title and location")
    public List<JobDto> getJobs(final String title, final String location) {
        return List.of(
                new JobDto(
                        "key-" + title + "-" + location + "-0",
                        title + " 0",
                        location,
                        "Example Company",
                        "This is a sample job posting. This is job 0: " + title + " in " + location),
                new JobDto(
                        "key-" + title + "-" + location + "-1",
                        title + " 1",
                        location,
                        "Sample Corp",
                        "This is a sample job posting. This is job 1: " + title + " in " + location),
                new JobDto(
                        "key-" + title + "-" + location + "-2",
                        title + " 2",
                        location,
                        "Demo Industries",
                        "This is a sample job posting. This is job 2: " + title + " in " + location));
    }
}