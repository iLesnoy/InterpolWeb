Management agency for the search for criminals and missing persons "Interol"
-

The web system is an Internet representation of the agency for managing the search for criminals and missing persons.
The system contains several sections: a directory of wanted criminals, missing persons and a news feed for users. 
Administrator maintains a list of wanted criminals and missing persons, appoints a reward, maintains a news feed. 
User fills out a Request for Search with an indication of the possible execution time and remuneration, if any.

User roles and functions available to them:
-

| Function                       | Admin   | Guest | User  |
| -----------------              |:-------:|:-----:|:-----:|
| Change Locale                  |  *      |  *    | *     | 
| Сhange personal information    |  *      |       | *     |
| Go to personal account         |  *      |       | *     |
| Update applications & news     |  *      |       |       |
| Update application status      |  *      |       |       |
| Update user role               |  *      |       |       |
| Block/Unblock user             |  *      |       |       |
| View all active applications   |  *      |  *    | *     |
| Delete applications & news     |  *      |       |       |
| Cancel applications            |  *      |       | *     |
| Add(applications & news)       |  *      |       |       |
| Accept applications            |  *      |       | *     |
| Go to personal account         |  *      |       | *     |
| Add applications & news        |  *      |       |       |
| Find users by parameters       |  *      |       |       |
| Find search applications       |  *      |       |       |
| signup                         |  *      |  *    |       |
| logout                         |  *      |       |       |
-
Application lifecycle
1) The user selects a suitable search application, fills it in: indicates the approximate time of execution.
2) If the data was entered correctly by the user, the application will receive the status "in processing" and will not appear in the user's personal account until it is approved by the administrator.
3) After the application is confirmed by the administrator and its status is updated to active, it begins to be displayed in the user's personal account.
4) The user has the right to cancel the search for an application at any time.
-
Database tables
-
![My db](https://github.com/iLesnoy/InterpolWeb/blob/master/src/main/resources/Interpol_db.png?raw=true)
