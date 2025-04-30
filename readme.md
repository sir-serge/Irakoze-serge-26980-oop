# OOP Java Project Suite

## Author & Credits
**Irakoze Serge**  
**Student ID: s6980**

## 📦 Project Overview
This repository contains a collection of Java projects that demonstrate Object-Oriented Programming (OOP) principles. Each project is designed to showcase different aspects of OOP, including encapsulation, inheritance, polymorphism, and abstraction.

The projects are built using Java and are containerized with Docker for easy deployment and testing. The main goal of these projects is to provide practical examples of OOP concepts in action.

### Projects Included
- **StockManagement**: Manage warehouse inventory, suppliers, and stock items interactively.
- **OnlineShoppingSystem**: Simulate an e-commerce experience with cart, checkout, and multiple product categories.
- **InsuranceSystem**: Manage vehicle insurance policies, file claims, and generate reports.

## 🛠️ Quick Start: Clone, Build, and Run Each Project

### 1. Clone the Repository
```sh
git clone https://github.com/sir-serge/irakoze-serge-oop.git
cd irakaze-serge-oop
```

### 2. Build a Project's Docker Image
Replace `<project-directory>` and `<image-name>` as needed:
```sh
cd <project-directory>
docker build -t <image-name> .
```

#### Examples:
- StockManagement: `cd StockManagement && docker build -t stock-management .`
- OnlineShoppingSystem: `cd OnlineShoppingSystem && docker build -t online-shopping-system .`
- InsuranceSystem: `cd InsuranceSystem && docker build -t insurance-system .`

### 3. Run the Project's Docker Container
```sh
docker run --rm -it <image-name>
```

#### Examples:
- `docker run --rm -it stock-management`
- `docker run --rm -it online-shopping-system`
- `docker run --rm -it insurance-system`

## 🐳 How to Pull and Run Projects from Docker Hub
Follow these steps to pull and run the projects directly from Docker Hub without building them locally.

### Prerequisites
- Docker installed on your machine
- Basic knowledge of Docker commands
- Internet connection to pull the images from Docker Hub
- Ensure Docker is running on your machine

### 1. Pull the Image
Choose the project you want and run the corresponding command:

#### Stock Management
```sh
docker pull sergeIrakoze/stock-management
```

#### Online Shopping System
```sh
docker pull sergeIrakoze/online-shopping-system
```

#### Insurance System
```sh
docker pull sergeIrakoze/insurance-system
```

### 2. Run the Project
After pulling, run the project with:

#### Stock Management
```sh
docker run --rm -it sergeIrakoze/stock-management
```

#### Online Shopping System
```sh
docker run --rm -it sergeIrakoze/online-shopping-system
```

#### Insurance System
```sh
docker run --rm -it sergeIrakoze/insurance-system
```

## 🔗 Docker Hub Links
- [Stock Management](https://hub.docker.com/r/sergeIrakoze/stock-management)
- [Online Shopping System](https://hub.docker.com/r/sergeIrakoze/online-shopping-system)
- [Insurance System](https://hub.docker.com/r/sergeIrakoze/insurance-system)

## 📚 Project Structure
Each project follows a consistent structure:
```
project-name/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── serge/
│   │               └── projectname/
│   │                   ├── models/
│   │                   ├── services/
│   │                   ├── utils/
│   │                   └── Main.java
├── Dockerfile
└── README.md
```

## 🤝 Contributing
Contributions are welcome! Please feel free to submit a Pull Request.

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details. 
