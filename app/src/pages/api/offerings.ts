import type { NextApiRequest, NextApiResponse } from "next";

interface Schedule {
  id: number;
  startDate: string;
  endDate: string;
  startTime: string;
  endTime: string;
}

interface Location {
  id: number;
  name: string;
  city: string;
}

interface Instructor {
  id: number;
  name: string;
  specialization: string[];
}

interface Lesson {
  id: number;
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


const offerings: Offering[] = [
  {
    id: 1,
    status: "Open",
    lesson: { id: 1, type: "Yoga", status: "Available" },
    instructor: { id: 1, name: "Alice", specialization: ["Yoga", "Wellness"] },
    schedule: { id: 1, startDate: "2024-10-01", endDate: "2024-12-01", startTime: "06:00", endTime: "07:00" },
    location: { id: 1, name: "Main Studio", city: "New York" }
  },
  {
    id: 2,
    status: "Open",
    lesson: { id: 2, type: "Swimming", status: "Available" },
    instructor: { id: 2, name: "Bob", specialization: ["Swimming", "Fitness"] },
    schedule: { id: 2, startDate: "2024-10-01", endDate: "2024-12-01", startTime: "08:00", endTime: "09:00" },
    location: { id: 2, name: "Pool", city: "New York" }
  },
  {
    id: 3,
    status: "Open",
    lesson: { id: 3, type: "Pilates", status: "Available" },
    instructor: { id: 3, name: "Charlie", specialization: ["Pilates", "Wellness"] },
    schedule: { id: 3, startDate: "2024-10-01", endDate: "2024-12-01", startTime: "10:00", endTime: "11:00" },
    location: { id: 3, name: "Main Studio", city: "New York" }
  },
  // Add more
];

export default function handler(req: NextApiRequest, res: NextApiResponse) {

  if (req.method === "GET") {
    res.status(200).json(offerings);
  } else {
    res.status(405).json({ message: "Method not allowed" });
  }
}
