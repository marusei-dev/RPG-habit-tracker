# RPG Habit Tracker

**Author:** Marsel Yuldashev (DUZJ06)

---

## Task Description

RPG Habit Tracker is a GUI application that enables the user to “upgrade” their profile across many attributes such as strength, constitution, agility, intellect, felicity of phrase, quick wit, and imagination, among others, by completing tasks and following habits.

---

## Features

- **Tasks:**
  - The user has full control over the nature of the tasks, their deadlines (if any), which attribute(s) their completion affects, and their status (completed or not).
  - Upon completion, the chosen attribute(s) will be adjusted accordingly.

- **Habits:**
  - The user has full control over the nature of the habits and which attribute(s) are affected by following them.
  - Since habits are of repeating nature, they cannot be fully completed, and the user may mark that they “followed” a particular habit by clicking on the relevant button. The related attribute will be adjusted right away.

- **Localization:**
  - The language of the application can be set by the user. Planned language support includes English, Russian, and Mongolian.
  - The attributes created by the user will be displayed in the language they were created, with no translation provided.

- **Profiles:**
  - The user may create or delete an existing profile.
  - Editing the attributes themselves is forbidden; they can be changed only by fulfilling tasks or following habits.

- **Attribute Progress:**
  - The user can overview their progress against time, attribute by attribute.

---

## GUI Design

- The entire application window space will be divided into:
  1. **Side Menu:**
     - Contains icons for navigation.
     - Includes four sections:
       - Tasks
       - Habits
       - Development
       - Settings
  2. **Main Content Area:**
     - Dynamically changes based on the selected side menu item.

---

## Use-Case Diagram and Description of Use-Cases

### Use Cases:
1. **Select Interface Language:**  
   - The user is able to select the interface language (EN – RU – MN) from the settings.

2. **Create Profile:**  
   - The user is able to create and delete profiles. A profile is a particular save of the user’s progress.

3. **Create Task:**  
   - The user is able to create, delete, set up deadlines, or edit/remove deadlines for tasks.
   - The user can also mark tasks as completed.

4. **Create Habit:**  
   - The user is able to create, delete, edit, and follow habits.

5. **Observe Attribute Development Over Time:**  
   - The user is able to observe visual elements demonstrating the progress of the development of the user’s profile’s attributes.
   - The user can also select a particular attribute to observe development, effectively filtering the visuals to show the progress of a chosen attribute.

6. **Highlight Tasks with Approaching and Missed Deadlines:**  
   - The system highlights tasks that are not completed within the predefined time or are about to expire, in a certain colour.

---

## Task Solution Plan

- **Technology:**  
  - Java Swing will be used for developing the graphical user interface of the application.

- **Interface Layout:**
  - **Side Menu:** Small menu with icons representing "Tasks", "Habits", "Development", and "Settings".
  - **Main Content Area:** Displays the relevant content dynamically based on the menu selection.
