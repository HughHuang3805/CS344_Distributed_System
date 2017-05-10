Exam Day

Students come to school (simulated by sleep of random time) and wait in front of the
classroom for the instructor to arrive. Each student should take three exams. The exams
are scheduled every 2 hours and each exam takes 1 hour. Four exams are
administrated throughout the day.

The instructor allows students to enter the classroom 15 minutes before the exam starts
(use notifyAll). Students enter the classroom up to the classroom’s capacity. If the
classroom is full or if the student didn’t make it by the time the exam starts, we consider
that the student missed the exam. The student will wait outside the classroom to take the
next exam at the next available time.

Once in the classroom, students take a seat at a table (students that sit at the same
table should wait on the same object). Each table has numSeats seats. All the tables,
but the very last one must have all seats occupied.

When it’s time to start the exam, the instructor hands out the exams (by signaling the
students). Then, the students and the instructor wait for the exam to end.
When the exam ends, each student takes a brief time to check their notes (simulated by
sleep(random time) about 5 units). Next, the student will return the exam by signaling the
instructor and will wait for the instructor to grade it (each student blocks on a different
object; use an implementation similar to the one in rwcv.java). It will take the instructor
a very brief (random) time (up to 2 units) to check an exam. After each exam is checked,
the instructor will assign a grade and notify the student (FCFS order).
Next, the instructor and students will get ready for the next exam.

At the end of the day, the exam grades for each student should be displayed. If a
student missed an exam, his grade will be 0.
If a student took three exam can terminate. The instructor will terminate after all four
exams are administrated.