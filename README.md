# Zephyr

## Introduction

Shareholder voting is an essential part of a corporate or small business
operation, and in-person voting is a common method used to conduct these votes.
However, online voting has become more commonplace, and the importance of having
options that are not in-person has increased due to COVID-19. In addition,
online voting can be more economical and easier to aggregate. Privacy

## Background

This product will be developed to be a web-based client-server application that
will allow shareholders of company stock to securely vote on the board of
directors. In addition, the voters will have the assurance that their votes are
fully anonymous, thus they cannot be tracked or identified by their votes. The
system will allow the vote results to be aggregated together and will have
support for weighted voting, as well as determining who has not participated in
the voting.

## Expected requirements

### User stories

- As a shareholder, I want to make a vote on who will be on the board of directors.
- As a shareholder, I want to be able to see the results of polls that have
  concluded.
- As a shareholder, I should not be able to see the preliminary results of polls
  that have not yet been - mpleted.
- As an administrator, I want to be able to create new polls for voting on by
  shareholders.
- As an administrator, I want to be able to see who has participated in a poll,
  but not their vote.
- As an unauthorized user, I should not be able to see the results of or vote in
  polls.

### Main components

- Database to store votes and accounts
- Front end to allow users to vote
- Components to ensure security of vote and anonymity of vote
- Server to host the website front end

## Anticipated development

The Agile development model will be the model used for this project. The
characteristics of this model are to provide an iterative approach to
development. The scope of the project along with its requirements are presented
at the beginning of development, and each sprint will iterate on each
requirement and feature. This development model was chosen to allow freedom for
the developers on features to implement, while keeping a clear goal on the final
objective.

## Preliminary assignment of team members

The agile development model defines three major roles: the product owner, the
scrum master, and the developers. The product owner sets the direction of the
project and the goal to achieve. The scrum master keeps the team on track and
focused on completing the objective in a timely manner. The developers are
managed by the scrum master to develop the product.

- Tyler Willey - Team Lead / Product Owner / Developer (Database/Server
  Side/Security)
- Austin McCall - Scrum Master / Developer (Database/Server Side/Security)
- Ryan Gonzalez - Developer (Client Side/Testing)
- Thomas Pryor - Developer (Client Side/Testing)

### Roles

- Team Lead/Product Owner: While also being a developer and coding, will keep
  the team organized and on route with the objective for a particular week while
  steering towards the final end goal of the product.
- Scrum Master: Ensuring the team stays within the agreed upon timeline while
  helping the team stay productive. Also ensures that all Scrum Events (Sprint
  Planning, Sprint, and Sprint Review) are completed correctly and on time.
- Developers: Deal with most of the coding, check for security compliance and
  test the program, both the front and back ends.

## Anticipated testing strategies and tools

For the final product, individual testing must be done on actual voting of
election polls for the board of directors, as well as testing for multiple
shareholders voting on the same poll and not seeing the result of the poll until
voting has concluded while keeping all information related to the shareholders
private. For these tests, simple Acceptance testing will be done.

JUnit will be used for automated backend testing, and Postman will be used for
additional backend experimentation and manual testing. These two tools were
chosen due to the team’s existing familiarity with them, as well as their
prevalent use within the Java and backend development ecosystems, respectively.
On the client side, Jest is chosen to test the web application, also due to its
prevalence and widespread support within the client-side testing ecosystem.

## Project timeline and tracking

Using the week this paper is submitted as the starting week (Week 5 in the
Tentative Schedule): by Week 8 for Presentation 1 we would like to have a decent
chunk of the backend completed and at least a crude first representation of the
front-end website to present. This is subject to change obviously. Weeks 9, 10,
and 11 will have a group report for the work completed during that week, similar
to a sprint but for 1 week. The final presentation on Week 15 will be the
culmination of work completed during weeks 5 to 14. For tracking of the timeline
to keep the team focused on objectives and features, the team will use ORA, a
software similar to Trello.

## Project risk/success

The project for Zephyr will be considered a success if it meets all requirements
stated in the user stories and use cases. This project will also be considered a
success if the objective of shareholders voting, both securely and anonymously,
for the board of directors via a website is completed.

Potential stumbling blocks:

- Making the web page responsive and friendly for mobile browsers.
  - Backup plan: web page is desktop only.
- Unexpected difficulties with selected database, SQL.
  - Backup plan: use MongoDB instead.
- Unexpected difficulties in self-hosting the project.
  - Backup plan: use an external hosting service.
