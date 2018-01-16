Sample story

Narrative:
In order to communicate effectively to the business some functionality
As a development team
I want to use Behaviour-Driven Development
					 
Scenario:  Testing page 5 TableFilterDemo
Given I wanna set my driver
Given I wanna go to table filter page
When I click on entries filter
Then I wanna see 50 entries
When I sort table by age
When I click on search box
And I provide London for filter
Then I wanna see Showing 1 to 7 of 7 entries (filtered from 32 total entries) and J. Gaines Office Manager London 30 Fri 19th Dec 08 $90,560/y below the Table
Given Finally I want to close table filter page
