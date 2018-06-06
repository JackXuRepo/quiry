# Summary
--------------------

The objective of this project is to create a search engine for the UTSC community. This web-based search engine will return results from a database of files uploaded by other students and instructors from UTSC. There are many existing search engines that are very efficient at searching webpages, there are also many websites that allow users to share notes or publish academic papers (with a cost). Our goal is to combine features of these existing products, that meets (or exceeds) our client's requirements, and is easy to use for our target users in the UTSC community.

Target users are in three categories: instructors, students, and guests.
We created a persona for each category with different goals and backgrounds. We expect different behavior from these users while using our product; it may be for research, study, or hobby.

* Our instructor persona is Professor Charles Xavier. Dr. Xavier is a tenured Psychology professor currently teaching two psychology courses in all University of Toronto Campuses. On his spare time, he conducts research and writes a book on Artificial Intelligence and the Human Mind.
* Our student persona is Jane Smith, a 22-year-old university student studying Management. Her method of studying for tests is by completing past exams to get an understanding of the type of questions she might encounter.
* Our guest persona is Hakim Boyd, a high school senior and a prospective university student. He is excited about the transition to college, interested in learning about courses and student life at University of Toronto. He enjoys Biology and Chemistry and wants to go into the medical field in the future.

We also split our user stories into four categories, based on functionality.

* User stories related to search concern presenting the results when a user enters a query. This is the highest priority.
* User stories related to upload can be addressed with two actions, storing the file on the server, and indexing the file into the database.
* User stories related to UI/UX generally improve user experience, including better graphics design and user documentation.
* User stories related to additional functionality is the lowest priority. These are considered nice-to-have features and will be added after basic search and upload features have been implemented.

The key principle driving our development is relevancy of the results. We strive to implement provide users with more relevant results even if it incurs an expense or inefficiency in the search algorithm. Such algorithm may determine the order results are presented, not only based on the documents themselves (such as the number of occurrences of a key word), but also with context of who is performing the search and what this user may be interested in (such as courses this user is currently taking/teaching).
