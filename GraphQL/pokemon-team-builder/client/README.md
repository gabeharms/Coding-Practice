## Welcome to "Battling Data with GraphQL & React" Dev Academy Course

### Please Start With Day 01

- Day 01 ~ Course & Homework: https://ryanyogan.gitbooks.io/graphql-dev-academy/content/
- Day 01 ~ Project Board: https://github.com/ryanyogan/pokemon-team-picker/projects/1

### Running The Application

- Clone the repository `git clone git@github.com:ryanyogan/pokemon-team-picker.git`
- `cd pokemon-team-picker && git fetch --all && git checkout DAY-01-START && yarn install`
- `yarn start`

### Log

```
DAY-01 - Gabe Harms
-------------------
What did I learn today?

What problems does GraphQL solve?
* I don't know how my data is stored, but I know the format/structure I want it in
* Many times, I have to hit many rest endpoints in order to get all the information I need for my UI
* Nested relationships have to be built dynamically via the response of several rest endpoints
* There are many attributes in my server response that I will not use and do not need
* In order to get all the data I need, I have to N+1 over my API endpoints
* As a client, I do not care how the server stores my data
* As a client, when I need new attributes to be made available, I have to make a pull request to
    the API endpoint backend code base
* As a client, when a change to an API endpoint is made, my code breaks
* As a client, caching is hard
* As an application developer, I want my backend services to be decoupled from the front end
* As an application developer, I want to serve data from multiple services to the client as one request
* As a developer, I want to be able to navigate through API documentation easily

What am I going to add to the app?


What do I feel stuck on?


DAY-02 - Gabe Harms
-------------------
What did I learn today?
    * A lot about the syntax of GQL. Such as, adding 'query queryName' is optional and only necessary when you have arguments in your query that need to be specified. When querying a field that is actually a subtype, you cannot only query that field, you must specify which field within that subtype that you want. example can't just say weight, have to say weight { minimum }. When variables exist in the query, a type must be assigned to them.
    * About the Query component. I love this abstraction! The render props pattern makes it very obvious to the consumer what is happening. ryan was talking about how these might becoming more favorable than HOCs. I think that makes sense. HOCs tend to bury the behavior added, and make it difficult for me to navigate through components. I'm also wondering if the Query component would be useful outside of graphql. Seems like an abstraction that could assist with any kind of client implementation. I am wondering how the Query component scales when considering a data intensive app like ours; where everything your rendering is data. and every part of the app needs access to data from the server.




What am I going to add to the app?


What do I feel stuck on?
```
