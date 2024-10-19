# Use Case Scenarios

<table>
  <tr>
   <td>UC1
   </td>
   <td>Register as Client
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Client
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Client is not registered
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>Client type account is created.
   </td>
  </tr>
  <tr>
   <td>Inputs
   </td>
   <td>Name, phone, age
   </td>
  </tr>
  <tr>
   <td>Outputs
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits client registration page
<p>
Actor enters information
<p>
Actor submits request
<p>
System validates
<p>
System creates client account
<p>
System displays account page
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>If the account already exists, display an error.
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC2
   </td>
   <td>Register as Instructor
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Instructor
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Instructor is not registered
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>Instructor type account is created.
   </td>
  </tr>
  <tr>
   <td>Inputs
   </td>
   <td>Name, phone, age, specializations
   </td>
  </tr>
  <tr>
   <td>Outputs
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits instructor registration page
<p>
Actor enters information
<p>
Actor submits request
<p>
System validates
<p>
System creates instructor account
<p>
System displays account page
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>If the account already exists, display an error.
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC3
   </td>
   <td>Instructor adds their availabilities
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Instructor
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Instructor is authenticated
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>Associations between instructor and citie(s) is created
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits availability management page
<p>
System displays available cities
<p>
Actor selects cities
<p>
Actor submits
<p>
System add availabilities to actor
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC4
   </td>
   <td>Login
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Client, instructor, administrator
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Actor is not logged in
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>Actor is authenticated
   </td>
  </tr>
  <tr>
   <td>Inputs
   </td>
   <td>credentials
   </td>
  </tr>
  <tr>
   <td>Outputs
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits login page
<p>
Actor enters credentials
<p>
Actor submits auth request
<p>
System authenticates
<p>
System displays homepage
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>If authentication fails, display an error.
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC5
   </td>
   <td>Delete Account
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Administrator
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Administrator is authenticated
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>- System deletes associated bookings
<p>
- System deletes associations between instructor and offerings
<p>
- System deletes the account
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits account management page
<p>
System retrieves and displays all accounts
<p>
Actor selects a client or instructor account
<p>
Actor deletes account
<p>
System prompts for confirmation
<p>
System deletes selected account and related resources
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>If actor cancels at prompt, the system does not delete the account, and returns to the account management page.
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC6
   </td>
   <td>Create Offerings
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Administrator (Organization)
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Administrator is authenticated
<p>
Space/Location exists and is available at given daytime slot(s)
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>LocationSchedule is created
<p>
Lesson is created
<p>
Association between lesson and locaitonSchedule is created
<p>
Offering is created
<p>
Association between offering and lesson is created
   </td>
  </tr>
  <tr>
   <td>Inputs
   </td>
   <td>location, schedule, lesson, lessonFormat(group,private,both), LessonType(swimming, yoga, soccer…)
   </td>
  </tr>
  <tr>
   <td>Outputs
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offerings management page
<p>
System retrieves and displays all offerings
<p>
Actor selects create offering
<p>
System displays offering creation page
<p>
Actor enters information
<p>
Actor submits form
<p>
System validates information
<p>
System creates a private and/or group offering
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>If the location-schedule for that space doesn’t exist, display an error.
<p>
If the lesson location-schedule is not unique, display an error.
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC7
   </td>
   <td>Delete Offerings
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Administrator
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Instance of offering exists
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>Association between lesson and locationSchedule is deleted
<p>
LocationSchedule is deleted
<p>
Lesson is deleted
<p>
Association between offering and instructor is deleted.
<p>
Association between offering and clients is deleted.
<p>
Offering is deleted.
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offerings management page
<p>
System retrieves and displays all offerings
<p>
Actor selects offering
<p>
Actors selects delete
<p>
Actor submits
<p>
System prompts for confirmation
<p>
Actor confirms
<p>
System deletes offering
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>If actor cancels, the system does not delete the offering, and returns to the offering management page
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC8
   </td>
   <td>Take on offerings
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Instructor
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Instructor is authenticated
<p>
Instance of offering exists
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>Association between instructor and offering is created.
<p>
Offering status is updated to available.
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offerings page
<p>
System retrieves and displays all offerings
<p>
Actor selects offering(s) to take on
<p>
Actor submits
<p>
System prompts for confirmation
<p>
Actor confirms
<p>
System adds offerings to instructor
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC9
   </td>
   <td>View Offerings as Public
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Public
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Actor Is not registered nor authenticated
<p>
Offering(s) has association with an instructor
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offering page
<p>
System retrieves and displays all offerings (available, non-available) that have an instructor associated
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC10
   </td>
   <td>View Offerings as client
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Client
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Actor is authenticated.
<p>
Offering(s) has association with an instructor
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offering page
<p>
System retrieves and displays all offerings (available, non-available) that have an instructor associated.
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC11
   </td>
   <td>View Offerings as instructor
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Instructor
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Actor is authenticated.
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offering page
<p>
System retrieves and displays all offerings made available by organization.
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC12
   </td>
   <td>Create Booking
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Client
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>User is a client
<p>
Client is authenticated
<p>
Offering(s) with available seats exist.
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>Booking is created.
<p>
System updates private offering(s) status to non-available.
<p>
System updates group offerings’ number of seats available.
<p>
System updates group offerings’, when full, to non-available.
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offerings page
<p>
System retrieves and displays offerings
<p>
Actor selects 1 or more offerings
<p>
Actor submits selection
<p>
System locks seat for bookings temporarily
<p>
System prompts for submit confirmation
<p>
Actor confirms
<p>
System attempts to create booking(s)
<p>
System outputs confirmation message
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>If no seats are available, the system indicates an error.
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC13
   </td>
   <td>View Booking Details
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Client, Administrator
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Actor is registered and authenticated
<p>
Booking(s) exist
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits bookings page
<p>
System retrieves and displays bookings
<p>
Actor selects a booking
<p>
System displays booking details
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC14
   </td>
   <td>Cancel Booking
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Client, Administrator
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>Is registered and authenticated
<p>
Booking(s) exist
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>Association between booking and client is deleted.
<p>
Association between booking and offering is deleted.
<p>
Booking is deleted
<p>
Private offering is made available.
<p>
Group offerings’ number of seats is updated.
<p>
Non-available group offerings are made available.
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>- Actor visits bookings page
<p>
- System retrieves and displays bookings
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>
   </td>
  </tr>
</table>
