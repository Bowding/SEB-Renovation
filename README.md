# SEB-Renovation
This repo is for UNNC SEB Renovation project. The aim is to develop a demo program for the intelligent terminals which is to be installed in the Science and Engineering Building. The demo system is written in Java using Android platform, and is expected to achieve the following objectives.

Two main states:
From a general perspective, the system will have two main states: quiescent state and active state. When in the quiescent state, the system will display publicity materials on the Faculty the Science and Engineering, as well as entries for different functionalities. Once user selects a functionality, the system enters active state. In the active state, the system process user's query and give response in terms of the query. The system is brought to quiescent state if user has chosen to or no operation has been performed on the machine for 3 minutes.

Two user mode:
The acquiescent mode for the system is Visitor mode, which can only access to a set of basic functionalities. University students and staffs can login and switch to Student and Staff (SnS) mode in the quiescent state using their university ID card. Under SnS mode, user is permitted to access to more advanced functionalities that related to university services. 

Two system language:
The system will be able to provide two system languages: Chinese and English. Out of usability, user is allowed to switch language through the whole process of using the system.

Basic functionalities:
- In-building navigation: 
In this functional module, user is allowed to inquire the shortest feasible route to one destination within the building. The system will be able to identify four categories of inputs, and response properly in terms of the input type. Please refer to Appendix 1 for detailed input-output table. The route display on the machine can be sent to user by email or text. In any emergency situation the system should display an arrow leading to the nearest emergency exit (if it is not power off).

- Introduction on school and departments: 
The system will be able to provide brief introductions on the Faculty of Science and Engineering and departments within the Faculty of Science and Engineering.

-	Introduction on laboratories within the building: 
User is allowed to view introductions for different laboratories in the Science and Engineering Building. The introductions will include aspects such as functionality, equipments, the man in charge of the laboratory, and the group of people who has access to the laboratory. Additional to the main menu in the quiescent state, this functionality can also be accessed through in-building navigation.

-	Introduction on professors based in the building: 
User is allowed to view introductions for academic staffs based in the Science and Engineering Building. The introductions will include aspects such as biography, qualifications and honours, and conducted researches or proposed projects. Additional to the main menu in the quiescent state, this functionality can also be accessed through in-building navigation.

Advanced functionalities:
-	Enquiry of classroom and laboratory timetable: 
 Under SnS mode, user can inquire timetables for classrooms and laboratories in the building using a unique room number.

-	Enquiry of vacancy number in terminal rooms within the building: 
SnS user can inquire the number of unoccupied computers in different terminal rooms within the building. 

-	Classroom and laboratory reservation: 
Only authorised SnS users can have access to this functionality. Those users is allowed to view opening periods of classrooms or laboratories and make room reservation on the machine.

Non-functional (performance) requirements:
-	The system should be easy to use and intuitive.
-	The response time should be no longer than that of a regular search engine.
-	When performing complex tasks the system should not be remained irresponsive. A loading bar or equivalent should be displayed.

