# LAN-EXAMINATION-SYSTEM
![Architecture Diagram!](docs/LAN_arch_diag.png)

## About LAN EXAMINATION SYSTEM:
LAN EXAMINATION SYSTEM is a secured, scalable, and efficient platform to conduct the online exam in the LAN environment over the pen-paper way.
It is a client-server-based project where the server module as an examiner is responsible for handling activities such as conducting exams, client authentication, and database management.
The client module is developed for the students with a graphical user interface(GUI), which will provide a secured online exam environment for students. It will be responsible for getting questionaries from the server and sending the responses from students back to the server.

## Technologies Used
- Used socket programming to connect server and client modules.
- Multithreading is used in the server module for creating a dedicated server handler instance for each client(student) request.
- The swing application framework is used to provide GUI.

## Intended User
This platform can be used by any organization that wants to organize examinations using LAN(Local area network). For example, this platform can be used for lab exams, university examinations.

## Future Scope:
- The user interface for the server can be added, providing the facility to customize the test.
- This project can be developed further to provide a facility to handle the large load by implementing a Load balancer.
