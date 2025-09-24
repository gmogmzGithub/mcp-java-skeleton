package com.example.mcp.java.skeleton;

import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.McpSyncClient;
import io.modelcontextprotocol.client.transport.HttpClientStreamableHttpTransport;
import io.modelcontextprotocol.spec.McpSchema;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        properties = {"spring.profiles.active=test"})
@EnableAutoConfiguration
class SkeletonMcpDaemonTest {
    @LocalServerPort
    private String port;

    private McpSyncClient mcpClient;

    @BeforeEach
    void setUp() {
        mcpClient = createMcpClient();
    }

    @AfterEach
    void tearDown() {
        mcpClient.closeGracefully();
    }

    @Test
    void shouldStartApplicationContext() {
        // This test will pass if the application context loads successfully.
        // It ensures that all beans are correctly configured and can be instantiated.
    }

    @Test
    void getJobsToolIsPresent() {
        McpSchema.ListToolsResult toolsList = mcpClient.listTools();
        assertThat(toolsList.tools()).hasSize(1);
        assertThat(toolsList.tools().get(0).name()).isEqualTo("getJobs");
        assertThat(toolsList.tools().get(0).description()).isEqualTo("Get jobs list by title and location");
    }

    @Test
    void shouldReturnListOfJobs() {
        CallToolRequest request =
                new CallToolRequest("getJobs", Map.of("title", "Software Engineer", "location", "New York"));
        CallToolResult result = mcpClient.callTool(request);

        TextContent textContent = (TextContent) result.content().get(0);
        // Check that we get a JSON response with job data
        assertThat(textContent.text()).contains("jobKey");
        assertThat(textContent.text()).contains("title");
        assertThat(textContent.text()).contains("location");
        assertThat(textContent.text()).contains("company");
        assertThat(textContent.text()).contains("description");
    }


    private McpSyncClient createMcpClient() {
        McpSyncClient client = McpClient.sync(
                        HttpClientStreamableHttpTransport.builder("http://localhost:" + port + "/mcp")
                                .build())
                .build();
        client.initialize();
        client.ping();
        return client;
    }
}