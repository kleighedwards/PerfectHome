# Skill Distillery Week 16 Project

## Perfect Home - Creating a Full-Stack Web Application with JPA, REST, and Angular

## Rod Hammond, Elijah Ginter, Kristen Edwards, and Toland Gooch

### Technologies Used

- Back-End
  - mySQL Database
  - JPA Project
  - Spring MVC Dynamic Web Project
- API
  - REST API
  - Google API
  - Zillow API
  - Cloudinary
- Front-End
  - Angular 
  - jQuery
  - HTML
  - CSS/Bootstrap
  
### Project Overview

- Allow the user to search for their “Perfect Home” and add it to their own personal list.
- Once the user has added a home, they may:
  - Jot down their thoughts about the property
    - These notes can help a user keep each home straight. The user may not remember how much they liked the kitchen in the first home they visited when they’re looking at home number 15.
    - The user is also free to edit or delete any of these notes.
  - List any relevant ToDo’s
    - These ToDo’s allow the user to keep track of any appointments they may have, phone calls they need to make, or questions they want to ask. 
    - Once the user has completed a ToDo, they may mark it as complete.
      - They may also delete the ToDo entirely if they choose to.
    - If the user wishes to edit a ToDo, they are free to do so.
  - Upload any photos they’ve taken of the home
    - Before the user has uploaded any images, the program will display whatever images Zillow has available as the default.
    - If Zillow does not have any images of the property available, the program will select its own default image.
  - Rate each home (on a scale of 1 - 5)
    - The user may also update these rating if their feelings change.
- If a user decides a home just isn’t for them, they are free to delete the property from their list.

### Stumbling Blocks

- Working with Zillow’s API was a significant challenge. The information we received was returned to us in XML instead of JSON. We ultimately decided to convert the XML data to JSON manually on the back-end by stepping through each data point to extract what information we needed. The information returned from these API calls was also fairly inconsistent. Many homes would return no information at all, some would return everything we needed, and many fell somewhere in the middle.
- Managing the complex relationships between a home and a user also posed a challenge. We were initially unable to retrieve all of the information we needed from the current user, which made populating certain fields quite difficult. 

### Goals Moving Forward

- We would like to refactor a fair bit of our AngularJS code. All of our data for the application was accessed in some way through the user. This made it difficult for certain components of the application to talk to each other and update when appropriate.

### Access on AWS
