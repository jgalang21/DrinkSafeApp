# DrinkSafe App
This an application that my group and I have created for our software development class. 

The app help prevents drunk driving by using a buddy system to help everyone keep track of who's able to drive. 
It takes in the User's height, weight, and gender, and calculates a time when they are able to drive. I had pushed 
this code from our Gitlab repository to my personal Github. I was responsible for the Spring side of our project. I 
had worked on figuring out how to add/manage drinks with our database along with getting our WebSockets to work. On top of 
that, we were learning how to use git with our project. We developed this app in an Agile environment. We had sprints that
lasted about 2-3 weeks with different requirements we had to meet. These requirements ranged from showing our understanding of git to implementing certain features such as CI/CD.



# DISCLAIMER 
This app only gives an estimate of when a person is sober enough to drive, it may not be fully accurate for everyone. 
Credit to this site for helping us with our calculations: https://www.teamdui.com/bac-widmarks-formula/



# Technologies
* Android Studio - For our client side development
* Spring Tools Suite 4 - Creating our backend logic
* MySQL Workbench - Database 
* Mockito - For testing frontend and backend
* GitLab - Private repository we used for version control 
* Maven - Build automation tool 
* Trello (https://trello.com/en) - Served as our SCRUM board 



# Other Project Features
* CI/CD - This helped us push live updates to our server without having to manually go into our server's file system and changing the .jar file everytime we want to make a change.
* WebSockets - This is the main component of our application. We used websockets as a chat between users along with how
we are checking who is available to drive and other features. It uses a list of commands available to the users to perform actions such as checking who is able to drive, list of people in the party, etc.
