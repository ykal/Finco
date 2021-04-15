# Finco
Financial corporation framework

**Sub-Systems**

- Framework

This is an abstraction of Financial Corporate called FinCo.

- Bank

A financial corporate application implemented using the FinCo framework.

- Creditcard

A financial corporate application implemented using the FinCo framework.

**_Components of The Projects_**

*RuleSystem*

We used the Rule Pattern to manage and abstract business rules dynamically[1].

*Model*

We Implemented a Factory Method to manage the creation of models[Accounts and Customers].

*Persistance*

We have Classes to simulate the persistence of models to a file.

*GUI*

We used Observer Pattern to synchronize data between the Persistance Controller
and our Swing GUI.

*References*
A Pattern Language for Adaptive and Scalable Business Rule Construction, Ali Arsanjani
Business rules tend to change more frequently than the rest of the business object
with which they are associated. These rules are typically implemented within the
rule methods of a business object. Rules also refer to other business objects that
their encompassing business object associates with; creating a web of implicit and
increasingly un-maintainable dependencies. This pattern language provides a set of
patterns that address the increasing need for handling scalability, adaptability and
complexity.
