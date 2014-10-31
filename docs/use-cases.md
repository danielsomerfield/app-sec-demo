# Use (misuse) Cases

## Demonstrate Injection

### Options
- SQL Injection
Filter user phone db. Query is injectable but read-only. This allows showing other tables, for example.

## Demonstrate XSS

## Demonstrate Poor Session Management

### Ideas

#### Ineffective logout
- Login page invalidates cookie but doesn't kill session
#### Sequential session IDs
- Attacker can modify cookie value to capture session
