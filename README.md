# BlogXpress

**BlogXpress: Your ultimate platform for seamless blogging, powered by Spring Boot.**

[![GitHub](https://img.shields.io/badge/GitHub-BlogXpress-blue)](https://github.com/alamin-karno/blogxpress)

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Installation](#installation)
- [API Endpoints](#api-endpoints)
  - [User Controller](#user-controller)
  - [Post Controller](#post-controller)
  - [Category Controller](#category-controller)
  - [Comment Controller](#comment-controller)
  - [File Controller](#file-controller)
  - [Auth Controller](#auth-controller)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)

## Introduction

BlogXpress is a powerful and intuitive blogging platform built using Spring Boot. It provides a seamless experience for users to create, manage, and share their blogs with the world. Whether you're a seasoned blogger or just starting, BlogXpress offers all the tools you need to get your voice heard.

## Features

- **User Management**: Create, update, and manage users.
- **Post Management**: Create, update, delete, and search posts.
- **Category Management**: Organize posts into categories for better content management.
- **Comment System**: Engage with your audience through comments on posts.
- **File Upload**: Easily upload images to enhance your posts.
- **Authentication**: Secure registration and login functionality.

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/alamin-karno/blogxpress.git
   cd blogxpress
   ```

2. **Build the project using Maven:**

   ```bash
   mvn clean install
   ```

3. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

4. **Access the application:**

   Open your browser and navigate to `http://localhost:8080`.

## API Endpoints

### User Controller

- **GET** `/api/users/{userId}`: Retrieve a user by ID.
- **PUT** `/api/users/{userId}`: Update a user by ID.
- **DELETE** `/api/users/{userId}`: Delete a user by ID.
- **GET** `/api/users/`: Retrieve all users.
- **POST** `/api/users/`: Create a new user.

### Post Controller

- **GET** `/api/posts/{postId}`: Retrieve a post by ID.
- **PUT** `/api/posts/{postId}`: Update a post by ID.
- **DELETE** `/api/posts/{postId}`: Delete a post by ID.
- **POST** `/api/user/{userId}/category/{categoryId}/posts/`: Create a new post for a user in a specific category.
- **POST** `/api/posts/images/upload/{postId}`: Upload an image for a post.
- **GET** `/api/user/{userId}/posts/`: Retrieve all posts by a user.
- **GET** `/api/posts/`: Retrieve all posts.
- **GET** `/api/posts/search/{keywords}`: Search posts by keywords.
- **GET** `/api/posts/images/{imageName}`: Retrieve a post image by name.
- **GET** `/api/category/{categoryId}/posts/`: Retrieve all posts in a specific category.

### Category Controller

- **GET** `/api/categories/{categoryId}`: Retrieve a category by ID.
- **PUT** `/api/categories/{categoryId}`: Update a category by ID.
- **DELETE** `/api/categories/{categoryId}`: Delete a category by ID.
- **GET** `/api/categories/`: Retrieve all categories.
- **POST** `/api/categories/`: Create a new category.

### Comment Controller

- **POST** `/api/users/{userId}/posts/{postId}/comments`: Add a comment to a post.
- **DELETE** `/api/comments/{commentId}`: Delete a comment by ID.

### File Controller

- **POST** `/api/file/upload`: Upload a file.
- **GET** `/api/file/images/{imageName}`: Retrieve an image by name.

### Auth Controller

- **POST** `/api/auth/register`: Register a new user.
- **POST** `/api/auth/login`: Login a user.

## Technologies Used

- **Spring Boot**: Backend framework
- **Maven**: Dependency management
- **Hibernate**: ORM for database operations
- **MySQL**: Database
- **Thymeleaf**: Server-side rendering (if used)
- **Spring Security**: Security and authentication
- **RESTful APIs**: For client-server communication

## Contributing

Contributions are welcome! Please fork this repository, make your changes, and submit a pull request. For major changes, please open an issue first to discuss what you would like to change.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
