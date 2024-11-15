# Object Constraint Language (OCL)

## 1. Uniqueness of Offerings

"Offerings are unique. In other words, multiple offerings on the same day and time slot must be offered at a different location."

```
context OfferingCatalog
inv:
    self.offerings -> forAll(o1, o2 : Offering |
        o1 <> o2 implies 
        (o1.location = o2.location implies o1.timeSlot <> o2.timeSlot))
```

## 2. Underage Client with Guardian

"Any client who is underage must necessarily be accompanied by an adult who acts as their guardian."

```
context Client
inv: 
    self.age < 18 implies self.guardian -> not isEmpty()
```

## 3. Instructor Availability for City 

"The city associated with an offering must be one the city's that the instructor has indicated in their availabilities."

```
context Offering
inv:
    self.instructor.availability -> includes(self.location.city)
```

## 4. No Overlapping Bookings for Client

"A client does not have multiple bookings on the same day and time slot."

```
context Client
inv: 
    self.bookings -> forAll(b1, b2 : Booking |
        b1 <> b2 implies (b1.offering.timeSlot <> b2.offering.timeSlot))
```


```
context Instructor
inv: 
    self.offerings -> forAll(o1, o2 : Offering | 
        o1 <> o2 implies (o1.timeSlot <> o2.timeSlot))
```

## 5. Schedule Time Consistency

For a given schedule, the endTime must be after the startTime.

```
context Schedule
inv: 
    self.startTime < self.endTime
```

No overlapping schedules:

```
context ScheduleCatalog
inv: 
    self.schedules -> forAll(s1, s2 : Schedule |
        s1 <> s1 implies (s1.timeSlot <> s1.timeSlot))
```

## 6. Instructor Specialization for Lesson Type

An instructor is specialized in the type of lesson they are assigned to in the Offering.

```
context Offering
inv: 
    self.instructor.specializations -> includes(self.lesson.type)
```

## 7. Location-Schedule Association Consistency

Each LocationSchedule association only has one unique pair of Location and Schedule.

```
context LocationSchedule
inv:
    self.locationSchedules -> forAll(ls1, ls2 : LocationSchedule | 
        ls1 <> ls2 implies (ls1.location = ls2.location implies ls1.schedule <> ls2.schedule))
```
```
