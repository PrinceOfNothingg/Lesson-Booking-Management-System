# Offerings System Sequence Diagrams

## Tools

[Plant UML](https://plantuml.com/sequence-diagram)

## View Offering

<details>
<summary>
View Offering plantUml Code
</summary>

``` plantuml
@startuml
participant "__:Actor__" as actor
participant "__:System__" as system

title View Offerings System Sequence Diagram

skinparam sequenceMessageAlign center
actor -> system : getOfferings()
|||
alt pulic user
|||
  system --> actor : display available & non-available offerings associated to an instructor
|||
else is registered client
|||
  system --> actor : display available & non-available offerings associated to an instructor
|||
else is registered guardian
|||
  system --> actor : display available & non-available offerings associated to an instructor
|||
else is registered instructor
|||
  system --> actor : display offerings not associated to an instructor
end

@enduml
```

</details>

![image](https://github.com/DarkMed15/soen342-teamproject/blob/main/deliverables/assets/view_offering_system_sequence_diagram.png)

## Create Offering

<details>
<summary>
Create Offering plantUml Code
</summary>

``` plantuml
@startuml
participant "__:Actor__" as actor
participant "__:System__" as system
participant "__:Offerings__" as offerings
collections "__:Offering__" as offeringCollection
participant "__o:Offering__" as offering



title Create Offerings System Sequence Diagram

skinparam sequenceMessageAlign center

actor -> system : makeOffering(location, schedule, format, type)

system -> offerings : exists(location,schedule)

alt unique slot
system -> offerings : createOffering(location, schedule, format, type, seats)
offerings -> offering** : create(location, schedule, format, type, seats)
offerings -> offeringCollection : add(o)
|||
else location daytime slot not unique
|||
offerings --> system : error
system --> actor : error
end

@enduml
```

</details>

![image](https://github.com/DarkMed15/soen342-teamproject/blob/main/deliverables/assets/create_offering_system_sequence_diagram.png)

## Delete Offering

<details>
<summary>
Delete Offering plantUml Code
</summary>

``` plantuml
@startuml
participant "__:Actor__" as actor
participant "__:System__" as system

title Delete Offerings System Sequence Diagram

skinparam sequenceMessageAlign center

actor -> system : removeOffering(offering)

alt if client associated
  system -> system : deleteBooking(client)
|||
else if instructor associated
|||
  system -> system : deleteInstructorOffering(instructor)
|||
end

system -> system : offering.delete()
system --> actor : confirmation
@enduml
```

</details>

![image](https://github.com/DarkMed15/soen342-teamproject/blob/main/deliverables/assets/delete_offering_system_sequence_diagram.png)

## Take on Offering

<details>
<summary>
Take Offering plantUml Code
</summary>

``` plantuml
@startuml
participant "__:Actor__" as actor
participant "__:System__" as system

title Take Offerings System Sequence Diagram

skinparam sequenceMessageAlign center

|||
actor -> system : takeOffering(offering)
|||
system -> system : not exists(offering at same location and time slot)

alt success
system -> system : createInstructorOffering(instructor, offering)
system -> system : offering.taken=true
system -> system : offering.status="available"
system -> actor : confirmation
|||
else not unique
system -> actor : error
end
@enduml
```

</details>

![image](https://github.com/DarkMed15/soen342-teamproject/blob/main/deliverables/assets/take_offering_system_sequence_diagram.png)
