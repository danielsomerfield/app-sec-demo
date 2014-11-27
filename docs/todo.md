# TODO
- Reject delete calls if not logged in
- Make sure we have request methods on everything

# Done
- ADD password protected page (and username / password table) that is susceptible to:
    - OR 1 = 1 
    - Leakage via UNION: (Talk about error message being a tip off)
        asdf' UNION ALL SELECT id, username, password_hash FROM application_user --

- Make DB connection read-only
    - String url = "jdbc:h2:~/test;ACCESS_MODE_DATA=rws";
    - http://www.h2database.com/html/features.html
    
- Find example injections
    - SQL injection
    - Login in via aasdf' OR 1 = 1 --
    - %3b = ;
    
    
- XSS
    - Direct link to entry that is JS injectable
