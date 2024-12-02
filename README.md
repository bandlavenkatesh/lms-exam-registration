# LMS Exam Registration API

A RESTful API built using Spring Boot for managing exam registrations in a Learning Management System (LMS).

## Features
- CRUD operations for Students, Subjects, and Exams.
- Students can register for exams only if they are enrolled in the corresponding subject.
- Handles exceptions gracefully with meaningful HTTP error codes.

## Endpoints

### **Students**
- `POST /api/students` - Add a new student.
- `GET /api/students/{id}` - Get a student by ID.
- `GET /api/students` - Get all students.
- `DELETE /api/students/{id}` - Delete a student by ID.
- `POST /api/students/{studentId}/enroll/{subjectId}` - Enroll a student in a subject.

### **Subjects**
- `POST /api/subjects` - Add a new subject.
- `GET /api/subjects/{id}` - Get a subject by ID.
- `GET /api/subjects` - Get all subjects.
- `DELETE /api/subjects/{id}` - Delete a subject by ID.

### **Exams**
- `POST /api/exams/{subjectId}` - Create a new exam for a subject.
- `GET /api/exams/{id}` - Get an exam by ID.
- `GET /api/exams` - Get all exams.
- `DELETE /api/exams/{id}` - Delete an exam by ID.
- `POST /api/exams/{examId}/register/{studentId}` - Register a student for an exam.

## Setup and Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/lms-exam-registration.git
   cd lms-exam-registration
