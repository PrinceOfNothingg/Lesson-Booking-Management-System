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

title Create Offerings System Sequence Diagram

skinparam sequenceMessageAlign center


actor -> system : beginMakeOfferingSession()
|||
actor -> system : makeOffering(location, schedule, format, type)


system -> system : validate()

alt success
  system -> system : lesson : createLesson(location, schedule, format, type)
  system -> system : createOffering(lesson)
  system --> actor : confirmation
  system --> actor : display offering
|||
else location daytime slot not available
|||
  system --> actor : error
|||
else location daytime slot not unique
|||
  system --> actor : error
end

actor -> system : endSession()

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
system --> actor : prompt()
|||
alt confirm
|||
actor -> system : confirm()
else cancel
|||
system --> actor : exit and display offerings
end

|||
system -> system : delete(lesson)
|||

alt if client associated
  system -> system : delete(client-offering)
|||
else if instructor associated
|||
  system -> system : delete(instructor-offering)
|||
end

system -> system : delete(offering)
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
system --> actor : prompt()
|||
actor -> system : confirm()

alt success
  system -> system : add(instructor, offering)
  system -> actor : confirmation
|||
else cancel
|||
  system -> actor : exit and display offerings
|||
end
@enduml
```

</details>

![image](https://github.com/DarkMed15/soen342-teamproject/blob/main/deliverables/assets/take_offering_system_sequence_diagram.png)
