# TODO
- ADD password protected page (and username / password table) that is susceptible to:
    - OR 1 = 1 
    - Leakage via UNION

- Make DB connection read-only
    - String url = "jdbc:h2:~/test;ACCESS_MODE_DATA=rws";
    - http://www.h2database.com/html/features.html
    
- Find example injections
    - SQL injection
    - Login in via OR 1 = 1
    - %3b = ;
    
    
- XSS: no result for ""