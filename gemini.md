
# Diary Application Setup

This document outlines the plan for creating a Java Swing-based diary application with MySQL database integration. The goal is to build a user-friendly application with a clean, modular design, incorporating aesthetic elements like custom colors and image support.

## 1. Project Goals

*   **Core Functionality:** Create, view, edit, and delete diary entries. Each entry should include a date, title, content, and an optional image.
*   **User Interface:** A visually appealing and intuitive GUI using Java Swing.
*   **Data Persistence:** Securely store diary entries in a MySQL database.
*   **Code Structure:** Employ high cohesion by separating concerns into distinct packages: UI, DTO, and Database access.
*   **Aesthetics:** Incorporate a pleasant color scheme and support for image attachments.
*   **Simplicity:** Write clear, maintainable, and easy-to-understand code.

## 2. Technology Stack

*   **Language:** Java
*   **GUI Framework:** Java Swing
*   **Database:** MySQL
*   **Database Connector:** MySQL Connector/J
*   **Styling:** Custom CSS-like styling within Swing components or using a dedicated look-and-feel if appropriate.

## 3. Proposed Project Structure

The project will follow a standard Maven/Gradle-like structure, with packages organized for clarity and maintainability.

```
src/
├── main/
│   ├── java/
│   │   └── com/example/diary/
│   │       ├── ui/             # Swing UI components and views
│   │       │   ├── DiaryFrame.java       # Main application window
│   │       │   ├── EntryPanel.java       # Panel for displaying/editing a single entry
│   │       │   ├── CalendarView.java     # Component to show dates with entries
│   │       │   └── ...                   # Other UI components
│   │       │
│   │       ├── dto/            # Data Transfer Objects (Plain Old Java Objects)
│   │       │   └── DiaryEntry.java       # Represents a single diary entry
│   │       │
│   │       ├── db/             # Database interaction logic
│   │       │   ├── DatabaseManager.java  # Handles connection and basic operations
│   │       │   └── EntryRepository.java  # CRUD operations for DiaryEntry
│   │       │
│   │       └── Main.java       # Application entry point
│   │
│   └── resources/            # Static assets like images, configuration
│       ├── images/
│       │   └── default_diary_icon.png  # Placeholder icon
│       │   └── ...
│       └── config.properties   # Database connection details
│
└── test/
    └── ...                   # Unit and integration tests
```

## 4. Key Design Considerations

### 4.1. Data Transfer Object (DTO) - `DiaryEntry.java`

A simple POJO to hold diary entry data.

```java
package com.example.diary.dto;

import java.time.LocalDate;
import java.util.UUID; // For unique IDs

public class DiaryEntry {
    private UUID id;
    private LocalDate date;
    private String title;
    private String content;
    private String imagePath; // Path to the attached image file

    // Constructor, getters, setters, equals, hashCode, toString
    // ...
}
```

### 4.2. Database Interaction - `DatabaseManager.java` and `EntryRepository.java`

*   **`DatabaseManager.java`:** Will manage the MySQL connection using `java.sql` or a more modern JDBC wrapper. It will read connection details from `config.properties`.
*   **`EntryRepository.java`:** Will abstract the specific SQL queries for creating, reading, updating, and deleting `DiaryEntry` objects.

### 4.2.1. Database Schema

The `diary_entries` table will store all diary entries. It is designed to be straightforward yet accommodate all fields from the `DiaryEntry` DTO.

```sql
CREATE TABLE IF NOT EXISTS diary_entries (
    id VARCHAR(36) PRIMARY KEY, -- Stores the UUID of the diary entry
    entry_date DATE NOT NULL,   -- Date of the diary entry
    title VARCHAR(255),         -- Title of the diary entry
    content TEXT,               -- Full content of the diary entry
    image_path VARCHAR(512)     -- File path to the associated image
);
```

This schema maps to the `DiaryEntry` DTO as follows:
*   `id` (VARCHAR(36)) -> `DiaryEntry.id` (UUID, stored as string)
*   `entry_date` (DATE) -> `DiaryEntry.date` (LocalDate)
*   `title` (VARCHAR(255)) -> `DiaryEntry.title` (String)
*   `content` (TEXT) -> `DiaryEntry.content` (String)
*   `image_path` (VARCHAR(512)) -> `DiaryEntry.imagePath` (String)

This table will be created automatically by the `DatabaseManager` upon first connection if it does not already exist.

### 4.3. UI - Swing Components

*   **`DiaryFrame.java`:** The main window. It will host other panels and manage overall application flow.
*   **`EntryPanel.java`:** A component to display and edit the details of a single diary entry, including a text area for content and a mechanism to select/display an image.
*   **Styling:** We will define a color palette and apply it consistently to backgrounds, text, borders, and buttons. For example:
    *   Primary background: `#F0F4F8` (Light Blue-Gray)
    *   Accent color: `#4CAF50` (Green) for positive actions, or `#2196F3` (Blue) for general accents.
    *   Text color: `#333333` (Dark Gray)
    *   Header/Title color: `#007BFF` (Bright Blue)
    *   Images will be displayed using `ImageIcon` and `JLabel`.

### 4.4. Image Handling

*   Diary entries can optionally have an image associated with them.
*   The `imagePath` in `DiaryEntry` will store the file system path to the image.
*   When displaying an entry, the application will load the image from this path.
*   A button or mechanism will be provided to browse and select an image file to attach.

## 5. Initial Steps

1.  **Create `gemini.md`:** This file serves as the project's foundational documentation. (This step is now complete).
2.  **Set up Project Structure:** Create the necessary directories and the `Main.java` entry point.
3.  **Database Configuration:** Create `config.properties` with placeholder MySQL credentials.
4.  **Implement `DiaryEntry` DTO:** Define the data structure for diary entries.
5.  **Implement `DatabaseManager`:** Set up the MySQL connection logic. Create the diary table if it doesn't exist.
6.  **Implement `EntryRepository`:** Define basic CRUD methods.
7.  **Create Basic UI:** Build the `DiaryFrame` and a simple `EntryPanel` with input fields.
8.  **Integrate UI and DB:** Connect UI actions (e.g., "Save Entry") to repository methods.
9.  **Add Styling:** Define and apply the color palette.
10. **Implement Image Functionality:** Add image selection and display to `EntryPanel`.

This plan provides a clear roadmap for developing the diary application. The focus on modularity and clear separation of concerns will lead to a maintainable and scalable codebase.
