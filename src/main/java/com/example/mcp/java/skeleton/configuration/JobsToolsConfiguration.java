package com.example.mcp.java.skeleton.configuration;

import com.example.mcp.java.skeleton.tools.JobsToolsService;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobsToolsConfiguration {
    @Bean
    public MethodToolCallbackProvider jobTools(final JobsToolsService service) {
        return MethodToolCallbackProvider.builder().toolObjects(service).build();
    }
}