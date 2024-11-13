# Booking Use Cases

<table>
  <tr>
   <td>UC15
   </td>
   <td>Register as Guardian
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
   <td>Guardian type account is created.
   <p>Client dependant accounts are created.
   <p>Client dependants are associated to Guardian.
   </td>
  </tr>
  <tr>
   <td>Inputs
   </td>
   <td>Name, phone, age, dependant details
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
<p>Actor enters personal information
<p>Actor adds one or more dependants
<p>Actor enters dependants information
<p>Actor submits request
<p>System validates
<p>System creates guardian account
<p>System creates client accounts for dependants
<p>System creates association between guardian and dependants
<p>System displays account page
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
   <td>UC16
   </td>
   <td>Create Booking as Guardian
   </td>
  </tr>
  <tr>
   <td>Primary Actor
   </td>
   <td>Guardian
   </td>
  </tr>
  <tr>
   <td>Preconditions
   </td>
   <td>User is a guardian
<p>Guardian is authenticated
<p>Offering(s) with available seats exist.
<p>Client does not have another booking at the same location at the same schedule.
   </td>
  </tr>
  <tr>
   <td>PostConditions
   </td>
   <td>Booking is created.
<p>Association between booking and offering is created.
<p>Association between booking and client dependant is created.
<p>System updates offerings’ number of seats available.
<p>System updates offerings’ status, when full, to non-available.

   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offerings page
<p>System retrieves and displays offerings
<p>Actor selects 1 or more offerings
<p>Actor selects "register to dependant"
<p>Actor selects a dependant
<p>Actor submits selection
<p>System prompts for submit confirmation
<p>Actor confirms
<p>System locks seat for selected offering(s) temporarily
<p>System creates booking(s)
<p>System updates offering status, seats, and unlocks for selected offering(s)
<p>System outputs confirmation message
   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>If canceled, the system unlocks the offering(s) and returns to the offering page.
   </td>
  </tr>
</table>
