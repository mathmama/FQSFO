# FQSFO
Flexible System for querying relational databases using fuzzy logic and ontlogies

This Wiki is dedicated to reflecting on various characteristics of the developed system. It covers system documentation, some illustrative examples, and some snapshots
##  1. System Overview
Many features have been implemented in the developed system to achieve a more flexible system with a friendly interface. In this section, we will show the main page of our system and its functionality; other features will be discussed in the following sections.

To enable the system functionalities, a connection to a specific database should be established, and if the chosen database has not yet been set up, the user must set it up using prepare database button. The system retains successful connections to databases to reuse them again. But, if it's a new connection, a helper form is created to help the user in building a connection string in order to connect to a database that will be used in answering his queries.

The system's main page contains multiple tabs as shown in the figure 1. The default `` Database connection '' tab is used to manage database connections. The ``Semantic query preparation `` tab is used to Setting up fuzzy query parameters like the choice of an ontology and associate it with a column of a selected table to define his semantic domain. The next tab offers a terminal to write and execute flexible queries and show the results. The `` Flexible query builder '' tab is used to assist build flexible queries easily. The last tab is used to help the user understand the various features of the system. Finally, the various forms supported by the system are covered in the rest of this Wiki.
![ystem’s main page after a successful connection to a database.](https://i.ibb.co/vB5Wtph/system-main-page.png)

##  2. Query Examples and Results
