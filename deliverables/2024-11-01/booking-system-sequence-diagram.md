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

participant "__:Bookings__" as bookings
collections "__:Booking__" as bookingCollection
participant "__booking:Booking__" as booking

title Create Booking System Sequence Diagram
skinparam sequenceMessageAlign center

|||
actor -> system : makeBooking(client, offering)
system -> system : client.bookings.schedule -> not contains(offering.schedule)

alt no overlap
|||
system -> bookings : createBooking(client, offering)
bookings -> booking** : create(instructor, offering)
bookings --> bookings : offering.setStatus("non-available")
bookings -> bookingCollection : add(booking)

bookingCollection --> bookings : confirmation
bookings --> system : confirmation
system --> actor : confirmation
|||
else overlap
|||
system --> actor : error
|||
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

participant "__:Bookings__" as bookings
collections "__:Booking__" as bookingCollection

participant "__:Offerings__" as offerings

title Cancel Booking System Sequence Diagram
skinparam sequenceMessageAlign center

|||
actor -> system : cancelBooking(client, booking)
system -> system : booking.clientId = client.id

alt client owns booking
|||
system -> bookings : deleteBooking(client, booking)
bookings --> system : confirmation
system -> offerings : offering := get(booking.offeringId)
system --> system : offering.seats++, offering.setStatus("available")
system --> actor : confirmation
|||
else not owns
|||
bookings --> system : error
system --> actor : error
end

@enduml
```

</details>

![image](https://github.com/DarkMed15/soen342-teamproject/blob/main/deliverables/assets/cancel_booking_system_sequence_diagram.png)
