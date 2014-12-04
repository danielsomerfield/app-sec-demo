# AppSec Demo 
This is an simple application for demonstrating different kinds of vulnerabilities in web application development. It is
a very simple phonebook-like application. It is implemented just enough to demonstrate the vulnerabilities, but not necessarily
to be a complete app; for example, the delete function doesn't actually update the backend store (although that can be enabled 
by uncommented a single line of code).

By the way, this app was designed to be fairly self-contained. There are no demonstrations of forced browsing or command injection
however, it is, after all, a vulnerable app, so I wouldn't recommend running in on a public wifi with your firewall off. 
And you should turn your firewall on anyway.

Running the Application
-----------------------

## Locally
*You need*:

- JDK 1.8

*Steps*:
- Check the source code out from git hub
    `git clone https://github.com/danielsomerfield/app-sec-demo.git`
- Run the application using gradlew
    `cd app-sec-demo`
    `./gradlew tR`
    

## On Heroku
*You need*:
- An account on (heroku)[TODO URL]
- The (heroku tools)[TODO URL]

*Steps*:
- Log in to heroku
    `heroku login`
- Check the source code out from git hub
    `git clone https://github.com/danielsomerfield/app-sec-demo.git`
- Create the app
    `cd app-sec-demo`
    `heroku create <name-for-your-app>`
- Set the remote URL (TODO: do you need to do this in all cases?)
    `heroku git:remote -a <name-for-your-app>`
- Deploy the app
    git push heroku master
- Browse to the app at `http[s]://<name-for-your-app>.herokuapp.com/AppSecDemo/demo/`

Misc Notes
----------
## Enabling the delete function
Because this application was designed for a multiple concurrent user demo, allowing writes would make demonstration 
difficult. Any time someone managed to blow away database records or even drop a tables, the app would break, making
it impossible for others to use it. With that in mind, I have made the application read-only. In order to make the application
read/write so you can try out those functions and see the kind of damage that can be wrought, you need to make two changes:

- Uncomment the call to directoryService.delete() in DirectoryServiceController.deleteEntry().
- Remove the "ACCESS_MODE_DATA=r" string in ApplicationConfiguration.jdbcTemplate()

At that point, the application will be writable and you can do all the damage to your own instance that you wish!