import Link from "next/link";

export default function Home() {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-background text-foreground">
      <header className="text-center mb-8">
        <h1 className="text-5xl font-bold mb-4">Welcome to Our Organization</h1>
        <p className="text-xl">Offering professional lessons in various disciplines</p>
      </header>
      <main className="flex flex-col items-center">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
          <Link href="/register-client" className="p-4 bg-blue-500 text-white rounded-lg text-center hover:bg-blue-600 transition">
            Register as Client
          </Link>
          <Link href="/register-instructor" className="p-4 bg-green-500 text-white rounded-lg text-center hover:bg-green-600 transition">
            Register as Instructor
          </Link>
          <Link href="/view-offerings" className="p-4 bg-purple-500 text-white rounded-lg text-center hover:bg-purple-600 transition">
            View Offerings
          </Link>
          <Link href="/login" className="p-4 bg-gray-500 text-white rounded-lg text-center hover:bg-gray-600 transition">
            Login
          </Link>
        </div>
      </main>
      <footer className="mt-auto py-4">
        <p className="text-center">
          &copy; {new Date().getFullYear()} Our Organization. All rights reserved.
        </p>
      </footer>
    </div>
  );
}
