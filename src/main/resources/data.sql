-- ===============================
-- USERS TABLE FIXES
-- ===============================

-- Ensure verified has default value
ALTER TABLE users
ALTER COLUMN verified SET DEFAULT false;

-- Fix existing rows with NULL verified
UPDATE users
SET verified = false
WHERE verified IS NULL;

-- ===============================
-- OPTIONAL: ADMIN SEED (COLLEGE SAFE)
-- ===============================
-- Only insert if not exists
INSERT INTO users (mobile, verified)
SELECT '9999999999', true
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE mobile = '9999999999'
);

-- ===============================
-- SAFETY: CLEAR OLD OTPs ON START
-- ===============================
UPDATE users
SET otp = NULL;

-- ===============================
-- OPTIONAL: RESET TICKET STATES (DEV ONLY)
-- ===============================
-- Uncomment ONLY if you want clean dev state
-- UPDATE ticket SET status = 'ACTIVE' WHERE status IS NULL;