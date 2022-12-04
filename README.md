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
| **Figure 4: An example of a complex query.** |

##  3. System Snapshots

In this section, we will show the forms of the implemented feature that aim to help users to set up their preferences. Figure 5 shows the main services provided in the "fuzzy query preparation" tab, which as :
* Linguistic Variables: responsible for managing linguistic variables like creating, modifying, showing, or deleting 
* Linguistic Values: responsible for managing linguistic values like creating and associating it to a specific linguistic variable, modifying, showing, or deleting
* Fuzzy Predicates: responsible for managing fuzzy predicates such as creating one by selecting a column from a table and linking it to a specific linguistic variable to build a fuzzy view of this table.

| ![Fuzzy query preparation tab services.](https://i.ibb.co/jvnT5jq/fuzzy-query-preparation-tab.png) | 
|:--:| 
| **Figure 5: Fuzzy query preparation tab services.** |

All these services are already presented in detail in section "The fuzzy querying interface" of our published paper(www.shorturl.at/klpv6) , which describes the developed application based on our first approach. Figure 6 and Figure 7, respectively, show the forms used to manage linguistic values and fuzzy predicates. Whilst, the linguistic variable service is the default service of this tab as shown in Figure 5.

| ![Linguistic values service.](https://i.ibb.co/WntKpy6/linguistic-values-service.png) | 
|:--:| 
| **Figure 6: Linguistic values service.** |

| ![Fuzzy predicates service.](https://i.ibb.co/ZG7DX15/fuzzy-predicates-service.png) | 
|:--:| 
| **Figure 7: Fuzzy predicates service.** |

The "semantic query preparation" tab is done to help the user to define their semantic preferences. It contains two main services (see Figure 8 ) :
* Ontology Manager: in which the user can choose the ontology that will represent the domain of an attribute that will be semantically queried.
* Semantic Predicates: responsible for managing semantic predicates.

| ![Semantic query preparation tab services.](https://i.ibb.co/bmr0YVD/semantic-query-preparation-tab.png) | 
|:--:| 
| **Figure 8: Semantic query preparation tab services.** |

Figure 8 shows the form used to manage ontologies. The user starts by choosing a name for the ontology. Then he is invited to choose their type and enter his reference or generate it via file explorer. All this information will be conserved in the database semantic catalog. A list of all prepared ontologies is displayed at the bottom of the form.

The form shown in Figure 9 is used to allow the user to add, update, or delete a semantic predicate. At the top of the form, the user has the required options to create a new semantic predicate. The user starts by choosing a column of a selected table and linking it with an existing prepared ontology by choosing their type and reference. Also, the user is invited to select from a list a semantic measure that will be used to calculate the semantic similarity degree between a search term and all values of the selected column. Besides each semantic predicate of this list, there are two buttons that may be used to update or delete a semantic predicate.

| ![Semantic predicates service.](https://i.ibb.co/z4L2x2g/semantic-predicates-service.png) | 
|:--:| 
| **Figure 9: Semantic predicates service.** |
