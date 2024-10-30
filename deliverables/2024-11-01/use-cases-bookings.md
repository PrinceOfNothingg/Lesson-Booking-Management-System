# Booking Use Cases

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
<p>Booking is created.
<p>Association between booking and offering is created.
<p>Association between booking and client is created.
<p>System updates private offering(s) status to non-available.
<p>System updates group offerings’ number of seats available. 
<p>System updates group offerings’, when full, to non-available. 

   </td>
  </tr>
  <tr>
   <td>Main Success Scenario
   </td>
   <td>Actor visits offerings page
<p>
<p>Actor visits offerings page
<p>System retrieves and displays offerings
<p>Actor selects 1 or more offerings
<p>Actor submits selection
<p>System locks seat for selected offering(s) temporarily
<p>System prompts for submit confirmation
<p>Actor confirms
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
<p>Booking(s) exist
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
   <td>
<p>Actor visits bookings page
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
   <td>Client
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
   <td>
<p>Association between booking and client is deleted.
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
   <td>
<p>Actor visits bookings page
<p>System retrieves and displays bookings
<p>Actor selects a booking
<p>Actor submits cancel request
<p>System prompts for submit confirmation
<p>Actor confirms
<p>System attempts to cancel booking
<p>System outputs confirmation message

   </td>
  </tr>
  <tr>
   <td>Extensions (alt. flows)
   </td>
   <td>
   </td>
  </tr>
</table>
