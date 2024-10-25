"use client";

import { useEffect, useState } from "react";

export default function ViewOfferings() {
  interface Schedule {
    startDate: string;
    endDate: string;
    startTime: string;
    endTime: string;
  }

  interface Location {
    name: string;
    city: string;
  }

  interface Instructor {
    name: string;
    specialization: string[];
  }

  interface Lesson {
    type: string;
    status: string;
  }

  interface Offering {
    id: number;
    status: string;
    lesson: Lesson;
    instructor: Instructor;
    schedule: Schedule;
    location: Location;
  }

  const [offerings, setOfferings] = useState<Offering[]>([]);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    // Fetch offerings from the API
    fetch("/api/offerings")
      .then((res) => {
        if (!res.ok) {
          throw new Error(`Network response was not ok: ${res.statusText}`);
        }
        return res.json();
      })
      .then((data) => setOfferings(data))
      .catch((error) => {
        console.error("Error fetching offerings:", error);
        setError(`Failed to fetch offerings: ${error.message}`);
      });
  }, []);

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-background text-foreground">
      <h1 className="text-2xl font-bold mb-4">Available Offerings</h1>
      {error && <p className="text-red-500">{error}</p>}
      <ul className="list-disc">
        {offerings.map((offering) => (
          <li key={offering.id} className="mb-4">
            <h2 className="text-xl font-bold">{offering.lesson.type} ({offering.status})</h2>
            <p>{offering.lesson.status}</p>
            <p><strong>Instructor:</strong> {offering.instructor.name} - Specialization: {offering.instructor.specialization.join(", ")}</p>
            <p><strong>Schedule:</strong> {offering.schedule.startDate} to {offering.schedule.endDate}, {offering.schedule.startTime}-{offering.schedule.endTime}</p>
            <p><strong>Location:</strong> {offering.location.name}, {offering.location.city}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}