# Bookings System Sequence Diagrams

## Tools

[Plant UML](https://plantuml.com/sequence-diagram)

## View Booking Details

<details>
<summary>
View Booking Details plantUml Code
</summary>

``` plantuml
@startuml
participant "__:Actor__" as actor
participant "__:System__" as system

title View Booking Details System Sequence Diagram

skinparam sequenceMessageAlign center
actor -> system : getBookings()
|||
system --> actor : bookings
|||
actor --> system : getBookingDetails(booking_id)
|||
system --> actor : booking details

@enduml
```

</details>

![image](https://github.com/DarkMed15/soen342-teamproject/blob/main/deliverables/assets/view_booking_details_system_sequence_diagram.png)

## Create Booking

<details>
<summary>
Create Booking plantUml Code
</summary>

``` plantuml
@startuml
participant "__:Client__" as actor
participant "__:System__" as system

title Create Booking System Sequence Diagram
skinparam sequenceMessageAlign center

actor -> system : getOfferings()
|||
system --> actor : offerings
|||
actor -> system : makeBooking(offering)
|||
system --> system : lock offering
|||
system --> actor : prompt
|||
alt confirms
actor --> system : confirm

system --> system : booking := create(offering, client)
system --> system : update and unlock offering

system --> actor : confirmation
else cancels
|||
system --> system : unlock offering
system --> actor : show offerings

end

@enduml
```

</details>

![image](https://github.com/DarkMed15/soen342-teamproject/blob/main/deliverables/assets/create_booking_system_sequence_diagram.png)

## Cancel Booking

<details>
<summary>
Cancel Booking plantUml Code
</summary>

``` plantuml
@startuml
participant "__:Client__" as actor
participant "__:System__" as system

title Cancel Booking System Sequence Diagram
skinparam sequenceMessageAlign center

actor -> system : getbookings(client)
|||
system --> actor : client bookings
|||
actor -> system : cancelBooking(booking)
|||
system --> actor : prompt
|||
alt confirms
actor --> system : confirm

system --> system : delete(booking)
system --> actor : confirmation
|||
else cancels
actor --> system : cancel
system --> actor : show bookings

end
@enduml
```

</details>

![image](https://github.com/DarkMed15/soen342-teamproject/blob/main/deliverables/assets/cancel_booking_system_sequence_diagram.png)
