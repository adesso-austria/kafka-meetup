Hello,

I have the great pleasure of speaking to you tonight on the subject of "Exactly once processing".

First I will introduce my employer and myself. Afterwards I will describe the problems & present possible solutions. As soon as we have the dry theory part behind us, I will show you a scenario and code. Finally, we can clarify open questions in a short Q&A session.

adesso Austria GmbH is an IT service provider that accompanies its customers as a strong partner from requirements engineering to rollout in all project stages.

My name is Dominik Dorfstetter and I am a full-stack software engineer. If you would like to stay in contact with me after my presentation, please follow me on Twitter or on GitHub.

In the by nature loose-coupled world of micro-services we have to take care of some issues to guarantee that requests are processed. We distinguish between "at-least-once", "at-most-once" and "exactly-once" processing.

If we have to rely on the fact that we process a request exactly once because of business requirements, we have to take precautions. Examples would be order processes or bank transfers. It means that our systems that transmit data and those who process the data must work closely together.

In exactly-once processing we have to make sure that our operations are idempotent, otherwise inconsistencies will occur in our system, and we have to take care of deduplicating our messages.

An idempotent operation is one that can be executed many times without causing any effect other than being executed once. The Http 1.1 standard defines idempotency as follows. GET, HEAD, PUT and DELETE would be examples of idempotent operations whereas POST is an example of a non-idempotent operation.

How can we face these problems?

Since our producing and consuming systems need to work closely together, we need to take care of establishing an end-to-end guarantee.

There are several possible solutions to this problem, and I will now introduce you to a few of them. Distributed transaction logs, including LRU caches, Bloom filters or the decision of the consumer to read only completed transactions.

In order to create idempotency we can use atomic transactions or perform our operations in a workflow engine.

A picture is worth a thousand words and for this reason I have prepared a scenario and would like to talk about its architecture.

We are dealing with a production data acquisition and specifically in this example with wind turbines. The sensor data is sent to a Kafka broker and processed by Spring-Boot based micro services. In the end, this data is made available to an Angular Dashboard via an API.

* Explanation Diagram

With this we have finished the theoretical part and I will show you this now with a live demo.

* Live demo

* Q&A session

* Adoption
