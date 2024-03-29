# Zephyr README/Documentation

## Project Contents
All necessary and related files for the Zephyr Project are located within the “Zephyr Capstone Project” folder.

- zephyr-frontend/: The “zephyr-frontend” folder contains all the source code, inside “src”, for the front-end actions and functionality, as well as all the dependencies and modules necessary for the front-end to operate (within the “node-modules” folder). The “public” folder contains the files for displaying images and icons on the front-end.

- zephyr/: The “zephyr” folder contains all source code files and dependencies necessary for the backend of the project to start and execute actions. The source code files can be found in “src”, which contain “main/java/capstone/zephyr/zephyr” and the packages that the project uses, namely “api”, “dao”, “model”, “requests”, and “security”, as well as the start file for the application “ZephyrApplication”.

- scripts/: This folder contains scripts such as the database creation script, and Windows/Linix backend and frontend startup scripts. 

## Executive Summary

Shareholder voting is an essential part of a corporate or small business operation, and in-person voting is a common method used to conduct these votes. However, online voting has become more commonplace, and the importance of having options that are not in-person has increased due to COVID-19. In addition, online voting can be more economical and easier to aggregate. At Zephyr, we have attempted to tackle the aspect of privacy in the area of shareholder voting by developing an application for shareholders to securely and anonymously vote on the board of directors, approving dividends, issuing securities, or voting on mergers and acquisitions. The goal is for shareholders to be unidentifiable and unable to be tracked by their vote while also being blind to the vote tallies until all votes have been cast . We want to ensure that shareholders feel comfortable by enabling them to remain anonymous while voting which should help prevent any unintended bias or potential backlash over who they chose to vote on. With the use of a third party website, votes can remain secure and be tallied outside the purview of the company, overall ensuring voting integrity.

## Final Requirements and Comparison With Initial Requirements

For our application’s requirements, we have completed every requirement set forth in our initial Project Plan. Shareholders, the “users”, can vote on polls created by the company admins as long as they are still open, and the shareholders have votes remaining. User and administrator accounts are authenticated and securely stored in a database but at no point is a vote attributed to a shareholder or stored in the database, the vote itself is tallied but not connected to the shareholder. The frontend allows the users to vote on open polls, view results of concluded polls, and allows Administrators to create new polls for their company as well. Administrators can close these polls whenever deemed necessary, as well as importing user accounts via CSV files which is an extra feature we implemented. Our site is even mobile friendly to a degree which was honestly done by accident.

## Final Timeline and Comparison With Initial Timeline

Our timeline stayed rather on track for a good portion of the project, only deviating slightly towards the end due to other classes and life being demanding. Overall, we managed to complete the tasks we wanted to complete regarding privacy, security, and voting on time. Our initial timeline had us a little further along and we also planned out extra features to implement if we had time, but we didn’t and that’s to be expected. With that said, every requirement we set forth in our Project Plan was completed, tested, and implemented; we even had time to finish a few of the “extras” we had originally planned.

## Project Results Compared With Expectations

Our project ended up rather successful, but with less features than we originally planned. The important aspect of the project, privacy, was captured and implemented all throughout the project. We made sure to keep user information and voting information as secure as possible, or as unrelated to anything else so that even if a compromise happened to the database, the information would essentially be meaningless to the attacker. The aspect of voting was rather simple, and the interface of the front-end with which an administrator creates polls and a user votes is elegant and clean. The difficult part was tying the front-end to the back-end securely so that information regarding users and votes were safe from tampering or collection. We think that more could have been done to polish up the project and add more engaging features if there was more time. We would have loved to put more work into the style of the site so it didn’t look so bland.

## Project Process Review

For this project, our team collectively chose to use the Agile Software Development model, albeit slightly modified to fit our smaller team size. The characteristics of this model allowed us to tackle the project with an iterative approach that gave way for us to have a somewhat deliverable product after each Sprint. Considering the size of our group, every member had more than one duty associated with them within the Agile Method, however, the roles themselves are not any different from a traditional Agile Method. All in all, this worked out well for our team as we met 2 or more times a week which allowed us to conduct our Sprint Planning and later our Sprint Review. Going into every week, we had a relatively good understanding of what we intended to accomplish for that week's Sprint and could collectively meet afterwards to fix any errors, bugs, or merge conflicts. This method helped us achieve the goal of the project within the means and possibilities of each group member’s skills and also schedules.

## Work to Be Done

- Allowing the Administrator to change password after default creation.
- Requiring Users to change default password upon first login.
- Frontend error handling and reporting.
- Implement a log system.
- Polish Frontend and add more features for users

## Manual

| Pictures | Description |
| ------ | ------ |
| ![1](https://user-images.githubusercontent.com/42798640/116766950-cb203f00-a9f2-11eb-83dd-81da20df1428.png) | As an administrator, you can login to the Zephyr site using the administrator’s username and password. After being logged in, the administrator will be able to create polls for the company’s shareholders to vote on. Once the poll has been created, the shareholders will be able to view and vote on it. Administrators can also close polls once voting has concluded. Using the default administrator name (“admin”) and password (“1admin”). |
| ![2](https://user-images.githubusercontent.com/42798640/116766963-e723e080-a9f2-11eb-963d-14d89a5d2a55.png) | Once logged into the administrator’s dashboard, you will be able to create polls, import users via CSV files (a file explorer window will open and you choose the file to upload, must be CSV and the format must be correct in the form of “username, company_name, number of shares, password”), close current polls, and view results of closed polls. |
| ![3](https://user-images.githubusercontent.com/42798640/116766970-ed19c180-a9f2-11eb-8092-47352db05db2.png) | Creating a poll requires the name of the poll, the company name, and the poll choices that users will vote on. You can add extra choices using the “+” button, and remove choices using the “x” button. A max of 10 choices can be in a poll. |
| ![4](https://user-images.githubusercontent.com/42798640/116766975-f3a83900-a9f2-11eb-8413-7b42a31329a1.png) | The new poll appears here. It can be closed by the administrator and the results can be viewed afterwards. You will receive a prompt to close the poll before doing so. |
| ![5](https://user-images.githubusercontent.com/42798640/116767026-408c0f80-a9f3-11eb-93c4-c4b202ae2d83.png) | No votes were cast in this example. |
| ![6](https://user-images.githubusercontent.com/42798640/116767022-3a962e80-a9f3-11eb-9336-44ab6687a8ca.png) | As a user, you can login to the Zephyr site using the user’s username and password the company has provided for you. Once logged in, the user will be able to view polls and vote on them if they are still open. Any polls that have been completed will display the voting results of that poll. Login using username and password. If the username and/or password is incorrect, you will receive a login failure. |
| ![7](https://user-images.githubusercontent.com/42798640/116767036-4e419500-a9f3-11eb-8619-a79eed296b32.png) | Once logged in, you will be greeted by the website, and be on the poll dashboard page. Here can you see Open Polls to vote on, Open Polls that you have already voted on (you can’t view the results), and Closed Polls where you can view the results. |
| ![8](https://user-images.githubusercontent.com/42798640/116767045-5699d000-a9f3-11eb-9251-9af4bd8154f3.png) | Select a poll choice (you can only choose 1), and you will cast all of your shareholder votes to that choice. |
| ![11](https://user-images.githubusercontent.com/42798640/116767188-2e5ea100-a9f4-11eb-9b06-fe52a9b0d81e.png) ![10](https://user-images.githubusercontent.com/42798640/116767124-abd5e180-a9f3-11eb-8d9e-0ebbbebcc52f.png) | Viewing a closed poll, you will be presented with a pie graph of all choices in the polls and the amount of votes they received. Hovering over a section shows the exact number of votes cast. (This is the result of the “Test poll”, as seen in the Closed Poll section). |

## Deployment/Installation Instructions

For deployment and use of the project, the database, frontend, and backend are remotely hosted, and a demo can easily be found by going to the following web address:

    https://zephyr.refi64.com

In order to run locally, you need:

- Node 12
- JDK 15
  - On Windows, JAVA_HOME *must* be set to the path to your JDK in order for `mvnw` to work.
    This is not required on most Linux distributions, but in that case, the `java` executable
    itself should be on your system PATH.
- A running MySQL database

### Setting up the database

Run the `Scripts/DatabaseCreationScript.sql` against the MySQL database to set up the initial database
tables. In addition, the database URL and credentials must be placed inside
`zephyr/src/main/secret.properties`; see `secret.ex.properties` in the same folder as an example.

Note that the `secret.properties` to access our hosted database has been provided in the Google Drive
submission.

### Configuring the backend

By default, the backend assumes that the frontend will be on `http://localhost:3000` (which will be
the case if following the directions below). If this should be changed, edit
`zephyr/src/main/application.properties`, setting `app.cors.allowed-origins` to the correct frontend URL.
Note that this URL should not contain any trailing slashes.

### Running the backend

To run the backend, `cd` into the `zephyr` folder and run `mvnw package -Dmaven.test.skip=true`. This will create
a .jar file named `zephyr-0.0.1-SNAPSHOT.jar` in the `target` folder. The .jar file can now be
run using `java -jar target/zephyr-0.0.1-SNAPSHOT.jar`.

#### Windows and Linux premade startup scripts

Premade scripts have been provided to start the backend server on Windows and Linux. Simply run
`startBackend.bat` or `./startBackend.sh` from the `Scripts` folder.

### Configuring the frontend

By default, the frontend assumes that the backend is running on `http://localhost:8080` (which will
be the case if following the directions above). If this should be changed, edit
`zephyr-frontend/.env`, setting `REACT_APP_SERVER` to the base URL of the server. Note that this URL
should not contain any trailing slashes.

### Running the frontend

To run the frontend, `cd` into the `zephyr-frontend` folder and run `npm install` to download the
dependencies. Once this has completed, `npm start` will build a development version, start a
local webserver on port `3000`, and open the URL in the browser.

In order to perform a release build, run `npm run build`. This will build the frontend and place
the static files in the `zephyr-frontend/build` directory, where they can be placed on the desired
hosting platform.

#### Windows and Linux premade startup scripts

Premade scripts have been provided to start the frontend dev server on Windows and Linux. Simply run
`startFrontend.bat` or `./startFrontend.sh` from the `Scripts` folder.

### Credentials

To use this demo, the default credentials have been kept to allow one to traverse the web application and to test the overall functionality. A stock CSV File has been added to allow the use of these user credentials, to test more than one user, open the CSV File located in the "Scripts" folder and choose a name along with their default password. A few credentials are as follows (case sensitive):

| Account Type | UserName | Password |
| ------ | ------ | ------ |
| Administrator | admin | 1admin |
| User | Amare Small | rdasda |
| User | Mariela Vega | hbhjhb |
| User | Jacob Forbes | iytio8o |
