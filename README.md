# MCP Server Template - Java (Spring AI)

A Model Context Protocol (MCP) server implementation in Java using [Spring AI](https://github.com/spring-projects/spring-ai).

## About

This is a minimal MCP server skeleton that demonstrates how to build MCP servers using Java and Spring AI. It provides a basic tool implementation that you can use as a starting point for your own MCP server.

## Features

- **Tools**: Example job search tool that returns mock job data
- **Spring Boot Integration**: Built on Spring Boot for easy configuration and deployment
- **Maven Support**: Uses Maven for dependency management and building
- **Minimal Setup**: Only includes essential components needed to demonstrate MCP functionality

## ⚠️ Important SSE Warning

**SSE Transport Limitation**: This server currently uses Server-Sent Events (SSE) transport because Spring AI does not yet support Streamable HTTP. SSE has known reliability issues:

- **Connection drops**: SSE connections may drop after several minutes of activity
- **Intermittent connectivity**: You may experience periodic disconnections requiring manual reconnection
- **Deprecated protocol**: SSE has been officially deprecated in favor of Streamable HTTP

**Spring AI 1.1.0** (planned release: September 30th, 2025) will include support for Streamable HTTP. This template will be updated once that version is available.

**Recommendation**: For production use, consider using Python or TypeScript MCP templates which support Streamable-HTTP and do not have these reliability issues.

Follow https://github.com/spring-projects/spring-ai/issues/3145 for Spring AI Streamable-HTTP support updates.

## Prerequisites

- Java 17 or higher
- Maven 3.6+ (or use the included Maven wrapper)

## Quick Start

### Using Maven Wrapper (Recommended)

```bash
./mvnw clean compile
./mvnw spring-boot:run
```

### Using System Maven

```bash
mvn clean compile
mvn spring-boot:run
```

## Configuration

### Application Properties

You can configure the application by creating `application-local.yml` in `src/main/resources/` for local development:

```yaml
# Add your configuration here
logging:
  level:
    com.example.mcp: DEBUG
```

## Usage Examples

### Running with MCP Inspector

1. Start the server:
   ```bash
   ./mvnw spring-boot:run
   ```

2. Run MCP Inspector:
   ```bash
   npx @modelcontextprotocol/inspector
   ```

3. Configure the inspector:
   - Set `Transport Type` to `Streamable HTTP`
   - Set `URL` to `http://localhost:8080/mcp`
   - Click `Connect`

4. Test the available tools from the inspector interface

### Connecting from VS Code

1. Add this configuration to your Cursor Code `mcp.json`:
   ```json
   {
      "mcpServers": {
         "java-mcp": {
            "url": "http://localhost:8082/mcp"
         }
      }
   }
   ```

2. Build the JAR file first:
   ```bash
   ./mvnw clean package
   ```

3. Update the path in `settings.json` to point to your JAR file in `target/mcp-java-skeleton-1.0.0.jar`

4. Start the server in VS Code and test with GitHub Copilot Chat:
   ```text
   Show me a few Software Engineer jobs in Austin
   ```

## Development

### Project Structure

```
src/
├── main/java/com/example/mcp/java/skeleton/
│   ├── SkeletonMcpDaemon.java          # Main application class
│   ├── tools/
│   │   ├── JobsToolsService.java       # Tool implementations
│   │   └── dto/JobDto.java            # Data transfer objects
│   └── configuration/
│       ├── JobsToolsConfiguration.java # Tool configuration
│       ├── PromptConfiguration.java   # Prompt configuration
│       └── ResourceConfiguration.java # Resource configuration
└── test/                              # Unit tests
```

### Adding New Tools

1. Create a service class in the `tools` package
2. Annotate methods with `@Tool` to expose them as MCP tools
3. Register the service in a configuration class

Example:
```java
@Service
public class MyToolsService {
    @Tool(description = "My custom tool")
    public String myTool(String input) {
        return "Result: " + input;
    }
}
```

### Adding Resources

1. Create a new configuration class similar to `JobsToolsConfiguration`
2. Add `SyncResourceSpecification` beans
3. Implement resource handlers

### Adding Prompts

1. Create a new configuration class for prompts
2. Add `SyncPromptSpecification` beans
3. Implement prompt handlers

### Running Tests

```bash
./mvnw test
```

## Building for Production

### Create JAR file

```bash
./mvnw clean package
```

The executable JAR will be created in `target/mcp-java-skeleton-1.0.0.jar`.

### Run the JAR

```bash
java -jar target/mcp-java-skeleton-1.0.0.jar
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

