[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/x6ckGcN8)
# 3500 PA05 Project Repo

[PA Write Up](https://markefontenot.notion.site/PA-05-8263d28a81a7473d8372c6579abd6481)


## App Pitch:

Are you struggling to manage your time? Can't remember what you need to accomplish in a week? Have no fear!
The t.checkExpect Bullet Journal is here! Our newly released Bullet Journal supports many exciting features which are 
guaranteed to blow anyone's mind! The specialized features include:

- An entire week's view (7 days) and the ability to choose which day to start on!
- Event / Task Creation: The ability to create a customized event and task on any day of the week!
- Saving your journal to a file and, open it back up!
- Setting the maximum number of events / tasks you want on each days! This can be changed at any time.
- Displaying commitment warnings when you try to go over your set max for events / tasks!
- A task queue on the left displaying all your tasks for the week and their completion status!
- Choosing and customizing categories for each event / task!
- Clicking on any event / task to view it and its summary as well as and having the option to edit or delete it
- Displaying the week's overview, including total events, total tasks, and percentage of tasks completed
- A beautiful and calming green theme!

Download the app today!

## GUI + Features: 

(Please see Preview for image)

Welcome:

![welcome.png](https://github.com/CS-3500-OOD/pa05-t-checkexpect/blob/4bc89db3d2f9a4e92045e215a14189fd9098c75e/WelcomeView.png)

Journal:

![journal.png](https://github.com/CS-3500-OOD/pa05-t-checkexpect/blob/4bc89db3d2f9a4e92045e215a14189fd9098c75e/JournalView.png)

## SOLID Principles:

**Single Responsibility**: At a high level, our classes are divided into 3 sections: model, view, and controller. Within the
model, we separated classes based on what data they hold. Each class a single purpose of keep tracking of a particular 
kind of data. For instance, WeekDaysModel keeps track of the data of each Weekday and similarly Settings holds all of 
Settings data. Moving on, we have two controllers. BujoController has the sole purpose of controlling and setting actions
for elements on the actual bullet journal. Then, WelcomeController controls all the actions and user input for our welcome 
screen. Finally, our view is separated into multiple different classes, each one representing a specific part of the view,
each class has a single responsibility for creating the view for one to two elements. Eventually, all the parts are put 
together in the BujoView. 

**Open for Extension but Closed for Modification**: Our program makes of use of many interfaces and an abstract class. In 
doing so, it always use to add functionality. For instance, we created an abstract class for the types of things to add
to the journal: events and tasks. With the abstract class, we would easily be able to add more types of things to add 
in addition to events and tasks by extending the abstract. Connecting this to the view, ActivitySelectionView takes in 
an Activity, and thus can handle and display anything that extends the Activity abstract class. Similarly, the 
interfaces, such as the controller interface, allows to easily add more controllers to control possibly other screens /
features simply by creating more classes to implement the interface. The model interface is similar in the fact that
different types of journals could be added by implementing the model interface. One more example is our TableView 
in the view. Any class in the future could implement that interface to create a customizable table. 

**Liskov Substitution Principle**: Our program makes use of one abstract class, Activity, and has two subclasses, 
Event and Task. Within our design, an Activity can be replaced by either an Event or a Task. An Event and Task simply
have more functionality than a standard Activity. At a high level, an event IS A activity and a task IS an activity,
making them able to replace any Activity superclass. 

**Interface Segregation Principle**: Our interfaces are small, meaningful and only contain relavent methods.
Each class that implements an interface intentionally uses the methods in the interface. For instance, the controller
interface only contains the showPopUp() method, since this is functionally that ALL controllers should have  - no 
customizable methods from WelcomeController or BujoController are included in the interface. The model interface follows
this same pattern. Similarly, our interfaces for the view are separated into: FormDelegate, TableView, TableViewDelegate
and FormView. Each have a particular purpose when displaying a particular part of the view. The TableView, for instance, 
allows functionality for ONLY implementing a table and classes that implement this interface to make a table view 
need all those methods specifically for the table. Not all view classes need to implement the TableView because not all
views needed a table, hence why it was made into its own interface.

**Dependency Inversion Principle**: The model - WeekdaysModel - is passed around a bit between the BujoMainStage and the 
controller. However, it used the interface - Model - when injected into another class. Similarly, with the FormView,
an interface needed when submitting a 'form', (settings creation / event + task creation) is also injected into 
multiple classes, such as the submit method in the FormDelegate interface. But, multiple classes implement the FormView
interface.

## Extend w/ New Feature:

**Filter by category**: To implement filter by category, the model's getActivity() method would take in a category. If
the category is null, then return the entire hashmap of days to list of activities. However, if not, the method
would go through the entire hashmap of days mapped to List of activities and return a Map<DayOfWeek, List<Activity>>
with ONLY the activities of the specified catergory. This is actually already implemented in this manner. 
The GUI would have a button / drop down which the user could pick the specific category they would like. A class would 
be implemented for this view feature. In the controller, the event handling for this button would be set in a method & 
would have the getActivities(Category cat) method be called with the chosen category. Then, the WeekDaysView would take 
in this new hashmap with only the specified categories and parse it to display the events / tasks given. The reloadAll() 
takes care of the user continually changing the category and the view will update to reflect what they choose.
Currently, the model and controller functionality exist for this, the view and button handling would have to be 
integrated in. 

## Attributions:

Settings.png and Save.png were used for the Save and Settings button icons. 

Thanks for choosing t.checkExpect <3!
