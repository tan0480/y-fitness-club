# Y Fitness Club

A Java console application developed as an object-oriented programming assignment. It demonstrates member, staff, coach, and administrator workflows for a fitness club. This is separate from the team's Oracle SQL Fitness Centre Database project.

## Features

- Member and coach registration, role-specific login, and console menus.
- Membership plans, renewals, and simulated payment records.
- Fitness classes, capacity checks, membership eligibility checks, bookings, and weekly schedules.
- Staff and coach shift arrangements, equipment availability, and equipment status management.
- Member progress records, BMI calculation, and management reports.

## Build and run

Install a JDK with `javac` and `java` on PATH. The original 23 source files were verified with JDK 25.0.2 on Windows. No external libraries, Maven, or Gradle are required.

From the repository root in PowerShell:

```powershell
New-Item -ItemType Directory -Path build -Force | Out-Null
$sources = Get-ChildItem -LiteralPath 'Source Code' -Filter '*.java' -File
javac -encoding UTF-8 -d build $sources.FullName
java -cp build Driver
```

The application starts at a console menu. Register a member or coach, select the corresponding login option, or choose `0` to exit. `Driver.java` also contains preset local staff/admin accounts for the assignment demonstration. Do not reuse those credentials outside this demo or enter real personal information.

## Structure

- `Source Code/`: 23 Java source files in the default package; `Driver.java` is the entry point.
- `README.md`: setup, scope, and contribution mapping.
- `.gitignore`: excludes compiled output, IDE settings, documents, and local secrets.

## Coding contributions and import groups

The following grouping was confirmed for importing the existing assignment. Shared files receive co-author credit instead of being represented as one person's exclusive work.

| Group | Author / co-authors | Files in `Source Code/` |
| --- | --- | --- |
| Shared models and system support | Tan Yi Heng; Lam Yi Hen, Lim Jun Yi, and Cheah Yong Shun as co-authors | `User.java`, `Gender.java`, `FitnessClub.java`, `Progress.java`, `Report.java` |
| Membership management | Lam Yi Hen; Tan Yi Heng and Lim Jun Yi as co-authors for booking/payment integration | `Member.java`, `Membership.java`, `MembershipPlan.java`, `MembershipStatus.java`, `MembershipType.java` |
| Payments | Lim Jun Yi | `Payment.java` |
| Staff, coach, and resource management | Cheah Yong Shun; Tan Yi Heng as co-author for class-scheduling integration | `Admin.java`, `Coach.java`, `Staff.java`, `Shift.java`, `Equipment.java`, `EquipStatus.java` |
| Classes and bookings | Tan Yi Heng | `Booking.java`, `BookingStatus.java`, `FitnessClass.java`, `ClassType.java`, `WeeklySchedule.java` |
| Console integration | Tan Yi Heng; Lam Yi Hen, Lim Jun Yi, and Cheah Yong Shun as co-authors | `Driver.java` |

Contributors: [Tan Yi Heng](https://github.com/tan0480), [Lam Yi Hen](https://github.com/lamyihen77-beep), [Lim Jun Yi](https://github.com/ljunyi0212), and [Cheah Yong Shun](https://github.com/ys012007).

Tan Yi Heng performs the repository import and push. Commit authors and `Co-authored-by` trailers credit the confirmed contributions; the import timestamps are not the original development dates. Early import commits contain incomplete sets of interdependent classes and are not standalone buildable snapshots.

## Scope and limitations

- Data is held in memory and is lost when the application exits. No database or persistent file storage is configured.
- Payments are simulated; no banking or payment gateway is contacted.
- Passwords and preset account details are plain text. This is an educational prototype, not a production authentication system.
- The original implementation uses fixed-capacity arrays and has not been hardened for production use.
- BMI and progress features are educational, not medical advice.

## Verification

The original source was preserved byte-for-byte. All 23 files compiled with JDK 25.0.2, and a smoke check passed for startup, main-menu display, and normal exit.

`javac -Xlint:all` reported 16 existing warnings, chiefly `equals()` without matching `hashCode()` overrides and a possible constructor-time `this` escape. No fixes were applied during import. Full registration, payment, booking, and scheduling workflows have not been verified end to end.

Assignment PDFs and generated build/test output are intentionally not included.
