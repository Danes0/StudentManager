**Student Manager Application**

This JavaFX application, developed using Scene Builder and MySQL, allows users to perform CRUD operations on student data effectively.

**Key Features:**
- **JavaFX UI:** Utilizes FXML and Scene Builder to create a user-friendly interface.
- **Model-View-Controller (MVC) Architecture:** Organized into models, views, and controllers for clear separation of concerns.
- **CRUD Operations:** Enables Create, Read, Update, and Delete operations on student records.
- **MySQL Integration:** Uses JDBC via a dedicated `DBConnectivity` class to ensure seamless connectivity with MySQL database.
- **Data Persistence:** Changes made via CRUD operations are instantly reflected and persisted in the MySQL database.

**Setup:**
1. **JavaFX Project Setup:** Includes packages for models, views, and controllers.
2. **Model Implementation:** Defines a `Student` class with properties like id, name, email, and major.
3. **View Design:** Main UI designed in FXML (`MainView.fxml`) featuring a TableView for displaying student data and input fields for data manipulation.
4. **Controller Logic:** `MainController` class manages UI interaction and implements logic for CRUD operations using PropertyValueFactory for TableView columns.

This project successfully implements a robust system for managing student data, demonstrating proficiency in Java, JavaFX, Scene Builder, and MySQL integration.
