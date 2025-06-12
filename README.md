# DevTask Pro

A professional task management system designed specifically for developers, built as a Full Stack project.

## Features

- **Task Management**
  - Priority-based task organization
  - Due date tracking
  - Task categorization
  - Status tracking
  - Progress visualization

- **Project Organization**
  - Multiple project support
  - Sprint planning
  - Time tracking
  - Team collaboration

- **Developer Tools**
  - Git integration
  - Code snippet storage
  - Technical documentation
  - Team collaboration features

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   ├── com/
│   │   │   └── devtaskpro/
│   │   │       ├── model/
│   │   │       ├── controller/
│   │   │       ├── view/
│   │   │       └── util/
│   └── resources/
└── test/
    └── java/
        └── com/
            └── devtaskpro/
```

## Getting Started

### Prerequisites
- Java 11 or higher
- Maven
- Git

### Installation
1. Clone the repository
2. Build the project: `mvn clean install`
3. Run the application: `mvn exec:java`

## Usage

### Running the Application

1. Compile the project:
   ```sh
   mvn clean compile
   ```
2. Run the application:
   ```sh
   mvn exec:java -Dexec.mainClass="com.devtaskpro.DevTaskPro"
   ```

### Running Tests

To run the test suite:
```sh
mvn test
```

## Contributing
This project is open for contributions. Please read our contributing guidelines before submitting pull requests.

## License
This project is licensed under the MIT License - see the LICENSE file for details. 