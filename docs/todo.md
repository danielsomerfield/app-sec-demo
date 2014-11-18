# TODO
- Reject delete calls if not logged in
- Make sure we have request methods on everything
- Get rid of extra console logging
- Remove (or replace with logging) all cases of System.out.println
- Check class, methods, variables for "final"

- Move spring initialization of application resources to the app context

- ADD password protected page (and username / password table) that is susceptible to:
    - OR 1 = 1 
    - Leakage via UNION: (Talk about error message being a tip off)
        asdf' UNION ALL SELECT id, username, password_hash FROM application_user --

- Make DB connection read-only
    - String url = "jdbc:h2:~/test;ACCESS_MODE_DATA=rws";
    - http://www.h2database.com/html/features.html
    
- Find example injections
    - SQL injection
    - Login in via OR 1 = 1
    - %3b = ;
    
    
- XSS: no result for "attacker data"
    - Create direct linking to search query
    - Added "no result" text to table