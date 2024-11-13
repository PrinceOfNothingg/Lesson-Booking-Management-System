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
   <p> name and phone are a unique combination
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
System displays client account page
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>If the account already exists ({name,phone} not unique), display an error.
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
   <p>name and phone are a unique combination
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
   <td>Name, phone, age, specializations, availabilities
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
Actor enters information:
<br>- unique name and phone
<br>- specializations
<br>- availabilities
<p>
Actor submits request
<p>
System validates
<p>
System creates instructor account
<p>
System displays instructor account page
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>If the account already exists ({name,phone} not unique), display an error.
   </td>
  </tr>
</table>

<table>
  <tr>
   <td>UC3
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
   <td>UC4
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
   <td>- If any, system deletes association between bookings and client
<p>- If any, System deletes associations between guardian and clients
<p>-- System updates dependant clients to inactive
<p>- If any, System deletes associations between instructor and offerings
<p>-- System updates offerings status to non-available
<p>- System deletes the account
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits account management page
<p>
System retrieves and displays all accounts
<p>
Actor selects an account
<p>
Actor deletes account
<p>
System deletes selected account and related resources
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
   <td>UC5
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
<p>Location exists.
<p>Schedule exists.
<p>{Location,Schedule} is unique and is not already assigned to another offering.
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>LocationSchedule is created
<p>Offering(s) is created
<p>Association between offering(s) and locationSchedule(s) is created
   </td>
  </tr>
  <tr>
   <td>Inputs
   </td>
   <td>location, schedule, format(group,private,both), LessonType(swimming, yoga, soccer…), seats
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
<p>Actor selects create offering
<p>Actor enters data
<p>Actor submits
<p>System validates data
<p>System creates an offering based on entered data
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
   <td>UC6
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
   <td>Association between offering and locationSchedule is deleted
<p>LocationSchedule is deleted
<p>Association between offering and instructor is deleted.
<p>Association between offering and clients (booking) is deleted.
<p>Offering is deleted.
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offerings management page
<p>System retrieves and displays all offerings
<p>Actor selects offering
<p>Actors selects delete
<p>Actor submits
<p>System deletes offering
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
   <td>
   </td>
   <td>Remove Offerings from instructor
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
<p>Offering must be associated to instructor
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>
<p>Association between offering and instructor is deleted.
<p>Association between offering and clients (booking) is deleted.
<p>Offering status and seats are updated
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offerings management page
<p>System retrieves and displays all offerings
<p>Actor selects offering
<p>Actors selects remove
<p>Actor submits
<p>System deletes offering
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
   <td>UC7
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
<p>Instructor specialization match Offering type
<p>Instructor availability match Offering location
<p>Instance of offering exists
<p>Instructor has not already taken an offering at the same time and location.
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>Association between instructor and offering is created.
<p>Offering status is updated to available.
   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offerings page
<p>System retrieves and displays all offerings
<p>Actor selects offering(s) to take on
<p>Actor submits
<p>Actor confirms
<p>System checks uniqueness, then adds offerings to instructor
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
   <td>UC8
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
There exist Offering(s) with an association to an instructor
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
   <td>UC9
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
<p>There exist Offering(s) with an association to an instructor
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
   <td>UC10
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
<p>System retrieves and displays all offerings not taken that have been made available by organization.
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>
   </td>
  </tr>
</table>
