package com.example.mcp.java.skeleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkeletonMcpDaemon {
    private static final Logger logger = LoggerFactory.getLogger(SkeletonMcpDaemon.class);

    public static void main(final String[] args) {
        logger.warn("⚠️  SSE TRANSPORT DEPRECATION WARNING ⚠️");
        logger.warn(
                "For remote connections, this MCP server uses Server-Sent Events (SSE) transport which has been officially DEPRECATED.");
        logger.warn("SSE connections may drop intermittently and are not suitable for production use.");
        logger.warn(
                "Tracking Spring AI Streamable HTTP support: https://github.com/spring-projects/spring-ai/issues/3145");
        logger.warn("═══════════════════════════════════════════════════════════════════════════════");

        SpringApplication.run(SkeletonMcpDaemon.class, args);
    }
}