# FQSFO
Flexible System for querying relational databases using fuzzy logic and ontlogies

This Wiki is dedicated to reflecting on various characteristics of the developed system. It covers system documentation, some illustrative examples, and some snapshots
##  1. System Overview
Many features have been implemented in the developed system to achieve a more flexible system with a friendly interface. In this section, we will show the main page of our system and its functionality; other features will be discussed in the following sections.

To enable the system functionalities, a connection to a specific database should be established, and if the chosen database has not yet been set up, the user must set it up using prepare database button. The system retains successful connections to databases to reuse them again. But, if it's a new connection, a helper form is created to help the user in building a connection string in order to connect to a database that will be used in answering his queries.

The system's main page contains multiple tabs as shown in the Figure 1. The default "Database connection" tab is used to manage database connections. The "Semantic query preparation" tab is used to Setting up fuzzy query parameters like the choice of an ontology and associate it with a column of a selected table to define his semantic domain. The next tab offers a terminal to write and execute flexible queries and show the results. The "Flexible query builder" tab is used to assist build flexible queries easily. The last tab is used to help the user understand the various features of the system. Finally, the various forms supported by the system are covered in the rest of this Wiki.
| ![ystem’s main page after a successful connection to a database.](https://i.ibb.co/vB5Wtph/system-main-page.png) | 
|:--:| 
| **Figure 1: System’s main page after a successful connection to a database** |

##  2. Query Examples and Results
Recall that the developed system can handle three main types of query conditions, namely simple, fuzzy, and semantic. Also, it can handle the query, whether simple or complex. All valid queries are expected to return a result that may be either a set of records or a single value. A number of examples are presented in this section to demonstrate the output produced by different types of queries.


Figure 2 shows an example of a fuzzy query that was performed to find the new movies with a threshold of 0.5. As previously discussed, the system analyzes the SQLf query and shows a generated SQL  query. In this kind of query, the system just changes the syntax of the fuzzy condition to be directly handled by DBMS.
| ![An example of a fuzzy query.](https://i.ibb.co/kgYjzN7/fuzzy-query-example.png) | 
|:--:| 
| **Figure 2: An example of a fuzzy query** |

Figure 3 and Figure 4, respectively, show examples of semantic and complex query types that involve all types of supported conditions. The semantic condition is also analyzed and processed to become a Boolean one.
Figure 3 and Figure 4, respectively, show examples of semantic and complex query types that involve all types of supported conditions. The semantic condition is also analyzed and processed to become a Boolean one.

| ![An example of a semantic query.](https://i.ibb.co/CnQSRRL/semantic-query-example.png) |
|:--:| 
| **Figure 3: An example of a semantic query** |
| ![An example of a complex query.](https://i.ibb.co/bsBG9wW/complex-query-example.png) | 
|:--:| 
| **Figure 4: n example of a complex query.** |

##  3. System Snapshots

In this section, we will show the forms of the implemented feature that aim to help users to set up their preferences. Figure 5 shows the main services provided in the "fuzzy query preparation" tab, which as :
* Linguistic Variables: responsible for managing linguistic variables like creating, modifying, showing, or deleting 
* Linguistic Values: responsible for managing linguistic values like creating and associating it to a specific linguistic variable, modifying, showing, or deleting
* Fuzzy Predicates: responsible for managing fuzzy predicates such as creating one by selecting a column from a table and linking it to a specific linguistic variable to build a fuzzy view of this table.

All these services are already presented in detail in section "The fuzzy querying interface" of our published paper(shorturl.at/klpv6) , which describes the developed application based on our first approach. Figure \ref{fig:linguistic_values_service} and Figure \ref{fig:fuzzy_predicates_service}, respectively, show the forms used to manage linguistic values and fuzzy predicates. Whilst, the linguistic variable service is the default service of this tab as shown in Figure \ref{fig:fuzzy_query_preparation_tab}.


