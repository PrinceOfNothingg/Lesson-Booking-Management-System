"use client";

import { useState } from "react";

export default function RegisterInstructor() {
  const [name, setName] = useState("");
  const [phone, setPhone] = useState("");
  const [specializations, setSpecializations] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    // Handle registration 
  };

  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-background text-foreground">
      <h1 className="text-2xl font-bold mb-4">Register as Instructor</h1>
      <form onSubmit={handleSubmit} className="flex flex-col gap-4">
        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
          className="p-2 border border-gray-300"
        />
        <input
          type="text"
          placeholder="Phone"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
          className="p-2 border border-gray-300"
        />
        <input
          type="text"
          placeholder="Specializations"
          value={specializations}
          onChange={(e) => setSpecializations(e.target.value)}
          className="p-2 border border-gray-300"
        />
        <button type="submit" className="p-2 bg-green-500 text-white">
          Register
        </button>
      </form>
    </div>
  );
}