package com.example.mcp.java.skeleton.tools;

import com.example.mcp.java.skeleton.tools.dto.JobDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JobsToolsServiceTest {
    private JobsToolsService service;

    @BeforeEach
    void setup() {
        service = new JobsToolsService();
    }

    @Test
    void shouldReturnListOfJobs() {
        List<JobDto> jobs = service.getJobs("Software Engineer", "New York");

        assertThat(jobs).isNotEmpty();
        assertThat(jobs).allSatisfy(job -> {
            assertThat(job.title()).startsWith("Software Engineer");
            assertThat(job.location()).isEqualTo("New York");
            assertThat(job.company()).isIn("Example Company", "Sample Corp", "Demo Industries");
            assertThat(job.description()).startsWith("This is a sample job posting");
        });
    }
}